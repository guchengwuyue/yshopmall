/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co

 */
package co.yixiang.modules.order.service.impl;

import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.common.utils.QueryHelpPlus;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.modules.order.domain.YxStoreOrderStatus;
import co.yixiang.modules.order.service.YxStoreOrderStatusService;
import co.yixiang.modules.order.service.dto.YxStoreOrderStatusDto;
import co.yixiang.modules.order.service.dto.YxStoreOrderStatusQueryCriteria;
import co.yixiang.modules.order.service.mapper.StoreOrderStatusMapper;
import co.yixiang.utils.FileUtil;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;



/**
* @author hupeng
* @date 2020-05-12
*/
@Service
@AllArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxStoreOrderStatusServiceImpl extends BaseServiceImpl<StoreOrderStatusMapper, YxStoreOrderStatus> implements YxStoreOrderStatusService {

    private final IGenerator generator;


    /**
     * 添加订单操作记录
     * @param oid 订单id
     * @param changetype 操作状态
     * @param changeMessage 操作内容
     */
    @Override
    public void create(Long oid, String changetype, String changeMessage) {
        YxStoreOrderStatus storeOrderStatus = new YxStoreOrderStatus();
        storeOrderStatus.setOid(oid);
        storeOrderStatus.setChangeType(changetype);
        storeOrderStatus.setChangeMessage(changeMessage);
        this.baseMapper.insert(storeOrderStatus);
    }



    @Override
    //@Cacheable
    public Map<String, Object> queryAll(YxStoreOrderStatusQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<YxStoreOrderStatus> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), YxStoreOrderStatusDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<YxStoreOrderStatus> queryAll(YxStoreOrderStatusQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(YxStoreOrderStatus.class, criteria));
    }


    @Override
    public void download(List<YxStoreOrderStatusDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxStoreOrderStatusDto yxStoreOrderStatus : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("订单id", yxStoreOrderStatus.getOid());
            map.put("操作类型", yxStoreOrderStatus.getChangeType());
            map.put("操作备注", yxStoreOrderStatus.getChangeMessage());
            map.put("操作时间", yxStoreOrderStatus.getChangeTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
