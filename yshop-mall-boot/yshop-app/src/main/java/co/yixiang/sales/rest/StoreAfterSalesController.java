package co.yixiang.sales.rest;

import io.swagger.v3.oas.annotations.media.Schema;

import io.swagger.v3.oas.annotations.enums.ParameterIn;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import co.yixiang.api.ApiResult;
import co.yixiang.api.YshopException;
import co.yixiang.common.bean.LocalUser;
import co.yixiang.common.interceptor.AuthCheck;
import co.yixiang.modules.logging.aop.log.AppLog;
import co.yixiang.modules.order.domain.YxExpress;
import co.yixiang.modules.order.service.YxExpressService;
import co.yixiang.modules.order.service.dto.YxExpressQueryCriteria;
import co.yixiang.modules.sales.param.StoreAfterSalesParam;
import co.yixiang.modules.sales.service.StoreAfterSalesItemService;
import co.yixiang.modules.sales.service.StoreAfterSalesService;
import co.yixiang.modules.sales.service.StoreAfterSalesStatusService;
import co.yixiang.modules.sales.service.vo.StoreAfterSalesVo;
import co.yixiang.modules.sales.service.vo.YxStoreOrderCartInfoVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author : gzlv 2021/6/27 16:00
 */
@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "用户:售后记录")
public class StoreAfterSalesController {

    @Autowired
    private StoreAfterSalesService storeAfterSalesService;
    @Autowired
    private StoreAfterSalesItemService storeAfterSalesItemService;
    @Autowired
    private StoreAfterSalesStatusService storeAfterSalesStatusService;
    @Autowired
    private YxExpressService yxExpressService;

    @AuthCheck
    @PostMapping("/getRefundAmount")
    @Operation(summary = "获取退款价格", description = "获取退款价格")
    public ApiResult<BigDecimal> getRefundAmount(@RequestBody StoreAfterSalesParam storeAfterSalesParam) {
        return ApiResult.ok(storeAfterSalesService.getRefundAmount(storeAfterSalesParam));
    }

    @AuthCheck
    @PostMapping("/applyForAfterSales")
    @Operation(summary = "申请售后", description = "申请售后")
    public ApiResult<Boolean> applyForAfterSales(@RequestBody StoreAfterSalesParam storeAfterSalesParam) {
        storeAfterSalesService.applyForAfterSales(LocalUser.getUser().getUid(), LocalUser.getUser().getNickname(), storeAfterSalesParam);
        return ApiResult.ok();
    }

    @AuthCheck
    @GetMapping("/applyForAfterSales/{key}")
    @Operation(summary = "查询订单详情", description = "查询订单详情")
    public ApiResult<List<YxStoreOrderCartInfoVo>> checkOrderDetails(@PathVariable String key) {
        return ApiResult.ok(storeAfterSalesService.checkOrderDetails(key));
    }

    @AppLog(value = "查看售后列表", type = 1)
    @AuthCheck
    @GetMapping("/storeAfterSales/list")
    @Operation(summary = "售后列表", description = "售后列表")
    public ApiResult<Object> salesList(@RequestParam(value = "type", defaultValue = "0") int type,
                                       @RequestParam(value = "page", defaultValue = "1") int page,
                                       @RequestParam(value = "limit", defaultValue = "10") int limit) {
        Map<String, Object> map = storeAfterSalesService.salesList(LocalUser.getUser().getUid(), type,
                page, null, limit);
        Long total = (Long) map.get("total");
        Long totalPage = (Long) map.get("totalPage");
        return ApiResult.resultPage(total.intValue(), totalPage.intValue(), map.get("list"));
    }

    @AppLog(value = "查看售後详情", type = 1)
    @AuthCheck
    @GetMapping("/store/detail/{key}/{id}")
    @Parameters({
            @Parameter(name = "key", description = "唯一的key", in = ParameterIn.QUERY, schema = @Schema(type = "string"))
    })
    @Operation(summary = "订单详情", description = "订单详情")
    public ApiResult<StoreAfterSalesVo> detail(@PathVariable String key, @PathVariable Long id) {
        Long uid = LocalUser.getUser().getUid();
        if (StrUtil.isEmpty(key)) {
            throw new YshopException("参数错误");
        }
        StoreAfterSalesVo storeInfo = storeAfterSalesService.getStoreInfoByOrderCodeAndAfterIdAndUid(key, id, uid);
        storeAfterSalesService.handleSales(storeInfo);
        return ApiResult.ok(storeInfo);
    }

    @AppLog(value = "通过订单号搜索", type = 1)
    @AuthCheck
    @GetMapping("/store/detail/{key}")
    @Parameters({
            @Parameter(name = "key", description = "唯一的key", in = ParameterIn.QUERY, schema = @Schema(type = "string"))
    })
    @Operation(summary = "订单详情", description = "订单详情")
    public ApiResult<List<StoreAfterSalesVo>> detail(@PathVariable String key) {
        Long uid = LocalUser.getUser().getUid();
        if (StrUtil.isEmpty(key)) {
            throw new YshopException("参数错误");
        }
        List<StoreAfterSalesVo> storeInfo = storeAfterSalesService.getStoreInfoByOrderCodeAndUid(key, uid);
            storeInfo.forEach(item ->
                    storeAfterSalesService.handleSales(item)
            );
        if (ObjectUtil.isNull(storeInfo)) {
            throw new YshopException("售后订单不存在");
        }
        return ApiResult.ok(storeInfo);
    }

    @AppLog(value = "撤销申请", type = 1)
    @AuthCheck
    @GetMapping("/revoke/{key}/{id}")
    @Parameters({
            @Parameter(name = "key", description = "订单号", in = ParameterIn.QUERY, schema = @Schema(type = "string"))
    })
    @Operation(summary = "撤销申请", description = "撤销申请")
    public ApiResult<Boolean> revoke(@PathVariable String key, @PathVariable Long id) {
        Long uid = LocalUser.getUser().getUid();
        if (StrUtil.isEmpty(key)) {
            throw new YshopException("参数错误");
        }
        Boolean revoke = storeAfterSalesService.revoke(key, uid, id);
        return ApiResult.ok(revoke);
    }

    @Operation(summary = "查询快递")
    @GetMapping(value = "/yxExpress")
    public ApiResult<List<YxExpress>> getYxExpresss(YxExpressQueryCriteria criteria) {
        return ApiResult.ok(yxExpressService.queryAll(criteria));
    }

    @AppLog(value = "添加物流信息", type = 1)
    @AuthCheck
    @PostMapping("/addLogisticsInformation")
    @Parameters({
            @Parameter(name = "code", description = "快递公司编码", in = ParameterIn.QUERY, schema = @Schema(type = "string")),
            @Parameter(name = "name", description = "快递公司名称", in = ParameterIn.QUERY, schema = @Schema(type = "string")),
            @Parameter(name = "postalCode", description = "快递编号", in = ParameterIn.QUERY, schema = @Schema(type = "string")),
            @Parameter(name = "orderCode", description = "订单编号", in = ParameterIn.QUERY, schema = @Schema(type = "string")),
            @Parameter(name = "id", description = "售后订单id", in = ParameterIn.QUERY, schema = @Schema(type = "integer"))
    })
    @Operation(summary = "添加物流信息", description = "添加物流信息")
    public ApiResult<Boolean> addLogisticsInformation(@RequestParam String code, @RequestParam String name,
                                                      @RequestParam String postalCode, @RequestParam String orderCode,
                                                      @RequestParam Long id) {
        Long uid = LocalUser.getUser().getUid();
        if (StrUtil.isEmpty(code) || StrUtil.isEmpty(name) || StrUtil.isEmpty(postalCode)) {
            throw new YshopException("参数错误");
        }
        Boolean revoke = storeAfterSalesService.addLogisticsInformation(code, name, postalCode, orderCode, id);
        return ApiResult.ok(revoke);
    }

    @AppLog(value = "删除售后订单", type = 1)
    @AuthCheck
    @DeleteMapping("/deleteAfterSalesOrder")
    @Parameters({
            @Parameter(name = "orderCode", description = "订单编码", in = ParameterIn.QUERY, schema = @Schema(type = "string"))
    })
    @Operation(summary = "删除售后订单", description = "删除售后订单")
    public ApiResult<Boolean> deleteAfterSalesOrder(@RequestParam String orderCode, @RequestParam Long id) {
        Long uid = LocalUser.getUser().getUid();
        if (StrUtil.isEmpty(orderCode) || ObjectUtil.isEmpty(id)) {
            throw new YshopException("参数错误");
        }
        Boolean revoke = storeAfterSalesService.deleteAfterSalesOrder(orderCode, id);
        return ApiResult.ok(revoke);
    }


}
