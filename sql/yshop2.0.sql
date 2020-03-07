/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : yxshop

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 06/03/2020 21:10:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for alipay_config
-- ----------------------------
DROP TABLE IF EXISTS `alipay_config`;
CREATE TABLE `alipay_config`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `app_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应用ID',
  `charset` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编码',
  `format` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型 固定格式json',
  `gateway_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网关地址',
  `notify_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '异步回调',
  `private_key` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '私钥',
  `public_key` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '公钥',
  `return_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回调地址',
  `sign_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '签名方式',
  `sys_service_provider_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商户号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '支付宝配置类' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of alipay_config
-- ----------------------------
INSERT INTO `alipay_config` VALUES (1, '2016091700532697', 'utf-8', 'JSON', 'https://openapi.alipaydev.com/gateway.do', 'http://api.auauz.net/api/aliPay/notify', 'MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQC5js8sInU10AJ0cAQ8UMMyXrQ+oHZEkVt5lBwsStmTJ7YikVYgbskx1YYEXTojRsWCb+SH/kDmDU4pK/u91SJ4KFCRMF2411piYuXU/jF96zKrADznYh/zAraqT6hvAIVtQAlMHN53nx16rLzZ/8jDEkaSwT7+HvHiS+7sxSojnu/3oV7BtgISoUNstmSe8WpWHOaWv19xyS+Mce9MY4BfseFhzTICUymUQdd/8hXA28/H6osUfAgsnxAKv7Wil3aJSgaJczWuflYOve0dJ3InZkhw5Cvr0atwpk8YKBQjy5CdkoHqvkOcIB+cYHXJKzOE5tqU7inSwVbHzOLQ3XbnAgMBAAECggEAVJp5eT0Ixg1eYSqFs9568WdetUNCSUchNxDBu6wxAbhUgfRUGZuJnnAll63OCTGGck+EGkFh48JjRcBpGoeoHLL88QXlZZbC/iLrea6gcDIhuvfzzOffe1RcZtDFEj9hlotg8dQj1tS0gy9pN9g4+EBH7zeu+fyv+qb2e/v1l6FkISXUjpkD7RLQr3ykjiiEw9BpeKb7j5s7Kdx1NNIzhkcQKNqlk8JrTGDNInbDM6inZfwwIO2R1DHinwdfKWkvOTODTYa2MoAvVMFT9Bec9FbLpoWp7ogv1JMV9svgrcF9XLzANZ/OQvkbe9TV9GWYvIbxN6qwQioKCWO4GPnCAQKBgQDgW5MgfhX8yjXqoaUy/d1VjI8dHeIyw8d+OBAYwaxRSlCfyQ+tieWcR2HdTzPca0T0GkWcKZm0ei5xRURgxt4DUDLXNh26HG0qObbtLJdu/AuBUuCqgOiLqJ2f1uIbrz6OZUHns+bT/jGW2Ws8+C13zTCZkZt9CaQsrp3QOGDx5wKBgQDTul39hp3ZPwGNFeZdkGoUoViOSd5Lhowd5wYMGAEXWRLlU8z+smT5v0POz9JnIbCRchIY2FAPKRdVTICzmPk2EPJFxYTcwaNbVqL6lN7J2IlXXMiit5QbiLauo55w7plwV6LQmKm9KV7JsZs5XwqF7CEovI7GevFzyD3w+uizAQKBgC3LY1eRhOlpWOIAhpjG6qOoohmeXOphvdmMlfSHq6WYFqbWwmV4rS5d/6LNpNdL6fItXqIGd8I34jzql49taCmi+A2nlR/E559j0mvM20gjGDIYeZUz5MOE8k+K6/IcrhcgofgqZ2ZED1ksHdB/E8DNWCswZl16V1FrfvjeWSNnAoGAMrBplCrIW5xz+J0Hm9rZKrs+AkK5D4fUv8vxbK/KgxZ2KaUYbNm0xv39c+PZUYuFRCz1HDGdaSPDTE6WeWjkMQd5mS6ikl9hhpqFRkyh0d0fdGToO9yLftQKOGE/q3XUEktI1XvXF0xyPwNgUCnq0QkpHyGVZPtGFxwXiDvpvgECgYA5PoB+nY8iDiRaJNko9w0hL4AeKogwf+4TbCw+KWVEn6jhuJa4LFTdSqp89PktQaoVpwv92el/AhYjWOl/jVCm122f9b7GyoelbjMNolToDwe5pF5RnSpEuDdLy9MfE8LnE3PlbE7E5BipQ3UjSebkgNboLHH/lNZA5qvEtvbfvQ==', 'MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAut9evKRuHJ/2QNfDlLwvN/S8l9hRAgPbb0u61bm4AtzaTGsLeMtScetxTWJnVvAVpMS9luhEJjt+Sbk5TNLArsgzzwARgaTKOLMT1TvWAK5EbHyI+eSrc3s7Awe1VYGwcubRFWDm16eQLv0k7iqiw+4mweHSz/wWyvBJVgwLoQ02btVtAQErCfSJCOmt0Q/oJQjj08YNRV4EKzB19+f5A+HQVAKy72dSybTzAK+3FPtTtNen/+b5wGeat7c32dhYHnGorPkPeXLtsqqUTp1su5fMfd4lElNdZaoCI7osZxWWUo17vBCZnyeXc9fk0qwD9mK6yRAxNbrY72Xx5VqIqwIDAQAB', 'http://api.auauz.net/api/aliPay/return', 'RSA2', '2088102176044281');

-- ----------------------------
-- Table structure for column_config
-- ----------------------------
DROP TABLE IF EXISTS `column_config`;
CREATE TABLE `column_config`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `table_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `column_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `column_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dict_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `extra` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `form_show` bit(1) NULL DEFAULT NULL,
  `form_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `key_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `list_show` bit(1) NULL DEFAULT NULL,
  `not_null` bit(1) NULL DEFAULT NULL,
  `query_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `date_annotation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 281 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '代码生成字段信息存储' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of column_config
-- ----------------------------
INSERT INTO `column_config` VALUES (1, 'gen_test', 'id', 'int', NULL, 'auto_increment', b'0', NULL, 'PRI', b'0', b'1', NULL, 'ID', NULL);
INSERT INTO `column_config` VALUES (2, 'gen_test', 'sex', 'int', NULL, '', b'1', NULL, '', b'1', b'0', 'NotNull', '性别', NULL);
INSERT INTO `column_config` VALUES (3, 'gen_test', 'create_time', 'datetime', NULL, '', b'0', NULL, '', b'1', b'0', 'BetWeen', '', NULL);
INSERT INTO `column_config` VALUES (139, 'users_roles', 'user_id', 'bigint', NULL, '', b'1', NULL, 'PRI', b'1', b'1', NULL, '用户ID', NULL);
INSERT INTO `column_config` VALUES (140, 'users_roles', 'role_id', 'bigint', NULL, '', b'1', NULL, 'PRI', b'1', b'1', NULL, '角色ID', NULL);
INSERT INTO `column_config` VALUES (141, 'user_avatar', 'id', 'bigint', NULL, 'auto_increment', b'1', NULL, 'PRI', b'1', b'0', NULL, '', NULL);
INSERT INTO `column_config` VALUES (142, 'user_avatar', 'real_name', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '真实文件名', NULL);
INSERT INTO `column_config` VALUES (143, 'user_avatar', 'path', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '路径', NULL);
INSERT INTO `column_config` VALUES (144, 'user_avatar', 'size', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '大小', NULL);
INSERT INTO `column_config` VALUES (145, 'user_avatar', 'create_time', 'datetime', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '创建时间', NULL);
INSERT INTO `column_config` VALUES (146, 'yx_material_group', 'id', 'varchar', NULL, '', b'1', NULL, 'PRI', b'1', b'1', NULL, 'PK', NULL);
INSERT INTO `column_config` VALUES (147, 'yx_material_group', 'user_id', 'varchar', NULL, '', b'1', NULL, 'MUL', b'1', b'1', NULL, '所属租户', NULL);
INSERT INTO `column_config` VALUES (148, 'yx_material_group', 'del_flag', 'char', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '逻辑删除标记（0：显示；1：隐藏）', NULL);
INSERT INTO `column_config` VALUES (149, 'yx_material_group', 'create_time', 'datetime', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '创建时间', NULL);
INSERT INTO `column_config` VALUES (150, 'yx_material_group', 'update_time', 'timestamp', NULL, 'on update CURRENT_TIMESTAMP', b'1', NULL, '', b'1', b'1', NULL, '最后更新时间', NULL);
INSERT INTO `column_config` VALUES (151, 'yx_material_group', 'create_id', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '创建者ID', NULL);
INSERT INTO `column_config` VALUES (152, 'yx_material_group', 'name', 'varchar', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '分组名', NULL);
INSERT INTO `column_config` VALUES (153, 'yx_material', 'id', 'varchar', NULL, '', b'1', NULL, 'PRI', b'1', b'1', NULL, 'PK', NULL);
INSERT INTO `column_config` VALUES (154, 'yx_material', 'user_id', 'varchar', NULL, '', b'1', NULL, 'MUL', b'1', b'1', NULL, '所属租户', NULL);
INSERT INTO `column_config` VALUES (155, 'yx_material', 'del_flag', 'char', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '逻辑删除标记（0：显示；1：隐藏）', NULL);
INSERT INTO `column_config` VALUES (156, 'yx_material', 'create_time', 'datetime', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '创建时间', NULL);
INSERT INTO `column_config` VALUES (157, 'yx_material', 'update_time', 'timestamp', NULL, 'on update CURRENT_TIMESTAMP', b'1', NULL, '', b'1', b'1', NULL, '最后更新时间', NULL);
INSERT INTO `column_config` VALUES (158, 'yx_material', 'create_id', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '创建者ID', NULL);
INSERT INTO `column_config` VALUES (159, 'yx_material', 'type', 'char', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '类型1、图片；2、视频', NULL);
INSERT INTO `column_config` VALUES (160, 'yx_material', 'group_id', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '分组ID', NULL);
INSERT INTO `column_config` VALUES (161, 'yx_material', 'name', 'varchar', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '素材名', NULL);
INSERT INTO `column_config` VALUES (162, 'yx_material', 'url', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '素材链接', NULL);
INSERT INTO `column_config` VALUES (163, 'yx_user', 'uid', 'int', NULL, 'auto_increment', b'1', NULL, 'PRI', b'1', b'0', NULL, '用户id', NULL);
INSERT INTO `column_config` VALUES (164, 'yx_user', 'username', 'varchar', NULL, '', b'1', NULL, 'MUL', b'1', b'0', NULL, '用户账户(跟accout一样)', NULL);
INSERT INTO `column_config` VALUES (165, 'yx_user', 'account', 'varchar', NULL, '', b'1', NULL, 'MUL', b'1', b'1', NULL, '用户账号', NULL);
INSERT INTO `column_config` VALUES (166, 'yx_user', 'password', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '用户密码（跟pwd）', NULL);
INSERT INTO `column_config` VALUES (167, 'yx_user', 'pwd', 'varchar', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '用户密码', NULL);
INSERT INTO `column_config` VALUES (168, 'yx_user', 'real_name', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '真实姓名', NULL);
INSERT INTO `column_config` VALUES (169, 'yx_user', 'birthday', 'int', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '生日', NULL);
INSERT INTO `column_config` VALUES (170, 'yx_user', 'card_id', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '身份证号码', NULL);
INSERT INTO `column_config` VALUES (171, 'yx_user', 'mark', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '用户备注', NULL);
INSERT INTO `column_config` VALUES (172, 'yx_user', 'partner_id', 'int', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '合伙人id', NULL);
INSERT INTO `column_config` VALUES (173, 'yx_user', 'group_id', 'int', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '用户分组id', NULL);
INSERT INTO `column_config` VALUES (174, 'yx_user', 'nickname', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '用户昵称', NULL);
INSERT INTO `column_config` VALUES (175, 'yx_user', 'avatar', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '用户头像', NULL);
INSERT INTO `column_config` VALUES (176, 'yx_user', 'phone', 'char', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '手机号码', NULL);
INSERT INTO `column_config` VALUES (177, 'yx_user', 'add_time', 'int', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '添加时间', NULL);
INSERT INTO `column_config` VALUES (178, 'yx_user', 'add_ip', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '添加ip', NULL);
INSERT INTO `column_config` VALUES (179, 'yx_user', 'last_time', 'int', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '最后一次登录时间', NULL);
INSERT INTO `column_config` VALUES (180, 'yx_user', 'last_ip', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '最后一次登录ip', NULL);
INSERT INTO `column_config` VALUES (181, 'yx_user', 'now_money', 'decimal', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '用户余额', NULL);
INSERT INTO `column_config` VALUES (182, 'yx_user', 'brokerage_price', 'decimal', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '佣金金额', NULL);
INSERT INTO `column_config` VALUES (183, 'yx_user', 'integral', 'decimal', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '用户剩余积分', NULL);
INSERT INTO `column_config` VALUES (184, 'yx_user', 'sign_num', 'int', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '连续签到天数', NULL);
INSERT INTO `column_config` VALUES (185, 'yx_user', 'status', 'tinyint', NULL, '', b'1', NULL, 'MUL', b'1', b'1', NULL, '1为正常，0为禁止', NULL);
INSERT INTO `column_config` VALUES (186, 'yx_user', 'level', 'tinyint', NULL, '', b'1', NULL, 'MUL', b'1', b'1', NULL, '等级', NULL);
INSERT INTO `column_config` VALUES (187, 'yx_user', 'spread_uid', 'int', NULL, '', b'1', NULL, 'MUL', b'1', b'1', NULL, '推广元id', NULL);
INSERT INTO `column_config` VALUES (188, 'yx_user', 'spread_time', 'int', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '推广员关联时间', NULL);
INSERT INTO `column_config` VALUES (189, 'yx_user', 'user_type', 'varchar', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '用户类型', NULL);
INSERT INTO `column_config` VALUES (190, 'yx_user', 'is_promoter', 'tinyint', NULL, '', b'1', NULL, 'MUL', b'1', b'1', NULL, '是否为推广员', NULL);
INSERT INTO `column_config` VALUES (191, 'yx_user', 'pay_count', 'int', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '用户购买次数', NULL);
INSERT INTO `column_config` VALUES (192, 'yx_user', 'spread_count', 'int', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '下级人数', NULL);
INSERT INTO `column_config` VALUES (193, 'yx_user', 'clean_time', 'int', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '清理会员时间', NULL);
INSERT INTO `column_config` VALUES (194, 'yx_user', 'addres', 'varchar', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '详细地址', NULL);
INSERT INTO `column_config` VALUES (195, 'yx_user', 'adminid', 'int', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '管理员编号 ', NULL);
INSERT INTO `column_config` VALUES (196, 'yx_user', 'login_type', 'varchar', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '用户登陆类型，h5,wechat,routine', NULL);
INSERT INTO `column_config` VALUES (197, 'yx_wechat_media', 'id', 'int', NULL, 'auto_increment', b'1', NULL, 'PRI', b'1', b'0', NULL, '微信视频音频id', NULL);
INSERT INTO `column_config` VALUES (198, 'yx_wechat_media', 'type', 'varchar', NULL, '', b'1', NULL, 'MUL', b'1', b'1', NULL, '回复类型', NULL);
INSERT INTO `column_config` VALUES (199, 'yx_wechat_media', 'path', 'varchar', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '文件路径', NULL);
INSERT INTO `column_config` VALUES (200, 'yx_wechat_media', 'media_id', 'varchar', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '微信服务器返回的id', NULL);
INSERT INTO `column_config` VALUES (201, 'yx_wechat_media', 'url', 'varchar', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '地址', NULL);
INSERT INTO `column_config` VALUES (202, 'yx_wechat_media', 'temporary', 'tinyint', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '是否永久或者临时 0永久1临时', NULL);
INSERT INTO `column_config` VALUES (203, 'yx_wechat_media', 'add_time', 'int', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '添加时间', NULL);
INSERT INTO `column_config` VALUES (204, 'yx_store_order', 'id', 'int', NULL, 'auto_increment', b'1', NULL, 'PRI', b'1', b'0', NULL, '订单ID', NULL);
INSERT INTO `column_config` VALUES (205, 'yx_store_order', 'order_id', 'varchar', NULL, '', b'1', NULL, 'MUL', b'1', b'1', NULL, '订单号', NULL);
INSERT INTO `column_config` VALUES (206, 'yx_store_order', 'extend_order_id', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '额外订单号', NULL);
INSERT INTO `column_config` VALUES (207, 'yx_store_order', 'uid', 'int', NULL, '', b'1', NULL, 'MUL', b'1', b'1', NULL, '用户id', NULL);
INSERT INTO `column_config` VALUES (208, 'yx_store_order', 'real_name', 'varchar', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '用户姓名', NULL);
INSERT INTO `column_config` VALUES (209, 'yx_store_order', 'user_phone', 'varchar', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '用户电话', NULL);
INSERT INTO `column_config` VALUES (210, 'yx_store_order', 'user_address', 'varchar', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '详细地址', NULL);
INSERT INTO `column_config` VALUES (211, 'yx_store_order', 'cart_id', 'varchar', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '购物车id', NULL);
INSERT INTO `column_config` VALUES (212, 'yx_store_order', 'freight_price', 'decimal', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '运费金额', NULL);
INSERT INTO `column_config` VALUES (213, 'yx_store_order', 'total_num', 'int', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '订单商品总数', NULL);
INSERT INTO `column_config` VALUES (214, 'yx_store_order', 'total_price', 'decimal', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '订单总价', NULL);
INSERT INTO `column_config` VALUES (215, 'yx_store_order', 'total_postage', 'decimal', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '邮费', NULL);
INSERT INTO `column_config` VALUES (216, 'yx_store_order', 'pay_price', 'decimal', NULL, '', b'1', NULL, 'MUL', b'1', b'1', NULL, '实际支付金额', NULL);
INSERT INTO `column_config` VALUES (217, 'yx_store_order', 'pay_postage', 'decimal', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '支付邮费', NULL);
INSERT INTO `column_config` VALUES (218, 'yx_store_order', 'deduction_price', 'decimal', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '抵扣金额', NULL);
INSERT INTO `column_config` VALUES (219, 'yx_store_order', 'coupon_id', 'int', NULL, '', b'1', NULL, 'MUL', b'1', b'1', NULL, '优惠券id', NULL);
INSERT INTO `column_config` VALUES (220, 'yx_store_order', 'coupon_price', 'decimal', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '优惠券金额', NULL);
INSERT INTO `column_config` VALUES (221, 'yx_store_order', 'paid', 'tinyint', NULL, '', b'1', NULL, 'MUL', b'1', b'1', NULL, '支付状态', NULL);
INSERT INTO `column_config` VALUES (222, 'yx_store_order', 'pay_time', 'int', NULL, '', b'1', NULL, 'MUL', b'1', b'0', NULL, '支付时间', NULL);
INSERT INTO `column_config` VALUES (223, 'yx_store_order', 'pay_type', 'varchar', NULL, '', b'1', NULL, 'MUL', b'1', b'1', NULL, '支付方式', NULL);
INSERT INTO `column_config` VALUES (224, 'yx_store_order', 'add_time', 'int', NULL, '', b'1', NULL, 'MUL', b'1', b'1', NULL, '创建时间', NULL);
INSERT INTO `column_config` VALUES (225, 'yx_store_order', 'status', 'tinyint', NULL, '', b'1', NULL, 'MUL', b'1', b'1', NULL, '订单状态（-1 : 申请退款 -2 : 退货成功 0：待发货；1：待收货；2：已收货；3：待评价；-1：已退款）', NULL);
INSERT INTO `column_config` VALUES (226, 'yx_store_order', 'refund_status', 'tinyint', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '0 未退款 1 申请中 2 已退款', NULL);
INSERT INTO `column_config` VALUES (227, 'yx_store_order', 'refund_reason_wap_img', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '退款图片', NULL);
INSERT INTO `column_config` VALUES (228, 'yx_store_order', 'refund_reason_wap_explain', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '退款用户说明', NULL);
INSERT INTO `column_config` VALUES (229, 'yx_store_order', 'refund_reason_time', 'int', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '退款时间', NULL);
INSERT INTO `column_config` VALUES (230, 'yx_store_order', 'refund_reason_wap', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '前台退款原因', NULL);
INSERT INTO `column_config` VALUES (231, 'yx_store_order', 'refund_reason', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '不退款的理由', NULL);
INSERT INTO `column_config` VALUES (232, 'yx_store_order', 'refund_price', 'decimal', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '退款金额', NULL);
INSERT INTO `column_config` VALUES (233, 'yx_store_order', 'delivery_sn', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '快递公司编号', NULL);
INSERT INTO `column_config` VALUES (234, 'yx_store_order', 'delivery_name', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '快递名称/送货人姓名', NULL);
INSERT INTO `column_config` VALUES (235, 'yx_store_order', 'delivery_type', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '发货类型', NULL);
INSERT INTO `column_config` VALUES (236, 'yx_store_order', 'delivery_id', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '快递单号/手机号', NULL);
INSERT INTO `column_config` VALUES (237, 'yx_store_order', 'gain_integral', 'decimal', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '消费赚取积分', NULL);
INSERT INTO `column_config` VALUES (238, 'yx_store_order', 'use_integral', 'decimal', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '使用积分', NULL);
INSERT INTO `column_config` VALUES (239, 'yx_store_order', 'back_integral', 'decimal', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '给用户退了多少积分', NULL);
INSERT INTO `column_config` VALUES (240, 'yx_store_order', 'mark', 'varchar', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '备注', NULL);
INSERT INTO `column_config` VALUES (241, 'yx_store_order', 'is_del', 'tinyint', NULL, '', b'1', NULL, 'MUL', b'1', b'1', NULL, '是否删除', NULL);
INSERT INTO `column_config` VALUES (242, 'yx_store_order', 'unique', 'char', NULL, '', b'1', NULL, 'UNI', b'1', b'1', NULL, '唯一id(md5加密)类似id', NULL);
INSERT INTO `column_config` VALUES (243, 'yx_store_order', 'remark', 'varchar', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '管理员备注', NULL);
INSERT INTO `column_config` VALUES (244, 'yx_store_order', 'mer_id', 'int', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '商户ID', NULL);
INSERT INTO `column_config` VALUES (245, 'yx_store_order', 'is_mer_check', 'tinyint', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '', NULL);
INSERT INTO `column_config` VALUES (246, 'yx_store_order', 'combination_id', 'int', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '拼团产品id0一般产品', NULL);
INSERT INTO `column_config` VALUES (247, 'yx_store_order', 'pink_id', 'int', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '拼团id 0没有拼团', NULL);
INSERT INTO `column_config` VALUES (248, 'yx_store_order', 'cost', 'decimal', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '成本价', NULL);
INSERT INTO `column_config` VALUES (249, 'yx_store_order', 'seckill_id', 'int', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '秒杀产品ID', NULL);
INSERT INTO `column_config` VALUES (250, 'yx_store_order', 'bargain_id', 'int', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '砍价id', NULL);
INSERT INTO `column_config` VALUES (251, 'yx_store_order', 'verify_code', 'varchar', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '核销码', NULL);
INSERT INTO `column_config` VALUES (252, 'yx_store_order', 'store_id', 'int', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '门店id', NULL);
INSERT INTO `column_config` VALUES (253, 'yx_store_order', 'shipping_type', 'tinyint', NULL, '', b'1', NULL, '', b'1', b'1', NULL, '配送方式 1=快递 ，2=门店自提', NULL);
INSERT INTO `column_config` VALUES (254, 'yx_store_order', 'is_channel', 'tinyint', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '支付渠道(0微信公众号1微信小程序)', NULL);
INSERT INTO `column_config` VALUES (255, 'yx_store_order', 'is_remind', 'tinyint', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '', NULL);
INSERT INTO `column_config` VALUES (256, 'yx_store_order', 'is_system_del', 'tinyint', NULL, '', b'1', NULL, '', b'1', b'0', NULL, '', NULL);
INSERT INTO `column_config` VALUES (257, 'yx_user_recharge', 'id', 'int', NULL, 'auto_increment', b'0', NULL, 'PRI', b'1', b'0', NULL, '', NULL);
INSERT INTO `column_config` VALUES (258, 'yx_user_recharge', 'uid', 'int', NULL, '', b'0', NULL, 'MUL', b'0', b'0', NULL, '充值用户UID', NULL);
INSERT INTO `column_config` VALUES (259, 'yx_user_recharge', 'order_id', 'varchar', NULL, '', b'0', NULL, 'UNI', b'1', b'0', NULL, '订单号', NULL);
INSERT INTO `column_config` VALUES (260, 'yx_user_recharge', 'price', 'decimal', NULL, '', b'0', NULL, '', b'1', b'0', NULL, '充值金额', NULL);
INSERT INTO `column_config` VALUES (261, 'yx_user_recharge', 'recharge_type', 'varchar', NULL, '', b'0', NULL, 'MUL', b'1', b'0', NULL, '充值类型', NULL);
INSERT INTO `column_config` VALUES (262, 'yx_user_recharge', 'paid', 'tinyint', NULL, '', b'0', NULL, 'MUL', b'1', b'0', NULL, '是否充值', NULL);
INSERT INTO `column_config` VALUES (263, 'yx_user_recharge', 'pay_time', 'int', NULL, '', b'0', NULL, '', b'1', b'0', NULL, '充值支付时间', NULL);
INSERT INTO `column_config` VALUES (264, 'yx_user_recharge', 'add_time', 'int', NULL, '', b'0', NULL, '', b'1', b'0', NULL, '充值时间', NULL);
INSERT INTO `column_config` VALUES (265, 'yx_user_recharge', 'refund_price', 'decimal', NULL, '', b'0', NULL, '', b'0', b'0', NULL, '退款金额', NULL);
INSERT INTO `column_config` VALUES (266, 'yx_user_recharge', 'nickname', 'varchar', NULL, '', b'0', NULL, '', b'1', b'0', 'Like', '昵称', NULL);
INSERT INTO `column_config` VALUES (267, 'yx_system_store', 'id', 'int', NULL, 'auto_increment', b'1', NULL, 'PRI', b'0', b'0', NULL, '', NULL);
INSERT INTO `column_config` VALUES (268, 'yx_system_store', 'name', 'varchar', NULL, '', b'1', NULL, '', b'0', b'1', NULL, '门店名称', NULL);
INSERT INTO `column_config` VALUES (269, 'yx_system_store', 'introduction', 'varchar', NULL, '', b'1', NULL, '', b'0', b'1', NULL, '简介', NULL);
INSERT INTO `column_config` VALUES (270, 'yx_system_store', 'phone', 'char', NULL, '', b'1', NULL, 'MUL', b'0', b'1', NULL, '手机号码', NULL);
INSERT INTO `column_config` VALUES (271, 'yx_system_store', 'address', 'varchar', NULL, '', b'1', NULL, '', b'0', b'1', NULL, '省市区', NULL);
INSERT INTO `column_config` VALUES (272, 'yx_system_store', 'detailed_address', 'varchar', NULL, '', b'1', NULL, '', b'0', b'1', NULL, '详细地址', NULL);
INSERT INTO `column_config` VALUES (273, 'yx_system_store', 'image', 'varchar', NULL, '', b'1', NULL, '', b'0', b'1', NULL, '门店logo', NULL);
INSERT INTO `column_config` VALUES (274, 'yx_system_store', 'latitude', 'char', NULL, '', b'1', NULL, '', b'0', b'1', NULL, '纬度', NULL);
INSERT INTO `column_config` VALUES (275, 'yx_system_store', 'longitude', 'char', NULL, '', b'1', NULL, '', b'0', b'1', NULL, '经度', NULL);
INSERT INTO `column_config` VALUES (276, 'yx_system_store', 'valid_time', 'varchar', NULL, '', b'1', NULL, '', b'0', b'1', NULL, '核销有效日期', NULL);
INSERT INTO `column_config` VALUES (277, 'yx_system_store', 'day_time', 'varchar', NULL, '', b'1', NULL, '', b'0', b'1', NULL, '每日营业开关时间', NULL);
INSERT INTO `column_config` VALUES (278, 'yx_system_store', 'add_time', 'int', NULL, '', b'1', NULL, '', b'0', b'1', NULL, '添加时间', NULL);
INSERT INTO `column_config` VALUES (279, 'yx_system_store', 'is_show', 'tinyint', NULL, '', b'1', NULL, '', b'0', b'1', NULL, '是否显示', NULL);
INSERT INTO `column_config` VALUES (280, 'yx_system_store', 'is_del', 'tinyint', NULL, '', b'1', NULL, '', b'0', b'1', NULL, '是否删除', NULL);

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `pid` bigint(20) NOT NULL COMMENT '上级部门',
  `enabled` bit(1) NOT NULL COMMENT '状态',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES (1, 'YSHOP', 0, b'1', '2019-03-01 12:07:37');
INSERT INTO `dept` VALUES (2, '研发部', 7, b'1', '2019-03-25 09:15:32');
INSERT INTO `dept` VALUES (5, '运维部', 7, b'1', '2019-03-25 09:20:44');
INSERT INTO `dept` VALUES (6, '测试部', 8, b'1', '2019-03-25 09:52:18');
INSERT INTO `dept` VALUES (7, '华南分部', 1, b'1', '2019-03-25 11:04:50');
INSERT INTO `dept` VALUES (8, '华北分部', 1, b'1', '2019-03-25 11:04:53');
INSERT INTO `dept` VALUES (11, '人事部', 8, b'1', '2019-03-25 11:07:58');

-- ----------------------------
-- Table structure for dict
-- ----------------------------
DROP TABLE IF EXISTS `dict`;
CREATE TABLE `dict`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典名称',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据字典' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of dict
-- ----------------------------
INSERT INTO `dict` VALUES (1, 'user_status', '用户状态', '2019-10-27 20:31:36');
INSERT INTO `dict` VALUES (4, 'dept_status', '部门状态', '2019-10-27 20:31:36');
INSERT INTO `dict` VALUES (5, 'job_status', '岗位状态', '2019-10-27 20:31:36');

-- ----------------------------
-- Table structure for dict_detail
-- ----------------------------
DROP TABLE IF EXISTS `dict_detail`;
CREATE TABLE `dict_detail`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `label` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典标签',
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典值',
  `sort` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '排序',
  `dict_id` bigint(11) NULL DEFAULT NULL COMMENT '字典id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK5tpkputc6d9nboxojdbgnpmyb`(`dict_id`) USING BTREE,
  CONSTRAINT `FK5tpkputc6d9nboxojdbgnpmyb` FOREIGN KEY (`dict_id`) REFERENCES `dict` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据字典详情' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of dict_detail
-- ----------------------------
INSERT INTO `dict_detail` VALUES (1, '激活', 'true', '1', 1, '2019-10-27 20:31:36');
INSERT INTO `dict_detail` VALUES (2, '禁用', 'false', '2', 1, NULL);
INSERT INTO `dict_detail` VALUES (3, '启用', 'true', '1', 4, NULL);
INSERT INTO `dict_detail` VALUES (4, '停用', 'false', '2', 4, '2019-10-27 20:31:36');
INSERT INTO `dict_detail` VALUES (5, '启用', 'true', '1', 5, NULL);
INSERT INTO `dict_detail` VALUES (6, '停用', 'false', '2', 5, '2019-10-27 20:31:36');

-- ----------------------------
-- Table structure for email_config
-- ----------------------------
DROP TABLE IF EXISTS `email_config`;
CREATE TABLE `email_config`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `from_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收件人',
  `host` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮件服务器SMTP地址',
  `pass` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `port` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '端口',
  `user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发件者用户名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '邮箱配置' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of email_config
-- ----------------------------
INSERT INTO `email_config` VALUES (1, '111@qq.com', '111', '111', '111', '1');

-- ----------------------------
-- Table structure for gen_config
-- ----------------------------
DROP TABLE IF EXISTS `gen_config`;
CREATE TABLE `gen_config`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `table_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表名',
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者',
  `cover` bit(1) NULL DEFAULT NULL COMMENT '是否覆盖',
  `module_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模块名称',
  `pack` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '至于哪个包下',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '前端代码生成的路径',
  `api_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '前端Api文件路径',
  `prefix` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表前缀',
  `api_alias` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接口名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '代码生成器配置' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of gen_config
-- ----------------------------
INSERT INTO `gen_config` VALUES (3, 'gen_test', 'Zheng Jie', b'1', 'eladmin-system', 'me.zhengjie.gen', 'E:\\workspace\\me\\front\\eladmin-web\\src\\views\\gen', 'E:\\workspace\\me\\front\\eladmin-web\\src\\api', NULL, '测试生成');
INSERT INTO `gen_config` VALUES (4, 'yx_material_group', 'hupeng', b'0', 'yshop-shop', 'co.yixiang.modules.shop', 'E:\\output', 'E:\\output\\', NULL, '素材');
INSERT INTO `gen_config` VALUES (5, 'yx_material', 'hupeng', b'0', 'yshop-shop', 'co.yixiang.modules.shop', 'E:\\output', 'E:\\output\\', NULL, '素材管理');
INSERT INTO `gen_config` VALUES (6, 'yx_user', 'hupeng', b'0', 'yshop-system', 'co.yixiang.modules', 'aa', 'aa\\', NULL, '用户');
INSERT INTO `gen_config` VALUES (7, 'yx_wechat_media', 'hupeng', b'0', 'yshop-system', 'co.yixiang.modules', 'E:\\book\\img', 'E:\\book\\img\\', NULL, 'ceshi');
INSERT INTO `gen_config` VALUES (8, 'yx_user_recharge', 'hupeng', b'0', 'yshop-shop', 'co.yixiang.modules.shop', 'E:\\java\\yxshop-private\\yshop-web\\src\\views\\shop\\recharge', 'E:\\java\\yxshop-private\\yshop-web\\src\\api', '', '充值管理');
INSERT INTO `gen_config` VALUES (9, 'yx_system_store', 'hupeng', b'0', 'yshop-shop', 'co.yixiang.modules.shop', 'E:\\java\\yxshop-private\\yshop-web\\src\\views\\shop\\store', 'E:\\java\\yxshop-private\\yshop-web\\src\\api', NULL, '门店');

-- ----------------------------
-- Table structure for gen_test
-- ----------------------------
DROP TABLE IF EXISTS `gen_test`;
CREATE TABLE `gen_test`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` int(255) NULL DEFAULT NULL COMMENT '性别',
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '代码生成测试' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for job
-- ----------------------------
DROP TABLE IF EXISTS `job`;
CREATE TABLE `job`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '岗位名称',
  `enabled` bit(1) NOT NULL COMMENT '岗位状态',
  `sort` bigint(20) NOT NULL COMMENT '岗位排序',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKmvhj0rogastlctflsxf1d6k3i`(`dept_id`) USING BTREE,
  CONSTRAINT `FKmvhj0rogastlctflsxf1d6k3i` FOREIGN KEY (`dept_id`) REFERENCES `dept` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '岗位' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of job
-- ----------------------------
INSERT INTO `job` VALUES (8, '人事专员', b'1', 3, 11, '2019-03-29 14:52:28');
INSERT INTO `job` VALUES (10, '产品经理', b'1', 4, 2, '2019-03-29 14:55:51');
INSERT INTO `job` VALUES (11, '全栈开发', b'1', 2, 2, '2019-03-31 13:39:30');
INSERT INTO `job` VALUES (12, '软件测试', b'1', 5, 2, '2019-03-31 13:39:43');

-- ----------------------------
-- Table structure for local_storage
-- ----------------------------
DROP TABLE IF EXISTS `local_storage`;
CREATE TABLE `local_storage`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `real_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件真实的名称',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件名',
  `suffix` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '后缀',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路径',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型',
  `size` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '大小',
  `operate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '本地存储' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of local_storage
-- ----------------------------
INSERT INTO `local_storage` VALUES (1, 'list_30-20200107120524671.png', 'list_30', 'png', 'E:\\yshop\\file\\图片\\list_30-20200107120524671.png', '图片', '28.57KB   ', 'admin', '2020-01-07 12:05:25');
INSERT INTO `local_storage` VALUES (2, 'list_34-20200109071140374.png', 'list_34', 'png', 'E:\\yshop\\file\\图片\\list_34-20200109071140374.png', '图片', '27.05KB   ', 'admin', '2020-01-09 19:11:40');
INSERT INTO `local_storage` VALUES (3, 'list_22-20200109072256534.png', 'list_22', 'png', 'E:\\yshop\\file\\pic\\list_22-20200109072256534.png', 'pic', '28.23KB   ', 'admin', '2020-01-09 19:22:57');
INSERT INTO `local_storage` VALUES (4, 'list_32-20200109093407741.png', 'list_32', 'png', 'E:\\yshop\\file\\pic\\list_32-20200109093407741.png', 'pic', '27.65KB   ', 'admin', '2020-01-09 21:34:08');
INSERT INTO `local_storage` VALUES (5, 'list_32-20200109094223500.png', 'list_32', 'png', 'E:\\yshop\\file\\pic\\list_32-20200109094223500.png', 'pic', '27.65KB   ', 'admin', '2020-01-09 21:42:24');
INSERT INTO `local_storage` VALUES (6, 'list_32-20200109094413459.png', 'list_32', 'png', 'E:\\yshop\\file\\pic\\list_32-20200109094413459.png', 'pic', '27.65KB   ', 'admin', '2020-01-09 21:44:13');
INSERT INTO `local_storage` VALUES (7, 'list_32-20200109094652138.png', 'list_32', 'png', 'E:\\yshop\\file\\pic\\list_32-20200109094652138.png', 'pic', '27.65KB   ', 'admin', '2020-01-09 21:46:52');
INSERT INTO `local_storage` VALUES (8, 'list_34-20200109095146476.png', 'list_34', 'png', 'E:\\yshop\\file\\pic\\list_34-20200109095146476.png', 'pic', '27.05KB   ', 'admin', '2020-01-09 21:51:47');
INSERT INTO `local_storage` VALUES (9, 'list_32-20200109095700685.png', 'list_32', 'png', 'E:\\yshop\\file\\pic\\list_32-20200109095700685.png', 'pic', '27.65KB   ', 'admin', '2020-01-09 21:57:01');
INSERT INTO `local_storage` VALUES (10, 'list_20-20200109095936988.png', 'list_20', 'png', 'E:\\yshop\\file\\pic\\list_20-20200109095936988.png', 'pic', '22.92KB   ', 'admin', '2020-01-09 21:59:37');
INSERT INTO `local_storage` VALUES (11, 'list_32-20200109100213309.png', 'list_32', 'png', 'E:\\yshop\\file\\pic\\list_32-20200109100213309.png', 'pic', '27.65KB   ', 'admin', '2020-01-09 22:02:13');
INSERT INTO `local_storage` VALUES (12, 'list_30-20200109104513493.png', 'list_30', 'png', 'E:\\yshop\\file\\pic\\list_30-20200109104513493.png', 'pic', '28.57KB   ', 'admin', '2020-01-09 22:45:13');
INSERT INTO `local_storage` VALUES (13, 'list_16-20200110120838173.png', 'list_16', 'png', 'E:\\yshop\\file\\pic\\list_16-20200110120838173.png', 'pic', '28.93KB   ', 'admin', '2020-01-10 12:08:38');
INSERT INTO `local_storage` VALUES (14, 'list_32-20200110035831202.png', 'list_32', 'png', 'E:\\yshop\\file\\pic\\list_32-20200110035831202.png', 'pic', '27.65KB   ', 'admin', '2020-01-10 15:58:31');
INSERT INTO `local_storage` VALUES (15, 'list_32-2020011004054091.png', 'list_32', 'png', 'E:\\yshop\\file\\pic\\list_32-2020011004054091.png', 'pic', '27.65KB   ', 'admin', '2020-01-10 16:05:40');
INSERT INTO `local_storage` VALUES (16, 'list_30-20200110053337209.png', 'list_30', 'png', 'E:\\yshop\\file\\pic\\list_30-20200110053337209.png', 'pic', '28.57KB   ', 'admin', '2020-01-10 17:33:37');
INSERT INTO `local_storage` VALUES (17, 'list_32-20200110064436937.png', 'list_32', 'png', 'E:\\yshop\\file\\pic\\list_32-20200110064436937.png', 'pic', '27.65KB   ', 'admin', '2020-01-10 18:44:37');
INSERT INTO `local_storage` VALUES (18, 'list_22-20200110104217508.png', 'list_22', 'png', 'E:\\yshop\\file\\pic\\list_22-20200110104217508.png', 'pic', '28.23KB   ', 'admin', '2020-01-10 22:42:18');
INSERT INTO `local_storage` VALUES (19, 'list_18-20200111051038569.png', 'list_18', 'png', 'E:\\yshop\\file\\pic\\list_18-20200111051038569.png', 'pic', '29.62KB   ', '15136175246', '2020-01-11 17:10:39');
INSERT INTO `local_storage` VALUES (20, 'list_30-2020011105115469.png', 'list_30', 'png', 'E:\\yshop\\file\\pic\\list_30-2020011105115469.png', 'pic', '28.57KB   ', '15136175246', '2020-01-11 17:11:54');
INSERT INTO `local_storage` VALUES (21, 'list_30-20200111051343335.png', 'list_30', 'png', 'E:\\yshop\\file\\pic\\list_30-20200111051343335.png', 'pic', '28.57KB   ', '15136175246', '2020-01-11 17:13:43');
INSERT INTO `local_storage` VALUES (22, 'list_16-20200111051622984.png', 'list_16', 'png', 'E:\\yshop\\file\\pic\\list_16-20200111051622984.png', 'pic', '28.93KB   ', '15136175246', '2020-01-11 17:16:23');
INSERT INTO `local_storage` VALUES (23, 'list_24-20200111051630227.png', 'list_24', 'png', 'E:\\yshop\\file\\pic\\list_24-20200111051630227.png', 'pic', '27.31KB   ', '15136175246', '2020-01-11 17:16:30');
INSERT INTO `local_storage` VALUES (24, 'logo-20200131103905417.png', 'logo', 'png', 'E:\\yshop\\file\\pic\\logo-20200131103905417.png', 'pic', '48.39KB   ', 'admin', '2020-01-31 10:39:05');
INSERT INTO `local_storage` VALUES (25, 'avatar-20200131103928688.png', 'avatar', 'png', 'E:\\yshop\\file\\pic\\avatar-20200131103928688.png', 'pic', '1.82KB   ', 'admin', '2020-01-31 10:39:29');
INSERT INTO `local_storage` VALUES (26, 'list_13-2020013110394473.png', 'list_13', 'png', 'E:\\yshop\\file\\pic\\list_13-2020013110394473.png', 'pic', '23.05KB   ', 'admin', '2020-01-31 10:39:44');
INSERT INTO `local_storage` VALUES (27, 'list_18-2020013110394988.png', 'list_18', 'png', 'E:\\yshop\\file\\pic\\list_18-2020013110394988.png', 'pic', '29.62KB   ', 'admin', '2020-01-31 10:39:49');
INSERT INTO `local_storage` VALUES (28, 'list_22-20200131103952926.png', 'list_22', 'png', 'E:\\yshop\\file\\pic\\list_22-20200131103952926.png', 'pic', '28.23KB   ', 'admin', '2020-01-31 10:39:53');
INSERT INTO `local_storage` VALUES (29, 'list_26-20200131103956836.png', 'list_26', 'png', 'E:\\yshop\\file\\pic\\list_26-20200131103956836.png', 'pic', '28.36KB   ', 'admin', '2020-01-31 10:39:57');
INSERT INTO `local_storage` VALUES (30, 'list_28-20200131104001169.png', 'list_28', 'png', 'E:\\yshop\\file\\pic\\list_28-20200131104001169.png', 'pic', '28.38KB   ', 'admin', '2020-01-31 10:40:01');
INSERT INTO `local_storage` VALUES (31, 'list_16-20200207061544740.png', 'list_16', 'png', 'E:\\yshop\\file\\pic\\list_16-20200207061544740.png', 'pic', '28.93KB   ', 'hupeng', '2020-02-07 18:15:45');
INSERT INTO `local_storage` VALUES (32, 'list_30-2020020706162996.png', 'list_30', 'png', 'E:\\yshop\\file\\pic\\list_30-2020020706162996.png', 'pic', '28.57KB   ', 'hupeng', '2020-02-07 18:16:29');
INSERT INTO `local_storage` VALUES (33, 'list_16-20200207061714702.png', 'list_16', 'png', 'E:\\yshop\\file\\pic\\list_16-20200207061714702.png', 'pic', '28.93KB   ', 'hupeng', '2020-02-07 18:17:15');
INSERT INTO `local_storage` VALUES (34, 'list_18-20200207061743361.png', 'list_18', 'png', 'E:\\yshop\\file\\pic\\list_18-20200207061743361.png', 'pic', '29.62KB   ', 'hupeng', '2020-02-07 18:17:43');
INSERT INTO `local_storage` VALUES (35, 'list_30-20200207062920744.png', 'list_30', 'png', 'E:\\yshop\\file\\pic\\list_30-20200207062920744.png', 'pic', '28.57KB   ', 'hupeng', '2020-02-07 18:29:21');
INSERT INTO `local_storage` VALUES (36, '05ea40b831858a8cf423aa709840507c-20200228083801500.png', '05ea40b831858a8cf423aa709840507c', 'png', 'E:\\yshop\\file\\pic\\05ea40b831858a8cf423aa709840507c-20200228083801500.png', 'pic', '5.19KB   ', 'admin', '2020-02-28 20:38:02');

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `exception_detail` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `log_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `params` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `request_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` bigint(20) NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `browser` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` int(1) NULL DEFAULT 0,
  `uid` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 414583 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统日志' ROW_FORMAT = Compact;

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
) ENGINE = InnoDB AUTO_INCREMENT = 182 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, b'0', '系统管理', NULL, 0, 99, 'system', 'system', b'0', b'0', NULL, '2018-12-18 15:11:29', NULL, 1);
INSERT INTO `menu` VALUES (2, b'0', '用户管理', 'system/user/index', 1, 2, 'peoples', 'user', b'0', b'0', 'User', '2018-12-18 15:14:44', NULL, 1);
INSERT INTO `menu` VALUES (3, b'0', '角色管理', 'system/role/index', 1, 3, 'role', 'role', b'0', b'0', 'Role', '2018-12-18 15:16:07', NULL, 1);
INSERT INTO `menu` VALUES (5, b'0', '菜单管理', 'system/menu/index', 1, 5, 'menu', 'menu', b'0', b'0', 'Menu', '2018-12-18 15:17:28', 'admin,menu:list,roles:list', 1);
INSERT INTO `menu` VALUES (6, b'0', '系统监控', NULL, 0, 100, 'monitor', 'monitor', b'0', b'0', NULL, '2018-12-18 15:17:48', NULL, 1);
INSERT INTO `menu` VALUES (7, b'0', '操作日志', 'monitor/log/index', 6, 11, 'log', 'logs', b'1', b'0', 'Log', '2018-12-18 15:18:26', NULL, 1);
INSERT INTO `menu` VALUES (9, b'0', 'SQL监控', 'monitor/sql/index', 6, 14, 'sqlMonitor', 'druid', b'0', b'0', 'Sql', '2018-12-18 15:19:34', NULL, 1);
INSERT INTO `menu` VALUES (14, b'0', '邮件工具', 'tools/email/index', 36, 24, 'email', 'email', b'0', b'0', 'Email', '2018-12-27 10:13:09', NULL, 1);
INSERT INTO `menu` VALUES (16, b'0', '图床管理', 'tools/picture/index', 36, 25, 'image', 'pictures', b'0', b'0', 'Pictures', '2018-12-28 09:36:53', 'pictures:list', 1);
INSERT INTO `menu` VALUES (18, b'0', '存储管理', 'tools/storage/index', 36, 23, 'qiniu', 'storage', b'0', b'0', 'Storage', '2018-12-31 11:12:15', 'storage:list', 1);
INSERT INTO `menu` VALUES (19, b'0', '支付宝工具', 'tools/aliPay/index', 36, 27, 'alipay', 'aliPay', b'0', b'0', 'AliPay', '2018-12-31 14:52:38', NULL, 1);
INSERT INTO `menu` VALUES (28, b'0', '定时任务', 'system/timing/index', 36, 21, 'timing', 'timing', b'0', b'0', 'Timing', '2019-01-07 20:34:40', 'timing:list', 1);
INSERT INTO `menu` VALUES (30, b'0', '代码生成', 'generator/index', 36, 22, 'dev', 'generator', b'0', b'0', 'GeneratorIndex', '2019-01-11 15:45:55', NULL, 1);
INSERT INTO `menu` VALUES (32, b'0', '异常日志', 'monitor/log/errorLog', 6, 12, 'error', 'errorLog', b'0', b'0', 'ErrorLog', '2019-01-13 13:49:03', NULL, 1);
INSERT INTO `menu` VALUES (35, b'0', '部门管理', 'system/dept/index', 1, 6, 'dept', 'dept', b'0', b'0', 'Dept', '2019-03-25 09:46:00', NULL, 1);
INSERT INTO `menu` VALUES (36, b'0', '系统工具', '', 0, 101, 'sys-tools', 'sys-tools', b'0', b'0', NULL, '2019-03-29 10:57:35', NULL, 1);
INSERT INTO `menu` VALUES (37, b'0', '岗位管理', 'system/job/index', 1, 7, 'Steve-Jobs', 'job', b'0', b'0', 'Job', '2019-03-29 13:51:18', NULL, 1);
INSERT INTO `menu` VALUES (39, b'0', '字典管理', 'system/dict/index', 1, 8, 'dictionary', 'dict', b'0', b'0', 'Dict', '2019-04-10 11:49:04', NULL, 1);
INSERT INTO `menu` VALUES (40, b'0', '商品管理', '', 0, 1, 'shop', 'shop', b'0', b'0', '', '2019-10-03 17:40:19', NULL, 1);
INSERT INTO `menu` VALUES (41, b'0', '商品分类', 'shop/cate/index', 40, 11, 'icon', 'cate', b'0', b'0', 'Cate', '2019-10-03 17:42:35', 'YXSTORECATEGORY_SELECT', 1);
INSERT INTO `menu` VALUES (45, b'0', '管理商品', 'shop/goods/tab', 40, 12, 'develop', 'goods', b'0', b'0', 'Goods', '2019-10-04 15:34:35', 'YXSTOREPRODUCT_SELECT', 1);
INSERT INTO `menu` VALUES (46, b'0', '会员管理', '', 0, 2, 'peoples', 'member', b'0', b'0', '', '2019-10-06 16:18:05', NULL, 1);
INSERT INTO `menu` VALUES (47, b'0', '会员', 'shop/user/index', 46, 21, 'peoples', 'member', b'0', b'0', 'Member', '2019-10-06 16:20:17', 'YXUSER_SELECT', 1);
INSERT INTO `menu` VALUES (48, b'0', '微信管理', '', 0, 3, 'weixin', 'wechat', b'0', b'0', '', '2019-10-06 18:28:54', NULL, 1);
INSERT INTO `menu` VALUES (49, b'0', '微信菜单', 'wechat/menu/index', 48, 31, 'menu', 'wemenu', b'0', b'0', 'WeMenu', '2019-10-06 18:31:06', NULL, 1);
INSERT INTO `menu` VALUES (50, b'0', '图文管理', 'wechat/article/index', 48, 32, 'article', 'wearticle', b'0', b'0', 'WeArticle', '2019-10-07 17:33:45', NULL, 1);
INSERT INTO `menu` VALUES (51, b'0', '自动回复', 'wechat/reply/index', 48, 33, 'reply', 'wereply', b'0', b'0', 'Wereply', '2019-10-10 09:58:31', NULL, 1);
INSERT INTO `menu` VALUES (52, b'0', '公众号配置', 'wechat/config/index', 48, 34, 'configure', 'weconfig', b'0', b'0', 'WeConfig', '2019-10-10 15:52:24', NULL, 1);
INSERT INTO `menu` VALUES (53, b'0', '订单管理', '', 0, 4, 'lock', 'order', b'0', b'0', '', '2019-10-14 14:35:18', NULL, 1);
INSERT INTO `menu` VALUES (54, b'0', '订单', 'shop/order/index', 53, 41, 'order', 'order', b'0', b'0', 'Order', '2019-10-14 14:36:28', 'YXSTOREORDER_SELECT', 1);
INSERT INTO `menu` VALUES (55, b'0', '数据配置', '', 0, 5, 'configure', 'set', b'0', b'0', '', '2019-10-18 15:21:26', NULL, 1);
INSERT INTO `menu` VALUES (56, b'0', '首页幻灯片', 'shop/set/index', 55, 51, 'banner', 'homeBanner', b'0', b'0', 'HomeBanner', '2019-10-18 15:24:30', NULL, 1);
INSERT INTO `menu` VALUES (57, b'0', '首页导航按钮', 'shop/set/menu', 55, 52, 'button', 'homeMenus', b'0', b'0', 'HomeMenus', '2019-10-18 17:23:35', NULL, 1);
INSERT INTO `menu` VALUES (59, b'0', '首页滚动新闻', 'shop/set/roll', 55, 54, 'news', 'roll', b'0', b'0', 'Roll', '2019-10-21 16:41:30', NULL, 1);
INSERT INTO `menu` VALUES (60, b'0', '热门搜索', 'shop/set/hot', 55, 55, 'search', 'hot', b'0', b'0', 'Hot', '2019-10-26 18:21:54', NULL, 1);
INSERT INTO `menu` VALUES (61, b'0', '个人中心菜单', 'shop/set/usermenu', 55, 56, 'menu', 'userMenu', b'0', b'0', 'UserMenu', '2019-10-26 18:42:18', NULL, 1);
INSERT INTO `menu` VALUES (62, b'0', '评论管理', 'shop/reply/index', 53, 42, 'comment', 'reply', b'0', b'0', 'Reply', '2019-11-03 14:39:09', 'YXSTOREPRODUCTREPLY_SELECT', 1);
INSERT INTO `menu` VALUES (63, b'0', '营销管理', '', 0, 6, 'yingxiao', 'activity', b'0', b'0', '', '2019-11-09 14:17:42', NULL, 1);
INSERT INTO `menu` VALUES (64, b'0', '优惠券制作', 'activity/coupon/index', 63, 61, 'coupon', 'coupon', b'0', b'0', 'Coupon', '2019-11-09 14:18:58', 'YXSTORECOUPON_SELECT', 1);
INSERT INTO `menu` VALUES (65, b'0', '已发布优惠券', 'activity/couponissue/index', 63, 62, 'coupon2', 'couponissue', b'0', b'0', 'Couponissue', '2019-11-09 14:20:35', 'YXSTORECOUPONISSUE_SELECT', 1);
INSERT INTO `menu` VALUES (66, b'0', '优惠券领取记录', 'activity/storecouponuser/index', 63, 63, 'log', 'couponuser', b'0', b'0', 'Couponuser', '2019-11-09 14:21:35', NULL, 1);
INSERT INTO `menu` VALUES (67, b'0', '优惠券配置', 'activity/coupon/set', 63, 64, 'log', 'couponset', b'0', b'1', 'Couponset', '2019-11-09 14:22:17', NULL, 1);
INSERT INTO `menu` VALUES (68, b'0', '积分配置', 'wechat/config/point', 55, 57, 'configure', 'pointConfig', b'0', b'0', 'PointConfig', '2019-11-10 18:45:55', NULL, 1);
INSERT INTO `menu` VALUES (69, b'0', '分销管理', '', 0, 7, 'fenxiao', 'promoter', b'0', b'0', '', '2019-11-11 10:42:16', NULL, 1);
INSERT INTO `menu` VALUES (70, b'0', '分销配置', 'wechat/config/promoter', 69, 71, 'configure', 'promoterconfig', b'0', b'0', 'Promoterconfig', '2019-11-11 10:48:37', NULL, 1);
INSERT INTO `menu` VALUES (71, b'0', '分销员', 'shop/user/aindex', 69, 72, 'user', 'agent', b'0', b'0', 'Agent', '2019-11-13 18:32:00', NULL, 1);
INSERT INTO `menu` VALUES (72, b'0', '提现管理', 'activity/extract/index', 176, 81, 'tixian', 'extract', b'0', b'0', 'Extract', '2019-11-14 10:49:39', NULL, 1);
INSERT INTO `menu` VALUES (73, b'0', '拼团产品', 'activity/combination/index', 63, 64, 'peoples', 'combination', b'0', b'0', 'Combination', '2019-11-18 14:23:04', 'YXSTORECOMBINATION_SELECT', 1);
INSERT INTO `menu` VALUES (74, b'0', '拼团列表', 'activity/combinlist/index', 63, 65, 'list', 'pink', b'0', b'0', 'Pink', '2019-11-21 19:35:58', NULL, 1);
INSERT INTO `menu` VALUES (75, b'0', '微信支付配置', 'wechat/config/pay', 48, 35, 'wxpay', 'wxpayconfig', b'0', b'0', 'Wxpayconfig', '2019-11-28 17:06:22', NULL, 1);
INSERT INTO `menu` VALUES (76, b'0', '小程序配置', 'wechat/config/wxapp', 48, 36, 'configure', 'wxapp', b'0', b'0', 'Wxapp', '2019-11-29 15:13:46', NULL, 1);
INSERT INTO `menu` VALUES (77, b'0', '会员等级', 'shop/userlevel/index', 46, 22, 'dengji', 'userlevel', b'0', b'0', 'Userlevel', '2019-12-04 16:35:41', 'YXSYSTEMUSERLEVEL_SELECT', 1);
INSERT INTO `menu` VALUES (78, b'0', '等级任务', 'shop/usertask/index', 46, 23, 'task manege', 'usertask', b'0', b'0', 'Usertask', '2019-12-04 17:26:19', 'YXSYSTEMUSERTASK_SELECT', 1);
INSERT INTO `menu` VALUES (79, b'0', '签到天数配置', 'shop/set/sign', 55, 57, 'sign2', 'signday', b'0', b'0', 'Signday', '2019-12-05 14:12:16', NULL, 1);
INSERT INTO `menu` VALUES (80, b'0', '用户账单', 'shop/user/bill', 46, 24, 'list', 'bill', b'0', b'0', 'Bill', '2019-12-11 17:28:38', NULL, 1);
INSERT INTO `menu` VALUES (81, b'0', '物流快递', 'shop/express/index', 53, 43, 'express', 'express', b'0', b'0', 'Express', '2019-12-12 16:36:00', 'YXEXPRESS_SELECT', 1);
INSERT INTO `menu` VALUES (82, b'0', '微信模板消息', 'wechat/template/index', 48, 35, 'anq', 'template', b'0', b'0', 'Template', '2019-12-13 14:42:50', NULL, 1);
INSERT INTO `menu` VALUES (83, b'0', '秒杀产品', 'activity/seckill/index', 63, 66, 'seckill', 'seckill', b'0', b'0', 'Seckill', '2019-12-16 13:06:29', 'YXSTORESECKILL_SELECT', 1);
INSERT INTO `menu` VALUES (84, b'0', '秒杀配置', 'shop/set/seckill', 63, 67, 'configure', 'seckillconfig', b'0', b'0', 'Seckillconfig', '2019-12-16 16:07:42', NULL, 1);
INSERT INTO `menu` VALUES (86, b'0', '砍价产品', 'activity/bargain/index', 63, 956, 'Sign', 'bargain', b'0', b'0', 'Bargain', '2019-12-22 12:25:55', 'YXSTOREBARGAIN_SELECT', 1);
INSERT INTO `menu` VALUES (87, b'0', '生成配置', 'generator/config', 36, 33, 'dev', 'generator/config/:tableName', b'1', b'1', 'GeneratorConfig', '2019-11-17 20:08:56', '', 1);
INSERT INTO `menu` VALUES (88, b'0', '生成预览', 'generator/preview', 36, 999, 'java', 'generator/preview/:tableName', b'1', b'1', 'Preview', '2019-11-26 14:54:36', NULL, 1);
INSERT INTO `menu` VALUES (116, b'0', '生成配置', 'generator/config', 36, 33, 'dev', 'generator/config/:tableName', b'1', b'1', 'GeneratorConfig', '2019-11-17 20:08:56', '', 1);
INSERT INTO `menu` VALUES (117, b'0', '图表库', 'components/Echarts', 10, 50, 'chart', 'echarts', b'1', b'0', 'Echarts', '2019-11-21 09:04:32', '', 1);
INSERT INTO `menu` VALUES (118, b'0', '新增', NULL, 45, 1, NULL, NULL, b'0', b'0', NULL, '2019-12-24 13:00:47', 'YXSTOREPRODUCT_EDIT', 2);
INSERT INTO `menu` VALUES (119, b'0', '修改', NULL, 45, 3, NULL, NULL, b'0', b'0', NULL, '2019-12-24 13:02:23', 'YXSTOREPRODUCT_CREATE', 2);
INSERT INTO `menu` VALUES (120, b'0', '删除', NULL, 45, 4, NULL, NULL, b'0', b'0', NULL, '2019-12-24 13:03:51', 'YXSTOREPRODUCT_DELETE', 2);
INSERT INTO `menu` VALUES (121, b'0', '在线用户', 'monitor/online/index', 6, 10, 'Steve-Jobs', 'online', b'0', b'0', 'OnlineUser', '2020-01-06 22:46:43', NULL, 1);
INSERT INTO `menu` VALUES (122, b'0', '浏览记录', 'monitor/log/mlog', 40, 13, 'log', 'viewlog', b'0', b'0', 'Viewlog', '2020-01-07 13:17:21', NULL, 1);
INSERT INTO `menu` VALUES (123, b'0', '后台接口文档', 'tools/swagger/index', 36, 31, 'swagger', 'swagger2', b'0', b'0', 'Swagger', '2020-01-07 18:05:52', NULL, 1);
INSERT INTO `menu` VALUES (124, b'0', '在线会员', 'monitor/online/indext', 46, 25, 'Steve-Jobs', 'onlinet', b'0', b'0', 'OnlineMember', '2020-01-13 10:53:07', NULL, 1);
INSERT INTO `menu` VALUES (125, b'0', '邮费配置', 'wechat/config/postage', 55, 58, 'configure', 'postageConfig', b'0', b'0', 'PostageConfig', '2020-02-13 15:38:24', NULL, 1);
INSERT INTO `menu` VALUES (126, b'0', '编辑', NULL, 54, 1, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:05:28', 'YXSTOREORDER_EDIT', 2);
INSERT INTO `menu` VALUES (127, b'0', '用户新增', NULL, 2, 2, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:12:21', 'user:add', 2);
INSERT INTO `menu` VALUES (128, b'0', '用户编辑', NULL, 2, 3, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:12:47', 'user:edit', 2);
INSERT INTO `menu` VALUES (129, b'0', '用户删除', NULL, 2, 4, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:13:08', 'user:del', 2);
INSERT INTO `menu` VALUES (130, b'0', '角色创建', NULL, 3, 2, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:13:49', 'roles:add', 2);
INSERT INTO `menu` VALUES (131, b'0', '角色修改', NULL, 3, 3, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:14:11', 'roles:edit', 2);
INSERT INTO `menu` VALUES (132, b'0', '角色删除', NULL, 3, 999, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:14:38', '4', 2);
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
INSERT INTO `menu` VALUES (145, b'0', '图片上传', NULL, 16, 2, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:52:31', 'pictures:add', 2);
INSERT INTO `menu` VALUES (146, b'0', '图片删除', NULL, 16, 3, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:52:56', 'pictures:del', 2);
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
INSERT INTO `menu` VALUES (177, b'0', '充值管理', 'shop/recharge/index', 176, 82, 'rec', 'recharge', b'0', b'0', 'Recharge', '2020-03-02 23:05:26', NULL, 1);
INSERT INTO `menu` VALUES (178, b'0', '门店管理', NULL, 0, 9, 'store', 'store', b'0', b'0', NULL, '2020-03-03 17:27:53', NULL, 1);
INSERT INTO `menu` VALUES (179, b'0', '门店信息', 'shop/store/index', 178, 92, 'edit', 'storeinfo', b'0', b'0', 'Storeinfo', '2020-03-03 17:29:09', NULL, 1);
INSERT INTO `menu` VALUES (180, b'0', '门店配置', 'shop/store/set', 178, 91, 'configure', 'storeset', b'0', b'0', 'Storeset', '2020-03-04 13:09:54', NULL, 1);
INSERT INTO `menu` VALUES (181, b'0', '核销订单', 'shop/order/indext', 178, 93, 'order', 'ordert', b'0', b'0', 'Ordert', '2020-03-05 17:04:12', NULL, 1);

-- ----------------------------
-- Table structure for mnt_app
-- ----------------------------
DROP TABLE IF EXISTS `mnt_app`;
CREATE TABLE `mnt_app`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '应用ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应用名称',
  `upload_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上传目录',
  `deploy_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部署路径',
  `backup_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备份路径',
  `port` int(255) NULL DEFAULT NULL COMMENT '应用端口',
  `start_script` varchar(4000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '启动脚本',
  `deploy_script` varchar(4000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部署脚本',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '应用管理' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mnt_database
-- ----------------------------
DROP TABLE IF EXISTS `mnt_database`;
CREATE TABLE `mnt_database`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `jdbc_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'jdbc连接',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据库管理' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mnt_deploy
-- ----------------------------
DROP TABLE IF EXISTS `mnt_deploy`;
CREATE TABLE `mnt_deploy`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `app_id` bigint(20) NULL DEFAULT NULL COMMENT '应用编号',
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK6sy157pseoxx4fmcqr1vnvvhy`(`app_id`) USING BTREE,
  CONSTRAINT `FK6sy157pseoxx4fmcqr1vnvvhy` FOREIGN KEY (`app_id`) REFERENCES `mnt_app` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部署管理' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mnt_deploy_history
-- ----------------------------
DROP TABLE IF EXISTS `mnt_deploy_history`;
CREATE TABLE `mnt_deploy_history`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编号',
  `app_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用名称',
  `deploy_date` datetime(0) NOT NULL COMMENT '部署日期',
  `deploy_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '部署用户',
  `ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '服务器IP',
  `deploy_id` bigint(20) NULL DEFAULT NULL COMMENT '部署编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部署历史管理' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mnt_deploy_server
-- ----------------------------
DROP TABLE IF EXISTS `mnt_deploy_server`;
CREATE TABLE `mnt_deploy_server`  (
  `deploy_id` bigint(20) NOT NULL,
  `server_id` bigint(20) NOT NULL,
  PRIMARY KEY (`deploy_id`, `server_id`) USING BTREE,
  INDEX `FKeaaha7jew9a02b3bk9ghols53`(`server_id`) USING BTREE,
  CONSTRAINT `FK3cehr56tedph6nk3gxsmeq0pb` FOREIGN KEY (`deploy_id`) REFERENCES `mnt_deploy` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKeaaha7jew9a02b3bk9ghols53` FOREIGN KEY (`server_id`) REFERENCES `mnt_server` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '应用与服务器关联' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for mnt_server
-- ----------------------------
DROP TABLE IF EXISTS `mnt_server`;
CREATE TABLE `mnt_server`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'IP地址',
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `port` int(11) NULL DEFAULT NULL COMMENT '端口',
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '服务器管理' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for monitor_server
-- ----------------------------
DROP TABLE IF EXISTS `monitor_server`;
CREATE TABLE `monitor_server`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cpu_core` int(11) NULL DEFAULT NULL COMMENT 'CPU内核数',
  `cpu_rate` double NULL DEFAULT NULL COMMENT 'CPU使用率',
  `disk_total` double NULL DEFAULT NULL COMMENT '磁盘总量',
  `disk_used` double NULL DEFAULT NULL COMMENT '磁盘使用量',
  `mem_total` double NULL DEFAULT NULL COMMENT '内存总数',
  `mem_used` double NULL DEFAULT NULL COMMENT '内存使用量',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `port` int(11) NULL DEFAULT NULL COMMENT '访问端口',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `swap_total` double NULL DEFAULT NULL COMMENT '交换区总量',
  `swap_used` double NULL DEFAULT NULL COMMENT '交换区使用量',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '服务地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '服务监控' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of monitor_server
-- ----------------------------
INSERT INTO `monitor_server` VALUES (1, 8, 0.05924018, 465.12402, 91.66521, 7.849415, 7.6052284, '本地', 8777, 999, '0', 14.599415, 11.263367, 'localhost');

-- ----------------------------
-- Table structure for picture
-- ----------------------------
DROP TABLE IF EXISTS `picture`;
CREATE TABLE `picture`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '上传日期',
  `delete_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除的URL',
  `filename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片名称',
  `height` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片高度',
  `size` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片大小',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片地址',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名称',
  `width` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片宽度',
  `md5code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件的MD5值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'Sm.Ms图床' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for qiniu_config
-- ----------------------------
DROP TABLE IF EXISTS `qiniu_config`;
CREATE TABLE `qiniu_config`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `access_key` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'accessKey',
  `bucket` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Bucket 识别符',
  `host` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '外链域名',
  `secret_key` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'secretKey',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '空间类型',
  `zone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机房',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '七牛云配置' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of qiniu_config
-- ----------------------------
INSERT INTO `qiniu_config` VALUES (1, '111111', 'yixiang', 'https://image.dayouqiantu.cn', '22222', '公开', '华东');

-- ----------------------------
-- Table structure for qiniu_content
-- ----------------------------
DROP TABLE IF EXISTS `qiniu_content`;
CREATE TABLE `qiniu_content`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `bucket` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Bucket 识别符',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件名称',
  `size` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件大小',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件类型：私有或公开',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '上传或同步的时间',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件url',
  `suffix` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '七牛云文件存储' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of qiniu_content
-- ----------------------------
INSERT INTO `qiniu_content` VALUES (1, 'yixiang', 'list_34', '27.05KB   ', '公开', '2020-01-07 12:05:41', 'https://pic.dayouqiantu.cn/list_34.png', 'png');
INSERT INTO `qiniu_content` VALUES (2, 'yixiang', 'list_32', '27.65KB   ', '公开', '2020-01-07 12:11:15', 'https://pic.dayouqiantu.cn/list_32.png', 'png');
INSERT INTO `qiniu_content` VALUES (3, 'yixiang', 'list_30', '28.57KB   ', '公开', '2020-01-07 12:13:26', 'https://image.dayouqiantu.cn/list_30.png', 'png');
INSERT INTO `qiniu_content` VALUES (4, 'yixiang', 'list_34', '27.05KB   ', '公开', '2020-01-07 12:32:15', 'https://image.dayouqiantu.cn/list_34.png', 'png');
INSERT INTO `qiniu_content` VALUES (5, 'yixiang', 'list_32', '27.65KB   ', '公开', '2020-01-07 12:53:11', 'https://image.dayouqiantu.cn/list_32.png', 'png');
INSERT INTO `qiniu_content` VALUES (6, 'yixiang', 'list_34', '27.05KB   ', '公开', '2020-01-07 13:05:55', 'https://image.dayouqiantu.cn/list_34.png', 'png');
INSERT INTO `qiniu_content` VALUES (7, 'yixiang', 'list_32', '27.65KB   ', '公开', '2020-01-07 13:08:32', 'https://image.dayouqiantu.cn/list_32.png', 'png');
INSERT INTO `qiniu_content` VALUES (8, 'yixiang', 'list_22', '28.23KB   ', '公开', '2020-01-07 13:08:38', 'https://image.dayouqiantu.cn/list_22.png', 'png');
INSERT INTO `qiniu_content` VALUES (9, 'yixiang', 'list_32', '27.65KB   ', '公开', '2020-01-07 13:08:49', 'https://image.dayouqiantu.cn/list_32.png', 'png');
INSERT INTO `qiniu_content` VALUES (10, 'yixiang', 'list_34', '27.05KB   ', '公开', '2020-01-10 12:02:24', 'https://image.dayouqiantu.cn/list_34.png', 'png');
INSERT INTO `qiniu_content` VALUES (11, 'yixiang', 'list_32', '27.65KB   ', '公开', '2020-01-10 16:49:48', 'https://image.dayouqiantu.cn/list_32.png', 'png');
INSERT INTO `qiniu_content` VALUES (12, 'yixiang', 'list_20', '22.92KB   ', '公开', '2020-01-10 22:43:47', 'https://image.dayouqiantu.cn/list_20.png', 'png');
INSERT INTO `qiniu_content` VALUES (13, 'yixiang', 'list_24', '27.31KB   ', '公开', '2020-01-10 22:44:21', 'https://image.dayouqiantu.cn/list_24.png', 'png');
INSERT INTO `qiniu_content` VALUES (14, 'yixiang', 'list_28', '28.38KB   ', '公开', '2020-01-11 17:06:00', 'https://image.dayouqiantu.cn/list_28.png', 'png');

-- ----------------------------
-- Table structure for quartz_job
-- ----------------------------
DROP TABLE IF EXISTS `quartz_job`;
CREATE TABLE `quartz_job`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `bean_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Spring Bean名称',
  `cron_expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'cron 表达式',
  `is_pause` bit(1) NULL DEFAULT NULL COMMENT '状态：1暂停、0启用',
  `job_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务名称',
  `method_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '方法名称',
  `params` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定时任务' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of quartz_job
-- ----------------------------
INSERT INTO `quartz_job` VALUES (1, 'visitsTask', '0 0 0 * * ?', b'0', '更新访客记录', 'run', NULL, '每日0点创建新的访客记录', '2019-01-08 14:53:31');
INSERT INTO `quartz_job` VALUES (2, 'testTask', '0/5 * * * * ?', b'1', '测试1', 'run1', 'test', '带参测试，多参使用json', '2019-08-22 14:08:29');
INSERT INTO `quartz_job` VALUES (3, 'testTask', '0/5 * * * * ?', b'1', '测试', 'run', '', '不带参测试', '2019-09-26 16:44:39');

-- ----------------------------
-- Table structure for quartz_log
-- ----------------------------
DROP TABLE IF EXISTS `quartz_log`;
CREATE TABLE `quartz_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `baen_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `cron_expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `exception_detail` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `is_success` bit(1) NULL DEFAULT NULL,
  `job_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `method_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `params` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定时任务日志' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of quartz_log
-- ----------------------------
INSERT INTO `quartz_log` VALUES (1, 'visitsTask', '2020-01-10 00:00:00', '0 0 0 * * ?', NULL, b'1', '更新访客记录', 'run', NULL, 160);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `data_scope` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据权限',
  `level` int(255) NULL DEFAULT NULL COMMENT '角色级别',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `permission` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '功能权限',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '超级管理员', '-', '全部', 1, '2018-11-23 11:04:37', 'admin');
INSERT INTO `role` VALUES (2, '普通用户', '-', '本级', 2, '2018-11-23 13:09:06', 'common');
INSERT INTO `role` VALUES (3, '管理员2', '222', '全部', 3, '2020-01-31 16:53:25', '22');

-- ----------------------------
-- Table structure for roles_depts
-- ----------------------------
DROP TABLE IF EXISTS `roles_depts`;
CREATE TABLE `roles_depts`  (
  `role_id` bigint(20) NOT NULL,
  `dept_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`, `dept_id`) USING BTREE,
  INDEX `FK7qg6itn5ajdoa9h9o78v9ksur`(`dept_id`) USING BTREE,
  CONSTRAINT `FK7qg6itn5ajdoa9h9o78v9ksur` FOREIGN KEY (`dept_id`) REFERENCES `dept` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKrg1ci4cxxfbja0sb0pddju7k` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色部门关联' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for roles_menus
-- ----------------------------
DROP TABLE IF EXISTS `roles_menus`;
CREATE TABLE `roles_menus`  (
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`menu_id`, `role_id`) USING BTREE,
  INDEX `FKcngg2qadojhi3a651a5adkvbq`(`role_id`) USING BTREE,
  CONSTRAINT `FKo7wsmlrrxb2osfaoavp46rv2r` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKtag324maketmxffly3pdyh193` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色菜单关联' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of roles_menus
-- ----------------------------
INSERT INTO `roles_menus` VALUES (1, 1);
INSERT INTO `roles_menus` VALUES (2, 1);
INSERT INTO `roles_menus` VALUES (3, 1);
INSERT INTO `roles_menus` VALUES (5, 1);
INSERT INTO `roles_menus` VALUES (6, 1);
INSERT INTO `roles_menus` VALUES (7, 1);
INSERT INTO `roles_menus` VALUES (9, 1);
INSERT INTO `roles_menus` VALUES (10, 1);
INSERT INTO `roles_menus` VALUES (11, 1);
INSERT INTO `roles_menus` VALUES (14, 1);
INSERT INTO `roles_menus` VALUES (15, 1);
INSERT INTO `roles_menus` VALUES (16, 1);
INSERT INTO `roles_menus` VALUES (18, 1);
INSERT INTO `roles_menus` VALUES (19, 1);
INSERT INTO `roles_menus` VALUES (21, 1);
INSERT INTO `roles_menus` VALUES (22, 1);
INSERT INTO `roles_menus` VALUES (23, 1);
INSERT INTO `roles_menus` VALUES (24, 1);
INSERT INTO `roles_menus` VALUES (27, 1);
INSERT INTO `roles_menus` VALUES (28, 1);
INSERT INTO `roles_menus` VALUES (30, 1);
INSERT INTO `roles_menus` VALUES (32, 1);
INSERT INTO `roles_menus` VALUES (33, 1);
INSERT INTO `roles_menus` VALUES (34, 1);
INSERT INTO `roles_menus` VALUES (35, 1);
INSERT INTO `roles_menus` VALUES (36, 1);
INSERT INTO `roles_menus` VALUES (37, 1);
INSERT INTO `roles_menus` VALUES (38, 1);
INSERT INTO `roles_menus` VALUES (39, 1);
INSERT INTO `roles_menus` VALUES (40, 1);
INSERT INTO `roles_menus` VALUES (41, 1);
INSERT INTO `roles_menus` VALUES (44, 1);
INSERT INTO `roles_menus` VALUES (45, 1);
INSERT INTO `roles_menus` VALUES (46, 1);
INSERT INTO `roles_menus` VALUES (47, 1);
INSERT INTO `roles_menus` VALUES (48, 1);
INSERT INTO `roles_menus` VALUES (49, 1);
INSERT INTO `roles_menus` VALUES (50, 1);
INSERT INTO `roles_menus` VALUES (51, 1);
INSERT INTO `roles_menus` VALUES (52, 1);
INSERT INTO `roles_menus` VALUES (53, 1);
INSERT INTO `roles_menus` VALUES (54, 1);
INSERT INTO `roles_menus` VALUES (55, 1);
INSERT INTO `roles_menus` VALUES (56, 1);
INSERT INTO `roles_menus` VALUES (57, 1);
INSERT INTO `roles_menus` VALUES (58, 1);
INSERT INTO `roles_menus` VALUES (59, 1);
INSERT INTO `roles_menus` VALUES (60, 1);
INSERT INTO `roles_menus` VALUES (61, 1);
INSERT INTO `roles_menus` VALUES (62, 1);
INSERT INTO `roles_menus` VALUES (63, 1);
INSERT INTO `roles_menus` VALUES (64, 1);
INSERT INTO `roles_menus` VALUES (65, 1);
INSERT INTO `roles_menus` VALUES (66, 1);
INSERT INTO `roles_menus` VALUES (67, 1);
INSERT INTO `roles_menus` VALUES (68, 1);
INSERT INTO `roles_menus` VALUES (69, 1);
INSERT INTO `roles_menus` VALUES (70, 1);
INSERT INTO `roles_menus` VALUES (71, 1);
INSERT INTO `roles_menus` VALUES (72, 1);
INSERT INTO `roles_menus` VALUES (73, 1);
INSERT INTO `roles_menus` VALUES (74, 1);
INSERT INTO `roles_menus` VALUES (75, 1);
INSERT INTO `roles_menus` VALUES (76, 1);
INSERT INTO `roles_menus` VALUES (77, 1);
INSERT INTO `roles_menus` VALUES (78, 1);
INSERT INTO `roles_menus` VALUES (79, 1);
INSERT INTO `roles_menus` VALUES (80, 1);
INSERT INTO `roles_menus` VALUES (81, 1);
INSERT INTO `roles_menus` VALUES (82, 1);
INSERT INTO `roles_menus` VALUES (83, 1);
INSERT INTO `roles_menus` VALUES (84, 1);
INSERT INTO `roles_menus` VALUES (86, 1);
INSERT INTO `roles_menus` VALUES (87, 1);
INSERT INTO `roles_menus` VALUES (88, 1);
INSERT INTO `roles_menus` VALUES (116, 1);
INSERT INTO `roles_menus` VALUES (118, 1);
INSERT INTO `roles_menus` VALUES (119, 1);
INSERT INTO `roles_menus` VALUES (120, 1);
INSERT INTO `roles_menus` VALUES (121, 1);
INSERT INTO `roles_menus` VALUES (122, 1);
INSERT INTO `roles_menus` VALUES (123, 1);
INSERT INTO `roles_menus` VALUES (124, 1);
INSERT INTO `roles_menus` VALUES (125, 1);
INSERT INTO `roles_menus` VALUES (176, 1);
INSERT INTO `roles_menus` VALUES (177, 1);
INSERT INTO `roles_menus` VALUES (178, 1);
INSERT INTO `roles_menus` VALUES (179, 1);
INSERT INTO `roles_menus` VALUES (180, 1);
INSERT INTO `roles_menus` VALUES (181, 1);
INSERT INTO `roles_menus` VALUES (1, 2);
INSERT INTO `roles_menus` VALUES (2, 2);
INSERT INTO `roles_menus` VALUES (3, 2);
INSERT INTO `roles_menus` VALUES (5, 2);
INSERT INTO `roles_menus` VALUES (6, 2);
INSERT INTO `roles_menus` VALUES (9, 2);
INSERT INTO `roles_menus` VALUES (10, 2);
INSERT INTO `roles_menus` VALUES (11, 2);
INSERT INTO `roles_menus` VALUES (14, 2);
INSERT INTO `roles_menus` VALUES (15, 2);
INSERT INTO `roles_menus` VALUES (18, 2);
INSERT INTO `roles_menus` VALUES (19, 2);
INSERT INTO `roles_menus` VALUES (21, 2);
INSERT INTO `roles_menus` VALUES (23, 2);
INSERT INTO `roles_menus` VALUES (24, 2);
INSERT INTO `roles_menus` VALUES (27, 2);
INSERT INTO `roles_menus` VALUES (28, 2);
INSERT INTO `roles_menus` VALUES (30, 2);
INSERT INTO `roles_menus` VALUES (33, 2);
INSERT INTO `roles_menus` VALUES (34, 2);
INSERT INTO `roles_menus` VALUES (35, 2);
INSERT INTO `roles_menus` VALUES (36, 2);
INSERT INTO `roles_menus` VALUES (37, 2);
INSERT INTO `roles_menus` VALUES (38, 2);
INSERT INTO `roles_menus` VALUES (39, 2);
INSERT INTO `roles_menus` VALUES (40, 2);
INSERT INTO `roles_menus` VALUES (41, 2);
INSERT INTO `roles_menus` VALUES (44, 2);
INSERT INTO `roles_menus` VALUES (45, 2);
INSERT INTO `roles_menus` VALUES (48, 2);
INSERT INTO `roles_menus` VALUES (49, 2);
INSERT INTO `roles_menus` VALUES (50, 2);
INSERT INTO `roles_menus` VALUES (53, 2);
INSERT INTO `roles_menus` VALUES (54, 2);
INSERT INTO `roles_menus` VALUES (77, 2);
INSERT INTO `roles_menus` VALUES (78, 2);
INSERT INTO `roles_menus` VALUES (79, 2);
INSERT INTO `roles_menus` VALUES (118, 2);
INSERT INTO `roles_menus` VALUES (119, 2);
INSERT INTO `roles_menus` VALUES (126, 2);
INSERT INTO `roles_menus` VALUES (154, 2);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `avatar_id` bigint(20) NULL DEFAULT NULL COMMENT '头像',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `enabled` bigint(20) NULL DEFAULT NULL COMMENT '状态：1启用、0禁用',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门名称',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `job_id` bigint(20) NULL DEFAULT NULL COMMENT '岗位名称',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `last_password_reset_time` datetime(0) NULL DEFAULT NULL COMMENT '最后修改密码的日期',
  `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_kpubos9gc2cvtkb0thktkbkes`(`email`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE,
  INDEX `FK5rwmryny6jthaaxkogownknqp`(`dept_id`) USING BTREE,
  INDEX `FKfftoc2abhot8f2wu6cl9a5iky`(`job_id`) USING BTREE,
  INDEX `FKpq2dhypk2qgt68nauh2by22jb`(`avatar_id`) USING BTREE,
  CONSTRAINT `FK5rwmryny6jthaaxkogownknqp` FOREIGN KEY (`dept_id`) REFERENCES `dept` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKfftoc2abhot8f2wu6cl9a5iky` FOREIGN KEY (`job_id`) REFERENCES `job` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKpq2dhypk2qgt68nauh2by22jb` FOREIGN KEY (`avatar_id`) REFERENCES `user_avatar` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统用户' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, NULL, 'yshop@qq.com', 1, '$2a$10$fP.426qKaTmix50Oln8L.uav55gELhAd0Eg66Av4oG86u8km7D/Ky', 'admin', 2, '18888888888', 11, '2018-08-23 09:11:56', '2019-05-18 17:34:21', '管理员', '男');
INSERT INTO `user` VALUES (3, NULL, 'test@yshopnet', 1, '$2a$10$HhxyGZy.ulf3RvAwaHUGb.k.2i9PBpv4YbLMJWp8pES7pPhTyRCF.', 'test', 2, '17777777777', 12, '2018-12-27 20:05:26', '2019-04-01 09:15:24', '测试', '男');
INSERT INTO `user` VALUES (4, NULL, 'test2@qq.com', 1, '$2a$10$IjehtV8MiXb8ni.Qz0wBteE7FjVn49cEcsSj2.ZBUqqHjnC3umSh.', 'test2', 2, '15136175247', 11, '2020-02-15 20:39:16', NULL, 'test2', '男');
INSERT INTO `user` VALUES (5, NULL, '444@qq.com', 0, '$2a$10$f/VH35NBOBszycV9KEA1HenQ0qVjazDm8LacQU9PO.A4UizFxLMuq', 'qqqqq', 11, '15136175249', 8, '2020-02-17 11:12:01', NULL, 'eeeeee', '男');

-- ----------------------------
-- Table structure for user_avatar
-- ----------------------------
DROP TABLE IF EXISTS `user_avatar`;
CREATE TABLE `user_avatar`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `real_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '真实文件名',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路径',
  `size` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '大小',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统用户头像' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for users_roles
-- ----------------------------
DROP TABLE IF EXISTS `users_roles`;
CREATE TABLE `users_roles`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE,
  INDEX `FKq4eq273l04bpu4efj0jd0jb98`(`role_id`) USING BTREE,
  CONSTRAINT `FKgd3iendaoyh04b95ykqise6qh` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKt4v0rrweyk393bdgt107vdx0x` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色关联' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of users_roles
-- ----------------------------
INSERT INTO `users_roles` VALUES (1, 1);
INSERT INTO `users_roles` VALUES (3, 2);
INSERT INTO `users_roles` VALUES (4, 2);
INSERT INTO `users_roles` VALUES (5, 2);

-- ----------------------------
-- Table structure for verification_code
-- ----------------------------
DROP TABLE IF EXISTS `verification_code`;
CREATE TABLE `verification_code`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '验证码',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `status` bit(1) NULL DEFAULT NULL COMMENT '状态：1有效、0过期',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '验证码类型：email或者短信',
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接收邮箱或者手机号码',
  `scenes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务名称：如重置邮箱、重置密码等',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '验证码' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for visits
-- ----------------------------
DROP TABLE IF EXISTS `visits`;
CREATE TABLE `visits`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ip_counts` bigint(20) NULL DEFAULT NULL,
  `pv_counts` bigint(20) NULL DEFAULT NULL,
  `week_day` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_11aksgq87euk9bcyeesfs4vtp`(`date`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 66 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '访客记录' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of visits
-- ----------------------------
INSERT INTO `visits` VALUES (21, '2019-12-31 11:40:24', '2019-12-31', 2, 4, 'Tue');
INSERT INTO `visits` VALUES (22, '2020-01-06 16:55:15', '2020-01-06', 4, 25, 'Mon');
INSERT INTO `visits` VALUES (23, '2020-01-07 11:32:57', '2020-01-07', 4, 8, 'Tue');
INSERT INTO `visits` VALUES (24, '2020-01-08 00:04:57', '2020-01-08', 2, 17, 'Wed');
INSERT INTO `visits` VALUES (25, '2020-01-09 14:28:38', '2020-01-09', 3, 3, 'Thu');
INSERT INTO `visits` VALUES (26, '2020-01-10 00:00:00', '2020-01-10', 6, 16, 'Fri');
INSERT INTO `visits` VALUES (27, '2020-01-11 11:06:01', '2020-01-11', 1, 2, 'Sat');
INSERT INTO `visits` VALUES (28, '2020-01-12 17:19:42', '2020-01-12', 1, 1, 'Sun');
INSERT INTO `visits` VALUES (29, '2020-01-13 10:18:00', '2020-01-13', 1, 2, 'Mon');
INSERT INTO `visits` VALUES (30, '2020-01-14 17:34:40', '2020-01-14', 1, 3, 'Tue');
INSERT INTO `visits` VALUES (31, '2020-01-15 11:23:03', '2020-01-15', 1, 1, 'Wed');
INSERT INTO `visits` VALUES (32, '2020-01-16 10:45:22', '2020-01-16', 1, 1, 'Thu');
INSERT INTO `visits` VALUES (33, '2020-01-17 18:07:10', '2020-01-17', 1, 1, 'Fri');
INSERT INTO `visits` VALUES (34, '2020-01-18 18:19:03', '2020-01-18', 1, 2, 'Sat');
INSERT INTO `visits` VALUES (35, '2020-01-19 09:53:20', '2020-01-19', 1, 1, 'Sun');
INSERT INTO `visits` VALUES (36, '2020-01-20 14:01:00', '2020-01-20', 1, 1, 'Mon');
INSERT INTO `visits` VALUES (37, '2020-01-31 10:38:18', '2020-01-31', 1, 2, 'Fri');
INSERT INTO `visits` VALUES (38, '2020-02-02 14:27:41', '2020-02-02', 1, 2, 'Sun');
INSERT INTO `visits` VALUES (39, '2020-02-03 14:11:42', '2020-02-03', 1, 1, 'Mon');
INSERT INTO `visits` VALUES (40, '2020-02-04 10:27:33', '2020-02-04', 1, 1, 'Tue');
INSERT INTO `visits` VALUES (41, '2020-02-05 10:40:14', '2020-02-05', 1, 2, 'Wed');
INSERT INTO `visits` VALUES (42, '2020-02-07 10:17:32', '2020-02-07', 1, 1, 'Fri');
INSERT INTO `visits` VALUES (43, '2020-02-09 13:31:49', '2020-02-09', 1, 1, 'Sun');
INSERT INTO `visits` VALUES (44, '2020-02-10 10:07:12', '2020-02-10', 1, 3, 'Mon');
INSERT INTO `visits` VALUES (45, '2020-02-11 21:31:13', '2020-02-11', 1, 1, 'Tue');
INSERT INTO `visits` VALUES (46, '2020-02-13 14:47:22', '2020-02-13', 1, 3, 'Thu');
INSERT INTO `visits` VALUES (47, '2020-02-14 13:32:26', '2020-02-14', 3, 5, 'Fri');
INSERT INTO `visits` VALUES (48, '2020-02-15 20:37:36', '2020-02-15', 1, 4, 'Sat');
INSERT INTO `visits` VALUES (49, '2020-02-16 20:11:40', '2020-02-16', 1, 4, 'Sun');
INSERT INTO `visits` VALUES (50, '2020-02-17 09:48:36', '2020-02-17', 1, 2, 'Mon');
INSERT INTO `visits` VALUES (51, '2020-02-19 10:34:18', '2020-02-19', 1, 3, 'Wed');
INSERT INTO `visits` VALUES (52, '2020-02-20 21:19:04', '2020-02-20', 1, 1, 'Thu');
INSERT INTO `visits` VALUES (53, '2020-02-21 10:25:10', '2020-02-21', 1, 1, 'Fri');
INSERT INTO `visits` VALUES (54, '2020-02-23 16:34:09', '2020-02-23', 1, 2, 'Sun');
INSERT INTO `visits` VALUES (55, '2020-02-24 14:30:06', '2020-02-24', 1, 2, 'Mon');
INSERT INTO `visits` VALUES (56, '2020-02-26 15:24:09', '2020-02-26', 1, 1, 'Wed');
INSERT INTO `visits` VALUES (57, '2020-02-27 12:55:34', '2020-02-27', 2, 3, 'Thu');
INSERT INTO `visits` VALUES (58, '2020-02-28 15:31:37', '2020-02-28', 2, 4, 'Fri');
INSERT INTO `visits` VALUES (59, '2020-02-29 14:37:48', '2020-02-29', 2, 3, 'Sat');
INSERT INTO `visits` VALUES (60, '2020-03-01 13:43:01', '2020-03-01', 2, 5, 'Sun');
INSERT INTO `visits` VALUES (61, '2020-03-02 18:36:56', '2020-03-02', 2, 7, 'Mon');
INSERT INTO `visits` VALUES (62, '2020-03-03 02:51:42', '2020-03-03', 2, 9, 'Tue');
INSERT INTO `visits` VALUES (63, '2020-03-04 12:55:53', '2020-03-04', 1, 11, 'Wed');
INSERT INTO `visits` VALUES (64, '2020-03-05 12:55:28', '2020-03-05', 1, 2, 'Thu');
INSERT INTO `visits` VALUES (65, '2020-03-06 02:48:11', '2020-03-06', 2, 2, 'Fri');

-- ----------------------------
-- Table structure for yx_article
-- ----------------------------
DROP TABLE IF EXISTS `yx_article`;
CREATE TABLE `yx_article`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '文章管理ID',
  `cid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '分类id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章标题',
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章作者',
  `image_input` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章图片',
  `synopsis` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章简介',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `share_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章分享标题',
  `share_synopsis` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章分享简介',
  `visit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '浏览次数',
  `sort` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '排序',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '原文链接',
  `status` tinyint(1) UNSIGNED NULL DEFAULT NULL COMMENT '状态',
  `add_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '添加时间',
  `hide` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '是否隐藏',
  `admin_id` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '管理员id',
  `mer_id` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '商户id',
  `product_id` int(10) NULL DEFAULT 0 COMMENT '产品关联id',
  `is_hot` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '是否热门(小程序)',
  `is_banner` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '是否轮播图(小程序)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yx_article
-- ----------------------------
INSERT INTO `yx_article` VALUES (1, '', '333', '333', 'https://i.loli.net/2019/10/08/CRnUIMgVQlk3eGL.png', '33333', NULL, '', '', '5', NULL, '', NULL, '2019-08-22 12:26', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `yx_article` VALUES (2, '', 'yshop1.3版本上线了', 'yshop', 'http://localhost:8000/file/pic/list_16-20200110120838173.png', 'yshop1.3版本上线了', '<h3 data-v-01881cfc=\"\" style=\"text-align: center;\"><p>yshop1.3版本上线了</p></h3>', '', '', '4', NULL, '', NULL, '2019-08-22 12:26', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `yx_article` VALUES (3, '', '333', '333', 'http://localhost:8000/file/pic/list_16-20200110120838173.png', '11111', '<p>11111</p>', '', '', '', NULL, '', NULL, '1580631108', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `yx_article` VALUES (4, '', '4444', '444', '', '4444', '<p>4444</p>', '', '', '', NULL, '', NULL, '1580632147', NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for yx_cache
-- ----------------------------
DROP TABLE IF EXISTS `yx_cache`;
CREATE TABLE `yx_cache`  (
  `key` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `result` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '缓存数据',
  `add_time` int(10) NULL DEFAULT NULL COMMENT '缓存时间',
  PRIMARY KEY (`key`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '微信缓存表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yx_cache
-- ----------------------------
INSERT INTO `yx_cache` VALUES ('wechat_menus', '[{\"subButtons\":[{\"name\":\"公众号演示\",\"type\":\"view\",\"url\":\"https://h5.dayouqiantu.cn\"}],\"name\":\"Yshop商城3\",\"type\":\"click\",\"key\":\"yshop\"},{\"subButtons\":[{\"appId\":\"wxa82b5b7fcb0ec161\",\"name\":\"小程序演示\",\"pagePath\":\"pages/index\",\"type\":\"miniprogram\",\"url\":\"pages/index\"}],\"name\":\"供货商城\",\"type\":\"click\",\"key\":\"supply\"}]', 1570435277);

-- ----------------------------
-- Table structure for yx_express
-- ----------------------------
DROP TABLE IF EXISTS `yx_express`;
CREATE TABLE `yx_express`  (
  `id` mediumint(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '快递公司id',
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '快递公司简称',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '快递公司全称',
  `sort` int(11) NOT NULL DEFAULT 0 COMMENT '排序',
  `is_show` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否显示',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `code`(`code`) USING BTREE,
  INDEX `is_show`(`is_show`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 430 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '快递公司表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yx_express
-- ----------------------------
INSERT INTO `yx_express` VALUES (426, 'STO', '申通快递', 0, 0);
INSERT INTO `yx_express` VALUES (427, 'SF', '顺丰速运', 0, 0);
INSERT INTO `yx_express` VALUES (428, 'ZTO', '中通快递', 0, 0);
INSERT INTO `yx_express` VALUES (429, 'YTO', '圆通速递', 0, 0);

-- ----------------------------
-- Table structure for yx_material
-- ----------------------------
DROP TABLE IF EXISTS `yx_material`;
CREATE TABLE `yx_material`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT 'PK',
  `del_flag` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者ID',
  `type` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '类型1、图片；2、视频',
  `group_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '分组ID',
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '素材名',
  `url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '素材链接',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '素材库' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for yx_material_group
-- ----------------------------
DROP TABLE IF EXISTS `yx_material_group`;
CREATE TABLE `yx_material_group`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT 'PK',
  `del_flag` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者ID',
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '分组名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '素材分组' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yx_material_group
-- ----------------------------
INSERT INTO `yx_material_group` VALUES ('3e4ee0cdbb484aaeb5a40c337a47dba6', '0', '2020-01-09 18:48:24', NULL, '商品');

-- ----------------------------
-- Table structure for yx_routine_form_id
-- ----------------------------
DROP TABLE IF EXISTS `yx_routine_form_id`;
CREATE TABLE `yx_routine_form_id`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '表单ID表ID',
  `uid` int(11) NULL DEFAULT 0 COMMENT '用户uid',
  `form_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '表单ID',
  `stop_time` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '表单ID失效时间',
  `status` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '状态1 未使用 2不能使用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '表单id表记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for yx_routine_template
-- ----------------------------
DROP TABLE IF EXISTS `yx_routine_template`;
CREATE TABLE `yx_routine_template`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '模板id',
  `tempkey` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '模板编号',
  `name` char(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '模板名',
  `content` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '回复内容',
  `tempid` char(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模板ID',
  `add_time` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '添加时间',
  `status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `tempkey`(`tempkey`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '微信模板' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yx_routine_template
-- ----------------------------
INSERT INTO `yx_routine_template` VALUES (13, 'AT0007', '订单发货提醒', '订单号{{keyword1.DATA}}\n快递公司{{keyword2.DATA}}\n快递单号{{keyword3.DATA}}\n发货时间{{keyword4.DATA}}\n备注{{keyword5.DATA}}', 'fLsTVQmI2AE9q48BL58tffLRcyCnPLC9guSFrEHXbR8', '1534469928', 1);
INSERT INTO `yx_routine_template` VALUES (14, 'AT0787', '退款成功通知', '订单号{{keyword1.DATA}}\n退款时间{{keyword2.DATA}}\n退款金额{{keyword3.DATA}}\n退款方式{{keyword4.DATA}}\n备注{{keyword5.DATA}}', 'vgTj1nk1A1bVObCX0Fw3h9WbXvay1URcHMITjGW13cc', '1534469993', 1);
INSERT INTO `yx_routine_template` VALUES (15, 'AT0009', '订单支付成功通知', '单号{{keyword1.DATA}}\n下单时间{{keyword2.DATA}}\n订单状态{{keyword3.DATA}}\n支付金额{{keyword4.DATA}}\n支付方式{{keyword5.DATA}}', 'Rwt2QIz3zHbQGPXCH3E8nBneWzgFFBMiMI6EShTBLpI', '1534470043', 1);
INSERT INTO `yx_routine_template` VALUES (16, 'AT1173', '砍价成功通知', '商品名称{{keyword1.DATA}}\n砍价金额{{keyword2.DATA}}\n底价{{keyword3.DATA}}\n砍掉价格{{keyword4.DATA}}\n支付金额{{keyword5.DATA}}\n备注{{keyword6.DATA}}', 'qu3nRoV9lTmmvVWOdgf-2uz8NPT3w71cOx7UDJ5Uryo', '1534470085', 1);
INSERT INTO `yx_routine_template` VALUES (17, 'AT0036', '退款通知', '订单编号{{keyword1.DATA}}\n退款原因{{keyword2.DATA}}\n退款时间{{keyword3.DATA}}\n退款金额{{keyword4.DATA}}\n退款方式{{keyword5.DATA}}', 'gYYXx5nz8eWHPd_zp42EdR9UzhdynOo8Eq5GDb4dTrc', '1534470134', 1);
INSERT INTO `yx_routine_template` VALUES (19, 'AT2430', '拼团取消通知', '活动名称{{keyword1.DATA}}\n订单编号{{keyword2.DATA}}\n订单金额{{keyword3.DATA}}', 'OmzyMqsWJFn3L68DmEcZqV9FdSHrr2ThHYe6Czs8iaM', '1553910500', 1);
INSERT INTO `yx_routine_template` VALUES (20, 'AT0310', '拼团失败通知', '商品名称{{keyword1.DATA}}\n失败原因{{keyword2.DATA}}\n订单号{{keyword3.DATA}}\n开团时间{{keyword4.DATA}}\n退款金额{{keyword5.DATA}}', 'GRpTaZ1-tHoLvrfM-iEsBBbVSIXgh7bO17b7Z2AUpKM', '1553910844', 1);
INSERT INTO `yx_routine_template` VALUES (21, 'AT0051', '拼团成功通知', '活动名称{{keyword1.DATA}}\n团长{{keyword2.DATA}}\n成团时间{{keyword3.DATA}}\n拼团价{{keyword4.DATA}}', 'dlYqzgGLrVgRq-Ealw31wCy5PV-rGof-99OppSZhWxY', '1553911022', 1);
INSERT INTO `yx_routine_template` VALUES (22, 'AT0541', '开团成功提醒', '开团时间{{keyword1.DATA}}\n截至时间{{keyword2.DATA}}\n产品名称{{keyword3.DATA}}\n单号{{keyword4.DATA}}\n支付金额{{keyword5.DATA}}', 'E2NURfqs4MnciPB1YzvjtMDZCMfCCOHOwCdKFl7XigU', '1555133496', 1);
INSERT INTO `yx_routine_template` VALUES (23, 'AT0241', '确认收货通知', '订单编号{{keyword1.DATA}}\n商品详情{{keyword2.DATA}}\n支付金额{{keyword3.DATA}}\n确认收货时间{{keyword4.DATA}}', '4-DQPQMVsJfHjiKhEACz0ofcs_PHupy3xHTX1N1tzTo', '1557384781', 1);
INSERT INTO `yx_routine_template` VALUES (24, 'AT0329', '退款失败通知', '订单号{{keyword1.DATA}}\n商品名称{{keyword2.DATA}}\n退款金额{{keyword3.DATA}}\n失败原因{{keyword4.DATA}}', 'rlOEIDa0s6PMG4L_CkDLpBWCPf_mGci3z86UC5LmXCc', '1557384804', 1);
INSERT INTO `yx_routine_template` VALUES (25, 'AT1242', '提现失败通知', '失败原因{{keyword1.DATA}}\n提现金额{{keyword2.DATA}}\n提现方式{{keyword3.DATA}}\n审核时间{{keyword4.DATA}}', 'MsVoI7IV4CTFObS9XoJYTjMWPh8zjbNRK5-Z_II0Dnk', '1558918703', 1);
INSERT INTO `yx_routine_template` VALUES (26, 'AT1330', ' 提现成功通知', '提现金额{{keyword1.DATA}}\n提现状态{{keyword2.DATA}}\n审核时间{{keyword3.DATA}}\n提现方式{{keyword4.DATA}}', 'Twp4EWZTRUF2mps0J5Inw55_DpbNEfqqdKvfEx_ihos', '1558918767', 1);

-- ----------------------------
-- Table structure for yx_store_bargain
-- ----------------------------
DROP TABLE IF EXISTS `yx_store_bargain`;
CREATE TABLE `yx_store_bargain`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '砍价产品ID',
  `product_id` int(11) UNSIGNED NOT NULL COMMENT '关联产品ID',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '砍价活动名称',
  `image` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '砍价活动图片',
  `unit_name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位名称',
  `stock` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '库存',
  `sales` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '销量',
  `images` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '砍价产品轮播图',
  `start_time` int(11) UNSIGNED NOT NULL COMMENT '砍价开启时间',
  `stop_time` int(11) UNSIGNED NOT NULL COMMENT '砍价结束时间',
  `store_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '砍价产品名称',
  `price` decimal(8, 2) UNSIGNED NULL DEFAULT NULL COMMENT '砍价金额',
  `min_price` decimal(8, 2) UNSIGNED NULL DEFAULT NULL COMMENT '砍价商品最低价',
  `num` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '每次购买的砍价产品数量',
  `bargain_max_price` decimal(8, 2) UNSIGNED NULL DEFAULT NULL COMMENT '用户每次砍价的最大金额',
  `bargain_min_price` decimal(8, 2) UNSIGNED NULL DEFAULT NULL COMMENT '用户每次砍价的最小金额',
  `bargain_num` int(11) UNSIGNED NOT NULL DEFAULT 1 COMMENT '用户每次砍价的次数',
  `status` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '砍价状态 0(到砍价时间不自动开启)  1(到砍价时间自动开启时间)',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '砍价详情',
  `give_integral` decimal(10, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '反多少积分',
  `info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '砍价活动简介',
  `cost` decimal(8, 2) UNSIGNED NULL DEFAULT NULL COMMENT '成本价',
  `sort` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序',
  `is_hot` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否推荐0不推荐1推荐',
  `is_del` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除 1删除',
  `add_time` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '添加时间',
  `is_postage` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否包邮 0不包邮 1包邮',
  `postage` decimal(10, 2) UNSIGNED NULL DEFAULT NULL COMMENT '邮费',
  `rule` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '砍价规则',
  `look` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '砍价产品浏览量',
  `share` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '砍价产品分享量',
  `end_time_date` datetime(0) NOT NULL,
  `start_time_date` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '砍价表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yx_store_bargain
-- ----------------------------
INSERT INTO `yx_store_bargain` VALUES (2, 20, '砍价测试01', 'https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg', '张', 100, 1, 'https://image.dayouqiantu.cn/5ca05103ad634.jpg,https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg', 1577090495, 1585213664, '70gA4彩色打印复印', 10.00, 0.01, 1, 2.00, 0.01, 1, 1, '<p style=\"text-align: center;\">7<strong>文件请传QQ2412733099，</strong></p><p style=\"text-align: center;\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\"text-align: center;\"><strong>手机/微信：13733990583</strong></p><p style=\"text-align: center;\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\"text-align: center;\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\"text-align: center;\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\"text-align: center;\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\"text-align: center;\"><strong>更大量请联系店主报价，</strong></p><p style=\"text-align: center;\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\"text-align: center;\"><strong>付款时直接抵扣</strong></p><p style=\"text-align: center;\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\"text-align: center;\"><img src=\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\" style=\"max-width:100%;\"><strong><br></strong></p><p style=\"text-align: center;\"><br></p>', 0.00, '70gA4彩色打印复印', 0.50, 0, 0, 0, 1577090553, 1, 0.00, '<p>这里是规则</p>', 40, 1, '2019-12-31 02:00:00', '2019-12-23 16:41:35');
INSERT INTO `yx_store_bargain` VALUES (3, 20, '砍价活动2', 'https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg', '张', 100, 1, 'https://image.dayouqiantu.cn/5ca05103ad634.jpg,https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg,http://localhost:8000/file/pic/05ea40b831858a8cf423aa709840507c-20200228083801500.png', 1578412800, 1580313600, '70gA4彩色打印复印', 0.01, 0.01, 1, 10.00, 0.01, 1, 1, '<p style=\"text-align: center;\">7<strong>文件请传QQ2412733099，</strong></p><p style=\"text-align: center;\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\"text-align: center;\"><strong>手机/微信：13733990583</strong></p><p style=\"text-align: center;\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\"text-align: center;\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\"text-align: center;\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\"text-align: center;\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\"text-align: center;\"><strong>更大量请联系店主报价，</strong></p><p style=\"text-align: center;\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\"text-align: center;\"><strong>付款时直接抵扣</strong></p><p style=\"text-align: center;\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\"text-align: center;\"><img src=\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\" style=\"max-width:100%;\"><strong><br></strong></p><p style=\"text-align: center;\"><br></p>', 0.00, '70gA4彩色打印复印', 0.50, 0, 0, 0, 1577101179, 1, 0.00, '<p>444444</p>', 0, 0, '2020-01-30 00:00:00', '2020-01-08 00:00:00');

-- ----------------------------
-- Table structure for yx_store_bargain_user
-- ----------------------------
DROP TABLE IF EXISTS `yx_store_bargain_user`;
CREATE TABLE `yx_store_bargain_user`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户参与砍价表ID',
  `uid` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '用户ID',
  `bargain_id` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '砍价产品id',
  `bargain_price_min` decimal(8, 2) UNSIGNED NULL DEFAULT NULL COMMENT '砍价的最低价',
  `bargain_price` decimal(8, 2) NULL DEFAULT NULL COMMENT '砍价金额',
  `price` decimal(8, 2) UNSIGNED NULL DEFAULT NULL COMMENT '砍掉的价格',
  `status` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '状态 1参与中 2 活动结束参与失败 3活动结束参与成功',
  `add_time` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '参与时间',
  `is_del` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否取消',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户参与砍价表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yx_store_bargain_user
-- ----------------------------
INSERT INTO `yx_store_bargain_user` VALUES (32, 12, 2, 0.01, 10.00, 1.06, 1, 1577099027, 1);
INSERT INTO `yx_store_bargain_user` VALUES (33, 12, 2, 0.01, 10.00, 1.34, 1, 1577099210, 0);
INSERT INTO `yx_store_bargain_user` VALUES (34, 11, 2, 0.01, 10.00, 0.71, 1, 1582708240, 0);

-- ----------------------------
-- Table structure for yx_store_bargain_user_help
-- ----------------------------
DROP TABLE IF EXISTS `yx_store_bargain_user_help`;
CREATE TABLE `yx_store_bargain_user_help`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '砍价用户帮助表ID',
  `uid` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '帮助的用户id',
  `bargain_id` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '砍价产品ID',
  `bargain_user_id` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '用户参与砍价表id',
  `price` decimal(8, 2) UNSIGNED NULL DEFAULT NULL COMMENT '帮助砍价多少金额',
  `add_time` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '砍价用户帮助表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yx_store_bargain_user_help
-- ----------------------------
INSERT INTO `yx_store_bargain_user_help` VALUES (33, 12, 2, 32, 1.06, 1577099027);
INSERT INTO `yx_store_bargain_user_help` VALUES (34, 12, 2, 33, 1.34, 1577099210);
INSERT INTO `yx_store_bargain_user_help` VALUES (35, 11, 2, 34, 0.71, 1582708240);

-- ----------------------------
-- Table structure for yx_store_cart
-- ----------------------------
DROP TABLE IF EXISTS `yx_store_cart`;
CREATE TABLE `yx_store_cart`  (
  `id` bigint(8) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '购物车表ID',
  `uid` int(10) UNSIGNED NOT NULL COMMENT '用户ID',
  `type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型',
  `product_id` int(10) UNSIGNED NOT NULL COMMENT '商品ID',
  `product_attr_unique` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商品属性',
  `cart_num` smallint(5) UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品数量',
  `add_time` int(10) UNSIGNED NOT NULL COMMENT '添加时间',
  `is_pay` tinyint(1) NOT NULL DEFAULT 0 COMMENT '0 = 未购买 1 = 已购买',
  `is_del` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
  `is_new` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否为立即购买',
  `combination_id` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '拼团id',
  `seckill_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '秒杀产品ID',
  `bargain_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '砍价id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`uid`) USING BTREE,
  INDEX `goods_id`(`product_id`) USING BTREE,
  INDEX `uid`(`uid`, `is_pay`) USING BTREE,
  INDEX `uid_2`(`uid`, `is_del`) USING BTREE,
  INDEX `uid_3`(`uid`, `is_new`) USING BTREE,
  INDEX `type`(`type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 140 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '购物车表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yx_store_cart
-- ----------------------------
INSERT INTO `yx_store_cart` VALUES (123, 11, 'product', 19, '', 2, 1582885462, 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (124, 12, 'product', 19, '', 1, 1582897654, 0, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (125, 11, 'product', 19, '', 2, 1582970873, 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (126, 11, 'product', 19, '', 1, 1582971001, 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (127, 11, 'product', 19, '', 1, 1582971278, 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (128, 11, 'product', 19, '', 1, 1582990627, 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (129, 11, 'product', 19, '', 1, 1583330733, 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (130, 11, 'product', 19, '', 1, 1583393646, 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (131, 11, 'product', 19, '', 1, 1583394583, 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (132, 11, 'product', 19, '', 1, 1583399181, 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (133, 11, 'product', 19, '', 1, 1583403251, 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (134, 11, 'product', 19, '', 1, 1583410813, 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (135, 11, 'product', 19, '', 1, 1583416029, 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (136, 11, 'product', 20, '2e51e5d2d86a44748f81cdd5bf7a51c0', 1, 1583422305, 0, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (137, 11, 'product', 19, '', 1, 1583479387, 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (138, 11, 'product', 19, '', 1, 1583486497, 1, 0, 0, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (139, 11, 'product', 20, '2e51e5d2d86a44748f81cdd5bf7a51c0', 1, 1583486504, 1, 0, 0, 0, 0, 0);

-- ----------------------------
-- Table structure for yx_store_category
-- ----------------------------
DROP TABLE IF EXISTS `yx_store_category`;
CREATE TABLE `yx_store_category`  (
  `id` mediumint(11) NOT NULL AUTO_INCREMENT COMMENT '商品分类表ID',
  `pid` mediumint(11) NOT NULL COMMENT '父id',
  `cate_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类名称',
  `sort` mediumint(11) NULL DEFAULT NULL COMMENT '排序',
  `pic` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '图标',
  `is_show` tinyint(1) NULL DEFAULT 1 COMMENT '是否推荐',
  `add_time` int(11) NULL DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `pid`(`pid`) USING BTREE,
  INDEX `is_base`(`is_show`) USING BTREE,
  INDEX `sort`(`sort`) USING BTREE,
  INDEX `add_time`(`add_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yx_store_category
-- ----------------------------
INSERT INTO `yx_store_category` VALUES (1, 0, '打印复印', 1, '', 1, NULL);
INSERT INTO `yx_store_category` VALUES (25, 0, '广告印刷', 2, '', 1, NULL);
INSERT INTO `yx_store_category` VALUES (26, 27, '广告印刷', 2, 'https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg', 1, NULL);
INSERT INTO `yx_store_category` VALUES (27, 0, '顶级0', 1, 'https://image.dayouqiantu.cn/list_34.png', 1, 1578371578);
INSERT INTO `yx_store_category` VALUES (28, 1, '字类', 2, 'https://image.dayouqiantu.cn/list_34.png', 1, 1578373561);
INSERT INTO `yx_store_category` VALUES (29, 25, '哈哈哈', 2, 'https://image.dayouqiantu.cn/list_34.png', 1, 1578373573);
INSERT INTO `yx_store_category` VALUES (30, 0, '444', 2, 'http://localhost:8000/file/pic/05ea40b831858a8cf423aa709840507c-20200228083801500.png', 1, 1583312551);
INSERT INTO `yx_store_category` VALUES (31, 0, '777', 2, 'http://localhost:8000/file/pic/05ea40b831858a8cf423aa709840507c-20200228083801500.png', 1, 1583312741);

-- ----------------------------
-- Table structure for yx_store_combination
-- ----------------------------
DROP TABLE IF EXISTS `yx_store_combination`;
CREATE TABLE `yx_store_combination`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `product_id` int(10) UNSIGNED NOT NULL COMMENT '商品id',
  `mer_id` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '商户id',
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '推荐图',
  `images` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '轮播图',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '活动标题',
  `attr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '活动属性',
  `people` int(2) UNSIGNED NOT NULL COMMENT '参团人数',
  `info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '简介',
  `price` decimal(10, 2) UNSIGNED NOT NULL COMMENT '价格',
  `sort` int(10) UNSIGNED NOT NULL COMMENT '排序',
  `sales` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '销量',
  `stock` int(10) UNSIGNED NOT NULL COMMENT '库存',
  `add_time` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '添加时间',
  `is_host` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '推荐',
  `is_show` tinyint(1) UNSIGNED NOT NULL COMMENT '产品状态',
  `is_del` tinyint(1) UNSIGNED NOT NULL DEFAULT 0,
  `combination` tinyint(1) UNSIGNED NOT NULL DEFAULT 1,
  `mer_use` tinyint(1) UNSIGNED NULL DEFAULT NULL COMMENT '商户是否可用1可用0不可用',
  `is_postage` tinyint(1) UNSIGNED NOT NULL COMMENT '是否包邮1是0否',
  `postage` decimal(10, 2) UNSIGNED NOT NULL COMMENT '邮费',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '拼团内容',
  `start_time` int(11) UNSIGNED NOT NULL COMMENT '拼团开始时间',
  `stop_time` int(11) UNSIGNED NOT NULL COMMENT '拼团结束时间',
  `effective_time` int(11) NOT NULL DEFAULT 0 COMMENT '拼团订单有效时间',
  `cost` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '拼图产品成本',
  `browse` int(11) NULL DEFAULT 0 COMMENT '浏览量',
  `unit_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '单位名',
  `end_time_date` datetime(0) NOT NULL,
  `start_time_date` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '拼团产品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yx_store_combination
-- ----------------------------
INSERT INTO `yx_store_combination` VALUES (1, 19, NULL, 'https://image.dayouqiantu.cn/5ca011a1cd487.jpg', 'https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg', '70gA4黑白打印复印', NULL, 2, '打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务', 1.00, 1, 19, 98, '1574064564', 1, 1, 0, 1, NULL, 1, 0.00, '<p style=\"text-align: center;\"><strong>文件请传QQ2412733099，</strong></p><p style=\"text-align: center;\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\"text-align: center;\"><strong>手机/微信：13733990583</strong></p><p style=\"text-align: center;\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\"text-align: center;\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\"text-align: center;\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\"text-align: center;\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\"text-align: center;\"><strong>更大量请联系店主报价，</strong></p><p style=\"text-align: center;\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\"text-align: center;\"><strong>付款时直接抵扣</strong></p><p style=\"text-align: center;\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\"text-align: left;\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\" style=\"max-width: 100%;\"><br></p>', 1574064557, 1585213664, 24, 0, 0, '张', '2019-11-22 00:00:00', '2019-11-18 16:09:17');
INSERT INTO `yx_store_combination` VALUES (2, 19, NULL, 'https://image.dayouqiantu.cn/5ca011a1cd487.jpg', 'https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,http://localhost:8000/file/pic/05ea40b831858a8cf423aa709840507c-20200228083801500.png', '70gA4黑白打印复印', NULL, 2, '打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务', 1.00, 1, 28, 89, '1574066608', 1, 1, 0, 1, NULL, 1, 0.00, '<p style=\"text-align: center;\"><strong>文件请传QQ2412733099，</strong></p><p style=\"text-align: center;\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\"text-align: center;\"><strong>手机/微信：13733990583</strong></p><p style=\"text-align: center;\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\"text-align: center;\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\"text-align: center;\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\"text-align: center;\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\"text-align: center;\"><strong>更大量请联系店主报价，</strong></p><p style=\"text-align: center;\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\"text-align: center;\"><strong>付款时直接抵扣</strong></p><p style=\"text-align: center;\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\"text-align: left;\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\" style=\"max-width: 100%;\"><br></p>', 1574066592, 1574870400, 24, 0, 0, '张', '2019-11-28 00:00:00', '2019-11-18 16:43:12');
INSERT INTO `yx_store_combination` VALUES (3, 22, 0, 'http://localhost:8000/file/pic/list_16-20200110120838173.png', 'http://localhost:8000/file/pic/list_16-20200110120838173.png,http://localhost:8000/file/pic/list_32-20200109100213309.png', '3333', NULL, 2, '3333', 1.00, 0, 0, 11, '1578644367', 0, 1, 1, 1, NULL, 0, 0.00, '<p>4444</p>', 1578644348, 1585213664, 24, 1, 0, '333', '2020-01-29 00:00:00', '2020-01-10 16:19:08');

-- ----------------------------
-- Table structure for yx_store_combination_attr
-- ----------------------------
DROP TABLE IF EXISTS `yx_store_combination_attr`;
CREATE TABLE `yx_store_combination_attr`  (
  `product_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品ID',
  `attr_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '属性名',
  `attr_values` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '属性值',
  INDEX `store_id`(`product_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品属性表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for yx_store_combination_attr_result
-- ----------------------------
DROP TABLE IF EXISTS `yx_store_combination_attr_result`;
CREATE TABLE `yx_store_combination_attr_result`  (
  `product_id` int(10) UNSIGNED NOT NULL COMMENT '商品ID',
  `result` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品属性参数',
  `change_time` int(10) UNSIGNED NOT NULL COMMENT '上次修改时间',
  UNIQUE INDEX `product_id`(`product_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品属性详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for yx_store_combination_attr_value
-- ----------------------------
DROP TABLE IF EXISTS `yx_store_combination_attr_value`;
CREATE TABLE `yx_store_combination_attr_value`  (
  `product_id` int(10) UNSIGNED NOT NULL COMMENT '商品ID',
  `suk` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品属性索引值 (attr_value|attr_value[|....])',
  `stock` int(10) UNSIGNED NOT NULL COMMENT '属性对应的库存',
  `sales` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '销量',
  `price` decimal(8, 2) UNSIGNED NOT NULL COMMENT '属性金额',
  `image` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片',
  `unique` char(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '唯一值',
  `cost` decimal(8, 2) UNSIGNED NOT NULL COMMENT '成本价',
  UNIQUE INDEX `unique`(`unique`, `suk`) USING BTREE,
  INDEX `store_id`(`product_id`, `suk`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品属性值表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for yx_store_coupon
-- ----------------------------
DROP TABLE IF EXISTS `yx_store_coupon`;
CREATE TABLE `yx_store_coupon`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '优惠券表ID',
  `title` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '优惠券名称',
  `integral` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '兑换消耗积分值',
  `coupon_price` decimal(8, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '兑换的优惠券面值',
  `use_min_price` decimal(8, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '最低消费多少金额可用优惠券',
  `coupon_time` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '优惠券有效期限（单位：天）',
  `sort` int(11) UNSIGNED NOT NULL DEFAULT 1 COMMENT '排序',
  `status` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '状态（0：关闭，1：开启）',
  `add_time` int(11) UNSIGNED NOT NULL COMMENT '兑换项目添加时间',
  `is_del` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `state`(`status`) USING BTREE,
  INDEX `add_time`(`add_time`) USING BTREE,
  INDEX `coupon_time`(`coupon_time`) USING BTREE,
  INDEX `is_del`(`is_del`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '优惠券表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yx_store_coupon
-- ----------------------------
INSERT INTO `yx_store_coupon` VALUES (1, '双十一', 0, 1.00, 100.00, 10, 0, 1, 1573281957, 0);
INSERT INTO `yx_store_coupon` VALUES (2, '44444446', 0, 1.00, 1.00, 1, 0, 1, 1574130748, 0);
INSERT INTO `yx_store_coupon` VALUES (3, '999', 0, 1.00, 1.00, 1, 1, 1, 1574131073, 0);
INSERT INTO `yx_store_coupon` VALUES (4, '333', 0, 3.00, 3.00, 1, 0, 1, 1582894050, 0);
INSERT INTO `yx_store_coupon` VALUES (5, '555', 0, 3.00, 1.00, 1, 0, 1, 1582894121, 0);
INSERT INTO `yx_store_coupon` VALUES (6, '44', 0, 4.00, 4.00, 1, 0, 1, 1582894183, 0);

-- ----------------------------
-- Table structure for yx_store_coupon_issue
-- ----------------------------
DROP TABLE IF EXISTS `yx_store_coupon_issue`;
CREATE TABLE `yx_store_coupon_issue`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `cname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `cid` int(10) NULL DEFAULT NULL COMMENT '优惠券ID',
  `start_time` int(10) NULL DEFAULT NULL COMMENT '优惠券领取开启时间',
  `end_time` int(10) NULL DEFAULT NULL COMMENT '优惠券领取结束时间',
  `total_count` int(10) NULL DEFAULT NULL COMMENT '优惠券领取数量',
  `remain_count` int(10) NULL DEFAULT NULL COMMENT '优惠券剩余领取数量',
  `is_permanent` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否无限张数',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '1 正常 0 未开启 -1 已无效',
  `is_del` tinyint(1) UNSIGNED NOT NULL DEFAULT 0,
  `add_time` int(10) NULL DEFAULT NULL COMMENT '优惠券添加时间',
  `end_time_date` datetime(0) NOT NULL,
  `start_time_date` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `cid`(`cid`) USING BTREE,
  INDEX `start_time`(`start_time`, `end_time`) USING BTREE,
  INDEX `remain_count`(`remain_count`) USING BTREE,
  INDEX `status`(`status`) USING BTREE,
  INDEX `is_del`(`is_del`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '优惠券前台领取表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yx_store_coupon_issue
-- ----------------------------
INSERT INTO `yx_store_coupon_issue` VALUES (11, '双十一', 1, 1573352364, 1577277208, 1000, 999, 0, 1, 0, 1573352375, '2020-01-15 01:00:00', '2019-12-02 10:19:24');
INSERT INTO `yx_store_coupon_issue` VALUES (12, '双十一', 1, 1573353166, 1577277208, 100, 99, 0, 1, 0, 1573353175, '2019-12-26 00:00:00', '2019-11-10 10:32:46');
INSERT INTO `yx_store_coupon_issue` VALUES (13, '双十一', 1, 1573353254, 1577277208, 10, 9, 0, 1, 0, 1573353261, '2019-12-26 00:00:00', '2019-11-10 10:34:14');
INSERT INTO `yx_store_coupon_issue` VALUES (14, '999', 3, 1582530746, 1582905600, 100, 100, 0, 0, 1, 1582530755, '2020-02-29 00:00:00', '2020-02-24 15:52:26');

-- ----------------------------
-- Table structure for yx_store_coupon_issue_user
-- ----------------------------
DROP TABLE IF EXISTS `yx_store_coupon_issue_user`;
CREATE TABLE `yx_store_coupon_issue_user`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `uid` int(10) NULL DEFAULT NULL COMMENT '领取优惠券用户ID',
  `issue_coupon_id` int(10) NULL DEFAULT NULL COMMENT '优惠券前台领取ID',
  `add_time` int(10) NULL DEFAULT NULL COMMENT '领取时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uid`(`uid`, `issue_coupon_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '优惠券前台用户领取记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yx_store_coupon_issue_user
-- ----------------------------
INSERT INTO `yx_store_coupon_issue_user` VALUES (1, 12, 11, 1573353024);
INSERT INTO `yx_store_coupon_issue_user` VALUES (2, 12, 12, 1573353194);
INSERT INTO `yx_store_coupon_issue_user` VALUES (3, 12, 13, 1573353269);

-- ----------------------------
-- Table structure for yx_store_coupon_user
-- ----------------------------
DROP TABLE IF EXISTS `yx_store_coupon_user`;
CREATE TABLE `yx_store_coupon_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '优惠券发放记录id',
  `cid` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '兑换的项目id',
  `uid` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '优惠券所属用户',
  `coupon_title` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '优惠券名称',
  `coupon_price` decimal(8, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '优惠券的面值',
  `use_min_price` decimal(8, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '最低消费多少金额可用优惠券',
  `add_time` int(11) UNSIGNED NOT NULL COMMENT '优惠券创建时间',
  `end_time` int(11) UNSIGNED NOT NULL COMMENT '优惠券结束时间',
  `use_time` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用时间',
  `type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'send' COMMENT '获取方式',
  `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态（0：未使用，1：已使用, 2:已过期）',
  `is_fail` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否有效',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `cid`(`cid`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE,
  INDEX `add_time`(`add_time`) USING BTREE,
  INDEX `end_time`(`end_time`) USING BTREE,
  INDEX `status`(`status`) USING BTREE,
  INDEX `is_fail`(`is_fail`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '优惠券发放记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yx_store_coupon_user
-- ----------------------------
INSERT INTO `yx_store_coupon_user` VALUES (1, 1, 12, '双十一', 1.00, 100.00, 1573353024, 1574217024, 0, 'get', 2, 0);
INSERT INTO `yx_store_coupon_user` VALUES (2, 1, 12, '双十一', 1.00, 100.00, 1573353194, 1574217194, 1573380051, 'get', 1, 0);
INSERT INTO `yx_store_coupon_user` VALUES (3, 1, 12, '双十一', 1.00, 100.00, 1573353269, 1574217269, 0, 'get', 2, 0);

-- ----------------------------
-- Table structure for yx_store_order
-- ----------------------------
DROP TABLE IF EXISTS `yx_store_order`;
CREATE TABLE `yx_store_order`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `order_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单号',
  `extend_order_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '额外订单号',
  `uid` int(11) UNSIGNED NOT NULL COMMENT '用户id',
  `real_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户姓名',
  `user_phone` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户电话',
  `user_address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '详细地址',
  `cart_id` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '[]' COMMENT '购物车id',
  `freight_price` decimal(8, 2) NOT NULL DEFAULT 0.00 COMMENT '运费金额',
  `total_num` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '订单商品总数',
  `total_price` decimal(8, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '订单总价',
  `total_postage` decimal(8, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '邮费',
  `pay_price` decimal(8, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '实际支付金额',
  `pay_postage` decimal(8, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '支付邮费',
  `deduction_price` decimal(8, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '抵扣金额',
  `coupon_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '优惠券id',
  `coupon_price` decimal(8, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '优惠券金额',
  `paid` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '支付状态',
  `pay_time` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '支付时间',
  `pay_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '支付方式',
  `add_time` int(11) UNSIGNED NOT NULL COMMENT '创建时间',
  `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '订单状态（-1 : 申请退款 -2 : 退货成功 0：待发货；1：待收货；2：已收货；3：待评价；-1：已退款）',
  `refund_status` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '0 未退款 1 申请中 2 已退款',
  `refund_reason_wap_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '退款图片',
  `refund_reason_wap_explain` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '退款用户说明',
  `refund_reason_time` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '退款时间',
  `refund_reason_wap` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '前台退款原因',
  `refund_reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '不退款的理由',
  `refund_price` decimal(8, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '退款金额',
  `delivery_sn` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '快递公司编号',
  `delivery_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '快递名称/送货人姓名',
  `delivery_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发货类型',
  `delivery_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '快递单号/手机号',
  `gain_integral` decimal(8, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '消费赚取积分',
  `use_integral` decimal(8, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '使用积分',
  `back_integral` decimal(8, 2) UNSIGNED NULL DEFAULT NULL COMMENT '给用户退了多少积分',
  `mark` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '备注',
  `is_del` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除',
  `unique` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '唯一id(md5加密)类似id',
  `remark` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员备注',
  `mer_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '商户ID',
  `is_mer_check` tinyint(3) UNSIGNED NOT NULL DEFAULT 0,
  `combination_id` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '拼团产品id0一般产品',
  `pink_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '拼团id 0没有拼团',
  `cost` decimal(8, 2) UNSIGNED NOT NULL COMMENT '成本价',
  `seckill_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '秒杀产品ID',
  `bargain_id` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '砍价id',
  `verify_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '核销码',
  `store_id` int(11) NOT NULL DEFAULT 0 COMMENT '门店id',
  `shipping_type` tinyint(1) NOT NULL DEFAULT 1 COMMENT '配送方式 1=快递 ，2=门店自提',
  `is_channel` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '支付渠道(0微信公众号1微信小程序)',
  `is_remind` tinyint(1) UNSIGNED NULL DEFAULT 0,
  `is_system_del` tinyint(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `order_id_2`(`order_id`, `uid`) USING BTREE,
  UNIQUE INDEX `unique`(`unique`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE,
  INDEX `add_time`(`add_time`) USING BTREE,
  INDEX `pay_price`(`pay_price`) USING BTREE,
  INDEX `paid`(`paid`) USING BTREE,
  INDEX `pay_time`(`pay_time`) USING BTREE,
  INDEX `pay_type`(`pay_type`) USING BTREE,
  INDEX `status`(`status`) USING BTREE,
  INDEX `is_del`(`is_del`) USING BTREE,
  INDEX `coupon_id`(`coupon_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 136 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yx_store_order
-- ----------------------------
INSERT INTO `yx_store_order` VALUES (129, '1235472550146867200', NULL, 11, 'hupeng', '15136175234', '   ', '131', 0.00, 1, 120.00, 0.00, 120.00, 0.00, 0.00, 0, 0.00, 1, 1583394584, 'yue', 1583394584, 2, 0, NULL, NULL, NULL, NULL, NULL, 0.00, '', NULL, NULL, NULL, 1.00, 0.00, NULL, '', 0, '00c3ee5544424633aa45b0a8bd64e4a4', NULL, 0, 0, 0, 0, 0.20, 0, 0, '550146867200', 3, 2, 1, 0, 0);
INSERT INTO `yx_store_order` VALUES (130, '1235491951936012288', NULL, 11, '胡鹏', '15139175246', '重庆 荣昌县 广顺镇 999', '132', 0.00, 1, 120.00, 10.00, 130.00, 10.00, 0.00, 0, 0.00, 1, 1583399210, 'yue', 1583399210, 0, 0, NULL, NULL, NULL, NULL, NULL, 0.00, '', NULL, NULL, NULL, 1.00, 0.00, NULL, '', 0, 'df23e790ed19483b8cf35e5ee8069a20', NULL, 0, 0, 0, 0, 0.20, 0, 0, '', 0, 1, 1, 0, 0);
INSERT INTO `yx_store_order` VALUES (131, '1235509056156729344', NULL, 11, 'hupeng', '15136175234', '   ', '133', 0.00, 1, 120.00, 0.00, 120.00, 0.00, 0.00, 0, 0.00, 1, 1583403288, 'yue', 1583403288, 2, 0, NULL, NULL, NULL, NULL, NULL, 0.00, '', NULL, NULL, NULL, 1.00, 0.00, NULL, '', 0, '5988854ea23645baa269e3b74eede8c0', NULL, 0, 0, 0, 0, 0.20, 0, 0, '056156729344', 3, 2, 1, 0, 0);
INSERT INTO `yx_store_order` VALUES (132, '1235540680072232960', NULL, 11, 'hupeng', '15136175246', '   ', '134', 0.00, 1, 120.00, 0.00, 120.00, 0.00, 0.00, 0, 0.00, 1, 1583410828, 'yue', 1583410828, 0, 0, NULL, NULL, NULL, NULL, NULL, 0.00, '', NULL, NULL, NULL, 1.00, 0.00, NULL, '', 0, '15c281cfa80f4e01b6b6cfd0f0ebabc9', NULL, 0, 0, 0, 0, 0.20, 0, 0, '680072232960', 3, 2, 1, 0, 0);
INSERT INTO `yx_store_order` VALUES (133, '1235562508245270528', NULL, 11, '胡鹏', '15139175246', '重庆 荣昌县 广顺镇 999', '135', 0.00, 1, 120.00, 10.00, 130.00, 10.00, 0.00, 0, 0.00, 1, 1583416032, 'yue', 1583416032, 0, 0, NULL, NULL, NULL, NULL, NULL, 0.00, '', NULL, NULL, NULL, 1.00, 0.00, NULL, '', 0, 'c5a8614162014e088e208bcf3a0250ed', NULL, 0, 0, 0, 0, 0.20, 0, 0, '', 0, 1, 1, 0, 0);
INSERT INTO `yx_store_order` VALUES (134, '1235828261041209344', NULL, 11, '胡鹏', '15139175246', '重庆 荣昌县 广顺镇 999', '137', 0.00, 1, 120.00, 10.00, 130.00, 10.00, 0.00, 0, 0.00, 0, NULL, 'weixin', 1583479392, 0, 0, NULL, NULL, NULL, NULL, NULL, 0.00, '', NULL, NULL, NULL, 1.00, 0.00, NULL, '', 0, '90de3ebc3bcc411092ac126532e4db76', NULL, 0, 0, 0, 0, 0.20, 0, 0, '', 0, 1, 1, 0, 0);
INSERT INTO `yx_store_order` VALUES (135, '1235858128151511040', NULL, 11, '胡鹏', '15139175246', '重庆 荣昌县 广顺镇 999', '139,138', 0.00, 2, 120.50, 15.00, 135.50, 15.00, 0.00, 0, 0.00, 1, 1583486513, 'yue', 1583486513, 0, 0, NULL, NULL, NULL, NULL, NULL, 0.00, '', NULL, NULL, NULL, 1.00, 0.00, NULL, '', 0, '715bec6fbc694f72b5696cf0ebe8f4b6', NULL, 0, 0, 0, 0, 0.70, 0, 0, '', 0, 1, 1, 0, 0);

-- ----------------------------
-- Table structure for yx_store_order_cart_info
-- ----------------------------
DROP TABLE IF EXISTS `yx_store_order_cart_info`;
CREATE TABLE `yx_store_order_cart_info`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `oid` int(11) UNSIGNED NOT NULL COMMENT '订单id',
  `cart_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '购物车id',
  `product_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品ID',
  `cart_info` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '购买东西的详细信息',
  `unique` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '唯一id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `oid`(`oid`, `unique`) USING BTREE,
  INDEX `cart_id`(`cart_id`) USING BTREE,
  INDEX `product_id`(`product_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 133 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单购物详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yx_store_order_cart_info
-- ----------------------------
INSERT INTO `yx_store_order_cart_info` VALUES (22, 24, 21, 19, '{\"addTime\":1573110327,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":0.2,\"combinationId\":0,\"costPrice\":0.2,\"trueStock\":1,\"truePrice\":0.2,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":1,\"price\":0.20,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"productId\":19,\"price\":0.20,\"unique\":\"b2f53a706c0646ce93e5df0b44350b35\",\"suk\":\"A1,白色\",\"id\":14,\"stock\":1}},\"uid\":10,\"productAttrUnique\":\"b2f53a706c0646ce93e5df0b44350b35\",\"bargainId\":0,\"id\":21}', '9f0a08deeec34114bbe70d6322b5bb13');
INSERT INTO `yx_store_order_cart_info` VALUES (23, 25, 23, 19, '{\"addTime\":1573118876,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":0.2,\"combinationId\":0,\"costPrice\":0.2,\"trueStock\":999,\"truePrice\":0.2,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":2,\"price\":0.20,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"productId\":19,\"price\":0.20,\"unique\":\"74d02ea6a57a46979a999457d8819421\",\"suk\":\"A4,白色\",\"id\":20,\"stock\":999}},\"uid\":7,\"productAttrUnique\":\"74d02ea6a57a46979a999457d8819421\",\"bargainId\":0,\"id\":23}', 'a6845f54a0d14bd0b6b26cd153e0f3a6');
INSERT INTO `yx_store_order_cart_info` VALUES (24, 26, 24, 19, '{\"addTime\":1573119028,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":0.2,\"combinationId\":0,\"costPrice\":0.2,\"trueStock\":999,\"truePrice\":0.2,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":3,\"price\":0.20,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"productId\":19,\"price\":0.20,\"unique\":\"2e4fdfe41da9408ebe5dc5f46b247c36\",\"suk\":\"A1,白色\",\"id\":18,\"stock\":999}},\"uid\":3,\"productAttrUnique\":\"2e4fdfe41da9408ebe5dc5f46b247c36\",\"bargainId\":0,\"id\":24}', 'b1c774c3d5d2498bb7c7cba0cb5dc711');
INSERT INTO `yx_store_order_cart_info` VALUES (25, 27, 25, 20, '{\"addTime\":1573119053,\"productId\":20,\"seckillId\":0,\"vipTruePrice\":0.5,\"combinationId\":0,\"costPrice\":0.5,\"trueStock\":999,\"truePrice\":0.5,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"addTime\":1573108370,\"otPrice\":1.00,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\">7<strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: center;\\\"><img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width:100%;\\\"><strong><br></strong></p><p style=\\\"text-align: center;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg\",\"isBest\":1,\"sales\":0,\"price\":0.50,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.50,\"storeName\":\"70gA4彩色打印复印\",\"id\":20,\"keyword\":\"彩色打印复印\",\"stock\":100,\"image\":\"https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg\",\"cost\":0.50,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":0.00,\"isNew\":1,\"sort\":0,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca05103ad634.jpg\",\"https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca05103ad634.jpg,https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"merId\":0,\"ficti\":0,\"storeInfo\":\"70gA4彩色打印复印\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg\",\"cost\":0.50,\"productId\":20,\"price\":0.50,\"unique\":\"75d1d355cdb44970beb3524911c7bae7\",\"suk\":\"A3,红色\",\"id\":11,\"stock\":999},\"browse\":0},\"uid\":7,\"productAttrUnique\":\"75d1d355cdb44970beb3524911c7bae7\",\"bargainId\":0,\"id\":25}', 'a39f347d838545dfa33cdf5136f89983');
INSERT INTO `yx_store_order_cart_info` VALUES (26, 28, 26, 19, '{\"addTime\":1573119078,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":0.2,\"combinationId\":0,\"costPrice\":0.2,\"trueStock\":9999,\"truePrice\":0.2,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":4,\"price\":0.20,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"productId\":19,\"price\":0.20,\"unique\":\"0938e298745940318f07af0887b657b8\",\"suk\":\"A1,蓝色\",\"id\":19,\"stock\":9999}},\"uid\":3,\"productAttrUnique\":\"0938e298745940318f07af0887b657b8\",\"bargainId\":0,\"id\":26}', '7128f9431bd04bcfa9bce462a6cf7368');
INSERT INTO `yx_store_order_cart_info` VALUES (27, 29, 27, 19, '{\"addTime\":1573119170,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":0.2,\"combinationId\":0,\"costPrice\":0.2,\"trueStock\":998,\"truePrice\":0.2,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":5,\"price\":0.20,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"productId\":19,\"price\":0.20,\"unique\":\"2e4fdfe41da9408ebe5dc5f46b247c36\",\"suk\":\"A1,白色\",\"id\":18,\"stock\":998}},\"uid\":7,\"productAttrUnique\":\"2e4fdfe41da9408ebe5dc5f46b247c36\",\"bargainId\":0,\"id\":27}', '9bab84881cf846c393f7a9b2ed68813b');
INSERT INTO `yx_store_order_cart_info` VALUES (28, 30, 29, 19, '{\"addTime\":1573119419,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":0.2,\"combinationId\":0,\"costPrice\":0.2,\"trueStock\":9998,\"truePrice\":0.2,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":6,\"price\":0.20,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"productId\":19,\"price\":0.20,\"unique\":\"0938e298745940318f07af0887b657b8\",\"suk\":\"A1,蓝色\",\"id\":19,\"stock\":9998}},\"uid\":8,\"productAttrUnique\":\"0938e298745940318f07af0887b657b8\",\"bargainId\":0,\"id\":29}', '28a09b3c854d41e7b16dd10886b083b1');
INSERT INTO `yx_store_order_cart_info` VALUES (29, 31, 30, 19, '{\"addTime\":1573120609,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":0.2,\"combinationId\":0,\"costPrice\":0.2,\"trueStock\":997,\"truePrice\":0.2,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":7,\"price\":0.20,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"productId\":19,\"price\":0.20,\"unique\":\"2e4fdfe41da9408ebe5dc5f46b247c36\",\"suk\":\"A1,白色\",\"id\":18,\"stock\":997}},\"uid\":11,\"productAttrUnique\":\"2e4fdfe41da9408ebe5dc5f46b247c36\",\"bargainId\":0,\"id\":30}', '5fbebeaf20e54dfab79ea58da33502ad');
INSERT INTO `yx_store_order_cart_info` VALUES (30, 32, 31, 19, '{\"addTime\":1573123442,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":0.2,\"combinationId\":0,\"costPrice\":0.2,\"trueStock\":996,\"truePrice\":0.2,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":8,\"price\":0.20,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"productId\":19,\"price\":0.20,\"unique\":\"2e4fdfe41da9408ebe5dc5f46b247c36\",\"suk\":\"A1,白色\",\"id\":18,\"stock\":996}},\"uid\":12,\"productAttrUnique\":\"2e4fdfe41da9408ebe5dc5f46b247c36\",\"bargainId\":0,\"id\":31}', 'afb19af2993c4eceb0a39056363e6886');
INSERT INTO `yx_store_order_cart_info` VALUES (31, 33, 32, 19, '{\"addTime\":1573123802,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":0.2,\"combinationId\":0,\"costPrice\":0.2,\"trueStock\":995,\"truePrice\":0.2,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":9,\"price\":0.20,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"productId\":19,\"price\":0.20,\"unique\":\"2e4fdfe41da9408ebe5dc5f46b247c36\",\"suk\":\"A1,白色\",\"id\":18,\"stock\":995}},\"uid\":12,\"productAttrUnique\":\"2e4fdfe41da9408ebe5dc5f46b247c36\",\"bargainId\":0,\"id\":32}', 'c55f2f7ef8e24e459cadd1a12dca6249');
INSERT INTO `yx_store_order_cart_info` VALUES (32, 34, 33, 19, '{\"addTime\":1573123872,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":0.2,\"combinationId\":0,\"costPrice\":0.2,\"trueStock\":994,\"truePrice\":0.2,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":10,\"price\":0.20,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"productId\":19,\"price\":0.20,\"unique\":\"2e4fdfe41da9408ebe5dc5f46b247c36\",\"suk\":\"A1,白色\",\"id\":18,\"stock\":994}},\"uid\":12,\"productAttrUnique\":\"2e4fdfe41da9408ebe5dc5f46b247c36\",\"bargainId\":0,\"id\":33}', '6086f21b510640b398df40d4fbd555de');
INSERT INTO `yx_store_order_cart_info` VALUES (33, 35, 34, 19, '{\"addTime\":1573368853,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":999,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":11,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":999}},\"uid\":12,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":34}', '7ff106b3a7494f0f917ed4eca6c77d35');
INSERT INTO `yx_store_order_cart_info` VALUES (34, 36, 36, 19, '{\"addTime\":1573380432,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":170.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":9999,\"truePrice\":170.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":12,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":170.00,\"unique\":\"d8c72945fdbe4035a95a393aff1eea46\",\"suk\":\"A4,蓝色\",\"id\":25,\"stock\":9999}},\"uid\":12,\"productAttrUnique\":\"d8c72945fdbe4035a95a393aff1eea46\",\"bargainId\":0,\"id\":36}', 'ff093e14f6454cdc8df13f8ff507a249');
INSERT INTO `yx_store_order_cart_info` VALUES (35, 37, 37, 19, '{\"addTime\":1573612783,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":998,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":13,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":998}},\"uid\":14,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":37}', '87313559f00648c5a43fc84a168b6a64');
INSERT INTO `yx_store_order_cart_info` VALUES (36, 38, 35, 19, '{\"addTime\":1573380121,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":997,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":14,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":997}},\"uid\":12,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":35}', '2a9f20b03d0a4f2588e932e3651e9072');
INSERT INTO `yx_store_order_cart_info` VALUES (37, 39, 42, 19, '{\"addTime\":1573888313,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":997,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":14,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":997}},\"uid\":14,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":42}', 'cf1ab595ceeb4d1281e56a65b1815241');
INSERT INTO `yx_store_order_cart_info` VALUES (40, 42, 43, 19, '{\"addTime\":1573979445,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":997,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":14,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":997}},\"uid\":14,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":43}', '6b4282ee66e9400e881cbb0a94d3a469');
INSERT INTO `yx_store_order_cart_info` VALUES (41, 43, 44, 19, '{\"addTime\":1573979624,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":996,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":15,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":996}},\"uid\":14,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":44}', '209dc336955b40818b5cc42345e0a6e5');
INSERT INTO `yx_store_order_cart_info` VALUES (42, 44, 45, 19, '{\"addTime\":1573980036,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":995,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":16,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":995}},\"uid\":14,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":45}', 'a21ddcde860c469bbfca289347a1ee17');
INSERT INTO `yx_store_order_cart_info` VALUES (43, 45, 46, 19, '{\"addTime\":1573980984,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":994,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":17,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":994}},\"uid\":14,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":46}', 'bef7346c0e6b439c9b7c9c9e55141848');
INSERT INTO `yx_store_order_cart_info` VALUES (44, 46, 47, 19, '{\"addTime\":1573981512,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":993,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":18,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":993}},\"uid\":14,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":47}', 'fe65a7d18c884816bf6e6118e816c27b');
INSERT INTO `yx_store_order_cart_info` VALUES (45, 47, 48, 19, '{\"addTime\":1573981625,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":992,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":19,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":992}},\"uid\":14,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":48}', '0cb3929d4e9041a2a15fe0b8b99b7582');
INSERT INTO `yx_store_order_cart_info` VALUES (46, 48, 49, 19, '{\"addTime\":1573982911,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":991,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":20,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":991}},\"uid\":14,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":49}', '6b82f8e86ad744a884266f1bae1bb607');
INSERT INTO `yx_store_order_cart_info` VALUES (47, 49, 50, 19, '{\"addTime\":1573983129,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":990,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":21,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":990}},\"uid\":14,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":50}', 'ae654633e331445caea3615a80081b32');
INSERT INTO `yx_store_order_cart_info` VALUES (48, 50, 51, 19, '{\"addTime\":1573984480,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":989,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":22,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":989}},\"uid\":14,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":51}', 'f849d4a0b54b46e192399b421c94b432');
INSERT INTO `yx_store_order_cart_info` VALUES (49, 51, 52, 19, '{\"addTime\":1573984575,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":988,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":23,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":988}},\"uid\":14,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":52}', '8cf1e3ad5bfc4cbdb14ebf26925e2772');
INSERT INTO `yx_store_order_cart_info` VALUES (50, 52, 53, 19, '{\"addTime\":1573984665,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":996,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":15,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":996}},\"uid\":14,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":53}', 'e1280295ae504aeaa06733a446a75258');
INSERT INTO `yx_store_order_cart_info` VALUES (51, 53, 54, 19, '{\"addTime\":1573985135,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":995,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":16,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":995}},\"uid\":14,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":54}', '225f6ec45fec4b7eaafff6eca34eee66');
INSERT INTO `yx_store_order_cart_info` VALUES (52, 54, 55, 19, '{\"addTime\":1573985245,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":995,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":16,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":995}},\"uid\":14,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":55}', 'f9ebd84148f94059bc4fadd399b00719');
INSERT INTO `yx_store_order_cart_info` VALUES (53, 55, 56, 19, '{\"addTime\":1573985381,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":995,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":16,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":995}},\"uid\":14,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":56}', 'bc94aeb032d641709f7f08af620ac7b3');
INSERT INTO `yx_store_order_cart_info` VALUES (54, 56, 57, 19, '{\"addTime\":1573985953,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":995,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":16,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":995}},\"uid\":14,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":57}', '5b165e0d27d54bc4847bbc309d471648');
INSERT INTO `yx_store_order_cart_info` VALUES (55, 57, 58, 19, '{\"addTime\":1573987260,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":996,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":15,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":996}},\"uid\":14,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":58}', '21af773114624b9fb0c45f9dcd7d7e2d');
INSERT INTO `yx_store_order_cart_info` VALUES (56, 58, 59, 19, '{\"addTime\":1573987803,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":996,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":15,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":996}},\"uid\":14,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":59}', '7aa6883c272a4b40ad53f9ed4022123e');
INSERT INTO `yx_store_order_cart_info` VALUES (57, 59, 60, 19, '{\"addTime\":1573988062,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":995,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":16,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":995}},\"uid\":14,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":60}', '54f016da7cda4bc4a2c8f60c740c02ad');
INSERT INTO `yx_store_order_cart_info` VALUES (58, 60, 61, 19, '{\"addTime\":1573989582,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":996,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":15,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":996}},\"uid\":14,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":61}', 'a59cdd8519504f589231e4346a678d0d');
INSERT INTO `yx_store_order_cart_info` VALUES (59, 61, 62, 19, '{\"addTime\":1573991786,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":995,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":16,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":995}},\"uid\":14,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":62}', '3410d21cc53e408b8656f6ca5363521a');
INSERT INTO `yx_store_order_cart_info` VALUES (60, 62, 63, 19, '{\"addTime\":1573991863,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":995,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":16,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":995}},\"uid\":14,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":63}', 'c1eff785b72e4d36b2bb400d49e69b3c');
INSERT INTO `yx_store_order_cart_info` VALUES (61, 63, 64, 19, '{\"addTime\":1573992167,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":994,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":17,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":994}},\"uid\":14,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":64}', 'd2661ad8457c43e68769974841dbd199');
INSERT INTO `yx_store_order_cart_info` VALUES (62, 64, 65, 19, '{\"addTime\":1573992682,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":995,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":16,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":995}},\"uid\":14,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":65}', '0b194cc7210c4aee8bc0b3900c6de3d5');
INSERT INTO `yx_store_order_cart_info` VALUES (64, 66, 66, 19, '{\"addTime\":1573995851,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":995,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":16,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":995}},\"uid\":14,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":66}', 'c172e14bca8b487b85004bd2e7e1a8b2');
INSERT INTO `yx_store_order_cart_info` VALUES (65, 67, 67, 19, '{\"addTime\":1573996185,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":995,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":16,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":995}},\"uid\":14,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":67}', '2af264b8a00849798bdb61071c4cdf23');
INSERT INTO `yx_store_order_cart_info` VALUES (66, 68, 68, 19, '{\"addTime\":1573996420,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":994,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":17,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":994}},\"uid\":14,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":68}', '3c5b98f947e3409bacad9d20a17af08c');
INSERT INTO `yx_store_order_cart_info` VALUES (73, 75, 69, 19, '{\"addTime\":1573996695,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":993,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":18,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":993}},\"uid\":14,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":69}', 'dd5fb60b6cce409c941faac9bfc29043');
INSERT INTO `yx_store_order_cart_info` VALUES (74, 76, 70, 19, '{\"addTime\":1573999215,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":993,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":18,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":993}},\"uid\":14,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":70}', '82770b6bd27046d7bd1757b6cb5b800f');
INSERT INTO `yx_store_order_cart_info` VALUES (75, 77, 71, 19, '{\"addTime\":1573999374,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":993,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":18,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":993}},\"uid\":14,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":71}', '78a8a18f789d4d3587e087d85c0a3324');
INSERT INTO `yx_store_order_cart_info` VALUES (76, 78, 72, 19, '{\"addTime\":1573999538,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":993,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":18,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":993}},\"uid\":14,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":72}', '321a280b69bf42219044e0677c520477');
INSERT INTO `yx_store_order_cart_info` VALUES (77, 79, 73, 19, '{\"addTime\":1573999673,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":993,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":18,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":993}},\"uid\":14,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":73}', '61fb913b3fc44cb5861f88a2dd7702d8');
INSERT INTO `yx_store_order_cart_info` VALUES (78, 80, 74, 19, '{\"addTime\":1574000128,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":993,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":18,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":993}},\"uid\":14,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":74}', '2eb073557f3e439ba95d0e6a3eb6253e');
INSERT INTO `yx_store_order_cart_info` VALUES (79, 81, 75, 19, '{\"addTime\":1574000409,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":993,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":18,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":993}},\"uid\":14,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":75}', '979fbfa642474275829595120bacddd8');
INSERT INTO `yx_store_order_cart_info` VALUES (80, 82, 76, 19, '{\"addTime\":1574000926,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":993,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":18,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":993}},\"uid\":14,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":76}', '49415f7331324948a53193088f6e2b80');
INSERT INTO `yx_store_order_cart_info` VALUES (81, 83, 77, 19, '{\"addTime\":1574001199,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":993,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":18,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":993}},\"uid\":14,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":77}', '66dbb6eac2dd43a99ff3273d2e29db80');
INSERT INTO `yx_store_order_cart_info` VALUES (82, 84, 78, 19, '{\"addTime\":1574001302,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":993,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":18,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":993}},\"uid\":14,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":78}', '6e76027cecde4891a40c00e3bc84922f');
INSERT INTO `yx_store_order_cart_info` VALUES (83, 85, 79, 19, '{\"addTime\":1574039489,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":993,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":18,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":993}},\"uid\":14,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":79}', '58b701815010440aa8d892fb0a8e22ff');
INSERT INTO `yx_store_order_cart_info` VALUES (84, 86, 80, 19, '{\"addTime\":1574040157,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":993,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":18,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":993}},\"uid\":14,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":80}', '918f2044600a43efac65bc49a528af75');
INSERT INTO `yx_store_order_cart_info` VALUES (85, 87, 81, 19, '{\"addTime\":1574040943,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":993,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":18,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":993}},\"uid\":14,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":81}', '150254729b63429b8273a02d6cfc6b5b');
INSERT INTO `yx_store_order_cart_info` VALUES (86, 88, 82, 19, '{\"addTime\":1574043311,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":993,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":18,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":993}},\"uid\":14,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":82}', '451e4661a80f4de5b123a50cae269a3c');
INSERT INTO `yx_store_order_cart_info` VALUES (87, 89, 83, 19, '{\"addTime\":1574216552,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":0.0,\"combinationId\":2,\"costPrice\":0.2,\"trueStock\":99,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":18,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1},\"uid\":12,\"productAttrUnique\":\"\",\"bargainId\":0,\"id\":83}', 'baf91a0e8b16413fbf81d90df38711d3');
INSERT INTO `yx_store_order_cart_info` VALUES (88, 90, 84, 19, '{\"addTime\":1574243149,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":0.0,\"combinationId\":2,\"costPrice\":0.2,\"trueStock\":99,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":18,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1},\"uid\":12,\"productAttrUnique\":\"\",\"bargainId\":0,\"id\":84}', '290daae35e7b428a8361648da10bc5aa');
INSERT INTO `yx_store_order_cart_info` VALUES (89, 91, 85, 19, '{\"addTime\":1574244169,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":0.0,\"combinationId\":2,\"costPrice\":0.2,\"trueStock\":99,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":18,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1},\"uid\":12,\"productAttrUnique\":\"\",\"bargainId\":0,\"id\":85}', '8c7b019a2e5e48cba12389c84e143dcc');
INSERT INTO `yx_store_order_cart_info` VALUES (90, 92, 86, 19, '{\"addTime\":1574320376,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":0.0,\"combinationId\":2,\"costPrice\":0.0,\"trueStock\":96,\"truePrice\":1.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"userCollect\":false,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"sales\":21,\"price\":1.00,\"userLike\":false,\"storeName\":\"70gA4黑白打印复印\",\"id\":2,\"stock\":96,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0,\"sliderImageArr\":[],\"isShow\":1,\"postage\":0.00,\"isDel\":0},\"uid\":12,\"productAttrUnique\":\"\",\"bargainId\":0,\"id\":86}', '15bd4c8455204bfe8329d9520c40b7b1');
INSERT INTO `yx_store_order_cart_info` VALUES (91, 93, 87, 19, '{\"addTime\":1574329392,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":0.0,\"combinationId\":2,\"costPrice\":0.0,\"trueStock\":95,\"truePrice\":1.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"userCollect\":false,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"sales\":22,\"price\":1.00,\"userLike\":false,\"storeName\":\"70gA4黑白打印复印\",\"id\":2,\"stock\":95,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0,\"sliderImageArr\":[],\"isShow\":1,\"postage\":0.00,\"isDel\":0},\"uid\":11,\"productAttrUnique\":\"\",\"bargainId\":0,\"id\":87}', '7233bf11cc3a460d83ce80a3e82d35be');
INSERT INTO `yx_store_order_cart_info` VALUES (92, 96, 88, 19, '{\"addTime\":1574424869,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":0.0,\"combinationId\":2,\"costPrice\":0.0,\"trueStock\":94,\"truePrice\":1.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"userCollect\":false,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"sales\":23,\"price\":1.00,\"userLike\":false,\"storeName\":\"70gA4黑白打印复印\",\"id\":2,\"stock\":94,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0,\"sliderImageArr\":[],\"isShow\":1,\"postage\":0.00,\"isDel\":0},\"uid\":12,\"productAttrUnique\":\"\",\"bargainId\":0,\"id\":88}', 'af8ca64055ac4c739cfded03c2c0b784');
INSERT INTO `yx_store_order_cart_info` VALUES (93, 97, 89, 19, '{\"addTime\":1575095144,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":993,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":18,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":993}},\"uid\":12,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":89}', '247853fbd42241269dc6812edc71fba1');
INSERT INTO `yx_store_order_cart_info` VALUES (94, 98, 90, 19, '{\"addTime\":1575095354,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":993,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":17,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":993}},\"uid\":12,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":90}', 'e76d7e013f46469492b62998752ad052');
INSERT INTO `yx_store_order_cart_info` VALUES (95, 99, 95, 19, '{\"addTime\":1575771477,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":993,\"truePrice\":118.8,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":17,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":993}},\"uid\":12,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":95}', '16e9212266b949cdac21e7e45881a84f');
INSERT INTO `yx_store_order_cart_info` VALUES (96, 100, 96, 19, '{\"addTime\":1576234305,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":992,\"truePrice\":118.8,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":18,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":992}},\"uid\":12,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":96}', 'd186a2b7f7f146389c33223f3ce180cf');
INSERT INTO `yx_store_order_cart_info` VALUES (97, 101, 99, 20, '{\"addTime\":1576561530,\"productId\":20,\"seckillId\":2,\"vipTruePrice\":0.0,\"combinationId\":0,\"costPrice\":0.5,\"trueStock\":100,\"truePrice\":1.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"userCollect\":false,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg\",\"sales\":1,\"price\":1.00,\"userLike\":false,\"storeName\":\"70gA4彩色打印复印\",\"id\":2,\"stock\":100,\"image\":\"https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg\",\"cost\":0.50,\"sliderImageArr\":[],\"isShow\":1,\"postage\":0.00,\"isDel\":0},\"uid\":12,\"productAttrUnique\":\"\",\"bargainId\":0,\"id\":99}', 'e132b2634fc64a8eb5fe4022ca04dcb7');
INSERT INTO `yx_store_order_cart_info` VALUES (98, 102, 103, 19, '{\"addTime\":1577085055,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":0.0,\"combinationId\":0,\"costPrice\":50.0,\"trueStock\":100,\"truePrice\":1.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":30.00,\"userCollect\":false,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"sales\":10,\"price\":1.00,\"userLike\":false,\"storeName\":\"砍价标题\",\"id\":1,\"stock\":100,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":50.00,\"sliderImageArr\":[],\"isShow\":1,\"postage\":0.00,\"isDel\":0},\"uid\":12,\"productAttrUnique\":\"\",\"bargainId\":1,\"id\":103}', '7b3edd5545764840bb7aa0eba8374286');
INSERT INTO `yx_store_order_cart_info` VALUES (99, 103, 97, 19, '{\"addTime\":1576290026,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":991,\"truePrice\":118.8,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":19,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":991}},\"uid\":12,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":97}', '1b650c0c743d42edb5f02dc545ea381d');
INSERT INTO `yx_store_order_cart_info` VALUES (100, 104, 104, 20, '{\"addTime\":1577442127,\"productId\":20,\"seckillId\":0,\"vipTruePrice\":0.5,\"combinationId\":0,\"costPrice\":0.5,\"trueStock\":999,\"truePrice\":0.495,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"addTime\":1573108370,\"otPrice\":1.00,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\">7<strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: center;\\\"><img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width:100%;\\\"><strong><br></strong></p><p style=\\\"text-align: center;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg\",\"isBest\":1,\"sales\":1,\"price\":0.50,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.50,\"storeName\":\"70gA4彩色打印复印\",\"id\":20,\"keyword\":\"彩色打印复印\",\"stock\":100,\"image\":\"https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg\",\"cost\":0.50,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":0.00,\"isNew\":1,\"sort\":0,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca05103ad634.jpg\",\"https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca05103ad634.jpg,https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"merId\":0,\"ficti\":0,\"storeInfo\":\"70gA4彩色打印复印\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg\",\"cost\":0.50,\"productId\":20,\"price\":0.50,\"unique\":\"ba5d202555c44d378e6644ca1296ee01\",\"suk\":\"A3,白色\",\"id\":10,\"stock\":999},\"browse\":0},\"uid\":12,\"productAttrUnique\":\"ba5d202555c44d378e6644ca1296ee01\",\"bargainId\":0,\"id\":104}', 'f2bccd6cf98f4afb9a7e6465f6ca224c');
INSERT INTO `yx_store_order_cart_info` VALUES (101, 105, 105, 19, '{\"addTime\":1577611673,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":990,\"truePrice\":118.8,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":20,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":990}},\"uid\":12,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":105}', 'c9735ac229bf41c6b07d43e7f84cc3f5');
INSERT INTO `yx_store_order_cart_info` VALUES (102, 106, 106, 19, '{\"addTime\":1577678968,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":989,\"truePrice\":118.8,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":21,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":989}},\"uid\":12,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":106}', '6c4d0a6e129641968c09bab2f3ed54b5');
INSERT INTO `yx_store_order_cart_info` VALUES (103, 107, 107, 19, '{\"addTime\":1577890650,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":988,\"truePrice\":118.8,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":22,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":988}},\"uid\":12,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":107}', 'b83df15cc9954742b72c3dc86b1f265c');
INSERT INTO `yx_store_order_cart_info` VALUES (104, 108, 108, 19, '{\"addTime\":1578386329,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":988,\"truePrice\":118.8,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":22,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":988}},\"uid\":12,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":108}', 'd1e842796cc146d78140b3b2e907b26e');
INSERT INTO `yx_store_order_cart_info` VALUES (106, 110, 109, 19, '{\"addTime\":1578387002,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":987,\"truePrice\":118.8,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":23,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":987}},\"uid\":12,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":109}', '2f8409dbb81448d4b5ee3c2482b1e9e3');
INSERT INTO `yx_store_order_cart_info` VALUES (107, 111, 110, 19, '{\"addTime\":1578839640,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":120.0,\"combinationId\":0,\"costPrice\":100.0,\"trueStock\":986,\"truePrice\":118.8,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":24,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":100.00,\"productId\":19,\"price\":120.00,\"unique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"suk\":\"A1,白色\",\"id\":22,\"stock\":986}},\"uid\":12,\"productAttrUnique\":\"00e86525a7af4dcf9e653ae0a2062369\",\"bargainId\":0,\"id\":110}', '8e62a2af9bd345ec8687dc4ee3d2c6ff');
INSERT INTO `yx_store_order_cart_info` VALUES (108, 112, 114, 20, '{\"addTime\":1581578722,\"productId\":20,\"seckillId\":0,\"vipTruePrice\":0.5,\"combinationId\":0,\"costPrice\":0.5,\"trueStock\":999,\"truePrice\":0.5,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"addTime\":1573108370,\"otPrice\":1.00,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\">7<strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: center;\\\"><img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width:100%;\\\"><strong><br></strong></p><p style=\\\"text-align: center;\\\"><br></p>\",\"merUse\":0,\"isPostage\":0,\"image_base\":\"https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg\",\"isBest\":1,\"sales\":2,\"price\":0.50,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.50,\"storeName\":\"70gA4彩色打印复印\",\"id\":20,\"keyword\":\"彩色打印复印\",\"stock\":3996,\"image\":\"https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg\",\"cost\":0.50,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":0.00,\"isNew\":1,\"sort\":0,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca05103ad634.jpg\",\"https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca05103ad634.jpg,https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":5.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"merId\":0,\"ficti\":0,\"storeInfo\":\"70gA4彩色打印复印\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg\",\"cost\":0.50,\"productId\":20,\"price\":0.50,\"unique\":\"2e51e5d2d86a44748f81cdd5bf7a51c0\",\"suk\":\"A3,白色\",\"id\":26,\"stock\":999},\"browse\":0},\"uid\":11,\"productAttrUnique\":\"2e51e5d2d86a44748f81cdd5bf7a51c0\",\"bargainId\":0,\"id\":114}', 'dd01d4bebd5d4d668f58446318aba1f6');
INSERT INTO `yx_store_order_cart_info` VALUES (109, 113, 115, 19, '{\"addTime\":1582080099,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":0.0,\"combinationId\":2,\"costPrice\":0.0,\"trueStock\":93,\"truePrice\":1.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"userCollect\":false,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"sales\":24,\"price\":1.00,\"userLike\":false,\"storeName\":\"70gA4黑白打印复印\",\"id\":2,\"stock\":93,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0,\"sliderImageArr\":[],\"isShow\":1,\"postage\":0.00,\"isDel\":0},\"uid\":11,\"productAttrUnique\":\"\",\"bargainId\":0,\"id\":115}', 'f748337e16da44f3a1540c1d798cc803');
INSERT INTO `yx_store_order_cart_info` VALUES (110, 114, 116, 19, '{\"addTime\":1582080945,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":0.0,\"combinationId\":2,\"costPrice\":0.0,\"trueStock\":92,\"truePrice\":1.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"userCollect\":false,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"sales\":25,\"price\":1.00,\"userLike\":false,\"storeName\":\"70gA4黑白打印复印\",\"id\":2,\"stock\":92,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0,\"sliderImageArr\":[],\"isShow\":1,\"postage\":0.00,\"isDel\":0},\"uid\":11,\"productAttrUnique\":\"\",\"bargainId\":0,\"id\":116}', '330f3ce386ec45d1b2f181de95d3659d');
INSERT INTO `yx_store_order_cart_info` VALUES (111, 115, 117, 19, '{\"addTime\":1582081728,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":0.0,\"combinationId\":2,\"costPrice\":0.0,\"trueStock\":91,\"truePrice\":1.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"userCollect\":false,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"sales\":26,\"price\":1.00,\"userLike\":false,\"storeName\":\"70gA4黑白打印复印\",\"id\":2,\"stock\":91,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0,\"sliderImageArr\":[],\"isShow\":1,\"postage\":0.00,\"isDel\":0},\"uid\":11,\"productAttrUnique\":\"\",\"bargainId\":0,\"id\":117}', '0a45dc1793b04a0bbbb686a5b9fd5aa1');
INSERT INTO `yx_store_order_cart_info` VALUES (112, 116, 118, 19, '{\"addTime\":1582081799,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":0.0,\"combinationId\":2,\"costPrice\":0.0,\"trueStock\":90,\"truePrice\":1.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"userCollect\":false,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"sales\":27,\"price\":1.00,\"userLike\":false,\"storeName\":\"70gA4黑白打印复印\",\"id\":2,\"stock\":90,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0,\"sliderImageArr\":[],\"isShow\":1,\"postage\":0.00,\"isDel\":0},\"uid\":11,\"productAttrUnique\":\"\",\"bargainId\":0,\"id\":118}', '9101f0eea8944537937dfed5cee81403');
INSERT INTO `yx_store_order_cart_info` VALUES (113, 117, 113, 19, '{\"addTime\":1581578302,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":0.0,\"combinationId\":0,\"costPrice\":0.2,\"trueStock\":99,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":25,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":99,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1},\"uid\":11,\"productAttrUnique\":\"\",\"bargainId\":0,\"id\":113}', '94d85e56c96c4898889ae1c1d9235155');
INSERT INTO `yx_store_order_cart_info` VALUES (114, 118, 119, 20, '{\"addTime\":1582809306,\"productId\":20,\"seckillId\":0,\"vipTruePrice\":0.5,\"combinationId\":0,\"costPrice\":0.5,\"trueStock\":998,\"truePrice\":0.5,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"addTime\":1573108370,\"otPrice\":1.00,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\">7<strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: center;\\\"><img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width:100%;\\\"><strong><br></strong></p><p style=\\\"text-align: center;\\\"><br></p>\",\"merUse\":0,\"isPostage\":0,\"image_base\":\"https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg\",\"isBest\":1,\"sales\":3,\"price\":0.50,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.50,\"storeName\":\"70gA4彩色打印复印\",\"id\":20,\"keyword\":\"彩色打印复印\",\"stock\":3995,\"image\":\"https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg\",\"cost\":0.50,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":0.00,\"isNew\":1,\"sort\":0,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca05103ad634.jpg\",\"https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca05103ad634.jpg,https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":5.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"merId\":0,\"ficti\":0,\"storeInfo\":\"70gA4彩色打印复印\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg\",\"cost\":0.50,\"productId\":20,\"price\":0.50,\"unique\":\"2e51e5d2d86a44748f81cdd5bf7a51c0\",\"suk\":\"A3,白色\",\"id\":26,\"stock\":998},\"browse\":0},\"uid\":11,\"productAttrUnique\":\"2e51e5d2d86a44748f81cdd5bf7a51c0\",\"bargainId\":0,\"id\":119}', '61c3e66680a94c6488b160ecac3eb0cc');
INSERT INTO `yx_store_order_cart_info` VALUES (115, 119, 120, 19, '{\"addTime\":1582809511,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":0.0,\"combinationId\":0,\"costPrice\":0.2,\"trueStock\":98,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":26,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":98,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1},\"uid\":11,\"productAttrUnique\":\"\",\"bargainId\":0,\"id\":120}', '71f21cb4c9f84a6f85381910f6f3147f');
INSERT INTO `yx_store_order_cart_info` VALUES (116, 120, 121, 19, '{\"addTime\":1582810583,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":0.0,\"combinationId\":0,\"costPrice\":0.2,\"trueStock\":97,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":27,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":97,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1},\"uid\":11,\"productAttrUnique\":\"\",\"bargainId\":0,\"id\":121}', 'a63efc786afa4491aadd40590a28e8d1');
INSERT INTO `yx_store_order_cart_info` VALUES (117, 121, 122, 20, '{\"addTime\":1582810979,\"productId\":20,\"seckillId\":0,\"vipTruePrice\":0.5,\"combinationId\":0,\"costPrice\":0.5,\"trueStock\":997,\"truePrice\":0.5,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"addTime\":1573108370,\"otPrice\":1.00,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\">7<strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: center;\\\"><img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width:100%;\\\"><strong><br></strong></p><p style=\\\"text-align: center;\\\"><br></p>\",\"merUse\":0,\"isPostage\":0,\"image_base\":\"https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg\",\"isBest\":1,\"sales\":4,\"price\":0.50,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.50,\"storeName\":\"70gA4彩色打印复印\",\"id\":20,\"keyword\":\"彩色打印复印\",\"stock\":3995,\"image\":\"https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg\",\"cost\":0.50,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":0.00,\"isNew\":1,\"sort\":0,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca05103ad634.jpg\",\"https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca05103ad634.jpg,https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":5.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"merId\":0,\"ficti\":0,\"storeInfo\":\"70gA4彩色打印复印\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg\",\"cost\":0.50,\"productId\":20,\"price\":0.50,\"unique\":\"2e51e5d2d86a44748f81cdd5bf7a51c0\",\"suk\":\"A3,白色\",\"id\":26,\"stock\":997},\"browse\":0},\"uid\":11,\"productAttrUnique\":\"2e51e5d2d86a44748f81cdd5bf7a51c0\",\"bargainId\":0,\"id\":122}', 'c88ca237ae124f638c244c1b6d73f31b');
INSERT INTO `yx_store_order_cart_info` VALUES (118, 122, 123, 19, '{\"addTime\":1582885462,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":0.0,\"combinationId\":0,\"costPrice\":0.2,\"trueStock\":96,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":2,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":28,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":96,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1},\"uid\":11,\"productAttrUnique\":\"\",\"bargainId\":0,\"id\":123}', 'a843a093b33a4d5d855f7389362739ca');
INSERT INTO `yx_store_order_cart_info` VALUES (119, 123, 125, 19, '{\"addTime\":1582970873,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":0.0,\"combinationId\":0,\"costPrice\":0.2,\"trueStock\":94,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":2,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":30,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":94,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1},\"uid\":11,\"productAttrUnique\":\"\",\"bargainId\":0,\"id\":125}', '275528d68f5d4962a6644ac1eda81e33');
INSERT INTO `yx_store_order_cart_info` VALUES (120, 124, 126, 19, '{\"addTime\":1582971001,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":0.0,\"combinationId\":0,\"costPrice\":0.2,\"trueStock\":92,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":32,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":92,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1},\"uid\":11,\"productAttrUnique\":\"\",\"bargainId\":0,\"id\":126}', 'c5e3fc0dc3ef42bcbfdab81fc43d47c8');
INSERT INTO `yx_store_order_cart_info` VALUES (121, 125, 127, 19, '{\"addTime\":1582971278,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":0.0,\"combinationId\":0,\"costPrice\":0.2,\"trueStock\":91,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":33,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":91,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1},\"uid\":11,\"productAttrUnique\":\"\",\"bargainId\":0,\"id\":127}', 'bd6c40a61f2d4a749cf847af4e3cb902');
INSERT INTO `yx_store_order_cart_info` VALUES (122, 126, 128, 19, '{\"addTime\":1582990627,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":0.0,\"combinationId\":0,\"costPrice\":0.2,\"trueStock\":90,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":34,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":90,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1},\"uid\":11,\"productAttrUnique\":\"\",\"bargainId\":0,\"id\":128}', 'a99f7cb06434447fac29ff68e218baf8');
INSERT INTO `yx_store_order_cart_info` VALUES (123, 127, 129, 19, '{\"addTime\":1583330733,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":0.0,\"combinationId\":0,\"costPrice\":0.2,\"trueStock\":89,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":35,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":89,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1},\"uid\":11,\"productAttrUnique\":\"\",\"bargainId\":0,\"id\":129}', 'f9a4c65947b340ea878e2356a05ba008');
INSERT INTO `yx_store_order_cart_info` VALUES (124, 128, 130, 19, '{\"addTime\":1583393646,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":0.0,\"combinationId\":0,\"costPrice\":0.2,\"trueStock\":88,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":36,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":88,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1},\"uid\":11,\"productAttrUnique\":\"\",\"bargainId\":0,\"id\":130}', '8cb9f8542e0f4dbcaa0ff3e3e78fe149');
INSERT INTO `yx_store_order_cart_info` VALUES (125, 129, 131, 19, '{\"addTime\":1583394583,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":0.0,\"combinationId\":0,\"costPrice\":0.2,\"trueStock\":87,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":37,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":87,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1},\"uid\":11,\"productAttrUnique\":\"\",\"bargainId\":0,\"id\":131}', 'ae2383d74a9c4d7bb1f1750c9579997e');
INSERT INTO `yx_store_order_cart_info` VALUES (126, 130, 132, 19, '{\"addTime\":1583399181,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":0.0,\"combinationId\":0,\"costPrice\":0.2,\"trueStock\":86,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":38,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":86,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1},\"uid\":11,\"productAttrUnique\":\"\",\"bargainId\":0,\"id\":132}', '571c60a6e57e42be98436095da6c9430');
INSERT INTO `yx_store_order_cart_info` VALUES (127, 131, 133, 19, '{\"addTime\":1583403251,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":0.0,\"combinationId\":0,\"costPrice\":0.2,\"trueStock\":85,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":39,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":85,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1},\"uid\":11,\"productAttrUnique\":\"\",\"bargainId\":0,\"id\":133}', 'dda5448962124bad9d8c2e370ccebf36');
INSERT INTO `yx_store_order_cart_info` VALUES (128, 132, 134, 19, '{\"addTime\":1583410813,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":0.0,\"combinationId\":0,\"costPrice\":0.2,\"trueStock\":84,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":40,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":84,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1},\"uid\":11,\"productAttrUnique\":\"\",\"bargainId\":0,\"id\":134}', '1fb7e2193c94489f8e6427f10ffcb621');
INSERT INTO `yx_store_order_cart_info` VALUES (129, 133, 135, 19, '{\"addTime\":1583416029,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":0.0,\"combinationId\":0,\"costPrice\":0.2,\"trueStock\":83,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":41,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":83,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1},\"uid\":11,\"productAttrUnique\":\"\",\"bargainId\":0,\"id\":135}', '4fafb699bb5946ac8ed4bf6f4ba2ab09');
INSERT INTO `yx_store_order_cart_info` VALUES (130, 134, 137, 19, '{\"addTime\":1583479387,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":0.0,\"combinationId\":0,\"costPrice\":0.2,\"trueStock\":82,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":42,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":82,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1},\"uid\":11,\"productAttrUnique\":\"\",\"bargainId\":0,\"id\":137}', '8d35da80e4be467394687dc2994aeff3');
INSERT INTO `yx_store_order_cart_info` VALUES (131, 135, 139, 20, '{\"addTime\":1583486504,\"productId\":20,\"seckillId\":0,\"vipTruePrice\":0.5,\"combinationId\":0,\"costPrice\":0.5,\"trueStock\":997,\"truePrice\":0.5,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"addTime\":1573108370,\"otPrice\":1.00,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\">7<strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: center;\\\"><img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width:100%;\\\"><strong><br></strong></p><p style=\\\"text-align: center;\\\"><br></p>\",\"merUse\":0,\"isPostage\":0,\"image_base\":\"https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg\",\"isBest\":1,\"sales\":4,\"price\":0.50,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.50,\"storeName\":\"70gA4彩色打印复印\",\"id\":20,\"keyword\":\"彩色打印复印\",\"stock\":3994,\"image\":\"https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg\",\"cost\":0.50,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":0.00,\"isNew\":1,\"sort\":0,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca05103ad634.jpg\",\"http://localhost:8000/file/pic/05ea40b831858a8cf423aa709840507c-20200228083801500.png\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca05103ad634.jpg,http://localhost:8000/file/pic/05ea40b831858a8cf423aa709840507c-20200228083801500.png\",\"barCode\":\"\",\"isShow\":1,\"postage\":5.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"merId\":0,\"ficti\":0,\"storeInfo\":\"70gA4彩色打印复印\",\"isDel\":0,\"isHot\":1,\"attrInfo\":{\"image\":\"https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg\",\"cost\":0.50,\"productId\":20,\"price\":0.50,\"unique\":\"2e51e5d2d86a44748f81cdd5bf7a51c0\",\"suk\":\"A3,白色\",\"id\":26,\"stock\":997},\"browse\":0},\"uid\":11,\"productAttrUnique\":\"2e51e5d2d86a44748f81cdd5bf7a51c0\",\"bargainId\":0,\"id\":139}', '8290e7cef14b4c669734747e7afcaa70');
INSERT INTO `yx_store_order_cart_info` VALUES (132, 135, 138, 19, '{\"addTime\":1583486497,\"productId\":19,\"seckillId\":0,\"vipTruePrice\":0.0,\"combinationId\":0,\"costPrice\":0.2,\"trueStock\":81,\"truePrice\":120.0,\"type\":\"product\",\"cartNum\":1,\"productInfo\":{\"otPrice\":0.50,\"userCollect\":false,\"description\":\"<p style=\\\"text-align: center;\\\"><strong>文件请传QQ2412733099，</strong></p><p style=\\\"text-align: center;\\\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\\\"text-align: center;\\\"><strong>手机/微信：13733990583</strong></p><p style=\\\"text-align: center;\\\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\\\"text-align: center;\\\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\\\"text-align: center;\\\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\\\"text-align: center;\\\"><strong>更大量请联系店主报价，</strong></p><p style=\\\"text-align: center;\\\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\\\"text-align: center;\\\"><strong>付款时直接抵扣</strong></p><p style=\\\"text-align: center;\\\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\\\"text-align: left;\\\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\\\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\\\" style=\\\"max-width: 100%;\\\"><br></p>\",\"merUse\":0,\"isPostage\":1,\"image_base\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"isBest\":1,\"sales\":43,\"price\":120.00,\"isBargain\":0,\"userLike\":false,\"vipPrice\":0.20,\"storeName\":\"70gA4黑白打印复印\",\"id\":19,\"keyword\":\"打印复印\",\"stock\":81,\"image\":\"https://image.dayouqiantu.cn/5ca011a1cd487.jpg\",\"cost\":0.20,\"isGood\":1,\"unitName\":\"张\",\"isBenefit\":1,\"giveIntegral\":1.00,\"isNew\":1,\"sort\":1,\"sliderImageArr\":[\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg\",\"https://image.dayouqiantu.cn/5ca01c7676042.jpg\"],\"sliderImage\":\"https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg\",\"barCode\":\"\",\"isShow\":1,\"postage\":0.00,\"codePath\":\"\",\"soureLink\":\"\",\"cateId\":\"24\",\"isSeckill\":0,\"storeInfo\":\"打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务\",\"isDel\":0,\"isHot\":1},\"uid\":11,\"productAttrUnique\":\"\",\"bargainId\":0,\"id\":138}', 'e54a38cd88fc499ea9cd32d4065dbf2f');

-- ----------------------------
-- Table structure for yx_store_order_status
-- ----------------------------
DROP TABLE IF EXISTS `yx_store_order_status`;
CREATE TABLE `yx_store_order_status`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `oid` int(10) UNSIGNED NOT NULL COMMENT '订单id',
  `change_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作类型',
  `change_message` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作备注',
  `change_time` int(10) UNSIGNED NOT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `oid`(`oid`) USING BTREE,
  INDEX `change_type`(`change_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 240 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单操作记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yx_store_order_status
-- ----------------------------
INSERT INTO `yx_store_order_status` VALUES (31, 24, 'cache_key_create_order', '订单生成', 1573110358);
INSERT INTO `yx_store_order_status` VALUES (32, 24, 'pay_success', '用户付款成功', 1573110365);
INSERT INTO `yx_store_order_status` VALUES (33, 24, 'delivery_goods', '已发货 快递公司：顺丰快递 快递单号：9999999', 1573110448);
INSERT INTO `yx_store_order_status` VALUES (34, 24, 'user_take_delivery', '用户已收货', 1573110461);
INSERT INTO `yx_store_order_status` VALUES (35, 24, 'check_order_over', '用户评价', 1573110475);
INSERT INTO `yx_store_order_status` VALUES (36, 25, 'cache_key_create_order', '订单生成', 1573118898);
INSERT INTO `yx_store_order_status` VALUES (37, 26, 'cache_key_create_order', '订单生成', 1573119034);
INSERT INTO `yx_store_order_status` VALUES (38, 26, 'pay_success', '用户付款成功', 1573119034);
INSERT INTO `yx_store_order_status` VALUES (39, 27, 'cache_key_create_order', '订单生成', 1573119055);
INSERT INTO `yx_store_order_status` VALUES (40, 28, 'cache_key_create_order', '订单生成', 1573119081);
INSERT INTO `yx_store_order_status` VALUES (41, 29, 'cache_key_create_order', '订单生成', 1573119177);
INSERT INTO `yx_store_order_status` VALUES (42, 30, 'cache_key_create_order', '订单生成', 1573119442);
INSERT INTO `yx_store_order_status` VALUES (43, 31, 'cache_key_create_order', '订单生成', 1573120633);
INSERT INTO `yx_store_order_status` VALUES (44, 31, 'pay_success', '用户付款成功', 1573120639);
INSERT INTO `yx_store_order_status` VALUES (45, 32, 'cache_key_create_order', '订单生成', 1573123599);
INSERT INTO `yx_store_order_status` VALUES (46, 33, 'cache_key_create_order', '订单生成', 1573123808);
INSERT INTO `yx_store_order_status` VALUES (47, 34, 'cache_key_create_order', '订单生成', 1573123875);
INSERT INTO `yx_store_order_status` VALUES (48, 34, 'pay_success', '用户付款成功', 1573124900);
INSERT INTO `yx_store_order_status` VALUES (49, 33, 'pay_success', '用户付款成功', 1573124914);
INSERT INTO `yx_store_order_status` VALUES (50, 35, 'cache_key_create_order', '订单生成', 1573380051);
INSERT INTO `yx_store_order_status` VALUES (51, 35, 'pay_success', '用户付款成功', 1573380051);
INSERT INTO `yx_store_order_status` VALUES (52, 36, 'cache_key_create_order', '订单生成', 1573381947);
INSERT INTO `yx_store_order_status` VALUES (53, 36, 'pay_success', '用户付款成功', 1573381947);
INSERT INTO `yx_store_order_status` VALUES (54, 37, 'cache_key_create_order', '订单生成', 1573612822);
INSERT INTO `yx_store_order_status` VALUES (55, 37, 'pay_success', '用户付款成功', 1573612822);
INSERT INTO `yx_store_order_status` VALUES (56, 37, 'delivery_goods', '已发货 快递公司：顺丰快递 快递单号：1111111', 1573613127);
INSERT INTO `yx_store_order_status` VALUES (57, 37, 'user_take_delivery', '用户已收货', 1573613426);
INSERT INTO `yx_store_order_status` VALUES (58, 38, 'cache_key_create_order', '订单生成', 1573887265);
INSERT INTO `yx_store_order_status` VALUES (59, 39, 'cache_key_create_order', '订单生成', 1573888321);
INSERT INTO `yx_store_order_status` VALUES (62, 42, 'cache_key_create_order', '订单生成', 1573979565);
INSERT INTO `yx_store_order_status` VALUES (63, 42, 'pay_success', '用户付款成功', 1573979565);
INSERT INTO `yx_store_order_status` VALUES (64, 43, 'cache_key_create_order', '订单生成', 1573979627);
INSERT INTO `yx_store_order_status` VALUES (65, 44, 'cache_key_create_order', '订单生成', 1573980050);
INSERT INTO `yx_store_order_status` VALUES (66, 45, 'cache_key_create_order', '订单生成', 1573980992);
INSERT INTO `yx_store_order_status` VALUES (67, 46, 'cache_key_create_order', '订单生成', 1573981520);
INSERT INTO `yx_store_order_status` VALUES (68, 47, 'cache_key_create_order', '订单生成', 1573981633);
INSERT INTO `yx_store_order_status` VALUES (69, 48, 'cache_key_create_order', '订单生成', 1573982919);
INSERT INTO `yx_store_order_status` VALUES (70, 49, 'cache_key_create_order', '订单生成', 1573983138);
INSERT INTO `yx_store_order_status` VALUES (71, 50, 'cache_key_create_order', '订单生成', 1573984487);
INSERT INTO `yx_store_order_status` VALUES (72, 51, 'cache_key_create_order', '订单生成', 1573984582);
INSERT INTO `yx_store_order_status` VALUES (73, 52, 'cache_key_create_order', '订单生成', 1573984673);
INSERT INTO `yx_store_order_status` VALUES (74, 53, 'cache_key_create_order', '订单生成', 1573985145);
INSERT INTO `yx_store_order_status` VALUES (75, 54, 'cache_key_create_order', '订单生成', 1573985250);
INSERT INTO `yx_store_order_status` VALUES (76, 55, 'cache_key_create_order', '订单生成', 1573985390);
INSERT INTO `yx_store_order_status` VALUES (77, 56, 'cache_key_create_order', '订单生成', 1573985955);
INSERT INTO `yx_store_order_status` VALUES (78, 57, 'cache_key_create_order', '订单生成', 1573987268);
INSERT INTO `yx_store_order_status` VALUES (79, 58, 'cache_key_create_order', '订单生成', 1573988022);
INSERT INTO `yx_store_order_status` VALUES (80, 59, 'cache_key_create_order', '订单生成', 1573988072);
INSERT INTO `yx_store_order_status` VALUES (81, 60, 'cache_key_create_order', '订单生成', 1573989589);
INSERT INTO `yx_store_order_status` VALUES (82, 61, 'cache_key_create_order', '订单生成', 1573991796);
INSERT INTO `yx_store_order_status` VALUES (83, 61, 'pay_success', '用户付款成功', 1573991796);
INSERT INTO `yx_store_order_status` VALUES (84, 62, 'cache_key_create_order', '订单生成', 1573991866);
INSERT INTO `yx_store_order_status` VALUES (85, 63, 'cache_key_create_order', '订单生成', 1573992169);
INSERT INTO `yx_store_order_status` VALUES (86, 64, 'cache_key_create_order', '订单生成', 1573992689);
INSERT INTO `yx_store_order_status` VALUES (88, 66, 'cache_key_create_order', '订单生成', 1573996087);
INSERT INTO `yx_store_order_status` VALUES (89, 67, 'cache_key_create_order', '订单生成', 1573996203);
INSERT INTO `yx_store_order_status` VALUES (90, 67, 'pay_success', '用户付款成功', 1573996204);
INSERT INTO `yx_store_order_status` VALUES (91, 68, 'cache_key_create_order', '订单生成', 1573996428);
INSERT INTO `yx_store_order_status` VALUES (92, 68, 'pay_success', '用户付款成功', 1573996429);
INSERT INTO `yx_store_order_status` VALUES (99, 75, 'cache_key_create_order', '订单生成', 1573999075);
INSERT INTO `yx_store_order_status` VALUES (100, 76, 'cache_key_create_order', '订单生成', 1573999223);
INSERT INTO `yx_store_order_status` VALUES (101, 77, 'cache_key_create_order', '订单生成', 1573999377);
INSERT INTO `yx_store_order_status` VALUES (102, 78, 'cache_key_create_order', '订单生成', 1573999546);
INSERT INTO `yx_store_order_status` VALUES (103, 79, 'cache_key_create_order', '订单生成', 1573999678);
INSERT INTO `yx_store_order_status` VALUES (104, 80, 'cache_key_create_order', '订单生成', 1574000136);
INSERT INTO `yx_store_order_status` VALUES (105, 81, 'cache_key_create_order', '订单生成', 1574000411);
INSERT INTO `yx_store_order_status` VALUES (106, 82, 'cache_key_create_order', '订单生成', 1574001048);
INSERT INTO `yx_store_order_status` VALUES (107, 83, 'cache_key_create_order', '订单生成', 1574001200);
INSERT INTO `yx_store_order_status` VALUES (108, 84, 'cache_key_create_order', '订单生成', 1574001303);
INSERT INTO `yx_store_order_status` VALUES (109, 85, 'cache_key_create_order', '订单生成', 1574039507);
INSERT INTO `yx_store_order_status` VALUES (110, 86, 'cache_key_create_order', '订单生成', 1574040168);
INSERT INTO `yx_store_order_status` VALUES (111, 87, 'cache_key_create_order', '订单生成', 1574040945);
INSERT INTO `yx_store_order_status` VALUES (112, 88, 'cache_key_create_order', '订单生成', 1574043328);
INSERT INTO `yx_store_order_status` VALUES (113, 68, 'delivery_goods', '已发货 快递公司：顺丰快递 快递单号：99999999', 1574047591);
INSERT INTO `yx_store_order_status` VALUES (114, 68, 'user_take_delivery', '用户已收货', 1574048275);
INSERT INTO `yx_store_order_status` VALUES (115, 89, 'cache_key_create_order', '订单生成', 1574221758);
INSERT INTO `yx_store_order_status` VALUES (116, 89, 'pay_success', '用户付款成功', 1574221758);
INSERT INTO `yx_store_order_status` VALUES (117, 90, 'cache_key_create_order', '订单生成', 1574243178);
INSERT INTO `yx_store_order_status` VALUES (118, 90, 'pay_success', '用户付款成功', 1574243179);
INSERT INTO `yx_store_order_status` VALUES (119, 91, 'cache_key_create_order', '订单生成', 1574244175);
INSERT INTO `yx_store_order_status` VALUES (120, 91, 'pay_success', '用户付款成功', 1574244175);
INSERT INTO `yx_store_order_status` VALUES (121, 91, 'apply_refund', '用户申请退款，原因：拼团取消开团', 1574326954);
INSERT INTO `yx_store_order_status` VALUES (122, 92, 'cache_key_create_order', '订单生成', 1574327309);
INSERT INTO `yx_store_order_status` VALUES (123, 92, 'pay_success', '用户付款成功', 1574327310);
INSERT INTO `yx_store_order_status` VALUES (124, 93, 'cache_key_create_order', '订单生成', 1574329404);
INSERT INTO `yx_store_order_status` VALUES (125, 93, 'pay_success', '用户付款成功', 1574329404);
INSERT INTO `yx_store_order_status` VALUES (126, 96, 'cache_key_create_order', '订单生成', 1574425447);
INSERT INTO `yx_store_order_status` VALUES (127, 96, 'pay_success', '用户付款成功', 1574425447);
INSERT INTO `yx_store_order_status` VALUES (128, 32, 'order_edit', '修改实际支付金额', 1574740506);
INSERT INTO `yx_store_order_status` VALUES (129, 32, 'order_edit', '修改实际支付金额', 1574740704);
INSERT INTO `yx_store_order_status` VALUES (130, 32, 'order_edit', '修改实际支付金额', 1574741913);
INSERT INTO `yx_store_order_status` VALUES (131, 96, 'delivery_goods', '已发货 快递公司：顺丰快递快递单号：99999', 1574752175);
INSERT INTO `yx_store_order_status` VALUES (132, 93, 'delivery_goods', '已发货 快递公司：顺丰快递快递单号：9669999', 1574752215);
INSERT INTO `yx_store_order_status` VALUES (133, 91, 'order_edit', '退款给用户：120.0元', 1574757130);
INSERT INTO `yx_store_order_status` VALUES (134, 91, 'order_edit', '退款给用户：120.0元', 1574757363);
INSERT INTO `yx_store_order_status` VALUES (135, 97, 'cache_key_create_order', '订单生成', 1575095149);
INSERT INTO `yx_store_order_status` VALUES (136, 98, 'cache_key_create_order', '订单生成', 1575095357);
INSERT INTO `yx_store_order_status` VALUES (137, 99, 'cache_key_create_order', '订单生成', 1575771514);
INSERT INTO `yx_store_order_status` VALUES (138, 99, 'pay_success', '用户付款成功', 1575771514);
INSERT INTO `yx_store_order_status` VALUES (139, 96, 'user_take_delivery', '用户已收货', 1575868078);
INSERT INTO `yx_store_order_status` VALUES (140, 92, 'delivery_goods', '已发货 快递公司：中通快递 快递单号：9999999', 1575880109);
INSERT INTO `yx_store_order_status` VALUES (141, 36, 'delivery_goods', '已发货 快递公司：申通快递 快递单号：null', 1576142259);
INSERT INTO `yx_store_order_status` VALUES (142, 67, 'delivery_goods', '已发货 快递公司：顺丰速运 快递单号：444444', 1576142488);
INSERT INTO `yx_store_order_status` VALUES (143, 61, 'delivery_goods', '已发货 快递公司：顺丰速运 快递单号：99999', 1576142551);
INSERT INTO `yx_store_order_status` VALUES (144, 42, 'delivery_goods', '已发货 快递公司：顺丰速运快递单号：999999', 1576229079);
INSERT INTO `yx_store_order_status` VALUES (145, 100, 'cache_key_create_order', '订单生成', 1576234308);
INSERT INTO `yx_store_order_status` VALUES (146, 100, 'pay_success', '用户付款成功', 1576234308);
INSERT INTO `yx_store_order_status` VALUES (147, 91, 'refund_price', '退款给用户：120元', 1576286856);
INSERT INTO `yx_store_order_status` VALUES (148, 101, 'cache_key_create_order', '订单生成', 1576565376);
INSERT INTO `yx_store_order_status` VALUES (149, 101, 'pay_success', '用户付款成功', 1576565376);
INSERT INTO `yx_store_order_status` VALUES (150, 102, 'cache_key_create_order', '订单生成', 1577086164);
INSERT INTO `yx_store_order_status` VALUES (151, 102, 'pay_success', '用户付款成功', 1577086165);
INSERT INTO `yx_store_order_status` VALUES (152, 103, 'cache_key_create_order', '订单生成', 1577345071);
INSERT INTO `yx_store_order_status` VALUES (153, 103, 'pay_success', '用户付款成功', 1577345071);
INSERT INTO `yx_store_order_status` VALUES (154, 104, 'cache_key_create_order', '订单生成', 1577442136);
INSERT INTO `yx_store_order_status` VALUES (155, 104, 'pay_success', '用户付款成功', 1577442136);
INSERT INTO `yx_store_order_status` VALUES (156, 104, 'apply_refund', '用户申请退款，原因：收货地址填错了', 1577586499);
INSERT INTO `yx_store_order_status` VALUES (157, 104, 'order_edit', '退款给用户：0.0元', 1577586525);
INSERT INTO `yx_store_order_status` VALUES (158, 103, 'apply_refund', '用户申请退款，原因：收货地址填错了', 1577586729);
INSERT INTO `yx_store_order_status` VALUES (159, 105, 'cache_key_create_order', '订单生成', 1577611680);
INSERT INTO `yx_store_order_status` VALUES (160, 105, 'pay_success', '用户付款成功', 1577611680);
INSERT INTO `yx_store_order_status` VALUES (161, 30, 'order_edit', '修改订单价格为：0', 1577769911);
INSERT INTO `yx_store_order_status` VALUES (162, 106, 'cache_key_create_order', '订单生成', 1577774706);
INSERT INTO `yx_store_order_status` VALUES (163, 106, 'pay_success', '用户付款成功', 1577774706);
INSERT INTO `yx_store_order_status` VALUES (164, 96, 'check_order_over', '用户评价', 1577779610);
INSERT INTO `yx_store_order_status` VALUES (165, 107, 'cache_key_create_order', '订单生成', 1577890654);
INSERT INTO `yx_store_order_status` VALUES (166, 108, 'cache_key_create_order', '订单生成', 1578386334);
INSERT INTO `yx_store_order_status` VALUES (167, 108, 'pay_success', '用户付款成功', 1578386338);
INSERT INTO `yx_store_order_status` VALUES (169, 30, 'order_edit', '修改订单价格为：0', 1578454206);
INSERT INTO `yx_store_order_status` VALUES (170, 110, 'cache_key_create_order', '订单生成', 1578482250);
INSERT INTO `yx_store_order_status` VALUES (171, 110, 'pay_success', '用户付款成功', 1578482250);
INSERT INTO `yx_store_order_status` VALUES (172, 99, 'user_take_delivery', '用户已收货', 1578733510);
INSERT INTO `yx_store_order_status` VALUES (173, 111, 'cache_key_create_order', '订单生成', 1578839691);
INSERT INTO `yx_store_order_status` VALUES (174, 111, 'pay_success', '用户付款成功', 1578839691);
INSERT INTO `yx_store_order_status` VALUES (175, 111, 'delivery_goods', '已发货 快递公司：申通快递 快递单号：333333', 1578994696);
INSERT INTO `yx_store_order_status` VALUES (176, 111, 'delivery_goods', '已发货 快递公司：申通快递 快递单号：333333', 1578995652);
INSERT INTO `yx_store_order_status` VALUES (177, 110, 'delivery_goods', '已发货 快递公司：申通快递 快递单号：111111', 1578995709);
INSERT INTO `yx_store_order_status` VALUES (178, 30, 'order_edit', '修改订单价格为：2', 1578998571);
INSERT INTO `yx_store_order_status` VALUES (179, 30, 'order_edit', '修改实际支付金额', 1579003631);
INSERT INTO `yx_store_order_status` VALUES (180, 104, 'refund_price', '退款给用户：0元', 1580440738);
INSERT INTO `yx_store_order_status` VALUES (181, 104, 'refund_price', '退款给用户：0元', 1580440967);
INSERT INTO `yx_store_order_status` VALUES (182, 112, 'cache_key_create_order', '订单生成', 1581586206);
INSERT INTO `yx_store_order_status` VALUES (183, 112, 'pay_success', '用户付款成功', 1581586206);
INSERT INTO `yx_store_order_status` VALUES (184, 112, 'delivery_goods', '已发货 快递公司：申通快递 快递单号：2222', 1581685821);
INSERT INTO `yx_store_order_status` VALUES (185, 113, 'cache_key_create_order', '订单生成', 1582080106);
INSERT INTO `yx_store_order_status` VALUES (186, 113, 'pay_success', '用户付款成功', 1582080107);
INSERT INTO `yx_store_order_status` VALUES (187, 113, 'apply_refund', '用户申请退款，原因：拼团取消开团', 1582080790);
INSERT INTO `yx_store_order_status` VALUES (188, 114, 'cache_key_create_order', '订单生成', 1582080948);
INSERT INTO `yx_store_order_status` VALUES (189, 114, 'pay_success', '用户付款成功', 1582080948);
INSERT INTO `yx_store_order_status` VALUES (190, 114, 'apply_refund', '用户申请退款，原因：拼团取消开团', 1582080960);
INSERT INTO `yx_store_order_status` VALUES (191, 115, 'cache_key_create_order', '订单生成', 1582081730);
INSERT INTO `yx_store_order_status` VALUES (192, 115, 'pay_success', '用户付款成功', 1582081730);
INSERT INTO `yx_store_order_status` VALUES (193, 115, 'apply_refund', '用户申请退款，原因：拼团取消开团', 1582081738);
INSERT INTO `yx_store_order_status` VALUES (194, 116, 'cache_key_create_order', '订单生成', 1582081800);
INSERT INTO `yx_store_order_status` VALUES (195, 116, 'pay_success', '用户付款成功', 1582081800);
INSERT INTO `yx_store_order_status` VALUES (196, 116, 'apply_refund', '用户申请退款，原因：拼团取消开团', 1582081807);
INSERT INTO `yx_store_order_status` VALUES (197, 112, 'user_take_delivery', '用户已收货', 1582704565);
INSERT INTO `yx_store_order_status` VALUES (198, 117, 'cache_key_create_order', '订单生成', 1582704871);
INSERT INTO `yx_store_order_status` VALUES (199, 117, 'pay_success', '用户付款成功', 1582704871);
INSERT INTO `yx_store_order_status` VALUES (200, 117, 'delivery_goods', '已发货 快递公司：申通快递快递单号：3333333', 1582705023);
INSERT INTO `yx_store_order_status` VALUES (201, 118, 'cache_key_create_order', '订单生成', 1582809322);
INSERT INTO `yx_store_order_status` VALUES (202, 118, 'pay_success', '用户付款成功', 1582809322);
INSERT INTO `yx_store_order_status` VALUES (203, 119, 'cache_key_create_order', '订单生成', 1582809558);
INSERT INTO `yx_store_order_status` VALUES (204, 120, 'cache_key_create_order', '订单生成', 1582810596);
INSERT INTO `yx_store_order_status` VALUES (205, 120, 'pay_success', '用户付款成功', 1582810596);
INSERT INTO `yx_store_order_status` VALUES (206, 121, 'cache_key_create_order', '订单生成', 1582810992);
INSERT INTO `yx_store_order_status` VALUES (209, 120, 'delivery_goods', '已发货 快递公司：顺丰速运快递单号：999999', 1582812344);
INSERT INTO `yx_store_order_status` VALUES (210, 120, 'user_take_delivery', '用户已收货', 1582812352);
INSERT INTO `yx_store_order_status` VALUES (211, 118, 'delivery_goods', '已发货 快递公司：申通快递 快递单号：444444', 1582813156);
INSERT INTO `yx_store_order_status` VALUES (212, 118, 'user_take_delivery', '用户已收货', 1582813163);
INSERT INTO `yx_store_order_status` VALUES (213, 122, 'cache_key_create_order', '订单生成', 1582970348);
INSERT INTO `yx_store_order_status` VALUES (214, 122, 'pay_success', '用户付款成功', 1582970348);
INSERT INTO `yx_store_order_status` VALUES (215, 123, 'cache_key_create_order', '订单生成', 1582970946);
INSERT INTO `yx_store_order_status` VALUES (216, 123, 'pay_success', '用户付款成功', 1582970946);
INSERT INTO `yx_store_order_status` VALUES (217, 124, 'cache_key_create_order', '订单生成', 1582971169);
INSERT INTO `yx_store_order_status` VALUES (218, 124, 'pay_success', '用户付款成功', 1582971170);
INSERT INTO `yx_store_order_status` VALUES (219, 125, 'cache_key_create_order', '订单生成', 1582971472);
INSERT INTO `yx_store_order_status` VALUES (220, 125, 'pay_success', '用户付款成功', 1582971472);
INSERT INTO `yx_store_order_status` VALUES (221, 126, 'cache_key_create_order', '订单生成', 1582990630);
INSERT INTO `yx_store_order_status` VALUES (222, 126, 'pay_success', '用户付款成功', 1582990630);
INSERT INTO `yx_store_order_status` VALUES (223, 127, 'cache_key_create_order', '订单生成', 1583392755);
INSERT INTO `yx_store_order_status` VALUES (224, 127, 'pay_success', '用户付款成功', 1583392756);
INSERT INTO `yx_store_order_status` VALUES (225, 128, 'cache_key_create_order', '订单生成', 1583393731);
INSERT INTO `yx_store_order_status` VALUES (226, 128, 'pay_success', '用户付款成功', 1583393731);
INSERT INTO `yx_store_order_status` VALUES (227, 129, 'cache_key_create_order', '订单生成', 1583394584);
INSERT INTO `yx_store_order_status` VALUES (228, 129, 'pay_success', '用户付款成功', 1583394584);
INSERT INTO `yx_store_order_status` VALUES (229, 130, 'cache_key_create_order', '订单生成', 1583399210);
INSERT INTO `yx_store_order_status` VALUES (230, 130, 'pay_success', '用户付款成功', 1583399210);
INSERT INTO `yx_store_order_status` VALUES (231, 131, 'cache_key_create_order', '订单生成', 1583403288);
INSERT INTO `yx_store_order_status` VALUES (232, 131, 'pay_success', '用户付款成功', 1583403288);
INSERT INTO `yx_store_order_status` VALUES (233, 132, 'cache_key_create_order', '订单生成', 1583410828);
INSERT INTO `yx_store_order_status` VALUES (234, 132, 'pay_success', '用户付款成功', 1583410828);
INSERT INTO `yx_store_order_status` VALUES (235, 133, 'cache_key_create_order', '订单生成', 1583416032);
INSERT INTO `yx_store_order_status` VALUES (236, 133, 'pay_success', '用户付款成功', 1583416032);
INSERT INTO `yx_store_order_status` VALUES (237, 134, 'cache_key_create_order', '订单生成', 1583479392);
INSERT INTO `yx_store_order_status` VALUES (238, 135, 'cache_key_create_order', '订单生成', 1583486513);
INSERT INTO `yx_store_order_status` VALUES (239, 135, 'pay_success', '用户付款成功', 1583486513);

-- ----------------------------
-- Table structure for yx_store_pink
-- ----------------------------
DROP TABLE IF EXISTS `yx_store_pink`;
CREATE TABLE `yx_store_pink`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `uid` int(10) UNSIGNED NOT NULL COMMENT '用户id',
  `order_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单id 生成',
  `order_id_key` int(10) UNSIGNED NOT NULL COMMENT '订单id  数据库',
  `total_num` int(10) UNSIGNED NOT NULL COMMENT '购买商品个数',
  `total_price` decimal(10, 2) UNSIGNED NOT NULL COMMENT '购买总金额',
  `cid` int(10) UNSIGNED NOT NULL COMMENT '拼团产品id',
  `pid` int(10) UNSIGNED NOT NULL COMMENT '产品id',
  `people` int(10) UNSIGNED NOT NULL COMMENT '拼图总人数',
  `price` decimal(10, 2) UNSIGNED NOT NULL COMMENT '拼团产品单价',
  `add_time` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '开始时间',
  `stop_time` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `k_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '团长id 0为团长',
  `is_tpl` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否发送模板消息0未发送1已发送',
  `is_refund` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否退款 0未退款 1已退款',
  `status` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态1进行中2已完成3未完成',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '拼团表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yx_store_pink
-- ----------------------------
INSERT INTO `yx_store_pink` VALUES (1, 12, '20191120180255448', 91, 1, 120.00, 2, 19, 2, 120.00, '1574244175', '1574326954', 0, 0, 0, 3);
INSERT INTO `yx_store_pink` VALUES (2, 12, '20191121170829222', 92, 1, 1.00, 2, 19, 2, 1.00, '1574327310', '1574330684', 0, 0, 0, 2);
INSERT INTO `yx_store_pink` VALUES (3, 11, '20191121174323679', 93, 1, 1.00, 2, 19, 2, 1.00, '1574329404', '1574330684', 2, 0, 0, 2);
INSERT INTO `yx_store_pink` VALUES (4, 12, '20191122202407197', 96, 1, 1.00, 2, 19, 2, 1.00, '1574425447', '1574511847', 0, 0, 0, 1);
INSERT INTO `yx_store_pink` VALUES (5, 11, '1229959230786633728', 113, 1, 11.00, 2, 19, 2, 1.00, '1582080107', '1582080790', 0, 0, 0, 3);
INSERT INTO `yx_store_pink` VALUES (6, 11, '1229962759391674368', 114, 1, 11.00, 2, 19, 2, 1.00, '1582080948', '1582080960', 0, 0, 0, 3);
INSERT INTO `yx_store_pink` VALUES (7, 11, '1229966041136758784', 115, 1, 11.00, 2, 19, 2, 1.00, '1582081730', '1582081738', 0, 0, 0, 3);
INSERT INTO `yx_store_pink` VALUES (8, 11, '1229966335119720448', 116, 1, 11.00, 2, 19, 2, 1.00, '1582081800', '1582081807', 0, 0, 0, 3);

-- ----------------------------
-- Table structure for yx_store_product
-- ----------------------------
DROP TABLE IF EXISTS `yx_store_product`;
CREATE TABLE `yx_store_product`  (
  `id` mediumint(11) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `mer_id` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '商户Id(0为总后台管理员创建,不为0的时候是商户后台创建)',
  `image` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品图片',
  `slider_image` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '轮播图',
  `store_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `store_info` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品简介',
  `keyword` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '关键字',
  `bar_code` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '产品条码（一维码）',
  `cate_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类id',
  `price` decimal(8, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '商品价格',
  `vip_price` decimal(8, 2) UNSIGNED NULL DEFAULT 0.00 COMMENT '会员价格',
  `ot_price` decimal(8, 2) UNSIGNED NULL DEFAULT 0.00 COMMENT '市场价',
  `postage` decimal(8, 2) UNSIGNED NULL DEFAULT 0.00 COMMENT '邮费',
  `unit_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位名',
  `sort` smallint(11) NULL DEFAULT 0 COMMENT '排序',
  `sales` mediumint(11) UNSIGNED NULL DEFAULT 0 COMMENT '销量',
  `stock` mediumint(11) UNSIGNED NULL DEFAULT 0 COMMENT '库存',
  `is_show` tinyint(1) NULL DEFAULT 1 COMMENT '状态（0：未上架，1：上架）',
  `is_hot` tinyint(1) NULL DEFAULT 0 COMMENT '是否热卖',
  `is_benefit` tinyint(1) NULL DEFAULT 0 COMMENT '是否优惠',
  `is_best` tinyint(1) NULL DEFAULT 0 COMMENT '是否精品',
  `is_new` tinyint(1) NULL DEFAULT 0 COMMENT '是否新品',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '产品描述',
  `add_time` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '添加时间',
  `is_postage` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '是否包邮',
  `is_del` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '是否删除',
  `mer_use` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '商户是否代理 0不可代理1可代理',
  `give_integral` decimal(8, 2) UNSIGNED NULL DEFAULT NULL COMMENT '获得积分',
  `cost` decimal(8, 2) UNSIGNED NULL DEFAULT NULL COMMENT '成本价',
  `is_seckill` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '秒杀状态 0 未开启 1已开启',
  `is_bargain` tinyint(1) UNSIGNED NULL DEFAULT NULL COMMENT '砍价状态 0未开启 1开启',
  `is_good` tinyint(1) NULL DEFAULT 0 COMMENT '是否优品推荐',
  `ficti` mediumint(11) NULL DEFAULT 100 COMMENT '虚拟销量',
  `browse` int(11) NULL DEFAULT 0 COMMENT '浏览量',
  `code_path` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '产品二维码地址(用户小程序海报)',
  `soure_link` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '淘宝京东1688类型',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `is_hot`(`is_hot`) USING BTREE,
  INDEX `is_benefit`(`is_benefit`) USING BTREE,
  INDEX `is_best`(`is_best`) USING BTREE,
  INDEX `is_new`(`is_new`) USING BTREE,
  INDEX `toggle_on_sale, is_del`(`is_del`) USING BTREE,
  INDEX `price`(`price`) USING BTREE,
  INDEX `is_show`(`is_show`) USING BTREE,
  INDEX `sort`(`sort`) USING BTREE,
  INDEX `sales`(`sales`) USING BTREE,
  INDEX `add_time`(`add_time`) USING BTREE,
  INDEX `is_postage`(`is_postage`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yx_store_product
-- ----------------------------
INSERT INTO `yx_store_product` VALUES (19, NULL, 'https://image.dayouqiantu.cn/5ca011a1cd487.jpg', 'https://image.dayouqiantu.cn/5ca01c7fc9238.jpg,https://image.dayouqiantu.cn/5ca01c7676042.jpg', '70gA4黑白打印复印', '打印复印资料A4黑白彩色印刷画册书本装订图文数码快印服务', '打印复印', '', '24', 120.00, 0.20, 0.50, 0.00, '张', 1, 44, 80, 1, 1, 1, 1, 1, '<p style=\"text-align: center;\"><strong>文件请传QQ2412733099，</strong></p><p style=\"text-align: center;\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\"text-align: center;\"><strong>手机/微信：13733990583</strong></p><p style=\"text-align: center;\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\"text-align: center;\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\"text-align: center;\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\"text-align: center;\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\"text-align: center;\"><strong>更大量请联系店主报价，</strong></p><p style=\"text-align: center;\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\"text-align: center;\"><strong>付款时直接抵扣</strong></p><p style=\"text-align: center;\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\"text-align: left;\"><strong><br></strong></p><p><br></p><p>&nbsp; &nbsp;&nbsp;<img src=\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\" style=\"max-width: 100%;\"><br></p>', NULL, 1, 0, 0, 1.00, 0.20, 0, 0, 1, NULL, NULL, '', '');
INSERT INTO `yx_store_product` VALUES (20, 0, 'https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg', 'https://image.dayouqiantu.cn/5ca05103ad634.jpg,http://localhost:8000/file/pic/05ea40b831858a8cf423aa709840507c-20200228083801500.png', '70gA4彩色打印复印', '70gA4彩色打印复印', '彩色打印复印', '', '24', 0.50, 0.50, 1.00, 5.00, '张', 0, 5, 3994, 1, 1, 1, 1, 1, '<p style=\"text-align: center;\">7<strong>文件请传QQ2412733099，</strong></p><p style=\"text-align: center;\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\"text-align: center;\"><strong>手机/微信：13733990583</strong></p><p style=\"text-align: center;\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\"text-align: center;\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\"text-align: center;\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\"text-align: center;\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\"text-align: center;\"><strong>更大量请联系店主报价，</strong></p><p style=\"text-align: center;\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\"text-align: center;\"><strong>付款时直接抵扣</strong></p><p style=\"text-align: center;\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\"text-align: center;\"><img src=\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\" style=\"max-width:100%;\"><strong><br></strong></p><p style=\"text-align: center;\"><br></p>', 1573108370, 0, 0, 0, 0.00, 0.50, 0, 0, 1, 0, 0, '', '');
INSERT INTO `yx_store_product` VALUES (21, 0, 'https://image.dayouqiantu.cn/list_32.png', 'https://image.dayouqiantu.cn/list_22.png', '4444', '44444', '444', '4', '28', 100.00, 0.00, 200.00, 0.00, '44', 0, 0, 999, 0, 1, 1, 1, 1, '<p>44444</p><p><img src=\"https://image.dayouqiantu.cn/list_32.png\" style=\"max-width:100%;\"><br></p>', 1578373770, 1, 0, 0, 10.00, 50.00, 0, 0, 1, 10, 0, '', '');
INSERT INTO `yx_store_product` VALUES (22, 0, 'http://localhost:8000/file/pic/list_16-20200110120838173.png', 'http://localhost:8000/file/pic/list_16-20200110120838173.png,http://localhost:8000/file/pic/list_32-20200109100213309.png', '3333', '3333', '333', '', '1', 1.00, 0.00, 1.00, 0.00, '333', 0, 0, 11, 1, 0, 0, 0, 0, '<p>4444</p><p><img src=\"http://localhost:8000/file/pic/list_32-20200110064436937.png\" style=\"max-width:100%;\"><br></p>', 1578642740, 0, 1, 0, 0.00, 1.00, 0, 0, 0, 0, 0, '', '');
INSERT INTO `yx_store_product` VALUES (23, 0, 'http://localhost:8000/file/pic/list_16-20200110120838173.png', 'http://localhost:8000/file/pic/list_32-20200109100213309.png', '111', '111', '1', '', '1', 0.00, 0.00, 0.00, 0.00, '1', 0, 0, 41, 1, 0, 0, 0, 0, '<p>1111</p>', 1578653221, 0, 1, 0, 0.00, 0.00, 0, 0, 0, 0, 0, '', '');
INSERT INTO `yx_store_product` VALUES (24, 0, 'http://localhost:8000/file/pic/05ea40b831858a8cf423aa709840507c-20200228083801500.png', 'http://localhost:8000/file/pic/05ea40b831858a8cf423aa709840507c-20200228083801500.png', '33333', '333', '33', '', '1', 1.00, 0.00, 1.00, 0.00, '33', 0, 0, 0, 1, 0, 0, 0, 0, '<p>333</p>', 1582894605, 0, 0, 0, 0.00, 1.00, 0, 0, 0, 0, 0, '', '');

-- ----------------------------
-- Table structure for yx_store_product_attr
-- ----------------------------
DROP TABLE IF EXISTS `yx_store_product_attr`;
CREATE TABLE `yx_store_product_attr`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `product_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品ID',
  `attr_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '属性名',
  `attr_values` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '属性值',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `store_id`(`product_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品属性表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yx_store_product_attr
-- ----------------------------
INSERT INTO `yx_store_product_attr` VALUES (14, 20, '纸张', 'A3,A4');
INSERT INTO `yx_store_product_attr` VALUES (15, 20, '颜色', '白色,红色');

-- ----------------------------
-- Table structure for yx_store_product_attr_result
-- ----------------------------
DROP TABLE IF EXISTS `yx_store_product_attr_result`;
CREATE TABLE `yx_store_product_attr_result`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `product_id` int(10) UNSIGNED NOT NULL COMMENT '商品ID',
  `result` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品属性参数',
  `change_time` int(10) UNSIGNED NOT NULL COMMENT '上次修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `product_id`(`product_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品属性详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yx_store_product_attr_result
-- ----------------------------
INSERT INTO `yx_store_product_attr_result` VALUES (8, 20, '{\"attr\":[{\"attrHidden\":true,\"detailValue\":\"\",\"detail\":[\"A3\",\"A4\"],\"value\":\"纸张\"},{\"attrHidden\":true,\"detailValue\":\"\",\"detail\":[\"白色\",\"红色\"],\"value\":\"颜色\"}],\"value\":[{\"cost\":0.5,\"price\":0.5,\"pic\":\"https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg\",\"detail\":{\"颜色\":\"白色\",\"纸张\":\"A3\"},\"check\":false,\"sales\":\"999\"},{\"cost\":0.5,\"price\":0.5,\"pic\":\"https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg\",\"detail\":{\"颜色\":\"红色\",\"纸张\":\"A3\"},\"check\":false,\"sales\":\"999\"},{\"cost\":0.5,\"price\":\"0.2\",\"pic\":\"https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg\",\"detail\":{\"颜色\":\"白色\",\"纸张\":\"A4\"},\"check\":false,\"sales\":\"999\"},{\"cost\":0.5,\"price\":\"0.2\",\"pic\":\"https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg\",\"detail\":{\"颜色\":\"红色\",\"纸张\":\"A4\"},\"check\":false,\"sales\":\"999\"}]}', 1578320995);

-- ----------------------------
-- Table structure for yx_store_product_attr_value
-- ----------------------------
DROP TABLE IF EXISTS `yx_store_product_attr_value`;
CREATE TABLE `yx_store_product_attr_value`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `product_id` int(10) UNSIGNED NOT NULL COMMENT '商品ID',
  `suk` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品属性索引值 (attr_value|attr_value[|....])',
  `stock` int(10) UNSIGNED NOT NULL COMMENT '属性对应的库存',
  `sales` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '销量',
  `price` decimal(8, 2) UNSIGNED NOT NULL COMMENT '属性金额',
  `image` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片',
  `unique` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '唯一值',
  `cost` decimal(8, 2) UNSIGNED NOT NULL COMMENT '成本价',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unique`(`unique`, `suk`) USING BTREE,
  INDEX `store_id`(`product_id`, `suk`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品属性值表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yx_store_product_attr_value
-- ----------------------------
INSERT INTO `yx_store_product_attr_value` VALUES (26, 20, 'A3,白色', 996, NULL, 0.50, 'https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg', '2e51e5d2d86a44748f81cdd5bf7a51c0', 0.50);
INSERT INTO `yx_store_product_attr_value` VALUES (27, 20, 'A3,红色', 999, NULL, 0.50, 'https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg', 'fd545718bbdb4634aa54228ecca6febe', 0.50);
INSERT INTO `yx_store_product_attr_value` VALUES (28, 20, 'A4,白色', 999, NULL, 0.20, 'https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg', '5e0ae3241d8c4ea793368666f862dd3b', 0.50);
INSERT INTO `yx_store_product_attr_value` VALUES (29, 20, 'A4,红色', 999, NULL, 0.20, 'https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg', '4b4b306134434832885a52f3a85955ce', 0.50);

-- ----------------------------
-- Table structure for yx_store_product_cate
-- ----------------------------
DROP TABLE IF EXISTS `yx_store_product_cate`;
CREATE TABLE `yx_store_product_cate`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL DEFAULT 0 COMMENT '产品id',
  `cate_id` int(11) NOT NULL DEFAULT 0 COMMENT '分类id',
  `add_time` int(11) NOT NULL DEFAULT 0 COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 73 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '产品分类辅助表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yx_store_product_cate
-- ----------------------------
INSERT INTO `yx_store_product_cate` VALUES (14, 6, 2, 1558580351);
INSERT INTO `yx_store_product_cate` VALUES (24, 4, 3, 1559110372);
INSERT INTO `yx_store_product_cate` VALUES (25, 4, 2, 1559110372);
INSERT INTO `yx_store_product_cate` VALUES (28, 3, 3, 1559110403);
INSERT INTO `yx_store_product_cate` VALUES (29, 3, 2, 1559110403);
INSERT INTO `yx_store_product_cate` VALUES (38, 12, 3, 1560650420);
INSERT INTO `yx_store_product_cate` VALUES (49, 5, 4, 1562729229);
INSERT INTO `yx_store_product_cate` VALUES (50, 5, 7, 1562729229);
INSERT INTO `yx_store_product_cate` VALUES (51, 5, 2, 1562729229);
INSERT INTO `yx_store_product_cate` VALUES (52, 5, 3, 1562729229);
INSERT INTO `yx_store_product_cate` VALUES (53, 5, 19, 1562729229);
INSERT INTO `yx_store_product_cate` VALUES (55, 11, 4, 1562729250);
INSERT INTO `yx_store_product_cate` VALUES (59, 10, 4, 1562741711);
INSERT INTO `yx_store_product_cate` VALUES (60, 1, 2, 1562835320);
INSERT INTO `yx_store_product_cate` VALUES (61, 1, 7, 1562835320);
INSERT INTO `yx_store_product_cate` VALUES (64, 17, 4, 1564471319);
INSERT INTO `yx_store_product_cate` VALUES (65, 2, 3, 1565148046);
INSERT INTO `yx_store_product_cate` VALUES (66, 2, 2, 1565148046);
INSERT INTO `yx_store_product_cate` VALUES (67, 18, 0, 1565148206);
INSERT INTO `yx_store_product_cate` VALUES (68, 9, 2, 1565148922);
INSERT INTO `yx_store_product_cate` VALUES (70, 8, 2, 1565426114);
INSERT INTO `yx_store_product_cate` VALUES (71, 7, 2, 1565600246);
INSERT INTO `yx_store_product_cate` VALUES (72, 19, 4, 1565687845);

-- ----------------------------
-- Table structure for yx_store_product_relation
-- ----------------------------
DROP TABLE IF EXISTS `yx_store_product_relation`;
CREATE TABLE `yx_store_product_relation`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `uid` int(10) UNSIGNED NOT NULL COMMENT '用户ID',
  `product_id` int(10) UNSIGNED NOT NULL COMMENT '商品ID',
  `type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型(收藏(collect）、点赞(like))',
  `category` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '某种类型的商品(普通商品、秒杀商品)',
  `add_time` int(10) UNSIGNED NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uid`(`uid`, `product_id`, `type`, `category`) USING BTREE,
  INDEX `type`(`type`) USING BTREE,
  INDEX `category`(`category`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品点赞和收藏表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for yx_store_product_reply
-- ----------------------------
DROP TABLE IF EXISTS `yx_store_product_reply`;
CREATE TABLE `yx_store_product_reply`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论ID',
  `uid` int(11) NOT NULL COMMENT '用户ID',
  `oid` int(11) NOT NULL COMMENT '订单ID',
  `unique` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '唯一id',
  `product_id` int(11) NOT NULL COMMENT '产品id',
  `reply_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'product' COMMENT '某种商品类型(普通商品、秒杀商品）',
  `product_score` tinyint(1) NOT NULL COMMENT '商品分数',
  `service_score` tinyint(1) NOT NULL COMMENT '服务分数',
  `comment` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论内容',
  `pics` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论图片',
  `add_time` int(11) NOT NULL COMMENT '评论时间',
  `merchant_reply_content` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员回复内容',
  `merchant_reply_time` int(11) NULL DEFAULT NULL COMMENT '管理员回复时间',
  `is_del` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '0未删除1已删除',
  `is_reply` tinyint(1) NOT NULL DEFAULT 0 COMMENT '0未回复1已回复',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `order_id_2`(`oid`, `unique`) USING BTREE,
  INDEX `add_time`(`add_time`) USING BTREE,
  INDEX `parent_id`(`reply_type`) USING BTREE,
  INDEX `is_del`(`is_del`) USING BTREE,
  INDEX `product_score`(`product_score`) USING BTREE,
  INDEX `service_score`(`service_score`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '评论表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yx_store_product_reply
-- ----------------------------
INSERT INTO `yx_store_product_reply` VALUES (2, 12, 11, '7eab5fa9f3de480095abc5b1dee40f85', 19, 'product', 5, 5, '999999', 'http://pic.dayouqiantu.cn/Screenshot_2019-12-08-15-15-17-366_com.tencent.mm.png,http://pic.dayouqiantu.cn/IMG_20150322_111102.jpg', 1572753366, NULL, NULL, 0, 0);
INSERT INTO `yx_store_product_reply` VALUES (3, 12, 12, '274fe50fc9d0492d9e375649cae0b194', 19, 'product', 5, 5, '好评', 'http://pic.dayouqiantu.cn/Screenshot_2019-12-08-15-15-17-366_com.tencent.mm.png,http://pic.dayouqiantu.cn/IMG_20150322_111102.jpg', 1573041203, NULL, NULL, 0, 0);
INSERT INTO `yx_store_product_reply` VALUES (4, 12, 96, 'af8ca64055ac4c739cfded03c2c0b784', 19, 'product', 1, 1, '差评', '', 1577779610, NULL, NULL, 1, 0);

-- ----------------------------
-- Table structure for yx_store_seckill
-- ----------------------------
DROP TABLE IF EXISTS `yx_store_seckill`;
CREATE TABLE `yx_store_seckill`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '商品秒杀产品表id',
  `product_id` int(10) UNSIGNED NOT NULL COMMENT '商品id',
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '推荐图',
  `images` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '轮播图',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '活动标题',
  `info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '简介',
  `price` decimal(10, 2) UNSIGNED NOT NULL COMMENT '价格',
  `cost` decimal(8, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '成本',
  `ot_price` decimal(10, 2) UNSIGNED NOT NULL COMMENT '原价',
  `give_integral` decimal(10, 2) UNSIGNED NOT NULL COMMENT '返多少积分',
  `sort` int(10) UNSIGNED NOT NULL COMMENT '排序',
  `stock` int(10) UNSIGNED NOT NULL COMMENT '库存',
  `sales` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '销量',
  `unit_name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '单位名',
  `postage` decimal(8, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '邮费',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `start_time` int(11) NOT NULL DEFAULT 0 COMMENT '开始时间',
  `stop_time` int(11) NOT NULL DEFAULT 0 COMMENT '结束时间',
  `add_time` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '添加时间',
  `status` tinyint(1) UNSIGNED NOT NULL COMMENT '产品状态',
  `is_postage` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否包邮',
  `is_hot` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '热门推荐',
  `is_del` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除 0未删除1已删除',
  `num` int(11) UNSIGNED NOT NULL COMMENT '最多秒杀几个',
  `is_show` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '显示',
  `end_time_date` datetime(0) NOT NULL,
  `start_time_date` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `product_id`(`product_id`) USING BTREE,
  INDEX `start_time`(`start_time`, `stop_time`) USING BTREE,
  INDEX `is_del`(`is_del`) USING BTREE,
  INDEX `is_hot`(`is_hot`) USING BTREE,
  INDEX `is_show`(`status`) USING BTREE,
  INDEX `add_time`(`add_time`) USING BTREE,
  INDEX `sort`(`sort`) USING BTREE,
  INDEX `is_postage`(`is_postage`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品秒杀产品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yx_store_seckill
-- ----------------------------
INSERT INTO `yx_store_seckill` VALUES (1, 20, 'https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg', 'https://image.dayouqiantu.cn/5ca05103ad634.jpg,https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg', '70gA4彩色打印复印', '70gA4彩色打印复印', 1.00, 0.50, 1.00, 0.00, 0, 100, 1, '张', 0.00, '<p style=\"text-align: center;\">7<strong>文件请传QQ2412733099，</strong></p><p style=\"text-align: center;\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\"text-align: center;\"><strong>手机/微信：13733990583</strong></p><p style=\"text-align: center;\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\"text-align: center;\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\"text-align: center;\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\"text-align: center;\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\"text-align: center;\"><strong>更大量请联系店主报价，</strong></p><p style=\"text-align: center;\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\"text-align: center;\"><strong>付款时直接抵扣</strong></p><p style=\"text-align: center;\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\"text-align: center;\"><img src=\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\" style=\"max-width:100%;\"><strong><br></strong></p><p style=\"text-align: center;\"><br></p>', 1576476046, 1577203200, '1576476055', 1, 1, 0, 0, 1, 1, '2019-12-25 00:00:00', '2019-12-16 14:00:46');
INSERT INTO `yx_store_seckill` VALUES (2, 20, 'https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg', 'https://image.dayouqiantu.cn/5ca05103ad634.jpg,http://localhost:8000/file/pic/05ea40b831858a8cf423aa709840507c-20200228083801500.png', '70gA4彩色打印复印', '70gA4彩色打印复印', 1.00, 0.50, 1.00, 0.00, 0, 99, 2, '张', 0.00, '<p style=\"text-align: center;\">7<strong>文件请传QQ2412733099，</strong></p><p style=\"text-align: center;\"><strong>718504558或2412733099@qq.com邮箱，</strong></p><p style=\"text-align: center;\"><strong>手机/微信：13733990583</strong></p><p style=\"text-align: center;\"><strong>量大请联系店主，一定让您满意而归</strong></p><p style=\"text-align: center;\"><strong>&nbsp; &nbsp;实体店面经营 保质保量</strong></p><p style=\"text-align: center;\"><strong>A4黑白2毛/张，量大1毛/张，</strong></p><p style=\"text-align: center;\"><strong>A3黑白双面5毛/张，量大3毛/张，</strong></p><p style=\"text-align: center;\"><strong>更大量请联系店主报价，</strong></p><p style=\"text-align: center;\"><strong>每消费1元可积1分，1分抵现金1毛，</strong></p><p style=\"text-align: center;\"><strong>付款时直接抵扣</strong></p><p style=\"text-align: center;\"><strong>（如此优惠的基础上还可积分，积分抵现金）</strong></p><p style=\"text-align: center;\"><img src=\"https://image.dayouqiantu.cn/5ca03007150f1.jpg\" style=\"max-width:100%;\"><strong><br></strong></p><p style=\"text-align: center;\"><br></p>', 1576477734, 1576598400, '1576477741', 1, 1, 0, 0, 1, 1, '2019-12-18 00:00:00', '2019-12-16 14:28:54');

-- ----------------------------
-- Table structure for yx_store_seckill_attr
-- ----------------------------
DROP TABLE IF EXISTS `yx_store_seckill_attr`;
CREATE TABLE `yx_store_seckill_attr`  (
  `product_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品ID',
  `attr_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '属性名',
  `attr_values` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '属性值',
  INDEX `store_id`(`product_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '秒杀商品属性表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for yx_store_seckill_attr_result
-- ----------------------------
DROP TABLE IF EXISTS `yx_store_seckill_attr_result`;
CREATE TABLE `yx_store_seckill_attr_result`  (
  `product_id` int(10) UNSIGNED NOT NULL COMMENT '商品ID',
  `result` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品属性参数',
  `change_time` int(10) UNSIGNED NOT NULL COMMENT '上次修改时间',
  UNIQUE INDEX `product_id`(`product_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '秒杀商品属性详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for yx_store_seckill_attr_value
-- ----------------------------
DROP TABLE IF EXISTS `yx_store_seckill_attr_value`;
CREATE TABLE `yx_store_seckill_attr_value`  (
  `product_id` int(10) UNSIGNED NOT NULL COMMENT '商品ID',
  `suk` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品属性索引值 (attr_value|attr_value[|....])',
  `stock` int(10) UNSIGNED NOT NULL COMMENT '属性对应的库存',
  `sales` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '销量',
  `price` decimal(8, 2) UNSIGNED NOT NULL COMMENT '属性金额',
  `image` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片',
  `unique` char(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '唯一值',
  `cost` decimal(8, 2) UNSIGNED NOT NULL COMMENT '成本价',
  UNIQUE INDEX `unique`(`unique`, `suk`) USING BTREE,
  INDEX `store_id`(`product_id`, `suk`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '秒杀商品属性值表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for yx_store_visit
-- ----------------------------
DROP TABLE IF EXISTS `yx_store_visit`;
CREATE TABLE `yx_store_visit`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NULL DEFAULT NULL COMMENT '产品ID',
  `product_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品类型',
  `cate_id` int(11) NULL DEFAULT NULL COMMENT '产品分类ID',
  `type` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品类型',
  `uid` int(11) NULL DEFAULT NULL COMMENT '用户ID',
  `count` int(11) NULL DEFAULT NULL COMMENT '访问次数',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注描述',
  `add_time` int(11) NULL DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '产品浏览分析表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for yx_system_attachment
-- ----------------------------
DROP TABLE IF EXISTS `yx_system_attachment`;
CREATE TABLE `yx_system_attachment`  (
  `att_id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '附件名称',
  `att_dir` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '附件路径',
  `satt_dir` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '压缩图片路径',
  `att_size` char(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '附件大小',
  `att_type` char(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '附件类型',
  `pid` int(10) NOT NULL DEFAULT 0 COMMENT '分类ID0编辑器,1产品图片,2拼团图片,3砍价图片,4秒杀图片,5文章图片,6组合数据图',
  `time` int(11) NOT NULL DEFAULT 0 COMMENT '上传时间',
  `image_type` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '图片上传类型 1本地 2七牛云 3OSS 4COS ',
  `module_type` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '图片上传模块类型 1 后台上传 2 用户生成',
  PRIMARY KEY (`att_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '附件管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for yx_system_config
-- ----------------------------
DROP TABLE IF EXISTS `yx_system_config`;
CREATE TABLE `yx_system_config`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '配置id',
  `menu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字段名称',
  `value` varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '默认值',
  `sort` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '排序',
  `status` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '是否隐藏',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 205 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yx_system_config
-- ----------------------------
INSERT INTO `yx_system_config` VALUES (162, 'wechat_share_img', 'https://image.dayouqiantu.cn/list_34.png', NULL, NULL);
INSERT INTO `yx_system_config` VALUES (165, 'wechat_avatar', '', NULL, NULL);
INSERT INTO `yx_system_config` VALUES (172, 'wechat_share_synopsis', '4444444', NULL, NULL);
INSERT INTO `yx_system_config` VALUES (174, 'api', 'http://你的H5端域名/api/wechat/serve', NULL, NULL);
INSERT INTO `yx_system_config` VALUES (175, 'wechat_share_title', '4444', NULL, NULL);
INSERT INTO `yx_system_config` VALUES (177, 'store_postage', '10', 0, 0);
INSERT INTO `yx_system_config` VALUES (178, 'store_free_postage', '200', 0, 0);
INSERT INTO `yx_system_config` VALUES (179, 'integral_ratio', '1', 0, 0);
INSERT INTO `yx_system_config` VALUES (180, 'store_brokerage_ratio', '70', NULL, NULL);
INSERT INTO `yx_system_config` VALUES (181, 'store_brokerage_statu', '2', NULL, NULL);
INSERT INTO `yx_system_config` VALUES (182, 'user_extract_min_price', '1', NULL, NULL);
INSERT INTO `yx_system_config` VALUES (183, 'store_brokerage_two', '30', NULL, NULL);
INSERT INTO `yx_system_config` VALUES (184, 'site_url', 'http://localhost:8080', 0, 0);
INSERT INTO `yx_system_config` VALUES (185, 'api_url', 'http://127.0.0.1:8009', 0, 0);
INSERT INTO `yx_system_config` VALUES (186, 'order_cancel_job_time', '20', 0, 0);
INSERT INTO `yx_system_config` VALUES (187, 'wechat_appid', '11112', NULL, NULL);
INSERT INTO `yx_system_config` VALUES (188, 'wechat_appsecret', '222266', NULL, NULL);
INSERT INTO `yx_system_config` VALUES (189, 'wechat_encodingaeskey', '6666', NULL, NULL);
INSERT INTO `yx_system_config` VALUES (190, 'wechat_token', '999', NULL, NULL);
INSERT INTO `yx_system_config` VALUES (191, 'wxpay_mchId', '2222', NULL, NULL);
INSERT INTO `yx_system_config` VALUES (192, 'wxpay_appId', '111111', NULL, NULL);
INSERT INTO `yx_system_config` VALUES (193, 'wxpay_mchKey', '55555', NULL, NULL);
INSERT INTO `yx_system_config` VALUES (194, 'wxapp_appId', '22222', NULL, NULL);
INSERT INTO `yx_system_config` VALUES (195, 'wxapp_secret', '22222222', NULL, NULL);
INSERT INTO `yx_system_config` VALUES (196, 'wxpay_keyPath', 'http://localhost:8000/file/pic/list_30-20200110053337209.png', 0, 0);
INSERT INTO `yx_system_config` VALUES (197, 'imageArr', '[\"https://image.dayouqiantu.cn/list_34.png\"]', NULL, NULL);
INSERT INTO `yx_system_config` VALUES (198, 'store_brokerage_open', '1', 0, 0);
INSERT INTO `yx_system_config` VALUES (199, 'integral_full', '0', NULL, NULL);
INSERT INTO `yx_system_config` VALUES (200, 'integral_max', '200', NULL, NULL);
INSERT INTO `yx_system_config` VALUES (201, 'store_user_min_recharge', '100', 0, 0);
INSERT INTO `yx_system_config` VALUES (203, 'store_self_mention', '1', NULL, NULL);
INSERT INTO `yx_system_config` VALUES (204, 'tengxun_map_key', 'OGABZ-Y5OCF-5UWJ5-N7DHH-VFIG7-DHFEB', NULL, NULL);

-- ----------------------------
-- Table structure for yx_system_group_data
-- ----------------------------
DROP TABLE IF EXISTS `yx_system_group_data`;
CREATE TABLE `yx_system_group_data`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '组合数据详情ID',
  `group_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '对应的数据名称',
  `value` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据组对应的数据值（json数据）',
  `add_time` int(10) NOT NULL DEFAULT 0 COMMENT '添加数据时间',
  `sort` int(11) NULL DEFAULT 0 COMMENT '数据排序',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态（1：开启；2：关闭；）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 231 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '组合数据详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yx_system_group_data
-- ----------------------------
INSERT INTO `yx_system_group_data` VALUES (177, 'routine_home_banner', '{\"imageArr\":[\"https://image.dayouqiantu.cn/5c9f02a110a92.jpg\"],\"name\":\"banner\",\"id\":177,\"pic\":\"https://image.dayouqiantu.cn/5c9f02a110a92.jpg\",\"sort\":\"2\",\"url\":\"wwww\",\"status\":1}', 1571387677, 2, 1);
INSERT INTO `yx_system_group_data` VALUES (180, 'routine_home_menus', '{\"name\":\"图文分类\",\"id\":180,\"pic\":\"https://image.dayouqiantu.cn/5e4e8ebdc5641.png\",\"url\":\"/category\"}', 1571390842, 0, 1);
INSERT INTO `yx_system_group_data` VALUES (182, 'routine_home_roll_news', '{\"id\":182,\"pic\":\"https://i.loli.net/2019/10/18/DqOUgNf7wjuFpPT.png\",\"title\":\"新版本商城上线啦！\",\"url\":\"/news_list\",\"info\":\"yshop1.0上线啦\"}', 0, 0, 1);
INSERT INTO `yx_system_group_data` VALUES (183, 'routine_hot_search', '{\"id\":183,\"title\":\"照片\"}', 0, 0, 1);
INSERT INTO `yx_system_group_data` VALUES (184, 'routine_hot_search', '{\"id\":184,\"title\":\"springboot\"}', 0, 0, 1);
INSERT INTO `yx_system_group_data` VALUES (187, 'routine_home_roll_news', '{\"id\":187,\"sort\":\"4\",\"url\":\"/news_list\",\"info\":\"springboot2+JPA+Mybatisplus商城系统\",\"status\":1}', 1572086163, 4, 1);
INSERT INTO `yx_system_group_data` VALUES (188, 'routine_hot_search', '{\"id\":188,\"sort\":\"5\",\"title\":\"打印\",\"status\":1}', 1572086172, 5, 1);
INSERT INTO `yx_system_group_data` VALUES (189, 'routine_my_menus', '{\"name\":\"会员中心\",\"id\":189,\"pic\":\"https://image.dayouqiantu.cn/5dec896eeb25a.png\",\"url\":\"/user/vip\"}', 1572087722, 0, 1);
INSERT INTO `yx_system_group_data` VALUES (190, 'routine_my_menus', '{\"name\":\"优惠券\",\"id\":190,\"pic\":\"https://image.dayouqiantu.cn/5db428984d64d.png\",\"url\":\"/user/user_coupon\"}', 0, 0, 1);
INSERT INTO `yx_system_group_data` VALUES (191, 'routine_my_menus', '{\"imageArr\":[\"https://image.dayouqiantu.cn/5db428a8d3ab0.png\"],\"name\":\"收藏商品\",\"id\":191,\"pic\":\"https://image.dayouqiantu.cn/5db428a8d3ab0.png\",\"sort\":\"3\",\"url\":\"/collection\",\"status\":1}', 0, 3, 1);
INSERT INTO `yx_system_group_data` VALUES (192, 'routine_my_menus', '{\"name\":\"地址管理\",\"id\":192,\"pic\":\"https://image.dayouqiantu.cn/5db428bd61b73.png\",\"url\":\"/user/add_manage\"}', 0, 0, 1);
INSERT INTO `yx_system_group_data` VALUES (193, 'routine_my_menus', '{\"name\":\"我的推广\",\"id\":193,\"pic\":\"https://image.dayouqiantu.cn/5db428e28dd48.png\",\"url\":\"/user/user_promotion\"}', 0, 0, 1);
INSERT INTO `yx_system_group_data` VALUES (194, 'routine_my_menus', '{\"imageArr\":[\"https://image.dayouqiantu.cn/5db42a4208c55.png\"],\"name\":\"我的余额\",\"id\":194,\"pic\":\"https://image.dayouqiantu.cn/5db42a4208c55.png\",\"sort\":4,\"url\":\"/user/account\",\"status\":1}', 0, 4, 1);
INSERT INTO `yx_system_group_data` VALUES (195, 'routine_my_menus', '{\"name\":\"我的积分\",\"id\":\"\",\"pic\":\"https://image.dayouqiantu.cn/5db428f410462.png\",\"sort\":\"\",\"url\":\"/user/integral\",\"status\":\"\"}', 0, 0, 1);
INSERT INTO `yx_system_group_data` VALUES (196, 'routine_home_menus', '{\"addTime\":\"\",\"name\":\"图文资讯\",\"id\":\"\",\"sort\":\"\",\"pic\":\"https://image.dayouqiantu.cn/5e4e93f004713.png\",\"value\":\"\",\"url\":\"/news_list\",\"status\":\"\"}', 1573109648, 0, 1);
INSERT INTO `yx_system_group_data` VALUES (197, 'routine_home_menus', '{\"name\":\"我的收藏\",\"id\":\"\",\"pic\":\"https://image.dayouqiantu.cn/5e4e939507b5e.png\",\"sort\":\"\",\"url\":\"/collection\",\"status\":\"\"}', 1573109723, 0, 1);
INSERT INTO `yx_system_group_data` VALUES (198, 'routine_home_banner', '{\"imageArr\":[\"https://image.dayouqiantu.cn/5c9f0657dda90.jpg\"],\"name\":\"222\",\"id\":198,\"pic\":\"https://image.dayouqiantu.cn/5c9f0657dda90.jpg\",\"sort\":\"1\",\"url\":\"/\",\"wxapp_url\":\"/pages/index\",\"status\":1}', 1573109952, 1, 1);
INSERT INTO `yx_system_group_data` VALUES (200, 'routine_home_menus', '{\"imageArr\":[\"https://image.dayouqiantu.cn/5db428984d64d.png\"],\"name\":\"领取优惠券\",\"id\":200,\"pic\":\"https://image.dayouqiantu.cn/5e4e93c9a8304.png\",\"sort\":\"3\",\"url\":\"/user/get_coupon\",\"status\":1}', 0, 5, 1);
INSERT INTO `yx_system_group_data` VALUES (201, 'routine_home_roll_news', '{\"id\":\"\",\"url\":\"/\",\"info\":\"new hhhh\"}', 1574047234, 0, 1);
INSERT INTO `yx_system_group_data` VALUES (202, 'routine_home_menus', '{\"name\":\"拼团活动\",\"id\":180,\"pic\":\"https://image.dayouqiantu.cn/5e4e925140b57.png\",\"url\":\"/activity/group\"}', 0, 0, 1);
INSERT INTO `yx_system_group_data` VALUES (203, 'routine_my_menus', '{\"name\":\"商户管理\",\"id\":\"\",\"pic\":\"https://image.dayouqiantu.cn/5ddb7a37d58d9.png\",\"sort\":\"\",\"url\":\"/customer/index\",\"status\":\"\"}', 0, 0, 1);
INSERT INTO `yx_system_group_data` VALUES (204, 'routine_home_roll_news', '{\"id\":204,\"url\":\"/index\",\"info\":\"新添加\",\"wxapp_url\":\"/page/index\"}', 1574681945, 0, 1);
INSERT INTO `yx_system_group_data` VALUES (205, 'sign_day_num', '{\"sign_num\":\"10\",\"id\":205,\"sort\":\"99\",\"day\":\"第一天\",\"status\":1}', 1575526887, 99, 1);
INSERT INTO `yx_system_group_data` VALUES (207, 'sign_day_num', '{\"sign_num\":\"20\",\"id\":207,\"sort\":\"88\",\"day\":\"第二天\",\"status\":1}', 1575527087, 88, 1);
INSERT INTO `yx_system_group_data` VALUES (209, 'sign_day_num', '{\"sign_num\":\"30\",\"id\":\"\",\"day\":\"第三天\"}', 1575527252, 0, 1);
INSERT INTO `yx_system_group_data` VALUES (210, 'sign_day_num', '{\"addTime\":\"\",\"sign_num\":\"40\",\"id\":\"\",\"sort\":\"\",\"value\":\"\",\"day\":\"第四天\",\"status\":\"\"}', 1575527262, 0, 1);
INSERT INTO `yx_system_group_data` VALUES (211, 'sign_day_num', '{\"addTime\":\"\",\"sign_num\":\"50\",\"id\":\"\",\"sort\":\"\",\"value\":\"\",\"day\":\"第五天\",\"status\":\"\"}', 1575527345, 0, 1);
INSERT INTO `yx_system_group_data` VALUES (212, 'sign_day_num', '{\"addTime\":\"\",\"sign_num\":\"60\",\"id\":\"\",\"sort\":\"\",\"value\":\"\",\"day\":\"第六天\",\"status\":\"\"}', 1575527354, 0, 1);
INSERT INTO `yx_system_group_data` VALUES (213, 'sign_day_num', '{\"sign_num\":\"100\",\"id\":213,\"day\":\"奖励\"}', 1575527381, 0, 1);
INSERT INTO `yx_system_group_data` VALUES (216, 'routine_home_menus', '{\"name\":\"积分签到\",\"id\":180,\"pic\":\"https://image.dayouqiantu.cn/5e4e93010a248.png\",\"url\":\"/user/sign\"}', 0, 0, 1);
INSERT INTO `yx_system_group_data` VALUES (218, 'routine_seckill_time', '{\"continued\":2,\"id\":\"\",\"time\":5}', 1576484368, 0, 1);
INSERT INTO `yx_system_group_data` VALUES (219, 'routine_seckill_time', '{\"addTime\":\"\",\"continued\":\"3\",\"id\":\"\",\"sort\":\"\",\"time\":\"7\",\"value\":\"\",\"status\":\"\"}', 1576484383, 0, 1);
INSERT INTO `yx_system_group_data` VALUES (220, 'routine_seckill_time', '{\"addTime\":\"\",\"continued\":\"2\",\"id\":\"\",\"sort\":\"\",\"time\":\"10\",\"value\":\"\",\"status\":\"\"}', 1576484404, 0, 1);
INSERT INTO `yx_system_group_data` VALUES (221, 'routine_seckill_time', '{\"addTime\":\"\",\"continued\":\"3\",\"id\":\"\",\"sort\":\"\",\"time\":\"12\",\"value\":\"\",\"status\":\"\"}', 1576484422, 0, 1);
INSERT INTO `yx_system_group_data` VALUES (222, 'routine_seckill_time', '{\"addTime\":\"\",\"continued\":\"4\",\"id\":\"\",\"sort\":\"\",\"time\":\"15\",\"value\":\"\",\"status\":\"\"}', 1576484435, 0, 1);
INSERT INTO `yx_system_group_data` VALUES (223, 'routine_seckill_time', '{\"continued\":\"2\",\"id\":223,\"time\":\"19\"}', 1576484444, 0, 1);
INSERT INTO `yx_system_group_data` VALUES (224, 'routine_home_menus', '{\"imageArr\":[\"https://image.dayouqiantu.cn/5e4e929f9be59.png\"],\"name\":\"秒杀\",\"id\":224,\"pic\":\"https://image.dayouqiantu.cn/5e4e929f9be59.png\",\"sort\":\"6\",\"url\":\"/activity/goods_seckill\",\"status\":1}', 0, 6, 1);
INSERT INTO `yx_system_group_data` VALUES (226, 'routine_home_menus', '{\"name\":\"砍价\",\"id\":226,\"pic\":\"https://image.dayouqiantu.cn/5e4e9276c608f.png\",\"url\":\"/activity/bargain\",\"wxapp_url\":\"/page/index\"}', 0, 0, 1);
INSERT INTO `yx_system_group_data` VALUES (227, 'routine_my_menus', '{\"name\":\"砍价记录\",\"id\":227,\"pic\":\"https://image.dayouqiantu.cn/5dfd7b748e053.png\",\"url\":\"/activity/bargain/record\",\"wxapp_url\":\"/page/index\"}', 0, 0, 1);
INSERT INTO `yx_system_group_data` VALUES (228, 'routine_hot_search', '{\"id\":228,\"sort\":99,\"title\":\"555\",\"status\":1}', 1581315732, 99, 1);
INSERT INTO `yx_system_group_data` VALUES (229, 'routine_home_roll_news', '{\"id\":\"\",\"sort\":0,\"url\":\"333\",\"info\":\"33\",\"wxapp_url\":\"3\",\"status\":1}', 1582895082, 0, 1);
INSERT INTO `yx_system_group_data` VALUES (230, 'routine_my_menus', '{\"imageArr\":[\"https://image.dayouqiantu.cn/5e60da498cfdd.png\"],\"name\":\"订单核销\",\"id\":230,\"pic\":\"https://image.dayouqiantu.cn/5e60da498cfdd.png\",\"sort\":0,\"url\":\"/order/order_cancellation\",\"wxapp_url\":\"\",\"status\":1}', 1583405730, 0, 1);

-- ----------------------------
-- Table structure for yx_system_store
-- ----------------------------
DROP TABLE IF EXISTS `yx_system_store`;
CREATE TABLE `yx_system_store`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '门店名称',
  `introduction` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '简介',
  `phone` char(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '手机号码',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '省市区',
  `detailed_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '详细地址',
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '门店logo',
  `latitude` char(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '纬度',
  `longitude` char(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '经度',
  `valid_time` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '核销有效日期',
  `day_time` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '每日营业开关时间',
  `add_time` int(11) NOT NULL DEFAULT 0 COMMENT '添加时间',
  `is_show` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否显示',
  `is_del` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
  `day_time_end` datetime(0) NULL DEFAULT NULL,
  `day_time_start` datetime(0) NULL DEFAULT NULL,
  `valid_time_end` datetime(0) NULL DEFAULT NULL,
  `valid_time_start` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `phone`(`phone`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '门店自提' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yx_system_store
-- ----------------------------
INSERT INTO `yx_system_store` VALUES (3, 'yshop店铺', 'springboot商城', '15136175234', '河南省漯河市', '', 'https://image.dayouqiantu.cn/noMeal_tt.png', '33.54528', '113.9202', '2020-03-09 - 2020-04-30', '11:33:49 - 19:33:49', 1583300059, 0, 0, '2020-03-04 19:33:49', '2020-03-04 11:33:49', '2020-04-30 00:00:00', '2020-03-09 00:00:00');

-- ----------------------------
-- Table structure for yx_system_user_level
-- ----------------------------
DROP TABLE IF EXISTS `yx_system_user_level`;
CREATE TABLE `yx_system_user_level`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mer_id` int(11) NOT NULL DEFAULT 0 COMMENT '商户id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '会员名称',
  `money` decimal(8, 2) NOT NULL DEFAULT 0.00 COMMENT '购买金额',
  `valid_date` int(11) NOT NULL DEFAULT 0 COMMENT '有效时间',
  `is_forever` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否为永久会员',
  `is_pay` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否购买,1=购买,0=不购买',
  `is_show` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否显示 1=显示,0=隐藏',
  `grade` int(11) NOT NULL DEFAULT 0 COMMENT '会员等级',
  `discount` decimal(8, 2) NOT NULL DEFAULT 0.00 COMMENT '享受折扣',
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '会员卡背景',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '会员图标',
  `explain` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '说明',
  `add_time` int(11) NOT NULL DEFAULT 0 COMMENT '添加时间',
  `is_del` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除.1=删除,0=未删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '设置用户等级表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yx_system_user_level
-- ----------------------------
INSERT INTO `yx_system_user_level` VALUES (1, 0, '普通会员', 20.00, 0, 1, 0, 1, 1, 99.00, 'http://pic.dayouqiantu.cn/5c9ccca8cd632.jpg', 'http://pic.dayouqiantu.cn/5c9ccca8bc1e0.png', '普通会员', 1553824559, 0);
INSERT INTO `yx_system_user_level` VALUES (2, 0, '青铜会员', 0.00, 0, 1, 0, 1, 2, 98.00, 'http://pic.dayouqiantu.cn/5c9ccca904016.jpg', 'http://pic.dayouqiantu.cn/5c9ccca8f0a30.png', '青铜会员', 1553824639, 0);
INSERT INTO `yx_system_user_level` VALUES (3, 0, '黄铜会员', 0.00, 0, 1, 0, 1, 3, 95.00, 'http://pic.dayouqiantu.cn/5c9ccca8b27f1.jpg', 'http://pic.dayouqiantu.cn/5c9ccca8e9365.png', '黄铜会员', 1553824742, 0);
INSERT INTO `yx_system_user_level` VALUES (4, 0, '白银会员', 0.00, 0, 1, 0, 1, 4, 94.00, 'http://pic.dayouqiantu.cn/5c9ccca8d6ae1.jpg', 'http://pic.dayouqiantu.cn/5c9ccca8a27f0.png', '白银会员', 1553824797, 0);
INSERT INTO `yx_system_user_level` VALUES (5, 0, '黄金会员', 0.00, 0, 1, 0, 1, 5, 90.00, 'http://pic.dayouqiantu.cn/5c9ccca8b27f1.jpg', 'http://pic.dayouqiantu.cn/5c9ccca8aa5b9.png', '黄金会员', 1553824837, 0);
INSERT INTO `yx_system_user_level` VALUES (6, 0, '钻石会员', 0.00, 0, 1, 0, 1, 6, 88.00, 'http://pic.dayouqiantu.cn/5c9ccca8dfe16.jpg', 'http://pic.dayouqiantu.cn/5c9ccca90d2d3.png', '钻石会员', 1553824871, 1);
INSERT INTO `yx_system_user_level` VALUES (7, 0, '44', 0.00, 4, 1, 0, 1, 4, 4.00, 'http://localhost:8000/file/pic/list_16-20200110120838173.png', 'http://localhost:8000/file/pic/list_32-20200109100213309.png', '4', 1578651584, 0);

-- ----------------------------
-- Table structure for yx_system_user_task
-- ----------------------------
DROP TABLE IF EXISTS `yx_system_user_task`;
CREATE TABLE `yx_system_user_task`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '任务名称',
  `real_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '配置原名',
  `task_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '任务类型',
  `number` int(11) NOT NULL DEFAULT 0 COMMENT '限定数',
  `level_id` int(11) NOT NULL DEFAULT 0 COMMENT '等级id',
  `sort` int(11) NOT NULL DEFAULT 0 COMMENT '排序',
  `is_show` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否显示',
  `is_must` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否务必达成任务,1务必达成,0=满足其一',
  `illustrate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '任务说明',
  `add_time` int(11) NOT NULL DEFAULT 0 COMMENT '新增时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '等级任务设置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yx_system_user_task
-- ----------------------------
INSERT INTO `yx_system_user_task` VALUES (1, '满足积分20分', '积分数', 'SatisfactionIntegral', 20, 1, 0, 1, 1, '', 1553827616);
INSERT INTO `yx_system_user_task` VALUES (2, '消费满100元', '消费金额', 'ConsumptionAmount', 100, 1, 0, 1, 1, '', 1553827625);
INSERT INTO `yx_system_user_task` VALUES (3, '满足积分200分', '积分数', 'SatisfactionIntegral', 200, 2, 0, 1, 1, '', 1553827638);
INSERT INTO `yx_system_user_task` VALUES (4, '累计签到20天', '累计签到', 'CumulativeAttendance', 20, 2, 0, 1, 1, '', 1553827681);
INSERT INTO `yx_system_user_task` VALUES (5, '满足积分500分', '积分数', 'SatisfactionIntegral', 500, 3, 0, 1, 1, '', 1553827695);
INSERT INTO `yx_system_user_task` VALUES (6, '累计签到30天', '累计签到', 'CumulativeAttendance', 30, 3, 0, 1, 1, '', 1553827703);
INSERT INTO `yx_system_user_task` VALUES (7, '满足积分1000分', '积分数', 'SatisfactionIntegral', 1000, 4, 0, 1, 1, '', 1553827731);
INSERT INTO `yx_system_user_task` VALUES (8, '累计签到10天', '累计签到', 'CumulativeAttendance', 10, 4, 0, 1, 1, '', 1553827740);
INSERT INTO `yx_system_user_task` VALUES (9, '满足积分1200分', '积分数', 'SatisfactionIntegral', 1200, 5, 0, 1, 1, '', 1553827759);
INSERT INTO `yx_system_user_task` VALUES (10, '累计签到60天', '累计签到', 'CumulativeAttendance', 60, 5, 0, 1, 1, '', 1553827768);
INSERT INTO `yx_system_user_task` VALUES (11, '消费满10000元', '消费次数', 'ConsumptionAmount', 10000, 5, 0, 1, 1, '', 1553827776);
INSERT INTO `yx_system_user_task` VALUES (12, '满足积分2000分', '积分数', 'SatisfactionIntegral', 2000, 6, 0, 1, 1, '', 1553827791);
INSERT INTO `yx_system_user_task` VALUES (13, '消费满10000元', '消费次数', 'ConsumptionAmount', 10000, 6, 0, 1, 1, '', 1553827803);
INSERT INTO `yx_system_user_task` VALUES (14, '累计签到100天', '累计签到', 'CumulativeAttendance', 100, 6, 0, 1, 1, '', 1553827814);
INSERT INTO `yx_system_user_task` VALUES (15, '消费满1000元', '消费金额', 'ConsumptionAmount', 1000, 4, 0, 1, 1, '', 1575456513);
INSERT INTO `yx_system_user_task` VALUES (16, '累计签到2天', '累计签到', 'CumulativeAttendance', 2, 1, 0, 1, 1, '', 1575456576);
INSERT INTO `yx_system_user_task` VALUES (17, '消费满100元', '消费次数', 'ConsumptionAmount', 100, 2, 0, 1, 1, '', 1575456612);
INSERT INTO `yx_system_user_task` VALUES (18, '消费满1000元', '消费金额', 'ConsumptionAmount', 1000, 3, 0, 1, 1, '', 1575456692);

-- ----------------------------
-- Table structure for yx_user
-- ----------------------------
DROP TABLE IF EXISTS `yx_user`;
CREATE TABLE `yx_user`  (
  `uid` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户账户(跟accout一样)',
  `account` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户密码（跟pwd）',
  `pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `real_name` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '真实姓名',
  `birthday` int(11) NULL DEFAULT 0 COMMENT '生日',
  `card_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '身份证号码',
  `mark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '用户备注',
  `partner_id` int(11) NULL DEFAULT 0 COMMENT '合伙人id',
  `group_id` int(11) NULL DEFAULT 0 COMMENT '用户分组id',
  `nickname` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `avatar` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `phone` char(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `add_time` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '添加时间',
  `add_ip` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '添加ip',
  `last_time` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后一次登录时间',
  `last_ip` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '最后一次登录ip',
  `now_money` decimal(8, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '用户余额',
  `brokerage_price` decimal(8, 2) NOT NULL DEFAULT 0.00 COMMENT '佣金金额',
  `integral` decimal(8, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '用户剩余积分',
  `sign_num` int(11) NOT NULL DEFAULT 0 COMMENT '连续签到天数',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '1为正常，0为禁止',
  `level` tinyint(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '等级',
  `spread_uid` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '推广元id',
  `spread_time` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '推广员关联时间',
  `user_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户类型',
  `is_promoter` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否为推广员',
  `pay_count` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '用户购买次数',
  `spread_count` int(11) NULL DEFAULT 0 COMMENT '下级人数',
  `clean_time` int(11) NULL DEFAULT 0 COMMENT '清理会员时间',
  `addres` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '详细地址',
  `adminid` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '管理员编号 ',
  `login_type` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户登陆类型，h5,wechat,routine',
  PRIMARY KEY (`uid`) USING BTREE,
  INDEX `account`(`account`) USING BTREE,
  INDEX `spreaduid`(`spread_uid`) USING BTREE,
  INDEX `level`(`level`) USING BTREE,
  INDEX `status`(`status`) USING BTREE,
  INDEX `is_promoter`(`is_promoter`) USING BTREE,
  INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yx_user
-- ----------------------------
INSERT INTO `yx_user` VALUES (11, 'hupeng', 'hupeng', '$2a$10$fP.426qKaTmix50Oln8L.uav55gELhAd0Eg66Av4oG86u8km7D/Ky', 'e10adc3949ba59abbe56e057f20f883e', '', 0, '', '', 0, 0, '会敲代码的喵88', 'http://localhost:8009/api/file/pic/list_30-20200207062920744.png', '15136175246', 1573120584, '', 1573120584, '', 8268.50, 26.86, 0.00, 1, 1, 0, 0, 1, 'h5', 0, 23, 1, 0, '', 1, '');
INSERT INTO `yx_user` VALUES (12, '15136175246', 'hupeng2', '$2a$10$fP.426qKaTmix50Oln8L.uav55gELhAd0Eg66Av4oG86u8km7D/Ky', 'e10adc3949ba59abbe56e057f20f883e', '', 0, '', '', 0, 0, '会敲代码的喵2', 'https://image.dayouqiantu.cn/5dc2c7f3a104c.png', '15136175246', 1573120881, '', 1573120881, '', 8766.20, 28.00, 70.51, 2, 1, 1, 11, 1582896799, 'h5', 0, 20, 0, 0, '', 1, '');
INSERT INTO `yx_user` VALUES (14, '15136175247', '15136175247', '$2a$10$fP.426qKaTmix50Oln8L.uav55gELhAd0Eg66Av4oG86u8km7D/Ky', 'e10adc3949ba59abbe56e057f20f883e', '', 0, '', '', 0, 0, '15136175247', 'https://image.dayouqiantu.cn/5dc2c7f3a104c.png', '15136175247', 1573612696, '', 1573612696, '', 9200.00, 0.00, 2.00, 0, 1, 0, 0, 0, 'h5', 0, 5, 0, 0, '', 0, '');
INSERT INTO `yx_user` VALUES (15, '15136175249', '15136175249', '$2a$10$fP.426qKaTmix50Oln8L.uav55gELhAd0Eg66Av4oG86u8km7D/Ky', '$2a$10$IG/fc8idkW35ezw7VtiM.OjUU1fvS2d/Bw7TmU76UUTbd5/4eCd9a', '', 0, '', '', 0, 0, '15136175249', 'https://image.dayouqiantu.cn/5dc2c7f3a104c.png', '15136175249', 1578839477, '', 1578839477, '', 0.00, 0.00, 0.00, 0, 1, 0, 0, 0, 'h5', 0, 0, 0, 0, '', 0, '');

-- ----------------------------
-- Table structure for yx_user_address
-- ----------------------------
DROP TABLE IF EXISTS `yx_user_address`;
CREATE TABLE `yx_user_address`  (
  `id` mediumint(8) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户地址id',
  `uid` int(10) UNSIGNED NOT NULL COMMENT '用户id',
  `real_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '收货人姓名',
  `phone` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '收货人电话',
  `province` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '收货人所在省',
  `city` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '收货人所在市',
  `district` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '收货人所在区',
  `detail` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '收货人详细地址',
  `post_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '邮编',
  `longitude` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '经度',
  `latitude` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '纬度',
  `is_default` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否默认',
  `is_del` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除',
  `add_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE,
  INDEX `is_default`(`is_default`) USING BTREE,
  INDEX `is_del`(`is_del`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户地址表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yx_user_address
-- ----------------------------
INSERT INTO `yx_user_address` VALUES (9, 8, '胡鹏', '15136175246', '山西', '大同市', '灵丘县', '999', '', '0', '0', 1, 0, 1573119435);
INSERT INTO `yx_user_address` VALUES (10, 11, '胡鹏', '15139175246', '重庆', '荣昌县', '广顺镇', '999', '', '0', '0', 1, 0, 1573120628);
INSERT INTO `yx_user_address` VALUES (11, 12, 'hu', '15136175246', '上海', '闸北区', '城区', '99999', '', '0', '0', 1, 0, 1573123595);
INSERT INTO `yx_user_address` VALUES (12, 14, 'zhang', '15136175247', '上海', '静安区', '城区', '999', '', '0', '0', 1, 0, 1573612804);

-- ----------------------------
-- Table structure for yx_user_bill
-- ----------------------------
DROP TABLE IF EXISTS `yx_user_bill`;
CREATE TABLE `yx_user_bill`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户账单id',
  `uid` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户uid',
  `link_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '关联id',
  `pm` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '0 = 支出 1 = 获得',
  `title` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '账单标题',
  `category` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '明细种类',
  `type` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '明细类型',
  `number` decimal(8, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '明细数字',
  `balance` decimal(8, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '剩余',
  `mark` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '备注',
  `add_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '添加时间',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '0 = 带确定 1 = 有效 -1 = 无效',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `openid`(`uid`) USING BTREE,
  INDEX `status`(`status`) USING BTREE,
  INDEX `add_time`(`add_time`) USING BTREE,
  INDEX `pm`(`pm`) USING BTREE,
  INDEX `type`(`category`, `type`, `link_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 101 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户账单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yx_user_bill
-- ----------------------------
INSERT INTO `yx_user_bill` VALUES (3, 3, '8', 0, '购买商品', 'now_money', 'pay_product', 100.00, 10000.00, '余额支付', 1572416780, 1);
INSERT INTO `yx_user_bill` VALUES (4, 3, '9', 0, '购买商品', 'now_money', 'pay_product', 300.00, 9900.00, '余额支付', 1572416917, 1);
INSERT INTO `yx_user_bill` VALUES (5, 3, '10', 0, '购买商品', 'now_money', 'pay_product', 100.00, 9600.00, '余额支付', 1572425699, 1);
INSERT INTO `yx_user_bill` VALUES (6, 3, '11', 0, '购买商品', 'now_money', 'pay_product', 100.00, 9500.00, '余额支付', 1572427199, 1);
INSERT INTO `yx_user_bill` VALUES (7, 3, '11', 1, '购买商品赠送积分', 'integral', 'gain', 10.00, 0.00, '购买商品赠送', 1572693386, 1);
INSERT INTO `yx_user_bill` VALUES (8, 3, '10', 1, '商品退款', 'now_money', 'pay_product_refund', 100.00, 9500.00, '订单退款到余额', 1573040186, 1);
INSERT INTO `yx_user_bill` VALUES (9, 3, '12', 0, '购买商品', 'now_money', 'pay_product', 100.00, 9500.00, '余额支付', 1573040704, 1);
INSERT INTO `yx_user_bill` VALUES (10, 12, '12', 1, '购买商品赠送积分', 'integral', 'gain', 10.00, 10.00, '购买商品赠送', 1573041037, 1);
INSERT INTO `yx_user_bill` VALUES (11, 3, '26', 0, '购买商品', 'now_money', 'pay_product', 0.20, 9400.00, '余额支付', 1573119034, 1);
INSERT INTO `yx_user_bill` VALUES (12, 12, '34', 0, '购买商品', 'now_money', 'pay_product', 0.20, 10000.00, '余额支付', 1573124900, 1);
INSERT INTO `yx_user_bill` VALUES (13, 12, '33', 0, '购买商品', 'now_money', 'pay_product', 0.20, 9999.80, '余额支付', 1573124914, 1);
INSERT INTO `yx_user_bill` VALUES (14, 12, '35', 0, '购买商品', 'now_money', 'pay_product', 119.00, 9999.60, '余额支付', 1573380051, 1);
INSERT INTO `yx_user_bill` VALUES (15, 12, '105e06edb7bf4113aff646a45b13abaa', 0, '积分抵扣', 'integral', 'deduction', 10.00, 10.00, '购买商品使用', 1573381947, 1);
INSERT INTO `yx_user_bill` VALUES (16, 12, '36', 0, '购买商品', 'now_money', 'pay_product', 160.00, 9880.60, '余额支付', 1573381947, 1);
INSERT INTO `yx_user_bill` VALUES (17, 14, '37', 0, '购买商品', 'now_money', 'pay_product', 120.00, 10000.00, '余额支付', 1573612822, 1);
INSERT INTO `yx_user_bill` VALUES (18, 14, '37', 1, '购买商品赠送积分', 'integral', 'gain', 1.00, 0.00, '购买商品赠送', 1573613426, 1);
INSERT INTO `yx_user_bill` VALUES (19, 12, '37', 1, '获得推广佣金', 'now_money', 'brokerage', 14.00, 14.00, '15136175247成功消费120.00元,奖励推广佣金14.0', 1573613427, 1);
INSERT INTO `yx_user_bill` VALUES (20, 11, '37', 1, '获得推广佣金', 'now_money', 'brokerage', 6.00, 6.00, '二级推广人15136175247成功消费120.00元,奖励推广佣金6.0', 1573613427, 1);
INSERT INTO `yx_user_bill` VALUES (21, 11, '1', 0, '佣金提现', 'now_money', 'extract', 1.00, 5.00, '使用微信提现1元', 1573648445, 1);
INSERT INTO `yx_user_bill` VALUES (25, 11, '1', 1, '提现失败', 'now_money', 'extract', 1.00, 6.00, '提现失败,退回佣金1元', 1573710526, 1);
INSERT INTO `yx_user_bill` VALUES (26, 14, '84892a2626a341939dd05cd7f2adff9e', 0, '积分抵扣', 'integral', 'deduction', 1.00, 1.00, '购买商品使用', 1573888321, 1);
INSERT INTO `yx_user_bill` VALUES (27, 14, '39', 1, '积分回退', 'integral', 'deduction', 1.00, 0.00, '购买商品失败,回退积分', 1573888707, 1);
INSERT INTO `yx_user_bill` VALUES (28, 14, '42', 0, '购买商品', 'now_money', 'pay_product', 120.00, 9880.00, '余额支付', 1573979565, 1);
INSERT INTO `yx_user_bill` VALUES (29, 14, '61', 0, '购买商品', 'now_money', 'pay_product', 120.00, 9760.00, '余额支付', 1573991796, 1);
INSERT INTO `yx_user_bill` VALUES (30, 14, '67', 0, '购买商品', 'now_money', 'pay_product', 120.00, 9640.00, '余额支付', 1573996204, 1);
INSERT INTO `yx_user_bill` VALUES (31, 14, '68', 0, '购买商品', 'now_money', 'pay_product', 120.00, 9520.00, '余额支付', 1573996429, 1);
INSERT INTO `yx_user_bill` VALUES (32, 14, '68', 1, '购买商品赠送积分', 'integral', 'gain', 1.00, 1.00, '购买商品赠送', 1574048275, 1);
INSERT INTO `yx_user_bill` VALUES (33, 12, '68', 1, '获得推广佣金', 'now_money', 'brokerage', 14.00, 28.00, '15136175247成功消费120.00元,奖励推广佣金14.0', 1574048275, 1);
INSERT INTO `yx_user_bill` VALUES (34, 11, '68', 1, '获得推广佣金', 'now_money', 'brokerage', 6.00, 12.00, '二级推广人15136175247成功消费120.00元,奖励推广佣金6.0', 1574048275, 1);
INSERT INTO `yx_user_bill` VALUES (35, 12, '89', 0, '购买商品', 'now_money', 'pay_product', 120.00, 9720.60, '余额支付', 1574221758, 1);
INSERT INTO `yx_user_bill` VALUES (36, 12, '90', 0, '购买商品', 'now_money', 'pay_product', 120.00, 9600.60, '余额支付', 1574243179, 1);
INSERT INTO `yx_user_bill` VALUES (37, 12, '91', 0, '购买商品', 'now_money', 'pay_product', 120.00, 9480.60, '余额支付', 1574244175, 1);
INSERT INTO `yx_user_bill` VALUES (38, 12, '92', 0, '购买商品', 'now_money', 'pay_product', 1.00, 9360.60, '余额支付', 1574327309, 1);
INSERT INTO `yx_user_bill` VALUES (39, 11, '93', 0, '购买商品', 'now_money', 'pay_product', 1.00, 10000.00, '余额支付', 1574329404, 1);
INSERT INTO `yx_user_bill` VALUES (40, 12, '96', 0, '购买商品', 'now_money', 'pay_product', 1.00, 9359.60, '余额支付', 1574425447, 1);
INSERT INTO `yx_user_bill` VALUES (41, 12, '91', 1, '商品退款', 'now_money', 'pay_product_refund', 120.00, 9478.60, '订单退款到余额', 1574757130, 1);
INSERT INTO `yx_user_bill` VALUES (42, 12, '91', 1, '商品退款', 'now_money', 'pay_product_refund', 120.00, 9598.60, '订单退款到余额', 1574757363, 1);
INSERT INTO `yx_user_bill` VALUES (43, 11, '0', 1, '充值', 'now_money', 'recharge', 10.00, 9410.00, '系统增加了10.0余额', 1575446946, 1);
INSERT INTO `yx_user_bill` VALUES (44, 14, '0', 0, '系统增加余额', 'now_money', 'system_sub', 10.00, 9400.00, '系统扣除了10.0余额', 1575447084, 1);
INSERT INTO `yx_user_bill` VALUES (45, 12, '0', 1, '签到奖励', 'integral', 'sign', 10.00, 0.00, '', 1575549244, 1);
INSERT INTO `yx_user_bill` VALUES (46, 12, '0', 1, '签到奖励', 'integral', 'sign', 10.00, 10.00, '', 1575684021, 1);
INSERT INTO `yx_user_bill` VALUES (47, 12, '99', 0, '购买商品', 'now_money', 'pay_product', 118.80, 9598.60, '余额支付', 1575771514, 1);
INSERT INTO `yx_user_bill` VALUES (48, 12, '0', 1, '签到奖励', 'integral', 'sign', 20.00, 20.00, '', 1575771932, 1);
INSERT INTO `yx_user_bill` VALUES (49, 11, '96', 1, '获得推广佣金', 'now_money', 'brokerage', 0.70, 12.70, '会敲代码的喵2成功消费1.00元,奖励推广佣金0.7', 1575868078, 1);
INSERT INTO `yx_user_bill` VALUES (50, 12, '100', 0, '购买商品', 'now_money', 'pay_product', 118.80, 9479.80, '余额支付', 1576234308, 1);
INSERT INTO `yx_user_bill` VALUES (51, 12, '91', 1, '商品退款', 'now_money', 'pay_product_refund', 120.00, 9481.00, '订单退款到余额', 1576286856, 1);
INSERT INTO `yx_user_bill` VALUES (52, 12, '101', 0, '购买商品', 'now_money', 'pay_product', 1.00, 9481.00, '余额支付', 1576565376, 1);
INSERT INTO `yx_user_bill` VALUES (53, 12, '0', 1, '签到奖励', 'integral', 'sign', 10.00, 40.00, '', 1577026465, 1);
INSERT INTO `yx_user_bill` VALUES (54, 12, '102', 0, '购买商品', 'now_money', 'pay_product', 1.00, 9480.00, '余额支付', 1577086164, 1);
INSERT INTO `yx_user_bill` VALUES (55, 12, '0', 1, '签到奖励', 'integral', 'sign', 20.00, 50.00, '', 1577099436, 1);
INSERT INTO `yx_user_bill` VALUES (56, 12, '103', 0, '购买商品', 'now_money', 'pay_product', 118.80, 9479.00, '余额支付', 1577345071, 1);
INSERT INTO `yx_user_bill` VALUES (57, 12, '25f5693f8e82419bba4f581af126dc47', 0, '积分抵扣', 'integral', 'deduction', 0.50, 70.00, '购买商品使用', 1577442136, 1);
INSERT INTO `yx_user_bill` VALUES (58, 12, '104', 0, '购买商品', 'now_money', 'pay_product', 0.00, 9360.20, '余额支付', 1577442136, 1);
INSERT INTO `yx_user_bill` VALUES (59, 12, '104', 1, '商品退款', 'now_money', 'pay_product_refund', 0.00, 9360.20, '订单退款到余额', 1577586525, 1);
INSERT INTO `yx_user_bill` VALUES (60, 12, '105', 0, '购买商品', 'now_money', 'pay_product', 118.80, 9360.20, '余额支付', 1577611680, 1);
INSERT INTO `yx_user_bill` VALUES (61, 12, '106', 0, '购买商品', 'now_money', 'pay_product', 118.80, 9241.40, '余额支付', 1577774706, 1);
INSERT INTO `yx_user_bill` VALUES (62, 14, '0', 1, '系统增加余额', 'now_money', 'system_add', 0.00, 9400.00, '系统增加了0.0余额', 1578321034, 1);
INSERT INTO `yx_user_bill` VALUES (63, 12, '108', 0, '购买商品', 'now_money', 'pay_product', 118.80, 9122.60, '余额支付', 1578386338, 1);
INSERT INTO `yx_user_bill` VALUES (64, 11, '1', 1, '提现失败', 'now_money', 'extract', 1.00, 13.70, '提现失败,退回佣金1元', 1578455379, 1);
INSERT INTO `yx_user_bill` VALUES (65, 12, '110', 0, '购买商品', 'now_money', 'pay_product', 118.80, 9003.80, '余额支付', 1578482250, 1);
INSERT INTO `yx_user_bill` VALUES (66, 12, '99', 1, '购买商品赠送积分', 'integral', 'gain', 1.00, 69.51, '购买商品赠送', 1578733510, 1);
INSERT INTO `yx_user_bill` VALUES (67, 11, '99', 1, '获得推广佣金', 'now_money', 'brokerage', 13.16, 26.86, '会敲代码的喵2成功消费118.80元,奖励推广佣金13.16', 1578733510, 1);
INSERT INTO `yx_user_bill` VALUES (68, 12, '111', 0, '购买商品', 'now_money', 'pay_product', 118.80, 8885.00, '余额支付', 1578839691, 1);
INSERT INTO `yx_user_bill` VALUES (69, 15, '0', 0, '系统减少余额', 'now_money', 'system_sub', 10.00, 0.00, '系统扣除了10.0余额', 1580440677, 1);
INSERT INTO `yx_user_bill` VALUES (70, 14, '0', 0, '系统减少余额', 'now_money', 'system_sub', 100.00, 9300.00, '系统扣除了100.0余额', 1580440694, 1);
INSERT INTO `yx_user_bill` VALUES (71, 14, '0', 0, '系统减少余额', 'now_money', 'recharge', 100.00, 9200.00, '系统扣除了100.0余额', 1580440702, 1);
INSERT INTO `yx_user_bill` VALUES (72, 12, '104', 1, '商品退款', 'now_money', 'pay_product_refund', 0.00, 8766.20, '订单退款到余额', 1580440738, 1);
INSERT INTO `yx_user_bill` VALUES (73, 12, '104', 1, '商品退款', 'now_money', 'pay_product_refund', 0.00, 8766.20, '订单退款到余额', 1580440967, 1);
INSERT INTO `yx_user_bill` VALUES (74, 11, '112', 0, '购买商品', 'now_money', 'pay_product', 15.50, 9999.00, '余额支付', 1581586206, 1);
INSERT INTO `yx_user_bill` VALUES (75, 11, '113', 0, '购买商品', 'now_money', 'pay_product', 11.00, 9983.50, '余额支付', 1582080107, 1);
INSERT INTO `yx_user_bill` VALUES (76, 11, '114', 0, '购买商品', 'now_money', 'pay_product', 11.00, 9972.50, '余额支付', 1582080948, 1);
INSERT INTO `yx_user_bill` VALUES (77, 11, '115', 0, '购买商品', 'now_money', 'pay_product', 11.00, 9961.50, '余额支付', 1582081730, 1);
INSERT INTO `yx_user_bill` VALUES (78, 11, '116', 0, '购买商品', 'now_money', 'pay_product', 11.00, 9950.50, '余额支付', 1582081800, 1);
INSERT INTO `yx_user_bill` VALUES (79, 11, '117', 0, '购买商品', 'now_money', 'pay_product', 120.00, 9939.50, '余额支付', 1582704871, 1);
INSERT INTO `yx_user_bill` VALUES (80, 11, '0', 1, '签到奖励', 'integral', 'sign', 10.00, 0.00, '', 1582781884, 1);
INSERT INTO `yx_user_bill` VALUES (81, 11, '118', 0, '购买商品', 'now_money', 'pay_product', 15.50, 9819.50, '余额支付', 1582809322, 1);
INSERT INTO `yx_user_bill` VALUES (82, 11, '120', 0, '购买商品', 'now_money', 'pay_product', 120.00, 9804.00, '余额支付', 1582810596, 1);
INSERT INTO `yx_user_bill` VALUES (83, 11, '120', 1, '购买商品赠送积分', 'integral', 'gain', 1.00, 10.00, '购买商品赠送', 1582812352, 1);
INSERT INTO `yx_user_bill` VALUES (84, 11, '8f93eb694f7142c5aeddd8acc6876eaf', 0, '积分抵扣', 'integral', 'deduction', 100.00, 1100.00, '购买商品使用', 1582970347, 1);
INSERT INTO `yx_user_bill` VALUES (85, 11, '122', 0, '购买商品', 'now_money', 'pay_product', 140.00, 9684.00, '余额支付', 1582970348, 1);
INSERT INTO `yx_user_bill` VALUES (86, 11, 'ac31a1bb85f14cfe92c16169495c7db7', 0, '积分抵扣', 'integral', 'deduction', 100.00, 1000.00, '购买商品使用', 1582970945, 1);
INSERT INTO `yx_user_bill` VALUES (87, 11, '123', 0, '购买商品', 'now_money', 'pay_product', 140.00, 9544.00, '余额支付', 1582970946, 1);
INSERT INTO `yx_user_bill` VALUES (88, 11, 'a51e7f23efa8430499f45ad76d45d696', 0, '积分抵扣', 'integral', 'deduction', 120.00, 900.00, '购买商品使用', 1582971169, 1);
INSERT INTO `yx_user_bill` VALUES (89, 11, '124', 0, '购买商品', 'now_money', 'pay_product', 0.00, 9404.00, '余额支付', 1582971170, 1);
INSERT INTO `yx_user_bill` VALUES (90, 11, 'f3ced8276fb64d1a90fe03c5efbad68a', 0, '积分抵扣', 'integral', 'deduction', 100.00, 100.00, '购买商品使用', 1582971472, 1);
INSERT INTO `yx_user_bill` VALUES (91, 11, '125', 0, '购买商品', 'now_money', 'pay_product', 20.00, 9404.00, '余额支付', 1582971472, 1);
INSERT INTO `yx_user_bill` VALUES (92, 11, '126', 0, '购买商品', 'now_money', 'pay_product', 120.00, 9384.00, '余额支付', 1582990630, 1);
INSERT INTO `yx_user_bill` VALUES (93, 11, '127', 0, '购买商品', 'now_money', 'pay_product', 120.00, 9264.00, '余额支付', 1583392756, 1);
INSERT INTO `yx_user_bill` VALUES (94, 11, '128', 0, '购买商品', 'now_money', 'pay_product', 120.00, 9144.00, '余额支付', 1583393731, 1);
INSERT INTO `yx_user_bill` VALUES (95, 11, '129', 0, '购买商品', 'now_money', 'pay_product', 120.00, 9024.00, '余额支付', 1583394584, 1);
INSERT INTO `yx_user_bill` VALUES (96, 11, '130', 0, '购买商品', 'now_money', 'pay_product', 130.00, 8904.00, '余额支付', 1583399210, 1);
INSERT INTO `yx_user_bill` VALUES (97, 11, '131', 0, '购买商品', 'now_money', 'pay_product', 120.00, 8774.00, '余额支付', 1583403288, 1);
INSERT INTO `yx_user_bill` VALUES (98, 11, '132', 0, '购买商品', 'now_money', 'pay_product', 120.00, 8654.00, '余额支付', 1583410828, 1);
INSERT INTO `yx_user_bill` VALUES (99, 11, '133', 0, '购买商品', 'now_money', 'pay_product', 130.00, 8534.00, '余额支付', 1583416032, 1);
INSERT INTO `yx_user_bill` VALUES (100, 11, '135', 0, '购买商品', 'now_money', 'pay_product', 135.50, 8404.00, '余额支付', 1583486513, 1);

-- ----------------------------
-- Table structure for yx_user_enter
-- ----------------------------
DROP TABLE IF EXISTS `yx_user_enter`;
CREATE TABLE `yx_user_enter`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '商户申请ID',
  `uid` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户ID',
  `province` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商户所在省',
  `city` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商户所在市',
  `district` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商户所在区',
  `address` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商户详细地址',
  `merchant_name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商户名称',
  `link_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `link_tel` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商户电话',
  `charter` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商户证书',
  `add_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '添加时间',
  `apply_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '审核时间',
  `success_time` int(11) NOT NULL COMMENT '通过时间',
  `fail_message` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '未通过原因',
  `fail_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '未通过时间',
  `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '-1 审核未通过 0未审核 1审核通过',
  `is_lock` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '0 = 开启 1= 关闭',
  `is_del` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uid`(`uid`) USING BTREE,
  INDEX `province`(`province`, `city`, `district`) USING BTREE,
  INDEX `is_lock`(`is_lock`) USING BTREE,
  INDEX `is_del`(`is_del`) USING BTREE,
  INDEX `status`(`status`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商户申请表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for yx_user_extract
-- ----------------------------
DROP TABLE IF EXISTS `yx_user_extract`;
CREATE TABLE `yx_user_extract`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `uid` int(10) UNSIGNED NULL DEFAULT NULL,
  `real_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `extract_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'bank' COMMENT 'bank = 银行卡 alipay = 支付宝wx=微信',
  `bank_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '银行卡',
  `bank_address` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '开户地址',
  `alipay_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '支付宝账号',
  `extract_price` decimal(8, 2) UNSIGNED NULL DEFAULT 0.00 COMMENT '提现金额',
  `mark` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `balance` decimal(8, 2) UNSIGNED NULL DEFAULT 0.00,
  `fail_msg` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '无效原因',
  `fail_time` int(10) UNSIGNED NULL DEFAULT NULL,
  `add_time` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '添加时间',
  `status` tinyint(2) NULL DEFAULT 0 COMMENT '-1 未通过 0 审核中 1 已提现',
  `wechat` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信号',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `extract_type`(`extract_type`) USING BTREE,
  INDEX `status`(`status`) USING BTREE,
  INDEX `add_time`(`add_time`) USING BTREE,
  INDEX `openid`(`uid`) USING BTREE,
  INDEX `fail_time`(`fail_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户提现表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yx_user_extract
-- ----------------------------
INSERT INTO `yx_user_extract` VALUES (1, 11, '会敲代码的喵', 'weixin', '0', '', '', 1.00, NULL, 5.00, '失败了', 1578455379, 1573648445, -1, 'hu');

-- ----------------------------
-- Table structure for yx_user_group
-- ----------------------------
DROP TABLE IF EXISTS `yx_user_group`;
CREATE TABLE `yx_user_group`  (
  `id` smallint(5) UNSIGNED NOT NULL AUTO_INCREMENT,
  `group_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户分组名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户分组表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for yx_user_level
-- ----------------------------
DROP TABLE IF EXISTS `yx_user_level`;
CREATE TABLE `yx_user_level`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL DEFAULT 0 COMMENT '用户uid',
  `level_id` int(11) NOT NULL DEFAULT 0 COMMENT '等级vip',
  `grade` int(11) NOT NULL DEFAULT 0 COMMENT '会员等级',
  `valid_time` int(11) NOT NULL DEFAULT 0 COMMENT '过期时间',
  `is_forever` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否永久',
  `mer_id` int(11) NOT NULL DEFAULT 0 COMMENT '商户id',
  `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '0:禁止,1:正常',
  `mark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '备注',
  `remind` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否已通知',
  `is_del` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除,0=未删除,1=删除',
  `add_time` int(11) NOT NULL DEFAULT 0 COMMENT '添加时间',
  `discount` int(11) NOT NULL DEFAULT 0 COMMENT '享受折扣',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户等级记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yx_user_level
-- ----------------------------
INSERT INTO `yx_user_level` VALUES (1, 12, 1, 1, 0, 1, 0, 1, '恭喜你成为了普通会员', 0, 0, 1575728091, 99);

-- ----------------------------
-- Table structure for yx_user_recharge
-- ----------------------------
DROP TABLE IF EXISTS `yx_user_recharge`;
CREATE TABLE `yx_user_recharge`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `uid` int(10) NULL DEFAULT NULL COMMENT '充值用户UID',
  `nickname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `order_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单号',
  `price` decimal(8, 2) NULL DEFAULT NULL COMMENT '充值金额',
  `recharge_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '充值类型',
  `paid` tinyint(1) NULL DEFAULT NULL COMMENT '是否充值',
  `pay_time` int(10) NULL DEFAULT NULL COMMENT '充值支付时间',
  `add_time` int(12) NULL DEFAULT NULL COMMENT '充值时间',
  `refund_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '退款金额',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `order_id`(`order_id`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE,
  INDEX `recharge_type`(`recharge_type`) USING BTREE,
  INDEX `paid`(`paid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户充值表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yx_user_recharge
-- ----------------------------
INSERT INTO `yx_user_recharge` VALUES (1, 11, 'hupeng', '1234428298159718400', 200.00, 'weixin', 0, NULL, 1583145615, 0.00);
INSERT INTO `yx_user_recharge` VALUES (2, 11, '会敲代码的喵88', '1235853569362493440', 110.00, 'weixin', 0, NULL, 1583485426, 0.00);
INSERT INTO `yx_user_recharge` VALUES (3, 11, '会敲代码的喵88', '1235874304738983936', 111.00, 'weixin', 0, NULL, 1583490370, 0.00);
INSERT INTO `yx_user_recharge` VALUES (4, 11, '会敲代码的喵88', '1235874561447165952', 111.00, 'weixin', 0, NULL, 1583490431, 0.00);
INSERT INTO `yx_user_recharge` VALUES (5, 11, '会敲代码的喵88', '1235874725738053632', 111.00, 'weixin', 0, NULL, 1583490470, 0.00);
INSERT INTO `yx_user_recharge` VALUES (6, 11, '会敲代码的喵88', '1235875114126409728', 1111.00, 'weixin', 0, NULL, 1583490563, 0.00);
INSERT INTO `yx_user_recharge` VALUES (7, 11, '会敲代码的喵88', '1235876251080589312', 1111.00, 'weixin', 0, NULL, 1583490834, 0.00);

-- ----------------------------
-- Table structure for yx_user_sign
-- ----------------------------
DROP TABLE IF EXISTS `yx_user_sign`;
CREATE TABLE `yx_user_sign`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL DEFAULT 0 COMMENT '用户uid',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '签到说明',
  `number` int(11) NOT NULL DEFAULT 0 COMMENT '获得积分',
  `balance` int(11) NOT NULL DEFAULT 0 COMMENT '剩余积分',
  `add_time` int(11) NOT NULL DEFAULT 0 COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '签到记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yx_user_sign
-- ----------------------------
INSERT INTO `yx_user_sign` VALUES (2, 12, '签到奖励', 10, 0, 1575549244);
INSERT INTO `yx_user_sign` VALUES (3, 12, '签到奖励', 10, 10, 1575684021);
INSERT INTO `yx_user_sign` VALUES (4, 12, '签到奖励', 20, 20, 1575771932);
INSERT INTO `yx_user_sign` VALUES (5, 12, '签到奖励', 10, 40, 1577026465);
INSERT INTO `yx_user_sign` VALUES (6, 12, '签到奖励', 20, 50, 1577099436);
INSERT INTO `yx_user_sign` VALUES (7, 11, '签到奖励', 10, 0, 1582781884);

-- ----------------------------
-- Table structure for yx_user_task_finish
-- ----------------------------
DROP TABLE IF EXISTS `yx_user_task_finish`;
CREATE TABLE `yx_user_task_finish`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `task_id` int(11) NOT NULL DEFAULT 0 COMMENT '任务id',
  `uid` int(11) NOT NULL DEFAULT 0 COMMENT '用户id',
  `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否有效',
  `add_time` int(11) NOT NULL DEFAULT 0 COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户任务完成记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yx_user_task_finish
-- ----------------------------
INSERT INTO `yx_user_task_finish` VALUES (1, 2, 12, 0, 1575725509);
INSERT INTO `yx_user_task_finish` VALUES (2, 1, 12, 0, 1575726526);
INSERT INTO `yx_user_task_finish` VALUES (3, 16, 12, 0, 1575726526);
INSERT INTO `yx_user_task_finish` VALUES (4, 17, 12, 0, 1575729673);
INSERT INTO `yx_user_task_finish` VALUES (5, 2, 11, 0, 1582705000);

-- ----------------------------
-- Table structure for yx_wechat_media
-- ----------------------------
DROP TABLE IF EXISTS `yx_wechat_media`;
CREATE TABLE `yx_wechat_media`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '微信视频音频id',
  `type` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '回复类型',
  `path` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '文件路径',
  `media_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '微信服务器返回的id',
  `url` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '地址',
  `temporary` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否永久或者临时 0永久1临时',
  `add_time` int(10) UNSIGNED NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `type`(`type`, `media_id`) USING BTREE,
  INDEX `type_2`(`type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '微信回复表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for yx_wechat_reply
-- ----------------------------
DROP TABLE IF EXISTS `yx_wechat_reply`;
CREATE TABLE `yx_wechat_reply`  (
  `id` mediumint(8) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '微信关键字回复id',
  `key` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '关键字',
  `type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '回复类型',
  `data` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '回复数据',
  `status` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '0=不可用  1 =可用',
  `hide` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '是否隐藏',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `key`(`key`) USING BTREE,
  INDEX `type`(`type`) USING BTREE,
  INDEX `status`(`status`) USING BTREE,
  INDEX `hide`(`hide`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '微信关键字回复表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yx_wechat_reply
-- ----------------------------
INSERT INTO `yx_wechat_reply` VALUES (1, 'subscribe', 'text', '{\"content\":\"2222222222244449999\"}', 1, NULL);

-- ----------------------------
-- Table structure for yx_wechat_template
-- ----------------------------
DROP TABLE IF EXISTS `yx_wechat_template`;
CREATE TABLE `yx_wechat_template`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '模板id',
  `tempkey` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '模板编号',
  `name` char(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '模板名',
  `content` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '回复内容',
  `tempid` char(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模板ID',
  `add_time` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '添加时间',
  `status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `tempkey`(`tempkey`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '微信模板' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yx_wechat_template
-- ----------------------------
INSERT INTO `yx_wechat_template` VALUES (3, 'OPENTM200565259', '订单发货提醒', '{{first.DATA}}\n订单编号：{{keyword1.DATA}}\n物流公司：{{keyword2.DATA}}\n物流单号：{{keyword3.DATA}}\n{{remark.DATA}}', 'KusKZOFc_4CrRU_gzuXMdMMTfFeR-OLVVuDiMyR5PiM', '1515052638', 1);
INSERT INTO `yx_wechat_template` VALUES (13, 'OPENTM207791277', '订单支付成功通知', '{{first.DATA}}\n订单编号：{{keyword1.DATA}}\n支付金额：{{keyword2.DATA}}\n{{remark.DATA}}', 'hJV1d1OwWB_lbPrSaRHi9RGr5CFAF4PJcZdYeg73Mtg', '1528966759', 1);
INSERT INTO `yx_wechat_template` VALUES (14, 'OPENTM405847076', '帐户资金变动提醒', '{{first.DATA}}\n变动类型：{{keyword1.DATA}}\n变动时间：{{keyword2.DATA}}\n变动金额：{{keyword3.DATA}}\n{{remark.DATA}}', 'ePF4RS3ONCEuS9AuPyqZ2Th_B-HZ6E1CIpnJRt7ACwI', '1528966759', 1);

-- ----------------------------
-- Table structure for yx_wechat_user
-- ----------------------------
DROP TABLE IF EXISTS `yx_wechat_user`;
CREATE TABLE `yx_wechat_user`  (
  `uid` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '微信用户id',
  `unionid` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段',
  `openid` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户的标识，对当前公众号唯一',
  `routine_openid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '小程序唯一身份ID',
  `nickname` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户的昵称',
  `headimgurl` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户头像',
  `sex` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户的性别，值为1时是男性，值为2时是女性，值为0时是未知',
  `city` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户所在城市',
  `language` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户的语言，简体中文为zh_CN',
  `province` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户所在省份',
  `country` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户所在国家',
  `remark` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注',
  `groupid` smallint(5) UNSIGNED NULL DEFAULT 0 COMMENT '用户所在的分组ID（兼容旧的用户分组接口）',
  `tagid_list` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户被打上的标签ID列表',
  `subscribe` tinyint(3) UNSIGNED NULL DEFAULT 1 COMMENT '用户是否订阅该公众号标识',
  `subscribe_time` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '关注公众号时间',
  `add_time` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '添加时间',
  `stair` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '一级推荐人',
  `second` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '二级推荐人',
  `order_stair` int(11) NULL DEFAULT NULL COMMENT '一级推荐人订单',
  `order_second` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '二级推荐人订单',
  `now_money` decimal(8, 2) UNSIGNED NULL DEFAULT NULL COMMENT '佣金',
  `session_key` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '小程序用户会话密匙',
  `user_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'wechat' COMMENT '用户类型',
  PRIMARY KEY (`uid`) USING BTREE,
  INDEX `groupid`(`groupid`) USING BTREE,
  INDEX `subscribe_time`(`subscribe_time`) USING BTREE,
  INDEX `add_time`(`add_time`) USING BTREE,
  INDEX `subscribe`(`subscribe`) USING BTREE,
  INDEX `unionid`(`unionid`) USING BTREE,
  INDEX `openid`(`openid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '微信用户表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
