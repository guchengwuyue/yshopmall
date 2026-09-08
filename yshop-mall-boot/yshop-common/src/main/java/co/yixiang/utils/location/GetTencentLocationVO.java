package co.yixiang.utils.location;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ：LionCity
 * @date ：Created in 2020-09-10 11:39
 * @description：
 * @modified By：
 * @version:
 */
@SuppressWarnings("ALL")
@Data
@Schema(description = "腾讯地图返回对象")
public class GetTencentLocationVO implements Serializable {

    /**
     * result : {"level":2,"similarity":0.8,"ad_info":{"adcode":"441302"},"reliability":1,"location":{"lng":114.38257,"lat":23.08464},"deviation":1000,"title":"惠城区","address_components":{"province":"广东省","city":"惠州市","street":"","district":"惠城区","street_number":""}}
     * message : query ok
     * status : 0
     */
    @Schema(description = "地址解析结果")
    private ResultBean result;
    @Schema(description = "状态说明")
    private String message;
    @Schema(description = "状态码，0为正常")
    private Integer status;

    @Data
    @Schema(description = "地址解析结果")
    public static class ResultBean {
        /**
         * level : 2
         * similarity : 0.8
         * ad_info : {"adcode":"441302"}
         * reliability : 1
         * location : {"lng":114.38257,"lat":23.08464}
         * deviation : 1000
         * title : 惠城区
         * address_components : {"province":"广东省","city":"惠州市","street":"","district":"惠城区","street_number":""}
         */

        @Schema(description = "解析到的坐标")
        private LocationBean location;
        @Schema(description = "解析精度级别，分为11个级别，一般>=9即可采用（定位到点，精度较高） 也可根据实际业务需求自行调整")
        private int level;
        @Schema(description = "即将下线，由reliability代替")
        private double similarity;

        @Schema(description = "行政区划信息")
        private AdInfoBean ad_info;
        @Schema(description = "可信度参考")
        private int reliability;
        @Schema(description = "即将下线，由level代替")
        private int deviation;
        @Schema(description = "区信息")
        private String title;
        @Schema(description = "行政区划信息")
        private AddressComponentsBean address_components;


        @Data
        @Schema(description = "行政区划信息")
        public static class AdInfoBean {
            /**
             * adcode : 441302
             */
            @Schema(description = "行政区划代码")
            private String adcode;

        }
        @Data
        @Schema(description = "解析到的坐标")
        public static class LocationBean {
            /**
             * lng : 114.38257
             * lat : 23.08464
             */

            @Schema(description = "经度")
            private double lng;

            @Schema(description = "纬度")
            private double lat;


        }
        @Data
        @Schema(description = "解析后的地址部件")
        public static class AddressComponentsBean {
            /**
             * province : 广东省
             * city : 惠州市
             * street :
             * district : 惠城区
             * street_number :
             */
            @Schema(description = "省")
            private String province;
            @Schema(description = "市")
            private String city;
            @Schema(description = "街道，可能为空字串")
            private String street;
            @Schema(description = "区，可能为空字串")
            private String district;
            @Schema(description = "门牌，可能为空字串")
            private String street_number;
        }
    }
}
