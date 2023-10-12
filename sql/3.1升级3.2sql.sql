

-- ----------------------------
-- 字段修改
-- ----------------------------
ALTER TABLE yx_store_product ADD COLUMN is_integral  tinyint(1)   ZEROFILL NULL DEFAULT 0 COMMENT '是开启积分兑换' AFTER is_del;

ALTER TABLE yx_store_product ADD COLUMN integral  int(11) NULL DEFAULT 0 COMMENT '需要多少积分兑换 只在开启积分兑换时生效' AFTER is_integral;

ALTER TABLE yx_store_product_attr_value ADD COLUMN integral INT(10)  DEFAULT 0 COMMENT '需要多少积分兑换' AFTER seckill_price;

ALTER TABLE  yx_store_order ADD COLUMN `pay_integral` decimal(8, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '实际支付积分';

ALTER TABLE yx_user modify column add_ip varchar(100) NULL DEFAULT '' COMMENT '添加ip';

ALTER TABLE yx_user modify column last_ip varchar(100) NULL DEFAULT '' COMMENT '最后一次登录ip';

ALTER TABLE yx_store_visit modify column id bigint(20) NOT NULL;

ALTER TABLE yx_store_visit modify column product_id bigint(20) NULL DEFAULT NULL COMMENT '产品ID';

ALTER TABLE yx_store_visit modify column uid bigint(20) NULL DEFAULT NULL COMMENT '用户ID';

ALTER TABLE yx_store_order_cart_info ADD COLUMN order_id  varchar(32) NULL DEFAULT NULL COMMENT '订单号' AFTER oid;

INSERT INTO `menu` VALUES (264, b'0', '终端装修', NULL, 0, 0, 'theme', 'theme', b'0', b'0', '--', '2021-02-25 19:33:17', '', 1, '2021-02-25 19:33:32', 0);
INSERT INTO `menu` VALUES (265, b'1', '商城装修', NULL, 264, 999, 'theme', 'https://demo2.yixiang.co/container', b'0', b'0', '-', '2021-02-25 19:35:13', NULL, 1, NULL, 0);
INSERT INTO `roles_menus` VALUES (264, 1);
INSERT INTO `roles_menus` VALUES (265, 1);

-- ----------------------------
-- Table structure for yx_store_canvas
-- ----------------------------
DROP TABLE IF EXISTS `yx_store_canvas`;
CREATE TABLE `yx_store_canvas`  (
                                    `canvas_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '画布id',
                                    `terminal` tinyint(1) NOT NULL COMMENT '终端 1-小程序 2-H5 3-APP 4-PC',
                                    `json` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '画布json数据',
                                    `type` tinyint(1) NULL DEFAULT 1 COMMENT '类型 1-系统画布 2-自定义页面 3-商家店铺装修',
                                    `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '名称',
                                    `shop_id` bigint(20) NULL DEFAULT 0 COMMENT '店铺id，当type=3的时候，值为具体的店铺id，其它情况为0',
                                    `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
                                    `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
                                    `is_del` tinyint(1) NULL DEFAULT NULL COMMENT '删除标识',
                                    PRIMARY KEY (`canvas_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '画布信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yx_store_canvas
-- ----------------------------
INSERT INTO `yx_store_canvas` VALUES (11, 3, '[{\"title\":\"店铺头部\",\"type\":\"header\",\"componentContent\":{\"title\":\"Yshop\"},\"index\":0},{\"title\":\"搜索商品\",\"type\":\"search\",\"componentContent\":{},\"index\":1},{\"title\":\"轮播图\",\"type\":\"banner\",\"componentContent\":{\"bannerData\":[{\"pic\":\"./static/img/banner.f96c3f5.png\",\"name\":\"0\",\"sort\":0,\"url\":\"/\",\"status\":1}]},\"index\":2},{\"title\":\"滚动新闻\",\"type\":\"noticeBar\",\"componentContent\":{\"roll\":[{\"uniapp_url\":\"/pages/shop/news/NewsList/index\",\"url\":\"/news_list\",\"info\":\"yshop基于springboot2+Mybatisplus商城系统，3.0版本重构了代码，新增了运费模板、sku单独管理、商品券等\",\"wxapp_url\":\"/pages/shop/news/NewsList/main\"}]},\"index\":3},{\"title\":\"菜单\",\"type\":\"menu\",\"componentContent\":{\"menus\":[{\"imageArr\":[\"https://image.dayouqiantu.cn/5e85bfa61251d.png\"],\"uniapp_url\":\"/pages/shop/GoodsList/index\",\"name\":\"全部商品\",\"id\":180,\"pic\":\"https://image.dayouqiantu.cn/all.png\",\"sort\":9,\"url\":\"/goods_list\",\"wxapp_url\":\"/pages/shop/GoodsClass/main\",\"status\":1},{\"imageArr\":[\"https://image.dayouqiantu.cn/news.png\"],\"uniapp_url\":\"/pages/shop/news/NewsList/index\",\"name\":\"图文资讯\",\"id\":196,\"pic\":\"https://image.dayouqiantu.cn/xw.png\",\"sort\":8,\"url\":\"/news_list\",\"wxapp_url\":\"/pages/shop/news/NewsList/main\",\"status\":1},{\"imageArr\":[\"https://image.dayouqiantu.cn/5e4e939507b5e.png\"],\"uniapp_url\":\"/pages/shop/GoodsCollection/index\",\"name\":\"我的收藏\",\"id\":197,\"pic\":\"https://image.dayouqiantu.cn/colle.png\",\"sort\":7,\"url\":\"/collection\",\"wxapp_url\":\"/pages/shop/GoodsCollection/main\",\"status\":1},{\"imageArr\":[\"https://image.dayouqiantu.cn/coupon.png\"],\"uniapp_url\":\"\",\"name\":\"优惠券\",\"id\":199,\"pic\":\"https://image.dayouqiantu.cn/cou.png\",\"sort\":6,\"url\":\"/user/get_coupon\",\"wxapp_url\":\"/pages/user/coupon/GetCoupon/main\",\"status\":1},{\"imageArr\":[\"https://image.dayouqiantu.cn/pink.png\"],\"uniapp_url\":\"/pages/activity/GoodsGroup/index\",\"name\":\"拼团专区\",\"id\":200,\"pic\":\"https://image.dayouqiantu.cn/62ac09d2914d36c65b9b59d2147d809a.png\",\"sort\":5,\"url\":\"/activity/group\",\"wxapp_url\":\"/pages/activity/GoodsGroup/main\",\"status\":1},{\"imageArr\":[\"https://image.dayouqiantu.cn/sign.png\"],\"uniapp_url\":\"/pages/user/signIn/Sign/index\",\"name\":\"积分签到\",\"id\":209,\"pic\":\"https://image.dayouqiantu.cn/29ea4acebbf99e7eaf6f85af2b6d79ae.png\",\"sort\":4,\"url\":\"/user/sign\",\"wxapp_url\":\"/pages/user/signIn/Sign/main\",\"status\":1},{\"imageArr\":[\"https://image.dayouqiantu.cn/sekill.png\"],\"uniapp_url\":\"/pages/activity/GoodsSeckill/index\",\"name\":\"秒杀专区\",\"id\":216,\"pic\":\"https://image.dayouqiantu.cn/b0344c148141b50d68db9722708ea49e.png\",\"sort\":3,\"url\":\"/activity/goods_seckill\",\"wxapp_url\":\"/pages/activity/GoodsSeckill/main\",\"status\":1},{\"imageArr\":[\"https://image.dayouqiantu.cn/bargin.png\"],\"uniapp_url\":\"/pages/activity/GoodsBargain/index\",\"name\":\"砍价专区\",\"id\":217,\"pic\":\"https://image.dayouqiantu.cn/bar.png\",\"sort\":2,\"url\":\"/activity/bargain\",\"wxapp_url\":\"/pages/activity/GoodsBargain/main\",\"status\":1}]},\"index\":4},{\"title\":\"广告\",\"type\":\"adv\",\"componentContent\":{\"detail\":{\"list\":[{\"image\":\"https://wx.yixiang.co/static/images/index001.png\",\"url\":\"\",\"uniapp_url\":\"/pages/user/coupon/GetCoupon/index\",\"wxapp_url\":\"/pages/user/coupon/GetCoupon/index\",\"path_type\":1},{\"image\":\"https://wx.yixiang.co/static/images/index002.png\",\"url\":\"\",\"uniapp_url\":\"/pages/shop/GoodsList/index\",\"wxapp_url\":\"/pages/shop/GoodsList/index\",\"path_type\":1},{\"image\":\"https://wx.yixiang.co/static/images/index003.png\",\"url\":\"\",\"uniapp_url\":\"/pages/shop/GoodsList/index?title=\\\"积分商城\\\"&isIntegral=true\",\"wxapp_url\":\"/pages/shop/GoodsList/index?title=\\\"积分商城\\\"&isIntegral=true\",\"path_type\":1}],\"name\":\"\",\"style\":3}},\"index\":5},{\"title\":\"热门榜单\",\"type\":\"hotCommodity\",\"componentContent\":{},\"index\":6},{\"title\":\"为您推荐\",\"type\":\"promotionGood\",\"componentContent\":{},\"index\":7}]', 1, '1', 0, '2021-02-25 19:36:06', '2021-02-25 22:39:55', 0);



