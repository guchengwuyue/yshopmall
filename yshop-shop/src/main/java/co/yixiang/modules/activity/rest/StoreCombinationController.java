package co.yixiang.modules.activity.rest;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import co.yixiang.aop.log.Log;
import co.yixiang.exception.BadRequestException;
import co.yixiang.modules.activity.domain.YxStoreCombination;
import co.yixiang.modules.activity.service.YxStoreCombinationService;
import co.yixiang.modules.activity.service.dto.YxStoreCombinationQueryCriteria;
import co.yixiang.utils.OrderUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author hupeng
* @date 2019-11-18
*/
@Api(tags = "商城:拼团管理")
@RestController
@RequestMapping("api")
public class StoreCombinationController {

    private final YxStoreCombinationService yxStoreCombinationService;

    public StoreCombinationController(YxStoreCombinationService yxStoreCombinationService) {
        this.yxStoreCombinationService = yxStoreCombinationService;
    }

    @Log("查询拼团")
    @ApiOperation(value = "查询拼团")
    @GetMapping(value = "/yxStoreCombination")
    @PreAuthorize("@el.check('admin','YXSTORECOMBINATION_ALL','YXSTORECOMBINATION_SELECT')")
    public ResponseEntity getYxStoreCombinations(YxStoreCombinationQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(yxStoreCombinationService.queryAll(criteria,pageable),HttpStatus.OK);
    }



    @Log("修改拼团")
    @ApiOperation(value = "新增/修改拼团")
    @PutMapping(value = "/yxStoreCombination")
    @PreAuthorize("@el.check('admin','YXSTORECOMBINATION_ALL','YXSTORECOMBINATION_EDIT')")
    public ResponseEntity update(@Validated @RequestBody YxStoreCombination resources){
        if(ObjectUtil.isNotNull(resources.getStartTimeDate())){
            resources.setStartTime(OrderUtil.
                    dateToTimestamp(resources.getStartTimeDate()));
        }
        if(ObjectUtil.isNotNull(resources.getEndTimeDate())){
            resources.setStopTime(OrderUtil.
                    dateToTimestamp(resources.getEndTimeDate()));
        }
        if(ObjectUtil.isNull(resources.getId())){
            resources.setAddTime(String.valueOf(OrderUtil.getSecondTimestampTwo()));
            return new ResponseEntity(yxStoreCombinationService.create(resources),HttpStatus.CREATED);
        }else{
            yxStoreCombinationService.update(resources);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

    }

    @ApiOperation(value = "开启关闭")
    @PostMapping(value = "/yxStoreCombination/onsale/{id}")
    public ResponseEntity onSale(@PathVariable Integer id,@RequestBody String jsonStr){
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        int status = Integer.valueOf(jsonObject.get("status").toString());
        //System.out.println(status);
        yxStoreCombinationService.onSale(id,status);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Log("删除拼团")
    @ApiOperation(value = "删除拼团")
    @DeleteMapping(value = "/yxStoreCombination/{id}")
    @PreAuthorize("@el.check('admin','YXSTORECOMBINATION_ALL','YXSTORECOMBINATION_DELETE')")
    public ResponseEntity delete(@PathVariable Integer id){
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        YxStoreCombination combination = new YxStoreCombination();
        combination.setIsDel(1);
        combination.setId(id);
        yxStoreCombinationService.update(combination);
        return new ResponseEntity(HttpStatus.OK);
    }
}