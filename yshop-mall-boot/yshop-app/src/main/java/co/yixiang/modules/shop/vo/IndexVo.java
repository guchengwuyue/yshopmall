package co.yixiang.modules.shop.vo;

import co.yixiang.modules.mp.service.dto.YxWechatLiveDto;
import co.yixiang.modules.product.vo.YxStoreProductQueryVo;
import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "首页数据")
public class IndexVo {

    @Schema(description = "banner")
    private List<JSONObject> banner;
    //首页按钮
    @Schema(description = "首页按钮")
    private List<JSONObject> menus;
    //精品推荐->拼团
    @Schema(description = "精品推荐")
    private List<YxStoreProductQueryVo> bastList;
    //首发新品->秒杀
    @Schema(description = "首发新品")
    private List<YxStoreProductQueryVo> firstList;
    //猜你喜欢
    @Schema(description = "猜你喜欢")
    private List<YxStoreProductQueryVo> benefit;
    //热门榜单
    @Schema(description = "热门榜单")
    private List<YxStoreProductQueryVo> likeInfo;
    //滚动
    @Schema(description = "滚动")
    private List<JSONObject> roll;
    //地图key
    @Schema(description = "地图key")
    private String mapKey;

    //直播间信息
    @Schema(description = "直播间")
    private List<YxWechatLiveDto> liveList;

}
