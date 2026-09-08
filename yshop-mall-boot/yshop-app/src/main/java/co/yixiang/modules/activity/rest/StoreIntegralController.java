package co.yixiang.modules.activity.rest;

import co.yixiang.api.ApiResult;
import co.yixiang.modules.product.param.YxStoreProductQueryParam;
import co.yixiang.modules.product.service.YxStoreProductService;
import co.yixiang.modules.product.vo.YxStoreProductQueryVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 积分兑换前端控制器
 * @author yshop
 */
@Slf4j
@RestController
@RequestMapping
@Tag(name = "营销:积分兑换")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StoreIntegralController {

    private final YxStoreProductService storeProductService;

    /**
     * 获取积分产品列表
     */
    @GetMapping("/products/integral")
    @Operation(summary = "获取积分产品列表", description = "获取积分产品列表")
    public ApiResult<List<YxStoreProductQueryVo>> goodsList(YxStoreProductQueryParam productQueryParam){
        return ApiResult.ok(storeProductService.getGoodsList(productQueryParam));
    }
}
