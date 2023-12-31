/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.product.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import co.yixiang.api.YshopException;
import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.common.utils.QueryHelpPlus;
import co.yixiang.constant.ShopConstants;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.enums.CommonEnum;
import co.yixiang.enums.ProductEnum;
import co.yixiang.enums.ProductTypeEnum;
import co.yixiang.enums.ShopCommonEnum;
import co.yixiang.enums.SortEnum;
import co.yixiang.enums.SpecTypeEnum;
import co.yixiang.exception.BadRequestException;
import co.yixiang.exception.ErrorRequestException;
import co.yixiang.modules.category.service.YxStoreCategoryService;
import co.yixiang.modules.product.domain.YxStoreProduct;
import co.yixiang.modules.product.domain.YxStoreProductAttrValue;
import co.yixiang.modules.product.domain.YxStoreProductRelation;
import co.yixiang.modules.product.param.YxStoreProductQueryParam;
import co.yixiang.modules.product.service.YxStoreProductAttrService;
import co.yixiang.modules.product.service.YxStoreProductAttrValueService;
import co.yixiang.modules.product.service.YxStoreProductRelationService;
import co.yixiang.modules.product.service.YxStoreProductReplyService;
import co.yixiang.modules.product.service.YxStoreProductService;
import co.yixiang.modules.product.service.dto.DetailDto;
import co.yixiang.modules.product.service.dto.FromatDetailDto;
import co.yixiang.modules.product.service.dto.ProductFormatDto;
import co.yixiang.modules.product.service.dto.ProductResultDto;
import co.yixiang.modules.product.service.dto.StoreProductDto;
import co.yixiang.modules.product.service.dto.YxStoreProductDto;
import co.yixiang.modules.product.service.dto.YxStoreProductQueryCriteria;
import co.yixiang.modules.product.service.mapper.StoreProductMapper;
import co.yixiang.modules.product.vo.ProductVo;
import co.yixiang.modules.product.vo.YxStoreProductAttrQueryVo;
import co.yixiang.modules.product.vo.YxStoreProductQueryVo;
import co.yixiang.modules.product.vo.YxStoreProductReplyQueryVo;
import co.yixiang.modules.shop.service.YxSystemStoreService;
import co.yixiang.modules.template.domain.YxShippingTemplates;
import co.yixiang.modules.template.service.YxShippingTemplatesService;
import co.yixiang.modules.user.service.YxUserService;
import co.yixiang.utils.FileUtil;
import co.yixiang.utils.RedisUtil;
import co.yixiang.utils.RegexUtil;
import co.yixiang.utils.ShopKeyUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.qiniu.util.StringUtils;
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
 * @date 2020-05-12
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class YxStoreProductServiceImpl extends BaseServiceImpl<StoreProductMapper, YxStoreProduct> implements YxStoreProductService {

    @Autowired
    private IGenerator generator;
    @Autowired
    private StoreProductMapper storeProductMapper;
    @Autowired
    private YxStoreCategoryService yxStoreCategoryService;
    @Autowired
    private YxStoreProductAttrService yxStoreProductAttrService;
    @Autowired
    private YxStoreProductAttrValueService yxStoreProductAttrValueService;
    @Autowired
    private YxUserService userService;
    @Autowired
    private YxStoreProductReplyService replyService;
    @Autowired
    private YxStoreProductRelationService relationService;
    @Autowired
    private YxSystemStoreService systemStoreService;
    @Autowired
    private YxShippingTemplatesService shippingTemplatesService;


    /**
     * 增加库存 减少销量
     *
     * @param num       数量
     * @param productId 商品id
     * @param unique    sku唯一值
     */
    @Override
    public void incProductStock(Integer num, Long productId, String unique, Long activityId, String type) {
        //处理属性sku
        if (StrUtil.isNotEmpty(unique)) {
            yxStoreProductAttrService.incProductAttrStock(num, productId, unique, type);
        }
        //先处理商品库存，活动商品也要处理，因为共享库存
        storeProductMapper.incStockDecSales(num, productId);
        //处理商品外层显示的库存
        if (ProductTypeEnum.COMBINATION.getValue().equals(type)) {
            storeProductMapper.incCombinationStockIncSales(num, productId, activityId);
        } else if (ProductTypeEnum.SECKILL.getValue().equals(type)) {
            storeProductMapper.incSeckillStockIncSales(num, productId, activityId);
        }
        //todo 处理砍价商品库存
    }

    /**
     * 减少库存与增加销量
     *
     * @param num       数量
     * @param productId 商品id
     * @param unique    sku
     */
    @Override
    public void decProductStock(int num, Long productId, String unique, Long activityId, String type) {
        //处理属性sku
        if (StrUtil.isNotEmpty(unique)) {
            yxStoreProductAttrService.decProductAttrStock(num, productId, unique, type);
        }
        //先处理商品库存，活动商品也要处理，因为共享库存
        int product = storeProductMapper.decStockIncSales(num, productId);
        if (product == 0) {
            throw new YshopException("共享商品库存不足");
        }
        //处理商品外层显示的库存
        if (ProductTypeEnum.COMBINATION.getValue().equals(type)) {
            int combinationRes = storeProductMapper.decCombinationStockIncSales(num, productId, activityId);
            if (combinationRes == 0) {
                throw new YshopException("拼团商品库存不足");
            }
        } else if (ProductTypeEnum.SECKILL.getValue().equals(type)) {
            int seckillRes = storeProductMapper.decSeckillStockIncSales(num, productId, activityId);
            if (seckillRes == 0) {
                throw new YshopException("秒杀商品库存不足");
            }
        }
        //todo 处理砍价库存

    }


    @Override
    public YxStoreProduct getProductInfo(Long id) {
        LambdaQueryWrapper<YxStoreProduct> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(YxStoreProduct::getIsShow, 1).eq(YxStoreProduct::getId, id);
        YxStoreProduct storeProduct = this.baseMapper.selectOne(wrapper);
        if (ObjectUtil.isNull(storeProduct)) {
            throw new ErrorRequestException("商品不存在或已下架");
        }

        return storeProduct;
    }


    /**
     * 获取单个商品
     *
     * @param id 商品id
     * @return YxStoreProductQueryVo
     */
    @Override
    public YxStoreProductQueryVo getStoreProductById(Long id) {
        return generator.convert(this.baseMapper.selectById(id), YxStoreProductQueryVo.class);
    }


    /**
     * 返回普通商品库存
     *
     * @param productId 商品id
     * @param unique    sku唯一值
     * @return int
     */
    @Override
    public int getProductStock(Long productId, String unique, String type) {
        YxStoreProductAttrValue storeProductAttrValue = yxStoreProductAttrValueService
                .getOne(Wrappers.<YxStoreProductAttrValue>lambdaQuery()
                        .eq(YxStoreProductAttrValue::getUnique, unique)
                        .eq(YxStoreProductAttrValue::getProductId, productId));

        if (storeProductAttrValue == null) {
            return 0;
        }
        if (ProductTypeEnum.PINK.getValue().equals(type)) {
            return storeProductAttrValue.getPinkStock();
        } else if (ProductTypeEnum.SECKILL.getValue().equals(type)) {
            return storeProductAttrValue.getSeckillStock();
        }
        return storeProductAttrValue.getStock();

    }


    /**
     * 商品列表
     *
     * @param productQueryParam YxStoreProductQueryParam
     * @return list
     */
    @Override
    public List<YxStoreProductQueryVo> getGoodsList(YxStoreProductQueryParam productQueryParam) {

        LambdaQueryWrapper<YxStoreProduct> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(YxStoreProduct::getIsShow, CommonEnum.SHOW_STATUS_1.getValue());
        wrapper.eq(YxStoreProduct::getIsDel, CommonEnum.DEL_STATUS_0.getValue());
//        wrapper.eq(YxStoreProduct::getIsIntegral, CommonEnum.SHOW_STATUS_1.getValue());

        if(productQueryParam.getIsIntegral()!=null){
            wrapper.eq(YxStoreProduct::getIsIntegral, productQueryParam.getIsIntegral());
        }
        //多字段模糊查询分类搜索
        if (StrUtil.isNotBlank(productQueryParam.getSid()) &&
                !ShopConstants.YSHOP_ZERO.equals(productQueryParam.getSid())) {
            wrapper.eq(YxStoreProduct::getCateId, productQueryParam.getSid());
        }
        //关键字搜索
        if (StrUtil.isNotEmpty(productQueryParam.getKeyword())) {
            wrapper.and(wrapper1 -> {
                wrapper1.or();
                wrapper1.like(YxStoreProduct::getStoreName, productQueryParam.getKeyword());
                wrapper1.or();
                wrapper1.like(YxStoreProduct::getStoreInfo, productQueryParam.getKeyword());
                wrapper1.or();
                wrapper1.like(YxStoreProduct::getKeyword, productQueryParam.getKeyword());
            });
        }
        //新品搜索
        if (StrUtil.isNotBlank(productQueryParam.getNews()) &&
                !ShopConstants.YSHOP_ZERO.equals(productQueryParam.getNews())) {
            wrapper.eq(YxStoreProduct::getIsNew, ShopCommonEnum.IS_NEW_1.getValue());
        }

        //销量排序
        if (SortEnum.DESC.getValue().equals(productQueryParam.getSalesOrder())) {
            wrapper.orderByDesc(YxStoreProduct::getSales);
        } else if (SortEnum.ASC.getValue().equals(productQueryParam.getSalesOrder())) {
            wrapper.orderByAsc(YxStoreProduct::getSales);
        }

        //价格排序
        if (SortEnum.DESC.getValue().equals(productQueryParam.getPriceOrder())) {
            wrapper.orderByDesc(YxStoreProduct::getPrice);
        } else if (SortEnum.ASC.getValue().equals(productQueryParam.getPriceOrder())) {
            wrapper.orderByAsc(YxStoreProduct::getPrice);
        }

        //无其他排序条件时,防止因为商品排序导致商品重复
        if (StringUtils.isNullOrEmpty(productQueryParam.getPriceOrder()) && StringUtils.isNullOrEmpty(productQueryParam.getSalesOrder())) {
            wrapper.orderByDesc(YxStoreProduct::getId);
            wrapper.orderByDesc(YxStoreProduct::getSort);
        }
        Page<YxStoreProduct> pageModel = new Page<>(productQueryParam.getPage(),
                productQueryParam.getLimit());

        IPage<YxStoreProduct> pageList = storeProductMapper.selectPage(pageModel, wrapper);

        List<YxStoreProductQueryVo> list = generator.convert(pageList.getRecords(), YxStoreProductQueryVo.class);

        return list;
    }

    /**
     * 商品详情
     *
     * @param id        商品id
     * @param uid       用户id
     * @param latitude  纬度
     * @param longitude 经度
     * @return ProductVo
     */
    @Override
    public ProductVo goodsDetail(Long id, Long uid, String latitude, String longitude) {
        LambdaQueryWrapper<YxStoreProduct> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(YxStoreProduct::getIsShow, ShopCommonEnum.SHOW_1.getValue())
                .eq(YxStoreProduct::getId, id);
        YxStoreProduct storeProduct = storeProductMapper.selectOne(wrapper);
        if (ObjectUtil.isNull(storeProduct)) {
            throw new ErrorRequestException("商品不存在或已下架");
        }

        //获取商品sku
        Map<String, Object> returnMap = yxStoreProductAttrService.getProductAttrDetail(id);
        ProductVo productVo = new ProductVo();
        YxStoreProductQueryVo storeProductQueryVo = generator.convert(storeProduct, YxStoreProductQueryVo.class);

        //设置销量
        storeProductQueryVo.setSales(storeProductQueryVo.getSales() + storeProductQueryVo.getFicti());

        if (uid.longValue() > 0) {
            //设置VIP价格
            double vipPrice = userService.setLevelPrice(
                    storeProductQueryVo.getPrice().doubleValue(), uid);
            storeProductQueryVo.setVipPrice(BigDecimal.valueOf(vipPrice));

            //收藏
            boolean isCollect = relationService.isProductRelation(id, uid,ProductTypeEnum.PRODUCT.getValue());
            storeProductQueryVo.setUserCollect(isCollect);
        }
        //总条数
        Long totalCount = replyService.productReplyCount(id);
        productVo.setReplyCount(totalCount);

        //评价
        YxStoreProductReplyQueryVo storeProductReplyQueryVo = replyService.getReply(id);
        productVo.setReply(storeProductReplyQueryVo);

        //好评比例
        String replyPer = replyService.replyPer(id);
        productVo.setReplyChance(replyPer);

        //获取运费模板名称
        String storeFreePostage = RedisUtil.get("store_free_postage");
        String tempName = "";
        if (StrUtil.isBlank(storeFreePostage)
                || !NumberUtil.isNumber(storeFreePostage)
                || Integer.valueOf(storeFreePostage) == 0) {
            tempName = "全国包邮";
        } else {
            YxShippingTemplates shippingTemplates = shippingTemplatesService.getById(storeProduct.getTempId());
            if (ObjectUtil.isNotNull(shippingTemplates)) {
                tempName = shippingTemplates.getName();
            } else {
                throw new BadRequestException("请配置运费模板");
            }

        }
        productVo.setTempName(tempName);

        //设置商品相关信息
        productVo.setStoreInfo(storeProductQueryVo);
        productVo.setProductAttr((List<YxStoreProductAttrQueryVo>) returnMap.get("productAttr"));
        productVo.setProductValue((Map<String, YxStoreProductAttrValue>) returnMap.get("productValue"));


        //门店
        productVo.setSystemStore(systemStoreService.getStoreInfo(latitude, longitude));
        productVo.setMapKey(RedisUtil.get(ShopKeyUtils.getTengXunMapKey()));
        if (uid.longValue() > 0) {
            //添加足迹
            YxStoreProductRelation foot = relationService.getOne(new LambdaQueryWrapper<YxStoreProductRelation>()
                    .eq(YxStoreProductRelation::getUid, uid)
                    .eq(YxStoreProductRelation::getProductId, storeProductQueryVo.getId())
                    .eq(YxStoreProductRelation::getCategory,ProductTypeEnum.PRODUCT.getValue())
                    .eq(YxStoreProductRelation::getType, "foot"));

            if (ObjectUtil.isNotNull(foot)) {
                foot.setCreateTime(new Date());
                relationService.saveOrUpdate(foot);
            } else {
                YxStoreProductRelation storeProductRelation = new YxStoreProductRelation();
                storeProductRelation.setProductId(storeProductQueryVo.getId());
                storeProductRelation.setUid(uid);
                storeProductRelation.setCreateTime(new Date());
                storeProductRelation.setType("foot");
                storeProductRelation.setCategory(ProductTypeEnum.PRODUCT.getValue());
                relationService.save(storeProductRelation);
            }
        }

        return productVo;
    }

    /**
     * 商品浏览量
     *
     * @param productId
     */
    @Override
    public void incBrowseNum(Long productId) {
        storeProductMapper.incBrowseNum(productId);
    }


    /**
     * 商品列表
     *
     * @param page  页码
     * @param limit 条数
     * @param order ProductEnum
     * @return List
     */
    @Override
    public List<YxStoreProductQueryVo> getList(int page, int limit, int order) {

        LambdaQueryWrapper<YxStoreProduct> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(YxStoreProduct::getIsShow, ShopCommonEnum.SHOW_1.getValue())
                .eq(YxStoreProduct::getIsDel,ShopCommonEnum.DELETE_0.getValue())
                .orderByDesc(YxStoreProduct::getSort);
        wrapper.eq(YxStoreProduct::getIsIntegral,0);
        // order
        switch (ProductEnum.toType(order)) {
            //精品推荐
            case TYPE_1:
                wrapper.eq(YxStoreProduct::getIsBest,
                        ShopCommonEnum.IS_STATUS_1.getValue());
                break;
            //首发新品
            case TYPE_3:
                wrapper.eq(YxStoreProduct::getIsNew,
                        ShopCommonEnum.IS_STATUS_1.getValue());
                break;
            // 猜你喜欢
            case TYPE_4:
                wrapper.eq(YxStoreProduct::getIsBenefit,
                        ShopCommonEnum.IS_STATUS_1.getValue());
                break;
            // 热门榜单
            case TYPE_2:
                wrapper.eq(YxStoreProduct::getIsHot,
                        ShopCommonEnum.IS_STATUS_1.getValue());
                break;
            default:
        }
        Page<YxStoreProduct> pageModel = new Page<>(page, limit);

        IPage<YxStoreProduct> pageList = storeProductMapper.selectPage(pageModel, wrapper);

        return generator.convert(pageList.getRecords(), YxStoreProductQueryVo.class);
    }


    //============ 分割线================//


    @Override
    public Map<String, Object> queryAll(YxStoreProductQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<YxStoreProduct> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), YxStoreProductDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    public List<YxStoreProduct> queryAll(YxStoreProductQueryCriteria criteria) {
        List<YxStoreProduct> yxStoreProductList = baseMapper.selectList(QueryHelpPlus.getPredicate(YxStoreProduct.class, criteria));
        yxStoreProductList.forEach(yxStoreProduct -> {
            yxStoreProduct.setStoreCategory(yxStoreCategoryService.getById(yxStoreProduct.getCateId()));
        });
        return yxStoreProductList;
    }


    @Override
    public void download(List<YxStoreProductDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (YxStoreProductDto yxStoreProduct : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("商户Id(0为总后台管理员创建,不为0的时候是商户后台创建)", yxStoreProduct.getMerId());
            map.put("商品图片", yxStoreProduct.getImage());
            map.put("轮播图", yxStoreProduct.getSliderImage());
            map.put("商品名称", yxStoreProduct.getStoreName());
            map.put("商品简介", yxStoreProduct.getStoreInfo());
            map.put("关键字", yxStoreProduct.getKeyword());
            map.put("产品条码（一维码）", yxStoreProduct.getBarCode());
            map.put("分类id", yxStoreProduct.getCateId());
            map.put("商品价格", yxStoreProduct.getPrice());
            map.put("会员价格", yxStoreProduct.getVipPrice());
            map.put("市场价", yxStoreProduct.getOtPrice());
            map.put("邮费", yxStoreProduct.getPostage());
            map.put("单位名", yxStoreProduct.getUnitName());
            map.put("排序", yxStoreProduct.getSort());
            map.put("销量", yxStoreProduct.getSales());
            map.put("库存", yxStoreProduct.getStock());
            map.put("状态（0：未上架，1：上架）", yxStoreProduct.getIsShow());
            map.put("是否热卖", yxStoreProduct.getIsHot());
            map.put("是否优惠", yxStoreProduct.getIsBenefit());
            map.put("是否精品", yxStoreProduct.getIsBest());
            map.put("是否新品", yxStoreProduct.getIsNew());
            map.put("产品描述", yxStoreProduct.getDescription());
            map.put("添加时间", yxStoreProduct.getAddTime());
            map.put("是否包邮", yxStoreProduct.getIsPostage());
            map.put("是否删除", yxStoreProduct.getIsDel());
            map.put("商户是否代理 0不可代理1可代理", yxStoreProduct.getMerUse());
            map.put("获得积分", yxStoreProduct.getGiveIntegral());
            map.put("成本价", yxStoreProduct.getCost());
            map.put("秒杀状态 0 未开启 1已开启", yxStoreProduct.getIsSeckill());
            map.put("砍价状态 0未开启 1开启", yxStoreProduct.getIsBargain());
            map.put("是否优品推荐", yxStoreProduct.getIsGood());
            map.put("虚拟销量", yxStoreProduct.getFicti());
            map.put("浏览量", yxStoreProduct.getBrowse());
            map.put("产品二维码地址(用户小程序海报)", yxStoreProduct.getCodePath());
            map.put("淘宝京东1688类型", yxStoreProduct.getSoureLink());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }


    /**
     * 商品上架下架
     *
     * @param id     商品id
     * @param status ShopCommonEnum
     */
    @Override
    public void onSale(Long id, Integer status) {
        if (ShopCommonEnum.SHOW_1.getValue().equals(status)) {
            status = ShopCommonEnum.SHOW_0.getValue();
        } else {
            status = ShopCommonEnum.SHOW_1.getValue();
        }
        storeProductMapper.updateOnsale(status, id);
    }


    /**
     * 新增/保存商品
     *
     * @param storeProductDto 商品
     */
    @Override
    public void insertAndEditYxStoreProduct(StoreProductDto storeProductDto) {
        storeProductDto.setDescription(RegexUtil.converProductDescription(storeProductDto.getDescription()));
        ProductResultDto resultDTO = this.computedProduct(storeProductDto.getAttrs());

        //添加商品
        YxStoreProduct yxStoreProduct = new YxStoreProduct();
        BeanUtil.copyProperties(storeProductDto, yxStoreProduct, "sliderImage");
        if (storeProductDto.getSliderImage().isEmpty()) {
            throw new YshopException("请上传轮播图");
        }

        yxStoreProduct.setPrice(BigDecimal.valueOf(resultDTO.getMinPrice()));
        yxStoreProduct.setOtPrice(BigDecimal.valueOf(resultDTO.getMinOtPrice()));
        yxStoreProduct.setCost(BigDecimal.valueOf(resultDTO.getMinCost()));
        yxStoreProduct.setIntegral(resultDTO.getMinIntegral());
        yxStoreProduct.setStock(resultDTO.getStock());
        yxStoreProduct.setSliderImage(String.join(",", storeProductDto.getSliderImage()));

        if (storeProductDto.getId() != null) {
            //清空商品转发图
            deleteForwardImg(storeProductDto.getId());
        }

        this.saveOrUpdate(yxStoreProduct);

        //属性处理
        //处理单sKu
        if (SpecTypeEnum.TYPE_0.getValue().equals(storeProductDto.getSpecType())) {
            FromatDetailDto fromatDetailDto = FromatDetailDto.builder()
                    .value("规格")
                    .detailValue("")
                    .attrHidden("")
                    .detail(ListUtil.toList("默认"))
                    .build();
            List<ProductFormatDto> attrs = storeProductDto.getAttrs();
            ProductFormatDto productFormatDto = attrs.get(0);
            productFormatDto.setValue1("规格");
            Map<String, String> map = new HashMap<>();
            map.put("规格", "默认");
            productFormatDto.setDetail(map);
            yxStoreProductAttrService.insertYxStoreProductAttr(ListUtil.toList(fromatDetailDto),
                    ListUtil.toList(productFormatDto), yxStoreProduct.getId());
        } else {
            yxStoreProductAttrService.insertYxStoreProductAttr(storeProductDto.getItems(),
                    storeProductDto.getAttrs(), yxStoreProduct.getId());
        }


    }


    /**
     * 获取生成的属性
     *
     * @param id      商品id
     * @param jsonStr jsonStr
     * @return map
     */
    @Override
    public Map<String, Object> getFormatAttr(Long id, String jsonStr, boolean isActivity) {
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        Map<String, Object> resultMap = new LinkedHashMap<>(3);

        if (jsonObject == null || jsonObject.get("attrs") == null || jsonObject.getJSONArray("attrs").isEmpty()) {
            resultMap.put("attr", new ArrayList<>());
            resultMap.put("value", new ArrayList<>());
            resultMap.put("header", new ArrayList<>());
            return resultMap;
        }


        List<FromatDetailDto> fromatDetailDTOList = JSON.parseArray(jsonObject.get("attrs").toString(),
                FromatDetailDto.class);

        //fromatDetailDTOList
        DetailDto detailDto = this.attrFormat(fromatDetailDTOList);

        List<Map<String, Object>> headerMapList = null;
        List<Map<String, Object>> valueMapList = new ArrayList<>();
        String align = "center";
        Map<String, Object> headerMap = new LinkedHashMap<>();
        for (Map<String, Map<String, String>> map : detailDto.getRes()) {
            Map<String, String> detail = map.get("detail");
            String[] detailArr = detail.values().toArray(new String[]{});
            Arrays.sort(detailArr);

            String sku = String.join(",", detailArr);

            Map<String, Object> valueMap = new LinkedHashMap<>();

            List<String> detailKeys =
                    detail.entrySet()
                            .stream()
                            .map(Map.Entry::getKey)
                            .collect(Collectors.toList());

            int i = 0;
            headerMapList = new ArrayList<>();
            for (String title : detailKeys) {
                headerMap.put("title", title);
                headerMap.put("minWidth", "130");
                headerMap.put("align", align);
                headerMap.put("key", "value" + (i + 1));
                headerMap.put("slot", "value" + (i + 1));
                headerMapList.add(ObjectUtil.clone(headerMap));
                i++;
            }

            String[] detailValues = detail.values().toArray(new String[]{});
            for (int j = 0; j < detailValues.length; j++) {
                String key = "value" + (j + 1);
                valueMap.put(key, detailValues[j]);
            }
//            /** 拼团属性对应的金额 */
//            private BigDecimal pinkPrice;
//
//            /** 秒杀属性对应的金额 */
//            private BigDecimal seckillPrice;
//            /** 拼团库存属性对应的库存 */
//            private Integer pinkStock;
//
//            private Integer seckillStock;
            valueMap.put("detail", detail);
            valueMap.put("sku", "");
            valueMap.put("pic", "");
            valueMap.put("price", 0);
            valueMap.put("cost", 0);
            valueMap.put("ot_price", 0);
            valueMap.put("stock", 0);
            valueMap.put("bar_code", "");
            valueMap.put("weight", 0);
            valueMap.put("volume", 0);
            valueMap.put("brokerage", 0);
            valueMap.put("brokerage_two", 0);
            valueMap.put("pink_price", 0);
            valueMap.put("seckill_price", 0);
            valueMap.put("pink_stock", 0);
            valueMap.put("seckill_stock", 0);
            valueMap.put("integral", 0);
            if (id > 0) {
                YxStoreProductAttrValue storeProductAttrValue = yxStoreProductAttrValueService
                        .getOne(Wrappers.<YxStoreProductAttrValue>lambdaQuery()
                                .eq(YxStoreProductAttrValue::getProductId, id)
                                .eq(YxStoreProductAttrValue::getSku, sku));
                if (storeProductAttrValue != null) {
                    valueMap.put("sku",storeProductAttrValue.getSku());
                    valueMap.put("pic", storeProductAttrValue.getImage());
                    valueMap.put("price", storeProductAttrValue.getPrice());
                    valueMap.put("cost", storeProductAttrValue.getCost());
                    valueMap.put("ot_price", storeProductAttrValue.getOtPrice());
                    valueMap.put("stock", storeProductAttrValue.getStock());
                    valueMap.put("bar_code", storeProductAttrValue.getBarCode());
                    valueMap.put("weight", storeProductAttrValue.getWeight());
                    valueMap.put("volume", storeProductAttrValue.getVolume());
                    valueMap.put("brokerage", storeProductAttrValue.getBrokerage());
                    valueMap.put("brokerage_two", storeProductAttrValue.getBrokerageTwo());
                    valueMap.put("pink_price", storeProductAttrValue.getPinkPrice());
                    valueMap.put("seckill_price", storeProductAttrValue.getSeckillPrice());
                    valueMap.put("pink_stock", storeProductAttrValue.getPinkStock());
                    valueMap.put("seckill_stock", storeProductAttrValue.getSeckillStock());
                    valueMap.put("integral", storeProductAttrValue.getIntegral());
                }
            }

            valueMapList.add(ObjectUtil.clone(valueMap));

        }

        this.addMap(headerMap, headerMapList, align, isActivity);


        resultMap.put("attr", fromatDetailDTOList);
        resultMap.put("value", valueMapList);
        resultMap.put("header", headerMapList);

        return resultMap;
    }


    /**
     * 计算产品数据
     *
     * @param attrs attrs
     * @return ProductResultDto
     */
    private ProductResultDto computedProduct(List<ProductFormatDto> attrs) {
        //取最小价格
        Double minPrice = attrs
                .stream()
                .map(ProductFormatDto::getPrice)
                .min(Comparator.naturalOrder())
                .orElse(0d);

        //取最小积分
        Integer minIntegral = attrs
                .stream()
                .map(ProductFormatDto::getIntegral)
                .min(Comparator.naturalOrder())
                .orElse(0);

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
                .map(ProductFormatDto::getStock)
                .reduce(Integer::sum)
                .orElse(0);

        if (stock <= 0) {
            throw new YshopException("库存不能低于0");
        }

        return ProductResultDto.builder()
                .minPrice(minPrice)
                .minOtPrice(minOtPrice)
                .minCost(minCost)
                .stock(stock)
                .minIntegral(minIntegral)
                .build();
    }

    /**
     * mapTobean
     *
     * @param listMap listMap
     * @return list
     */
    private List<ProductFormatDto> ListMapToListBean(List<Map<String, Object>> listMap) {
        List<ProductFormatDto> list = new ArrayList<>();
        // 循环遍历出map对象
        for (Map<String, Object> m : listMap) {
            list.add(BeanUtil.mapToBean(m, ProductFormatDto.class, true));
        }
        return list;
    }

    /**
     * 增加表头
     *
     * @param headerMap     headerMap
     * @param headerMapList headerMapList
     * @param align         align
     */
    private void addMap(Map<String, Object> headerMap, List<Map<String, Object>> headerMapList, String align, boolean isActivity) {
        headerMap.put("title", "图片");
        headerMap.put("slot", "pic");
        headerMap.put("align", align);
        headerMap.put("minWidth", 80);
        headerMapList.add(ObjectUtil.clone(headerMap));

        headerMap.put("title", "售价");
        headerMap.put("slot", "price");
        headerMap.put("align", align);
        headerMap.put("minWidth", 120);
        headerMapList.add(ObjectUtil.clone(headerMap));

        headerMap.put("title", "成本价");
        headerMap.put("slot", "cost");
        headerMap.put("align", align);
        headerMap.put("minWidth", 140);
        headerMapList.add(ObjectUtil.clone(headerMap));

        headerMap.put("title", "原价");
        headerMap.put("slot", "ot_price");
        headerMap.put("align", align);
        headerMap.put("minWidth", 140);
        headerMapList.add(ObjectUtil.clone(headerMap));

        headerMap.put("title", "库存");
        headerMap.put("slot", "stock");
        headerMap.put("align", align);
        headerMap.put("minWidth", 140);
        headerMapList.add(ObjectUtil.clone(headerMap));

        headerMap.put("title", "产品编号");
        headerMap.put("slot", "bar_code");
        headerMap.put("align", align);
        headerMap.put("minWidth", 140);
        headerMapList.add(ObjectUtil.clone(headerMap));

        headerMap.put("title", "重量(KG)");
        headerMap.put("slot", "weight");
        headerMap.put("align", align);
        headerMap.put("minWidth", 140);
        headerMapList.add(ObjectUtil.clone(headerMap));

        headerMap.put("title", "体积(m³)");
        headerMap.put("slot", "volume");
        headerMap.put("align", align);
        headerMap.put("minWidth", 140);
        headerMapList.add(ObjectUtil.clone(headerMap));

        headerMap.put("title", "所需兑换积分");
        headerMap.put("slot", "integral");
        headerMap.put("align", align);
        headerMap.put("minWidth", 140);
        headerMapList.add(ObjectUtil.clone(headerMap));

        if (isActivity) {
            headerMap.put("title", "拼团价");
            headerMap.put("slot", "pink_price");
            headerMap.put("align", align);
            headerMap.put("minWidth", 140);
            headerMapList.add(ObjectUtil.clone(headerMap));

            headerMap.put("title", "拼团活动库存");
            headerMap.put("slot", "pink_stock");
            headerMap.put("align", align);
            headerMap.put("minWidth", 140);
            headerMapList.add(ObjectUtil.clone(headerMap));

            headerMap.put("title", "秒杀价");
            headerMap.put("slot", "seckill_price");
            headerMap.put("align", align);
            headerMap.put("minWidth", 140);
            headerMapList.add(ObjectUtil.clone(headerMap));

            headerMap.put("title", "秒杀活动库存");
            headerMap.put("slot", "seckill_stock");
            headerMap.put("align", align);
            headerMap.put("minWidth", 140);
            headerMapList.add(ObjectUtil.clone(headerMap));
        }

        headerMap.put("title", "操作");
        headerMap.put("slot", "action");
        headerMap.put("align", align);
        headerMap.put("minWidth", 70);
        headerMapList.add(ObjectUtil.clone(headerMap));
    }

    /**
     * 组合规则属性算法
     *
     * @param fromatDetailDTOList
     * @return DetailDto
     */
    private DetailDto attrFormat(List<FromatDetailDto> fromatDetailDTOList) {

        List<String> data = new ArrayList<>();
        List<Map<String, Map<String, String>>> res = new ArrayList<>();

        fromatDetailDTOList.stream()
                .map(FromatDetailDto::getDetail)
                .forEach(i -> {
                    if (i == null || i.isEmpty()) {
                        throw new YshopException("请至少添加一个规格值哦");
                    }
                    String str = ArrayUtil.join(i.toArray(), ",");
                    if (str.contains("-")) {
                        throw new YshopException("规格值里包含'-',请重新添加");
                    }
                });

        if (fromatDetailDTOList.size() > 1) {
            for (int i = 0; i < fromatDetailDTOList.size() - 1; i++) {
                if (i == 0) {
                    data = fromatDetailDTOList.get(i).getDetail();
                }
                List<String> tmp = new LinkedList<>();
                for (String v : data) {
                    for (String g : fromatDetailDTOList.get(i + 1).getDetail()) {
                        String rep2 = "";
                        if (i == 0) {
                            rep2 = fromatDetailDTOList.get(i).getValue() + "_" + v + "-"
                                    + fromatDetailDTOList.get(i + 1).getValue() + "_" + g;
                        } else {
                            rep2 = v + "-"
                                    + fromatDetailDTOList.get(i + 1).getValue() + "_" + g;
                        }

                        tmp.add(rep2);

                        if (i == fromatDetailDTOList.size() - 2) {
                            Map<String, Map<String, String>> rep4 = new LinkedHashMap<>();
                            Map<String, String> reptemp = new LinkedHashMap<>();
                            for (String h : Arrays.asList(rep2.split("-"))) {
                                List<String> rep3 = Arrays.asList(h.split("_"));
                                if (rep3.size() > 1) {
                                    reptemp.put(rep3.get(0), rep3.get(1));
                                } else {
                                    reptemp.put(rep3.get(0), "");
                                }
                            }
                            rep4.put("detail", reptemp);

                            res.add(rep4);
                        }
                    }

                }

                if (!tmp.isEmpty()) {
                    data = tmp;
                }
            }
        } else {
            List<String> dataArr = new ArrayList<>();
            for (FromatDetailDto fromatDetailDTO : fromatDetailDTOList) {
                for (String str : fromatDetailDTO.getDetail()) {
                    Map<String, Map<String, String>> map2 = new LinkedHashMap<>();
                    dataArr.add(fromatDetailDTO.getValue() + "_" + str);
                    Map<String, String> map1 = new LinkedHashMap<>();
                    map1.put(fromatDetailDTO.getValue(), str);
                    map2.put("detail", map1);
                    res.add(map2);
                }
            }
            String s = StrUtil.join("-", dataArr);
            data.add(s);
        }

        DetailDto detailDto = new DetailDto();
        detailDto.setData(data);
        detailDto.setRes(res);

        return detailDto;
    }

    @Override
    public void deleteForwardImg(Long id) {
        baseMapper.deleteForwardImg(id, "_product_detail_wap");
    }

}
