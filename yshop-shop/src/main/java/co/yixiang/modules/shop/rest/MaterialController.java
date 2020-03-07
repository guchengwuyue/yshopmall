package co.yixiang.modules.shop.rest;


import co.yixiang.aop.log.Log;
import co.yixiang.modules.shop.domain.YxMaterial;
import co.yixiang.modules.shop.service.YxMaterialService;
import co.yixiang.modules.shop.service.dto.YxMaterialQueryCriteria;
import co.yixiang.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author hupeng
* @date 2020-01-09
*/
@Api(tags = "商城:素材管理管理")
@RestController
@RequestMapping("/api/material")
public class MaterialController {

    private final YxMaterialService yxMaterialService;

    public MaterialController(YxMaterialService yxMaterialService) {
        this.yxMaterialService = yxMaterialService;
    }



    @GetMapping(value = "/page")
    @Log("查询素材管理")
    @ApiOperation("查询素材管理")
    public ResponseEntity<Object> getYxMaterials(YxMaterialQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(yxMaterialService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增素材管理")
    @ApiOperation("新增素材管理")
    public ResponseEntity<Object> create(@Validated @RequestBody YxMaterial resources){
        resources.setCreateId(SecurityUtils.getUsername());
        return new ResponseEntity<>(yxMaterialService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改素材管理")
    @ApiOperation("修改素材管理")
    public ResponseEntity<Object> update(@Validated @RequestBody YxMaterial resources){
        yxMaterialService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除素材管理")
    @ApiOperation("删除素材管理")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteAll(@PathVariable String id) {
        yxMaterialService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}