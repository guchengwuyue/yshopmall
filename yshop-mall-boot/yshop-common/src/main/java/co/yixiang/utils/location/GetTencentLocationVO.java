package co.yixiang.utils.location;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value="腾讯地图返回对象")
public class GetTencentLocationVO implements Serializable {

    /**
     * result : {"level":2,"similarity":0.8,"ad_info":{"adcode":"441302"},"reliability":1,"location":{"lng":114.38257,"lat":23.08464},"deviation":1000,"title":"惠城区","address_components":{"province":"广东省","city":"惠州市","street":"","district":"惠城区","street_number":""}}
     * message : query ok
     * status : 0
     */
    @ApiModelProperty(value = "地址解析结果")
    private ResultBean result;
    @ApiModelProperty(value = "状态说明")
    private String message;
    @ApiModelProperty(value = "状态码，0为正常")
    private Integer status;

    @Data
    @ApiModel(value="地址解析结果")
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

        @ApiModelProperty(value = "解析到的坐标")
        private LocationBean location;
        @ApiModelProperty(value = "解析精度级别，分为11个级别，一般>=9即可采用（定位到点，精度较高） 也可根据实际业务需求自行调整")
        private int level;
        @ApiModelProperty(value = "即将下线，由reliability代替")
        private double similarity;

        @ApiModelProperty(value = "行政区划信息")
        private AdInfoBean ad_info;
        @ApiModelProperty(value = "可信度参考")
        private int reliability;
        @ApiModelProperty(value = "即将下线，由level代替")
        private int deviation;
        @ApiModelProperty(value = "区信息")
        private String title;
        @ApiModelProperty(value = "行政区划信息")
        private AddressComponentsBean address_components;


        @Data
        @ApiModel(value="行政区划信息")
        public static class AdInfoBean {
            /**
             * adcode : 441302
             */
            @ApiModelProperty(value = "行政区划代码")
            private String adcode;

        }
        @Data
        @ApiModel(value="解析到的坐标")
        public static class LocationBean {
            /**
             * lng : 114.38257
             * lat : 23.08464
             */

            @ApiModelProperty(value = "经度")
            private double lng;

            @ApiModelProperty(value = "纬度")
            private double lat;


        }
        @Data
        @ApiModel(value="解析后的地址部件")
        public static class AddressComponentsBean {
            /**
             * province : 广东省
             * city : 惠州市
             * street :
             * district : 惠城区
             * street_number :
             */
            @ApiModelProperty(value = "省")
            private String province;
            @ApiModelProperty(value = "市")
            private String city;
            @ApiModelProperty(value = "街道，可能为空字串")
            private String street;
            @ApiModelProperty(value = "区，可能为空字串")
            private String district;
            @ApiModelProperty(value = "门牌，可能为空字串")
            private String street_number;
        }
    }
}
