/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : yshop

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 27/05/2020 20:55:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
) ENGINE = InnoDB AUTO_INCREMENT = 226 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '组合数据详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yx_system_group_data
-- ----------------------------
INSERT INTO `yx_system_group_data` VALUES (177, 'yshop_home_banner', '{\"imageArr\":[\"https://image.dayouqiantu.cn/5c9f05aee5059.jpg\"],\"name\":\"banner2\",\"id\":177,\"pic\":\"https://image.dayouqiantu.cn/5c9f05aee5059.jpg\",\"sort\":1,\"url\":\"wwww\",\"status\":1}', 1571387677, 1, 1);
INSERT INTO `yx_system_group_data` VALUES (180, 'yshop_home_menus', '{\"imageArr\":[\"https://image.dayouqiantu.cn/5e85bfa61251d.png\"],\"uniapp_url\":\"/pages/shop/GoodsList/index\",\"name\":\"全部商品\",\"id\":180,\"pic\":\"https://image.dayouqiantu.cn/5e85bfa61251d.png\",\"sort\":9,\"url\":\"/goods_list\",\"wxapp_url\":\"/pages/shop/GoodsList/main\",\"status\":1}', 1571390842, 9, 1);
INSERT INTO `yx_system_group_data` VALUES (182, 'yshop_home_roll_news', '{\"uniapp_url\":\"/pages/shop/news/NewsList/index\",\"id\":182,\"pic\":\"https://i.loli.net/2019/10/18/DqOUgNf7wjuFpPT.png\",\"sort\":2,\"title\":\"分销、拼团、商户功能上线啦！\",\"url\":\"/news_list\",\"info\":\"yshop2.0上线啦\",\"wxapp_url\":\"/pages/shop/news/NewsList/main\",\"status\":1}', 0, 2, 1);
INSERT INTO `yx_system_group_data` VALUES (183, 'yshop_hot_search', '{\"id\":183,\"title\":\"照片\"}', 0, 0, 1);
INSERT INTO `yx_system_group_data` VALUES (184, 'yshop_hot_search', '{\"id\":184,\"title\":\"springboot\"}', 0, 0, 1);
INSERT INTO `yx_system_group_data` VALUES (187, 'yshop_home_roll_news', '{\"uniapp_url\":\"/pages/shop/news/NewsList/index\",\"id\":187,\"sort\":1,\"url\":\"/news_list\",\"info\":\"springboot2+JPA+Mybatisplus商城系统\",\"wxapp_url\":\"/pages/shop/news/NewsList/main\",\"status\":1}', 1572086163, 1, 1);
INSERT INTO `yx_system_group_data` VALUES (188, 'yshop_hot_search', '{\"id\":188,\"title\":\"打印\"}', 1572086172, 0, 1);
INSERT INTO `yx_system_group_data` VALUES (189, 'yshop_my_menus', '{\"imageArr\":[\"https://image.dayouqiantu.cn/5dec896eeb25a.png\"],\"uniapp_url\":\"/pages/user/UserVip/index\",\"name\":\"会员中心\",\"id\":189,\"pic\":\"https://image.dayouqiantu.cn/5dec896eeb25a.png\",\"sort\":9,\"url\":\"/user/vip\",\"wxapp_url\":\"/pages/user/UserVip/main\",\"status\":1}', 1572087722, 9, 1);
INSERT INTO `yx_system_group_data` VALUES (190, 'yshop_my_menus', '{\"imageArr\":[\"https://image.dayouqiantu.cn/5db428984d64d.png\"],\"uniapp_url\":\"/pages/user/coupon/UserCoupon/index\",\"name\":\"我的卡券\",\"id\":190,\"pic\":\"https://image.dayouqiantu.cn/5db428984d64d.png\",\"sort\":8,\"url\":\"/user/get_coupon\",\"wxapp_url\":\"/pages/user/coupon/UserCoupon/main\",\"status\":1}', 0, 8, 1);
INSERT INTO `yx_system_group_data` VALUES (191, 'yshop_my_menus', '{\"imageArr\":[\"https://image.dayouqiantu.cn/5db428a8d3ab0.png\"],\"uniapp_url\":\"/pages/shop/GoodsCollection/index\",\"name\":\"收藏商品\",\"id\":191,\"pic\":\"https://image.dayouqiantu.cn/5db428a8d3ab0.png\",\"sort\":7,\"url\":\"/collection\",\"wxapp_url\":\"/pages/shop/GoodsCollection/main\",\"status\":1}', 0, 7, 1);
INSERT INTO `yx_system_group_data` VALUES (192, 'yshop_my_menus', '{\"imageArr\":[\"https://image.dayouqiantu.cn/5db428bd61b73.png\"],\"uniapp_url\":\"/pages/user/address/AddressManagement/index\",\"name\":\"地址管理\",\"id\":192,\"pic\":\"https://image.dayouqiantu.cn/5db428bd61b73.png\",\"sort\":6,\"url\":\"/user/add_manage\",\"wxapp_url\":\"/pages/user/address/AddressManagement/main\",\"status\":1}', 0, 6, 1);
INSERT INTO `yx_system_group_data` VALUES (193, 'yshop_my_menus', '{\"imageArr\":[\"https://image.dayouqiantu.cn/5db428e28dd48.png\"],\"uniapp_url\":\"/pages/user/promotion/UserPromotion/index\",\"name\":\"我的推广\",\"id\":193,\"pic\":\"https://image.dayouqiantu.cn/5db428e28dd48.png\",\"sort\":5,\"url\":\"/user/user_promotion\",\"wxapp_url\":\"/pages/user/promotion/UserPromotion/main\",\"status\":1}', 0, 5, 1);
INSERT INTO `yx_system_group_data` VALUES (194, 'yshop_my_menus', '{\"imageArr\":[\"https://image.dayouqiantu.cn/5db42a4208c55.png\"],\"uniapp_url\":\"/pages/user/UserAccount/index\",\"name\":\"我的余额\",\"id\":194,\"pic\":\"https://image.dayouqiantu.cn/5db42a4208c55.png\",\"sort\":4,\"url\":\"/user/account\",\"wxapp_url\":\"/pages/user/UserAccount/main\",\"status\":1}', 0, 4, 1);
INSERT INTO `yx_system_group_data` VALUES (195, 'yshop_my_menus', '{\"imageArr\":[\"https://image.dayouqiantu.cn/5db428f410462.png\"],\"uniapp_url\":\"/pages/user/signIn/Integral/index\",\"name\":\"我的积分\",\"id\":195,\"pic\":\"https://image.dayouqiantu.cn/5db428f410462.png\",\"sort\":3,\"url\":\"/user/integral\",\"wxapp_url\":\"/pages/user/signIn/Integral/main\",\"status\":1}', 0, 3, 1);
INSERT INTO `yx_system_group_data` VALUES (196, 'yshop_home_menus', '{\"imageArr\":[\"https://image.dayouqiantu.cn/5e4e93f004713.png\"],\"uniapp_url\":\"/pages/shop/news/NewsList/index\",\"name\":\"图文资讯\",\"id\":196,\"pic\":\"https://image.dayouqiantu.cn/5e85bfea151b7.png\",\"sort\":8,\"url\":\"/news_list\",\"wxapp_url\":\"/pages/shop/news/NewsList/main\",\"status\":1}', 1573109648, 8, 1);
INSERT INTO `yx_system_group_data` VALUES (197, 'yshop_home_menus', '{\"imageArr\":[\"https://image.dayouqiantu.cn/5e4e939507b5e.png\"],\"uniapp_url\":\"/pages/shop/GoodsCollection/index\",\"name\":\"我的收藏\",\"id\":197,\"pic\":\"https://image.dayouqiantu.cn/5e85bfa55f352.png\",\"sort\":7,\"url\":\"/collection\",\"wxapp_url\":\"/pages/shop/GoodsCollection/main\",\"status\":1}', 1573109723, 7, 1);
INSERT INTO `yx_system_group_data` VALUES (199, 'yshop_home_menus', '{\"imageArr\":[\"https://image.dayouqiantu.cn/5e4e93c9a8304.png\"],\"uniapp_url\":\"/pages/user/coupon/GetCoupon/index\",\"name\":\"我的卡券\",\"id\":199,\"pic\":\"https://image.dayouqiantu.cn/5e85bfa6621ca.png\",\"sort\":6,\"url\":\"/user/get_coupon\",\"wxapp_url\":\"/pages/user/coupon/GetCoupon/main\",\"status\":1}', 1573387422, 6, 1);
INSERT INTO `yx_system_group_data` VALUES (200, 'yshop_home_menus', '{\"imageArr\":[\"https://image.dayouqiantu.cn/5e4e925140b57.png\"],\"uniapp_url\":\"/pages/activity/GoodsGroup/index\",\"name\":\"拼团专区\",\"id\":200,\"pic\":\"https://image.dayouqiantu.cn/5e85bfa4b273f.png\",\"sort\":5,\"url\":\"/activity/group\",\"wxapp_url\":\"/pages/activity/GoodsGroup/main\",\"status\":1}', 0, 5, 1);
INSERT INTO `yx_system_group_data` VALUES (201, 'yshop_my_menus', '{\"imageArr\":[\"https://image.dayouqiantu.cn/5ddb7a37d58d9.png\"],\"uniapp_url\":\"/pages/orderAdmin/OrderIndex/index\",\"name\":\"商户管理\",\"id\":201,\"pic\":\"https://image.dayouqiantu.cn/5ddb7a37d58d9.png\",\"sort\":2,\"url\":\"/customer/index\",\"wxapp_url\":\"/pages/orderAdmin/OrderIndex/main\",\"status\":1}', 0, 2, 1);
INSERT INTO `yx_system_group_data` VALUES (202, 'yshop_sign_day_num', '{\"sign_num\":\"10\",\"id\":205,\"day\":\"第一天\"}', 0, 9, 1);
INSERT INTO `yx_system_group_data` VALUES (203, 'yshop_sign_day_num', '{\"sign_num\":\"20\",\"id\":\"\",\"day\":\"第二天\"}', 0, 8, 1);
INSERT INTO `yx_system_group_data` VALUES (204, 'yshop_sign_day_num', '{\"sign_num\":\"30\",\"id\":\"\",\"day\":\"第三天\"}', 0, 7, 1);
INSERT INTO `yx_system_group_data` VALUES (205, 'yshop_sign_day_num', '{\"addTime\":\"\",\"sign_num\":\"40\",\"id\":\"\",\"sort\":\"\",\"value\":\"\",\"day\":\"第四天\",\"status\":\"\"}', 0, 6, 1);
INSERT INTO `yx_system_group_data` VALUES (206, 'yshop_sign_day_num', '{\"addTime\":\"\",\"sign_num\":\"50\",\"id\":\"\",\"sort\":\"\",\"value\":\"\",\"day\":\"第五天\",\"status\":\"\"}', 0, 5, 1);
INSERT INTO `yx_system_group_data` VALUES (207, 'yshop_sign_day_num', '{\"addTime\":\"\",\"sign_num\":\"60\",\"id\":\"\",\"sort\":\"\",\"value\":\"\",\"day\":\"第六天\",\"status\":\"\"}', 0, 4, 1);
INSERT INTO `yx_system_group_data` VALUES (208, 'yshop_sign_day_num', '{\"addTime\":\"\",\"sign_num\":\"100\",\"id\":\"\",\"sort\":\"\",\"value\":\"\",\"day\":\"奖励\",\"status\":\"\"}', 0, 3, 1);
INSERT INTO `yx_system_group_data` VALUES (209, 'yshop_home_menus', '{\"imageArr\":[\"https://image.dayouqiantu.cn/5e4e93010a248.png\"],\"uniapp_url\":\"/pages/user/signIn/Sign/index\",\"name\":\"积分签到\",\"id\":209,\"pic\":\"https://image.dayouqiantu.cn/5e85bf8e494f2.png\",\"sort\":4,\"url\":\"/user/sign\",\"wxapp_url\":\"/pages/user/signIn/Sign/main\",\"status\":1}', 0, 4, 1);
INSERT INTO `yx_system_group_data` VALUES (210, 'yshop_seckill_time', '{\"continued\":2,\"id\":\"\",\"time\":5}', 0, 0, 1);
INSERT INTO `yx_system_group_data` VALUES (211, 'yshop_seckill_time', '{\"addTime\":\"\",\"continued\":\"3\",\"id\":\"\",\"sort\":\"\",\"time\":\"7\",\"value\":\"\",\"status\":\"\"}', 0, 0, 1);
INSERT INTO `yx_system_group_data` VALUES (212, 'yshop_seckill_time', '{\"addTime\":\"\",\"continued\":\"2\",\"id\":\"\",\"sort\":\"\",\"time\":\"10\",\"value\":\"\",\"status\":\"\"}', 0, 0, 1);
INSERT INTO `yx_system_group_data` VALUES (213, 'yshop_seckill_time', '{\"addTime\":\"\",\"continued\":\"3\",\"id\":\"\",\"sort\":\"\",\"time\":\"12\",\"value\":\"\",\"status\":\"\"}', 0, 0, 1);
INSERT INTO `yx_system_group_data` VALUES (214, 'yshop_seckill_time', '{\"addTime\":\"\",\"continued\":\"4\",\"id\":\"\",\"sort\":\"\",\"time\":\"15\",\"value\":\"\",\"status\":\"\"}', 0, 0, 1);
INSERT INTO `yx_system_group_data` VALUES (215, 'yshop_seckill_time', '{\"continued\":\"2\",\"id\":223,\"time\":\"19\"}', 0, 0, 1);
INSERT INTO `yx_system_group_data` VALUES (216, 'yshop_home_menus', '{\"imageArr\":[\"https://image.dayouqiantu.cn/5e4e929f9be59.png\"],\"uniapp_url\":\"/pages/activity/GoodsSeckill/index\",\"name\":\"秒杀专区\",\"id\":216,\"pic\":\"https://image.dayouqiantu.cn/5e85bfa5a9f85.png\",\"sort\":3,\"url\":\"/activity/goods_seckill\",\"wxapp_url\":\"/pages/activity/GoodsSeckill/main\",\"status\":1}', 0, 3, 1);
INSERT INTO `yx_system_group_data` VALUES (217, 'yshop_home_menus', '{\"imageArr\":[\"https://image.dayouqiantu.cn/5e4e9276c608f.png\"],\"uniapp_url\":\"/pages/activity/GoodsBargain/index\",\"name\":\"砍价专区\",\"id\":217,\"pic\":\"https://image.dayouqiantu.cn/5e85bfe9b1da8.png\",\"sort\":2,\"url\":\"/activity/bargain\",\"wxapp_url\":\"/pages/activity/GoodsBargain/main\",\"status\":1}', 0, 2, 1);
INSERT INTO `yx_system_group_data` VALUES (218, 'yshop_my_menus', '{\"imageArr\":[\"https://image.dayouqiantu.cn/5dfd7b748e053.png\"],\"uniapp_url\":\"/pages/activity/BargainRecord/index\",\"name\":\"砍价记录\",\"id\":218,\"pic\":\"https://image.dayouqiantu.cn/5dfd7b748e053.png\",\"sort\":1,\"url\":\"/activity/bargain/record\",\"wxapp_url\":\"/pages/activity/BargainRecord/main\",\"status\":1}', 0, 1, 1);
INSERT INTO `yx_system_group_data` VALUES (219, 'yshop_home_banner', '{\"name\":\"222\",\"id\":\"\",\"pic\":\"https://image.dayouqiantu.cn/5c9f117f624ee.jpg\",\"sort\":\"\",\"url\":\"/\",\"status\":\"\"}', 0, 0, 1);
INSERT INTO `yx_system_group_data` VALUES (221, 'yshop_my_menus', '{\"imageArr\":[\"https://image.dayouqiantu.cn/5e60da498cfdd.png\"],\"name\":\"订单核销\",\"id\":230,\"pic\":\"https://image.dayouqiantu.cn/5e60da498cfdd.png\",\"sort\":0,\"url\":\"/order/order_cancellation\",\"wxapp_url\":\"\",\"status\":1}', 0, 0, 1);
INSERT INTO `yx_system_group_data` VALUES (222, 'yshop_recharge_price_ways', '{\"give_price\":\"10\",\"price\":\"100\",\"id\":\"\",\"sort\":0,\"status\":1}', 0, 1, 1);
INSERT INTO `yx_system_group_data` VALUES (223, 'yshop_recharge_price_ways', '{\"give_price\":\"1\",\"price\":1,\"id\":\"\",\"sort\":0,\"status\":1}', 0, 0, 1);
INSERT INTO `yx_system_group_data` VALUES (224, 'yshop_home_banner', '{\"imageArr\":[\"https://image.dayouqiantu.cn/微信截图_20200505091325 - 副本 (2).png\"],\"uniapp_url\":\"11\",\"name\":\"11\",\"id\":\"\",\"pic\":\"https://image.dayouqiantu.cn/微信截图_20200505091325 - 副本 (2).png\",\"sort\":0,\"url\":\"11\",\"wxapp_url\":\"\",\"status\":1}', 1590571271, 0, 1);

SET FOREIGN_KEY_CHECKS = 1;
