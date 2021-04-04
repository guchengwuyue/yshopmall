/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : yshop2

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 04/04/2021 17:46:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for alipay_config
-- ----------------------------
DROP TABLE IF EXISTS `alipay_config`;
CREATE TABLE `alipay_config` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `app_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '应用ID',
  `charset` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '编码',
  `format` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '类型 固定格式json',
  `gateway_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '网关地址',
  `notify_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '异步回调',
  `private_key` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '私钥',
  `public_key` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '公钥',
  `return_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '回调地址',
  `sign_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '签名方式',
  `sys_service_provider_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商户号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='支付宝配置类';

-- ----------------------------
-- Records of alipay_config
-- ----------------------------
BEGIN;
INSERT INTO `alipay_config` VALUES (1, '2016091700532697', 'utf-8', 'JSON', 'https://openapi.alipaydev.com/gateway.do', 'http://api.auauz.net/api/aliPay/notify', 'MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQC5js8sInU10AJ0cAQ8UMMyXrQ+oHZEkVt5lBwsStmTJ7YikVYgbskx1YYEXTojRsWCb+SH/kDmDU4pK/u91SJ4KFCRMF2411piYuXU/jF96zKrADznYh/zAraqT6hvAIVtQAlMHN53nx16rLzZ/8jDEkaSwT7+HvHiS+7sxSojnu/3oV7BtgISoUNstmSe8WpWHOaWv19xyS+Mce9MY4BfseFhzTICUymUQdd/8hXA28/H6osUfAgsnxAKv7Wil3aJSgaJczWuflYOve0dJ3InZkhw5Cvr0atwpk8YKBQjy5CdkoHqvkOcIB+cYHXJKzOE5tqU7inSwVbHzOLQ3XbnAgMBAAECggEAVJp5eT0Ixg1eYSqFs9568WdetUNCSUchNxDBu6wxAbhUgfRUGZuJnnAll63OCTGGck+EGkFh48JjRcBpGoeoHLL88QXlZZbC/iLrea6gcDIhuvfzzOffe1RcZtDFEj9hlotg8dQj1tS0gy9pN9g4+EBH7zeu+fyv+qb2e/v1l6FkISXUjpkD7RLQr3ykjiiEw9BpeKb7j5s7Kdx1NNIzhkcQKNqlk8JrTGDNInbDM6inZfwwIO2R1DHinwdfKWkvOTODTYa2MoAvVMFT9Bec9FbLpoWp7ogv1JMV9svgrcF9XLzANZ/OQvkbe9TV9GWYvIbxN6qwQioKCWO4GPnCAQKBgQDgW5MgfhX8yjXqoaUy/d1VjI8dHeIyw8d+OBAYwaxRSlCfyQ+tieWcR2HdTzPca0T0GkWcKZm0ei5xRURgxt4DUDLXNh26HG0qObbtLJdu/AuBUuCqgOiLqJ2f1uIbrz6OZUHns+bT/jGW2Ws8+C13zTCZkZt9CaQsrp3QOGDx5wKBgQDTul39hp3ZPwGNFeZdkGoUoViOSd5Lhowd5wYMGAEXWRLlU8z+smT5v0POz9JnIbCRchIY2FAPKRdVTICzmPk2EPJFxYTcwaNbVqL6lN7J2IlXXMiit5QbiLauo55w7plwV6LQmKm9KV7JsZs5XwqF7CEovI7GevFzyD3w+uizAQKBgC3LY1eRhOlpWOIAhpjG6qOoohmeXOphvdmMlfSHq6WYFqbWwmV4rS5d/6LNpNdL6fItXqIGd8I34jzql49taCmi+A2nlR/E559j0mvM20gjGDIYeZUz5MOE8k+K6/IcrhcgofgqZ2ZED1ksHdB/E8DNWCswZl16V1FrfvjeWSNnAoGAMrBplCrIW5xz+J0Hm9rZKrs+AkK5D4fUv8vxbK/KgxZ2KaUYbNm0xv39c+PZUYuFRCz1HDGdaSPDTE6WeWjkMQd5mS6ikl9hhpqFRkyh0d0fdGToO9yLftQKOGE/q3XUEktI1XvXF0xyPwNgUCnq0QkpHyGVZPtGFxwXiDvpvgECgYA5PoB+nY8iDiRaJNko9w0hL4AeKogwf+4TbCw+KWVEn6jhuJa4LFTdSqp89PktQaoVpwv92el/AhYjWOl/jVCm122f9b7GyoelbjMNolToDwe5pF5RnSpEuDdLy9MfE8LnE3PlbE7E5BipQ3UjSebkgNboLHH/lNZA5qvEtvbfvQ==', 'MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAut9evKRuHJ/2QNfDlLwvN/S8l9hRAgPbb0u61bm4AtzaTGsLeMtScetxTWJnVvAVpMS9luhEJjt+Sbk5TNLArsgzzwARgaTKOLMT1TvWAK5EbHyI+eSrc3s7Awe1VYGwcubRFWDm16eQLv0k7iqiw+4mweHSz/wWyvBJVgwLoQ02btVtAQErCfSJCOmt0Q/oJQjj08YNRV4EKzB19+f5A+HQVAKy72dSybTzAK+3FPtTtNen/+b5wGeat7c32dhYHnGorPkPeXLtsqqUTp1su5fMfd4lElNdZaoCI7osZxWWUo17vBCZnyeXc9fk0qwD9mK6yRAxNbrY72Xx5VqIqwIDAQAB', 'http://api.auauz.net/api/aliPay/return', 'RSA2', '2088102176044281');
COMMIT;

-- ----------------------------
-- Table structure for column_config
-- ----------------------------
DROP TABLE IF EXISTS `column_config`;
CREATE TABLE `column_config` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `table_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `column_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `column_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `dict_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `extra` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `form_show` bit(1) DEFAULT NULL,
  `form_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `key_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `list_show` bit(1) DEFAULT NULL,
  `not_null` bit(1) DEFAULT NULL,
  `query_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `date_annotation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=386 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='代码生成字段信息存储';

-- ----------------------------
-- Records of column_config
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `pid` bigint NOT NULL COMMENT '上级部门',
  `enabled` bit(1) NOT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL,
  `is_del` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='部门';

-- ----------------------------
-- Records of dept
-- ----------------------------
BEGIN;
INSERT INTO `dept` VALUES (1, 'YSHOP', 0, b'1', '2019-03-01 12:07:37', NULL, 0);
INSERT INTO `dept` VALUES (2, '研发部', 7, b'1', '2019-03-25 09:15:32', NULL, 0);
INSERT INTO `dept` VALUES (5, '运维部', 7, b'1', '2019-03-25 09:20:44', NULL, 0);
INSERT INTO `dept` VALUES (6, '测试部', 8, b'1', '2019-03-25 09:52:18', NULL, 0);
INSERT INTO `dept` VALUES (7, '华南分部', 1, b'1', '2019-03-25 11:04:50', NULL, 0);
INSERT INTO `dept` VALUES (8, '华北分部', 1, b'1', '2019-03-25 11:04:53', NULL, 0);
INSERT INTO `dept` VALUES (11, '人事部', 8, b'1', '2019-03-25 11:07:58', NULL, 0);
INSERT INTO `dept` VALUES (12, '7773', 1, b'1', '2020-05-18 19:43:53', NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for dict
-- ----------------------------
DROP TABLE IF EXISTS `dict`;
CREATE TABLE `dict` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典名称',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL,
  `is_del` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='数据字典';

-- ----------------------------
-- Records of dict
-- ----------------------------
BEGIN;
INSERT INTO `dict` VALUES (1, 'user_status', '用户状态', '2019-10-27 20:31:36', NULL, 0);
INSERT INTO `dict` VALUES (4, 'dept_status', '部门状态', '2019-10-27 20:31:36', NULL, 0);
INSERT INTO `dict` VALUES (5, 'job_status', '岗位状态', '2019-10-27 20:31:36', NULL, 0);
INSERT INTO `dict` VALUES (6, '33', '3', '2020-05-18 19:55:49', NULL, 0);
INSERT INTO `dict` VALUES (16, 'force_update', '强制升级', '2020-12-09 11:13:21', NULL, 0);
INSERT INTO `dict` VALUES (17, 'is_enable', '是否启用', '2020-12-10 12:02:57', NULL, 0);
INSERT INTO `dict` VALUES (18, '55', '55', '2021-04-04 15:31:47', NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for dict_detail
-- ----------------------------
DROP TABLE IF EXISTS `dict_detail`;
CREATE TABLE `dict_detail` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `label` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典标签',
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典值',
  `sort` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '排序',
  `dict_id` bigint DEFAULT NULL COMMENT '字典id',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL,
  `is_del` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK5tpkputc6d9nboxojdbgnpmyb` (`dict_id`) USING BTREE,
  CONSTRAINT `FK5tpkputc6d9nboxojdbgnpmyb` FOREIGN KEY (`dict_id`) REFERENCES `dict` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='数据字典详情';

-- ----------------------------
-- Records of dict_detail
-- ----------------------------
BEGIN;
INSERT INTO `dict_detail` VALUES (1, '激活', 'true', '1', 1, '2019-10-27 20:31:36', NULL, 0);
INSERT INTO `dict_detail` VALUES (2, '禁用', 'false', '2', 1, NULL, NULL, 0);
INSERT INTO `dict_detail` VALUES (3, '启用', 'true', '1', 4, NULL, NULL, 0);
INSERT INTO `dict_detail` VALUES (4, '停用', 'false', '2', 4, '2019-10-27 20:31:36', NULL, 0);
INSERT INTO `dict_detail` VALUES (5, '启用2', 'true', '1', 5, NULL, NULL, 0);
INSERT INTO `dict_detail` VALUES (6, '停用', 'false', '2', 5, '2019-10-27 20:31:36', NULL, 0);
INSERT INTO `dict_detail` VALUES (7, '8', '8', '999', NULL, '2020-05-18 19:44:05', NULL, 0);
INSERT INTO `dict_detail` VALUES (8, '99', '999', '999', NULL, '2020-05-18 19:44:31', NULL, 0);
INSERT INTO `dict_detail` VALUES (20, '是', '1', '999', 16, '2020-12-09 11:41:30', NULL, 0);
INSERT INTO `dict_detail` VALUES (21, '否', '0', '999', 16, '2020-12-09 11:41:36', NULL, 0);
INSERT INTO `dict_detail` VALUES (22, '是', '1', '999', 17, '2020-12-10 12:03:09', NULL, 0);
INSERT INTO `dict_detail` VALUES (23, '否', '0', '999', 17, '2020-12-10 12:03:16', NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for email_config
-- ----------------------------
DROP TABLE IF EXISTS `email_config`;
CREATE TABLE `email_config` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `from_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '收件人',
  `host` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮件服务器SMTP地址',
  `pass` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码',
  `port` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '端口',
  `user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '发件者用户名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='邮箱配置';

-- ----------------------------
-- Records of email_config
-- ----------------------------
BEGIN;
INSERT INTO `email_config` VALUES (1, '111@qq.com', '111', '111', '111', '1');
COMMIT;

-- ----------------------------
-- Table structure for gen_config
-- ----------------------------
DROP TABLE IF EXISTS `gen_config`;
CREATE TABLE `gen_config` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `table_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '表名',
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '作者',
  `cover` bit(1) DEFAULT NULL COMMENT '是否覆盖',
  `module_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '模块名称',
  `pack` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '至于哪个包下',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '前端代码生成的路径',
  `api_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '前端Api文件路径',
  `prefix` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '表前缀',
  `api_alias` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '接口名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='代码生成器配置';

-- ----------------------------
-- Records of gen_config
-- ----------------------------
BEGIN;
INSERT INTO `gen_config` VALUES (3, 'gen_test', 'Zheng Jie', b'1', 'eladmin-system', 'me.zhengjie.gen', 'E:\\workspace\\me\\front\\eladmin-web\\src\\views\\gen', 'E:\\workspace\\me\\front\\eladmin-web\\src\\api', NULL, '测试生成');
INSERT INTO `gen_config` VALUES (4, 'yx_material_group', 'hupeng', b'0', 'yshop-shop', 'co.yixiang.modules.shop', 'E:\\output', 'E:\\output\\', NULL, '素材');
INSERT INTO `gen_config` VALUES (5, 'yx_material', 'hupeng', b'0', 'yshop-shop', 'co.yixiang.modules.shop', 'E:\\output', 'E:\\output\\', NULL, '素材管理');
INSERT INTO `gen_config` VALUES (6, 'yx_user', 'hupeng', b'0', 'yshop-system', 'co.yixiang.modules', 'aa', 'aa\\', NULL, '用户');
INSERT INTO `gen_config` VALUES (7, 'yx_wechat_media', 'hupeng', b'0', 'yshop-system', 'co.yixiang.modules', 'E:\\book\\img', 'E:\\book\\img\\', NULL, 'ceshi');
INSERT INTO `gen_config` VALUES (8, 'yx_user_recharge', 'hupeng', b'0', 'yshop-shop', 'co.yixiang.modules.shop', 'E:\\java\\yxshop-private\\yshop-web\\src\\views\\shop\\recharge', 'E:\\java\\yxshop-private\\yshop-web\\src\\api', '', '充值管理');
INSERT INTO `gen_config` VALUES (9, 'yx_system_store', 'hupeng', b'0', 'yshop-shop', 'co.yixiang.modules.shop', 'E:\\java\\yxshop-private\\yshop-web\\src\\views\\shop\\store', 'E:\\java\\yxshop-private\\yshop-web\\src\\api', NULL, '门店');
INSERT INTO `gen_config` VALUES (10, 'yx_system_store_staff', 'hupeng', b'1', 'yshop-shop', 'co.yixiang.modules.shop', 'E:\\java\\yxshop-private\\yshop-web\\src\\views\\shop\\storestaff', 'E:\\java\\yxshop-private\\yshop-web\\src\\api', NULL, '门店店员');
INSERT INTO `gen_config` VALUES (11, 'yx_store_product_rule', 'hupeng', b'0', 'yshop-mall', 'co.yixiang.modules', 'product', 'product\\', NULL, 'sku规则');
INSERT INTO `gen_config` VALUES (12, 'yx_shipping_templates', 'hupeng', b'0', 'yshop-mall', 'co.yixiang.modules', 'product', 'product\\', NULL, '运费模板');
INSERT INTO `gen_config` VALUES (13, 'yx_shipping_templates_region', 'hupeng', b'0', 'yshop-mall', 'co.yixiang.modules', 'product', 'product\\', NULL, '运费模板区域');
INSERT INTO `gen_config` VALUES (14, 'yx_shipping_templates_free', 'hupeng', b'0', 'yshop-mall', 'co.yixiang.modules', 'product', 'product\\', NULL, '运费模板免费');
INSERT INTO `gen_config` VALUES (15, 'yx_system_city', 'hupeng', b'0', 'yshop-mall', 'co.yixiang.modules', 'product', 'product\\', NULL, '城市');
INSERT INTO `gen_config` VALUES (16, 'yx_store_product_relation', 'hupeng', b'0', 'yshop-mall', 'co.yixiang.modules.product', 'd:/', 'd:/', NULL, 'ProductRelation');
COMMIT;

-- ----------------------------
-- Table structure for gen_test
-- ----------------------------
DROP TABLE IF EXISTS `gen_test`;
CREATE TABLE `gen_test` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sex` int DEFAULT NULL COMMENT '性别',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='代码生成测试';

-- ----------------------------
-- Records of gen_test
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for job
-- ----------------------------
DROP TABLE IF EXISTS `job`;
CREATE TABLE `job` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '岗位名称',
  `enabled` bit(1) NOT NULL COMMENT '岗位状态',
  `sort` bigint NOT NULL COMMENT '岗位排序',
  `dept_id` bigint DEFAULT NULL COMMENT '部门ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL,
  `is_del` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FKmvhj0rogastlctflsxf1d6k3i` (`dept_id`) USING BTREE,
  CONSTRAINT `FKmvhj0rogastlctflsxf1d6k3i` FOREIGN KEY (`dept_id`) REFERENCES `dept` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='岗位';

-- ----------------------------
-- Records of job
-- ----------------------------
BEGIN;
INSERT INTO `job` VALUES (8, '人事专员', b'1', 3, 11, '2019-03-29 14:52:28', NULL, 0);
INSERT INTO `job` VALUES (10, '产品经理', b'1', 4, 2, '2019-03-29 14:55:51', NULL, 0);
INSERT INTO `job` VALUES (11, '全栈开发2', b'1', 2, 2, '2019-03-31 13:39:30', NULL, 0);
INSERT INTO `job` VALUES (12, '软件测试', b'1', 5, 2, '2019-03-31 13:39:43', NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for local_storage
-- ----------------------------
DROP TABLE IF EXISTS `local_storage`;
CREATE TABLE `local_storage` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `real_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文件真实的名称',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文件名',
  `suffix` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '后缀',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '路径',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '类型',
  `size` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '大小',
  `operate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '操作人',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='本地存储';

-- ----------------------------
-- Records of local_storage
-- ----------------------------
BEGIN;
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
INSERT INTO `local_storage` VALUES (37, '05ea40b831858a8cf423aa709840507c-20200311043711341.png', '05ea40b831858a8cf423aa709840507c', 'png', 'E:\\yshop\\file\\pic\\05ea40b831858a8cf423aa709840507c-20200311043711341.png', 'pic', '5.19KB   ', 'admin', '2020-03-11 16:37:11');
INSERT INTO `local_storage` VALUES (38, '秒杀-2020031104371672.png', '秒杀', 'png', 'E:\\yshop\\file\\pic\\秒杀-2020031104371672.png', 'pic', '6.07KB   ', 'admin', '2020-03-11 16:37:16');
INSERT INTO `local_storage` VALUES (39, '砍价-20200311043720679.png', '砍价', 'png', 'E:\\yshop\\file\\pic\\砍价-20200311043720679.png', 'pic', '6.13KB   ', 'admin', '2020-03-11 16:37:21');
INSERT INTO `local_storage` VALUES (40, '优惠券-20200311043724709.png', '优惠券', 'png', 'E:\\yshop\\file\\pic\\优惠券-20200311043724709.png', 'pic', '5.45KB   ', 'admin', '2020-03-11 16:37:25');
INSERT INTO `local_storage` VALUES (41, '资讯-20200311043727918.png', '资讯', 'png', 'E:\\yshop\\file\\pic\\资讯-20200311043727918.png', 'pic', '5.19KB   ', 'admin', '2020-03-11 16:37:28');
INSERT INTO `local_storage` VALUES (42, 'list_16-20200322071324803.png', 'list_16', 'png', 'E:\\yshop\\file\\pic\\list_16-20200322071324803.png', 'pic', '28.93KB   ', 'admin', '2020-03-22 19:13:25');
INSERT INTO `local_storage` VALUES (43, 'list_22-20200322071348844.png', 'list_22', 'png', 'E:\\yshop\\file\\pic\\list_22-20200322071348844.png', 'pic', '28.23KB   ', 'admin', '2020-03-22 19:13:49');
INSERT INTO `local_storage` VALUES (44, 'list_34-2020032207154023.png', 'list_34', 'png', 'E:\\yshop\\file\\pic\\list_34-2020032207154023.png', 'pic', '27.05KB   ', 'admin', '2020-03-22 19:15:40');
INSERT INTO `local_storage` VALUES (45, '钻石-20200328094531898.jpg', '钻石', 'jpg', 'E:\\yshop\\file\\pic\\钻石-20200328094531898.jpg', 'pic', '32.42KB   ', 'admin', '2020-03-28 21:45:32');
INSERT INTO `local_storage` VALUES (46, '20200527164526793230.png', 'list_13', 'png', 'D:\\yshop\\file\\pic\\20200527164526793230.png', 'pic', '23.05KB   ', 'admin', '2020-05-27 16:45:27');
INSERT INTO `local_storage` VALUES (47, '20200527164543489004.png', 'list_18', 'png', 'D:\\yshop\\file\\pic\\20200527164543489004.png', 'pic', '29.62KB   ', 'admin', '2020-05-27 16:45:43');
INSERT INTO `local_storage` VALUES (48, '20200623200105269693.png', 'list_13', 'png', 'D:\\yshop\\file\\pic\\20200623200105269693.png', 'pic', '23.05KB   ', 'yshop', '2020-06-23 20:01:05');
INSERT INTO `local_storage` VALUES (49, '20200623200118648188.png', 'list_18', 'png', 'D:\\yshop\\file\\pic\\20200623200118648188.png', 'pic', '29.62KB   ', 'yshop', '2020-06-23 20:01:19');
INSERT INTO `local_storage` VALUES (50, '20200626110808157592.png', 'list_34', 'png', 'D:\\yshop\\file\\pic\\20200626110808157592.png', 'pic', '27.05KB   ', 'yshop', '2020-06-26 11:08:08');
INSERT INTO `local_storage` VALUES (51, '20200626172220229003.png', 'list_16', 'png', 'D:\\yshop\\file\\pic\\20200626172220229003.png', 'pic', '28.93KB   ', 'yshop', '2020-06-26 17:22:20');
INSERT INTO `local_storage` VALUES (52, '20200626173520849370.png', 'list_18', 'png', 'D:\\yshop\\file\\pic\\20200626173520849370.png', 'pic', '29.62KB   ', 'yshop', '2020-06-26 17:35:21');
INSERT INTO `local_storage` VALUES (53, '20200626173943611335.png', 'list_34', 'png', 'D:\\yshop\\file\\pic\\20200626173943611335.png', 'pic', '27.05KB   ', 'yshop', '2020-06-26 17:39:44');
INSERT INTO `local_storage` VALUES (54, '20200903171003417769.png', 'avatar_default', 'png', '/home/yshop/file/pic/20200903171003417769.png', 'pic', '54.06KB   ', 'yshop', '2020-09-03 17:10:03');
INSERT INTO `local_storage` VALUES (55, '20200903171208889668.png', 'avatar_default', 'png', '/home/yshop/file/pic/20200903171208889668.png', 'pic', '54.06KB   ', 'yshop', '2020-09-03 17:12:09');
INSERT INTO `local_storage` VALUES (56, '20200903171351713145.jpg', '1', 'jpg', '/home/yshop/file/pic/20200903171351713145.jpg', 'pic', '126.59KB   ', 'yshop', '2020-09-03 17:13:52');
INSERT INTO `local_storage` VALUES (57, '20200903171807568739.jpg', '4', 'jpg', '/home/yshop/file/pic/20200903171807568739.jpg', 'pic', '68.57KB   ', 'yshop', '2020-09-03 17:18:08');
INSERT INTO `local_storage` VALUES (58, '20200903171807584910.jpg', '3', 'jpg', '/home/yshop/file/pic/20200903171807584910.jpg', 'pic', '34.32KB   ', 'yshop', '2020-09-03 17:18:08');
INSERT INTO `local_storage` VALUES (59, '20200903171807625149.jpg', '5', 'jpg', '/home/yshop/file/pic/20200903171807625149.jpg', 'pic', '81.55KB   ', 'yshop', '2020-09-03 17:18:08');
INSERT INTO `local_storage` VALUES (60, '20200903171807641535.jpg', '6', 'jpg', '/home/yshop/file/pic/20200903171807641535.jpg', 'pic', '61.97KB   ', 'yshop', '2020-09-03 17:18:08');
INSERT INTO `local_storage` VALUES (61, '20200903171807647067.jpg', '7', 'jpg', '/home/yshop/file/pic/20200903171807647067.jpg', 'pic', '76.04KB   ', 'yshop', '2020-09-03 17:18:08');
INSERT INTO `local_storage` VALUES (62, '20200911093912577832.png', 'avatar_default', 'png', '/home/yshop/file/pic/20200911093912577832.png', 'pic', '54.06KB   ', 'admin', '2020-09-11 09:39:13');
INSERT INTO `local_storage` VALUES (63, '20200915074139926178.jpg', '1600126884(1)', 'jpg', '/home/yshop/file/pic/20200915074139926178.jpg', 'pic', '23.64KB   ', 'yshop', '2020-09-15 07:41:40');
COMMIT;

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `exception_detail` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `log_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `params` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `request_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `time` bigint DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `browser` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `type` int DEFAULT '0',
  `uid` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_del` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=249 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='系统日志';

-- ----------------------------
-- Records of log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `i_frame` bit(1) DEFAULT NULL COMMENT '是否外链',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单名称',
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '组件',
  `pid` bigint NOT NULL COMMENT '上级菜单ID',
  `sort` bigint NOT NULL COMMENT '排序',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图标',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '链接地址',
  `cache` bit(1) DEFAULT b'0' COMMENT '缓存',
  `hidden` bit(1) DEFAULT b'0' COMMENT '是否隐藏',
  `component_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '-' COMMENT '组件名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `permission` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限',
  `type` int DEFAULT NULL COMMENT '类型',
  `update_time` datetime DEFAULT NULL,
  `is_del` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FKqcf9gem97gqa5qjm4d3elcqt5` (`pid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=259 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of menu
-- ----------------------------
BEGIN;
INSERT INTO `menu` VALUES (1, b'0', '系统管理', NULL, 0, 99, 'system', 'system', b'0', b'0', NULL, '2018-12-18 15:11:29', NULL, 1, '2021-04-04 16:02:03', 0);
INSERT INTO `menu` VALUES (2, b'0', '用户管理', 'system/user/index', 1, 2, 'peoples', 'user', b'0', b'0', 'User', '2018-12-18 15:14:44', 'user:list', 1, NULL, 0);
INSERT INTO `menu` VALUES (3, b'0', '角色管理', 'system/role/index', 1, 3, 'role', 'role', b'0', b'0', 'Role', '2018-12-18 15:16:07', 'roles:list', 1, NULL, 0);
INSERT INTO `menu` VALUES (5, b'0', '菜单管理', 'system/menu/index', 1, 5, 'menu', 'menu', b'0', b'0', 'Menu', '2018-12-18 15:17:28', 'menu:list', 1, NULL, 0);
INSERT INTO `menu` VALUES (6, b'0', '系统监控', NULL, 0, 100, 'monitor', 'monitor', b'0', b'0', NULL, '2018-12-18 15:17:48', NULL, 1, NULL, 0);
INSERT INTO `menu` VALUES (7, b'0', '操作日志', 'monitor/log/index', 6, 11, 'log', 'logs', b'1', b'0', 'Log', '2018-12-18 15:18:26', 'log:list', 1, NULL, 0);
INSERT INTO `menu` VALUES (9, b'0', 'SQL监控', 'monitor/sql/index', 6, 14, 'sqlMonitor', 'druid', b'0', b'0', 'Sql', '2018-12-18 15:19:34', NULL, 1, NULL, 0);
INSERT INTO `menu` VALUES (14, b'0', '邮件工具', 'tools/email/index', 36, 24, 'email', 'email', b'0', b'0', 'Email', '2018-12-27 10:13:09', NULL, 1, NULL, 0);
INSERT INTO `menu` VALUES (18, b'0', '存储管理', 'tools/storage/index', 36, 23, 'qiniu', 'storage', b'0', b'0', 'Storage', '2018-12-31 11:12:15', 'storage:list', 1, '2020-08-06 22:57:28', 0);
INSERT INTO `menu` VALUES (19, b'0', '支付宝工具', 'tools/aliPay/index', 36, 27, 'alipay', 'aliPay', b'0', b'0', 'AliPay', '2018-12-31 14:52:38', NULL, 1, NULL, 0);
INSERT INTO `menu` VALUES (28, b'0', '定时任务', 'system/timing/index', 36, 21, 'timing', 'timing', b'0', b'0', 'Timing', '2019-01-07 20:34:40', 'timing:list', 1, NULL, 0);
INSERT INTO `menu` VALUES (30, b'0', '代码生成', 'generator/index', 36, 22, 'dev', 'generator', b'0', b'0', 'GeneratorIndex', '2019-01-11 15:45:55', NULL, 1, NULL, 0);
INSERT INTO `menu` VALUES (32, b'0', '异常日志', 'monitor/log/errorLog', 6, 12, 'error', 'errorLog', b'0', b'0', 'ErrorLog', '2019-01-13 13:49:03', 'logError:list', 1, NULL, 0);
INSERT INTO `menu` VALUES (35, b'0', '部门管理', 'system/dept/index', 1, 6, 'dept', 'dept', b'0', b'0', 'Dept', '2019-03-25 09:46:00', 'dept:list', 1, NULL, 0);
INSERT INTO `menu` VALUES (36, b'0', '系统工具', '', 0, 101, 'sys-tools', 'sys-tools', b'0', b'0', NULL, '2019-03-29 10:57:35', NULL, 1, NULL, 0);
INSERT INTO `menu` VALUES (37, b'0', '岗位管理', 'system/job/index', 1, 7, 'Steve-Jobs', 'job', b'0', b'0', 'Job', '2019-03-29 13:51:18', 'user:list', 1, NULL, 0);
INSERT INTO `menu` VALUES (39, b'0', '字典管理', 'system/dict/index', 1, 8, 'dictionary', 'dict', b'0', b'0', 'Dict', '2019-04-10 11:49:04', 'dict:list', 1, NULL, 0);
INSERT INTO `menu` VALUES (40, b'0', '商品管理', '', 0, 1, 'shop', 'shop', b'1', b'0', '', '2019-10-03 17:40:19', NULL, 1, NULL, 0);
INSERT INTO `menu` VALUES (41, b'0', '商品分类', 'shop/cate/index', 40, 11, 'icon', 'cate', b'0', b'0', 'Cate', '2019-10-03 17:42:35', 'YXSTORECATEGORY_ALL,YXSTORECATEGORY_SELECT', 1, NULL, 0);
INSERT INTO `menu` VALUES (45, b'0', '管理商品', 'shop/goods/tab', 40, 12, 'develop', 'goods', b'1', b'0', 'Goods', '2019-10-04 15:34:35', 'YXSTOREPRODUCT_SELECT,YXSTORECATEGORY_ALL,YXSTORECATEGORY_SELECT,YXSYSTEMGROUPDATA_SELECT', 1, '2020-08-06 23:01:36', 0);
INSERT INTO `menu` VALUES (46, b'0', '会员管理', '', 0, 2, 'peoples', 'member', b'0', b'0', '', '2019-10-06 16:18:05', NULL, 1, NULL, 0);
INSERT INTO `menu` VALUES (47, b'0', '会员', 'shop/user/index', 46, 21, 'peoples', 'member', b'0', b'0', 'Member', '2019-10-06 16:20:17', 'YXUSER_SELECT', 1, NULL, 0);
INSERT INTO `menu` VALUES (48, b'0', '微信管理', '', 0, 14, 'weixin', 'wechat', b'0', b'0', '', '2019-10-06 18:28:54', NULL, 1, '2020-06-26 15:18:32', 0);
INSERT INTO `menu` VALUES (49, b'0', '微信菜单', 'wechat/menu/index', 48, 31, 'menu', 'wemenu', b'0', b'0', 'WeMenu', '2019-10-06 18:31:06', 'YxWechatMenu_ALL,YxWechatMenu_SELECT', 1, NULL, 0);
INSERT INTO `menu` VALUES (50, b'0', '图文管理', 'wechat/article/index', 48, 32, 'article', 'wearticle', b'0', b'0', 'WeArticle', '2019-10-07 17:33:45', 'YXARTICLE_ALL,YXARTICLE_SELECT', 1, NULL, 0);
INSERT INTO `menu` VALUES (51, b'0', '自动回复', 'wechat/reply/index', 48, 33, 'reply', 'wereply', b'0', b'0', 'Wereply', '2019-10-10 09:58:31', 'YXWECHATREPLY_ALL', 1, NULL, 0);
INSERT INTO `menu` VALUES (52, b'0', '公众号配置', 'wechat/config/index', 48, 34, 'configure', 'weconfig', b'0', b'0', 'WeConfig', '2019-10-10 15:52:24', 'YXSYSTEMCONFIG_ALL', 1, NULL, 0);
INSERT INTO `menu` VALUES (53, b'0', '订单管理', '', 0, 4, 'lock', 'order', b'0', b'0', '', '2019-10-14 14:35:18', NULL, 1, NULL, 0);
INSERT INTO `menu` VALUES (54, b'0', '订单', 'shop/order/index', 53, 41, 'order', 'order', b'1', b'0', 'Order', '2019-10-14 14:36:28', 'YXSTOREORDER_SELECT,YXEXPRESS_SELECT', 1, '2020-10-27 18:36:36', 0);
INSERT INTO `menu` VALUES (55, b'0', '商城配置', '', 0, 15, 'configure', 'set', b'0', b'0', '', '2019-10-18 15:21:26', NULL, 1, '2020-06-26 15:18:20', 0);
INSERT INTO `menu` VALUES (56, b'0', '首页幻灯片', 'shop/set/index', 55, 51, 'banner', 'homeBanner', b'0', b'0', 'HomeBanner', '2019-10-18 15:24:30', 'YXSYSTEMGROUPDATA_ALL,YXSYSTEMGROUPDATA_SELECT', 1, NULL, 0);
INSERT INTO `menu` VALUES (57, b'0', '首页导航按钮', 'shop/set/menu', 55, 52, 'button', 'homeMenus', b'0', b'0', 'HomeMenus', '2019-10-18 17:23:35', 'YXSYSTEMGROUPDATA_ALL,YXSYSTEMGROUPDATA_SELECT', 1, NULL, 0);
INSERT INTO `menu` VALUES (59, b'0', '首页滚动新闻', 'shop/set/roll', 55, 54, 'news', 'roll', b'0', b'0', 'Roll', '2019-10-21 16:41:30', 'YXSYSTEMGROUPDATA_ALL,YXSYSTEMGROUPDATA_SELECT', 1, NULL, 0);
INSERT INTO `menu` VALUES (60, b'0', '热门搜索', 'shop/set/hot', 55, 55, 'search', 'hot', b'0', b'0', 'Hot', '2019-10-26 18:21:54', 'YXSYSTEMGROUPDATA_ALL,YXSYSTEMGROUPDATA_SELECT', 1, NULL, 0);
INSERT INTO `menu` VALUES (61, b'0', '个人中心菜单', 'shop/set/usermenu', 55, 56, 'menu', 'userMenu', b'0', b'0', 'UserMenu', '2019-10-26 18:42:18', 'YXSYSTEMGROUPDATA_ALL,YXSYSTEMGROUPDATA_SELECT', 1, NULL, 0);
INSERT INTO `menu` VALUES (62, b'0', '评论管理', 'shop/reply/index', 53, 42, 'comment', 'reply', b'0', b'0', 'Reply', '2019-11-03 14:39:09', 'YXSTOREPRODUCTREPLY_SELECT', 1, NULL, 0);
INSERT INTO `menu` VALUES (63, b'0', '营销管理', '', 0, 6, 'yingxiao', 'activity', b'0', b'0', '', '2019-11-09 14:17:42', NULL, 1, NULL, 0);
INSERT INTO `menu` VALUES (64, b'0', '优惠券制作', 'activity/coupon/index', 229, 61, 'coupon', 'coupon', b'0', b'0', 'Coupon', '2019-11-09 14:18:58', 'YXSTORECOUPON_SELECT', 1, '2020-06-26 15:16:40', 0);
INSERT INTO `menu` VALUES (65, b'0', '已发布优惠券', 'activity/couponissue/index', 229, 62, 'coupon2', 'couponissue', b'0', b'0', 'Couponissue', '2019-11-09 14:20:35', 'YXSTORECOUPONISSUE_SELECT', 1, '2020-06-26 15:16:48', 0);
INSERT INTO `menu` VALUES (66, b'0', '优惠券领取记录', 'activity/storecouponuser/index', 229, 63, 'log', 'couponuser', b'0', b'0', 'Couponuser', '2019-11-09 14:21:35', 'YXSTORECOUPONUSER_SELECT,YXSTORECOUPONUSER_SELECT', 1, '2020-08-06 23:11:10', 0);
INSERT INTO `menu` VALUES (68, b'0', '积分配置', 'wechat/config/point', 227, 59, 'configure', 'pointConfig', b'0', b'0', 'PointConfig', '2019-11-10 18:45:55', 'YXSYSTEMCONFIG_SELECT,YXSYSTEMCONFIG_SELECT', 1, '2020-08-06 23:09:31', 0);
INSERT INTO `menu` VALUES (69, b'0', '分销管理', '', 0, 7, 'fenxiao', 'promoter', b'0', b'0', '', '2019-11-11 10:42:16', NULL, 1, NULL, 0);
INSERT INTO `menu` VALUES (70, b'0', '分销配置', 'wechat/config/promoter', 69, 71, 'configure', 'promoterconfig', b'0', b'0', 'Promoterconfig', '2019-11-11 10:48:37', 'YXSYSTEMCONFIG_SELECT,YXSYSTEMCONFIG_SELECT', 1, '2020-08-06 23:12:31', 0);
INSERT INTO `menu` VALUES (71, b'0', '分销员', 'shop/user/aindex', 69, 72, 'user', 'agent', b'0', b'0', 'Agent', '2019-11-13 18:32:00', 'YXUSER_SELECT', 1, NULL, 1);
INSERT INTO `menu` VALUES (72, b'0', '提现管理', 'activity/extract/index', 176, 81, 'tixian', 'extract', b'0', b'0', 'Extract', '2019-11-14 10:49:39', 'YXUSEREXTRACT_SELECT,YXUSEREXTRACT_SELECT', 1, '2020-08-06 23:07:28', 0);
INSERT INTO `menu` VALUES (73, b'0', '拼团产品', 'activity/combination/index', 63, 64, 'peoples', 'combination', b'0', b'0', 'Combination', '2019-11-18 14:23:04', 'YXSTORECOMBINATION_SELECT', 1, NULL, 0);
INSERT INTO `menu` VALUES (74, b'0', '拼团列表', 'activity/combinlist/index', 63, 65, 'list', 'pink', b'0', b'0', 'Pink', '2019-11-21 19:35:58', 'YXSTOREPINK_SELECT', 1, NULL, 0);
INSERT INTO `menu` VALUES (75, b'0', '微信支付配置', 'wechat/config/pay', 48, 35, 'wxpay', 'wxpayconfig', b'0', b'0', 'Wxpayconfig', '2019-11-28 17:06:22', 'YXSYSTEMCONFIG_ALL', 1, NULL, 0);
INSERT INTO `menu` VALUES (76, b'0', '小程序配置', 'wechat/config/wxapp', 48, 36, 'configure', 'wxapp', b'0', b'0', 'Wxapp', '2019-11-29 15:13:46', 'YXSYSTEMCONFIG_ALL', 1, NULL, 0);
INSERT INTO `menu` VALUES (77, b'0', '会员等级', 'shop/userlevel/index', 46, 22, 'dengji', 'userlevel', b'0', b'0', 'Userlevel', '2019-12-04 16:35:41', 'YXSYSTEMUSERLEVEL_SELECT', 1, NULL, 0);
INSERT INTO `menu` VALUES (78, b'0', '等级任务', 'shop/usertask/index', 46, 23, 'task manege', 'usertask', b'0', b'0', 'Usertask', '2019-12-04 17:26:19', 'YXSYSTEMUSERTASK_SELECT', 1, NULL, 0);
INSERT INTO `menu` VALUES (79, b'0', '签到天数配置', 'shop/set/sign', 227, 61, 'sign2', 'signday', b'0', b'0', 'Signday', '2019-12-05 14:12:16', 'YXSYSTEMGROUPDATA_ALL,YXSYSTEMGROUPDATA_SELECT', 1, '2020-06-26 15:09:25', 0);
INSERT INTO `menu` VALUES (80, b'0', '用户账单', 'shop/user/bill', 46, 24, 'list', 'bill', b'0', b'0', 'Bill', '2019-12-11 17:28:38', 'YXUSERBILL_ALL,YXUSERBILL_SELECT', 1, NULL, 0);
INSERT INTO `menu` VALUES (81, b'0', '物流快递', 'shop/express/index', 53, 43, 'express', 'express', b'0', b'0', 'Express', '2019-12-12 16:36:00', 'YXEXPRESS_SELECT', 1, NULL, 0);
INSERT INTO `menu` VALUES (82, b'0', '微信模板消息', 'wechat/template/index', 48, 35, 'anq', 'template', b'0', b'0', 'Template', '2019-12-13 14:42:50', 'yxWechatTemplate:list', 1, NULL, 0);
INSERT INTO `menu` VALUES (83, b'0', '秒杀产品', 'activity/seckill/index', 63, 66, 'seckill', 'seckill', b'0', b'0', 'Seckill', '2019-12-16 13:06:29', 'YXSTORESECKILL_SELECT', 1, NULL, 0);
INSERT INTO `menu` VALUES (84, b'0', '秒杀配置', 'shop/set/seckill', 63, 67, 'configure', 'seckillconfig', b'0', b'0', 'Seckillconfig', '2019-12-16 16:07:42', 'YXSYSTEMGROUPDATA_SELECT', 1, NULL, 0);
INSERT INTO `menu` VALUES (86, b'0', '砍价产品', 'activity/bargain/index', 63, 956, 'Sign', 'bargain', b'0', b'0', 'Bargain', '2019-12-22 12:25:55', 'YXSTOREBARGAIN_SELECT', 1, NULL, 0);
INSERT INTO `menu` VALUES (87, b'0', '生成配置', 'generator/config', 36, 33, 'dev', 'generator/config/:tableName', b'1', b'1', 'GeneratorConfig', '2019-11-17 20:08:56', '', 1, NULL, 0);
INSERT INTO `menu` VALUES (88, b'0', '生成预览', 'generator/preview', 36, 999, 'java', 'generator/preview/:tableName', b'1', b'1', 'Preview', '2019-11-26 14:54:36', NULL, 1, NULL, 0);
INSERT INTO `menu` VALUES (116, b'0', '生成配置', 'generator/config', 36, 33, 'dev', 'generator/config/:tableName', b'1', b'1', 'GeneratorConfig', '2019-11-17 20:08:56', '', 1, NULL, 0);
INSERT INTO `menu` VALUES (117, b'0', '图表库', 'components/Echarts', 10, 50, 'chart', 'echarts', b'1', b'0', 'Echarts', '2019-11-21 09:04:32', '', 1, NULL, 0);
INSERT INTO `menu` VALUES (118, b'0', '商品新增', 'shop/goods/form', 40, 1, 'anq', 'goodsAdd', b'0', b'1', 'GoodsAdd', '2019-12-24 13:00:47', 'YXSTOREPRODUCT_EDIT', 1, '2020-10-27 18:42:37', 0);
INSERT INTO `menu` VALUES (119, b'0', '商品修改', 'shop/goods/form', 40, 3, 'anq', 'goodsEdit/:id', b'0', b'1', 'GoodsEdit', '2019-12-24 13:02:23', 'YXSTOREPRODUCT_CREATE', 1, '2020-07-10 16:45:33', 0);
INSERT INTO `menu` VALUES (120, b'0', '商品删除', NULL, 45, 4, NULL, NULL, b'0', b'0', NULL, '2019-12-24 13:03:51', 'YXSTOREPRODUCT_DELETE', 2, '2020-07-10 16:22:51', 0);
INSERT INTO `menu` VALUES (121, b'0', '在线用户', 'monitor/online/index', 6, 10, 'Steve-Jobs', 'online', b'0', b'0', 'OnlineUser', '2020-01-06 22:46:43', NULL, 1, NULL, 0);
INSERT INTO `menu` VALUES (122, b'0', '浏览记录', 'monitor/log/mlog', 40, 13, 'log', 'viewlog', b'0', b'0', 'Viewlog', '2020-01-07 13:17:21', NULL, 1, NULL, 1);
INSERT INTO `menu` VALUES (123, b'0', '后台接口文档', 'tools/swagger/index', 36, 31, 'swagger', 'swagger2', b'0', b'0', 'Swagger', '2020-01-07 18:05:52', NULL, 1, NULL, 0);
INSERT INTO `menu` VALUES (124, b'0', '在线会员', 'monitor/online/indext', 46, 25, 'Steve-Jobs', 'onlinet', b'0', b'0', 'OnlineMember', '2020-01-13 10:53:07', 'auth_online', 1, '2020-08-06 22:50:49', 0);
INSERT INTO `menu` VALUES (125, b'0', '邮费配置', 'wechat/config/postage', 55, 58, 'configure', 'postageConfig', b'0', b'0', 'PostageConfig', '2020-02-13 15:38:24', 'YXSYSTEMCONFIG_SELECT', 1, NULL, 1);
INSERT INTO `menu` VALUES (126, b'0', '编辑', NULL, 54, 1, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:05:28', 'YXSTOREORDER_EDIT', 2, NULL, 0);
INSERT INTO `menu` VALUES (127, b'0', '用户新增', NULL, 2, 2, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:12:21', 'user:add', 2, NULL, 0);
INSERT INTO `menu` VALUES (128, b'0', '用户编辑', NULL, 2, 3, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:12:47', 'user:edit', 2, NULL, 0);
INSERT INTO `menu` VALUES (129, b'0', '用户删除', NULL, 2, 4, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:13:08', 'user:del', 2, NULL, 0);
INSERT INTO `menu` VALUES (130, b'0', '角色创建', NULL, 3, 2, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:13:49', 'roles:add', 2, NULL, 0);
INSERT INTO `menu` VALUES (131, b'0', '角色修改', NULL, 3, 3, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:14:11', 'roles:edit', 2, NULL, 0);
INSERT INTO `menu` VALUES (132, b'0', '角色删除', NULL, 3, 999, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:14:38', 'roles:del', 2, NULL, 0);
INSERT INTO `menu` VALUES (133, b'0', '菜单新增', NULL, 5, 2, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:15:05', 'menu:add', 2, NULL, 0);
INSERT INTO `menu` VALUES (134, b'0', '菜单编辑', NULL, 5, 3, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:18:44', 'menu:edit', 2, NULL, 0);
INSERT INTO `menu` VALUES (135, b'0', '菜单删除', NULL, 5, 4, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:19:05', 'menu:del', 2, NULL, 0);
INSERT INTO `menu` VALUES (136, b'0', '部门新增', NULL, 35, 2, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:21:07', 'dept:add', 2, NULL, 0);
INSERT INTO `menu` VALUES (137, b'0', '部门编辑', NULL, 35, 3, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:21:33', 'dept:edit', 2, NULL, 0);
INSERT INTO `menu` VALUES (138, b'0', '部门删除', NULL, 35, 4, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:21:53', 'dept:del', 2, NULL, 0);
INSERT INTO `menu` VALUES (139, b'0', '岗位新增', NULL, 37, 2, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:29:04', 'job:add', 2, NULL, 0);
INSERT INTO `menu` VALUES (140, b'0', '岗位编辑', NULL, 37, 3, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:48:38', 'job:edit', 2, NULL, 0);
INSERT INTO `menu` VALUES (141, b'0', '岗位删除', NULL, 37, 4, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:49:00', 'job:del', 2, NULL, 0);
INSERT INTO `menu` VALUES (142, b'0', '字典新增', NULL, 39, 2, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:49:26', 'dict:add', 2, NULL, 0);
INSERT INTO `menu` VALUES (143, b'0', '字典编辑', NULL, 39, 3, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:49:39', 'dict:edit', 2, NULL, 0);
INSERT INTO `menu` VALUES (144, b'0', '字典删除', NULL, 39, 4, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:49:56', 'dict:del', 2, NULL, 0);
INSERT INTO `menu` VALUES (147, b'0', '上传文件', NULL, 18, 2, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:53:49', 'storage:add', 2, NULL, 0);
INSERT INTO `menu` VALUES (148, b'0', '文件编辑', NULL, 18, 3, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:54:06', 'storage:edit', 2, NULL, 0);
INSERT INTO `menu` VALUES (149, b'0', '文件删除', NULL, 18, 4, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:54:27', 'storage:del', 2, NULL, 0);
INSERT INTO `menu` VALUES (150, b'0', '任务新增', NULL, 28, 2, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:55:58', 'timing:add', 2, NULL, 0);
INSERT INTO `menu` VALUES (151, b'0', '任务编辑', NULL, 28, 3, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:56:54', 'timing:edit', 2, NULL, 0);
INSERT INTO `menu` VALUES (152, b'0', '任务删除', NULL, 28, 4, NULL, NULL, b'0', b'0', NULL, '2020-02-14 21:57:10', 'timing:del', 2, NULL, 0);
INSERT INTO `menu` VALUES (153, b'0', '新增分类', NULL, 41, 2, NULL, NULL, b'0', b'0', NULL, '2020-02-14 22:00:41', 'YXSTORECATEGORY_CREATE', 2, NULL, 0);
INSERT INTO `menu` VALUES (154, b'0', '分类编辑', NULL, 41, 3, NULL, NULL, b'0', b'0', NULL, '2020-02-14 22:01:15', 'YXSTORECATEGORY_EDIT', 2, NULL, 0);
INSERT INTO `menu` VALUES (155, b'0', '分类删除', NULL, 41, 4, NULL, NULL, b'0', b'0', NULL, '2020-02-14 22:01:37', 'YXSTORECATEGORY_DELETE', 2, NULL, 0);
INSERT INTO `menu` VALUES (156, b'0', '修改会员', NULL, 47, 2, NULL, NULL, b'0', b'0', NULL, '2020-02-14 22:03:40', 'YXUSER_EDIT', 2, NULL, 0);
INSERT INTO `menu` VALUES (157, b'0', '等级新增', NULL, 77, 2, NULL, NULL, b'0', b'0', NULL, '2020-02-14 22:06:55', 'YXSYSTEMUSERLEVEL_CREATE', 2, NULL, 0);
INSERT INTO `menu` VALUES (158, b'0', '等级编辑', NULL, 77, 3, NULL, NULL, b'0', b'0', NULL, '2020-02-14 22:08:03', 'YXSYSTEMUSERLEVEL_EDIT', 2, NULL, 0);
INSERT INTO `menu` VALUES (159, b'0', '等级删除', NULL, 77, 4, NULL, NULL, b'0', b'0', NULL, '2020-02-14 22:08:41', 'YXSYSTEMUSERLEVEL_DELETE', 2, NULL, 0);
INSERT INTO `menu` VALUES (160, b'0', '编辑任务', NULL, 78, 2, NULL, NULL, b'0', b'0', NULL, '2020-02-14 22:10:08', 'YXSYSTEMUSERTASK_EDIT', 2, NULL, 0);
INSERT INTO `menu` VALUES (161, b'0', '评论删除', NULL, 62, 2, NULL, NULL, b'0', b'0', NULL, '2020-02-14 22:14:22', 'YXSTOREPRODUCTREPLY_DELETE', 2, NULL, 0);
INSERT INTO `menu` VALUES (162, b'0', '新增物流', NULL, 81, 2, NULL, NULL, b'0', b'0', NULL, '2020-02-14 22:15:33', 'YXEXPRESS_CREATE', 2, NULL, 0);
INSERT INTO `menu` VALUES (163, b'0', '编辑物流', NULL, 81, 3, NULL, NULL, b'0', b'0', NULL, '2020-02-14 22:15:53', 'YXEXPRESS_EDIT', 2, NULL, 0);
INSERT INTO `menu` VALUES (164, b'0', '删除物流', NULL, 81, 4, NULL, NULL, b'0', b'0', NULL, '2020-02-14 22:16:11', 'YXEXPRESS_DELETE', 2, NULL, 0);
INSERT INTO `menu` VALUES (165, b'0', '新增优惠券', NULL, 64, 2, NULL, NULL, b'0', b'0', NULL, '2020-02-14 22:18:32', 'YXSTORECOUPON_CREATE', 2, NULL, 0);
INSERT INTO `menu` VALUES (166, b'0', '编辑优惠券', NULL, 64, 3, NULL, NULL, b'0', b'0', NULL, '2020-02-14 22:18:50', 'YXSTORECOUPON_EDIT', 2, NULL, 0);
INSERT INTO `menu` VALUES (167, b'0', '删除优惠券', NULL, 64, 4, NULL, NULL, b'0', b'0', NULL, '2020-02-14 22:19:10', 'YXSTORECOUPON_DELETE', 2, NULL, 0);
INSERT INTO `menu` VALUES (168, b'0', '编辑已发布', NULL, 65, 2, NULL, NULL, b'0', b'0', NULL, '2020-02-14 22:20:23', 'YXSTORECOUPONISSUE_EDIT', 2, NULL, 0);
INSERT INTO `menu` VALUES (169, b'0', '删除已发布', NULL, 65, 3, NULL, NULL, b'0', b'0', NULL, '2020-02-14 22:20:42', 'YXSTORECOUPONISSUE_DELETE', 2, NULL, 0);
INSERT INTO `menu` VALUES (170, b'0', '编辑拼团', NULL, 73, 2, NULL, NULL, b'0', b'0', NULL, '2020-02-14 22:24:15', 'YXSTORECOMBINATION_EDIT', 2, NULL, 0);
INSERT INTO `menu` VALUES (171, b'0', '删除拼团', NULL, 73, 3, NULL, NULL, b'0', b'0', NULL, '2020-02-14 22:24:37', 'YXSTORECOMBINATION_DELETE', 2, NULL, 0);
INSERT INTO `menu` VALUES (172, b'0', '编辑秒杀', NULL, 83, 2, NULL, NULL, b'0', b'0', NULL, '2020-02-14 22:25:23', 'YXSTORESECKILL_EDIT', 2, NULL, 0);
INSERT INTO `menu` VALUES (173, b'0', '删除秒杀', NULL, 83, 3, NULL, NULL, b'0', b'0', NULL, '2020-02-14 22:25:41', 'YXSTORESECKILL_DELETE', 2, NULL, 0);
INSERT INTO `menu` VALUES (174, b'0', '编辑砍价', NULL, 86, 2, NULL, NULL, b'0', b'0', NULL, '2020-02-14 22:26:20', 'YXSTOREBARGAIN_EDIT', 2, NULL, 0);
INSERT INTO `menu` VALUES (175, b'0', '删除砍价', NULL, 86, 999, NULL, NULL, b'0', b'0', NULL, '2020-02-14 22:26:40', 'YXSTOREBARGAIN_DELETE', 2, NULL, 0);
INSERT INTO `menu` VALUES (176, b'0', '财务管理', NULL, 0, 8, 'price', 'price', b'0', b'0', NULL, '2020-03-02 22:30:23', NULL, 1, NULL, 0);
INSERT INTO `menu` VALUES (177, b'0', '充值管理', 'shop/recharge/index', 176, 82, 'rec', 'recharge', b'0', b'0', 'Recharge', '2020-03-02 23:05:26', 'yxUserRecharge:list', 1, NULL, 0);
INSERT INTO `menu` VALUES (178, b'0', '门店管理', NULL, 0, 9, 'store', 'store', b'0', b'0', NULL, '2020-03-03 17:27:53', NULL, 1, NULL, 0);
INSERT INTO `menu` VALUES (179, b'0', '门店列表', 'shop/store/index', 178, 92, 'edit', 'storeinfo', b'0', b'0', 'Storeinfo', '2020-03-03 17:29:09', 'yxSystemStore:list', 1, NULL, 0);
INSERT INTO `menu` VALUES (180, b'0', '门店配置', 'shop/store/set', 178, 91, 'configure', 'storeset', b'0', b'0', 'Storeset', '2020-03-04 13:09:54', 'YXSYSTEMCONFIG_SELECT,YXSYSTEMCONFIG_SELECT', 1, '2020-08-06 23:05:23', 0);
INSERT INTO `menu` VALUES (181, b'0', '核销订单', 'shop/order/indext', 178, 95, 'order', 'ordert', b'1', b'0', 'Ordert', '2020-03-05 17:04:12', 'YXSTOREORDER_SELECT', 1, '2020-10-27 17:59:24', 0);
INSERT INTO `menu` VALUES (182, b'0', '充值金额配置', 'shop/set/recharge', 176, 83, 'money', 'rechargeset', b'0', b'0', 'Rechargeset', '2020-03-21 14:24:05', 'YXSYSTEMGROUPDATA_ALL,YXSYSTEMGROUPDATA_SELECT', 1, '2020-06-26 15:11:06', 0);
INSERT INTO `menu` VALUES (183, b'0', '店员列表', 'shop/storestaff/index', 178, 94, 'peoples', 'staff', b'0', b'0', 'Staff', '2020-03-22 14:11:36', 'yxSystemStoreStaff:list', 1, NULL, 0);
INSERT INTO `menu` VALUES (184, b'0', '新增菜单', NULL, 49, 0, 'add', NULL, b'0', b'0', NULL, '2020-06-14 20:10:02', 'YxWechatMenu_CREATE', 2, NULL, 0);
INSERT INTO `menu` VALUES (185, b'0', '模板新增', NULL, 82, 1, NULL, NULL, b'0', b'0', NULL, '2020-06-14 20:14:17', 'yxWechatTemplate:add', 2, NULL, 0);
INSERT INTO `menu` VALUES (186, b'0', '模板修改', NULL, 82, 2, NULL, NULL, b'0', b'0', NULL, '2020-06-14 20:14:46', 'yxWechatTemplate:edit', 2, NULL, 0);
INSERT INTO `menu` VALUES (187, b'0', '模板删除', NULL, 82, 3, NULL, NULL, b'0', b'0', NULL, '2020-06-14 20:15:10', 'yxWechatTemplate:del', 2, NULL, 0);
INSERT INTO `menu` VALUES (188, b'0', '新增幻灯片', NULL, 56, 1, NULL, NULL, b'0', b'0', NULL, '2020-06-14 20:33:48', 'YXSYSTEMGROUPDATA_CREATE', 2, NULL, 0);
INSERT INTO `menu` VALUES (189, b'0', '修改幻灯片', NULL, 56, 2, NULL, NULL, b'0', b'0', NULL, '2020-06-14 20:35:11', 'YXSYSTEMGROUPDATA_EDIT', 2, NULL, 0);
INSERT INTO `menu` VALUES (190, b'0', '删除幻灯片', NULL, 56, 3, NULL, NULL, b'0', b'0', NULL, '2020-06-14 20:40:30', 'YXSYSTEMGROUPDATA_DELETE', 2, NULL, 0);
INSERT INTO `menu` VALUES (191, b'0', '新增导航按钮', NULL, 57, 1, NULL, NULL, b'0', b'0', NULL, '2020-06-14 20:42:43', 'YXSYSTEMGROUPDATA_CREATE', 2, NULL, 0);
INSERT INTO `menu` VALUES (192, b'0', '修改导航按钮', NULL, 57, 2, NULL, NULL, b'0', b'0', NULL, '2020-06-14 20:43:53', 'YXSYSTEMGROUPDATA_EDIT', 2, NULL, 0);
INSERT INTO `menu` VALUES (193, b'0', '删除导航按钮', NULL, 57, 3, NULL, NULL, b'0', b'0', NULL, '2020-06-14 20:44:43', 'YXSYSTEMGROUPDATA_DELETE', 2, NULL, 0);
INSERT INTO `menu` VALUES (194, b'0', '新增滚动新闻', NULL, 59, 1, NULL, NULL, b'0', b'0', NULL, '2020-06-14 20:48:32', 'YXSYSTEMGROUPDATA_CREATE', 2, NULL, 0);
INSERT INTO `menu` VALUES (195, b'0', '修改滚动新闻', NULL, 59, 2, NULL, NULL, b'0', b'0', NULL, '2020-06-14 20:48:52', 'YXSYSTEMGROUPDATA_EDIT', 2, NULL, 0);
INSERT INTO `menu` VALUES (196, b'0', '删除滚动新闻', NULL, 59, 3, NULL, NULL, b'0', b'0', NULL, '2020-06-14 20:49:32', 'YXSYSTEMGROUPDATA_DELETE', 2, NULL, 0);
INSERT INTO `menu` VALUES (197, b'0', '新增热门搜索', NULL, 60, 1, NULL, NULL, b'0', b'0', NULL, '2020-06-14 21:14:25', 'YXSYSTEMGROUPDATA_CREATE', 2, NULL, 0);
INSERT INTO `menu` VALUES (198, b'0', '修改热门搜索', NULL, 60, 2, NULL, NULL, b'0', b'0', NULL, '2020-06-14 21:14:55', 'YXSYSTEMGROUPDATA_EDIT', 2, NULL, 0);
INSERT INTO `menu` VALUES (199, b'0', '删除热门搜索', NULL, 60, 3, NULL, NULL, b'0', b'0', NULL, '2020-06-14 21:15:25', 'YXSYSTEMGROUPDATA_DELETE', 2, NULL, 0);
INSERT INTO `menu` VALUES (200, b'0', '新增个人中心菜单', NULL, 61, 1, NULL, NULL, b'0', b'0', NULL, '2020-06-14 21:17:47', 'YXSYSTEMGROUPDATA_CREATE', 2, NULL, 0);
INSERT INTO `menu` VALUES (201, b'0', '修改个人中心菜单', NULL, 61, 2, NULL, NULL, b'0', b'0', NULL, '2020-06-14 21:18:37', 'YXSYSTEMGROUPDATA_EDIT', 2, NULL, 0);
INSERT INTO `menu` VALUES (202, b'0', '删除个人中心菜单', NULL, 61, 3, NULL, NULL, b'0', b'0', NULL, '2020-06-14 21:19:47', 'YXSYSTEMGROUPDATA_DELETE', 2, NULL, 0);
INSERT INTO `menu` VALUES (203, b'0', '新增积分配置', NULL, 68, 1, NULL, NULL, b'0', b'0', NULL, '2020-06-14 21:20:47', 'YXSYSTEMCONFIG_CREATE', 2, NULL, 0);
INSERT INTO `menu` VALUES (204, b'0', '新增签到天数', NULL, 79, 1, NULL, NULL, b'0', b'0', NULL, '2020-06-14 21:26:32', 'YXSYSTEMGROUPDATA_CREATE', 2, NULL, 0);
INSERT INTO `menu` VALUES (205, b'0', '修改签到天数', NULL, 79, 2, NULL, NULL, b'0', b'0', NULL, '2020-06-14 22:26:32', 'YXSYSTEMGROUPDATA_EDIT', 2, NULL, 0);
INSERT INTO `menu` VALUES (206, b'0', '删除签到天数', NULL, 79, 3, NULL, NULL, b'0', b'0', NULL, '2020-06-14 22:26:52', 'YXSYSTEMGROUPDATA_DELETE', 2, NULL, 0);
INSERT INTO `menu` VALUES (207, b'0', '新增邮费配置', NULL, 125, 1, NULL, NULL, b'0', b'0', NULL, '2020-06-14 21:29:20', 'YXSYSTEMCONFIG_CREATE', 2, NULL, 1);
INSERT INTO `menu` VALUES (208, b'0', '新增充值金额', NULL, 182, 1, NULL, NULL, b'0', b'0', NULL, '2020-06-14 21:30:59', 'YXSYSTEMGROUPDATA_CREATE', 2, NULL, 0);
INSERT INTO `menu` VALUES (209, b'0', '修改充值金额', NULL, 182, 2, NULL, NULL, b'0', b'0', NULL, '2020-06-14 22:30:30', 'YXSYSTEMGROUPDATA_EDIT', 2, NULL, 0);
INSERT INTO `menu` VALUES (210, b'0', '删除充值金额', NULL, 182, 3, NULL, NULL, b'0', b'0', NULL, '2020-06-14 22:30:59', 'YXSYSTEMGROUPDATA_DELETE', 2, NULL, 0);
INSERT INTO `menu` VALUES (211, b'0', '新增秒杀配置', NULL, 84, 1, NULL, NULL, b'0', b'0', NULL, '2020-06-14 21:43:36', 'YXSYSTEMGROUPDATA_CREATE', 2, NULL, 0);
INSERT INTO `menu` VALUES (212, b'0', '修改秒杀配置', NULL, 84, 2, NULL, NULL, b'0', b'0', NULL, '2020-06-14 21:43:56', 'YXSYSTEMGROUPDATA_EDIT', 2, NULL, 0);
INSERT INTO `menu` VALUES (213, b'0', '删除秒杀配置', NULL, 84, 3, NULL, NULL, b'0', b'0', NULL, '2020-06-14 22:23:36', 'YXSYSTEMGROUPDATA_DELETE', 2, NULL, 0);
INSERT INTO `menu` VALUES (214, b'0', '新增分销配置', NULL, 70, 1, NULL, NULL, b'0', b'0', NULL, '2020-06-14 21:46:46', 'YXSYSTEMCONFIG_CREATE', 2, NULL, 0);
INSERT INTO `menu` VALUES (215, b'0', '提现审核', NULL, 72, 1, NULL, NULL, b'0', b'0', NULL, '2020-06-14 21:56:11', 'YXUSEREXTRACT_EDIT', 2, NULL, 0);
INSERT INTO `menu` VALUES (216, b'0', '删除充值', NULL, 177, 1, NULL, NULL, b'0', b'0', NULL, '2020-06-14 21:59:11', 'yxUserRecharge:del', 2, NULL, 0);
INSERT INTO `menu` VALUES (217, b'0', '导出充值', NULL, 177, 2, NULL, NULL, b'0', b'0', NULL, '2020-06-14 21:59:54', 'yxUserRecharge:list', 2, NULL, 0);
INSERT INTO `menu` VALUES (218, b'0', '新增门店', NULL, 179, 1, NULL, NULL, b'0', b'0', NULL, '2020-06-14 22:01:57', 'yxSystemStore:add', 2, NULL, 0);
INSERT INTO `menu` VALUES (219, b'0', '修改门店', NULL, 179, 2, NULL, NULL, b'0', b'0', NULL, '2020-06-14 22:02:30', 'yxSystemStore:edit', 2, NULL, 0);
INSERT INTO `menu` VALUES (220, b'0', '删除门店', NULL, 179, 3, NULL, NULL, b'0', b'0', NULL, '2020-06-14 22:02:57', 'yxSystemStore:del', 2, NULL, 0);
INSERT INTO `menu` VALUES (221, b'0', '新增门店配置', NULL, 180, 1, NULL, NULL, b'0', b'0', NULL, '2020-06-14 22:04:25', 'YXSYSTEMCONFIG_CREATE', 2, NULL, 0);
INSERT INTO `menu` VALUES (222, b'0', '编辑核销订单', NULL, 181, 1, NULL, NULL, b'0', b'0', NULL, '2020-06-14 22:07:26', 'YXSTOREORDER_EDIT', 2, NULL, 0);
INSERT INTO `menu` VALUES (223, b'0', '新增店员', NULL, 183, 1, NULL, NULL, b'0', b'0', NULL, '2020-06-14 22:11:13', 'yxSystemStoreStaff:add', 2, NULL, 0);
INSERT INTO `menu` VALUES (224, b'0', '修改店员', NULL, 183, 2, NULL, NULL, b'0', b'0', NULL, '2020-06-14 22:11:37', 'yxSystemStoreStaff:edit', 2, NULL, 0);
INSERT INTO `menu` VALUES (225, b'0', '删除店员', NULL, 183, 3, NULL, NULL, b'0', b'0', NULL, '2020-06-14 22:11:59', 'yxSystemStoreStaff:del', 2, NULL, 0);
INSERT INTO `menu` VALUES (229, b'0', '电子券管理', NULL, 0, 5, 'coupon', 'syscoupon', b'0', b'0', '', '2020-06-26 15:15:47', NULL, 1, '2020-06-26 15:22:16', 0);
COMMIT;

-- ----------------------------
-- Table structure for picture
-- ----------------------------
DROP TABLE IF EXISTS `picture`;
CREATE TABLE `picture` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_time` datetime DEFAULT NULL COMMENT '上传日期',
  `delete_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '删除的URL',
  `filename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图片名称',
  `height` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图片高度',
  `size` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图片大小',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图片地址',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名称',
  `width` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图片宽度',
  `md5code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文件的MD5值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='Sm.Ms图床';

-- ----------------------------
-- Records of picture
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for qiniu_config
-- ----------------------------
DROP TABLE IF EXISTS `qiniu_config`;
CREATE TABLE `qiniu_config` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `access_key` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT 'accessKey',
  `bucket` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'Bucket 识别符',
  `host` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '外链域名',
  `secret_key` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT 'secretKey',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '空间类型',
  `zone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '机房',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='七牛云配置';

-- ----------------------------
-- Records of qiniu_config
-- ----------------------------
BEGIN;
INSERT INTO `qiniu_config` VALUES (1, 'OJou_rI3QATSsY0MMqkpq0FPvAXpEjs1o4KHuvCx', 'yixiang', 'https://image.dayouqiantu.cn', 'XwNNqLeAnXP754LhkJYmBM6nu1djcc1LUXTDYylY', '公开', '华东');
COMMIT;

-- ----------------------------
-- Table structure for qiniu_content
-- ----------------------------
DROP TABLE IF EXISTS `qiniu_content`;
CREATE TABLE `qiniu_content` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `bucket` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'Bucket 识别符',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文件名称',
  `size` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文件大小',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文件类型：私有或公开',
  `update_time` datetime DEFAULT NULL COMMENT '上传或同步的时间',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文件url',
  `suffix` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='七牛云文件存储';

-- ----------------------------
-- Records of qiniu_content
-- ----------------------------
BEGIN;
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
INSERT INTO `qiniu_content` VALUES (15, 'yixiang', 'goods', '2.82KB   ', '公开', '2020-06-26 18:20:58', 'https://image.dayouqiantu.cn/goods.png', 'png');
INSERT INTO `qiniu_content` VALUES (16, 'yixiang', 'coupon', '2.92KB   ', '公开', '2020-06-26 18:20:58', 'https://image.dayouqiantu.cn/coupon.png', 'png');
INSERT INTO `qiniu_content` VALUES (17, 'yixiang', 'buy', '2.59KB   ', '公开', '2020-06-26 18:20:58', 'https://image.dayouqiantu.cn/buy.png', 'png');
INSERT INTO `qiniu_content` VALUES (18, 'yixiang', 'collect', '3.65KB   ', '公开', '2020-06-26 18:20:58', 'https://image.dayouqiantu.cn/collect.png', 'png');
INSERT INTO `qiniu_content` VALUES (19, 'yixiang', 'bargin', '3.39KB   ', '公开', '2020-06-26 18:24:51', 'https://image.dayouqiantu.cn/bargin.png', 'png');
INSERT INTO `qiniu_content` VALUES (20, 'yixiang', 'add', '3.17KB   ', '公开', '2020-06-26 18:24:51', 'https://image.dayouqiantu.cn/add.png', 'png');
INSERT INTO `qiniu_content` VALUES (21, 'yixiang', 'sign', '2.63KB   ', '公开', '2020-06-26 18:25:07', 'https://image.dayouqiantu.cn/sign.png', 'png');
INSERT INTO `qiniu_content` VALUES (22, 'yixiang', 'sekill', '3.54KB   ', '公开', '2020-06-26 18:25:07', 'https://image.dayouqiantu.cn/sekill.png', 'png');
INSERT INTO `qiniu_content` VALUES (23, 'yixiang', 'pink', '3.25KB   ', '公开', '2020-06-26 18:25:07', 'https://image.dayouqiantu.cn/pink.png', 'png');
INSERT INTO `qiniu_content` VALUES (24, 'yixiang', 'news', '2.70KB   ', '公开', '2020-06-26 18:25:07', 'https://image.dayouqiantu.cn/news.png', 'png');
INSERT INTO `qiniu_content` VALUES (25, 'yixiang', '5ca04fa9c08ef', '314.06KB   ', '公开', '2020-06-26 18:25:23', 'https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg', 'jpg');
INSERT INTO `qiniu_content` VALUES (26, 'yixiang', '5ca0786c5d2c1', '388.24KB   ', '公开', '2020-06-26 18:25:23', 'https://image.dayouqiantu.cn/5ca0786c5d2c1.jpg', 'jpg');
INSERT INTO `qiniu_content` VALUES (27, 'yixiang', '5ca081af6183f', '339.37KB   ', '公开', '2020-06-26 18:25:23', 'https://image.dayouqiantu.cn/5ca081af6183f.jpg', 'jpg');
COMMIT;

-- ----------------------------
-- Table structure for quartz_job
-- ----------------------------
DROP TABLE IF EXISTS `quartz_job`;
CREATE TABLE `quartz_job` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `bean_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'Spring Bean名称',
  `cron_expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'cron 表达式',
  `is_pause` bit(1) DEFAULT NULL COMMENT '状态：1暂停、0启用',
  `job_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '任务名称',
  `method_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '方法名称',
  `params` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '参数',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL,
  `is_del` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='定时任务';

-- ----------------------------
-- Records of quartz_job
-- ----------------------------
BEGIN;
INSERT INTO `quartz_job` VALUES (1, 'visitsTask', '0 0 0 * * ?', b'0', '更新访客记录', 'run', NULL, '每日0点创建新的访客记录', '2019-01-08 14:53:31', NULL, 0);
INSERT INTO `quartz_job` VALUES (2, 'testTask', '0/5 * * * * ?', b'1', '测试1', 'run1', 'test', '带参测试，多参使用json', '2019-08-22 14:08:29', NULL, 0);
INSERT INTO `quartz_job` VALUES (3, 'testTask', '0/5 * * * * ?', b'1', '测试43', 'run', '44', '不带参测试', '2019-09-26 16:44:39', NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for quartz_log
-- ----------------------------
DROP TABLE IF EXISTS `quartz_log`;
CREATE TABLE `quartz_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `baen_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `cron_expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `exception_detail` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `is_success` bit(1) DEFAULT NULL,
  `job_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `method_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `params` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `time` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_del` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='定时任务日志';

-- ----------------------------
-- Records of quartz_log
-- ----------------------------
BEGIN;
INSERT INTO `quartz_log` VALUES (1, 'visitsTask', '2020-01-10 00:00:00', '0 0 0 * * ?', NULL, b'1', '更新访客记录', 'run', NULL, 160, NULL, 0);
INSERT INTO `quartz_log` VALUES (2, 'visitsTask', '2020-03-10 00:00:00', '0 0 0 * * ?', NULL, b'1', '更新访客记录', 'run', NULL, 42, NULL, 0);
INSERT INTO `quartz_log` VALUES (3, 'visitsTask', '2020-04-02 00:00:00', '0 0 0 * * ?', NULL, b'1', '更新访客记录', 'run', NULL, 60, NULL, 0);
INSERT INTO `quartz_log` VALUES (4, 'visitsTask', '2020-09-01 00:00:00', '0 0 0 * * ?', NULL, b'1', '更新访客记录', 'run', NULL, 36, NULL, 0);
INSERT INTO `quartz_log` VALUES (5, 'visitsTask', '2020-09-02 00:00:00', '0 0 0 * * ?', NULL, b'1', '更新访客记录', 'run', NULL, 19, NULL, 0);
INSERT INTO `quartz_log` VALUES (6, 'visitsTask', '2020-09-03 00:00:00', '0 0 0 * * ?', NULL, b'1', '更新访客记录', 'run', NULL, 39, NULL, 0);
INSERT INTO `quartz_log` VALUES (7, 'visitsTask', '2020-09-04 00:00:00', '0 0 0 * * ?', NULL, b'1', '更新访客记录', 'run', NULL, 30, NULL, 0);
INSERT INTO `quartz_log` VALUES (8, 'visitsTask', '2020-09-05 00:00:00', '0 0 0 * * ?', NULL, b'1', '更新访客记录', 'run', NULL, 23, NULL, 0);
INSERT INTO `quartz_log` VALUES (9, 'visitsTask', '2020-09-06 00:00:00', '0 0 0 * * ?', NULL, b'1', '更新访客记录', 'run', NULL, 70, NULL, 0);
INSERT INTO `quartz_log` VALUES (10, 'visitsTask', '2020-09-07 00:00:00', '0 0 0 * * ?', NULL, b'1', '更新访客记录', 'run', NULL, 18, NULL, 0);
INSERT INTO `quartz_log` VALUES (11, 'visitsTask', '2020-09-08 00:00:00', '0 0 0 * * ?', NULL, b'1', '更新访客记录', 'run', NULL, 14, NULL, 0);
INSERT INTO `quartz_log` VALUES (12, 'visitsTask', '2020-09-09 00:00:00', '0 0 0 * * ?', NULL, b'1', '更新访客记录', 'run', NULL, 31, NULL, 0);
INSERT INTO `quartz_log` VALUES (13, 'visitsTask', '2020-09-10 00:00:00', '0 0 0 * * ?', NULL, b'1', '更新访客记录', 'run', NULL, 40, NULL, 0);
INSERT INTO `quartz_log` VALUES (14, 'visitsTask', '2020-09-11 00:00:00', '0 0 0 * * ?', NULL, b'1', '更新访客记录', 'run', NULL, 20, NULL, 0);
INSERT INTO `quartz_log` VALUES (15, 'visitsTask', '2020-09-12 00:00:00', '0 0 0 * * ?', NULL, b'1', '更新访客记录', 'run', NULL, 39, NULL, 0);
INSERT INTO `quartz_log` VALUES (16, 'visitsTask', '2020-09-13 00:00:00', '0 0 0 * * ?', NULL, b'1', '更新访客记录', 'run', NULL, 15, NULL, 0);
INSERT INTO `quartz_log` VALUES (17, 'visitsTask', '2020-09-14 00:00:00', '0 0 0 * * ?', NULL, b'1', '更新访客记录', 'run', NULL, 15, NULL, 0);
INSERT INTO `quartz_log` VALUES (18, 'visitsTask', '2020-09-15 00:00:00', '0 0 0 * * ?', NULL, b'1', '更新访客记录', 'run', NULL, 35, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `data_scope` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '数据权限',
  `level` int DEFAULT NULL COMMENT '角色级别',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `permission` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '功能权限',
  `update_time` datetime DEFAULT NULL,
  `is_del` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES (1, '超级管理员', '-', '全部', 1, '2018-11-23 11:04:37', 'admin', NULL, 0);
INSERT INTO `role` VALUES (2, '普通用户', '-', '本级', 2, '2018-11-23 13:09:06', 'common', NULL, 0);
INSERT INTO `role` VALUES (3, '管理员2', '222', '全部', 3, '2020-01-31 16:53:25', '22', NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for roles_depts
-- ----------------------------
DROP TABLE IF EXISTS `roles_depts`;
CREATE TABLE `roles_depts` (
  `role_id` bigint NOT NULL,
  `dept_id` bigint NOT NULL,
  PRIMARY KEY (`role_id`,`dept_id`) USING BTREE,
  KEY `FK7qg6itn5ajdoa9h9o78v9ksur` (`dept_id`) USING BTREE,
  CONSTRAINT `FK7qg6itn5ajdoa9h9o78v9ksur` FOREIGN KEY (`dept_id`) REFERENCES `dept` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKrg1ci4cxxfbja0sb0pddju7k` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色部门关联';

-- ----------------------------
-- Records of roles_depts
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for roles_menus
-- ----------------------------
DROP TABLE IF EXISTS `roles_menus`;
CREATE TABLE `roles_menus` (
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`menu_id`,`role_id`) USING BTREE,
  KEY `FKcngg2qadojhi3a651a5adkvbq` (`role_id`) USING BTREE,
  CONSTRAINT `FKo7wsmlrrxb2osfaoavp46rv2r` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKtag324maketmxffly3pdyh193` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色菜单关联';

-- ----------------------------
-- Records of roles_menus
-- ----------------------------
BEGIN;
INSERT INTO `roles_menus` VALUES (1, 1);
INSERT INTO `roles_menus` VALUES (2, 1);
INSERT INTO `roles_menus` VALUES (3, 1);
INSERT INTO `roles_menus` VALUES (5, 1);
INSERT INTO `roles_menus` VALUES (6, 1);
INSERT INTO `roles_menus` VALUES (7, 1);
INSERT INTO `roles_menus` VALUES (9, 1);
INSERT INTO `roles_menus` VALUES (14, 1);
INSERT INTO `roles_menus` VALUES (18, 1);
INSERT INTO `roles_menus` VALUES (19, 1);
INSERT INTO `roles_menus` VALUES (28, 1);
INSERT INTO `roles_menus` VALUES (30, 1);
INSERT INTO `roles_menus` VALUES (32, 1);
INSERT INTO `roles_menus` VALUES (35, 1);
INSERT INTO `roles_menus` VALUES (36, 1);
INSERT INTO `roles_menus` VALUES (37, 1);
INSERT INTO `roles_menus` VALUES (39, 1);
INSERT INTO `roles_menus` VALUES (40, 1);
INSERT INTO `roles_menus` VALUES (41, 1);
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
INSERT INTO `roles_menus` VALUES (59, 1);
INSERT INTO `roles_menus` VALUES (60, 1);
INSERT INTO `roles_menus` VALUES (61, 1);
INSERT INTO `roles_menus` VALUES (62, 1);
INSERT INTO `roles_menus` VALUES (63, 1);
INSERT INTO `roles_menus` VALUES (64, 1);
INSERT INTO `roles_menus` VALUES (65, 1);
INSERT INTO `roles_menus` VALUES (66, 1);
INSERT INTO `roles_menus` VALUES (69, 1);
INSERT INTO `roles_menus` VALUES (70, 1);
INSERT INTO `roles_menus` VALUES (72, 1);
INSERT INTO `roles_menus` VALUES (73, 1);
INSERT INTO `roles_menus` VALUES (74, 1);
INSERT INTO `roles_menus` VALUES (75, 1);
INSERT INTO `roles_menus` VALUES (76, 1);
INSERT INTO `roles_menus` VALUES (77, 1);
INSERT INTO `roles_menus` VALUES (78, 1);
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
INSERT INTO `roles_menus` VALUES (123, 1);
INSERT INTO `roles_menus` VALUES (124, 1);
INSERT INTO `roles_menus` VALUES (126, 1);
INSERT INTO `roles_menus` VALUES (127, 1);
INSERT INTO `roles_menus` VALUES (128, 1);
INSERT INTO `roles_menus` VALUES (129, 1);
INSERT INTO `roles_menus` VALUES (130, 1);
INSERT INTO `roles_menus` VALUES (131, 1);
INSERT INTO `roles_menus` VALUES (132, 1);
INSERT INTO `roles_menus` VALUES (133, 1);
INSERT INTO `roles_menus` VALUES (134, 1);
INSERT INTO `roles_menus` VALUES (135, 1);
INSERT INTO `roles_menus` VALUES (136, 1);
INSERT INTO `roles_menus` VALUES (137, 1);
INSERT INTO `roles_menus` VALUES (138, 1);
INSERT INTO `roles_menus` VALUES (139, 1);
INSERT INTO `roles_menus` VALUES (140, 1);
INSERT INTO `roles_menus` VALUES (141, 1);
INSERT INTO `roles_menus` VALUES (142, 1);
INSERT INTO `roles_menus` VALUES (143, 1);
INSERT INTO `roles_menus` VALUES (144, 1);
INSERT INTO `roles_menus` VALUES (147, 1);
INSERT INTO `roles_menus` VALUES (148, 1);
INSERT INTO `roles_menus` VALUES (149, 1);
INSERT INTO `roles_menus` VALUES (150, 1);
INSERT INTO `roles_menus` VALUES (151, 1);
INSERT INTO `roles_menus` VALUES (152, 1);
INSERT INTO `roles_menus` VALUES (153, 1);
INSERT INTO `roles_menus` VALUES (154, 1);
INSERT INTO `roles_menus` VALUES (155, 1);
INSERT INTO `roles_menus` VALUES (156, 1);
INSERT INTO `roles_menus` VALUES (157, 1);
INSERT INTO `roles_menus` VALUES (158, 1);
INSERT INTO `roles_menus` VALUES (159, 1);
INSERT INTO `roles_menus` VALUES (160, 1);
INSERT INTO `roles_menus` VALUES (161, 1);
INSERT INTO `roles_menus` VALUES (162, 1);
INSERT INTO `roles_menus` VALUES (163, 1);
INSERT INTO `roles_menus` VALUES (164, 1);
INSERT INTO `roles_menus` VALUES (165, 1);
INSERT INTO `roles_menus` VALUES (166, 1);
INSERT INTO `roles_menus` VALUES (167, 1);
INSERT INTO `roles_menus` VALUES (168, 1);
INSERT INTO `roles_menus` VALUES (169, 1);
INSERT INTO `roles_menus` VALUES (170, 1);
INSERT INTO `roles_menus` VALUES (171, 1);
INSERT INTO `roles_menus` VALUES (172, 1);
INSERT INTO `roles_menus` VALUES (173, 1);
INSERT INTO `roles_menus` VALUES (174, 1);
INSERT INTO `roles_menus` VALUES (175, 1);
INSERT INTO `roles_menus` VALUES (176, 1);
INSERT INTO `roles_menus` VALUES (177, 1);
INSERT INTO `roles_menus` VALUES (178, 1);
INSERT INTO `roles_menus` VALUES (179, 1);
INSERT INTO `roles_menus` VALUES (180, 1);
INSERT INTO `roles_menus` VALUES (181, 1);
INSERT INTO `roles_menus` VALUES (182, 1);
INSERT INTO `roles_menus` VALUES (183, 1);
INSERT INTO `roles_menus` VALUES (184, 1);
INSERT INTO `roles_menus` VALUES (185, 1);
INSERT INTO `roles_menus` VALUES (186, 1);
INSERT INTO `roles_menus` VALUES (187, 1);
INSERT INTO `roles_menus` VALUES (188, 1);
INSERT INTO `roles_menus` VALUES (189, 1);
INSERT INTO `roles_menus` VALUES (190, 1);
INSERT INTO `roles_menus` VALUES (191, 1);
INSERT INTO `roles_menus` VALUES (192, 1);
INSERT INTO `roles_menus` VALUES (193, 1);
INSERT INTO `roles_menus` VALUES (194, 1);
INSERT INTO `roles_menus` VALUES (195, 1);
INSERT INTO `roles_menus` VALUES (196, 1);
INSERT INTO `roles_menus` VALUES (197, 1);
INSERT INTO `roles_menus` VALUES (198, 1);
INSERT INTO `roles_menus` VALUES (199, 1);
INSERT INTO `roles_menus` VALUES (200, 1);
INSERT INTO `roles_menus` VALUES (201, 1);
INSERT INTO `roles_menus` VALUES (202, 1);
INSERT INTO `roles_menus` VALUES (208, 1);
INSERT INTO `roles_menus` VALUES (209, 1);
INSERT INTO `roles_menus` VALUES (210, 1);
INSERT INTO `roles_menus` VALUES (211, 1);
INSERT INTO `roles_menus` VALUES (212, 1);
INSERT INTO `roles_menus` VALUES (213, 1);
INSERT INTO `roles_menus` VALUES (214, 1);
INSERT INTO `roles_menus` VALUES (215, 1);
INSERT INTO `roles_menus` VALUES (216, 1);
INSERT INTO `roles_menus` VALUES (217, 1);
INSERT INTO `roles_menus` VALUES (218, 1);
INSERT INTO `roles_menus` VALUES (219, 1);
INSERT INTO `roles_menus` VALUES (220, 1);
INSERT INTO `roles_menus` VALUES (221, 1);
INSERT INTO `roles_menus` VALUES (222, 1);
INSERT INTO `roles_menus` VALUES (223, 1);
INSERT INTO `roles_menus` VALUES (224, 1);
INSERT INTO `roles_menus` VALUES (225, 1);
INSERT INTO `roles_menus` VALUES (229, 1);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `avatar_id` bigint DEFAULT NULL COMMENT '头像',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `enabled` bigint DEFAULT NULL COMMENT '状态：1启用、0禁用',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名',
  `dept_id` bigint DEFAULT NULL COMMENT '部门名称',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机号码',
  `job_id` bigint DEFAULT NULL COMMENT '岗位名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `last_password_reset_time` datetime DEFAULT NULL COMMENT '最后修改密码的日期',
  `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_del` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `UK_kpubos9gc2cvtkb0thktkbkes` (`email`) USING BTREE,
  UNIQUE KEY `username` (`username`) USING BTREE,
  KEY `FK5rwmryny6jthaaxkogownknqp` (`dept_id`) USING BTREE,
  KEY `FKfftoc2abhot8f2wu6cl9a5iky` (`job_id`) USING BTREE,
  KEY `FKpq2dhypk2qgt68nauh2by22jb` (`avatar_id`) USING BTREE,
  CONSTRAINT `FK5rwmryny6jthaaxkogownknqp` FOREIGN KEY (`dept_id`) REFERENCES `dept` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKfftoc2abhot8f2wu6cl9a5iky` FOREIGN KEY (`job_id`) REFERENCES `job` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKpq2dhypk2qgt68nauh2by22jb` FOREIGN KEY (`avatar_id`) REFERENCES `user_avatar` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='系统用户';

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 1, 'yshop@qq.com', 1, '$2a$10$fP.426qKaTmix50Oln8L.uav55gELhAd0Eg66Av4oG86u8km7D/Ky', 'admin', 2, '18888888888', 11, '2018-08-23 09:11:56', '2019-05-18 17:34:21', '管理员', '男', '2020-06-27 12:05:56', 0);
INSERT INTO `user` VALUES (3, NULL, 'test@yshopnet', 1, '$2a$10$HhxyGZy.ulf3RvAwaHUGb.k.2i9PBpv4YbLMJWp8pES7pPhTyRCF.', 'test', 2, '17777777777', 12, '2018-12-27 20:05:26', '2019-04-01 09:15:24', '测试', '男', NULL, 0);
INSERT INTO `user` VALUES (4, NULL, 'test2@qq.com', 1, '$2a$10$IjehtV8MiXb8ni.Qz0wBteE7FjVn49cEcsSj2.ZBUqqHjnC3umSh.', 'test2', 2, '15136175247', 11, '2020-02-15 20:39:16', NULL, 'test2', '男', NULL, 0);
INSERT INTO `user` VALUES (5, NULL, '444@qq.com', 0, '$2a$10$f/VH35NBOBszycV9KEA1HenQ0qVjazDm8LacQU9PO.A4UizFxLMuq', 'qqqqq', 11, '15136175249', 8, '2020-02-17 11:12:01', NULL, 'eeeeee', '男', NULL, 0);
INSERT INTO `user` VALUES (6, NULL, '666@qq.com', 0, '$2a$10$3Vlo24eOmHHW7.3vAjqPSusfIINNo4JiujzxgqsaoWLx/d5de/jEm', '7777', 8, '15136175246', 8, '2020-05-18 19:43:30', NULL, '777', '男', NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for user_avatar
-- ----------------------------
DROP TABLE IF EXISTS `user_avatar`;
CREATE TABLE `user_avatar` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `real_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '真实文件名',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '路径',
  `size` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '大小',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='系统用户头像';

-- ----------------------------
-- Records of user_avatar
-- ----------------------------
BEGIN;
INSERT INTO `user_avatar` VALUES (1, '20200627120555818410.png', 'D:\\yshop\\avatar\\20200627120555818410.png', '12.93KB   ', '2020-06-27 12:05:56');
COMMIT;

-- ----------------------------
-- Table structure for users_roles
-- ----------------------------
DROP TABLE IF EXISTS `users_roles`;
CREATE TABLE `users_roles` (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`) USING BTREE,
  KEY `FKq4eq273l04bpu4efj0jd0jb98` (`role_id`) USING BTREE,
  CONSTRAINT `FKgd3iendaoyh04b95ykqise6qh` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKt4v0rrweyk393bdgt107vdx0x` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户角色关联';

-- ----------------------------
-- Records of users_roles
-- ----------------------------
BEGIN;
INSERT INTO `users_roles` VALUES (1, 1);
INSERT INTO `users_roles` VALUES (3, 2);
INSERT INTO `users_roles` VALUES (4, 2);
INSERT INTO `users_roles` VALUES (5, 2);
INSERT INTO `users_roles` VALUES (6, 2);
COMMIT;

-- ----------------------------
-- Table structure for verification_code
-- ----------------------------
DROP TABLE IF EXISTS `verification_code`;
CREATE TABLE `verification_code` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '验证码',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `status` bit(1) DEFAULT NULL COMMENT '状态：1有效、0过期',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '验证码类型：email或者短信',
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '接收邮箱或者手机号码',
  `scenes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '业务名称：如重置邮箱、重置密码等',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='验证码';

-- ----------------------------
-- Records of verification_code
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for visits
-- ----------------------------
DROP TABLE IF EXISTS `visits`;
CREATE TABLE `visits` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ip_counts` bigint DEFAULT NULL,
  `pv_counts` bigint DEFAULT NULL,
  `week_day` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `UK_11aksgq87euk9bcyeesfs4vtp` (`date`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1266934639347150883 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='访客记录';

-- ----------------------------
-- Records of visits
-- ----------------------------
BEGIN;
INSERT INTO `visits` VALUES (1265104717297319937, '2020-05-26 10:17:24', '2020-05-26', 1, 1, 'Tue');
INSERT INTO `visits` VALUES (1265104717297319938, '2020-05-27 16:32:32', '2020-05-27', 1, 2, 'Wed');
INSERT INTO `visits` VALUES (1265104717297319939, '2020-05-29 14:18:55', '2020-05-29', 1, 1, 'Fri');
INSERT INTO `visits` VALUES (1266934639347150849, '2020-05-31 11:28:51', '2020-05-31', 1, 1, 'Sun');
INSERT INTO `visits` VALUES (1266934639347150850, '2020-06-01 15:37:07', '2020-06-01', 1, 1, 'Mon');
INSERT INTO `visits` VALUES (1266934639347150851, '2020-06-09 11:52:36', '2020-06-09', 1, 2, 'Tue');
INSERT INTO `visits` VALUES (1266934639347150852, '2020-06-12 16:23:50', '2020-06-12', 1, 2, 'Fri');
INSERT INTO `visits` VALUES (1266934639347150853, '2020-06-13 16:49:12', '2020-06-13', 1, 1, 'Sat');
INSERT INTO `visits` VALUES (1266934639347150854, '2020-06-25 16:12:58', '2020-06-25', 1, 8, 'Thu');
INSERT INTO `visits` VALUES (1266934639347150855, '2020-06-26 10:15:43', '2020-06-26', 2, 7, 'Fri');
INSERT INTO `visits` VALUES (1266934639347150856, '2020-06-27 11:38:14', '2020-06-27', 1, 5, 'Sat');
INSERT INTO `visits` VALUES (1266934639347150857, '2020-06-28 15:59:08', '2020-06-28', 1, 6, 'Sun');
INSERT INTO `visits` VALUES (1266934639347150858, '2020-06-29 09:19:53', '2020-06-29', 1, 4, 'Mon');
INSERT INTO `visits` VALUES (1266934639347150859, '2020-06-30 09:55:25', '2020-06-30', 1, 5, 'Tue');
INSERT INTO `visits` VALUES (1266934639347150860, '2020-07-01 10:02:51', '2020-07-01', 1, 9, 'Wed');
INSERT INTO `visits` VALUES (1266934639347150861, '2020-07-02 10:55:09', '2020-07-02', 1, 7, 'Thu');
INSERT INTO `visits` VALUES (1266934639347150862, '2020-07-03 10:40:46', '2020-07-03', 1, 8, 'Fri');
INSERT INTO `visits` VALUES (1266934639347150863, '2020-07-04 10:03:30', '2020-07-04', 1, 4, 'Sat');
INSERT INTO `visits` VALUES (1266934639347150864, '2020-07-05 09:56:06', '2020-07-05', 1, 4, 'Sun');
INSERT INTO `visits` VALUES (1266934639347150865, '2020-07-06 14:35:25', '2020-07-06', 1, 2, 'Mon');
INSERT INTO `visits` VALUES (1266934639347150866, '2020-09-01 00:00:00', '2020-09-01', 4, 4, 'Tue');
INSERT INTO `visits` VALUES (1266934639347150867, '2020-09-02 00:00:00', '2020-09-02', 1, 4, 'Wed');
INSERT INTO `visits` VALUES (1266934639347150868, '2020-09-03 00:00:00', '2020-09-03', 1, 2, 'Thu');
INSERT INTO `visits` VALUES (1266934639347150869, '2020-09-04 00:00:00', '2020-09-04', 1, 3, 'Fri');
INSERT INTO `visits` VALUES (1266934639347150870, '2020-09-05 00:00:00', '2020-09-05', 5, 2, 'Sat');
INSERT INTO `visits` VALUES (1266934639347150871, '2020-09-06 00:00:00', '2020-09-06', 4, 2, 'Sun');
INSERT INTO `visits` VALUES (1266934639347150872, '2020-09-07 00:00:00', '2020-09-07', 5, 3, 'Mon');
INSERT INTO `visits` VALUES (1266934639347150873, '2020-09-08 00:00:00', '2020-09-08', 7, 15, 'Tue');
INSERT INTO `visits` VALUES (1266934639347150874, '2020-09-09 00:00:00', '2020-09-09', 5, 3, 'Wed');
INSERT INTO `visits` VALUES (1266934639347150875, '2020-09-10 00:00:00', '2020-09-10', 2, 4, 'Thu');
INSERT INTO `visits` VALUES (1266934639347150876, '2020-09-11 00:00:00', '2020-09-11', 12, 5, 'Fri');
INSERT INTO `visits` VALUES (1266934639347150877, '2020-09-12 00:00:00', '2020-09-12', 3, 4, 'Sat');
INSERT INTO `visits` VALUES (1266934639347150878, '2020-09-13 00:00:00', '2020-09-13', 10, 4, 'Sun');
INSERT INTO `visits` VALUES (1266934639347150879, '2020-09-14 00:00:00', '2020-09-14', 1, 1, 'Mon');
INSERT INTO `visits` VALUES (1266934639347150880, '2020-09-15 00:00:00', '2020-09-15', 1, 1, 'Tue');
INSERT INTO `visits` VALUES (1266934639347150881, '2021-04-03 21:42:20', '2021-04-03', 2, 5, 'Sat');
INSERT INTO `visits` VALUES (1266934639347150882, '2021-04-04 11:04:21', '2021-04-04', 1, 3, 'Sun');
COMMIT;

-- ----------------------------
-- Table structure for yx_article
-- ----------------------------
DROP TABLE IF EXISTS `yx_article`;
CREATE TABLE `yx_article` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '文章管理ID',
  `cid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '1' COMMENT '分类id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章标题',
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文章作者',
  `image_input` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章图片',
  `synopsis` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文章简介',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `share_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文章分享标题',
  `share_synopsis` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文章分享简介',
  `visit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '浏览次数',
  `sort` int unsigned DEFAULT '0' COMMENT '排序',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '原文链接',
  `status` tinyint unsigned DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime DEFAULT NULL,
  `is_del` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='文章管理表';

-- ----------------------------
-- Records of yx_article
-- ----------------------------
BEGIN;
INSERT INTO `yx_article` VALUES (2, '', 'yshop2版本上线了', 'yshop', 'http://127.0.0.1:8000/file/pic/20200527164526793230.png', 'yshop1.3版本上线了', '<h3 data-v-01881cfc=\"\" style=\"text-align: center;\"><p>yshop1.3版本上线了</p></h3>', '', '', '8', NULL, '', NULL, '2019-08-22 12:26:00', '2020-06-25 19:12:53', 0);
INSERT INTO `yx_article` VALUES (3, '', '44', '444', 'http://127.0.0.1:8000/file/pic/20200527164543489004.png', '4444', '<p>55555</p>', '', '', '', 0, '', NULL, '2020-06-25 19:12:35', NULL, 1);
INSERT INTO `yx_article` VALUES (4, '', 'yshop3.0', 'yshop', 'https://image.dayouqiantu.cn/news.png', 'yshop3.0', '<p><br/></p><p><img src=\"https://image.dayouqiantu.cn/add.png\"/></p><p>yshop3.0</p>', '', '', '3', 0, '', NULL, '2020-07-02 12:15:13', '2020-07-03 17:58:19', 0);
COMMIT;

-- ----------------------------
-- Table structure for yx_express
-- ----------------------------
DROP TABLE IF EXISTS `yx_express`;
CREATE TABLE `yx_express` (
  `id` mediumint unsigned NOT NULL AUTO_INCREMENT COMMENT '快递公司id',
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '快递公司简称',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '快递公司全称',
  `sort` int NOT NULL DEFAULT '0' COMMENT '排序',
  `is_show` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否显示',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_del` tinyint DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `is_show` (`is_show`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=433 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='快递公司表';

-- ----------------------------
-- Records of yx_express
-- ----------------------------
BEGIN;
INSERT INTO `yx_express` VALUES (426, 'STO', '申通快递', 0, 0, NULL, NULL, 1);
INSERT INTO `yx_express` VALUES (427, 'SF', '顺丰速运', 0, 0, NULL, NULL, 0);
INSERT INTO `yx_express` VALUES (428, 'ZTO', '中通快递', 0, 0, NULL, '2021-04-04 15:08:36', 0);
INSERT INTO `yx_express` VALUES (429, 'YTO', '圆通速递', 0, 0, NULL, '2020-09-06 09:59:36', 0);
INSERT INTO `yx_express` VALUES (430, '555', '555555', 0, 0, NULL, NULL, 1);
INSERT INTO `yx_express` VALUES (431, '555', '5555', 0, 0, NULL, NULL, 1);
INSERT INTO `yx_express` VALUES (432, '555', '555', 0, 0, '2020-06-26 12:29:48', NULL, 1);
COMMIT;

-- ----------------------------
-- Table structure for yx_material
-- ----------------------------
DROP TABLE IF EXISTS `yx_material`;
CREATE TABLE `yx_material` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT 'PK',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建者ID',
  `type` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '类型1、图片；2、视频',
  `group_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '分组ID',
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '素材名',
  `url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '' COMMENT '素材链接',
  `update_time` datetime DEFAULT NULL,
  `is_del` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT='素材库';

-- ----------------------------
-- Records of yx_material
-- ----------------------------
BEGIN;
INSERT INTO `yx_material` VALUES ('05bf470c271f73fe2f572cad60b356f4', '2020-09-03 17:13:52', 'admin', '1', NULL, '1.jpg', 'https://consoleapi.xinxintuan.co/file/pic/20200903171351713145.jpg', NULL, 0);
INSERT INTO `yx_material` VALUES ('1888a13fdeca0d85dcc6bef413c531b9', '2020-06-26 17:22:21', 'admin', '1', NULL, 'list_16.png', 'http://127.0.0.1:8000/file/pic/20200626172220229003.png', NULL, 1);
INSERT INTO `yx_material` VALUES ('353760137dad5526444c01074f9490e0', '2020-09-03 17:18:08', 'admin', '1', NULL, '3.jpg', 'https://consoleapi.xinxintuan.co/file/pic/20200903171807584910.jpg', NULL, 0);
INSERT INTO `yx_material` VALUES ('3e2aa47438e059b06eea6327ae2260ec', '2020-06-26 18:25:07', 'admin', '1', 'd9b1b9d69f5b72cda63e26dc9f087623', 'news.png', 'https://image.dayouqiantu.cn/news.png', NULL, 0);
INSERT INTO `yx_material` VALUES ('612f4f924840739f0ebdfbdce21d878b', '2020-06-26 18:25:07', 'admin', '1', 'd9b1b9d69f5b72cda63e26dc9f087623', 'sign.png', 'https://image.dayouqiantu.cn/sign.png', NULL, 0);
INSERT INTO `yx_material` VALUES ('658cff7a98834617e46adfd105db0a3c', '2020-05-27 16:45:44', 'admin', '1', NULL, 'list_18.png', 'http://127.0.0.1:8000/file/pic/20200527164543489004.png', NULL, 1);
INSERT INTO `yx_material` VALUES ('6d2c5511b87631ffa6c5eecb9e4d736f', '2020-06-26 18:25:23', 'admin', '1', '8cc5996f6c4b54fcf5c0f2845a5d9afa', '5ca04fa9c08ef.jpg', 'https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg', NULL, 0);
INSERT INTO `yx_material` VALUES ('6d4adf87cbb42bac277edf212dcbf2a7', '2020-06-26 18:20:58', 'admin', '1', 'd9b1b9d69f5b72cda63e26dc9f087623', 'collect.png', 'https://image.dayouqiantu.cn/collect.png', NULL, 0);
INSERT INTO `yx_material` VALUES ('700a8cade3e6ff35a5e0d12b55849735', '2020-09-03 17:18:08', 'admin', '1', NULL, '7.jpg', 'https://consoleapi.xinxintuan.co/file/pic/20200903171807647067.jpg', NULL, 0);
INSERT INTO `yx_material` VALUES ('7492896114a45879b88c08f02ef88a3e', '2020-06-26 18:25:07', 'admin', '1', 'd9b1b9d69f5b72cda63e26dc9f087623', 'pink.png', 'https://image.dayouqiantu.cn/pink.png', NULL, 0);
INSERT INTO `yx_material` VALUES ('77b45342d9861a25323f284b155c4c9d', '2020-09-03 17:18:08', 'admin', '1', NULL, '6.jpg', 'https://consoleapi.xinxintuan.co/file/pic/20200903171807641535.jpg', NULL, 0);
INSERT INTO `yx_material` VALUES ('7cc18f371ebcfaeb63d95f734251453b', '2020-06-26 18:25:24', 'admin', '1', '8cc5996f6c4b54fcf5c0f2845a5d9afa', '5ca0786c5d2c1.jpg', 'https://image.dayouqiantu.cn/5ca0786c5d2c1.jpg', NULL, 1);
INSERT INTO `yx_material` VALUES ('80116a9fea6f899d4530e82bc4d9b97b', '2020-09-03 17:12:09', 'admin', '1', NULL, 'avatar_default.png', 'https://consoleapi.xinxintuan.co/file/pic/20200903171208889668.png', NULL, 0);
INSERT INTO `yx_material` VALUES ('808cbc928e818b2c0d12f5919042cdc2', '2020-06-26 11:08:09', 'admin', '1', NULL, 'list_34.png', 'http://127.0.0.1:8000/file/pic/20200626110808157592.png', NULL, 1);
INSERT INTO `yx_material` VALUES ('8a599a4721469bfcd1c313241c88bee0', '2020-06-26 18:20:58', 'admin', '1', 'd9b1b9d69f5b72cda63e26dc9f087623', 'buy.png', 'https://image.dayouqiantu.cn/buy.png', NULL, 0);
INSERT INTO `yx_material` VALUES ('8e19af4d16b717574d863e6316cf15e4', '2020-06-26 18:24:51', 'admin', '1', 'd9b1b9d69f5b72cda63e26dc9f087623', 'add.png', 'https://image.dayouqiantu.cn/add.png', NULL, 0);
INSERT INTO `yx_material` VALUES ('9ddc310b8b6e224cd1ee2f27f1d19017', '2020-06-26 18:25:07', 'admin', '1', 'd9b1b9d69f5b72cda63e26dc9f087623', 'sekill.png', 'https://image.dayouqiantu.cn/sekill.png', NULL, 0);
INSERT INTO `yx_material` VALUES ('a0fc29a301a72712802ef38efe011fc4', '2020-09-11 09:39:13', 'admin', '1', 'd9b1b9d69f5b72cda63e26dc9f087623', 'avatar_default.png', 'https://consoleapi.xinxintuan.co/file/pic/20200911093912577832.png', NULL, 0);
INSERT INTO `yx_material` VALUES ('a961d6c0f255083294c27bed47330ad9', '2020-05-27 16:45:27', 'admin', '1', '83ee56004ff88fd1ecdde5a43469ef2e', 'list_13.png', 'http://127.0.0.1:8000/file/pic/20200527164526793230.png', NULL, 1);
INSERT INTO `yx_material` VALUES ('b36ab3797cd310a210f5d3dec2d05349', '2020-09-03 17:18:08', 'admin', '1', NULL, '5.jpg', 'https://consoleapi.xinxintuan.co/file/pic/20200903171807625149.jpg', NULL, 0);
INSERT INTO `yx_material` VALUES ('bd6c9dc5a3373fe377b4aeb0579ed1bb', '2020-06-26 17:35:22', 'admin', '1', NULL, 'list_18.png', 'http://127.0.0.1:8009/api/api/file/pic/20200626173520849370.png', NULL, 1);
INSERT INTO `yx_material` VALUES ('c246328a65ecb0170b67334e8ef4683e', '2020-06-26 18:20:58', 'admin', '1', 'd9b1b9d69f5b72cda63e26dc9f087623', 'goods.png', 'https://image.dayouqiantu.cn/goods.png', NULL, 0);
INSERT INTO `yx_material` VALUES ('c48c27553f32321e5b5e6cdc2b934484', '2020-09-15 07:41:40', 'admin', '1', NULL, '1600126884(1).jpg', 'https://consoleapi.xinxintuan.co/file/pic/20200915074139926178.jpg', NULL, 0);
INSERT INTO `yx_material` VALUES ('c83bb0d55e101f6ab525acb1f524b10b', '2020-06-26 17:39:44', 'admin', '1', NULL, 'list_34.png', 'http://127.0.0.1:8000/file/pic/20200626173943611335.png', NULL, 1);
INSERT INTO `yx_material` VALUES ('d63a722c0a0e55e73126ff9411ae1224', '2020-06-26 18:20:58', 'admin', '1', 'd9b1b9d69f5b72cda63e26dc9f087623', 'coupon.png', 'https://image.dayouqiantu.cn/coupon.png', NULL, 0);
INSERT INTO `yx_material` VALUES ('d98b9f8ad2daa9e9a7567b3ae31b81d7', '2020-06-26 18:24:51', 'admin', '1', 'd9b1b9d69f5b72cda63e26dc9f087623', 'bargin.png', 'https://image.dayouqiantu.cn/bargin.png', NULL, 0);
INSERT INTO `yx_material` VALUES ('eb8aa8d0846009f5b685e9f07a05c836', '2020-09-03 17:10:04', 'admin', '1', NULL, 'avatar_default.png', 'https://consoleapi.xinxintuan.co/file/pic/20200903171003417769.png', NULL, 0);
INSERT INTO `yx_material` VALUES ('f04dcbf4b61ab35d0f08f85a27ee92ff', '2020-06-26 18:25:24', 'admin', '1', '8cc5996f6c4b54fcf5c0f2845a5d9afa', '5ca081af6183f.jpg', 'https://image.dayouqiantu.cn/5ca081af6183f.jpg', NULL, 0);
INSERT INTO `yx_material` VALUES ('f0e8dddf88c587f638e5a7859d8cc7a3', '2020-09-03 17:18:08', 'admin', '1', NULL, '4.jpg', 'https://consoleapi.xinxintuan.co/file/pic/20200903171807568739.jpg', NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for yx_material_group
-- ----------------------------
DROP TABLE IF EXISTS `yx_material_group`;
CREATE TABLE `yx_material_group` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT 'PK',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建者ID',
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '分组名',
  `update_time` datetime DEFAULT NULL,
  `is_del` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT='素材分组';

-- ----------------------------
-- Records of yx_material_group
-- ----------------------------
BEGIN;
INSERT INTO `yx_material_group` VALUES ('83ee56004ff88fd1ecdde5a43469ef2e', '2020-05-27 16:45:08', NULL, 'test', '2020-06-26 18:20:00', 0);
INSERT INTO `yx_material_group` VALUES ('8cc5996f6c4b54fcf5c0f2845a5d9afa', '2020-06-26 11:07:26', NULL, '商品', '2020-06-26 18:20:14', 0);
INSERT INTO `yx_material_group` VALUES ('d9b1b9d69f5b72cda63e26dc9f087623', '2020-06-26 18:20:19', NULL, 'icon', NULL, 0);
INSERT INTO `yx_material_group` VALUES ('fe2e32c3ce27983c298ab344c6e1c4fa', '2020-05-27 16:45:13', NULL, 'bb', NULL, 1);
COMMIT;

-- ----------------------------
-- Table structure for yx_store_bargain
-- ----------------------------
DROP TABLE IF EXISTS `yx_store_bargain`;
CREATE TABLE `yx_store_bargain` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '砍价产品ID',
  `product_id` bigint unsigned NOT NULL COMMENT '关联产品ID',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '砍价活动名称',
  `image` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '砍价活动图片',
  `unit_name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '单位名称',
  `stock` int unsigned DEFAULT NULL COMMENT '库存',
  `sales` int unsigned DEFAULT NULL COMMENT '销量',
  `images` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '砍价产品轮播图',
  `start_time` datetime NOT NULL COMMENT '砍价开启时间',
  `stop_time` datetime NOT NULL COMMENT '砍价结束时间',
  `store_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '砍价产品名称',
  `price` decimal(8,2) unsigned DEFAULT NULL COMMENT '砍价金额',
  `min_price` decimal(8,2) unsigned DEFAULT NULL COMMENT '砍价商品最低价',
  `num` int unsigned DEFAULT NULL COMMENT '每次购买的砍价产品数量',
  `bargain_max_price` decimal(8,2) unsigned DEFAULT NULL COMMENT '用户每次砍价的最大金额',
  `bargain_min_price` decimal(8,2) unsigned DEFAULT NULL COMMENT '用户每次砍价的最小金额',
  `bargain_num` int unsigned NOT NULL DEFAULT '1' COMMENT '用户每次砍价的次数',
  `status` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '砍价状态 0(到砍价时间不自动开启)  1(到砍价时间自动开启时间)',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '砍价详情',
  `give_integral` decimal(10,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '反多少积分',
  `info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '砍价活动简介',
  `cost` decimal(8,2) unsigned DEFAULT NULL COMMENT '成本价',
  `sort` int unsigned NOT NULL DEFAULT '0' COMMENT '排序',
  `is_del` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否删除 0未删除 1删除',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime DEFAULT NULL,
  `rule` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '砍价规则',
  `look` int unsigned DEFAULT '0' COMMENT '砍价产品浏览量',
  `share` int unsigned DEFAULT '0' COMMENT '砍价产品分享量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='砍价表';

-- ----------------------------
-- Records of yx_store_bargain
-- ----------------------------
BEGIN;
INSERT INTO `yx_store_bargain` VALUES (12, 7, '专属测试商品', 'https://consoleapi.xinxintuan.co/file/pic/20200903171208889668.png', '张', 7, 3, 'https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg,https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg,https://image.dayouqiantu.cn/5ca081af6183f.jpg', '2020-09-12 18:20:56', '2020-09-30 00:00:00', NULL, 100.00, 1.00, 1, 10.00, 1.00, 1, 1, '<p><br/></p><p><img src=\"https://consoleapi.xinxintuan.co/file/pic/20200903171807647067.jpg\"/></p>', 0.00, '结算', 110.00, 0, 1, '2020-09-12 18:22:14', NULL, '<p>1、规则1</p><p>2、规则2</p>', 40, 23);
INSERT INTO `yx_store_bargain` VALUES (13, 8, '多规格商品测试', 'https://image.dayouqiantu.cn/5ca081af6183f.jpg', '张', 501, 5, 'https://image.dayouqiantu.cn/5ca081af6183f.jpg,https://image.dayouqiantu.cn/5ca081af6183f.jpg,https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg', '2021-04-04 00:00:00', '2021-04-14 00:00:00', NULL, 0.01, 1.00, 1, 1.00, 1.00, 1, 1, '<p><br/></p><p><img src=\"https://image.dayouqiantu.cn/5ca081af6183f.jpg\"/></p>', 0.00, '多规格商品测试', 1.00, 0, 0, '2021-04-04 11:28:09', NULL, '<p>1111</p>', 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for yx_store_cart
-- ----------------------------
DROP TABLE IF EXISTS `yx_store_cart`;
CREATE TABLE `yx_store_cart` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '购物车表ID',
  `uid` bigint unsigned NOT NULL COMMENT '用户ID',
  `type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'product' COMMENT '类型',
  `product_id` bigint unsigned NOT NULL COMMENT '商品ID',
  `product_attr_unique` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商品属性',
  `cart_num` smallint unsigned NOT NULL DEFAULT '0' COMMENT '商品数量',
  `create_time` datetime NOT NULL COMMENT '添加时间',
  `update_time` datetime DEFAULT NULL,
  `is_pay` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 = 未购买 1 = 已购买',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `is_new` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否为立即购买',
  `combination_id` int unsigned DEFAULT '0' COMMENT '拼团id',
  `seckill_id` int unsigned NOT NULL DEFAULT '0' COMMENT '秒杀产品ID',
  `bargain_id` int unsigned NOT NULL DEFAULT '0' COMMENT '砍价id',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `user_id` (`uid`) USING BTREE,
  KEY `goods_id` (`product_id`) USING BTREE,
  KEY `uid` (`uid`,`is_pay`) USING BTREE,
  KEY `uid_2` (`uid`,`is_del`) USING BTREE,
  KEY `uid_3` (`uid`,`is_new`) USING BTREE,
  KEY `type` (`type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=150 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='购物车表';

-- ----------------------------
-- Records of yx_store_cart
-- ----------------------------
BEGIN;
INSERT INTO `yx_store_cart` VALUES (86, 40, 'product', 6, '91d12632e2c047d7b34dab48cfc5a34e', 1, '2020-09-12 14:54:08', '2020-09-12 14:54:45', 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (87, 43, 'product', 7, '789984b858bb489bb6313107e11fdc15', 1, '2020-09-12 17:04:12', '2020-09-12 17:06:04', 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (88, 43, 'product', 7, '789984b858bb489bb6313107e11fdc15', 1, '2020-09-12 17:06:54', '2020-09-12 17:07:00', 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (89, 43, 'product', 7, '789984b858bb489bb6313107e11fdc15', 1, '2020-09-12 17:07:53', '2020-09-12 17:07:57', 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (90, 43, 'product', 8, '2f7ee7305cfe43f782ea28c0235256db', 1, '2020-09-12 17:27:03', '2020-09-12 17:27:24', 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (91, 43, 'product', 8, '183fcbaffb3849c48de2e87a226209b9', 1, '2020-09-12 17:48:03', '2020-09-12 17:48:14', 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (92, 43, 'product', 7, '789984b858bb489bb6313107e11fdc15', 1, '2020-09-12 17:54:50', NULL, 0, 0, 0, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (93, 43, 'product', 8, '67138eee0bed408abe7d32b0ab2060b0', 1, '2020-09-12 17:56:08', NULL, 0, 0, 0, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (94, 43, 'product', 7, '789984b858bb489bb6313107e11fdc15', 1, '2020-09-12 18:10:27', NULL, 0, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (95, 43, 'product', 8, '183fcbaffb3849c48de2e87a226209b9', 1, '2020-09-12 18:10:44', '2020-09-12 18:11:05', 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (96, 43, 'product', 8, 'f25af180071742458622febf7dde528b', 1, '2020-09-12 18:18:27', '2020-09-12 18:18:37', 1, 0, 1, 10, 0, 0);
INSERT INTO `yx_store_cart` VALUES (97, 42, 'product', 3, 'b951f8e2ed4641f08be1bce99b716a6d', 1, '2020-09-12 19:27:31', NULL, 0, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (98, 42, 'product', 8, '013f89ec740f478d8144f052909c0807', 1, '2020-09-12 19:42:36', NULL, 0, 0, 1, 10, 0, 0);
INSERT INTO `yx_store_cart` VALUES (99, 40, 'product', 6, '90babee9cea24645870c6027fc420d42', 1, '2020-09-12 23:18:58', '2020-09-13 15:01:09', 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (100, 53, 'product', 5, '4dd9ea6fb9f1414aabf18b713f0f6832', 1, '2020-09-13 02:29:15', '2020-09-13 02:29:37', 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (101, 53, 'product', 8, '013f89ec740f478d8144f052909c0807', 1, '2020-09-13 03:54:24', '2020-09-13 03:54:28', 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (102, 42, 'product', 8, '8112da382da7420f961203372f7c1d31', 1, '2020-09-13 06:59:31', NULL, 0, 0, 1, 0, 7, 0);
INSERT INTO `yx_store_cart` VALUES (103, 44, 'product', 5, '040fb7ab2a8b4ff3b6c9d1c78ea3e889', 1, '2020-09-13 10:15:35', '2020-09-13 10:18:02', 1, 0, 1, 0, 6, 0);
INSERT INTO `yx_store_cart` VALUES (104, 53, 'product', 6, '90babee9cea24645870c6027fc420d42', 1, '2020-09-13 10:41:44', '2020-09-13 10:41:49', 1, 0, 1, 5, 0, 0);
INSERT INTO `yx_store_cart` VALUES (105, 53, 'product', 5, '040fb7ab2a8b4ff3b6c9d1c78ea3e889', 1, '2020-09-13 10:42:37', '2020-09-13 10:42:42', 1, 0, 1, 0, 6, 0);
INSERT INTO `yx_store_cart` VALUES (106, 53, 'product', 6, '90babee9cea24645870c6027fc420d42', 1, '2020-09-13 10:58:02', '2020-09-13 11:04:18', 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (107, 42, 'product', 6, '90babee9cea24645870c6027fc420d42', 1, '2020-09-13 11:04:15', '2020-09-14 10:59:22', 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (108, 53, 'product', 6, '90babee9cea24645870c6027fc420d42', 1, '2020-09-13 11:05:22', '2020-09-13 11:05:26', 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (109, 53, 'product', 6, '90babee9cea24645870c6027fc420d42', 1, '2020-09-13 11:06:59', '2020-09-13 11:07:03', 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (110, 53, 'product', 6, '90babee9cea24645870c6027fc420d42', 1, '2020-09-13 11:07:24', '2020-09-13 11:07:29', 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (111, 53, 'product', 6, '90babee9cea24645870c6027fc420d42', 1, '2020-09-13 11:08:49', '2020-09-13 11:08:53', 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (112, 53, 'product', 6, '90babee9cea24645870c6027fc420d42', 1, '2020-09-13 11:09:01', '2020-09-13 11:09:05', 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (113, 53, 'product', 6, '90babee9cea24645870c6027fc420d42', 1, '2020-09-13 11:11:33', '2020-09-13 11:11:38', 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (114, 53, 'product', 6, '90babee9cea24645870c6027fc420d42', 1, '2020-09-13 11:18:03', '2020-09-13 11:18:06', 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (115, 53, 'product', 6, '90babee9cea24645870c6027fc420d42', 1, '2020-09-13 11:42:10', '2020-09-13 11:42:13', 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (116, 53, 'product', 6, '90babee9cea24645870c6027fc420d42', 1, '2020-09-13 11:43:03', '2020-09-13 11:43:09', 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (117, 53, 'product', 6, '90babee9cea24645870c6027fc420d42', 1, '2020-09-13 11:46:23', '2020-09-13 11:46:28', 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (118, 53, 'product', 6, '90babee9cea24645870c6027fc420d42', 1, '2020-09-13 11:56:22', '2020-09-13 11:56:24', 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (119, 53, 'product', 6, '90babee9cea24645870c6027fc420d42', 1, '2020-09-13 11:57:35', '2020-09-13 11:57:38', 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (120, 53, 'product', 6, '90babee9cea24645870c6027fc420d42', 1, '2020-09-13 12:00:39', '2020-09-13 12:00:42', 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (121, 53, 'product', 6, '90babee9cea24645870c6027fc420d42', 1, '2020-09-13 20:00:13', '2020-09-13 20:00:16', 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (122, 53, 'product', 6, '90babee9cea24645870c6027fc420d42', 1, '2020-09-13 20:01:24', '2020-09-13 20:01:49', 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (123, 42, 'product', 2, '04e9d586cef8464aaee0a45439f12520', 1, '2020-09-13 21:32:46', '2020-09-13 21:33:38', 1, 0, 1, 16, 0, 0);
INSERT INTO `yx_store_cart` VALUES (124, 42, 'product', 6, '90babee9cea24645870c6027fc420d42', 1, '2020-09-13 21:33:49', '2020-09-13 21:33:51', 1, 0, 1, 5, 0, 0);
INSERT INTO `yx_store_cart` VALUES (125, 54, 'product', 6, '90babee9cea24645870c6027fc420d42', 1, '2020-09-13 22:04:08', '2020-09-13 22:05:10', 1, 0, 1, 5, 0, 0);
INSERT INTO `yx_store_cart` VALUES (126, 50, 'product', 3, 'b951f8e2ed4641f08be1bce99b716a6d', 1, '2020-09-13 22:04:30', '2020-09-13 22:04:52', 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (127, 50, 'product', 4, '6a1919d9178b48f3a75fe4640e51693e', 1, '2020-09-13 22:12:38', '2020-09-13 22:12:44', 1, 0, 1, 7, 0, 0);
INSERT INTO `yx_store_cart` VALUES (128, 49, 'product', 5, '040fb7ab2a8b4ff3b6c9d1c78ea3e889', 1, '2020-09-13 23:59:44', '2020-09-14 00:01:07', 1, 0, 1, 6, 0, 0);
INSERT INTO `yx_store_cart` VALUES (129, 49, 'product', 5, '040fb7ab2a8b4ff3b6c9d1c78ea3e889', 1, '2020-09-14 00:02:41', '2020-09-14 00:02:42', 1, 0, 1, 6, 0, 0);
INSERT INTO `yx_store_cart` VALUES (130, 49, 'product', 5, '040fb7ab2a8b4ff3b6c9d1c78ea3e889', 1, '2020-09-14 00:02:48', '2020-09-14 00:02:50', 1, 0, 1, 6, 0, 0);
INSERT INTO `yx_store_cart` VALUES (131, 59, 'product', 5, '040fb7ab2a8b4ff3b6c9d1c78ea3e889', 1, '2020-09-14 00:03:19', '2020-09-14 00:04:08', 1, 0, 1, 6, 0, 0);
INSERT INTO `yx_store_cart` VALUES (132, 49, 'product', 5, '040fb7ab2a8b4ff3b6c9d1c78ea3e889', 1, '2020-09-14 00:03:36', '2020-09-14 00:03:39', 1, 0, 1, 6, 0, 0);
INSERT INTO `yx_store_cart` VALUES (133, 55, 'product', 5, '040fb7ab2a8b4ff3b6c9d1c78ea3e889', 1, '2020-09-14 00:18:43', NULL, 0, 0, 1, 6, 0, 0);
INSERT INTO `yx_store_cart` VALUES (134, 42, 'product', 3, '43cdbd1043474b4e97a1cffffa18071c', 1, '2020-09-14 08:40:57', '2020-09-14 08:41:02', 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (135, 42, 'product', 3, '43cdbd1043474b4e97a1cffffa18071c', 1, '2020-09-14 08:41:11', '2020-09-14 08:41:17', 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (136, 42, 'product', 6, '90babee9cea24645870c6027fc420d42', 1, '2020-09-14 09:56:15', NULL, 0, 0, 1, 5, 0, 0);
INSERT INTO `yx_store_cart` VALUES (137, 61, 'product', 2, '04e9d586cef8464aaee0a45439f12520', 1, '2020-09-14 10:01:46', '2020-09-14 10:02:55', 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (138, 61, 'product', 2, '04e9d586cef8464aaee0a45439f12520', 1, '2020-09-14 10:08:05', '2020-09-14 10:08:07', 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (139, 40, 'product', 6, '90babee9cea24645870c6027fc420d42', 1, '2020-09-14 10:13:05', '2020-09-14 10:13:17', 1, 0, 1, 5, 0, 0);
INSERT INTO `yx_store_cart` VALUES (140, 42, 'product', 1, 'd4ce8cd4bda84d479c11348e060afb8e', 1, '2020-09-14 10:18:05', '2020-09-14 10:18:12', 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (141, 42, 'product', 1, 'd4ce8cd4bda84d479c11348e060afb8e', 1, '2020-09-14 10:20:20', '2020-09-14 10:20:24', 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (142, 42, 'product', 1, 'd4ce8cd4bda84d479c11348e060afb8e', 1, '2020-09-14 10:22:06', '2020-09-14 10:22:12', 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (143, 42, 'product', 4, '6a1919d9178b48f3a75fe4640e51693e', 1, '2020-09-14 10:50:38', '2020-09-14 10:50:44', 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (144, 42, 'product', 3, '43cdbd1043474b4e97a1cffffa18071c', 1, '2020-09-14 11:00:30', '2020-09-14 11:00:36', 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (145, 71, 'product', 6, '90babee9cea24645870c6027fc420d42', 1, '2020-09-14 12:52:02', '2020-09-14 12:53:06', 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (146, 71, 'product', 5, '040fb7ab2a8b4ff3b6c9d1c78ea3e889', 1, '2020-09-14 13:16:45', '2020-09-14 13:16:46', 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (147, 76, 'product', 6, '90babee9cea24645870c6027fc420d42', 1, '2020-09-14 22:20:51', NULL, 0, 0, 0, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (148, 76, 'product', 6, '90babee9cea24645870c6027fc420d42', 1, '2020-09-14 22:20:55', '2020-09-14 22:21:11', 1, 0, 1, 0, 0, 0);
INSERT INTO `yx_store_cart` VALUES (149, 54, 'product', 2, '04e9d586cef8464aaee0a45439f12520', 1, '2020-09-14 22:34:54', '2020-09-14 22:34:59', 1, 0, 1, 0, 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for yx_store_category
-- ----------------------------
DROP TABLE IF EXISTS `yx_store_category`;
CREATE TABLE `yx_store_category` (
  `id` mediumint NOT NULL AUTO_INCREMENT COMMENT '商品分类表ID',
  `pid` mediumint NOT NULL COMMENT '父id',
  `cate_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类名称',
  `sort` mediumint DEFAULT NULL COMMENT '排序',
  `pic` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '图标',
  `is_show` tinyint(1) DEFAULT '1' COMMENT '是否推荐',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime DEFAULT NULL,
  `is_del` tinyint unsigned DEFAULT '0' COMMENT '删除状态',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `pid` (`pid`) USING BTREE,
  KEY `is_base` (`is_show`) USING BTREE,
  KEY `sort` (`sort`) USING BTREE,
  KEY `add_time` (`create_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='商品分类表';

-- ----------------------------
-- Records of yx_store_category
-- ----------------------------
BEGIN;
INSERT INTO `yx_store_category` VALUES (1, 0, '图文广告', 1, '', 1, '2020-06-25 18:54:35', NULL, 0);
INSERT INTO `yx_store_category` VALUES (2, 1, '写真', 1, 'http://localhost:8000/file/pic/资讯-20200311043727918.png', 1, '2020-06-25 18:54:35', NULL, 1);
INSERT INTO `yx_store_category` VALUES (3, 1, '平面设计1', 1, 'https://image.dayouqiantu.cn/5ca081af6183f.jpg', 1, '2020-06-25 18:54:35', '2021-04-03 22:26:18', 0);
INSERT INTO `yx_store_category` VALUES (5, 1, '名片', 1, 'https://consoleapi.xinxintuan.co/file/pic/20200903171807647067.jpg', 1, '2020-06-25 18:54:35', '2020-09-09 21:00:32', 0);
INSERT INTO `yx_store_category` VALUES (6, 0, '666', 1, '', 1, '2020-06-25 18:54:35', NULL, 1);
INSERT INTO `yx_store_category` VALUES (7, 0, '99995', 1, '', 1, '2020-06-25 18:54:35', NULL, 1);
INSERT INTO `yx_store_category` VALUES (8, 7, '8888', 1, 'http://localhost:8000/file/pic/钻石-20200328094531898.jpg', 1, '2020-06-25 18:54:35', NULL, 1);
INSERT INTO `yx_store_category` VALUES (9, 0, '3333', 1, '', 1, '2020-06-25 17:11:45', NULL, 1);
INSERT INTO `yx_store_category` VALUES (10, 0, '666', 1, 'https://image.dayouqiantu.cn/5ca081af6183f.jpg', 1, '2021-04-03 22:25:23', NULL, 1);
COMMIT;

-- ----------------------------
-- Table structure for yx_store_combination
-- ----------------------------
DROP TABLE IF EXISTS `yx_store_combination`;
CREATE TABLE `yx_store_combination` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `product_id` bigint unsigned NOT NULL COMMENT '商品id',
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '推荐图',
  `images` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '轮播图',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '活动标题',
  `attr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '活动属性',
  `people` int unsigned NOT NULL COMMENT '参团人数',
  `info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '简介',
  `price` decimal(10,2) unsigned DEFAULT NULL COMMENT '价格',
  `product_price` decimal(10,2) DEFAULT NULL COMMENT '商品原价',
  `sort` int unsigned NOT NULL COMMENT '排序',
  `sales` int unsigned NOT NULL DEFAULT '0' COMMENT '销量',
  `stock` int unsigned DEFAULT NULL COMMENT '库存',
  `create_time` datetime NOT NULL COMMENT '添加时间',
  `update_time` datetime DEFAULT NULL,
  `is_host` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '推荐',
  `is_show` tinyint unsigned NOT NULL COMMENT '产品状态',
  `is_del` tinyint unsigned NOT NULL DEFAULT '0',
  `combination` tinyint unsigned NOT NULL DEFAULT '1',
  `mer_use` tinyint unsigned DEFAULT NULL COMMENT '商户是否可用1可用0不可用',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '拼团内容',
  `start_time` datetime NOT NULL COMMENT '拼团开始时间',
  `stop_time` datetime NOT NULL COMMENT '拼团结束时间',
  `effective_time` int NOT NULL DEFAULT '0' COMMENT '拼团订单有效时间',
  `cost` int unsigned NOT NULL DEFAULT '0' COMMENT '拼图产品成本',
  `browse` int DEFAULT '0' COMMENT '浏览量',
  `unit_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '单位名',
  `spec_type` tinyint(1) DEFAULT NULL COMMENT '规格 0单 1多',
  `temp_id` int DEFAULT NULL COMMENT '运费模板ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='拼团产品表';

-- ----------------------------
-- Records of yx_store_combination
-- ----------------------------
BEGIN;
INSERT INTO `yx_store_combination` VALUES (1, 8, 'https://image.dayouqiantu.cn/5ca081af6183f.jpg', 'https://image.dayouqiantu.cn/5ca081af6183f.jpg,https://image.dayouqiantu.cn/5ca081af6183f.jpg,https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg', '多规格商品测试', NULL, 1, '多规格商品测试', 1.00, NULL, 0, 5, 501, '2021-04-04 11:15:03', NULL, 1, 1, 0, 1, NULL, '<p><br/></p><p><img src=\"https://image.dayouqiantu.cn/5ca081af6183f.jpg\"/></p>', '2021-04-04 00:00:00', '2021-04-19 00:00:00', 24, 1, 0, '张', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for yx_store_coupon
-- ----------------------------
DROP TABLE IF EXISTS `yx_store_coupon`;
CREATE TABLE `yx_store_coupon` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '优惠券表ID',
  `title` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '优惠券名称',
  `integral` int unsigned NOT NULL DEFAULT '0' COMMENT '兑换消耗积分值',
  `coupon_price` decimal(8,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '兑换的优惠券面值',
  `use_min_price` decimal(8,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '最低消费多少金额可用优惠券',
  `coupon_time` int unsigned NOT NULL DEFAULT '0' COMMENT '优惠券有效期限（单位：天）',
  `sort` int unsigned NOT NULL DEFAULT '1' COMMENT '排序',
  `status` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '状态（0：关闭，1：开启）',
  `product_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品ids',
  `type` tinyint DEFAULT '0' COMMENT '优惠券类型 0-通用 1-商品券',
  `create_time` datetime NOT NULL COMMENT '兑换项目添加时间',
  `update_time` datetime DEFAULT NULL,
  `is_del` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `state` (`status`) USING BTREE,
  KEY `add_time` (`create_time`) USING BTREE,
  KEY `coupon_time` (`coupon_time`) USING BTREE,
  KEY `is_del` (`is_del`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='优惠券表';

-- ----------------------------
-- Records of yx_store_coupon
-- ----------------------------
BEGIN;
INSERT INTO `yx_store_coupon` VALUES (6, '全场通用', 0, 1.00, 10.00, 100, 0, 1, '', 0, '2020-09-12 17:57:02', NULL, 0);
INSERT INTO `yx_store_coupon` VALUES (7, '多规格商品券', 0, 1.00, 5.00, 100, 0, 1, '8', 1, '2020-09-12 17:57:46', NULL, 0);
INSERT INTO `yx_store_coupon` VALUES (8, '22', 0, 2.00, 2.00, 2, 0, 1, NULL, 0, '2021-04-04 16:03:42', NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for yx_store_coupon_issue
-- ----------------------------
DROP TABLE IF EXISTS `yx_store_coupon_issue`;
CREATE TABLE `yx_store_coupon_issue` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `cname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `cid` int DEFAULT NULL COMMENT '优惠券ID',
  `ctype` tinyint(1) DEFAULT NULL COMMENT '优惠券类型 0-通用 1-商品券',
  `start_time` datetime DEFAULT NULL COMMENT '优惠券领取开启时间',
  `end_time` datetime DEFAULT NULL COMMENT '优惠券领取结束时间',
  `total_count` int DEFAULT NULL COMMENT '优惠券领取数量',
  `remain_count` int DEFAULT NULL COMMENT '优惠券剩余领取数量',
  `is_permanent` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否无限张数',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1 正常 0 未开启 -1 已无效',
  `is_del` tinyint unsigned NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL COMMENT '优惠券添加时间',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `cid` (`cid`) USING BTREE,
  KEY `start_time` (`start_time`,`end_time`) USING BTREE,
  KEY `remain_count` (`remain_count`) USING BTREE,
  KEY `status` (`status`) USING BTREE,
  KEY `is_del` (`is_del`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='优惠券前台领取表';

-- ----------------------------
-- Records of yx_store_coupon_issue
-- ----------------------------
BEGIN;
INSERT INTO `yx_store_coupon_issue` VALUES (6, '多规格商品券', 7, 1, '2020-09-12 18:07:10', '2020-09-30 00:00:00', 100, 99, 0, 1, 0, '2020-09-12 18:07:19', NULL);
INSERT INTO `yx_store_coupon_issue` VALUES (7, '全场通用', 6, 0, '2020-09-12 18:07:25', '2020-09-29 00:00:00', 100, 100, 0, 1, 0, '2020-09-12 18:07:34', NULL);
INSERT INTO `yx_store_coupon_issue` VALUES (8, '22', 8, NULL, NULL, NULL, 0, 0, 0, 1, 1, '2021-04-04 16:03:55', NULL);
INSERT INTO `yx_store_coupon_issue` VALUES (9, '22', 8, NULL, NULL, NULL, 0, 0, 0, 1, 1, '2021-04-04 16:04:43', '2021-04-04 16:06:39');
INSERT INTO `yx_store_coupon_issue` VALUES (10, '22', 8, NULL, '2021-04-05 00:00:00', '2021-04-30 00:00:00', 0, 0, 0, 1, 0, '2021-04-04 16:06:29', NULL);
COMMIT;

-- ----------------------------
-- Table structure for yx_store_coupon_issue_user
-- ----------------------------
DROP TABLE IF EXISTS `yx_store_coupon_issue_user`;
CREATE TABLE `yx_store_coupon_issue_user` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `uid` bigint DEFAULT NULL COMMENT '领取优惠券用户ID',
  `issue_coupon_id` int DEFAULT NULL COMMENT '优惠券前台领取ID',
  `create_time` datetime DEFAULT NULL COMMENT '领取时间',
  `update_time` datetime DEFAULT NULL,
  `is_del` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uid` (`uid`,`issue_coupon_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='优惠券前台用户领取记录表';

-- ----------------------------
-- Records of yx_store_coupon_issue_user
-- ----------------------------
BEGIN;
INSERT INTO `yx_store_coupon_issue_user` VALUES (5, 43, 6, '2020-09-12 18:08:26', NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for yx_store_coupon_user
-- ----------------------------
DROP TABLE IF EXISTS `yx_store_coupon_user`;
CREATE TABLE `yx_store_coupon_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '优惠券发放记录id',
  `cid` int unsigned NOT NULL DEFAULT '0' COMMENT '兑换的项目id',
  `uid` bigint unsigned NOT NULL DEFAULT '0' COMMENT '优惠券所属用户',
  `coupon_title` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '优惠券名称',
  `coupon_price` decimal(8,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '优惠券的面值',
  `use_min_price` decimal(8,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '最低消费多少金额可用优惠券',
  `create_time` datetime NOT NULL COMMENT '优惠券创建时间',
  `update_time` datetime DEFAULT NULL,
  `end_time` datetime NOT NULL COMMENT '优惠券结束时间',
  `use_time` datetime DEFAULT NULL COMMENT '使用时间',
  `type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'send' COMMENT '获取方式',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态（0：未使用，1：已使用, 2:已过期）',
  `is_fail` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否有效',
  `is_del` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `cid` (`cid`) USING BTREE,
  KEY `uid` (`uid`) USING BTREE,
  KEY `add_time` (`create_time`) USING BTREE,
  KEY `end_time` (`end_time`) USING BTREE,
  KEY `status` (`status`) USING BTREE,
  KEY `is_fail` (`is_fail`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='优惠券发放记录表';

-- ----------------------------
-- Records of yx_store_coupon_user
-- ----------------------------
BEGIN;
INSERT INTO `yx_store_coupon_user` VALUES (5, 7, 43, '多规格商品券', 1.00, 5.00, '2020-09-12 18:08:26', '2020-09-12 18:11:05', '2020-12-21 18:08:26', '2020-09-12 18:11:05', 'get', 1, 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for yx_store_order
-- ----------------------------
DROP TABLE IF EXISTS `yx_store_order`;
CREATE TABLE `yx_store_order` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `order_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单号',
  `extend_order_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '额外订单号',
  `uid` bigint unsigned NOT NULL COMMENT '用户id',
  `real_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户姓名',
  `user_phone` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户电话',
  `user_address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '详细地址',
  `cart_id` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '[]' COMMENT '购物车id',
  `freight_price` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '运费金额',
  `total_num` int unsigned NOT NULL DEFAULT '0' COMMENT '订单商品总数',
  `total_price` decimal(8,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '订单总价',
  `total_postage` decimal(8,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '邮费',
  `pay_price` decimal(8,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '实际支付金额',
  `pay_postage` decimal(8,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '支付邮费',
  `deduction_price` decimal(8,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '抵扣金额',
  `coupon_id` int unsigned NOT NULL DEFAULT '0' COMMENT '优惠券id',
  `coupon_price` decimal(8,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '优惠券金额',
  `paid` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '支付状态',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `pay_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '支付方式',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '订单状态（-1 : 申请退款 -2 : 退货成功 0：待发货；1：待收货；2：已收货；3：已完成；-1：已退款）',
  `refund_status` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '0 未退款 1 申请中 2 已退款',
  `refund_reason_wap_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '退款图片',
  `refund_reason_wap_explain` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '退款用户说明',
  `refund_reason_time` datetime DEFAULT NULL COMMENT '退款时间',
  `refund_reason_wap` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '前台退款原因',
  `refund_reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '不退款的理由',
  `refund_price` decimal(8,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '退款金额',
  `delivery_sn` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '快递公司编号',
  `delivery_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '快递名称/送货人姓名',
  `delivery_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '发货类型',
  `delivery_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '快递单号/手机号',
  `gain_integral` decimal(8,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '消费赚取积分',
  `use_integral` decimal(8,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '使用积分',
  `back_integral` decimal(8,2) unsigned DEFAULT NULL COMMENT '给用户退了多少积分',
  `mark` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '备注',
  `is_del` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否删除',
  `unique` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '唯一id(md5加密)类似id',
  `remark` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '管理员备注',
  `combination_id` bigint unsigned DEFAULT '0' COMMENT '拼团产品id0一般产品',
  `pink_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '拼团id 0没有拼团',
  `cost` decimal(8,2) unsigned NOT NULL COMMENT '成本价',
  `seckill_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '秒杀产品ID',
  `bargain_id` int unsigned DEFAULT '0' COMMENT '砍价id',
  `verify_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '核销码',
  `store_id` int NOT NULL DEFAULT '0' COMMENT '门店id',
  `shipping_type` tinyint(1) NOT NULL DEFAULT '1' COMMENT '配送方式 1=快递 ，2=门店自提',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `order_id_2` (`order_id`,`uid`) USING BTREE,
  UNIQUE KEY `unique` (`unique`) USING BTREE,
  KEY `uid` (`uid`) USING BTREE,
  KEY `add_time` (`create_time`) USING BTREE,
  KEY `pay_price` (`pay_price`) USING BTREE,
  KEY `paid` (`paid`) USING BTREE,
  KEY `pay_time` (`pay_time`) USING BTREE,
  KEY `pay_type` (`pay_type`) USING BTREE,
  KEY `status` (`status`) USING BTREE,
  KEY `is_del` (`is_del`) USING BTREE,
  KEY `coupon_id` (`coupon_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='订单表';

-- ----------------------------
-- Records of yx_store_order
-- ----------------------------
BEGIN;
INSERT INTO `yx_store_order` VALUES (1, '1302228251506114560', NULL, 29, '哈哈', '18888888888', '北京市 北京市 朝阳区 老两口儿', '2', 0.00, 1, 10.00, 0.00, 10.00, 0.00, 0.00, 0, 0.00, 1, '2020-09-05 20:55:40', 'yue', '2020-09-05 20:53:04', '2020-09-05 20:55:40', 0, 0, NULL, NULL, NULL, NULL, NULL, 0.00, '', NULL, NULL, NULL, 0.00, 0.00, NULL, '', 0, '8b53cc02e2ed45dda4356979fc7f2f87', NULL, 0, 0, 11.00, 0, 0, '', 0, 1);
COMMIT;

-- ----------------------------
-- Table structure for yx_store_order_cart_info
-- ----------------------------
DROP TABLE IF EXISTS `yx_store_order_cart_info`;
CREATE TABLE `yx_store_order_cart_info` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `oid` bigint unsigned NOT NULL COMMENT '订单id',
  `cart_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '购物车id',
  `product_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '商品ID',
  `cart_info` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '购买东西的详细信息',
  `unique` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '唯一id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `oid` (`oid`,`unique`) USING BTREE,
  KEY `cart_id` (`cart_id`) USING BTREE,
  KEY `product_id` (`product_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='订单购物详情表';

-- ----------------------------
-- Records of yx_store_order_cart_info
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for yx_store_order_status
-- ----------------------------
DROP TABLE IF EXISTS `yx_store_order_status`;
CREATE TABLE `yx_store_order_status` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `oid` bigint unsigned NOT NULL COMMENT '订单id',
  `change_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作类型',
  `change_message` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作备注',
  `change_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `oid` (`oid`) USING BTREE,
  KEY `change_type` (`change_type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='订单操作记录表';

-- ----------------------------
-- Records of yx_store_order_status
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for yx_store_pink
-- ----------------------------
DROP TABLE IF EXISTS `yx_store_pink`;
CREATE TABLE `yx_store_pink` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `uid` bigint unsigned NOT NULL COMMENT '用户id',
  `order_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单id 生成',
  `order_id_key` int unsigned NOT NULL COMMENT '订单id  数据库',
  `total_num` int unsigned NOT NULL COMMENT '购买商品个数',
  `total_price` decimal(10,2) unsigned NOT NULL COMMENT '购买总金额',
  `cid` bigint unsigned NOT NULL COMMENT '拼团产品id',
  `pid` bigint unsigned NOT NULL COMMENT '产品id',
  `people` int unsigned NOT NULL COMMENT '拼图总人数',
  `price` decimal(10,2) unsigned NOT NULL COMMENT '拼团产品单价',
  `create_time` datetime NOT NULL COMMENT '开始时间',
  `update_time` datetime DEFAULT NULL,
  `stop_time` datetime DEFAULT NULL,
  `k_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '团长id 0为团长',
  `is_tpl` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否发送模板消息0未发送1已发送',
  `is_refund` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否退款 0未退款 1已退款',
  `status` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '状态1进行中2已完成3未完成',
  `is_del` tinyint(1) DEFAULT '0' COMMENT '是否删除1删除 0否',
  `unique_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '库存唯一值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='拼团表';

-- ----------------------------
-- Records of yx_store_pink
-- ----------------------------
BEGIN;
INSERT INTO `yx_store_pink` VALUES (5, 28, '1302844793633636352', 5, 1, 2.00, 4, 4, 2, 2.00, '2020-09-07 13:47:32', '2020-09-10 21:13:59', '2020-09-10 21:13:59', 0, 0, 0, 3, 0, NULL);
COMMIT;

-- ----------------------------
-- Table structure for yx_store_product
-- ----------------------------
DROP TABLE IF EXISTS `yx_store_product`;
CREATE TABLE `yx_store_product` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `image` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品图片',
  `slider_image` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '轮播图',
  `store_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `store_info` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品简介',
  `keyword` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '关键字',
  `bar_code` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '产品条码（一维码）',
  `cate_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类id',
  `price` decimal(8,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '商品价格',
  `vip_price` decimal(8,2) unsigned DEFAULT '0.00' COMMENT '会员价格',
  `ot_price` decimal(8,2) unsigned DEFAULT '0.00' COMMENT '市场价',
  `postage` decimal(8,2) unsigned DEFAULT '0.00' COMMENT '邮费',
  `unit_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '单位名',
  `sort` smallint DEFAULT '0' COMMENT '排序',
  `sales` mediumint unsigned DEFAULT '0' COMMENT '销量',
  `stock` mediumint unsigned DEFAULT '0' COMMENT '库存',
  `is_show` tinyint(1) DEFAULT '1' COMMENT '状态（0：未上架，1：上架）',
  `is_hot` tinyint(1) DEFAULT '0' COMMENT '是否热卖',
  `is_benefit` tinyint(1) DEFAULT '0' COMMENT '是否优惠',
  `is_best` tinyint(1) DEFAULT '0' COMMENT '是否精品',
  `is_new` tinyint(1) DEFAULT '0' COMMENT '是否新品',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '产品描述',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime DEFAULT NULL,
  `is_postage` tinyint unsigned DEFAULT '0' COMMENT '是否包邮',
  `is_del` tinyint unsigned DEFAULT '0' COMMENT '是否删除',
  `mer_use` tinyint unsigned DEFAULT '0' COMMENT '商户是否代理 0不可代理1可代理',
  `give_integral` decimal(8,2) unsigned DEFAULT NULL COMMENT '获得积分',
  `cost` decimal(8,2) unsigned DEFAULT NULL COMMENT '成本价',
  `is_good` tinyint(1) DEFAULT '0' COMMENT '是否优品推荐',
  `ficti` mediumint DEFAULT '100' COMMENT '虚拟销量',
  `browse` int DEFAULT '0' COMMENT '浏览量',
  `is_sub` tinyint(1) DEFAULT '0' COMMENT '是否单独分佣',
  `temp_id` int DEFAULT NULL COMMENT '运费模板ID',
  `spec_type` tinyint(1) DEFAULT '0' COMMENT '规格 0单 1多',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `is_hot` (`is_hot`) USING BTREE,
  KEY `is_benefit` (`is_benefit`) USING BTREE,
  KEY `is_best` (`is_best`) USING BTREE,
  KEY `is_new` (`is_new`) USING BTREE,
  KEY `toggle_on_sale, is_del` (`is_del`) USING BTREE,
  KEY `price` (`price`) USING BTREE,
  KEY `is_show` (`is_show`) USING BTREE,
  KEY `sort` (`sort`) USING BTREE,
  KEY `sales` (`sales`) USING BTREE,
  KEY `add_time` (`create_time`) USING BTREE,
  KEY `is_postage` (`is_postage`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='商品表';

-- ----------------------------
-- Records of yx_store_product
-- ----------------------------
BEGIN;
INSERT INTO `yx_store_product` VALUES (1, 'https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg', 'https://image.dayouqiantu.cn/5ca081af6183f.jpg,https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg', '照片打印', '照片打印', '照片打印', '', '5', 2.00, 0.00, 1.00, 0.00, '张', 0, 8, 992, 1, 0, 0, 0, 0, '<p><br/></p><p><img src=\"https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg\"/></p>', '2020-09-04 17:41:30', '2020-09-05 20:57:49', 0, 0, 0, 0.00, 3.00, 0, 0, 3, 0, 34, 0);
INSERT INTO `yx_store_product` VALUES (2, 'https://consoleapi.xinxintuan.co/file/pic/20200903171807584910.jpg', 'https://consoleapi.xinxintuan.co/file/pic/20200903171807584910.jpg,https://consoleapi.xinxintuan.co/file/pic/20200903171807647067.jpg,https://consoleapi.xinxintuan.co/file/pic/20200903171807641535.jpg', 'X型展架', 'X型展架', 'X型展架', '', '3', 10.00, 0.00, 12.00, 0.00, '张', 0, 20, 3310, 1, 1, 1, 0, 0, '<p><br/></p><p><img src=\"https://consoleapi.xinxintuan.co/file/pic/20200903171807641535.jpg\"/></p>', '2020-09-04 17:55:16', '2021-04-04 15:13:02', 0, 1, 0, 0.00, 11.00, 0, 69, 25, 0, 34, 1);
INSERT INTO `yx_store_product` VALUES (3, 'https://consoleapi.xinxintuan.co/file/pic/20200903171807647067.jpg', 'https://consoleapi.xinxintuan.co/file/pic/20200903171807647067.jpg', '彩色复印-量大有优惠', '彩色复印', '彩色复印', '', '3', 2.00, 0.00, 1.00, 0.00, '张', 0, 8, 9996, 1, 1, 1, 0, 0, '<p>1111</p>', '2020-09-07 13:14:50', '2020-09-13 22:18:46', 0, 1, 0, 0.00, 1.00, 0, 0, 43, 0, 34, 0);
INSERT INTO `yx_store_product` VALUES (4, 'https://consoleapi.xinxintuan.co/file/pic/20200903171807625149.jpg', 'https://consoleapi.xinxintuan.co/file/pic/20200903171807625149.jpg', '户外写真背胶', '户外写真背胶', '户外写真背胶', '', '3', 2.00, 0.00, 1.00, 0.00, '米', 0, 24, 87, 1, 1, 1, 0, 0, '<p>户外写真背胶</p>', '2020-09-07 13:16:02', NULL, 0, 1, 0, 0.00, 1.00, 0, 0, 32, 0, 34, 0);
INSERT INTO `yx_store_product` VALUES (5, 'https://consoleapi.xinxintuan.co/file/pic/20200903171807568739.jpg', 'https://consoleapi.xinxintuan.co/file/pic/20200903171807568739.jpg', '广告版定制', '广告版定制', '广告版定制', '', '3', 2.00, 0.00, 11.00, 0.00, '米', 0, 16, 2, 1, 1, 1, 1, 1, '<p>广告版定制</p>', '2020-09-07 13:17:49', '2020-09-12 00:14:32', 0, 1, 0, 0.00, 1.00, 0, 0, 26, 0, 34, 0);
INSERT INTO `yx_store_product` VALUES (6, 'https://consoleapi.xinxintuan.co/file/pic/20200903171807641535.jpg', 'https://consoleapi.xinxintuan.co/file/pic/20200903171807641535.jpg', '印刷各种印刷', '印刷各种印刷', '印刷各种印刷', '', '3', 11.00, 0.00, 33.00, 0.00, '张', 1, 35, 9965, 1, 1, 1, 0, 0, '<p>印刷各种印刷</p>', '2020-09-07 13:18:45', '2020-09-11 17:18:18', 0, 1, 0, 1.00, 22.00, 0, 1, 139, 0, 34, 0);
INSERT INTO `yx_store_product` VALUES (7, 'https://consoleapi.xinxintuan.co/file/pic/20200903171208889668.png', 'https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg,https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg,https://image.dayouqiantu.cn/5ca081af6183f.jpg', '专属测试商品', '', '专属测试商品', '', '3', 100.00, 0.00, 120.00, 0.00, '张', 0, 3, 7, 1, 0, 0, 0, 0, '<p><br/></p><p><img src=\"https://consoleapi.xinxintuan.co/file/pic/20200903171807647067.jpg\"/></p>', '2020-09-12 16:11:05', '2020-09-12 17:03:58', 0, 1, 0, 10.00, 110.00, 0, 9, 9, 0, 34, 0);
INSERT INTO `yx_store_product` VALUES (8, 'https://image.dayouqiantu.cn/5ca081af6183f.jpg', 'https://image.dayouqiantu.cn/5ca081af6183f.jpg,https://image.dayouqiantu.cn/5ca081af6183f.jpg,https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg', '多规格商品测试', '多规格商品测试', '多规格商品测试', '', '5', 4.00, 0.00, 9.00, 0.00, '张', 0, 5, 501, 1, 1, 1, 1, 1, '<p><br/></p><p><img src=\"https://image.dayouqiantu.cn/5ca081af6183f.jpg\"/></p>', '2020-09-12 17:24:51', NULL, 0, 0, 0, 0.00, 1.00, 0, 0, 19, 1, 34, 1);
COMMIT;

-- ----------------------------
-- Table structure for yx_store_product_attr
-- ----------------------------
DROP TABLE IF EXISTS `yx_store_product_attr`;
CREATE TABLE `yx_store_product_attr` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `product_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '商品ID',
  `attr_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '属性名',
  `attr_values` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '属性值',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `store_id` (`product_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='商品属性表';

-- ----------------------------
-- Records of yx_store_product_attr
-- ----------------------------
BEGIN;
INSERT INTO `yx_store_product_attr` VALUES (32, 1, '规格', '默认');
INSERT INTO `yx_store_product_attr` VALUES (55, 4, '规格', '默认');
INSERT INTO `yx_store_product_attr` VALUES (57, 2, '纸张', 'A4,A3');
INSERT INTO `yx_store_product_attr` VALUES (58, 2, '颜色', '白色,红色');
INSERT INTO `yx_store_product_attr` VALUES (59, 0, '规格', '默认');
INSERT INTO `yx_store_product_attr` VALUES (60, 6, '规格', '默认');
INSERT INTO `yx_store_product_attr` VALUES (62, 7, '规格', '默认');
INSERT INTO `yx_store_product_attr` VALUES (67, 8, '纸张', 'A4,A3,A5');
INSERT INTO `yx_store_product_attr` VALUES (68, 8, '颜色', '白色,红色');
INSERT INTO `yx_store_product_attr` VALUES (69, 5, '规格', '默认');
INSERT INTO `yx_store_product_attr` VALUES (70, 3, '规格', '默认');
COMMIT;

-- ----------------------------
-- Table structure for yx_store_product_attr_result
-- ----------------------------
DROP TABLE IF EXISTS `yx_store_product_attr_result`;
CREATE TABLE `yx_store_product_attr_result` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `product_id` bigint unsigned NOT NULL COMMENT '商品ID',
  `result` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品属性参数',
  `change_time` datetime NOT NULL COMMENT '上次修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `product_id` (`product_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='商品属性详情表';

-- ----------------------------
-- Records of yx_store_product_attr_result
-- ----------------------------
BEGIN;
INSERT INTO `yx_store_product_attr_result` VALUES (26, 1, '{\"attr\":[{\"attrHidden\":\"\",\"detail\":[\"默认\"],\"detailValue\":\"\",\"value\":\"规格\"}],\"value\":[{\"barCode\":\"00005\",\"brokerage\":0.0,\"brokerageTwo\":0.0,\"cost\":3.0,\"detail\":{\"规格\":\"默认\"},\"otPrice\":1.0,\"pic\":\"\",\"pinkPrice\":0.1,\"pinkStock\":100,\"price\":2.0,\"seckillPrice\":0.2,\"seckillStock\":100,\"stock\":999,\"value1\":\"规格\",\"value2\":\"\",\"volume\":0.0,\"weight\":0.0}]}', '2020-09-08 21:18:03');
INSERT INTO `yx_store_product_attr_result` VALUES (44, 4, '{\"attr\":[{\"attrHidden\":\"\",\"detail\":[\"默认\"],\"detailValue\":\"\",\"value\":\"规格\"}],\"value\":[{\"barCode\":\"\",\"brokerage\":0.0,\"brokerageTwo\":0.0,\"cost\":0.0,\"detail\":{\"规格\":\"默认\"},\"otPrice\":0.0,\"pic\":\"https://consoleapi.xinxintuan.co/file/pic/20200903171807584910.jpg\",\"pinkPrice\":22.0,\"pinkStock\":18,\"price\":0.0,\"seckillPrice\":0.0,\"seckillStock\":0,\"stock\":93,\"value1\":\"规格\",\"volume\":0.0,\"weight\":0.0}]}', '2020-09-12 15:55:17');
INSERT INTO `yx_store_product_attr_result` VALUES (46, 2, '{\"attr\":[{\"detail\":[\"A4\",\"A3\"],\"value\":\"纸张\"},{\"detail\":[\"白色\",\"红色\"],\"value\":\"颜色\"}],\"value\":[{\"barCode\":\"00001\",\"brokerage\":0.0,\"brokerageTwo\":0.0,\"cost\":11.0,\"detail\":{\"纸张\":\"A4\",\"颜色\":\"白色\"},\"otPrice\":12.0,\"pic\":\"https://consoleapi.xinxintuan.co/file/pic/20200903171807584910.jpg\",\"pinkPrice\":11.09,\"pinkStock\":100,\"price\":10.0,\"seckillPrice\":1.0,\"seckillStock\":100,\"stock\":991,\"value1\":\"A4\",\"value2\":\"白色\",\"volume\":0.0,\"weight\":0.0},{\"barCode\":\"00002\",\"brokerage\":0.0,\"brokerageTwo\":0.0,\"cost\":11.0,\"detail\":{\"纸张\":\"A4\",\"颜色\":\"红色\"},\"otPrice\":12.0,\"pic\":\"https://consoleapi.xinxintuan.co/file/pic/20200903171807584910.jpg\",\"pinkPrice\":22.88,\"pinkStock\":99,\"price\":11.0,\"seckillPrice\":2.0,\"seckillStock\":100,\"stock\":661,\"value1\":\"A4\",\"value2\":\"红色\",\"volume\":0.0,\"weight\":0.0},{\"barCode\":\"00003\",\"brokerage\":0.0,\"brokerageTwo\":0.0,\"cost\":11.0,\"detail\":{\"纸张\":\"A3\",\"颜色\":\"白色\"},\"otPrice\":12.0,\"pic\":\"https://consoleapi.xinxintuan.co/file/pic/20200903171807584910.jpg\",\"pinkPrice\":33.89,\"pinkStock\":99,\"price\":12.0,\"seckillPrice\":3.0,\"seckillStock\":100,\"stock\":995,\"value1\":\"A3\",\"value2\":\"白色\",\"volume\":0.0,\"weight\":0.0},{\"barCode\":\"00004\",\"brokerage\":0.0,\"brokerageTwo\":0.0,\"cost\":11.0,\"detail\":{\"纸张\":\"A3\",\"颜色\":\"红色\"},\"otPrice\":12.0,\"pic\":\"https://consoleapi.xinxintuan.co/file/pic/20200903171807584910.jpg\",\"pinkPrice\":45.66,\"pinkStock\":100,\"price\":13.0,\"seckillPrice\":4.0,\"seckillStock\":100,\"stock\":665,\"value1\":\"A3\",\"value2\":\"红色\",\"volume\":0.0,\"weight\":0.0}]}', '2020-09-12 16:10:09');
INSERT INTO `yx_store_product_attr_result` VALUES (48, 6, '{\"attr\":[{\"attrHidden\":\"\",\"detail\":[\"默认\"],\"detailValue\":\"\",\"value\":\"规格\"}],\"value\":[{\"barCode\":\"00002\",\"brokerage\":0.0,\"brokerageTwo\":0.0,\"cost\":22.0,\"detail\":{\"规格\":\"默认\"},\"otPrice\":33.0,\"pic\":\"https://consoleapi.xinxintuan.co/file/pic/20200903171807584910.jpg\",\"pinkPrice\":19.9,\"pinkStock\":99,\"price\":11.0,\"seckillPrice\":22.98,\"seckillStock\":2000,\"stock\":9989,\"value1\":\"规格\",\"volume\":1.0,\"weight\":1.0}]}', '2020-09-12 16:11:31');
INSERT INTO `yx_store_product_attr_result` VALUES (50, 7, '{\"attr\":[{\"attrHidden\":\"\",\"detail\":[\"默认\"],\"detailValue\":\"\",\"value\":\"规格\"}],\"value\":[{\"barCode\":\"\",\"brokerage\":0.0,\"brokerageTwo\":0.0,\"cost\":110.0,\"detail\":{\"规格\":\"默认\"},\"otPrice\":120.0,\"pic\":\"https://consoleapi.xinxintuan.co/file/pic/20200911093912577832.png\",\"pinkPrice\":0.0,\"pinkStock\":0,\"price\":100.0,\"seckillPrice\":0.0,\"seckillStock\":0,\"stock\":10,\"value1\":\"规格\",\"volume\":0.0,\"weight\":0.0}]}', '2020-09-12 17:03:58');
INSERT INTO `yx_store_product_attr_result` VALUES (53, 8, '{\"attr\":[{\"detail\":[\"A4\",\"A3\",\"A5\"],\"value\":\"纸张\"},{\"detail\":[\"白色\",\"红色\"],\"value\":\"颜色\"}],\"value\":[{\"barCode\":\"\",\"brokerage\":1.0,\"brokerageTwo\":1.0,\"cost\":1.0,\"detail\":{\"纸张\":\"A4\",\"颜色\":\"白色\"},\"otPrice\":12.0,\"pic\":\"https://image.dayouqiantu.cn/5ca081af6183f.jpg\",\"pinkPrice\":1.0,\"pinkStock\":9,\"price\":9.0,\"seckillPrice\":1.0,\"seckillStock\":10,\"stock\":96,\"value1\":\"A4\",\"value2\":\"白色\",\"volume\":0.0,\"weight\":0.0},{\"barCode\":\"\",\"brokerage\":1.0,\"brokerageTwo\":1.0,\"cost\":1.0,\"detail\":{\"纸张\":\"A4\",\"颜色\":\"红色\"},\"otPrice\":9.0,\"pic\":\"https://image.dayouqiantu.cn/5ca081af6183f.jpg\",\"pinkPrice\":2.0,\"pinkStock\":10,\"price\":8.0,\"seckillPrice\":1.0,\"seckillStock\":10,\"stock\":99,\"value1\":\"A4\",\"value2\":\"红色\",\"volume\":0.0,\"weight\":0.0},{\"barCode\":\"\",\"brokerage\":1.0,\"brokerageTwo\":1.0,\"cost\":1.0,\"detail\":{\"纸张\":\"A3\",\"颜色\":\"白色\"},\"otPrice\":9.0,\"pic\":\"https://image.dayouqiantu.cn/5ca081af6183f.jpg\",\"pinkPrice\":0.5,\"pinkStock\":10,\"price\":7.0,\"seckillPrice\":0.1,\"seckillStock\":10,\"stock\":88,\"value1\":\"A3\",\"value2\":\"白色\",\"volume\":0.0,\"weight\":0.0},{\"barCode\":\"\",\"brokerage\":1.0,\"brokerageTwo\":1.0,\"cost\":1.0,\"detail\":{\"纸张\":\"A3\",\"颜色\":\"红色\"},\"otPrice\":9.0,\"pic\":\"https://image.dayouqiantu.cn/5ca081af6183f.jpg\",\"pinkPrice\":1.0,\"pinkStock\":9,\"price\":6.0,\"seckillPrice\":0.1,\"seckillStock\":9,\"stock\":88,\"value1\":\"A3\",\"value2\":\"红色\",\"volume\":0.0,\"weight\":0.0},{\"barCode\":\"\",\"brokerage\":1.0,\"brokerageTwo\":1.0,\"cost\":1.0,\"detail\":{\"纸张\":\"A5\",\"颜色\":\"白色\"},\"otPrice\":9.0,\"pic\":\"https://image.dayouqiantu.cn/5ca081af6183f.jpg\",\"pinkPrice\":2.0,\"pinkStock\":9,\"price\":5.0,\"seckillPrice\":0.1,\"seckillStock\":9,\"stock\":65,\"value1\":\"A5\",\"value2\":\"白色\",\"volume\":0.0,\"weight\":0.0},{\"barCode\":\"\",\"brokerage\":1.0,\"brokerageTwo\":1.0,\"cost\":1.0,\"detail\":{\"纸张\":\"A5\",\"颜色\":\"红色\"},\"otPrice\":9.0,\"pic\":\"https://image.dayouqiantu.cn/5ca081af6183f.jpg\",\"pinkPrice\":3.0,\"pinkStock\":9,\"price\":4.0,\"seckillPrice\":0.1,\"seckillStock\":9,\"stock\":66,\"value1\":\"A5\",\"value2\":\"红色\",\"volume\":0.0,\"weight\":0.0}]}', '2020-09-12 18:23:33');
INSERT INTO `yx_store_product_attr_result` VALUES (54, 5, '{\"attr\":[{\"attrHidden\":\"\",\"detail\":[\"默认\"],\"detailValue\":\"\",\"value\":\"规格\"}],\"value\":[{\"barCode\":\"1231321\",\"brokerage\":0.0,\"brokerageTwo\":0.0,\"cost\":1.0,\"detail\":{\"规格\":\"默认\"},\"otPrice\":11.0,\"pic\":\"https://consoleapi.xinxintuan.co/file/pic/20200903171807641535.jpg\",\"pinkPrice\":29.0,\"pinkStock\":10,\"price\":2.0,\"seckillPrice\":11.99,\"seckillStock\":10,\"stock\":11,\"value1\":\"规格\",\"volume\":1.0,\"weight\":1.0}]}', '2020-09-13 10:15:24');
INSERT INTO `yx_store_product_attr_result` VALUES (55, 3, '{\"attr\":[{\"attrHidden\":\"\",\"detail\":[\"默认\"],\"detailValue\":\"\",\"value\":\"规格\"}],\"value\":[{\"barCode\":\"\",\"brokerage\":0.0,\"brokerageTwo\":0.0,\"cost\":1.0,\"detail\":{\"规格\":\"默认\"},\"otPrice\":1.0,\"pic\":\"https://consoleapi.xinxintuan.co/file/pic/20200903171807647067.jpg\",\"pinkPrice\":119.99,\"pinkStock\":10,\"price\":2.0,\"seckillPrice\":0.0,\"seckillStock\":0,\"stock\":9999,\"value1\":\"规格\",\"volume\":0.0,\"weight\":0.0}]}', '2020-09-13 22:18:46');
COMMIT;

-- ----------------------------
-- Table structure for yx_store_product_attr_value
-- ----------------------------
DROP TABLE IF EXISTS `yx_store_product_attr_value`;
CREATE TABLE `yx_store_product_attr_value` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `product_id` bigint unsigned NOT NULL COMMENT '商品ID',
  `sku` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品属性索引值 (attr_value|attr_value[|....])',
  `stock` int unsigned NOT NULL COMMENT '属性对应的库存',
  `sales` int unsigned DEFAULT '0' COMMENT '销量',
  `price` decimal(8,2) unsigned NOT NULL COMMENT '属性金额',
  `image` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图片',
  `unique` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '唯一值',
  `cost` decimal(8,2) unsigned NOT NULL COMMENT '成本价',
  `bar_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品条码',
  `ot_price` decimal(10,2) DEFAULT '0.00' COMMENT '原价',
  `weight` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '重量',
  `volume` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '体积',
  `brokerage` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '一级返佣',
  `brokerage_two` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '二级返佣',
  `pink_price` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '拼团价',
  `pink_stock` int NOT NULL DEFAULT '0' COMMENT '拼团库存',
  `seckill_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '秒杀价',
  `seckill_stock` int NOT NULL DEFAULT '0' COMMENT '秒杀库存',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `unique` (`unique`,`sku`) USING BTREE,
  KEY `store_id` (`product_id`,`sku`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='商品属性值表';

-- ----------------------------
-- Records of yx_store_product_attr_value
-- ----------------------------
BEGIN;
INSERT INTO `yx_store_product_attr_value` VALUES (44, 1, '默认', 993, 6, 2.00, 'https://consoleapi.xinxintuan.co/file/pic/20200903171807584910.jpg', 'd4ce8cd4bda84d479c11348e060afb8e', 3.00, '00005', 1.00, 0.00, 0.00, 0.00, 0.00, 0.10, 100, 0.20, 97);
INSERT INTO `yx_store_product_attr_value` VALUES (77, 4, '默认', 91, 2, 0.00, 'https://consoleapi.xinxintuan.co/file/pic/20200903171807584910.jpg', '6a1919d9178b48f3a75fe4640e51693e', 0.00, '', 0.00, 0.00, 0.00, 0.00, 0.00, 22.00, 17, 0.00, 0);
INSERT INTO `yx_store_product_attr_value` VALUES (79, 2, 'A4,白色', 987, 4, 10.00, 'https://consoleapi.xinxintuan.co/file/pic/20200903171807584910.jpg', '04e9d586cef8464aaee0a45439f12520', 11.00, '00001', 12.00, 0.00, 0.00, 0.00, 0.00, 11.09, 99, 1.00, 100);
INSERT INTO `yx_store_product_attr_value` VALUES (80, 2, 'A4,红色', 661, 0, 11.00, 'https://consoleapi.xinxintuan.co/file/pic/20200903171807584910.jpg', '3d1ffa92e6064c36b614d3046c268968', 11.00, '00002', 12.00, 0.00, 0.00, 0.00, 0.00, 22.88, 99, 2.00, 100);
INSERT INTO `yx_store_product_attr_value` VALUES (81, 2, 'A3,白色', 995, 0, 12.00, 'https://consoleapi.xinxintuan.co/file/pic/20200903171807584910.jpg', '344f3a0e9d644845ae483c8b9a84a112', 11.00, '00003', 12.00, 0.00, 0.00, 0.00, 0.00, 33.89, 99, 3.00, 100);
INSERT INTO `yx_store_product_attr_value` VALUES (82, 2, 'A3,红色', 665, 0, 13.00, 'https://consoleapi.xinxintuan.co/file/pic/20200903171807584910.jpg', '4a9ca8587855412ea7be9ba53f829052', 11.00, '00004', 12.00, 0.00, 0.00, 0.00, 0.00, 45.66, 100, 4.00, 100);
INSERT INTO `yx_store_product_attr_value` VALUES (83, 0, '默认', 10, 0, 100.00, 'https://consoleapi.xinxintuan.co/file/pic/20200911093912577832.png', 'bbdc071ff67c4892839eac9f62e7eb18', 110.00, '', 120.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0, 0.00, 0);
INSERT INTO `yx_store_product_attr_value` VALUES (84, 6, '默认', 9966, 23, 11.00, 'https://consoleapi.xinxintuan.co/file/pic/20200903171807584910.jpg', '90babee9cea24645870c6027fc420d42', 22.00, '00002', 33.00, 1.00, 1.00, 0.00, 0.00, 19.90, 95, 22.98, 2000);
INSERT INTO `yx_store_product_attr_value` VALUES (86, 7, '默认', 7, 3, 100.00, 'https://consoleapi.xinxintuan.co/file/pic/20200911093912577832.png', '789984b858bb489bb6313107e11fdc15', 110.00, '', 120.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0, 0.00, 0);
INSERT INTO `yx_store_product_attr_value` VALUES (99, 8, 'A4,白色', 95, 1, 9.00, 'https://image.dayouqiantu.cn/5ca081af6183f.jpg', '013f89ec740f478d8144f052909c0807', 1.00, '', 12.00, 0.00, 0.00, 1.00, 1.00, 1.00, 9, 1.00, 10);
INSERT INTO `yx_store_product_attr_value` VALUES (100, 8, 'A4,红色', 99, 0, 8.00, 'https://image.dayouqiantu.cn/5ca081af6183f.jpg', '4e1d7c450a8e48f6ae4ec7788f0cfd90', 1.00, '', 9.00, 0.00, 0.00, 1.00, 1.00, 2.00, 10, 1.00, 10);
INSERT INTO `yx_store_product_attr_value` VALUES (101, 8, 'A3,白色', 88, 0, 7.00, 'https://image.dayouqiantu.cn/5ca081af6183f.jpg', '3ce61e5e6f4b46a7a210862af85212bc', 1.00, '', 9.00, 0.00, 0.00, 1.00, 1.00, 0.50, 10, 0.10, 10);
INSERT INTO `yx_store_product_attr_value` VALUES (102, 8, 'A3,红色', 88, 0, 6.00, 'https://image.dayouqiantu.cn/5ca081af6183f.jpg', 'b1e48ae88299479899d95b86dd1be700', 1.00, '', 9.00, 0.00, 0.00, 1.00, 1.00, 1.00, 9, 0.10, 9);
INSERT INTO `yx_store_product_attr_value` VALUES (103, 8, 'A5,白色', 65, 0, 5.00, 'https://image.dayouqiantu.cn/5ca081af6183f.jpg', '81b7a068f84a498ca8ad4e1916011e00', 1.00, '', 9.00, 0.00, 0.00, 1.00, 1.00, 2.00, 9, 0.10, 9);
INSERT INTO `yx_store_product_attr_value` VALUES (104, 8, 'A5,红色', 66, 0, 4.00, 'https://image.dayouqiantu.cn/5ca081af6183f.jpg', '8112da382da7420f961203372f7c1d31', 1.00, '', 9.00, 0.00, 0.00, 1.00, 1.00, 3.00, 9, 0.10, 9);
INSERT INTO `yx_store_product_attr_value` VALUES (105, 5, '默认', 3, 8, 2.00, 'https://consoleapi.xinxintuan.co/file/pic/20200903171807641535.jpg', '040fb7ab2a8b4ff3b6c9d1c78ea3e889', 1.00, '1231321', 11.00, 1.00, 1.00, 0.00, 0.00, 29.00, 5, 11.99, 8);
INSERT INTO `yx_store_product_attr_value` VALUES (106, 3, '默认', 9996, 3, 2.00, 'https://consoleapi.xinxintuan.co/file/pic/20200903171807647067.jpg', '43cdbd1043474b4e97a1cffffa18071c', 1.00, '', 1.00, 0.00, 0.00, 0.00, 0.00, 119.99, 10, 0.00, 0);
COMMIT;

-- ----------------------------
-- Table structure for yx_store_product_relation
-- ----------------------------
DROP TABLE IF EXISTS `yx_store_product_relation`;
CREATE TABLE `yx_store_product_relation` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `uid` bigint unsigned NOT NULL COMMENT '用户ID',
  `product_id` bigint unsigned NOT NULL COMMENT '商品ID',
  `type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '类型(收藏(collect）、点赞(like))',
  `category` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '某种类型的商品(普通商品、秒杀商品)',
  `create_time` datetime NOT NULL COMMENT '添加时间',
  `update_time` datetime DEFAULT NULL,
  `is_del` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uid` (`uid`,`product_id`,`type`,`category`) USING BTREE,
  KEY `type` (`type`) USING BTREE,
  KEY `category` (`category`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='商品点赞和收藏表';

-- ----------------------------
-- Records of yx_store_product_relation
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for yx_store_product_reply
-- ----------------------------
DROP TABLE IF EXISTS `yx_store_product_reply`;
CREATE TABLE `yx_store_product_reply` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '评论ID',
  `uid` bigint NOT NULL COMMENT '用户ID',
  `oid` bigint NOT NULL COMMENT '订单ID',
  `unique` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '唯一id',
  `product_id` bigint NOT NULL COMMENT '产品id',
  `reply_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'product' COMMENT '某种商品类型(普通商品、秒杀商品）',
  `product_score` tinyint(1) NOT NULL COMMENT '商品分数',
  `service_score` tinyint(1) NOT NULL COMMENT '服务分数',
  `comment` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '评论内容',
  `pics` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论图片',
  `create_time` datetime NOT NULL COMMENT '评论时间',
  `update_time` datetime DEFAULT NULL,
  `merchant_reply_content` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '管理员回复内容',
  `merchant_reply_time` datetime DEFAULT NULL COMMENT '管理员回复时间',
  `is_del` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '0未删除1已删除',
  `is_reply` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0未回复1已回复',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `add_time` (`create_time`) USING BTREE,
  KEY `parent_id` (`reply_type`) USING BTREE,
  KEY `is_del` (`is_del`) USING BTREE,
  KEY `product_score` (`product_score`) USING BTREE,
  KEY `service_score` (`service_score`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='评论表';

-- ----------------------------
-- Records of yx_store_product_reply
-- ----------------------------
BEGIN;
INSERT INTO `yx_store_product_reply` VALUES (3, 43, 67, 'c1907f2efeff48168c119ee28ddcac42', 8, 'product', 5, 5, '棒棒哒', '', '2020-09-12 17:45:11', NULL, NULL, NULL, 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for yx_store_seckill
-- ----------------------------
DROP TABLE IF EXISTS `yx_store_seckill`;
CREATE TABLE `yx_store_seckill` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '商品秒杀产品表id',
  `product_id` bigint unsigned NOT NULL COMMENT '商品id',
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '推荐图',
  `images` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '轮播图',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '活动标题',
  `info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '简介',
  `price` decimal(10,2) unsigned DEFAULT NULL COMMENT '价格',
  `cost` decimal(8,2) unsigned DEFAULT '0.00' COMMENT '成本',
  `ot_price` decimal(10,2) unsigned DEFAULT NULL COMMENT '原价',
  `give_integral` decimal(10,2) unsigned DEFAULT NULL COMMENT '返多少积分',
  `sort` int unsigned NOT NULL COMMENT '排序',
  `stock` int unsigned NOT NULL COMMENT '库存',
  `sales` int unsigned NOT NULL DEFAULT '0' COMMENT '销量',
  `unit_name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '单位名',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '内容',
  `start_time` date NOT NULL COMMENT '开始时间',
  `stop_time` date NOT NULL COMMENT '结束时间',
  `create_time` datetime NOT NULL COMMENT '添加时间',
  `update_time` datetime DEFAULT NULL,
  `status` tinyint unsigned NOT NULL COMMENT '产品状态',
  `is_del` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '删除 0未删除1已删除',
  `num` int unsigned NOT NULL COMMENT '最多秒杀几个',
  `is_show` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '显示',
  `time_id` int unsigned DEFAULT '0' COMMENT '时间段id',
  `spec_type` tinyint(1) DEFAULT NULL COMMENT '规格 0单 1多',
  `temp_id` int DEFAULT NULL COMMENT '运费模板id',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `product_id` (`product_id`) USING BTREE,
  KEY `start_time` (`start_time`,`stop_time`) USING BTREE,
  KEY `is_del` (`is_del`) USING BTREE,
  KEY `is_show` (`status`) USING BTREE,
  KEY `add_time` (`create_time`) USING BTREE,
  KEY `sort` (`sort`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='商品秒杀产品表';

-- ----------------------------
-- Records of yx_store_seckill
-- ----------------------------
BEGIN;
INSERT INTO `yx_store_seckill` VALUES (8, 8, 'https://image.dayouqiantu.cn/5ca081af6183f.jpg', 'https://image.dayouqiantu.cn/5ca081af6183f.jpg,https://image.dayouqiantu.cn/5ca081af6183f.jpg,https://image.dayouqiantu.cn/5ca04fa9c08ef.jpg', '多规格商品测试', '多规格商品测试', 0.01, 1.00, 9.00, 0.00, 0, 501, 5, '张', '<p><br/></p><p><img src=\"https://image.dayouqiantu.cn/5ca081af6183f.jpg\"/></p>', '2021-04-04', '2021-04-06', '2021-04-04 11:22:40', NULL, 1, 0, 1, 1, 210, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for yx_system_attachment
-- ----------------------------
DROP TABLE IF EXISTS `yx_system_attachment`;
CREATE TABLE `yx_system_attachment` (
  `att_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '附件名称',
  `att_dir` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '附件路径',
  `satt_dir` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '压缩图片路径',
  `att_size` char(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '附件大小',
  `att_type` char(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '附件类型',
  `pid` int NOT NULL DEFAULT '0' COMMENT '分类ID0编辑器,1产品图片,2拼团图片,3砍价图片,4秒杀图片,5文章图片,6组合数据图',
  `image_type` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '图片上传类型 1本地 2七牛云 3OSS 4COS ',
  `module_type` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '图片上传模块类型 1 后台上传 2 用户生成',
  `uid` bigint unsigned DEFAULT '0' COMMENT '用户id',
  `invite_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '邀请码',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_del` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`att_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='附件管理表';

-- ----------------------------
-- Records of yx_system_attachment
-- ----------------------------
BEGIN;
INSERT INTO `yx_system_attachment` VALUES (113, '12_55_wechat_bargain_share_wap.jpg', '/home/yshop/file/qrcode/12_55_wechat_bargain_share_wap.jpg', 'qrcode/12_55_wechat_bargain_share_wap.jpg', '3897220', 'image/jpeg', 0, 1, 1, 0, '', '2020-09-14 13:38:52', NULL, 0);
INSERT INTO `yx_system_attachment` VALUES (114, '12_55_wechat_bargain_user_spread.jpg', '/home/yshop/file/qrcode/12_55_wechat_bargain_user_spread.jpg', 'qrcode/12_55_wechat_bargain_user_spread.jpg', '43231', 'image/jpeg', 0, 1, 1, 0, '', '2020-09-14 13:38:53', NULL, 0);
INSERT INTO `yx_system_attachment` VALUES (115, '4_59_uniappH5_product_detail_wap.jpg', '/home/yshop/file/qrcode/4_59_uniappH5_product_detail_wap.jpg', 'qrcode/4_59_uniappH5_product_detail_wap.jpg', '3904929', 'image/jpeg', 0, 1, 1, 0, '', '2020-09-14 13:43:20', NULL, 0);
INSERT INTO `yx_system_attachment` VALUES (116, '4_59_uniappH5_product_user_spread.jpg', '/home/yshop/file/qrcode/4_59_uniappH5_product_user_spread.jpg', 'qrcode/4_59_uniappH5_product_user_spread.jpg', '113247', 'image/jpeg', 0, 1, 1, 0, '', '2020-09-14 13:43:20', NULL, 0);
INSERT INTO `yx_system_attachment` VALUES (117, '4_75_uniappH5_product_detail_wap.jpg', '/home/yshop/file/qrcode/4_75_uniappH5_product_detail_wap.jpg', 'qrcode/4_75_uniappH5_product_detail_wap.jpg', '4025717', 'image/jpeg', 0, 1, 1, 0, '', '2020-09-14 17:25:11', NULL, 0);
INSERT INTO `yx_system_attachment` VALUES (118, '4_75_uniappH5_product_user_spread.jpg', '/home/yshop/file/qrcode/4_75_uniappH5_product_user_spread.jpg', 'qrcode/4_75_uniappH5_product_user_spread.jpg', '113146', 'image/jpeg', 0, 1, 1, 0, '', '2020-09-14 17:25:11', NULL, 0);
INSERT INTO `yx_system_attachment` VALUES (119, '915891458048_yshop.jpg', '/home/yshop/file/qrcode/915891458048_yshop.jpg', 'qrcode/915891458048_yshop.jpg', '4148444', 'image/jpeg', 0, 1, 1, 0, '', '2020-09-14 22:21:17', NULL, 0);
INSERT INTO `yx_system_attachment` VALUES (120, '915891458048_yshop.jpg', '/home/yshop/file/qrcode/915891458048_yshop.jpg', 'qrcode/915891458048_yshop.jpg', '4148444', 'image/jpeg', 0, 1, 1, 0, '', '2020-09-14 22:21:17', NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for yx_system_config
-- ----------------------------
DROP TABLE IF EXISTS `yx_system_config`;
CREATE TABLE `yx_system_config` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '配置id',
  `menu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字段名称',
  `value` varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '默认值',
  `sort` int unsigned DEFAULT '0' COMMENT '排序',
  `status` tinyint unsigned DEFAULT '0' COMMENT '是否隐藏',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=227 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='配置表';

-- ----------------------------
-- Records of yx_system_config
-- ----------------------------
BEGIN;
INSERT INTO `yx_system_config` VALUES (162, 'wechat_share_img', '', NULL, NULL);
INSERT INTO `yx_system_config` VALUES (165, 'wechat_avatar', '', NULL, NULL);
INSERT INTO `yx_system_config` VALUES (172, 'wechat_share_synopsis', 'springboot2前后端分离电商系统', NULL, NULL);
INSERT INTO `yx_system_config` VALUES (174, 'api', 'http://你的H5端域名/api/wechat/serve', NULL, NULL);
INSERT INTO `yx_system_config` VALUES (175, 'wechat_share_title', 'yshop电商系统', NULL, NULL);
INSERT INTO `yx_system_config` VALUES (177, 'store_postage', '1', 0, 0);
INSERT INTO `yx_system_config` VALUES (178, 'store_free_postage', '1', 0, 0);
INSERT INTO `yx_system_config` VALUES (179, 'integral_ratio', '1', 0, 0);
INSERT INTO `yx_system_config` VALUES (180, 'store_brokerage_ratio', '70', NULL, NULL);
INSERT INTO `yx_system_config` VALUES (182, 'user_extract_min_price', '1', NULL, NULL);
INSERT INTO `yx_system_config` VALUES (183, 'store_brokerage_two', '30', NULL, NULL);
INSERT INTO `yx_system_config` VALUES (184, 'site_url', 'http://127.0.0.1:8080', 0, 0);
INSERT INTO `yx_system_config` VALUES (185, 'api_url', 'http://127.0.0.1:8008', 0, 0);
INSERT INTO `yx_system_config` VALUES (186, 'order_cancel_job_time', '20', 0, 0);
INSERT INTO `yx_system_config` VALUES (187, 'wechat_appid', 'wxc061dee8806ff712', NULL, NULL);
INSERT INTO `yx_system_config` VALUES (188, 'wechat_appsecret', '1111', NULL, NULL);
INSERT INTO `yx_system_config` VALUES (189, 'wechat_encodingaeskey', 'yYuBUkC8BXImCXyu7O6hkzLj4TC5nxsWPfL4CQAZPNY', NULL, NULL);
INSERT INTO `yx_system_config` VALUES (190, 'wechat_token', 'yshop', NULL, NULL);
INSERT INTO `yx_system_config` VALUES (191, 'wxpay_mchId', '1111', NULL, NULL);
INSERT INTO `yx_system_config` VALUES (192, 'wxpay_appId', '111111', NULL, NULL);
INSERT INTO `yx_system_config` VALUES (193, 'wxpay_mchKey', '1111', NULL, NULL);
INSERT INTO `yx_system_config` VALUES (194, 'wxapp_appId', '121221', NULL, NULL);
INSERT INTO `yx_system_config` VALUES (195, 'wxapp_secret', '121212', NULL, NULL);
INSERT INTO `yx_system_config` VALUES (196, 'wxpay_keyPath', 'http://localhost:8000/file/pic/list_30-20200110053337209.png', 0, 0);
INSERT INTO `yx_system_config` VALUES (198, 'store_brokerage_open', '1', 0, 0);
INSERT INTO `yx_system_config` VALUES (199, 'integral_full', '0', NULL, NULL);
INSERT INTO `yx_system_config` VALUES (200, 'integral_max', '0', NULL, NULL);
INSERT INTO `yx_system_config` VALUES (201, 'store_user_min_recharge', '1', 0, 0);
INSERT INTO `yx_system_config` VALUES (203, 'store_self_mention', '1', NULL, NULL);
INSERT INTO `yx_system_config` VALUES (204, 'tengxun_map_key', '11111111111', NULL, NULL);
INSERT INTO `yx_system_config` VALUES (205, 'wechat_id', '', NULL, NULL);
INSERT INTO `yx_system_config` VALUES (206, 'wechat_type', '', NULL, NULL);
INSERT INTO `yx_system_config` VALUES (207, 'wechat_encode', '', NULL, NULL);
INSERT INTO `yx_system_config` VALUES (208, 'wechat_sourceid', '', NULL, NULL);
INSERT INTO `yx_system_config` VALUES (209, 'wechat_name', '', NULL, NULL);
INSERT INTO `yx_system_config` VALUES (210, 'wechat_qrcode', '', NULL, NULL);
INSERT INTO `yx_system_config` VALUES (211, 'wx_native_app_appId', 'wx7c84ede33062d1e4', NULL, NULL);
INSERT INTO `yx_system_config` VALUES (212, 'file_store_mode', '1', 0, 0);
INSERT INTO `yx_system_config` VALUES (213, 'sms_access_key', 'yshopyshop', 0, 0);
INSERT INTO `yx_system_config` VALUES (214, 'sms_enable', '2', 0, 0);
INSERT INTO `yx_system_config` VALUES (215, 'sms_access_secret', 'yshop', 0, 0);
INSERT INTO `yx_system_config` VALUES (216, 'sms_region', 'cn-hangzhou', 0, 0);
INSERT INTO `yx_system_config` VALUES (217, 'sms_templateId', '111111111', 0, 0);
INSERT INTO `yx_system_config` VALUES (218, 'sms_sign', 'yshop', 0, 0);
INSERT INTO `yx_system_config` VALUES (219, 'exp_enable', '1', 0, 0);
INSERT INTO `yx_system_config` VALUES (220, 'exp_appId', '1607734', 0, 0);
INSERT INTO `yx_system_config` VALUES (221, 'exp_appKey', '33333333333333', 0, 0);
INSERT INTO `yx_system_config` VALUES (222, 'admin_api_url', 'http://127.0.0.1:8001', 0, 0);
INSERT INTO `yx_system_config` VALUES (223, 'imageArr', '[\"\"]', 0, 0);
INSERT INTO `yx_system_config` VALUES (224, 'wechat_ma_encodingaeskey', '', 0, 0);
INSERT INTO `yx_system_config` VALUES (225, 'wxapi', 'http://你的H5api端域名/api/wxapp/serve', 0, 0);
INSERT INTO `yx_system_config` VALUES (226, 'wechat_ma_token', '', 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for yx_system_group_data
-- ----------------------------
DROP TABLE IF EXISTS `yx_system_group_data`;
CREATE TABLE `yx_system_group_data` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '组合数据详情ID',
  `group_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '对应的数据名称',
  `value` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据组对应的数据值（json数据）',
  `create_time` datetime DEFAULT NULL COMMENT '添加数据时间',
  `update_time` datetime DEFAULT NULL,
  `sort` int DEFAULT '0' COMMENT '数据排序',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态（1：开启；2：关闭；）',
  `is_del` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=227 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='组合数据详情表';

-- ----------------------------
-- Records of yx_system_group_data
-- ----------------------------
BEGIN;
INSERT INTO `yx_system_group_data` VALUES (177, 'yshop_home_banner', '{\"imageArr\":[\"https://image.dayouqiantu.cn/5c9f05aee5059.jpg\"],\"name\":\"banner2\",\"id\":177,\"pic\":\"https://image.dayouqiantu.cn/5c9f05aee5059.jpg\",\"sort\":1,\"url\":\"wwww\",\"status\":0}', '2020-06-25 18:54:35', '2021-04-04 15:52:00', 1, 0, 0);
INSERT INTO `yx_system_group_data` VALUES (180, 'yshop_home_menus', '{\"imageArr\":[\"https://image.dayouqiantu.cn/all.png\"],\"uniapp_url\":\"/pages/shop/GoodsList/index\",\"name\":\"全部商品\",\"id\":180,\"pic\":\"https://image.dayouqiantu.cn/all.png\",\"sort\":9,\"url\":\"/goods_list\",\"wxapp_url\":\"/pages/shop/GoodsClass/main\",\"status\":1}', '2020-06-25 18:54:35', NULL, 9, 1, 0);
INSERT INTO `yx_system_group_data` VALUES (182, 'yshop_home_roll_news', '{\"uniapp_url\":\"/pages/shop/news/NewsList/index\",\"id\":182,\"pic\":\"https://i.loli.net/2019/10/18/DqOUgNf7wjuFpPT.png\",\"sort\":2,\"title\":\"分销、拼团、商户功能上线啦！\",\"url\":\"/news_list\",\"info\":\"yshop3.0\",\"wxapp_url\":\"/pages/shop/news/NewsList/main\",\"status\":1}', '2020-06-25 18:54:35', '2020-06-27 15:47:38', 2, 1, 0);
INSERT INTO `yx_system_group_data` VALUES (183, 'yshop_hot_search', '{\"id\":183,\"title\":\"照片\"}', '2020-06-25 18:54:35', NULL, 0, 1, 0);
INSERT INTO `yx_system_group_data` VALUES (184, 'yshop_hot_search', '{\"id\":184,\"title\":\"springboot\"}', '2020-06-25 18:54:35', NULL, 0, 1, 0);
INSERT INTO `yx_system_group_data` VALUES (187, 'yshop_home_roll_news', '{\"uniapp_url\":\"/pages/shop/news/NewsList/index\",\"id\":187,\"sort\":1,\"url\":\"/news_list\",\"info\":\"yshop基于springboot2+Mybatisplus+jwt商城系统\",\"wxapp_url\":\"/pages/shop/news/NewsList/main\",\"status\":1}', '2020-06-25 18:54:35', '2020-06-27 15:47:22', 4, 1, 0);
INSERT INTO `yx_system_group_data` VALUES (188, 'yshop_hot_search', '{\"id\":188,\"title\":\"打印\"}', '2020-06-25 18:54:35', NULL, 0, 1, 0);
INSERT INTO `yx_system_group_data` VALUES (189, 'yshop_my_menus', '{\"imageArr\":[\"https://image.dayouqiantu.cn/5dec896eeb25a.png\"],\"uniapp_url\":\"/pages/user/UserVip/index\",\"name\":\"会员中心\",\"id\":189,\"pic\":\"https://image.dayouqiantu.cn/5dec896eeb25a.png\",\"sort\":9,\"url\":\"/user/vip\",\"wxapp_url\":\"/pages/user/UserVip/main\",\"status\":1}', '2020-06-25 18:54:35', NULL, 9, 1, 0);
INSERT INTO `yx_system_group_data` VALUES (190, 'yshop_my_menus', '{\"imageArr\":[\"https://image.dayouqiantu.cn/5db428984d64d.png\"],\"uniapp_url\":\"/pages/user/coupon/UserCoupon/index\",\"name\":\"优惠券\",\"id\":190,\"pic\":\"https://image.dayouqiantu.cn/5db428984d64d.png\",\"sort\":8,\"url\":\"/user/get_coupon\",\"wxapp_url\":\"/pages/user/coupon/UserCoupon/main\",\"status\":1}', '2020-06-25 18:54:35', NULL, 8, 1, 0);
INSERT INTO `yx_system_group_data` VALUES (191, 'yshop_my_menus', '{\"imageArr\":[\"https://image.dayouqiantu.cn/5db428a8d3ab0.png\"],\"uniapp_url\":\"/pages/shop/GoodsCollection/index\",\"name\":\"收藏商品\",\"id\":191,\"pic\":\"https://image.dayouqiantu.cn/5db428a8d3ab0.png\",\"sort\":7,\"url\":\"/collection\",\"wxapp_url\":\"/pages/shop/GoodsCollection/main\",\"status\":1}', '2020-06-25 18:54:35', NULL, 7, 1, 0);
INSERT INTO `yx_system_group_data` VALUES (192, 'yshop_my_menus', '{\"imageArr\":[\"https://image.dayouqiantu.cn/5db428bd61b73.png\"],\"uniapp_url\":\"/pages/user/address/AddressManagement/index\",\"name\":\"地址管理\",\"id\":192,\"pic\":\"https://image.dayouqiantu.cn/5db428bd61b73.png\",\"sort\":6,\"url\":\"/user/add_manage\",\"wxapp_url\":\"/pages/user/address/AddressManagement/main\",\"status\":1}', '2020-06-25 18:54:35', NULL, 6, 1, 0);
INSERT INTO `yx_system_group_data` VALUES (193, 'yshop_my_menus', '{\"imageArr\":[\"https://image.dayouqiantu.cn/5db428e28dd48.png\"],\"uniapp_url\":\"/pages/user/promotion/UserPromotion/index\",\"name\":\"我的推广\",\"id\":193,\"pic\":\"https://image.dayouqiantu.cn/5db428e28dd48.png\",\"sort\":5,\"url\":\"/user/user_promotion\",\"wxapp_url\":\"/pages/user/promotion/UserPromotion/main\",\"status\":1}', '2020-06-25 18:54:35', NULL, 5, 1, 0);
INSERT INTO `yx_system_group_data` VALUES (194, 'yshop_my_menus', '{\"imageArr\":[\"https://image.dayouqiantu.cn/5db42a4208c55.png\"],\"uniapp_url\":\"/pages/user/UserAccount/index\",\"name\":\"我的余额\",\"id\":194,\"pic\":\"https://image.dayouqiantu.cn/5db42a4208c55.png\",\"sort\":4,\"url\":\"/user/account\",\"wxapp_url\":\"/pages/user/UserAccount/main\",\"status\":1}', '2020-06-25 18:54:35', NULL, 4, 1, 0);
INSERT INTO `yx_system_group_data` VALUES (195, 'yshop_my_menus', '{\"imageArr\":[\"https://image.dayouqiantu.cn/5db428f410462.png\"],\"uniapp_url\":\"/pages/user/signIn/Integral/index\",\"name\":\"我的积分\",\"id\":195,\"pic\":\"https://image.dayouqiantu.cn/5db428f410462.png\",\"sort\":3,\"url\":\"/user/integral\",\"wxapp_url\":\"/pages/user/signIn/Integral/main\",\"status\":1}', '2020-06-25 18:54:35', NULL, 3, 1, 0);
INSERT INTO `yx_system_group_data` VALUES (196, 'yshop_home_menus', '{\"imageArr\":[\"https://image.dayouqiantu.cn/xw.png\"],\"uniapp_url\":\"/pages/shop/news/NewsList/index\",\"name\":\"图文资讯\",\"id\":196,\"pic\":\"https://image.dayouqiantu.cn/xw.png\",\"sort\":8,\"url\":\"/news_list\",\"wxapp_url\":\"/pages/shop/news/NewsList/main\",\"status\":1}', '2020-06-25 18:54:35', '2020-06-26 18:26:24', 8, 1, 0);
INSERT INTO `yx_system_group_data` VALUES (197, 'yshop_home_menus', '{\"imageArr\":[\"https://image.dayouqiantu.cn/colle.png\"],\"uniapp_url\":\"/pages/shop/GoodsCollection/index\",\"name\":\"我的收藏\",\"id\":197,\"pic\":\"https://image.dayouqiantu.cn/colle.png\",\"sort\":7,\"url\":\"/collection\",\"wxapp_url\":\"/pages/shop/GoodsCollection/main\",\"status\":1}', '2020-06-25 18:54:35', NULL, 7, 1, 0);
INSERT INTO `yx_system_group_data` VALUES (199, 'yshop_home_menus', '{\"imageArr\":[\"https://image.dayouqiantu.cn/cou.png\"],\"uniapp_url\":\"/pages/user/coupon/GetCoupon/index\",\"name\":\"优惠券\",\"id\":199,\"pic\":\"https://image.dayouqiantu.cn/cou.png\",\"sort\":6,\"url\":\"/user/get_coupon\",\"wxapp_url\":\"/pages/user/coupon/GetCoupon/main\",\"status\":1}', '2020-06-25 18:54:35', '2020-06-26 18:26:36', 6, 1, 0);
INSERT INTO `yx_system_group_data` VALUES (200, 'yshop_home_menus', '{\"imageArr\":[\"https://image.dayouqiantu.cn/62ac09d2914d36c65b9b59d2147d809a.png\"],\"uniapp_url\":\"/pages/activity/GoodsGroup/index\",\"name\":\"拼团专区\",\"id\":200,\"pic\":\"https://image.dayouqiantu.cn/62ac09d2914d36c65b9b59d2147d809a.png\",\"sort\":5,\"url\":\"/activity/group\",\"wxapp_url\":\"/pages/activity/GoodsGroup/main\",\"status\":1}', '2020-06-25 18:54:35', '2020-06-26 18:26:45', 5, 1, 0);
INSERT INTO `yx_system_group_data` VALUES (201, 'yshop_my_menus', '{\"imageArr\":[\"https://image.dayouqiantu.cn/5ddb7a37d58d9.png\"],\"uniapp_url\":\"/pages/orderAdmin/OrderIndex/index\",\"name\":\"商户管理\",\"id\":201,\"pic\":\"https://image.dayouqiantu.cn/5ddb7a37d58d9.png\",\"sort\":2,\"url\":\"/customer/index\",\"wxapp_url\":\"/pages/orderAdmin/OrderIndex/main\",\"status\":1}', '2020-06-25 18:54:35', NULL, 2, 1, 0);
INSERT INTO `yx_system_group_data` VALUES (202, 'yshop_sign_day_num', '{\"sign_num\":\"10\",\"id\":205,\"day\":\"第一天\"}', '2020-06-25 18:54:35', NULL, 9, 1, 0);
INSERT INTO `yx_system_group_data` VALUES (203, 'yshop_sign_day_num', '{\"sign_num\":\"20\",\"id\":\"\",\"day\":\"第二天\"}', '2020-06-25 18:54:35', NULL, 8, 1, 0);
INSERT INTO `yx_system_group_data` VALUES (204, 'yshop_sign_day_num', '{\"sign_num\":\"30\",\"id\":\"\",\"day\":\"第三天\"}', '2020-06-25 18:54:35', NULL, 7, 1, 0);
INSERT INTO `yx_system_group_data` VALUES (205, 'yshop_sign_day_num', '{\"addTime\":\"\",\"sign_num\":\"40\",\"id\":\"\",\"sort\":\"\",\"value\":\"\",\"day\":\"第四天\",\"status\":\"\"}', '2020-06-25 18:54:35', NULL, 6, 1, 0);
INSERT INTO `yx_system_group_data` VALUES (206, 'yshop_sign_day_num', '{\"addTime\":\"\",\"sign_num\":\"50\",\"id\":\"\",\"sort\":\"\",\"value\":\"\",\"day\":\"第五天\",\"status\":\"\"}', '2020-06-25 18:54:35', NULL, 5, 1, 0);
INSERT INTO `yx_system_group_data` VALUES (207, 'yshop_sign_day_num', '{\"addTime\":\"\",\"sign_num\":\"60\",\"id\":\"\",\"sort\":\"\",\"value\":\"\",\"day\":\"第六天\",\"status\":\"\"}', '2020-06-25 18:54:35', NULL, 4, 1, 0);
INSERT INTO `yx_system_group_data` VALUES (208, 'yshop_sign_day_num', '{\"addTime\":\"\",\"sign_num\":\"100\",\"id\":\"\",\"sort\":\"\",\"value\":\"\",\"day\":\"奖励\",\"status\":\"\"}', '2020-06-25 18:54:35', NULL, 3, 1, 0);
INSERT INTO `yx_system_group_data` VALUES (209, 'yshop_home_menus', '{\"imageArr\":[\"https://image.dayouqiantu.cn/29ea4acebbf99e7eaf6f85af2b6d79ae.png\"],\"uniapp_url\":\"/pages/user/signIn/Sign/index\",\"name\":\"积分签到\",\"id\":209,\"pic\":\"https://image.dayouqiantu.cn/29ea4acebbf99e7eaf6f85af2b6d79ae.png\",\"sort\":4,\"url\":\"/user/sign\",\"wxapp_url\":\"/pages/user/signIn/Sign/main\",\"status\":1}', '2020-06-25 18:54:35', '2020-06-26 18:26:56', 4, 1, 0);
INSERT INTO `yx_system_group_data` VALUES (210, 'yshop_seckill_time', '{\"continued\":2,\"id\":\"\",\"time\":5}', '2020-06-25 18:54:35', NULL, 0, 1, 0);
INSERT INTO `yx_system_group_data` VALUES (211, 'yshop_seckill_time', '{\"addTime\":\"\",\"continued\":\"3\",\"id\":\"\",\"sort\":\"\",\"time\":\"7\",\"value\":\"\",\"status\":\"\"}', '2020-06-25 18:54:35', NULL, 0, 1, 0);
INSERT INTO `yx_system_group_data` VALUES (212, 'yshop_seckill_time', '{\"addTime\":\"\",\"continued\":\"2\",\"id\":\"\",\"sort\":\"\",\"time\":\"10\",\"value\":\"\",\"status\":\"\"}', '2020-06-25 18:54:35', NULL, 0, 1, 0);
INSERT INTO `yx_system_group_data` VALUES (213, 'yshop_seckill_time', '{\"addTime\":\"\",\"continued\":\"3\",\"id\":\"\",\"sort\":\"\",\"time\":\"12\",\"value\":\"\",\"status\":\"\"}', '2020-06-25 18:54:35', NULL, 0, 1, 0);
INSERT INTO `yx_system_group_data` VALUES (214, 'yshop_seckill_time', '{\"continued\":\"10\",\"id\":214,\"time\":\"15\"}', '2020-06-25 18:54:35', '2020-09-05 17:16:06', 0, 1, 0);
INSERT INTO `yx_system_group_data` VALUES (215, 'yshop_seckill_time', '{\"continued\":\"2\",\"id\":223,\"time\":\"19\"}', '2020-06-25 18:54:35', NULL, 0, 1, 0);
INSERT INTO `yx_system_group_data` VALUES (216, 'yshop_home_menus', '{\"imageArr\":[\"https://image.dayouqiantu.cn/b0344c148141b50d68db9722708ea49e.png\"],\"uniapp_url\":\"/pages/activity/GoodsSeckill/index\",\"name\":\"秒杀专区\",\"id\":216,\"pic\":\"https://image.dayouqiantu.cn/b0344c148141b50d68db9722708ea49e.png\",\"sort\":3,\"url\":\"/activity/goods_seckill\",\"wxapp_url\":\"/pages/activity/GoodsSeckill/main\",\"status\":1}', '2020-06-25 18:54:35', '2020-06-26 18:27:06', 3, 1, 0);
INSERT INTO `yx_system_group_data` VALUES (217, 'yshop_home_menus', '{\"imageArr\":[\"https://image.dayouqiantu.cn/bar.png\"],\"uniapp_url\":\"/pages/activity/GoodsBargain/index\",\"name\":\"砍价专区\",\"id\":217,\"pic\":\"https://image.dayouqiantu.cn/bar.png\",\"sort\":2,\"url\":\"/activity/bargain\",\"wxapp_url\":\"/pages/activity/GoodsBargain/main\",\"status\":1}', '2020-06-25 18:54:35', '2020-06-26 18:27:16', 2, 1, 0);
INSERT INTO `yx_system_group_data` VALUES (218, 'yshop_my_menus', '{\"imageArr\":[\"https://image.dayouqiantu.cn/5dfd7b748e053.png\"],\"uniapp_url\":\"/pages/activity/BargainRecord/index\",\"name\":\"砍价记录\",\"id\":218,\"pic\":\"https://image.dayouqiantu.cn/5dfd7b748e053.png\",\"sort\":1,\"url\":\"/activity/bargain/record\",\"wxapp_url\":\"/pages/activity/BargainRecord/main\",\"status\":1}', '2020-06-25 18:54:35', NULL, 1, 1, 0);
INSERT INTO `yx_system_group_data` VALUES (219, 'yshop_home_banner', '{\"name\":\"222\",\"id\":\"\",\"pic\":\"https://image.dayouqiantu.cn/5c9f117f624ee.jpg\",\"sort\":\"\",\"url\":\"/\",\"status\":\"\"}', '2020-06-25 18:54:35', NULL, 0, 1, 0);
INSERT INTO `yx_system_group_data` VALUES (221, 'yshop_my_menus', '{\"imageArr\":[\"https://image.dayouqiantu.cn/5e60da498cfdd.png\"],\"uniapp_url\":\"/pages/orderAdmin/OrderCancellation/index\",\"name\":\"订单核销\",\"id\":221,\"pic\":\"https://image.dayouqiantu.cn/5e60da498cfdd.png\",\"sort\":0,\"url\":\"/order/order_cancellation\",\"wxapp_url\":\"\",\"status\":1}', '2020-06-25 18:54:35', '2020-09-15 15:46:36', 0, 1, 0);
INSERT INTO `yx_system_group_data` VALUES (222, 'yshop_recharge_price_ways', '{\"give_price\":\"10\",\"price\":\"100\",\"id\":\"\",\"sort\":0,\"status\":1}', '2020-06-25 18:54:35', NULL, 1, 1, 0);
INSERT INTO `yx_system_group_data` VALUES (223, 'yshop_recharge_price_ways', '{\"give_price\":\"1000\",\"price\":1,\"id\":223,\"sort\":0,\"status\":1}', '2020-06-25 18:54:35', '2020-09-12 18:34:21', 0, 1, 0);
INSERT INTO `yx_system_group_data` VALUES (224, 'yshop_home_banner', '{\"imageArr\":[\"https://consoleapi.xinxintuan.co/file/pic/20200903171807647067.jpg\"],\"uniapp_url\":\"3\",\"name\":\"3\",\"id\":224,\"pic\":\"https://consoleapi.xinxintuan.co/file/pic/20200903171807647067.jpg\",\"sort\":0,\"url\":\"3\",\"wxapp_url\":\"\",\"status\":1}', '2020-09-03 17:13:59', '2020-09-15 07:54:11', 0, 1, 0);
INSERT INTO `yx_system_group_data` VALUES (225, 'yshop_my_menus', '{\"imageArr\":[\"https://consoleapi.xinxintuan.co/file/pic/20200911093912577832.png\"],\"uniapp_url\":\"/pages/shop/GoodsFoot/index\",\"name\":\"我的足迹\",\"id\":225,\"pic\":\"https://consoleapi.xinxintuan.co/file/pic/20200911093912577832.png\",\"sort\":10,\"url\":\"\",\"wxapp_url\":\"\",\"status\":1}', '2020-09-11 09:39:21', '2020-09-11 09:40:49', 10, 1, 0);
INSERT INTO `yx_system_group_data` VALUES (226, 'yshop_home_banner', '{\"imageArr\":[\"https://consoleapi.xinxintuan.co/file/pic/20200915074139926178.jpg\"],\"uniapp_url\":\"\",\"name\":\"sdaas\",\"id\":\"\",\"pic\":\"https://consoleapi.xinxintuan.co/file/pic/20200915074139926178.jpg\",\"sort\":0,\"url\":\"\",\"wxapp_url\":\"\",\"status\":1}', '2020-09-15 07:41:46', NULL, 0, 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for yx_system_store
-- ----------------------------
DROP TABLE IF EXISTS `yx_system_store`;
CREATE TABLE `yx_system_store` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
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
  `create_time` datetime NOT NULL COMMENT '添加时间',
  `update_time` datetime DEFAULT NULL,
  `is_show` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否显示',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `day_time_end` datetime DEFAULT NULL,
  `day_time_start` datetime DEFAULT NULL,
  `valid_time_end` datetime DEFAULT NULL,
  `valid_time_start` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `phone` (`phone`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='门店自提';

-- ----------------------------
-- Records of yx_system_store
-- ----------------------------
BEGIN;
INSERT INTO `yx_system_store` VALUES (3, 'yshop店铺', 'springboot商城', '15136175234', '河南省漯河市', '', 'https://image.dayouqiantu.cn/noMeal_tt.png', '33.54528', '113.9202', '2020-03-09 - 2020-04-30', '11:33:49 - 19:33:49', '2020-06-25 18:54:35', NULL, 0, 0, '2020-03-04 19:33:49', '2020-03-04 11:33:49', '2020-04-30 00:00:00', '2020-03-09 00:00:00');
INSERT INTO `yx_system_store` VALUES (4, '信阳门店', '信阳门店', '15136275234', '河南省信阳市', '', 'https://image.dayouqiantu.cn/noMeal_tt.png', '32.11683', '114.05857', '2020-03-21 - 2020-03-31', '17:11:13 - 20:11:13', '2020-06-25 18:54:35', '2021-04-04 11:47:28', 1, 0, '2020-03-21 20:11:13', '2020-03-21 17:11:13', '2020-03-31 00:00:00', '2020-03-21 00:00:00');
INSERT INTO `yx_system_store` VALUES (5, '郑州门店', '郑州门店', '15136175246', '河南省郑州', '', 'https://image.dayouqiantu.cn/noMeal_tt.png', '34.72468', '113.6401', '2020-03-21 - 2020-03-31', '17:11:13 - 20:11:15', '2020-06-25 18:54:35', '2020-06-26 10:51:21', 1, 1, '2020-03-21 20:11:15', '2020-03-21 17:11:13', '2020-03-31 00:00:00', '2020-03-21 00:00:00');
COMMIT;

-- ----------------------------
-- Table structure for yx_system_store_staff
-- ----------------------------
DROP TABLE IF EXISTS `yx_system_store_staff`;
CREATE TABLE `yx_system_store_staff` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `uid` bigint unsigned NOT NULL COMMENT '微信用户id',
  `nickname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '店员头像',
  `store_id` int NOT NULL COMMENT '门店id',
  `store_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `staff_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '店员名称',
  `phone` char(15) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机号码',
  `verify_status` tinyint NOT NULL DEFAULT '0' COMMENT '核销开关',
  `status` tinyint DEFAULT '1' COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime DEFAULT NULL,
  `is_del` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='门店店员表';

-- ----------------------------
-- Records of yx_system_store_staff
-- ----------------------------
BEGIN;
INSERT INTO `yx_system_store_staff` VALUES (2, 12, '会敲代码的喵2', 'https://image.dayouqiantu.cn/5dc2c7f3a104c.png', 4, '信阳门店', 'tttt', '15136175423', 1, 1, '2020-06-25 18:54:35', NULL, 1);
INSERT INTO `yx_system_store_staff` VALUES (3, 11, '会敲代码的喵88', 'https://image.dayouqiantu.cn/5dc2c7f3a104c.png', 5, '郑州门店', 'tttt', '16136175234', 1, 1, '2020-06-25 18:54:35', NULL, 1);
INSERT INTO `yx_system_store_staff` VALUES (4, 19, '15136175528', 'https://image.dayouqiantu.cn/5dc2c7f3a104c.png', 5, '郑州门店', '111', '15136175246', 1, 1, '2020-06-25 18:54:35', NULL, 0);
INSERT INTO `yx_system_store_staff` VALUES (5, 20, '15136171112', 'https://image.dayouqiantu.cn/5e79f6cfd33b6.png', 4, '信阳门店', '444', '44444', 1, 1, NULL, '2020-06-26 10:56:09', 0);
INSERT INTO `yx_system_store_staff` VALUES (6, 21, '15136175234', 'https://image.dayouqiantu.cn/5e79f6cfd33b6.png', 4, '信阳门店', '5555', '555555', 1, 1, '2020-06-26 10:53:46', NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for yx_system_user_level
-- ----------------------------
DROP TABLE IF EXISTS `yx_system_user_level`;
CREATE TABLE `yx_system_user_level` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '会员名称',
  `money` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '购买金额',
  `valid_date` int NOT NULL DEFAULT '0' COMMENT '有效时间',
  `is_forever` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否为永久会员',
  `is_pay` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否购买,1=购买,0=不购买',
  `is_show` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否显示 1=显示,0=隐藏',
  `grade` int NOT NULL DEFAULT '0' COMMENT '会员等级',
  `discount` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '享受折扣',
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '会员卡背景',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '会员图标',
  `explain` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '说明',
  `create_time` datetime NOT NULL COMMENT '添加时间',
  `update_time` datetime DEFAULT NULL,
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除.1=删除,0=未删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='设置用户等级表';

-- ----------------------------
-- Records of yx_system_user_level
-- ----------------------------
BEGIN;
INSERT INTO `yx_system_user_level` VALUES (1, '普通会员', 20.00, 0, 1, 0, 1, 1, 99.00, 'http://pic.dayouqiantu.cn/5c9ccca8cd632.jpg', 'http://pic.dayouqiantu.cn/5c9ccca8bc1e0.png', '普通会员', '2020-06-25 18:54:35', NULL, 1);
INSERT INTO `yx_system_user_level` VALUES (2, '青铜会员', 0.00, 0, 1, 0, 1, 2, 98.00, 'http://pic.dayouqiantu.cn/5c9ccca904016.jpg', 'http://pic.dayouqiantu.cn/5c9ccca8f0a30.png', '青铜会员', '2020-06-25 18:54:35', NULL, 0);
INSERT INTO `yx_system_user_level` VALUES (3, '黄铜会员', 0.00, 0, 1, 0, 1, 3, 95.00, 'http://pic.dayouqiantu.cn/5c9ccca8b27f1.jpg', 'http://pic.dayouqiantu.cn/5c9ccca8e9365.png', '黄铜会员', '2020-06-25 18:54:35', NULL, 0);
INSERT INTO `yx_system_user_level` VALUES (4, '白银会员', 0.00, 0, 1, 0, 1, 4, 94.00, 'http://pic.dayouqiantu.cn/5c9ccca8d6ae1.jpg', 'http://pic.dayouqiantu.cn/5c9ccca8a27f0.png', '白银会员', '2020-06-25 18:54:35', NULL, 0);
INSERT INTO `yx_system_user_level` VALUES (5, '黄金会员', 0.00, 0, 1, 0, 1, 5, 90.00, 'http://pic.dayouqiantu.cn/5c9ccca8b27f1.jpg', 'http://pic.dayouqiantu.cn/5c9ccca8aa5b9.png', '黄金会员', '2020-06-25 18:54:35', NULL, 0);
INSERT INTO `yx_system_user_level` VALUES (6, '钻石会员', 0.00, 0, 1, 0, 1, 6, 88.00, 'http://localhost:8000/file/pic/钻石-20200328094531898.jpg', 'http://pic.dayouqiantu.cn/5c9ccca90d2d3.png', '钻石会员', '2020-06-25 18:54:35', NULL, 1);
COMMIT;

-- ----------------------------
-- Table structure for yx_system_user_task
-- ----------------------------
DROP TABLE IF EXISTS `yx_system_user_task`;
CREATE TABLE `yx_system_user_task` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '任务名称',
  `real_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '配置原名',
  `task_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '任务类型',
  `number` int NOT NULL DEFAULT '0' COMMENT '限定数',
  `level_id` int NOT NULL DEFAULT '0' COMMENT '等级id',
  `sort` int NOT NULL DEFAULT '0' COMMENT '排序',
  `is_show` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否显示',
  `is_must` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否务必达成任务,1务必达成,0=满足其一',
  `illustrate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '任务说明',
  `create_time` datetime NOT NULL COMMENT '新增时间',
  `update_time` datetime DEFAULT NULL,
  `is_del` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='等级任务设置';

-- ----------------------------
-- Records of yx_system_user_task
-- ----------------------------
BEGIN;
INSERT INTO `yx_system_user_task` VALUES (1, '满足积分20分', '积分数', 'SatisfactionIntegral', 20, 1, 0, 1, 1, '', '2020-06-25 18:54:35', '2020-06-25 18:54:35', 0);
INSERT INTO `yx_system_user_task` VALUES (2, '消费满100元', '消费金额', 'ConsumptionAmount', 100, 1, 0, 1, 1, '', '2020-06-25 18:54:35', NULL, 0);
INSERT INTO `yx_system_user_task` VALUES (3, '满足积分200分', '积分数', 'SatisfactionIntegral', 200, 2, 0, 1, 1, '', '2020-06-25 18:54:35', NULL, 0);
INSERT INTO `yx_system_user_task` VALUES (4, '累计签到20天', '累计签到', 'CumulativeAttendance', 20, 2, 0, 1, 1, '', '2020-06-25 18:54:35', NULL, 0);
INSERT INTO `yx_system_user_task` VALUES (5, '满足积分500分', '积分数', 'SatisfactionIntegral', 500, 3, 0, 1, 1, '', '2020-06-25 18:54:35', NULL, 0);
INSERT INTO `yx_system_user_task` VALUES (6, '累计签到30天', '累计签到', 'CumulativeAttendance', 30, 3, 0, 1, 1, '', '2020-06-25 18:54:35', NULL, 0);
INSERT INTO `yx_system_user_task` VALUES (7, '满足积分1000分', '积分数', 'SatisfactionIntegral', 1000, 4, 0, 1, 1, '', '2020-06-25 18:54:35', NULL, 0);
INSERT INTO `yx_system_user_task` VALUES (8, '累计签到10天', '累计签到', 'CumulativeAttendance', 10, 4, 0, 1, 1, '', '2020-06-25 18:54:35', NULL, 0);
INSERT INTO `yx_system_user_task` VALUES (9, '满足积分1200分', '积分数', 'SatisfactionIntegral', 1200, 5, 0, 1, 1, '', '2020-06-25 18:54:35', NULL, 0);
INSERT INTO `yx_system_user_task` VALUES (10, '累计签到60天', '累计签到', 'CumulativeAttendance', 60, 5, 0, 1, 1, '', '2020-06-25 18:54:35', NULL, 0);
INSERT INTO `yx_system_user_task` VALUES (11, '消费满10000元', '消费次数', 'ConsumptionAmount', 10000, 5, 0, 1, 1, '', '2020-06-25 18:54:35', NULL, 0);
INSERT INTO `yx_system_user_task` VALUES (12, '满足积分2000分', '积分数', 'SatisfactionIntegral', 2000, 6, 0, 1, 1, '', '2020-06-25 18:54:35', NULL, 0);
INSERT INTO `yx_system_user_task` VALUES (13, '消费满10000元', '消费次数', 'ConsumptionAmount', 10000, 6, 0, 1, 1, '', '2020-06-25 18:54:35', NULL, 0);
INSERT INTO `yx_system_user_task` VALUES (14, '累计签到100天', '累计签到', 'CumulativeAttendance', 100, 6, 0, 1, 1, '', '2020-06-25 18:54:35', NULL, 0);
INSERT INTO `yx_system_user_task` VALUES (15, '消费满1000元', '消费金额', 'ConsumptionAmount', 1000, 4, 0, 1, 1, '', '2020-06-25 18:54:35', NULL, 0);
INSERT INTO `yx_system_user_task` VALUES (16, '累计签到2天', '累计签到', 'CumulativeAttendance', 2, 1, 0, 1, 1, '', '2020-06-25 18:54:35', NULL, 0);
INSERT INTO `yx_system_user_task` VALUES (17, '消费满100元', '消费次数', 'ConsumptionAmount', 100, 2, 0, 1, 1, '', '2020-06-25 18:54:35', NULL, 0);
INSERT INTO `yx_system_user_task` VALUES (18, '消费满1000元', '消费金额', 'ConsumptionAmount', 1000, 3, 0, 1, 1, '', '2020-06-25 18:54:35', NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for yx_user
-- ----------------------------
DROP TABLE IF EXISTS `yx_user`;
CREATE TABLE `yx_user` (
  `uid` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户账户(跟accout一样)',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户密码（跟pwd）',
  `real_name` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '真实姓名',
  `birthday` int DEFAULT '0' COMMENT '生日',
  `card_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '身份证号码',
  `mark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '用户备注',
  `nickname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户昵称',
  `avatar` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户头像',
  `phone` char(15) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机号码',
  `add_ip` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '添加ip',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime DEFAULT NULL COMMENT '最后一次登录时间',
  `last_ip` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '最后一次登录ip',
  `now_money` decimal(8,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '用户余额',
  `brokerage_price` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '佣金金额',
  `integral` decimal(8,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '用户剩余积分',
  `sign_num` int NOT NULL DEFAULT '0' COMMENT '连续签到天数',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1为正常，0为禁止',
  `level` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '等级',
  `spread_uid` bigint unsigned DEFAULT '0' COMMENT '推广元id',
  `spread_time` datetime DEFAULT NULL COMMENT '推广员关联时间',
  `user_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户类型',
  `is_promoter` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否为推广员',
  `pay_count` int unsigned DEFAULT '0' COMMENT '用户购买次数',
  `spread_count` int DEFAULT '0' COMMENT '下级人数',
  `addres` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '详细地址',
  `adminid` int unsigned DEFAULT '0' COMMENT '管理员编号 ',
  `login_type` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户登陆类型，h5,wechat,routine',
  `wx_profile` json DEFAULT NULL COMMENT '微信用户json信息',
  `is_del` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`uid`) USING BTREE,
  UNIQUE KEY `username` (`username`) USING BTREE,
  KEY `spreaduid` (`spread_uid`) USING BTREE,
  KEY `level` (`level`) USING BTREE,
  KEY `status` (`status`) USING BTREE,
  KEY `is_promoter` (`is_promoter`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of yx_user
-- ----------------------------
BEGIN;
INSERT INTO `yx_user` VALUES (1, '222', '222', '222', 20, '2', '2', '2', '2', '2', '', NULL, '2021-04-04 15:19:28', '', 0.00, 0.00, 0.00, 0, 1, 0, 0, NULL, '2', 0, 0, 0, '', 0, '', NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for yx_user_address
-- ----------------------------
DROP TABLE IF EXISTS `yx_user_address`;
CREATE TABLE `yx_user_address` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '用户地址id',
  `uid` bigint unsigned NOT NULL COMMENT '用户id',
  `real_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '收货人姓名',
  `phone` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '收货人电话',
  `province` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '收货人所在省',
  `city` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '收货人所在市',
  `city_id` int DEFAULT NULL,
  `district` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '收货人所在区',
  `detail` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '收货人详细地址',
  `post_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '邮编',
  `longitude` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '经度',
  `latitude` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '纬度',
  `is_default` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否默认',
  `is_del` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否删除',
  `create_time` datetime NOT NULL COMMENT '添加时间',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `uid` (`uid`) USING BTREE,
  KEY `is_default` (`is_default`) USING BTREE,
  KEY `is_del` (`is_del`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户地址表';

-- ----------------------------
-- Records of yx_user_address
-- ----------------------------
BEGIN;
INSERT INTO `yx_user_address` VALUES (24, 40, '陶子', '13666666666', '北京市', '北京市', 2, '朝阳区', '哈哈哈家', '', '0', '0', 0, 0, '2020-09-12 14:54:31', NULL);
INSERT INTO `yx_user_address` VALUES (25, 43, '张先生', '18888888888', '天津市', '天津市', 7363, '和平区', '6666', '', '0', '0', 0, 0, '2020-09-12 17:04:50', '2020-09-13 02:29:33');
INSERT INTO `yx_user_address` VALUES (26, 53, 'hahsdhsd', '15601064107', '北京市', '北京市', 2, '东城区', 'wqwewe', '', '0', '0', 0, 0, '2020-09-13 02:29:33', '2020-09-13 11:01:30');
INSERT INTO `yx_user_address` VALUES (27, 44, '胡先生', '18888888888', '北京市', '北京市', 2, '东城区', '999', '', '0', '0', 0, 0, '2020-09-13 10:17:00', '2020-09-13 11:01:30');
INSERT INTO `yx_user_address` VALUES (28, 53, '12', '15601064107', '山西省', '长治市', 72975, '长治县', '21231', '', '0', '0', 0, 0, '2020-09-13 11:01:30', '2020-09-13 21:33:36');
INSERT INTO `yx_user_address` VALUES (29, 42, '阿萨德', '18888888888', '天津市', '天津市', 7363, '河东区', '阿萨打算打', '', '0', '0', 0, 0, '2020-09-13 21:33:36', '2020-09-13 22:04:49');
INSERT INTO `yx_user_address` VALUES (30, 50, 'Hhhh', '18888888888', '河北省', '邯郸市', 26779, '复兴区', 'Hhhh', '', '0', '0', 0, 0, '2020-09-13 22:04:49', '2020-09-13 22:05:05');
INSERT INTO `yx_user_address` VALUES (31, 54, '胡新生', '15136175234', '北京市', '北京市', 2, '东城区', '999', '', '0', '0', 0, 0, '2020-09-13 22:05:05', '2020-09-14 00:00:20');
INSERT INTO `yx_user_address` VALUES (32, 49, 'Hhhh', '18855555555', '天津市', '天津市', 7363, '河东区', 'Yyyy', '', '0', '0', 0, 0, '2020-09-14 00:00:20', '2020-09-14 00:03:47');
INSERT INTO `yx_user_address` VALUES (33, 59, '胡先生', '15136175245', '天津市', '天津市', 7363, '和平区', '666', '', '0', '0', 0, 0, '2020-09-14 00:03:47', '2020-09-14 10:02:22');
INSERT INTO `yx_user_address` VALUES (34, 61, '胡先生', '15136175246', '北京市', '北京市', 2, '东城区', '666', '', '0', '0', 0, 0, '2020-09-14 10:02:22', '2020-09-14 12:53:03');
INSERT INTO `yx_user_address` VALUES (35, 71, '666', '18888888888', '天津市', '天津市', 7363, '河东区', '摸摸', '', '0', '0', 1, 0, '2020-09-14 12:53:03', NULL);
COMMIT;

-- ----------------------------
-- Table structure for yx_user_bill
-- ----------------------------
DROP TABLE IF EXISTS `yx_user_bill`;
CREATE TABLE `yx_user_bill` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '用户账单id',
  `uid` bigint unsigned NOT NULL DEFAULT '0' COMMENT '用户uid',
  `link_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '关联id',
  `pm` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '0 = 支出 1 = 获得',
  `title` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '账单标题',
  `category` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '明细种类',
  `type` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '明细类型',
  `number` decimal(8,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '明细数字',
  `balance` decimal(8,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '剩余',
  `mark` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '备注',
  `create_time` datetime NOT NULL COMMENT '添加时间',
  `update_time` datetime DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '0 = 带确定 1 = 有效 -1 = 无效',
  `is_del` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `openid` (`uid`) USING BTREE,
  KEY `status` (`status`) USING BTREE,
  KEY `add_time` (`create_time`) USING BTREE,
  KEY `pm` (`pm`) USING BTREE,
  KEY `type` (`category`,`type`,`link_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户账单表';

-- ----------------------------
-- Records of yx_user_bill
-- ----------------------------
BEGIN;
INSERT INTO `yx_user_bill` VALUES (1, 11, '1', 0, '购买商品', 'now_money', 'pay_product', 119.80, 7381.31, '余额支付', '2020-06-25 18:54:35', NULL, 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for yx_user_extract
-- ----------------------------
DROP TABLE IF EXISTS `yx_user_extract`;
CREATE TABLE `yx_user_extract` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `uid` bigint unsigned DEFAULT NULL,
  `real_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
  `extract_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'bank' COMMENT 'bank = 银行卡 alipay = 支付宝wx=微信',
  `bank_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '银行卡',
  `bank_address` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '开户地址',
  `alipay_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '支付宝账号',
  `extract_price` decimal(8,2) unsigned DEFAULT '0.00' COMMENT '提现金额',
  `mark` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `balance` decimal(8,2) unsigned DEFAULT '0.00',
  `fail_msg` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '无效原因',
  `fail_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime DEFAULT NULL,
  `status` tinyint DEFAULT '0' COMMENT '-1 未通过 0 审核中 1 已提现',
  `wechat` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '微信号',
  `is_del` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `extract_type` (`extract_type`) USING BTREE,
  KEY `status` (`status`) USING BTREE,
  KEY `add_time` (`create_time`) USING BTREE,
  KEY `openid` (`uid`) USING BTREE,
  KEY `fail_time` (`fail_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户提现表';

-- ----------------------------
-- Records of yx_user_extract
-- ----------------------------
BEGIN;
INSERT INTO `yx_user_extract` VALUES (1, 11, '会敲代码的喵', 'weixin', '0', '', '', 1.00, NULL, 5.00, '失败了', '2020-09-13 12:25:52', '2020-06-25 18:54:35', '2020-09-13 12:25:52', -1, 'hu', 0);
COMMIT;

-- ----------------------------
-- Table structure for yx_user_group
-- ----------------------------
DROP TABLE IF EXISTS `yx_user_group`;
CREATE TABLE `yx_user_group` (
  `id` smallint unsigned NOT NULL AUTO_INCREMENT,
  `group_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户分组名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户分组表';

-- ----------------------------
-- Records of yx_user_group
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for yx_user_level
-- ----------------------------
DROP TABLE IF EXISTS `yx_user_level`;
CREATE TABLE `yx_user_level` (
  `id` int NOT NULL AUTO_INCREMENT,
  `uid` bigint NOT NULL DEFAULT '0' COMMENT '用户uid',
  `level_id` int NOT NULL DEFAULT '0' COMMENT '等级vip',
  `grade` int NOT NULL DEFAULT '0' COMMENT '会员等级',
  `valid_time` int NOT NULL DEFAULT '0' COMMENT '过期时间',
  `is_forever` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否永久',
  `mer_id` int NOT NULL DEFAULT '0' COMMENT '商户id',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0:禁止,1:正常',
  `mark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '备注',
  `remind` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已通知',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除,0=未删除,1=删除',
  `create_time` datetime NOT NULL COMMENT '添加时间',
  `update_time` datetime DEFAULT NULL,
  `discount` int NOT NULL DEFAULT '0' COMMENT '享受折扣',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `id` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户等级记录表';

-- ----------------------------
-- Records of yx_user_level
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for yx_user_recharge
-- ----------------------------
DROP TABLE IF EXISTS `yx_user_recharge`;
CREATE TABLE `yx_user_recharge` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `uid` bigint DEFAULT NULL COMMENT '充值用户UID',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '',
  `order_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '订单号',
  `price` decimal(8,2) DEFAULT NULL COMMENT '充值金额',
  `give_price` decimal(8,2) DEFAULT '0.00' COMMENT '购买赠送金额',
  `recharge_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '充值类型',
  `paid` tinyint(1) DEFAULT NULL COMMENT '是否充值',
  `pay_time` datetime DEFAULT NULL COMMENT '充值支付时间',
  `create_time` datetime DEFAULT NULL COMMENT '充值时间',
  `update_time` datetime DEFAULT NULL,
  `refund_price` decimal(10,2) DEFAULT '0.00' COMMENT '退款金额',
  `is_del` tinyint DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `order_id` (`order_id`) USING BTREE,
  KEY `uid` (`uid`) USING BTREE,
  KEY `recharge_type` (`recharge_type`) USING BTREE,
  KEY `paid` (`paid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户充值表';

-- ----------------------------
-- Records of yx_user_recharge
-- ----------------------------
BEGIN;
INSERT INTO `yx_user_recharge` VALUES (1, 11, 'hupeng', '1234428298159718400', 200.00, 0.00, 'weixin', 0, NULL, '2020-06-25 18:54:35', NULL, 0.00, 0);
COMMIT;

-- ----------------------------
-- Table structure for yx_user_sign
-- ----------------------------
DROP TABLE IF EXISTS `yx_user_sign`;
CREATE TABLE `yx_user_sign` (
  `int` bigint NOT NULL AUTO_INCREMENT,
  `uid` bigint NOT NULL DEFAULT '0' COMMENT '用户uid',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '签到说明',
  `number` int NOT NULL DEFAULT '0' COMMENT '获得积分',
  `balance` int NOT NULL DEFAULT '0' COMMENT '剩余积分',
  `create_time` datetime NOT NULL COMMENT '添加时间',
  `update_time` datetime DEFAULT NULL,
  `is_del` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`int`) USING BTREE,
  KEY `uid` (`uid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='签到记录表';

-- ----------------------------
-- Records of yx_user_sign
-- ----------------------------
BEGIN;
INSERT INTO `yx_user_sign` VALUES (17, 43, '签到奖励', 10, 10, '2020-09-12 17:41:55', NULL, 0);
INSERT INTO `yx_user_sign` VALUES (18, 44, '签到奖励', 10, 0, '2020-09-13 22:21:57', NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for yx_wechat_menu
-- ----------------------------
DROP TABLE IF EXISTS `yx_wechat_menu`;
CREATE TABLE `yx_wechat_menu` (
  `key` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `result` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '缓存数据',
  `add_time` int DEFAULT NULL COMMENT '缓存时间',
  PRIMARY KEY (`key`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='微信缓存表';

-- ----------------------------
-- Records of yx_wechat_menu
-- ----------------------------
BEGIN;
INSERT INTO `yx_wechat_menu` VALUES ('wechat_menus', '[{\"subButtons\":[{\"name\":\"公众号演示\",\"type\":\"view\",\"url\":\"https://h5.dayouqiantu.cn\"}],\"name\":\"Yshop商城3\",\"type\":\"click\",\"key\":\"yshop\"},{\"subButtons\":[{\"appId\":\"wxa82b5b7fcb0ec161\",\"name\":\"小程序演示\",\"pagePath\":\"pages/index\",\"type\":\"miniprogram\",\"url\":\"pages/index\"}],\"name\":\"供货商城\",\"type\":\"click\",\"key\":\"supply\"},{\"subButtons\":[],\"name\":\"3333\",\"type\":\"click\",\"key\":\"2222\"}]', 1570435277);
COMMIT;

-- ----------------------------
-- Table structure for yx_wechat_reply
-- ----------------------------
DROP TABLE IF EXISTS `yx_wechat_reply`;
CREATE TABLE `yx_wechat_reply` (
  `id` mediumint unsigned NOT NULL AUTO_INCREMENT COMMENT '微信关键字回复id',
  `key` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '关键字',
  `type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '回复类型',
  `data` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '回复数据',
  `status` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '0=不可用  1 =可用',
  `hide` tinyint unsigned DEFAULT '0' COMMENT '是否隐藏',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `key` (`key`) USING BTREE,
  KEY `type` (`type`) USING BTREE,
  KEY `status` (`status`) USING BTREE,
  KEY `hide` (`hide`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='微信关键字回复表';

-- ----------------------------
-- Records of yx_wechat_reply
-- ----------------------------
BEGIN;
INSERT INTO `yx_wechat_reply` VALUES (1, 'subscribe', 'text', '{\"content\":\"22222222222444499990000\"}', 1, NULL);
COMMIT;

-- ----------------------------
-- Table structure for yx_wechat_template
-- ----------------------------
DROP TABLE IF EXISTS `yx_wechat_template`;
CREATE TABLE `yx_wechat_template` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '模板id',
  `tempkey` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '模板编号',
  `name` char(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '模板名',
  `content` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '回复内容',
  `tempid` char(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '模板ID',
  `create_time` datetime NOT NULL COMMENT '添加时间',
  `update_time` datetime DEFAULT NULL,
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '状态',
  `is_del` tinyint(1) DEFAULT '0',
  `type` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '类型：template:模板消息 subscribe:订阅消息',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `tempkey` (`tempkey`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='微信模板';

-- ----------------------------
-- Records of yx_wechat_template
-- ----------------------------
BEGIN;
INSERT INTO `yx_wechat_template` VALUES (3, 'delivery_success', '订单发货提醒', '{{first.DATA}}\n订单编号：{{keyword1.DATA}}\n物流公司：{{keyword2.DATA}}\n物流单号：{{keyword3.DATA}}\n{{remark.DATA}}', 'NyrkeQ5TDFDq0GV0wkNA9L39GzPHfzbQqVLnbA5OTsY', '2020-06-25 18:54:35', '2020-07-06 15:52:09', 1, 1, NULL);
INSERT INTO `yx_wechat_template` VALUES (13, 'pay_success', '订单支付成功通知', '{{first.DATA}}\n订单编号：{{keyword1.DATA}}\n支付金额：{{keyword2.DATA}}\n{{remark.DATA}}', 'W5r2c2kzhbq8uxStkPAVx_sk-5aapMFCqe7b7KU5jXI', '2020-06-25 18:54:35', '2020-09-14 12:51:34', 1, 0, 'subscribe');
INSERT INTO `yx_wechat_template` VALUES (14, 'recharge_success', '帐户资金变动提醒', '{{first.DATA}}\n变动类型：{{keyword1.DATA}}\n变动时间：{{keyword2.DATA}}\n变动金额：{{keyword3.DATA}}\n{{remark.DATA}}', 'ePF4RS3ONCEuS9AuPyqZ2Th_B-HZ6E1CIpnJRt7ACwI', '2020-06-25 18:54:35', '2020-07-06 15:51:54', 1, 0, NULL);
INSERT INTO `yx_wechat_template` VALUES (15, 'refund_success', '退款进度通知', '', 'jaDVkOdbbk01WcWSxp1_liEQen44-euhj7shxjDvLIc', '2020-07-06 15:53:10', NULL, 0, 0, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
