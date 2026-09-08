# Boot3 + JDK17 升级冒烟清单（功能不变验收）

分支：`upgrade/boot3-jdk17`  
基准：Spring Boot 2.7.18 / JDK8 → Spring Boot 3.4.x / JDK17  
原则：对外 REST 路径、请求/响应字段、鉴权 Header 保持不变。

## yshop-admin（AppRun）

| # | 场景 | 验收点 |
|---|---|---|
| 1 | 管理端登录 | 验证码、账号密码登录成功，返回 token |
| 2 | 鉴权与权限 | 带 token 访问受保护接口；无 token / 过期返回 401 |
| 3 | `@AnonymousAccess` | 匿名接口可访问；非匿名接口不可绕过 |
| 4 | 在线用户 | 在线用户列表、踢人下线 |
| 5 | 商品管理 | 商品列表/详情/上下架 |
| 6 | 订单管理 | 订单列表/详情/发货 |
| 7 | 文件上传 | 本地/OSS 上传成功可访问 |
| 8 | Druid 监控 | `/druid` 可打开（按现有放行策略） |
| 9 | 定时任务 Quartz | 任务列表、启停、执行一次 |
| 10 | API 文档 | `/doc.html` 可打开，接口分组与鉴权 Header 可用 |
| 11 | Redis 会话 | 登录态存 Redis，重启后按 TTL 策略一致 |
| 12 | WebSocket | 后台通知/在线通道连通（若启用） |

## yshop-app（ApiRun）

| # | 场景 | 验收点 |
|---|---|---|
| 1 | 用户登录/注册 | 手机号/微信登录签发 JWT |
| 2 | JWT 拦截 | 需登录接口校验 Header；公开接口可匿名 |
| 3 | 商品浏览 | 首页、分类、商品详情 |
| 4 | 购物车 | 加购、改数量、删除 |
| 5 | 下单主流程 | 确认订单 → 创建订单 → 订单详情 |
| 6 | 支付回调 | 微信支付回调验签与订单状态更新 |
| 7 | 微信相关 | 小程序/公众号配置读取、JSSDK/登录 |
| 8 | 限流/缓存 | Redis 限流与缓存读写正常 |
| 9 | 文件/图片 | 上传或静态资源访问 |
| 10 | API 文档 | `/doc.html` 可打开 |

## 已知可接受差异（非业务）

- 依赖版本、文档 UI / OpenAPI3 路径（仍以 `/doc.html` 为主入口）
- JDK17 日志格式差异
- springfox 相关 `ant_path_matcher` 配置已移除

## 编译验收

```bash
export JAVA_HOME=$(/usr/libexec/java_home -v 17)
mvn -pl yshop-admin,yshop-app -am clean package -DskipTests
```

**状态（upgrade/boot3-jdk17）：** `mvn -pl yshop-admin,yshop-app -am package -DskipTests` 已通过（Boot 3.4.3 / JDK 17）。

### 启动注意

- 使用 JDK 17：`export JAVA_HOME=$(/usr/libexec/java_home -v 17)`
- 若 Dozer 反射报 `InaccessibleObjectException`，启动时追加：

```bash
--add-opens java.base/java.lang=ALL-UNNAMED --add-opens java.base/java.lang.reflect=ALL-UNNAMED
```

- admin 登录 token 签发算法已适配 JJWT 0.12（secret 经 SHA-512 派生）；升级后需重新登录，旧 token 可能失效
- 文档入口仍为 `/doc.html`；OpenAPI 为 `/v3/api-docs`（分组时可能为 `/v3/api-docs/{group}`）

## 回归记录（本分支）

| 项 | 结果 |
|---|---|
| `mvn -pl yshop-admin,yshop-app -am package -DskipTests` | 通过 |
| `AppRun` 启动（dev） | 通过（~19s），Dozer/Redis/Quartz/Druid 正常 |
| `ApiRun` 启动（dev） | 通过（~17s），Dozer/Redis/微信配置加载正常 |
| admin `/`、`/doc.html`、`/druid/login.html` | HTTP 200 |
| app `/api/`、`/api/doc.html`、`/api/v3/api-docs` | HTTP 200 |
