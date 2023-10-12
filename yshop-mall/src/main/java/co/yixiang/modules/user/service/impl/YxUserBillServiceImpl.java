/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.user.service.impl;

import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.enums.BillDetailEnum;
import co.yixiang.enums.BillEnum;
import co.yixiang.enums.BillInfoEnum;
import co.yixiang.enums.ShopCommonEnum;
import co.yixiang.modules.user.domain.YxUserBill;
import co.yixiang.modules.user.service.YxUserBillService;
import co.yixiang.modules.user.service.dto.BillOrderDto;
import co.yixiang.modules.user.service.dto.BillOrderRecordDto;
import co.yixiang.modules.user.service.dto.YxUserBillDto;
import co.yixiang.modules.user.service.dto.YxUserBillQueryCriteria;
import co.yixiang.modules.user.service.mapper.UserBillMapper;
import co.yixiang.modules.user.vo.BillVo;
import co.yixiang.modules.user.vo.YxUserBillQueryVo;
import co.yixiang.utils.FileUtil;
import co.yixiang.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;


/**
* @author hupeng
* @date 2020-05-12
*/
@Service
@AllArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxUserBillServiceImpl extends BaseServiceImpl<UserBillMapper, YxUserBill> implements YxUserBillService {

    private final IGenerator generator;
    private final UserBillMapper yxUserBillMapper;


    /**
     * 增加支出流水
     * @param uid uid
     * @param title 账单标题
     * @param category 明细种类
     * @param type 明细类型
     * @param number 明细数字
     * @param balance 剩余
     * @param mark 备注
     */
    @Override
    public void expend(Long uid,String title,String category,String type,double number,double balance,String mark){
        YxUserBill userBill = YxUserBill.builder()
                .uid(uid)
                .title(title)
                .category(category)
                .type(type)
                .number(BigDecimal.valueOf(number))
                .balance(BigDecimal.valueOf(balance))
                .mark(mark)
                .pm(BillEnum.PM_0.getValue())
                .build();

        yxUserBillMapper.insert(userBill);
    }

    /**
     * 增加收入/支入流水
     * @param uid uid
     * @param title 账单标题
     * @param category 明细种类
     * @param type 明细类型
     * @param number 明细数字
     * @param balance 剩余
     * @param mark 备注
     * @param linkid 关联id
     */
    @Override
    public void income(Long uid,String title,String category,String type,double number,
                       double balance,String mark,String linkid){
        YxUserBill userBill = YxUserBill.builder()
                .uid(uid)
                .title(title)
                .category(category)
                .type(type)
                .number(BigDecimal.valueOf(number))
                .balance(BigDecimal.valueOf(balance))
                .mark(mark)
                .pm(BillEnum.PM_1.getValue())
                .linkId(linkid)
                .build();

        yxUserBillMapper.insert(userBill);
    }

    /**
     * 签到了多少次
     *
     * @param uid
     * @return
     */
    @Override
    public Long cumulativeAttendance(Long uid) {
       LambdaQueryWrapper<YxUserBill> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(YxUserBill::getUid,uid).eq(YxUserBill::getCategory,"integral")
                .eq(YxUserBill::getType,"sign").eq(YxUserBill::getPm,1);
        return yxUserBillMapper.selectCount(wrapper);
    }

    /**
     * 获取推广订单列表
     * @param uid   uid
     * @param page  page
     * @param limit limit
     * @return Map
     */
    @Override
    public Map<String, Object> spreadOrder(Long uid, int page, int limit) {
       QueryWrapper<YxUserBill> wrapper = new QueryWrapper<>();
        wrapper.lambda().in(YxUserBill::getUid, uid)
                .eq(YxUserBill::getType, BillDetailEnum.TYPE_2.getValue())
                .eq(YxUserBill::getCategory, BillDetailEnum.CATEGORY_1.getValue());
        wrapper.orderByDesc("time").groupBy("time");
        Page<YxUserBill> pageModel = new Page<>(page, limit);
        List<String> list = yxUserBillMapper.getBillOrderList(wrapper, pageModel);

        Long count = yxUserBillMapper.selectCount(Wrappers.<YxUserBill>lambdaQuery()
                .eq(YxUserBill::getUid, uid)
                .eq(YxUserBill::getType, BillDetailEnum.TYPE_2.getValue())
                .eq(YxUserBill::getCategory, BillDetailEnum.CATEGORY_1.getValue()));
        List<BillOrderDto> listT = new ArrayList<>();
        for (String str : list) {
            BillOrderDto billOrderDTO = new BillOrderDto();
            List<BillOrderRecordDto> orderRecordDTOS = yxUserBillMapper
                    .getBillOrderRList(str, uid);
            billOrderDTO.setChild(orderRecordDTOS);
            billOrderDTO.setCount(orderRecordDTOS.size());
            billOrderDTO.setTime(str);

            listT.add(billOrderDTO);
        }

        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("list", listT);
        map.put("count", count);

        return map;
    }

    /**
     * 获取用户账单记录
     * @param page page
     * @param limit limit
     * @param uid uid
     * @param type BillDetailEnum
     * @return map
     */
    @Override
    public Map<String,Object> getUserBillList(int page, int limit, long uid, int type) {
       QueryWrapper<YxUserBill> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(YxUserBill::getUid,uid).orderByDesc(YxUserBill::getCreateTime)
                .orderByAsc(YxUserBill::getId);
        wrapper.groupBy("time");
        switch (BillInfoEnum.toType(type)){
            case PAY_PRODUCT:
                wrapper.lambda().eq(YxUserBill::getCategory,BillDetailEnum.CATEGORY_1.getValue());
                wrapper.lambda().eq(YxUserBill::getType,BillDetailEnum.TYPE_3.getValue());
                break;
            case RECHAREGE:
                wrapper.lambda().eq(YxUserBill::getCategory,BillDetailEnum.CATEGORY_1.getValue());
                wrapper.lambda().eq(YxUserBill::getType,BillDetailEnum.TYPE_1.getValue());
                break;
            case BROKERAGE:
                wrapper.lambda().eq(YxUserBill::getCategory,BillDetailEnum.CATEGORY_1.getValue());
                wrapper.lambda().eq(YxUserBill::getType,BillDetailEnum.TYPE_2.getValue());
                break;
            case EXTRACT:
                wrapper.lambda().eq(YxUserBill::getCategory,BillDetailEnum.CATEGORY_1.getValue());
                wrapper.lambda().eq(YxUserBill::getType,BillDetailEnum.TYPE_4.getValue());
                break;
            case SIGN_INTEGRAL:
                wrapper.lambda().eq(YxUserBill::getCategory,BillDetailEnum.CATEGORY_2.getValue());
                wrapper.lambda().eq(YxUserBill::getType,BillDetailEnum.TYPE_10.getValue());
                break;
            default:
                wrapper.lambda().eq(YxUserBill::getCategory,BillDetailEnum.CATEGORY_1.getValue());

        }
        Page<YxUserBill> pageModel = new Page<>(page, limit);
        List<BillVo> billDTOList = yxUserBillMapper.getBillList(wrapper,pageModel);
        for (BillVo billDTO : billDTOList) {
           LambdaQueryWrapper<YxUserBill> wrapperT = new LambdaQueryWrapper<>();
            wrapperT.in(YxUserBill::getId,Arrays.asList(billDTO.getIds().split(",")));
            wrapperT.orderByDesc(YxUserBill::getCreateTime);
            billDTO.setList(yxUserBillMapper.getUserBillList(wrapperT));

        }
        Map<String,Object> map = new HashMap<>();
        map.put("list",billDTOList);
        map.put("total",pageModel.getTotal());
        map.put("totalPage",pageModel.getPages());
        return map;
       // return billDTOList;
    }

    @Override
    public double getBrokerage(int uid) {
        return yxUserBillMapper.sumPrice(uid);
    }

    /**
     * 统计昨天的佣金
     * @param uid uid
     * @return double
     */
    @Override
    public double yesterdayCommissionSum(Long uid) {
        return yxUserBillMapper.sumYesterdayPrice(uid);
    }

    /**
     * 根据类别获取账单记录
     * @param uid uid
     * @param category  BillDetailEnum
     * @param page page
     * @param limit limit
     * @return List
     */
    @Override
    public List<YxUserBillQueryVo> userBillList(Long uid,String category,int page,int limit) {
       LambdaQueryWrapper<YxUserBill> wrapper = new LambdaQueryWrapper<>();
        wrapper
                .eq(YxUserBill::getStatus, ShopCommonEnum.IS_STATUS_1.getValue())
                .eq(YxUserBill::getUid,uid)
                .eq(YxUserBill::getCategory,category)
                .orderByDesc(YxUserBill::getId);
        Page<YxUserBill> pageModel = new Page<>(page, limit);
        IPage<YxUserBill> pageList = yxUserBillMapper.selectPage(pageModel,wrapper);
        return generator.convert(pageList.getRecords(),YxUserBillQueryVo.class);
    }


    //============================================//

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(YxUserBillQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<YxUserBillDto> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", page.getList());
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    public List<YxUserBillDto> queryAll(YxUserBillQueryCriteria criteria){
        String date =null;
        String date1 = null;
        if(StringUtils.isNotEmpty(criteria.getStartTime())){
            date =  criteria.getStartTime()+ " 00:00:00";
            if(StringUtils.isNotEmpty(criteria.getEndTime())){
                date1 =criteria.getEndTime()+ " 23:59:59";
            }
        }

        return baseMapper.findAllByQueryCriteria(criteria.getCategory(),criteria.getType(),criteria.getNickname(),criteria.getPm(),date,date1,criteria.getTitle());
    }


    @Override
    public void download(List<YxUserBillDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxUserBillDto yxUserBill : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("用户uid", yxUserBill.getUid());
            map.put("关联id", yxUserBill.getLinkId());
            map.put("0 = 支出 1 = 获得", yxUserBill.getPm());
            map.put("账单标题", yxUserBill.getTitle());
            map.put("明细种类", yxUserBill.getCategory());
            map.put("明细类型", yxUserBill.getType());
            map.put("明细数字", yxUserBill.getNumber());
            map.put("剩余", yxUserBill.getBalance());
            map.put("备注", yxUserBill.getMark());
            map.put("0 = 带确定 1 = 有效 -1 = 无效", yxUserBill.getStatus());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
