/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.activity.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import co.yixiang.api.YshopException;
import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.common.utils.QueryHelpPlus;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.enums.ShopCommonEnum;
import co.yixiang.enums.SpecTypeEnum;
import co.yixiang.exception.BadRequestException;
import co.yixiang.modules.activity.domain.YxStoreSeckill;
import co.yixiang.modules.activity.domain.YxStoreSeckill;
import co.yixiang.modules.activity.service.YxStoreSeckillService;
import co.yixiang.modules.activity.service.dto.YxStoreSeckillDto;
import co.yixiang.modules.activity.service.dto.YxStoreSeckillQueryCriteria;
import co.yixiang.modules.activity.service.mapper.YxStoreSeckillMapper;
import co.yixiang.modules.activity.vo.StoreSeckillVo;
import co.yixiang.modules.activity.vo.YxStoreSeckillQueryVo;
import co.yixiang.modules.product.domain.YxStoreProductAttrValue;
import co.yixiang.modules.product.service.YxStoreProductAttrService;
import co.yixiang.modules.product.service.YxStoreProductReplyService;
import co.yixiang.modules.product.service.dto.FromatDetailDto;
import co.yixiang.modules.product.service.dto.ProductFormatDto;
import co.yixiang.modules.product.service.dto.ProductResultDto;
import co.yixiang.modules.product.vo.YxStoreProductAttrQueryVo;
import co.yixiang.modules.template.domain.YxShippingTemplates;
import co.yixiang.modules.template.service.YxShippingTemplatesService;
import co.yixiang.utils.FileUtil;
import co.yixiang.utils.OrderUtil;
import co.yixiang.utils.RedisUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
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
* @date 2020-05-13
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxStoreSeckillServiceImpl extends BaseServiceImpl<YxStoreSeckillMapper, YxStoreSeckill> implements YxStoreSeckillService {

    @Autowired
    private IGenerator generator;

    @Autowired
    private YxStoreSeckillMapper yxStoreSeckillMapper;

    @Autowired
    private YxStoreProductReplyService replyService;

    @Autowired
    private YxStoreProductAttrService yxStoreProductAttrService;

    @Autowired
    private YxShippingTemplatesService shippingTemplatesService;

    /**
     * 产品详情
     * @param id 砍价商品id
     * @return StoreSeckillVo
     */
    @Override
    public StoreSeckillVo getDetail(Long id){
        Date now = new Date();
        YxStoreSeckill storeSeckill = this.lambdaQuery().eq(YxStoreSeckill::getId,id)
                .eq(YxStoreSeckill::getStatus, ShopCommonEnum.IS_STATUS_1.getValue())
                .eq(YxStoreSeckill::getIsShow,ShopCommonEnum.SHOW_1.getValue())
                .le(YxStoreSeckill::getStartTime,now)
                .ge(YxStoreSeckill::getStopTime,now)
                .one();

        if(storeSeckill == null){
            throw new YshopException("秒杀产品不存在或已下架");
        }
        //获取商品sku
        Map<String, Object> returnMap = yxStoreProductAttrService.getProductAttrDetail(storeSeckill.getProductId());
        //获取运费模板名称
        String storeFreePostage = RedisUtil.get("store_free_postage");
        String tempName = "";
        if(StrUtil.isBlank(storeFreePostage)
                || !NumberUtil.isNumber(storeFreePostage)
                || Integer.parseInt(storeFreePostage) == 0){
            tempName = "全国包邮";
        }else{
            YxShippingTemplates shippingTemplates = shippingTemplatesService.getById(storeSeckill.getTempId());
            if(ObjectUtil.isNotNull(shippingTemplates)){
                tempName = shippingTemplates.getName();
            }else {
                throw new BadRequestException("请配置运费模板");
            }

        }
        return StoreSeckillVo.builder()
                .productAttr((List<YxStoreProductAttrQueryVo>)returnMap.get("productAttr"))
                .productValue((Map<String, YxStoreProductAttrValue>)returnMap.get("productValue"))
                .storeInfo(generator.convert(storeSeckill, YxStoreSeckillQueryVo.class))
                .reply(replyService.getReply(storeSeckill.getProductId()))
                .replyCount(replyService.productReplyCount(storeSeckill.getProductId()))
                .tempName(tempName)
                .build();
    }

    /**
     * 秒杀产品列表
     * @param page page
     * @param limit limit
     * @return list
     */
    @Override
    public List<YxStoreSeckillQueryVo> getList(int page, int limit, int time) {
        Date nowTime = new Date();
        Page<YxStoreSeckill> pageModel = new Page<>(page, limit);
       LambdaQueryWrapper<YxStoreSeckill> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(YxStoreSeckill::getStatus, ShopCommonEnum.IS_STATUS_1.getValue())
                .eq(YxStoreSeckill::getTimeId,time)
                .le(YxStoreSeckill::getStartTime,nowTime)
                .ge(YxStoreSeckill::getStopTime,nowTime)
                .orderByDesc(YxStoreSeckill::getSort);
        List<YxStoreSeckillQueryVo> yxStoreSeckillQueryVos = generator.convert
               (yxStoreSeckillMapper.selectPage(pageModel,wrapper).getRecords(),
                       YxStoreSeckillQueryVo.class);
        yxStoreSeckillQueryVos.forEach(item->{
            Integer sum = item.getSales() + item.getStock();
            item.setPercent(NumberUtil.round(NumberUtil.mul(NumberUtil.div(item.getSales(),sum),
                    100),0).intValue());
        });
        return yxStoreSeckillQueryVos;
    }
    /**
     * 秒杀产品列表（首页用）
     * @param page page
     * @param limit limit
     * @return list
     */
    @Override
    public List<YxStoreSeckillQueryVo> getList(int page, int limit) {
        Date nowTime = new Date();
        Page<YxStoreSeckill> pageModel = new Page<>(page, limit);
       LambdaQueryWrapper<YxStoreSeckill> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(YxStoreSeckill::getStatus, ShopCommonEnum.IS_STATUS_1.getValue())
                .eq(YxStoreSeckill::getIsHot,1)
                .le(YxStoreSeckill::getStartTime,nowTime)
                .ge(YxStoreSeckill::getStopTime,nowTime)
                .orderByDesc(YxStoreSeckill::getSort);
        List<YxStoreSeckillQueryVo> yxStoreSeckillQueryVos = generator.convert
                (yxStoreSeckillMapper.selectPage(pageModel,wrapper).getRecords(),
                        YxStoreSeckillQueryVo.class);
        yxStoreSeckillQueryVos.forEach(item->{
            Integer sum = item.getSales() + item.getStock();
            item.setPercent(NumberUtil.round(NumberUtil.mul(NumberUtil.div(item.getSales(),sum),
                    100),0).intValue());
        });
        return yxStoreSeckillQueryVos;
    }
    @Override
    //@Cacheable
    public Map<String, Object> queryAll(YxStoreSeckillQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<YxStoreSeckill> page = new PageInfo<>(queryAll(criteria));
        List<YxStoreSeckillDto> storeSeckillDTOS = generator.convert(page.getList(),YxStoreSeckillDto.class);
        for (YxStoreSeckillDto storeSeckillDTO : storeSeckillDTOS){
            String statusStr = OrderUtil.checkActivityStatus(storeSeckillDTO.getStartTime(),
                    storeSeckillDTO.getStopTime(), storeSeckillDTO.getStatus());
            storeSeckillDTO.setStatusStr(statusStr);
        }
        Map<String,Object> map = new LinkedHashMap<>(2);
        map.put("content",storeSeckillDTOS);
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<YxStoreSeckill> queryAll(YxStoreSeckillQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(YxStoreSeckill.class, criteria));
    }


    @Override
    public void download(List<YxStoreSeckillDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxStoreSeckillDto yxStoreSeckill : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("推荐图", yxStoreSeckill.getImage());
            map.put("轮播图", yxStoreSeckill.getImages());
            map.put("活动标题", yxStoreSeckill.getTitle());
            map.put("简介", yxStoreSeckill.getInfo());
            map.put("返多少积分", yxStoreSeckill.getGiveIntegral());
            map.put("排序", yxStoreSeckill.getSort());
            map.put("库存", yxStoreSeckill.getStock());
            map.put("销量", yxStoreSeckill.getSales());
            map.put("单位名", yxStoreSeckill.getUnitName());
            map.put("邮费", yxStoreSeckill.getPostage());
            map.put("内容", yxStoreSeckill.getDescription());
            map.put("开始时间", yxStoreSeckill.getStartTime());
            map.put("结束时间", yxStoreSeckill.getStopTime());
            map.put("产品状态", yxStoreSeckill.getStatus());
            map.put("是否包邮", yxStoreSeckill.getIsPostage());
            map.put("热门推荐", yxStoreSeckill.getIsHot());
            map.put("最多秒杀几个", yxStoreSeckill.getNum());
            map.put("显示", yxStoreSeckill.getIsShow());
            map.put("时间段id", yxStoreSeckill.getTimeId());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public boolean saveSeckill(YxStoreSeckillDto resources) {
        ProductResultDto resultDTO = this.computedProduct(resources.getAttrs());

        //添加商品
        YxStoreSeckill yxStoreSeckill = new YxStoreSeckill();
        BeanUtil.copyProperties(resources,yxStoreSeckill,"images");
        if(resources.getImages().isEmpty()) {
            throw new YshopException("请上传轮播图");
        }

        yxStoreSeckill.setStock(resultDTO.getStock());
        yxStoreSeckill.setOtPrice(BigDecimal.valueOf(resultDTO.getMinOtPrice()));
        yxStoreSeckill.setPrice(BigDecimal.valueOf(resultDTO.getMinPrice()));
        yxStoreSeckill.setCost(BigDecimal.valueOf(resultDTO.getMinCost()));
        yxStoreSeckill.setStock(resultDTO.getStock());
        yxStoreSeckill.setImages(String.join(",", resources.getImages()));
        this.saveOrUpdate(yxStoreSeckill);

        //属性处理
        //处理单sKu
        if(SpecTypeEnum.TYPE_0.getValue().equals(resources.getSpecType())){
            FromatDetailDto fromatDetailDto = FromatDetailDto.builder()
                    .value("规格")
                    .detailValue("")
                    .attrHidden("")
                    .detail(ListUtil.toList("默认"))
                    .build();
            List<ProductFormatDto> attrs = resources.getAttrs();
            ProductFormatDto productFormatDto = attrs.get(0);
            productFormatDto.setValue1("规格");
            Map<String,String> map = new HashMap<>();
            map.put("规格","默认");
            productFormatDto.setDetail(map);
            yxStoreProductAttrService.insertYxStoreProductAttr(ListUtil.toList(fromatDetailDto),
                    ListUtil.toList(productFormatDto),resources.getProductId());
        }else{
            yxStoreProductAttrService.insertYxStoreProductAttr(resources.getItems(),
                    resources.getAttrs(),resources.getProductId());
        }
        return true;
    }

    /**
     * 计算产品数据
     * @param attrs attrs
     * @return ProductResultDto
     */
    private ProductResultDto computedProduct(List<ProductFormatDto> attrs){
        //取最小价格
        Double minPrice = attrs
                .stream()
                .map(ProductFormatDto::getSeckillPrice)
                .min(Comparator.naturalOrder())
                .orElse(0d);

        Double minOtPrice = attrs
                .stream()
                .map(ProductFormatDto::getOtPrice)
                .min(Comparator.naturalOrder())
                .orElse(0d);

        Double minCost = attrs
                .stream()
                .map(ProductFormatDto::getCost)
                .min(Comparator.naturalOrder())
                .orElse(0d);
        //计算库存
        Integer stock = attrs
                .stream()
                .map(ProductFormatDto::getSeckillStock)
                .reduce(Integer::sum)
                .orElse(0);

        if(stock <= 0) {
            throw new YshopException("库存不能低于0");
        }

        return ProductResultDto.builder()
                .minPrice(minPrice)
                .minOtPrice(minOtPrice)
                .minCost(minCost)
                .stock(stock)
                .build();
    }

}
