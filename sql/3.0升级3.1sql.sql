

-- ----------------------------
-- 字段修改
-- ----------------------------
ALTER TABLE `yx_store_seckill`
	CHANGE COLUMN `price` `price` DECIMAL(10,2) UNSIGNED COMMENT '价格' AFTER `info`,
	CHANGE COLUMN `cost` `cost` DECIMAL(8,2) UNSIGNED COMMENT '成本' AFTER `price`,
	CHANGE COLUMN `ot_price` `ot_price` DECIMAL(10,2) UNSIGNED COMMENT '原价' AFTER `cost`;
ALTER TABLE yx_store_seckill ADD COLUMN spec_type tinyint(1)     COMMENT '规格 0单 1多' AFTER time_id;
ALTER TABLE yx_store_seckill ADD COLUMN temp_id INT(10)     COMMENT '运费模板id' AFTER spec_type;
ALTER TABLE yx_store_pink ADD COLUMN unique_id VARCHAR(128)     COMMENT '库存唯一值' AFTER is_del;
ALTER TABLE yx_store_seckill MODIFY COLUMN give_integral DECIMAL(10,2) UNSIGNED    COMMENT '返多少积分';
ALTER TABLE `yx_store_combination`
	CHANGE COLUMN `price` `price` DECIMAL(10,2) UNSIGNED NULL DEFAULT '0.00' COMMENT '价格' AFTER `info`,
	CHANGE COLUMN `stock` `stock` INT(10) UNSIGNED NULL DEFAULT '0' COMMENT '库存' AFTER `sales`,
	CHANGE COLUMN `unit_name` `unit_name` VARCHAR(32) NULL DEFAULT '' COMMENT '单位名' AFTER `browse`;
ALTER TABLE yx_store_combination ADD COLUMN spec_type tinyint(1)     COMMENT '规格 0单 1多' AFTER unit_name;
ALTER TABLE yx_store_combination ADD COLUMN temp_id INT(10)     COMMENT '运费模板ID' AFTER spec_type;
ALTER TABLE yx_store_combination DROP is_postage;
ALTER TABLE yx_store_combination DROP postage;
ALTER TABLE yx_store_product_attr_value ADD COLUMN pink_price DECIMAL(8,2) NOT NULL    COMMENT '拼团价' AFTER brokerage_two;
ALTER TABLE yx_store_product_attr_value ADD COLUMN pink_stock INT(10) NOT NULL    COMMENT '拼团库存' AFTER pink_price;
ALTER TABLE yx_store_product_attr_value ADD COLUMN seckill_price DECIMAL(10,2) NOT NULL    COMMENT '秒杀价' AFTER pink_stock;
ALTER TABLE yx_store_product_attr_value ADD COLUMN seckill_stock INT(10) NOT NULL    COMMENT '秒杀库存' AFTER seckill_price;
ALTER TABLE yx_wechat_template ADD COLUMN type VARCHAR(30)     COMMENT '类型：template:模板消息 subscribe:订阅消息' AFTER is_del;

-- ----------------------------
-- 足迹菜单添加
-- ----------------------------
INSERT INTO `yx_system_group_data` VALUES (225, 'yshop_my_menus', '{\"imageArr\":[\"https://app2.yixiang.co/file/pic/20200911093912577832.png\"],\"uniapp_url\":\"/pages/shop/GoodsFoot/index\",\"name\":\"我的足迹\",\"id\":225,\"pic\":\"https://app2.yixiang.co/file/pic/20200911093912577832.png\",\"sort\":10,\"url\":\"\",\"wxapp_url\":\"\",\"status\":1}', '2020-09-11 09:39:21', '2020-09-11 09:40:49', 10, 1, 0);

-- ----------------------------
-- 菜单添加
-- ----------------------------
INSERT INTO `menu` VALUES (237, b'0', '规格新增、修改', NULL, 233, 1, NULL, NULL, b'0', b'0', NULL, '2020-06-28 16:35:00', 'yxStoreProductRule:add', 2, '2020-07-16 20:09:03', 0);
INSERT INTO `menu` VALUES (238, b'0', '规格删除', NULL, 233, 1, NULL, NULL, b'0', b'0', NULL, '2020-06-28 16:35:00', 'yxStoreProductRule:del', 2, '2020-07-16 20:08:57', 0);
INSERT INTO `menu` VALUES (240, b'0', '新增、修改模板', NULL, 234, 1, NULL, NULL, b'0', b'0', NULL, '2020-06-29 17:16:06', 'yxShippingTemplates:add', 2, '2020-07-16 20:09:11', 0);
INSERT INTO `menu` VALUES (241, b'0', '删除模板', NULL, 234, 2, NULL, NULL, b'0', b'0', NULL, '2020-06-29 17:16:06', 'yxShippingTemplates:del', 2, '2020-07-16 20:09:16', 0);
INSERT INTO `menu` VALUES (242, b'0', '直播管理', 'wechat/live/index', 48, 999, 'weixin', 'wxlive', b'0', b'0', 'Wxlive', '2020-08-10 17:20:54', NULL, 1, NULL, 0);
INSERT INTO `menu` VALUES (243, b'0', '直播商品管理', 'wechat/goods/index', 48, 999, 'weixin', 'wxlivegoods', b'0', b'0', 'WxliveGoods', '2020-08-10 17:20:54', NULL, 1, NULL, 0);
INSERT INTO `menu` VALUES (244, b'0', '拼团商品添加', 'activity/combination/form', 63, 999, NULL, 'combinationAdd', b'0', b'1', 'CombinationAdd', '2020-08-13 21:28:45', 'YXSTORECOMBINATION_EDIT', 1, '2020-08-13 21:31:26', 0);
INSERT INTO `menu` VALUES (245, b'0', '拼团商品修改', 'activity/combination/form', 63, 3, 'anq', 'combinationEdit/:id', b'0', b'1', 'CombinationEdit', '2019-12-24 13:02:23', 'YXSTORECOMBINATION_EDIT', 1, '2020-07-10 16:45:33', 0);
INSERT INTO `menu` VALUES (246, b'0', '秒杀商品添加', 'activity/seckill/form', 63, 999, NULL, 'secKillAdd', b'0', b'1', 'SecKillAdd', '2020-08-13 21:28:45', 'YXSTORESECKILL_EDIT', 1, '2020-08-13 21:31:26', 0);
INSERT INTO `menu` VALUES (247, b'0', '秒杀商品修改', 'activity/seckill/form', 63, 3, 'anq', 'secKillEdit/:id', b'0', b'1', 'SecKillEdit', '2019-12-24 13:02:23', 'YXSTORESECKILL_EDIT', 1, '2020-07-10 16:45:33', 0);
INSERT INTO `menu` VALUES (248, b'0', '多级菜单', NULL, 0, 999, 'menu', 'nested', b'0', b'0', '-', '2020-08-19 11:31:10', NULL, 0, NULL, 0);
INSERT INTO `menu` VALUES (249, b'0', '二级菜单1', 'nested/menu1/index', 248, 999, 'menu', 'menu1', b'0', b'0', '-', '2020-08-19 11:34:34', NULL, 1, NULL, 0);
INSERT INTO `menu` VALUES (250, b'0', '三级菜单1', 'nested/menu1/menu1-1', 249, 999, 'menu', 'menu1-1', b'0', b'0', '-', '2020-08-19 11:35:52', NULL, 1, NULL, 0);
INSERT INTO `menu` VALUES (251, b'0', '三级菜单2', 'nested/menu1/menu1-2', 249, 999, 'menu', 'menu1-2', b'0', b'0', '-', '2020-08-19 11:37:48', NULL, 1, NULL, 0);
INSERT INTO `menu` VALUES (252, b'0', '二级菜单2', 'nested/menu2/index', 248, 999, 'menu', 'menu2', b'0', b'0', '-', '2020-08-19 11:38:35', NULL, 1, NULL, 0);
INSERT INTO `menu` VALUES (253, b'0', '浏览记录', 'monitor/log/mlog', 40, 15, 'log', 'viewlog', b'0', b'0', 'Viewlog', '2020-07-31 09:47:11', 'log:list', 1, '2020-07-31 09:49:39', 0);
INSERT INTO `menu` VALUES (256, b'0', '商品收藏', 'shop/collect/index', 40, 16, 'menu', 'productRelation', b'0', b'0', 'ProductRelation', '2020-09-03 14:32:49', 'yxStoreProductRelation:list', 1, '2020-09-03 16:21:08', 0);
INSERT INTO `menu` VALUES (257, b'0', '用户足迹', 'shop/foot/index', 40, 17, 'list', 'footRelation', b'0', b'0', 'FootRelation', '2020-09-03 16:20:21', 'yxStoreProductRelation:list', 1, '2020-09-03 16:21:16', 0);
INSERT INTO `menu` VALUES (258, b'0', '订单详情', 'shop/order/detail', 53, 999, 'sqlMonitor', 'detail/:id', b'0', b'1', 'Detail', '2020-09-10 07:29:34', NULL, 1, '2020-09-10 08:52:09', 0);
-- ----------------------------
-- 菜单添加权限
-- ----------------------------
INSERT INTO `roles_menus` VALUES (237, 1);
INSERT INTO `roles_menus` VALUES (238, 1);
INSERT INTO `roles_menus` VALUES (240, 1);
INSERT INTO `roles_menus` VALUES (241, 1);
INSERT INTO `roles_menus` VALUES (242, 1);
INSERT INTO `roles_menus` VALUES (243, 1);
INSERT INTO `roles_menus` VALUES (244, 1);
INSERT INTO `roles_menus` VALUES (245, 1);
INSERT INTO `roles_menus` VALUES (246, 1);
INSERT INTO `roles_menus` VALUES (247, 1);
INSERT INTO `roles_menus` VALUES (248, 1);
INSERT INTO `roles_menus` VALUES (249, 1);
INSERT INTO `roles_menus` VALUES (250, 1);
INSERT INTO `roles_menus` VALUES (251, 1);
INSERT INTO `roles_menus` VALUES (252, 1);
INSERT INTO `roles_menus` VALUES (253, 1);
INSERT INTO `roles_menus` VALUES (256, 1);
INSERT INTO `roles_menus` VALUES (257, 1);
INSERT INTO `roles_menus` VALUES (258, 1);




SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for yx_wechat_live
-- ----------------------------
DROP TABLE IF EXISTS `yx_wechat_live`;
CREATE TABLE `yx_wechat_live`  (
  `room_id` bigint(11) NOT NULL COMMENT '直播间id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '直播间标题',
  `cover_imge` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '背景图',
  `share_imge` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分享图片',
  `live_status` int(9) NULL DEFAULT NULL COMMENT '直播间状态',
  `start_time` bigint(11) NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` bigint(11) NULL DEFAULT NULL COMMENT '预计结束时间',
  `anchor_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '主播昵称',
  `anchor_wechat` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '主播微信号',
  `anchor_imge` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '主播头像',
  `type` tinyint(1) NULL DEFAULT NULL COMMENT '直播间类型 1：推流 0：手机直播',
  `screen_type` tinyint(1) NULL DEFAULT NULL COMMENT '横屏、竖屏 【1：横屏，0：竖屏】',
  `close_like` tinyint(1) NULL DEFAULT NULL COMMENT '是否关闭点赞 【0：开启，1：关闭】',
  `close_comment` tinyint(1) NULL DEFAULT NULL COMMENT '是否关闭评论 【0：开启，1：关闭】',
  `close_goods` tinyint(1) NULL DEFAULT NULL COMMENT '是否关闭货架 【0：开启，1：关闭】',
  `product_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品id 多个,分割',
  `close_replay` tinyint(1)  NULL DEFAULT NULL COMMENT '是否关闭回放【0：开启，1：关闭】',
  `close_share` tinyint(1)  NULL DEFAULT NULL COMMENT '是否关闭分享【0：开启，1：关闭】',
  `close_kf` tinyint(1)  NULL DEFAULT NULL COMMENT '是否关闭客服【0：开启，1：关闭】',
  PRIMARY KEY (`room_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '微信直播表' ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for yx_wechat_live_goods
-- ----------------------------
DROP TABLE IF EXISTS `yx_wechat_live_goods`;
CREATE TABLE `yx_wechat_live_goods`  (
  `goods_id` bigint(9) NOT NULL COMMENT '直播商品id',
  `product_id` bigint(9) NULL DEFAULT NULL COMMENT '关联商品id',
  `cover_imge_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品图片',
  `url` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品小程序路径',
  `price_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '价格类型 1：一口价（只需要传入price，price2不传） 2：价格区间（price字段为左边界，price2字段为右边界，price和price2必传） 3：显示折扣价（price字段为原价，price2字段为现价， price和price2必传）',
  `price` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `price2` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品名称',
  `third_party_tag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '1, 2：表示是为api添加商品，否则是直播控制台添加的商品',
  `audit_id` bigint(20) NULL DEFAULT NULL COMMENT '审核单id',
  `audit_status` int(1) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '审核状态 0：未审核，1：审核中，2:审核通过，3审核失败',
  PRIMARY KEY (`goods_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '微信直播商品表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;




drop index uid on yx_store_product_relation;
