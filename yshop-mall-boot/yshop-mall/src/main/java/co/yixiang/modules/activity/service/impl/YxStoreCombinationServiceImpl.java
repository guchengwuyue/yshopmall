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
import co.yixiang.enums.ProductTypeEnum;
import co.yixiang.enums.ShopCommonEnum;
import co.yixiang.enums.SpecTypeEnum;
import co.yixiang.exception.BadRequestException;
import co.yixiang.modules.activity.domain.YxStoreCombination;
import co.yixiang.modules.activity.domain.YxStorePink;
import co.yixiang.modules.activity.domain.YxStoreSeckill;
import co.yixiang.modules.activity.domain.YxStoreVisit;
import co.yixiang.modules.activity.service.YxStoreCombinationService;
import co.yixiang.modules.activity.service.YxStorePinkService;
import co.yixiang.modules.activity.service.dto.PinkAllDto;
import co.yixiang.modules.activity.service.dto.YxStoreCombinationDto;
import co.yixiang.modules.activity.service.dto.YxStoreCombinationQueryCriteria;
import co.yixiang.modules.activity.service.mapper.YxStoreCombinationMapper;
import co.yixiang.modules.activity.service.mapper.YxStorePinkMapper;
import co.yixiang.modules.activity.service.mapper.YxStoreVisitMapper;
import co.yixiang.modules.activity.vo.CombinationQueryVo;
import co.yixiang.modules.activity.vo.StoreCombinationVo;
import co.yixiang.modules.activity.vo.YxStoreCombinationQueryVo;
import co.yixiang.modules.product.domain.YxStoreProduct;
import co.yixiang.modules.product.domain.YxStoreProductAttrValue;
import co.yixiang.modules.product.service.YxStoreProductAttrService;
import co.yixiang.modules.product.service.YxStoreProductAttrValueService;
import co.yixiang.modules.product.service.YxStoreProductReplyService;
import co.yixiang.modules.product.service.YxStoreProductService;
import co.yixiang.modules.product.service.dto.FromatDetailDto;
import co.yixiang.modules.product.service.dto.ProductFormatDto;
import co.yixiang.modules.product.service.dto.ProductResultDto;
import co.yixiang.modules.product.vo.YxStoreProductAttrQueryVo;
import co.yixiang.modules.template.domain.YxShippingTemplates;
import co.yixiang.modules.template.service.YxShippingTemplatesService;
import co.yixiang.utils.FileUtil;
import co.yixiang.utils.RedisUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


/**
* @author hupeng
* @date 2020-05-13
*/
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class YxStoreCombinationServiceImpl extends BaseServiceImpl<YxStoreCombinationMapper, YxStoreCombination> implements YxStoreCombinationService {

    @Autowired
    private IGenerator generator;

    @Autowired
    private YxStorePinkMapper yxStorePinkMapper;
    @Autowired
    private YxStoreVisitMapper yxStoreVisitMapper;

    @Autowired
    private YxStoreCombinationMapper yxStoreCombinationMapper;
    @Autowired
    private YxStoreProductReplyService replyService;
    @Autowired
    private YxStorePinkService storePinkService;
    @Autowired
    private YxStoreProductAttrService yxStoreProductAttrService;
    @Autowired
    private YxStoreProductAttrValueService yxStoreProductAttrValueService;
    @Autowired
    private YxShippingTemplatesService shippingTemplatesService;

    @Autowired
    private YxStoreProductService storeProductService;



    /**
     * 获取拼团详情
     * @param id 拼团产品id
     * @param uid uid
     * @return StoreCombinationVo
     */
    @Override
    public StoreCombinationVo getDetail(Long id, Long uid) {
        Date now = new Date();
        YxStoreCombination storeCombination = this
                .lambdaQuery().eq(YxStoreCombination::getId,id)
                .eq(YxStoreCombination::getIsShow, ShopCommonEnum.SHOW_1.getValue())
                .le(YxStoreCombination::getStartTime,now)
                .ge(YxStoreCombination::getStopTime,now)
                .one();
        if(storeCombination == null){
            throw new YshopException("拼团不存在或已下架");
        }
        //获取商品sku
        Map<String, Object> returnMap = yxStoreProductAttrService.getProductAttrDetail(storeCombination.getProductId());

        YxStoreCombinationQueryVo storeCombinationQueryVo = generator.convert(storeCombination,
                YxStoreCombinationQueryVo.class);

        StoreCombinationVo storeCombinationVo = new StoreCombinationVo();

        storeCombinationVo.setStoreInfo(storeCombinationQueryVo);

        //评价
        storeCombinationVo.setReply(replyService
                .getReply(storeCombinationQueryVo.getProductId()));
        Long replyCount = replyService.productReplyCount(storeCombinationQueryVo.getProductId());
        //总条数
        storeCombinationVo.setReplyCount(replyCount);
        //好评比例
        storeCombinationVo.setReplyChance(replyService.replyPer(storeCombinationQueryVo.getProductId()));

        //获取运费模板名称
        String storeFreePostage = RedisUtil.get("store_free_postage");
        String tempName = "";
        if(StrUtil.isBlank(storeFreePostage)
                || !NumberUtil.isNumber(storeFreePostage)
                || Integer.valueOf(storeFreePostage) == 0){
            tempName = "全国包邮";
        }else{
            YxShippingTemplates shippingTemplates = shippingTemplatesService.getById(storeCombination.getTempId());
            if(ObjectUtil.isNotNull(shippingTemplates)){
                tempName = shippingTemplates.getName();
            }else {
                throw new BadRequestException("请配置运费模板");
            }

        }
        storeCombinationVo.setTempName(tempName);

        PinkAllDto pinkAllDto = storePinkService.getPinkAll(id);
        storeCombinationVo.setPindAll(pinkAllDto.getPindAll());
        storeCombinationVo.setPink(pinkAllDto.getList());
        storeCombinationVo.setPinkOkList(storePinkService.getPinkOkList(uid));
        storeCombinationVo.setPinkOkSum(storePinkService.getPinkOkSumTotalNum());
        storeCombinationVo.setProductAttr((List<YxStoreProductAttrQueryVo>)returnMap.get("productAttr"));
        storeCombinationVo.setProductValue((Map<String, YxStoreProductAttrValue>)returnMap.get("productValue"));
        return storeCombinationVo;
    }

    /**
     * 拼团列表
     * @param page page
     * @param limit limit
     * @return list
     */
    @Override
    public CombinationQueryVo getList(int page, int limit) {
        CombinationQueryVo combinationQueryVo = new CombinationQueryVo();
        Date nowTime = new Date();
        Page<YxStoreCombination> pageModel = new Page<>(page, limit);
       LambdaQueryWrapper<YxStoreCombination> wrapper = new LambdaQueryWrapper<>();
        wrapper
                .eq(YxStoreCombination::getIsShow,1)
                .le(YxStoreCombination::getStartTime,nowTime)
                .ge(YxStoreCombination::getStopTime,nowTime)
                .orderByDesc(YxStoreCombination::getSort);
        IPage<YxStoreCombination> yxStoreCombinationIPage = yxStoreCombinationMapper.selectPage(pageModel, wrapper);

        List<YxStoreCombinationQueryVo> collect = yxStoreCombinationIPage.getRecords().stream().map(i -> {
            YxStoreCombinationQueryVo yxStoreCombinationQueryVo = new YxStoreCombinationQueryVo();
            BeanUtils.copyProperties(i, yxStoreCombinationQueryVo);
            return yxStoreCombinationQueryVo;
        }).collect(Collectors.toList());
        combinationQueryVo.setStoreCombinationQueryVos(collect);
        combinationQueryVo.setLastPage(yxStoreCombinationIPage.getPages());
        return combinationQueryVo;
    }


    //=======================================//

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(YxStoreCombinationQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<YxStoreCombination> page = new PageInfo<>(queryAll(criteria));

        List<YxStoreCombinationDto> combinationDTOS = generator.convert(page.getList(),YxStoreCombinationDto.class);
        for (YxStoreCombinationDto combinationDTO : combinationDTOS) {
            //参与人数
            combinationDTO.setCountPeopleAll(yxStorePinkMapper.selectCount(new LambdaQueryWrapper<YxStorePink>()
                    .eq(YxStorePink::getCid,combinationDTO.getId())));

            //成团人数
            combinationDTO.setCountPeoplePink(yxStorePinkMapper.selectCount(new LambdaQueryWrapper<YxStorePink>()
                    .eq(YxStorePink::getCid,combinationDTO.getId())
                    .eq(YxStorePink::getKId,0)));//团长
            //获取查看拼团产品人数
            combinationDTO.setCountPeopleBrowse(yxStoreVisitMapper.selectCount(new LambdaQueryWrapper<YxStoreVisit>()
                    .eq(YxStoreVisit::getProductId,combinationDTO.getId())
                    .eq(YxStoreVisit::getProductType, ProductTypeEnum.COMBINATION.getValue())));
        }
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content",combinationDTOS);
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<YxStoreCombination> queryAll(YxStoreCombinationQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(YxStoreCombination.class, criteria));
    }


    @Override
    public void download(List<YxStoreCombinationDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxStoreCombinationDto yxStoreCombination : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("商品id", yxStoreCombination.getProductId());
            map.put("推荐图", yxStoreCombination.getImage());
            map.put("轮播图", yxStoreCombination.getImages());
            map.put("活动标题", yxStoreCombination.getTitle());
            map.put("参团人数", yxStoreCombination.getPeople());
            map.put("简介", yxStoreCombination.getInfo());
            map.put("价格", yxStoreCombination.getPrice());
            map.put("排序", yxStoreCombination.getSort());
            map.put("销量", yxStoreCombination.getSales());
            map.put("库存", yxStoreCombination.getStock());
            map.put("推荐", yxStoreCombination.getIsHost());
            map.put("产品状态", yxStoreCombination.getIsShow());
            map.put(" combination",  yxStoreCombination.getCombination());
            map.put("商户是否可用1可用0不可用", yxStoreCombination.getMerUse());
            map.put("是否包邮1是0否", yxStoreCombination.getIsPostage());
            map.put("邮费", yxStoreCombination.getPostage());
            map.put("拼团内容", yxStoreCombination.getDescription());
            map.put("拼团开始时间", yxStoreCombination.getStartTime());
            map.put("拼团结束时间", yxStoreCombination.getStopTime());
            map.put("拼团订单有效时间", yxStoreCombination.getEffectiveTime());
            map.put("拼团产品成本", yxStoreCombination.getCost());
            map.put("浏览量", yxStoreCombination.getBrowse());
            map.put("单位名", yxStoreCombination.getUnitName());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    /**
     * 修改状态
     * @param id 拼团产品id
     * @param status ShopCommonEnum
     */
    @Override
    public void onSale(Long id, Integer status) {
        if(ShopCommonEnum.SHOW_1.getValue().equals(status)){
            status = ShopCommonEnum.SHOW_0.getValue();
        }else{
            status = ShopCommonEnum.SHOW_1.getValue();
        }
        YxStoreCombination yxStoreCombination = new YxStoreCombination();
        yxStoreCombination.setIsShow(status);
        yxStoreCombination.setId(id);
        this.saveOrUpdate(yxStoreCombination);
    }

    @Override
    public boolean saveCombination(YxStoreCombinationDto resources) {
        ProductResultDto resultDTO = this.computedProduct(resources.getAttrs());

        //添加商品
        YxStoreCombination yxStoreCombination = new YxStoreCombination();
        BeanUtil.copyProperties(resources,yxStoreCombination,"images");
        if(resources.getImages().isEmpty()) {
            throw new YshopException("请上传轮播图");
        }

        yxStoreCombination.setPrice(BigDecimal.valueOf(resultDTO.getMinPrice()));
        yxStoreCombination.setProductPrice(BigDecimal.valueOf(resultDTO.getMinOtPrice()));
        yxStoreCombination.setCost(resultDTO.getMinCost().intValue());
        yxStoreCombination.setStock(resultDTO.getStock());
        yxStoreCombination.setImages(String.join(",", resources.getImages()));
        this.saveOrUpdate(yxStoreCombination);

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
                .map(ProductFormatDto::getPinkPrice)
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
                .map(ProductFormatDto::getPinkStock)
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

    /**
     * mapTobean
     * @param listMap listMap
     * @return list
     */
    private List<ProductFormatDto> ListMapToListBean(List<Map<String, Object>> listMap){
        List<ProductFormatDto> list = new ArrayList<>();
        // 循环遍历出map对象
        for (Map<String, Object> m : listMap) {
            list.add(BeanUtil.mapToBean(m,ProductFormatDto.class,true));
        }
        return list;
    }
}
