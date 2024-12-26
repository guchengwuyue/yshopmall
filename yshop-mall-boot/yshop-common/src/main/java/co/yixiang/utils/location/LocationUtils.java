package co.yixiang.utils.location;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import co.yixiang.exception.BadRequestException;
import co.yixiang.utils.RedisUtil;
import co.yixiang.utils.ShopKeyUtils;
import com.alibaba.fastjson.JSONObject;

/**
 * 具体查看 https://lbs.qq.com/service/webService/webServiceGuide/webServiceGeocoder
 * @author ：LionCity
 * @date ：Created in 2020-09-10 11:46
 * @description：
 * @modified By：
 * @version:
 */
public class LocationUtils {

    private static double EARTH_RADIUS = 6378.137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 腾讯地图地址解析
     */
    private static final String QQ_MAP_URL = "https://apis.map.qq.com/ws/geocoder/v1/";


    /**
     * 通过经纬度获取距离(单位：千米)
     *
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return
     */
    public static double getDistance(double lat1, double lng1, double lat2,
                                     double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000d) / 10000d;
        //s = s*1000;
        return NumberUtil.round(s, 2).doubleValue();
        //return s;
    }


    public static GetTencentLocationVO getLocation(String addr) {
        String key = RedisUtil.get(ShopKeyUtils.getTengXunMapKey());
        if (StrUtil.isBlank(key)) {
            throw new BadRequestException("请先配置腾讯地图key");
        }
        String url = StrUtil.format("?address={}&key={}", addr, key);
        String json = HttpUtil.get(QQ_MAP_URL + url);
        return JSONObject.parseObject(json, GetTencentLocationVO.class);
    }
}
