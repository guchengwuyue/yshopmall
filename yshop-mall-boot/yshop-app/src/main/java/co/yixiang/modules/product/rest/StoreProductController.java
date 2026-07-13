/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.modules.product.rest;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import co.yixiang.api.ApiResult;
import co.yixiang.api.YshopException;
import co.yixiang.constant.ShopConstants;
import co.yixiang.modules.logging.aop.log.AppLog;
import co.yixiang.common.aop.NoRepeatSubmit;
import co.yixiang.common.bean.LocalUser;
import co.yixiang.common.interceptor.AuthCheck;
import co.yixiang.constant.SystemConfigConstants;
import co.yixiang.enums.AppFromEnum;
import co.yixiang.enums.ProductEnum;
import co.yixiang.enums.ShopCommonEnum;
import co.yixiang.modules.product.domain.YxStoreProduct;
import co.yixiang.modules.product.param.CollectDelFootParam;
import co.yixiang.modules.product.param.YxStoreProductQueryParam;
import co.yixiang.modules.product.param.YxStoreProductRelationQueryParam;
import co.yixiang.modules.product.service.YxStoreProductRelationService;
import co.yixiang.modules.product.service.YxStoreProductReplyService;
import co.yixiang.modules.product.service.YxStoreProductService;
import co.yixiang.modules.product.vo.ProductVo;
import co.yixiang.modules.product.vo.ReplyCountVo;
import co.yixiang.modules.product.vo.YxStoreProductQueryVo;
import co.yixiang.modules.product.vo.YxStoreProductReplyQueryVo;
import co.yixiang.modules.services.CreatShareProductService;
import co.yixiang.modules.shop.domain.YxSystemAttachment;
import co.yixiang.modules.shop.service.YxSystemAttachmentService;
import co.yixiang.modules.shop.service.YxSystemConfigService;
import co.yixiang.modules.user.domain.YxUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品控制器
 * </p>
 *
 * @author hupeng
 * @since 2019-10-19
 */
@Slf4j
@RestController
@Api(value = "产品模块", tags = "商城:产品模块")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StoreProductController {

    private final YxStoreProductService storeProductService;
    private final YxStoreProductRelationService productRelationService;
    private final YxStoreProductReplyService replyService;
    private final YxSystemConfigService systemConfigService;
    private final YxSystemAttachmentService systemAttachmentService;
    private final CreatShareProductService creatShareProductService;
    @Value("${file.path}")
    private String path;


    /**
     * 获取首页更多产品
     */
    @GetMapping("/groom/list/{type}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型：1精品推荐，2热门榜单，3首发新品，4促销单品", paramType = "query", dataType = "int")
    })
    @ApiOperation(value = "获取首页更多产品",notes = "获取首页更多产品")
    public ApiResult<Map<String,Object>> moreGoodsList(@PathVariable Integer type){
        Map<String,Object> map = new LinkedHashMap<>();
        // 精品推荐
        if(ProductEnum.TYPE_1.getValue().equals(type)){
            map.put("list",storeProductService.getList(1,20,ProductEnum.TYPE_1.getValue()));
        // 热门榜单
        }else if(type.equals(ProductEnum.TYPE_2.getValue())){
            map.put("list",storeProductService.getList(1,20,ProductEnum.TYPE_2.getValue()));
        // 首发新品
        }else if(type.equals(ProductEnum.TYPE_3.getValue())){
            map.put("list",storeProductService.getList(1,20,ProductEnum.TYPE_3.getValue()));
        // 促销单品
        }else if(type.equals(ProductEnum.TYPE_4.getValue())){
            map.put("list",storeProductService.getList(1,20,ProductEnum.TYPE_4.getValue()));
        }

        return ApiResult.ok(map);
    }

    /**
     * 获取产品列表
     */
    @GetMapping("/products")
    @ApiOperation(value = "商品列表",notes = "商品列表")
    public ApiResult<List<YxStoreProductQueryVo>> goodsList(YxStoreProductQueryParam productQueryParam){
        return ApiResult.ok(storeProductService.getGoodsList(productQueryParam));
    }

    /**
     * 为你推荐
     */
    @GetMapping("/product/hot")
    @ApiOperation(value = "为你推荐",notes = "为你推荐")
    public ApiResult<List<YxStoreProductQueryVo>> productRecommend(YxStoreProductQueryParam queryParam){
        return ApiResult.ok(storeProductService.getList(queryParam.getPage(), queryParam.getLimit(),
                ShopCommonEnum.IS_STATUS_1.getValue()));
    }

    /**
     * 商品详情海报
     */
    @AppLog(value = "商品详情海报", type = 1)
    @AuthCheck
    @GetMapping("/product/poster/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品ID", paramType = "query", dataType = "int")
    })
    @ApiOperation(value = "商品详情海报",notes = "商品详情海报")
    public ApiResult<String> prodoctPoster(@PathVariable Integer id,@RequestParam(value = "from",defaultValue = "h5") String from) throws IOException, FontFormatException {
        YxUser userInfo = LocalUser.getUser();

        long uid = userInfo.getUid();

        YxStoreProduct storeProduct = storeProductService.getById(id);
        // 海报
        String siteUrl = systemConfigService.getData(SystemConfigConstants.SITE_URL);
        if(StrUtil.isEmpty(siteUrl)){
            return ApiResult.fail("未配置h5地址");
        }
        String apiUrl = systemConfigService.getData(SystemConfigConstants.API_URL);
        if(StrUtil.isEmpty(apiUrl)){
            return ApiResult.fail("未配置api地址");
        }
        String name = id+"_"+uid + "_"+from+"_product_detail_wap.jpg";
        YxSystemAttachment attachment = systemAttachmentService.getInfo(name);
        String sepa = File.separator;
        String fileDir = path+"qrcode"+ sepa;
        String qrcodeUrl = "";
        if(ObjectUtil.isNull(attachment)){
            File file = FileUtil.mkdir(new File(fileDir));
            //如果类型是小程序
            if(AppFromEnum.ROUNTINE.getValue().equals(from)){
                siteUrl = siteUrl+"/product/";
                //生成二维码
                QrCodeUtil.generate(siteUrl+"?id="+id+"&spread="+uid+"&pageType=good&codeType="+AppFromEnum.ROUNTINE.getValue(), 180, 180,
                        FileUtil.file(fileDir+name));
            }
            else if(AppFromEnum.APP.getValue().equals(from)){
                siteUrl = siteUrl+"/product/";
                //生成二维码
                QrCodeUtil.generate(siteUrl+"?id="+id+"&spread="+uid+"&pageType=good&codeType="+AppFromEnum.APP.getValue(), 180, 180,
                        FileUtil.file(fileDir+name));
            //如果类型是h5
            }else if(AppFromEnum.H5.getValue().equals(from)){
                //生成二维码
                QrCodeUtil.generate(siteUrl+"/detail/"+id+"?spread="+uid, 180, 180,
                        FileUtil.file(fileDir+name));
            }else {
                //生成二维码
                String uniUrl = systemConfigService.getData(SystemConfigConstants.UNI_SITE_URL);
                siteUrl =  StrUtil.isNotBlank(uniUrl) ? uniUrl :  ShopConstants.DEFAULT_UNI_H5_URL;
                QrCodeUtil.generate(siteUrl+"/pages/shop/GoodsCon/index?id="+id+"&spread="+uid, 180, 180,
                        FileUtil.file(fileDir+name));
            }
            systemAttachmentService.attachmentAdd(name,String.valueOf(FileUtil.size(file)),
                    fileDir+name,"qrcode/"+name);

            qrcodeUrl = apiUrl + "/api/file/qrcode/"+name;
        }else{
            qrcodeUrl = apiUrl + "/api/file/" + attachment.getSattDir();
        }
        String spreadPicName = id+"_"+uid + "_"+from+"_product_user_spread.jpg";
        String spreadPicPath = fileDir+spreadPicName;
        String rr =  creatShareProductService.creatProductPic(storeProduct,qrcodeUrl,
                spreadPicName,spreadPicPath,apiUrl);
        return ApiResult.ok(rr);
    }



    /**
     * 普通商品详情
     */
    //@AppLog(value = "普通商品详情", type = 1)
    //@AuthCheck
    @GetMapping("/product/detail/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品ID", paramType = "query", dataType = "long",required = true),
            @ApiImplicitParam(name = "latitude", value = "纬度", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "longitude", value = "经度", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "from", value = "来自:", paramType = "query", dataType = "string")
    })
    @ApiOperation(value = "普通商品详情",notes = "普通商品详情")
    public ApiResult<ProductVo> detail(@PathVariable long id,
                                       @RequestParam(value = "",required=false) String latitude,
                                       @RequestParam(value = "",required=false) String longitude,
                                       @RequestParam(value = "",required=false) String from)  {
        long uid = LocalUser.getUidByToken();
        storeProductService.incBrowseNum(id);
        ProductVo productDTO = storeProductService.goodsDetail(id,uid,latitude,longitude);
        return ApiResult.ok(productDTO);
    }

    /**
     * 添加收藏
     */
    @AppLog(value = "添加收藏", type = 1)
    @NoRepeatSubmit
    @AuthCheck
    @PostMapping("/collect/add")
    @ApiOperation(value = "添加收藏",notes = "添加收藏")
    public ApiResult<Boolean> collectAdd(@Validated @RequestBody YxStoreProductRelationQueryParam param){
        long uid = LocalUser.getUser().getUid();
        if(!NumberUtil.isNumber(param.getId())) {
            throw new YshopException("参数非法");
        }
        productRelationService.addRroductRelation(Long.valueOf(param.getId()),uid,param.getCategory(),param.getType());
        return ApiResult.ok();
    }

    /**
     * 取消收藏
     */
    @AppLog(value = "取消收藏", type = 1)
    @NoRepeatSubmit
    @AuthCheck
    @PostMapping("/collect/del")
    @ApiOperation(value = "取消收藏",notes = "取消收藏")
    public ApiResult<Boolean> collectDel(@Validated @RequestBody YxStoreProductRelationQueryParam param){
        long uid = LocalUser.getUser().getUid();
        if(!NumberUtil.isNumber(param.getId())) {
            throw new YshopException("参数非法");
        }
        productRelationService.delRroductRelation(Long.valueOf(param.getId()),
                uid,param.getCategory(),param.getType());
        return ApiResult.ok();
    }

    /**
     * 取消收藏/足跡
     */
    @AppLog(value = "删除足跡", type = 1)
    @NoRepeatSubmit
    @AuthCheck
    @DeleteMapping("/collect/delFoot")
    @ApiOperation(value = "删除足跡",notes = "删除足跡")
    public ApiResult<Boolean> collectDelFoot(@Validated @RequestBody CollectDelFootParam param){
        if (CollectionUtil.isEmpty(param.getIds())){
            throw new YshopException("参数非法");
        }
        productRelationService.collectDelFoot(param.getIds());
        return ApiResult.ok();
    }

    /**
     * 获取产品评论
     */
    @GetMapping("/reply/list/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品ID", paramType = "query", dataType = "long",required = true),
            @ApiImplicitParam(name = "type", value = "评论分数类型", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "page", value = "页码,默认为1", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "limit", value = "页大小,默认为10", paramType = "query", dataType = "int")
    })
    @ApiOperation(value = "获取产品评论",notes = "获取产品评论")
    public ApiResult<List<YxStoreProductReplyQueryVo>> replyList(@PathVariable Long id,
                                                                 @RequestParam(value = "type",defaultValue = "0") int type,
                                                                 @RequestParam(value = "page",defaultValue = "1") int page,
                                                                 @RequestParam(value = "limit",defaultValue = "10") int limit){
        return ApiResult.ok(replyService.getReplyList(id,type, page,limit));
    }

    /**
     * 获取产品评论数据
     */
    @GetMapping("/reply/config/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品ID", paramType = "query", dataType = "int")
    })
    @ApiOperation(value = "获取产品评论数据",notes = "获取产品评论数据")
    public ApiResult<ReplyCountVo> replyCount(@PathVariable Integer id){
        return ApiResult.ok(replyService.getReplyCount(id));
    }



}

