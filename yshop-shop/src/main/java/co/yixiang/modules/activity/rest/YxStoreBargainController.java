package co.yixiang.modules.activity.rest;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import co.yixiang.aop.log.Log;
import co.yixiang.exception.BadRequestException;
import co.yixiang.modules.activity.domain.YxStoreBargain;
import co.yixiang.modules.activity.service.YxStoreBargainService;
import co.yixiang.modules.activity.service.dto.YxStoreBargainQueryCriteria;
import co.yixiang.utils.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

/**
* @author xuwenbo
* @date 2019-12-22
*/
@Api(tags = "YxStoreBargain管理")
@RestController
@RequestMapping("api")
public class YxStoreBargainController {

    @Autowired
    private YxStoreBargainService yxStoreBargainService;

    @Log("查询YxStoreBargain")
    @ApiOperation(value = "查询YxStoreBargain")
    @GetMapping(value = "/yxStoreBargain")
    @PreAuthorize("hasAnyRole('ADMIN','YXSTOREBARGAIN_ALL','YXSTOREBARGAIN_SELECT')")
    public ResponseEntity getYxStoreBargains(YxStoreBargainQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(yxStoreBargainService.queryAll(criteria,pageable),HttpStatus.OK);
    }



    @Log("修改YxStoreBargain")
    @ApiOperation(value = "修改YxStoreBargain")
    @PutMapping(value = "/yxStoreBargain")
    @PreAuthorize("hasAnyRole('ADMIN','YXSTOREBARGAIN_ALL','YXSTOREBARGAIN_EDIT')")
    public ResponseEntity update(@Validated @RequestBody YxStoreBargain resources){

        if(ObjectUtil.isNotNull(resources.getStartTimeDate())){
            resources.setStartTime(OrderUtil.
                    dateToTimestamp(resources.getStartTimeDate()));
        }
        if(ObjectUtil.isNotNull(resources.getEndTimeDate())){
            resources.setStopTime(OrderUtil.
                    dateToTimestamp(resources.getEndTimeDate()));
        }
        if(ObjectUtil.isNull(resources.getId())){
            resources.setAddTime(OrderUtil.getSecondTimestampTwo());
            return new ResponseEntity(yxStoreBargainService.create(resources),HttpStatus.CREATED);
        }else{
            yxStoreBargainService.update(resources);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @Log("删除YxStoreBargain")
    @ApiOperation(value = "删除YxStoreBargain")
    @DeleteMapping(value = "/yxStoreBargain/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','YXSTOREBARGAIN_ALL','YXSTOREBARGAIN_DELETE')")
    public ResponseEntity delete(@PathVariable Integer id){
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        yxStoreBargainService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}