

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `i_frame` bit(1) NULL DEFAULT NULL COMMENT '是否外链',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件',
  `pid` bigint(20) NOT NULL COMMENT '上级菜单ID',
  `sort` bigint(20) NOT NULL COMMENT '排序',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接地址',
  `cache` bit(1) NULL DEFAULT b'0' COMMENT '缓存',
  `hidden` bit(1) NULL DEFAULT b'0' COMMENT '是否隐藏',
  `component_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '-' COMMENT '组件名称',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `permission` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限',
  `type` int(11) NULL DEFAULT NULL COMMENT '类型',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKqcf9gem97gqa5qjm4d3elcqt5`(`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 226 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, b'0', '系统管理', NULL, 0, 99, 'system', 'system', b'0', b'0', NULL, '2018-12-18 15:11:29', NULL, 1);
INSERT INTO `menu` VALUES (2, b'0', '用户管理', 'system/user/index', 1, 2, 'peoples', 'user', b'0', b'0', 'User', '2018-12-18 15:14:44', 'user:list', 1);
INSERT INTO `menu` VALUES (3, b'0', '角色管理', 'system/role/index', 1, 3, 'role', 'role', b'0', b'0', 'Role', '2018-12-18 15:16:07', 'roles:list', 1);
INSERT INTO `menu` VALUES (5, b'0', '菜单管理', 'system/menu/index', 1, 5, 'menu', 'menu', b'0', b'0', 'Menu', '2018-12-18 15:17:28', 'menu:list', 1);
INSERT INTO `menu` VALUES (6, b'0', '系统监控', NULL, 0, 100, 'monitor', 'monitor', b'0', b'0', NULL, '2018-12-18 15:17:48', NULL, 1);
INSERT INTO `menu` VALUES (7, b'0', '操作日志', 'monitor/log/index', 6, 11, 'log', 'logs', b'1', b'0', 'Log', '2018-12-18 15:18:26', 'log:list', 1);
INSERT INTO `menu` VALUES (9, b'0', 'SQL监控', 'monitor/sql/index', 6, 14, 'sqlMonitor', 'druid', b'0', b'0', 'Sql', '2018-12-18 15:19:34', NULL, 1);
INSERT INTO `menu` VALUES (14, b'0', '邮件工具', 'tools/email/index', 36, 24, 'email', 'email', b'0', b'0', 'Email', '2018-12-27 10:13:09', NULL, 1);
INSERT INTO `menu` VALUES (18, b'0', '存储管理', 'tools/storage/index', 36, 23, 'qiniu', 'storage', b'0', b'0', 'Storage', '2018-12-31 11:12:15', 'storage:list', 1);
INSERT INTO `menu` VALUES (19, b'0', '支付宝工具', 'tools/aliPay/index', 36, 27, 'alipay', 'aliPay', b'0', b'0', 'AliPay', '2018-12-31 14:52:38', NULL, 1);
INSERT INTO `menu` VALUES (28, b'0', '定时任务', 'system/timing/index', 36, 21, 'timing', 'timing', b'0', b'0', 'Timing', '2019-01-07 20:34:40', 'timing:list', 1);
INSERT INTO `menu` VALUES (30, b'0', '代码生成', 'generator/index', 36, 22, 'dev', 'generator', b'0', b'0', 'GeneratorIndex', '2019-01-11 15:45:55', NULL, 1);
INSERT INTO `menu` VALUES (32, b'0', '异常日志', 'monitor/log/errorLog', 6, 12, 'error', 'errorLog', b'0', b'0', 'ErrorLog', '2019-01-13 13:49:03', 'logError:list,logError:detail', 1);
INSERT INTO `menu` VALUES (35, b'0', '部门管理', 'system/dept/index', 1, 6, 'dept', 'dept', b'0', b'0', 'Dept', '2019-03-25 09:46:00', 'dept:list', 1);
INSERT INTO `menu` VALUES (36, b'0', '系统工具', '', 0, 101, 'sys-tools', 'sys-tools', b'0', b'0', NULL, '2019-03-29 10:57:35', NULL, 1);
INSERT INTO `menu` VALUES (37, b'0', '岗位管理', 'system/job/index', 1, 7, 'Steve-Jobs', 'job', b'0', b'0', 'Job', '2019-03-29 13:51:18', 'user:list', 1);
INSERT INTO `menu` VALUES (39, b'0', '字典管理', 'system/dict/index', 1, 8, 'dictionary', 'dict', b'0', b'0', 'Dict', '2019-04-10 11:49:04', 'dict:list', 1);
INSERT INTO `menu` VALUES (40, b'0', '商品管理', '', 0, 1, 'shop', 'shop', b'1', b'0', '', '2019-10-03 17:40:19', NULL, 1);
INSERT INTO `menu` VALUES (41, b'0', '商品分类', 'shop/cate/index', 40, 11, 'icon', 'cate', b'0', b'0', 'Cate', '2019-10-03 17:42:35', 'cate:list,YXSTORECATEGORY_ALL,YXSTORECATEGORY_SELECT', 1);
INSERT INTO `menu` VALUES (45, b'0', '管理商品', 'shop/goods/tab', 40, 12, 'develop', 'goods', b'1', b'0', 'Goods', '2019-10-04 15:34:35', 'YXSTOREPRODUCT_SELECT,YXSTORECATEGORY_ALL,YXSTORECATEGORY_SELECT', 1);
INSERT INTO `menu` VALUES (46, b'0', '会员管理', '', 0, 2, 'peoples', 'member', b'0', b'0', '', '2019-10-06 16:18:05', NULL, 1);
INSERT INTO `menu` VALUES (47, b'0', '会员', 'shop/user/index', 46, 21, 'peoples', 'member', b'0', b'0', 'Member', '2019-10-06 16:20:17', 'YXUSER_SELECT', 1);
INSERT INTO `menu` VALUES (48, b'0', '微信管理', '', 0, 3, 'weixin', 'wechat', b'0', b'0', '', '2019-10-06 18:28:54', NULL, 1);
INSERT INTO `menu` VALUES (49, b'0', '微信菜单', 'wechat/menu/index', 48, 31, 'menu', 'wemenu', b'0', b'0', 'WeMenu', '2019-10-06 18:31:06', 'YxWechatMenu_ALL,YxWechatMenu_SELECT', 1);
INSERT INTO `menu` VALUES (50, b'0', '图文管理', 'wechat/article/index', 48, 32, 'article', 'wearticle', b'0', b'0', 'WeArticle', '2019-10-07 17:33:45', 'YXARTICLE_ALL,YXARTICLE_SELECT', 1);
INSERT INTO `menu` VALUES (51, b'0', '自动回复', 'wechat/reply/index', 48, 33, 'reply', 'wereply', b'0', b'0', 'Wereply', '2019-10-10 09:58:31', 'YXWECHATREPLY_ALL', 1);
INSERT INTO `menu` VALUES (52, b'0', '公众号配置', 'wechat/config/index', 48, 34, 'configure', 'weconfig', b'0', b'0', 'WeConfig', '2019-10-10 15:52:24', 'YXSYSTEMCONFIG_ALL', 1);
INSERT INTO `menu` VALUES (53, b'0', '订单管理', '', 0, 4, 'lock', 'order', b'0', b'0', '', '2019-10-14 14:35:18', NULL, 1);
INSERT INTO `menu` VALUES (54, b'0', '订单', 'shop/order/index', 53, 41, 'order', 'order', b'0', b'0', 'Order', '2019-10-14 14:36:28', 'YXSTOREORDER_SELECT', 1);
INSERT INTO `menu` VALUES (55, b'0', '商城配置', '', 0, 5, 'configure', 'set', b'0', b'0', '', '2019-10-18 15:21:26', NULL, 1);
INSERT INTO `menu` VALUES (56, b'0', '首页幻灯片', 'shop/set/index', 55, 51, 'banner', 'homeBanner', b'0', b'0', 'HomeBanner', '2019-10-18 15:24:30', 'YXSYSTEMGROUPDATA_ALL,YXSYSTEMGROUPDATA_SELECT', 1);
INSERT INTO `menu` VALUES (57, b'0', '首页导航按钮', 'shop/set/menu', 55, 52, 'button', 'homeMenus', b'0', b'0', 'HomeMenus', '2019-10-18 17:23:35', 'YXSYSTEMGROUPDATA_ALL,YXSYSTEMGROUPDATA_SELECT', 1);
INSERT INTO `menu` VALUES (59, b'0', '首页滚动新闻', 'shop/set/roll', 55, 54, 'news', 'roll', b'0', b'0', 'Roll', '2019-10-21 16:41:30', 'YXSYSTEMGROUPDATA_ALL,YXSYSTEMGROUPDATA_SELECT', 1);
INSERT INTO `menu` VALUES (60, b'0', '热门搜索', 'shop/set/hot', 55, 55, 'search', 'hot', b'0', b'0', 'Hot', '2019-10-26 18:21:54', 'YXSYSTEMGROUPDATA_ALL,YXSYSTEMGROUPDATA_SELECT', 1);
INSERT INTO `menu` VALUES (61, b'0', '个人中心菜单', 'shop/set/usermenu', 55, 56, 'menu', 'userMenu', b'0', b'0', 'UserMenu', '2019-10-26 18:42:18', 'YXSYSTEMGROUPDATA_ALL,YXSYSTEMGROUPDATA_SELECT', 1);
INSERT INTO `menu` VALUES (62, b'0', '评论管理', 'shop/reply/index', 53, 42, 'comment', 'reply', b'0', b'0', 'Reply', '2019-11-03 14:39:09', 'YXSTOREPRODUCTREPLY_SELECT', 1);
INSERT INTO `menu` VALUES (63, b'0', '营销管理', '', 0, 6, 'yingxiao', 'activity', b'0', b'0', '', '2019-11-09 14:17:42', NULL, 1);
INSERT INTO `menu` VALUES (64, b'0', '优惠券制作', 'activity/coupon/index', 63, 61, 'coupon', 'coupon', b'0', b'0', 'Coupon', '2019-11-09 14:18:58', 'YXSTORECOUPON_SELECT', 1);
INSERT INTO `menu` VALUES (65, b'0', '已发布优惠券', 'activity/couponissue/index', 63, 62, 'coupon2', 'couponissue', b'0', b'0', 'Couponissue', '2019-11-09 14:20:35', 'YXSTORECOUPONISSUE_SELECT', 1);
INSERT INTO `menu` VALUES (66, b'0', '优惠券领取记录', 'activity/storecouponuser/index', 63, 63, 'log', 'couponuser', b'0', b'0', 'Couponuser', '2019-11-09 14:21:35', 'YXSTORECOUPONUSER_SELECT', 1);
INSERT INTO `menu` VALUES (68, b'0', '积分配置', 'wechat/config/point', 55, 59, 'configure', 'pointConfig', b'0', b'0', 'PointConfig', '2019-11-10 18:45:55', 'YXSYSTEMCONFIG_SELECT', 1);
INSERT INTO `menu` VALUES (69, b'0', '分销管理', '', 0, 7, 'fenxiao', 'promoter', b'0', b'0', '', '2019-11-11 10:42:16', NULL, 1);
INSERT INTO `menu` VALUES (70, b'0', '分销配置', 'wechat/config/promoter', 69, 71, 'configure', 'promoterconfig', b'0', b'0', 'Promoterconfig', '2019-11-11 10:48:37', 'YXSYSTEMCONFIG_SELECT', 1);
INSERT INTO `menu` VALUES (71, b'0', '分销员', 'shop/user/aindex', 69, 72, 'user', 'agent', b'0', b'0', 'Agent', '2019-11-13 18:32:00', 'YXUSER_SELECT', 1);
INSERT INTO `menu` VALUES (72, b'0', '提现管理', 'activity/extract/index', 176, 81, 'tixian', 'extract', b'0', b'0', 'Extract', '2019-11-14 10:49:39', 'YXUSEREXTRACT_SELECT', 1);
INSERT INTO `menu` VALUES (73, b'0', '拼团产品', 'activity/combination/index', 63, 64, 'peoples', 'combination', b'0', b'0', 'Combination', '2019-11-18 14:23:04', 'YXSTORECOMBINATION_SELECT', 1);
INSERT INTO `menu` VALUES (74, b'0', '拼团列表', 'activity/combinlist/index', 63, 65, 'list', 'pink', b'0', b'0', 'Pink', '2019-11-21 19:35:58', 'YXSTOREPINK_SELECT', 1);
INSERT INTO `menu` VALUES (75, b'0', '微信支付配置', 'wechat/config/pay', 48, 35, 'wxpay', 'wxpayconfig', b'0', b'0', 'Wxpayconfig', '2019-11-28 17:06:22', 'YXSYSTEMCONFIG_ALL', 1);
INSERT INTO `menu` VALUES (76, b'0', '小程序配置', 'wechat/config/wxapp', 48, 36, 'configure', 'wxapp', b'0', b'0', 'Wxapp', '2019-11-29 15:13:46', 'YXSYSTEMCONFIG_ALL', 1);
INSERT INTO `menu` VALUES (77, b'0', '会员等级', 'shop/userlevel/index', 46, 22, 'dengji', 'userlevel', b'0', b'0', 'Userlevel', '2019-12-04 16:35:41', 'YXSYSTEMUSERLEVEL_SELECT', 1);
INSERT INTO `menu` VALUES (78, b'0', '等级任务', 'shop/usertask/index', 46, 23, 'task manege', 'usertask', b'0', b'0', 'Usertask', '2019-12-04 17:26:19', 'YXSYSTEMUSERTASK_SELECT', 1);
INSERT INTO `menu` VALUES (79, b'0', '签到天数配置', 'shop/set/sign', 55, 57, 'sign2', 'signday', b'0', b'0', 'Signday', '2019-12-05 14:12:16', 'YXSYSTEMGROUPDATA_ALL,YXSYSTEMGROUPDATA_SELECT', 1);
INSERT INTO `menu` VALUES (80, b'0', '用户账单', 'shop/user/bill', 46, 24, 'list', 'bill', b'0', b'0', 'Bill', '2019-12-11 17:28:38', 'YXUSERBILL_ALL,YXUSERBILL_SELECT', 1);
INSERT INTO `menu` VALUES (81, b'0', '物流快递', 'shop/express/index', 53, 43, 'express', 'express', b'0', b'0', 'Express', '2019-12-12 16:36:00', 'YXEXPRESS_SELECT', 1);
INSERT INTO `menu` VALUES (82, b'0', '微信模板消息', 'wechat/template/index', 48, 35, 'anq', 'template', b'0', b'0', 'Template', '2019-12-13 14:42:50', 'yxWechatTemplate:list', 1);
INSERT INTO `menu` VALUES (83, b'0', '秒杀产品', 'activity/seckill/index', 63, 66, 'seckill', 'seckill', b'0', b'0', 'Seckill', '2019-12-16 13:06:29', 'YXSTORESECKILL_SELECT', 1);
INSERT INTO `menu` VALUES (84, b'0', '秒杀配置', 'shop/set/seckill', 63, 67, 'configure', 'seckillconfig', b'0', b'0', 'Seckillconfig', '2019-12-16 16:07:42', 'YXSYSTEMGROUPDATA_SELECT', 1);
INSERT INTO `menu` VALUES (86, b'0', '砍价产品', 'activity/bargain/index', 63, 956, 'Sign', 'bargain', b'0', b'0', 'Bargain', '2019-12-22 12:25:55', 'YXSTOREBARGAIN_SELECT', 1);
INSERT INTO `menu` VALUES (87, b'0', '生成配置', 'generator/config', 36, 33, 'dev', 'generator/config/:tableName', b'1', b'1', 'GeneratorConfig', '2019-11-17 20:08:56', '', 1);
INSERT INTO `menu` VALUES (88, b'0', '生成预览', 'generator/preview', 36, 999, 'java', 'generator/preview/:tableName', b'1', b'1', 'Preview', '2019-11-26 14:54:36', NULL, 1);
INSERT INTO `menu` VALUES (116, b'0', '生成配置', 'generator/config', 36, 33, 'dev', 'generator/config/:tableName', b'1', b'1', 'GeneratorConfig', '2019-11-17 20:08:56', '', 1);
INSERT INTO `menu` VALUES (117, b'0', '图表库', 'components/Echarts', 10, 50, 'chart', 'echarts', b'1', b'0', 'Echarts', '2019-11-21 09:04:32', '', 1);
INSERT INTO `menu` VALUES (118, b'0', '新增', NULL, 45, 1, NULL, NULL, b'0', b'0', NULL, '2019-12-24 13:00:47', 'YXSTOREPRODUCT_EDIT', 2);
INSERT INTO `menu` VALUES (119, b'0', '修改', NULL, 45, 3, NULL, NULL, b'0', b'0', NULL, '2019-12-24 13:02:23', 'YXSTOREPRODUCT_CREATE', 2);
INSERT INTO `menu` VALUES (120, b'0', '删除', NULL, 45, 4, NULL, NULL, b'0', b'0', NULL, '2019-12-24 13:03:51', 'YXSTOREPRODUCT_DELETE', 2);
INSERT INTO `menu` VALUES (121, b'0', '在线用户', 'monitor/online/index', 6, 10, 'Steve-Jobs', 'online', b'0', b'0', 'OnlineUser', '2020-01-06 22:46:43', NULL, 1);
INSERT INTO `menu` VALUES (122, b'0', '浏览记录', 'monitor/log/mlog', 40, 13, 'log', 'viewlog', b'0', b'0', 'Viewlog', '2020-01-07 13:17:21', 'log:list', 1);
INSERT INTO `menu` VALUES (123, b'0', '后台接口文档', 'tools/swagger/index', 36, 31, 'swagger', 'swagger2', b'0', b'0', 'Swagger', '2020-01-07 18:05:52', NULL, 1);
INSERT INTO `menu` VALUES (124, b'0', '在线会员', 'monitor/online/indext', 46, 25, 'Steve-Jobs', 'onlinet', b'0', b'0', 'OnlineMember', '2020-01-13 10:53:07', NULL, 1);
INSERT INTO `menu` VALUES (125, b'0', '邮费配置', 'wechat/config/postage', 55, 58, 'configure', 'postageConfig', b'0', b'0', 'PostageConfig', '2020-02-13 15:38:24', 'YXSYSTEMCONFIG_SELECT', 1);
INSERT INTO `menu` VALUES (126, b'0', '编辑', NULL, 54, 1, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:05:28', 'YXSTOREORDER_EDIT', 2);
INSERT INTO `menu` VALUES (127, b'0', '用户新增', NULL, 2, 2, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:12:21', 'user:add', 2);
INSERT INTO `menu` VALUES (128, b'0', '用户编辑', NULL, 2, 3, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:12:47', 'user:edit', 2);
INSERT INTO `menu` VALUES (129, b'0', '用户删除', NULL, 2, 4, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:13:08', 'user:del', 2);
INSERT INTO `menu` VALUES (130, b'0', '角色创建', NULL, 3, 2, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:13:49', 'roles:add', 2);
INSERT INTO `menu` VALUES (131, b'0', '角色修改', NULL, 3, 3, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:14:11', 'roles:edit', 2);
INSERT INTO `menu` VALUES (132, b'0', '角色删除', NULL, 3, 999, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:14:38', 'roles:del', 2);
INSERT INTO `menu` VALUES (133, b'0', '菜单新增', NULL, 5, 2, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:15:05', 'menu:add', 2);
INSERT INTO `menu` VALUES (134, b'0', '菜单编辑', NULL, 5, 3, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:18:44', 'menu:edit', 2);
INSERT INTO `menu` VALUES (135, b'0', '菜单删除', NULL, 5, 4, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:19:05', 'menu:del', 2);
INSERT INTO `menu` VALUES (136, b'0', '部门新增', NULL, 35, 2, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:21:07', 'dept:add', 2);
INSERT INTO `menu` VALUES (137, b'0', '部门编辑', NULL, 35, 3, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:21:33', 'dept:edit', 2);
INSERT INTO `menu` VALUES (138, b'0', '部门删除', NULL, 35, 4, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:21:53', 'dept:del', 2);
INSERT INTO `menu` VALUES (139, b'0', '岗位新增', NULL, 37, 2, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:29:04', 'job:add', 2);
INSERT INTO `menu` VALUES (140, b'0', '岗位编辑', NULL, 37, 3, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:48:38', 'job:edit', 2);
INSERT INTO `menu` VALUES (141, b'0', '岗位删除', NULL, 37, 4, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:49:00', 'job:del', 2);
INSERT INTO `menu` VALUES (142, b'0', '字典新增', NULL, 39, 2, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:49:26', 'dict:add', 2);
INSERT INTO `menu` VALUES (143, b'0', '字典编辑', NULL, 39, 3, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:49:39', 'dict:edit', 2);
INSERT INTO `menu` VALUES (144, b'0', '字典删除', NULL, 39, 4, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:49:56', 'dict:del', 2);
INSERT INTO `menu` VALUES (147, b'0', '上传文件', NULL, 18, 2, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:53:49', 'storage:add', 2);
INSERT INTO `menu` VALUES (148, b'0', '文件编辑', NULL, 18, 3, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:54:06', 'storage:edit', 2);
INSERT INTO `menu` VALUES (149, b'0', '文件删除', NULL, 18, 4, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:54:27', 'storage:del', 2);
INSERT INTO `menu` VALUES (150, b'0', '任务新增', NULL, 28, 2, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:55:58', 'timing:add', 2);
INSERT INTO `menu` VALUES (151, b'0', '任务编辑', NULL, 28, 3, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:56:54', 'timing:edit', 2);
INSERT INTO `menu` VALUES (152, b'0', '任务删除', NULL, 28, 4, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:57:10', 'timing:del', 2);
INSERT INTO `menu` VALUES (153, b'0', '新增分类', NULL, 41, 2, NULL, NULL, b'0', b'0', NULL, '2020-02-14 22:00:41', 'YXSTORECATEGORY_CREATE', 2);
INSERT INTO `menu` VALUES (154, b'0', '分类编辑', NULL, 41, 3, NULL, NULL, b'0', b'0', NULL, '2020-02-14 22:01:15', 'YXSTORECATEGORY_EDIT', 2);
INSERT INTO `menu` VALUES (155, b'0', '分类删除', NULL, 41, 4, NULL, NULL, b'0', b'0', NULL, '2020-02-14 22:01:37', 'YXSTORECATEGORY_DELETE', 2);
INSERT INTO `menu` VALUES (156, b'0', '修改会员', NULL, 47, 2, NULL, NULL, b'0', b'0', NULL, '2020-02-14 22:03:40', 'YXUSER_EDIT', 2);
INSERT INTO `menu` VALUES (157, b'0', '等级新增', NULL, 77, 2, NULL, NULL, b'0', b'0', NULL, '2020-02-14 22:06:55', 'YXSYSTEMUSERLEVEL_CREATE', 2);
INSERT INTO `menu` VALUES (158, b'0', '等级编辑', NULL, 77, 3, NULL, NULL, b'0', b'0', NULL, '2020-02-14 22:08:03', 'YXSYSTEMUSERLEVEL_EDIT', 2);
INSERT INTO `menu` VALUES (159, b'0', '等级删除', NULL, 77, 4, NULL, NULL, b'0', b'0', NULL, '2020-02-14 22:08:41', 'YXSYSTEMUSERLEVEL_DELETE', 2);
INSERT INTO `menu` VALUES (160, b'0', '编辑任务', NULL, 78, 2, NULL, NULL, b'0', b'0', NULL, '2020-02-14 22:10:08', 'YXSYSTEMUSERTASK_EDIT', 2);
INSERT INTO `menu` VALUES (161, b'0', '评论删除', NULL, 62, 2, NULL, NULL, b'0', b'0', NULL, '2020-02-14 22:14:22', 'YXSTOREPRODUCTREPLY_DELETE', 2);
INSERT INTO `menu` VALUES (162, b'0', '新增物流', NULL, 81, 2, NULL, NULL, b'0', b'0', NULL, '2020-02-14 22:15:33', 'YXEXPRESS_CREATE', 2);
INSERT INTO `menu` VALUES (163, b'0', '编辑物流', NULL, 81, 3, NULL, NULL, b'0', b'0', NULL, '2020-02-14 22:15:53', 'YXEXPRESS_EDIT', 2);
INSERT INTO `menu` VALUES (164, b'0', '删除物流', NULL, 81, 4, NULL, NULL, b'0', b'0', NULL, '2020-02-14 22:16:11', 'YXEXPRESS_DELETE', 2);
INSERT INTO `menu` VALUES (165, b'0', '新增优惠券', NULL, 64, 2, NULL, NULL, b'0', b'0', NULL, '2020-02-14 22:18:32', 'YXSTORECOUPON_CREATE', 2);
INSERT INTO `menu` VALUES (166, b'0', '编辑优惠券', NULL, 64, 3, NULL, NULL, b'0', b'0', NULL, '2020-02-14 22:18:50', 'YXSTORECOUPON_EDIT', 2);
INSERT INTO `menu` VALUES (167, b'0', '删除优惠券', NULL, 64, 4, NULL, NULL, b'0', b'0', NULL, '2020-02-14 22:19:10', 'YXSTORECOUPON_DELETE', 2);
INSERT INTO `menu` VALUES (168, b'0', '编辑已发布', NULL, 65, 2, NULL, NULL, b'0', b'0', NULL, '2020-02-14 22:20:23', 'YXSTORECOUPONISSUE_EDIT', 2);
INSERT INTO `menu` VALUES (169, b'0', '删除已发布', NULL, 65, 3, NULL, NULL, b'0', b'0', NULL, '2020-02-14 22:20:42', 'YXSTORECOUPONISSUE_DELETE', 2);
INSERT INTO `menu` VALUES (170, b'0', '编辑拼团', NULL, 73, 2, NULL, NULL, b'0', b'0', NULL, '2020-02-14 22:24:15', 'YXSTORECOMBINATION_EDIT', 2);
INSERT INTO `menu` VALUES (171, b'0', '删除拼团', NULL, 73, 3, NULL, NULL, b'0', b'0', NULL, '2020-02-14 22:24:37', 'YXSTORECOMBINATION_DELETE', 2);
INSERT INTO `menu` VALUES (172, b'0', '编辑秒杀', NULL, 83, 2, NULL, NULL, b'0', b'0', NULL, '2020-02-14 22:25:23', 'YXSTORESECKILL_EDIT', 2);
INSERT INTO `menu` VALUES (173, b'0', '删除秒杀', NULL, 83, 3, NULL, NULL, b'0', b'0', NULL, '2020-02-14 22:25:41', 'YXSTORESECKILL_DELETE', 2);
INSERT INTO `menu` VALUES (174, b'0', '编辑砍价', NULL, 86, 2, NULL, NULL, b'0', b'0', NULL, '2020-02-14 22:26:20', 'YXSTOREBARGAIN_EDIT', 2);
INSERT INTO `menu` VALUES (175, b'0', '删除砍价', NULL, 86, 999, NULL, NULL, b'0', b'0', NULL, '2020-02-14 22:26:40', 'YXSTOREBARGAIN_DELETE', 2);
INSERT INTO `menu` VALUES (176, b'0', '财务管理', NULL, 0, 8, 'price', 'price', b'0', b'0', NULL, '2020-03-02 22:30:23', NULL, 1);
INSERT INTO `menu` VALUES (177, b'0', '充值管理', 'shop/recharge/index', 176, 82, 'rec', 'recharge', b'0', b'0', 'Recharge', '2020-03-02 23:05:26', 'yxUserRecharge:list', 1);
INSERT INTO `menu` VALUES (178, b'0', '门店管理', NULL, 0, 9, 'store', 'store', b'0', b'0', NULL, '2020-03-03 17:27:53', NULL, 1);
INSERT INTO `menu` VALUES (179, b'0', '门店列表', 'shop/store/index', 178, 92, 'edit', 'storeinfo', b'0', b'0', 'Storeinfo', '2020-03-03 17:29:09', 'yxSystemStore:list', 1);
INSERT INTO `menu` VALUES (180, b'0', '门店配置', 'shop/store/set', 178, 91, 'configure', 'storeset', b'0', b'0', 'Storeset', '2020-03-04 13:09:54', 'YXSYSTEMCONFIG_SELECT', 1);
INSERT INTO `menu` VALUES (181, b'0', '核销订单', 'shop/order/indext', 178, 95, 'order', 'ordert', b'0', b'0', 'Ordert', '2020-03-05 17:04:12', 'YXSTOREORDER_SELECT', 1);
INSERT INTO `menu` VALUES (182, b'0', '充值金额配置', 'shop/set/recharge', 55, 60, 'money', 'rechargeset', b'0', b'0', 'Rechargeset', '2020-03-21 14:24:05', 'YXSYSTEMGROUPDATA_ALL,YXSYSTEMGROUPDATA_SELECT', 1);
INSERT INTO `menu` VALUES (183, b'0', '店员列表', 'shop/storestaff/index', 178, 94, 'peoples', 'staff', b'0', b'0', 'Staff', '2020-03-22 14:11:36', 'yxSystemStoreStaff:list', 1);
INSERT INTO `menu` VALUES (184, b'0', '新增菜单', NULL, 49, 0, 'add', NULL, b'0', b'0', NULL, '2020-06-14 20:10:02', 'YxWechatMenu_CREATE', 2);
INSERT INTO `menu` VALUES (185, b'0', '模板新增', NULL, 82, 1, NULL, NULL, b'0', b'0', NULL, '2020-06-14 20:14:17', 'yxWechatTemplate:add', 2);
INSERT INTO `menu` VALUES (186, b'0', '模板修改', NULL, 82, 2, NULL, NULL, b'0', b'0', NULL, '2020-06-14 20:14:46', 'yxWechatTemplate:edit', 2);
INSERT INTO `menu` VALUES (187, b'0', '模板删除', NULL, 82, 3, NULL, NULL, b'0', b'0', NULL, '2020-06-14 20:15:10', 'yxWechatTemplate:del', 2);
INSERT INTO `menu` VALUES (188, b'0', '新增幻灯片', NULL, 56, 1, NULL, NULL, b'0', b'0', NULL, '2020-06-14 20:33:48', 'YXSYSTEMGROUPDATA_CREATE', 2);
INSERT INTO `menu` VALUES (189, b'0', '修改幻灯片', NULL, 56, 2, NULL, NULL, b'0', b'0', NULL, '2020-06-14 20:35:11', 'YXSYSTEMGROUPDATA_EDIT', 2);
INSERT INTO `menu` VALUES (190, b'0', '删除幻灯片', NULL, 56, 3, NULL, NULL, b'0', b'0', NULL, '2020-06-14 20:40:30', 'YXSYSTEMGROUPDATA_DELETE', 2);
INSERT INTO `menu` VALUES (191, b'0', '新增导航按钮', NULL, 57, 1, NULL, NULL, b'0', b'0', NULL, '2020-06-14 20:42:43', 'YXSYSTEMGROUPDATA_CREATE', 2);
INSERT INTO `menu` VALUES (192, b'0', '修改导航按钮', NULL, 57, 2, NULL, NULL, b'0', b'0', NULL, '2020-06-14 20:43:53', 'YXSYSTEMGROUPDATA_EDIT', 2);
INSERT INTO `menu` VALUES (193, b'0', '删除导航按钮', NULL, 57, 3, NULL, NULL, b'0', b'0', NULL, '2020-06-14 20:44:43', 'YXSYSTEMGROUPDATA_DELETE', 2);
INSERT INTO `menu` VALUES (194, b'0', '新增滚动新闻', NULL, 59, 1, NULL, NULL, b'0', b'0', NULL, '2020-06-14 20:48:32', 'YXSYSTEMGROUPDATA_CREATE', 2);
INSERT INTO `menu` VALUES (195, b'0', '修改滚动新闻', NULL, 59, 2, NULL, NULL, b'0', b'0', NULL, '2020-06-14 20:48:52', 'YXSYSTEMGROUPDATA_EDIT', 2);
INSERT INTO `menu` VALUES (196, b'0', '删除滚动新闻', NULL, 59, 3, NULL, NULL, b'0', b'0', NULL, '2020-06-14 20:49:32', 'YXSYSTEMGROUPDATA_DELETE', 2);
INSERT INTO `menu` VALUES (197, b'0', '新增热门搜索', NULL, 60, 1, NULL, NULL, b'0', b'0', NULL, '2020-06-14 21:14:25', 'YXSYSTEMGROUPDATA_CREATE', 2);
INSERT INTO `menu` VALUES (198, b'0', '修改热门搜索', NULL, 60, 2, NULL, NULL, b'0', b'0', NULL, '2020-06-14 21:14:55', 'YXSYSTEMGROUPDATA_EDIT', 2);
INSERT INTO `menu` VALUES (199, b'0', '删除热门搜索', NULL, 60, 3, NULL, NULL, b'0', b'0', NULL, '2020-06-14 21:15:25', 'YXSYSTEMGROUPDATA_DELETE', 2);
INSERT INTO `menu` VALUES (200, b'0', '新增个人中心菜单', NULL, 61, 1, NULL, NULL, b'0', b'0', NULL, '2020-06-14 21:17:47', 'YXSYSTEMGROUPDATA_CREATE', 2);
INSERT INTO `menu` VALUES (201, b'0', '修改个人中心菜单', NULL, 61, 2, NULL, NULL, b'0', b'0', NULL, '2020-06-14 21:18:37', 'YXSYSTEMGROUPDATA_EDIT', 2);
INSERT INTO `menu` VALUES (202, b'0', '删除个人中心菜单', NULL, 61, 3, NULL, NULL, b'0', b'0', NULL, '2020-06-14 21:19:47', 'YXSYSTEMGROUPDATA_DELETE', 2);
INSERT INTO `menu` VALUES (203, b'0', '新增积分配置', NULL, 68, 1, NULL, NULL, b'0', b'0', NULL, '2020-06-14 21:20:47', 'YXSYSTEMCONFIG_CREATE', 2);
INSERT INTO `menu` VALUES (204, b'0', '新增签到天数', NULL, 79, 1, NULL, NULL, b'0', b'0', NULL, '2020-06-14 21:26:32', 'YXSYSTEMGROUPDATA_CREATE', 2);
INSERT INTO `menu` VALUES (205, b'0', '修改签到天数', NULL, 79, 2, NULL, NULL, b'0', b'0', NULL, '2020-06-14 22:26:32', 'YXSYSTEMGROUPDATA_EDIT', 2);
INSERT INTO `menu` VALUES (206, b'0', '删除签到天数', NULL, 79, 3, NULL, NULL, b'0', b'0', NULL, '2020-06-14 22:26:52', 'YXSYSTEMGROUPDATA_DELETE', 2);
INSERT INTO `menu` VALUES (207, b'0', '新增邮费配置', NULL, 125, 1, NULL, NULL, b'0', b'0', NULL, '2020-06-14 21:29:20', 'YXSYSTEMCONFIG_CREATE', 2);
INSERT INTO `menu` VALUES (208, b'0', '新增充值金额', NULL, 182, 1, NULL, NULL, b'0', b'0', NULL, '2020-06-14 21:30:59', 'YXSYSTEMGROUPDATA_CREATE', 2);
INSERT INTO `menu` VALUES (209, b'0', '修改充值金额', NULL, 182, 2, NULL, NULL, b'0', b'0', NULL, '2020-06-14 22:30:30', 'YXSYSTEMGROUPDATA_EDIT', 2);
INSERT INTO `menu` VALUES (210, b'0', '删除充值金额', NULL, 182, 3, NULL, NULL, b'0', b'0', NULL, '2020-06-14 22:30:59', 'YXSYSTEMGROUPDATA_DELETE', 2);
INSERT INTO `menu` VALUES (211, b'0', '新增秒杀配置', NULL, 84, 1, NULL, NULL, b'0', b'0', NULL, '2020-06-14 21:43:36', 'YXSYSTEMGROUPDATA_CREATE', 2);
INSERT INTO `menu` VALUES (212, b'0', '修改秒杀配置', NULL, 84, 2, NULL, NULL, b'0', b'0', NULL, '2020-06-14 21:43:56', 'YXSYSTEMGROUPDATA_EDIT', 2);
INSERT INTO `menu` VALUES (213, b'0', '删除秒杀配置', NULL, 84, 3, NULL, NULL, b'0', b'0', NULL, '2020-06-14 22:23:36', 'YXSYSTEMGROUPDATA_DELETE', 2);
INSERT INTO `menu` VALUES (214, b'0', '新增分销配置', NULL, 70, 1, NULL, NULL, b'0', b'0', NULL, '2020-06-14 21:46:46', 'YXSYSTEMCONFIG_CREATE', 2);
INSERT INTO `menu` VALUES (215, b'0', '提现审核', NULL, 72, 1, NULL, NULL, b'0', b'0', NULL, '2020-06-14 21:56:11', 'YXUSEREXTRACT_EDIT', 2);
INSERT INTO `menu` VALUES (216, b'0', '删除充值', NULL, 177, 1, NULL, NULL, b'0', b'0', NULL, '2020-06-14 21:59:11', 'yxUserRecharge:del', 2);
INSERT INTO `menu` VALUES (217, b'0', '导出充值', NULL, 177, 2, NULL, NULL, b'0', b'0', NULL, '2020-06-14 21:59:54', 'yxUserRecharge:list', 2);
INSERT INTO `menu` VALUES (218, b'0', '新增门店', NULL, 179, 1, NULL, NULL, b'0', b'0', NULL, '2020-06-14 22:01:57', 'yxSystemStore:add', 2);
INSERT INTO `menu` VALUES (219, b'0', '修改门店', NULL, 179, 2, NULL, NULL, b'0', b'0', NULL, '2020-06-14 22:02:30', 'yxSystemStore:edit', 2);
INSERT INTO `menu` VALUES (220, b'0', '删除门店', NULL, 179, 3, NULL, NULL, b'0', b'0', NULL, '2020-06-14 22:02:57', 'yxSystemStore:del', 2);
INSERT INTO `menu` VALUES (221, b'0', '新增门店配置', NULL, 180, 1, NULL, NULL, b'0', b'0', NULL, '2020-06-14 22:04:25', 'YXSYSTEMCONFIG_CREATE', 2);
INSERT INTO `menu` VALUES (222, b'0', '编辑核销订单', NULL, 181, 1, NULL, NULL, b'0', b'0', NULL, '2020-06-14 22:07:26', 'YXSTOREORDER_EDIT', 2);
INSERT INTO `menu` VALUES (223, b'0', '新增店员', NULL, 183, 1, NULL, NULL, b'0', b'0', NULL, '2020-06-14 22:11:13', 'yxSystemStoreStaff:add', 2);
INSERT INTO `menu` VALUES (224, b'0', '修改店员', NULL, 183, 2, NULL, NULL, b'0', b'0', NULL, '2020-06-14 22:11:37', 'yxSystemStoreStaff:edit', 2);
INSERT INTO `menu` VALUES (225, b'0', '删除店员', NULL, 183, 3, NULL, NULL, b'0', b'0', NULL, '2020-06-14 22:11:59', 'yxSystemStoreStaff:del', 2);

SET FOREIGN_KEY_CHECKS = 1;
