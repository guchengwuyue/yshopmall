package co.yixiang.modules.sales.service;

import co.yixiang.common.service.BaseService;
import co.yixiang.modules.sales.domain.StoreAfterSales;
import co.yixiang.modules.sales.param.StoreAfterSalesParam;
import co.yixiang.modules.sales.param.YxStoreAfterSalesDto;
import co.yixiang.modules.sales.param.YxStoreAfterSalesQueryCriteria;
import co.yixiang.modules.sales.service.vo.StoreAfterSalesVo;
import co.yixiang.modules.sales.service.vo.YxStoreOrderCartInfoVo;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author : gzlv 2021/6/27 15:54
 */
public interface StoreAfterSalesService extends BaseService<StoreAfterSales> {

    /**
     * 创建售后订单
     *
     * @param userId               用户id
     * @param nickname             用户昵称
     * @param storeAfterSalesParam /
     */
    void applyForAfterSales(Long userId, String nickname, StoreAfterSalesParam storeAfterSalesParam);

    /**
     * 查询订单详情
     * @param key 订单号
     * @return
     */
    List<YxStoreOrderCartInfoVo> checkOrderDetails(String key);

    /**
     * 查询列表
     * @param uid 用户id
     * @param status 状态
     * @param page 页
     * @param orderCode
     * @param limit 数量
     * @return
     */
    Map<String, Object> salesList(Long uid, Integer status, Integer page, String orderCode, Integer limit);

    /**
     * 查询详情
     * @param key 订单号
     * @param id 售后单id
     * @param uid 用户id
     */
    StoreAfterSalesVo getStoreInfoByOrderCodeAndAfterIdAndUid(String key, Long id, Long uid);

    /**
     * 查询详情
     * @param key 订单号
     * @param uid 用户id
     */
    List<StoreAfterSalesVo> getStoreInfoByOrderCodeAndUid(String key, Long uid);

    /**
     *
     * @param storeInfo
     * @return
     */
    StoreAfterSalesVo handleSales(StoreAfterSalesVo storeInfo);

    /**
     * 撤销申请
     * @param key 订单号
     * @param uid 用户id
     * @param aLong
     * @return Boolean
     */
    Boolean revoke(String key, Long uid, Long aLong);

    /**
     * 添加快递信息
     *
     * @param code       物流公司编码
     * @param name       物流公司名称
     * @param postalCode
     * @param orderCode
     * @param id
     * @return
     */
    Boolean addLogisticsInformation(String code, String name, String postalCode ,String orderCode, Long id);

    /**
     * 删除售后订单
     * @param orderCode /
     * @param id
     * @return /
     */
    Boolean deleteAfterSalesOrder(String orderCode, Long id);

    /**
     * 售后订单审核
     * @param salesId 售后id
     * @param orderCode 订单id
     * @param approvalStatus 审核状态
     * @param consignee 收货人
     * @param phoneNumber 收货人电话
     * @param address 地址
     * @return /
     */
    Object salesCheck(Long salesId, String orderCode, Integer approvalStatus, String consignee, String phoneNumber, String address);

    /**
     * 打款
     */
    StoreAfterSales makeMoney(Long salesId, String orderCode);


    /**
     * 查询数据分页
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map<String,Object>
     */
    Map<String, Object> queryAll(YxStoreAfterSalesQueryCriteria criteria, Pageable pageable);

    /**
     * 查询所有数据不分页
     * @param criteria 条件参数
     * @return List<YxStoreAfterSalesDto>
     */
    List<StoreAfterSales> queryAll(YxStoreAfterSalesQueryCriteria criteria);

    /**
     * 导出数据
     * @param all 待导出的数据
     * @param response /
     * @throws IOException /
     */
    void download(List<YxStoreAfterSalesDto> all, HttpServletResponse response) throws IOException;
}
