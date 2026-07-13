# 使用说明

## Hbuilderx

- 已支持 HBuilderX 最新版

## 小程序安装步骤

- 先从私服上 clone 下来项目
- 下载 uni 的开发者工具 https://www.dcloud.io/hbuilderx.html
- 登录微信开发者工具打开 菜单 > 设置 > 安全设置 勾选服务端口为开启
- 当前项目下执行 npm install !!!!!!!!!必须执行，找不到模块都是因为这个原因导致的
- 命令行进入项目所在的目录，点击 hbuilderx > 菜单 > 运行 > 运行到小程序模拟器 > 微信开发者工具
- uni 会自动打开微信开发者工具并且打开 uni 的项目

## 注意事项

- uniapp v3.1 版本已经兼容 h5，另外 yshop 有自己的 H5，uniappv3.1 以下版本未对 H5 端进行处理，如果需要请自行兼容。
- manifest.json 中可以配置 uni 项目的一些信息
- 打开 manifest.json 可配置小程序的 appid
- 由于需要兼容 app，公共样式由 main.js 迁移到了 App.vue，公共样式请在 App.vue 中进行编辑
- 由于需要兼容 app，尺寸单位由之前的 rem 改为 rpx，由于修改样式工作量太大并且容易出错，已将.css 更改为.less 并在其中以之前 rem 的单位\*100，获得新的 rpx 单位
- 如需修改样式问题，请编辑.less 的文件，请勿编辑.css 的文件

## git issuse 地址

https://gitee.com/guchengwuyue/yshopmall

### 请按照以下模板提交 issuse

标题：
H5/uni/后端代码/后台管理系统+简单描述

问题端：
H5/uni/后端代码/后台管理系统

代码版本：
v3.0

代码运行环境：
windows/macOS/Linux

问题描述：
Ps:如果是 uni 出现问题，请具体描述是运行那个端小程序/android/ios

## 声明

- app 测试版已上，请通过 `https://www.pgyer.com/yRYf` 安装测试。
- 运行 app 项目 ios 需要安装 xcode，安卓需要装安卓的 sdk 以及安卓模拟器，建议安卓安装 genymotion https://www.genymotion.com/ 登录时可选择私人使用，勾选后无需付费
