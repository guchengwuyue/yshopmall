package co.yixiang.constant;

/**
 * 商城统一常量
 * @author hupeng
 * @since 2020-02-27
 */
public interface ShopConstants {

	/**
	 * 订单自动取消时间（分钟）
	 */
	long ORDER_OUTTIME_UNPAY = 30;
	/**
	 * 订单自动收货时间（天）
	 */
	long ORDER_OUTTIME_UNCONFIRM = 7;
	/**
	 * redis订单未付款key
	 */
	String REDIS_ORDER_OUTTIME_UNPAY = "order:unpay:";
	/**
	 * redis订单收货key
	 */
	String REDIS_ORDER_OUTTIME_UNCONFIRM = "order:unconfirm:";

	/**
	 * 微信支付service
	 */
	String YSHOP_WEIXIN_PAY_SERVICE = "yshop_weixin_pay_service";

	/**
	 * 微信公众号service
	 */
	String YSHOP_WEIXIN_MP_SERVICE = "yshop_weixin_mp_service";

	/**
	 * 商城默认密码
	 */
	String YSHOP_DEFAULT_PWD = "123456";

	/**
	 * 商城默认注册图片
	 */
	String YSHOP_DEFAULT_AVATAR = "https://image.dayouqiantu.cn/5dc2c7f3a104c.png";

	/**
	 * 腾讯地图地址解析
	 */
	String QQ_MAP_URL = "https://apis.map.qq.com/ws/geocoder/v1/";

	/**
	 * redis首页键
	 */
	String YSHOP_REDIS_INDEX_KEY = "yshop:index_data";

	/**
	 * redis首页过期时间 单位秒
	 */
	long YSHOP_REDIS_INDEX_KEY_EXPIRE = 7200;



}
