package co.yixiang.modules.mp.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class WxMaLiveInfo {

    private static final long serialVersionUID = 7285263767524755887L;

    /**
     * 直播列表
     */
    @Data
    public static class RoomInfo implements Serializable {
        private static final long serialVersionUID = 7745775280267417154L;
        private String name;
        private Integer roomid;
        private String coverImg;
        private String shareImg;
        private String feedsImg;
        private Integer liveStatus;
        private Long startTime;
        private Long endTime;
        private String anchorName;
        private String anchorWechat;
        private String anchorImg;
        private Integer type;
        private Integer screenType;
        private Integer closeLike;
        private Integer closeGoods;
        private Integer closeComment;
        private Integer closeReplay  ; // 是否关闭回放 1 关闭
        private Integer closeShare ;   //  是否关闭分享 1 关闭
        private Integer closeKf ; // 是否关闭客服，1 关闭
        private List<Goods> goods;
    }

    /**
     * 商品列表
     */
    @Data
    public static class Goods implements Serializable {
        private static final long serialVersionUID = 5769245932149287574L;
        private Integer goodsId;
        private String coverImgUrl;
        private String url;
        private Integer priceType;
        private String price;
        private String price2;
        private String name;
        /**
         * 1, 2：表示是为api添加商品，否则是在MP添加商品
         */
        private String thirdPartyTag;
    }
}
