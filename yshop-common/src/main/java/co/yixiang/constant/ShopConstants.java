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
	 * 微信支付小程序service
	 */
	String YSHOP_WEIXIN_MINI_PAY_SERVICE = "yshop_weixin_mini_pay_service";

	/**
	 * 微信支付app service
	 */
	String YSHOP_WEIXIN_APP_PAY_SERVICE = "yshop_weixin_app_pay_service";

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
	String YSHOP_DEFAULT_AVATAR = "https://image.dayouqiantu.cn/5e79f6cfd33b6.png";

	/**
	 * 腾讯地图地址解析
	 */
	String QQ_MAP_URL = "https://apis.map.qq.com/ws/geocoder/v1/";

	/**
	 * redis首页键
	 */
	String YSHOP_REDIS_INDEX_KEY = "yshop:index_data";

	/**
	 * 充值方案
	 */
	String YSHOP_RECHARGE_PRICE_WAYS = "yshop_recharge_price_ways";
	/**
	 * 首页banner
	 */
	String YSHOP_HOME_BANNER = "yshop_home_banner";
	/**
	 * 首页菜单
	 */
	String YSHOP_HOME_MENUS = "yshop_home_menus";
	/**
	 * 首页滚动新闻
	 */
	String YSHOP_HOME_ROLL_NEWS = "yshop_home_roll_news";
	/**
	 * 热门搜索
	 */
	String YSHOP_HOT_SEARCH = "yshop_hot_search";
	/**
	 * 个人中心菜单
	 */
	String YSHOP_MY_MENUES = "yshop_my_menus";
	/**
	 * 秒杀时间段
	 */
	String YSHOP_SECKILL_TIME = "yshop_seckill_time";
	/**
	 * 签到天数
	 */
	String YSHOP_SIGN_DAY_NUM = "yshop_sign_day_num";




}
