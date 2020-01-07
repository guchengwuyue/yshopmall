package co.yixiang.modules.shop.rest;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import co.yixiang.aop.log.Log;
import co.yixiang.exception.BadRequestException;
import co.yixiang.modules.shop.domain.YxStoreProduct;
import co.yixiang.modules.shop.service.YxStoreProductService;
import co.yixiang.modules.shop.service.dto.YxStoreProductQueryCriteria;
import co.yixiang.utils.OrderUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
* @author hupeng
* @date 2019-10-04
*/
@Api(tags = "商品管理")
@RestController
@RequestMapping("api")
public class YxStoreProductController {

    @Autowired
    private YxStoreProductService yxStoreProductService;

    @Log("查询商品")
    @ApiOperation(value = "查询商品")
    @GetMapping(value = "/yxStoreProduct")
    @PreAuthorize("@el.check('admin','YXSTOREPRODUCT_ALL','YXSTOREPRODUCT_SELECT')")
    public ResponseEntity getYxStoreProducts(YxStoreProductQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(yxStoreProductService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增商品")
    @ApiOperation(value = "新增商品")
    @PostMapping(value = "/yxStoreProduct")
    @PreAuthorize("@el.check('admin','YXSTOREPRODUCT_ALL','YXSTOREPRODUCT_CREATE')")
    public ResponseEntity create(@Validated @RequestBody YxStoreProduct resources){
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        resources.setAddTime(OrderUtil.getSecondTimestampTwo());
        if(ObjectUtil.isEmpty(resources.getGiveIntegral())) resources.setGiveIntegral(BigDecimal.ZERO);
        if(ObjectUtil.isEmpty(resources.getCost())) resources.setCost(BigDecimal.ZERO);
        return new ResponseEntity(yxStoreProductService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改商品")
    @ApiOperation(value = "修改商品")
    @PutMapping(value = "/yxStoreProduct")
    @PreAuthorize("@el.check('admin','YXSTOREPRODUCT_ALL','YXSTOREPRODUCT_EDIT')")
    public ResponseEntity update(@Validated @RequestBody YxStoreProduct resources){
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        yxStoreProductService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除商品")
    @ApiOperation(value = "删除商品")
    @DeleteMapping(value = "/yxStoreProduct/{id}")
    @PreAuthorize("@el.check('admin','YXSTOREPRODUCT_ALL','YXSTOREPRODUCT_DELETE')")
    public ResponseEntity delete(@PathVariable Integer id){
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        yxStoreProductService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "恢复数据")
    @DeleteMapping(value = "/yxStoreProduct/recovery/{id}")
    @PreAuthorize("@el.check('admin','YXSTOREPRODUCT_ALL','YXSTOREPRODUCT_DELETE')")
    public ResponseEntity recovery(@PathVariable Integer id){
        yxStoreProductService.recovery(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "商品上架下架")
    @PostMapping(value = "/yxStoreProduct/onsale/{id}")
    public ResponseEntity onSale(@PathVariable Integer id,@RequestBody String jsonStr){
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        int status = Integer.valueOf(jsonObject.get("status").toString());
        //System.out.println(status);
        yxStoreProductService.onSale(id,status);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "生成属性")
    @PostMapping(value = "/yxStoreProduct/isFormatAttr/{id}")
    public ResponseEntity isFormatAttr(@PathVariable Integer id,@RequestBody String jsonStr){
        return new ResponseEntity(yxStoreProductService.isFormatAttr(id,jsonStr),HttpStatus.OK);
    }

    @ApiOperation(value = "设置保存属性")
    @PostMapping(value = "/yxStoreProduct/setAttr/{id}")
    public ResponseEntity setAttr(@PathVariable Integer id,@RequestBody String jsonStr){
        yxStoreProductService.createProductAttr(id,jsonStr);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "清除属性")
    @PostMapping(value = "/yxStoreProduct/clearAttr/{id}")
    public ResponseEntity clearAttr(@PathVariable Integer id){
        yxStoreProductService.clearProductAttr(id,true);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "获取属性")
    @GetMapping(value = "/yxStoreProduct/attr/{id}")
    public ResponseEntity attr(@PathVariable Integer id){
        String str = yxStoreProductService.getStoreProductAttrResult(id);
        if(StrUtil.isEmpty(str)){
            return new ResponseEntity(HttpStatus.OK);
        }
        JSONObject jsonObject = JSON.parseObject(str);

        return new ResponseEntity(jsonObject,HttpStatus.OK);
    }



}