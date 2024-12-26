package co.yixiang.modules.shop.vo;

import co.yixiang.modules.activity.vo.YxStoreCombinationQueryVo;
import co.yixiang.modules.activity.vo.YxStoreSeckillQueryVo;
import co.yixiang.modules.mp.service.dto.YxWechatLiveDto;
import co.yixiang.modules.product.vo.YxStoreProductQueryVo;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("首页数据")
public class IndexVo {

    @ApiModelProperty("banner")
    private List<JSONObject> banner;
    //首页按钮
    @ApiModelProperty("首页按钮")
    private List<JSONObject> menus;
    //精品推荐->拼团
    @ApiModelProperty("精品推荐")
    private List<YxStoreProductQueryVo> bastList;
    //首发新品->秒杀
    @ApiModelProperty("首发新品")
    private List<YxStoreProductQueryVo> firstList;
    //猜你喜欢
    @ApiModelProperty("猜你喜欢")
    private List<YxStoreProductQueryVo> benefit;
    //热门榜单
    @ApiModelProperty("热门榜单")
    private List<YxStoreProductQueryVo> likeInfo;
    //滚动
    @ApiModelProperty("滚动")
    private List<JSONObject> roll;
    //地图key
    @ApiModelProperty("地图key")
    private String mapKey;
    //精品推荐->拼团
    @ApiModelProperty("精品推荐->拼团")
    private List<YxStoreCombinationQueryVo> combinationList;
    //首发新品->秒杀
    @ApiModelProperty("首发新品->秒杀")
    private List<YxStoreSeckillQueryVo> seckillList;
    //直播间信息
    @ApiModelProperty("直播间")
    private List<YxWechatLiveDto> liveList;

}
