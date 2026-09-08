package co.yixiang.modules.activity.vo;


import co.yixiang.modules.product.domain.YxStoreProductAttrValue;
import co.yixiang.modules.product.vo.YxStoreProductAttrQueryVo;
import co.yixiang.modules.product.vo.YxStoreProductReplyQueryVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 秒杀产品表 查询结果对象
 * </p>
 *
 * @author hupeng
 * @date 2019-12-17
 */
@Data
@Builder
public class StoreSeckillVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "秒杀产品评论信息")
    private YxStoreProductReplyQueryVo reply;

    @Schema(description = "秒杀产品评论数量")
    private Long replyCount;


    @Builder.Default
    @Schema(description = "秒杀产品用户是否收藏")
    private Boolean userCollect = false;

    @Schema(description = "模板名称")
    private String tempName;

    private List<YxStoreProductAttrQueryVo> productAttr = new ArrayList();

    private Map<String, YxStoreProductAttrValue> productValue = new LinkedHashMap<>();

}
