package co.yixiang.modules.activity.rest;

import co.yixiang.aop.log.Log;
import co.yixiang.modules.activity.domain.YxStoreVisit;
import co.yixiang.modules.activity.service.YxStoreVisitService;
import co.yixiang.modules.activity.service.dto.YxStoreVisitQueryCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

/**
* @author hupeng
* @date 2019-11-18
*/
@Api(tags = "YxStoreVisit管理")
@RestController
@RequestMapping("api")
public class YxStoreVisitController {

    @Autowired
    private YxStoreVisitService yxStoreVisitService;

    @Log("查询YxStoreVisit")
    @ApiOperation(value = "查询YxStoreVisit")
    @GetMapping(value = "/yxStoreVisit")
    @PreAuthorize("hasAnyRole('ADMIN','YXSTOREVISIT_ALL','YXSTOREVISIT_SELECT')")
    public ResponseEntity getYxStoreVisits(YxStoreVisitQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(yxStoreVisitService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增YxStoreVisit")
    @ApiOperation(value = "新增YxStoreVisit")
    @PostMapping(value = "/yxStoreVisit")
    @PreAuthorize("hasAnyRole('ADMIN','YXSTOREVISIT_ALL','YXSTOREVISIT_CREATE')")
    public ResponseEntity create(@Validated @RequestBody YxStoreVisit resources){
        return new ResponseEntity(yxStoreVisitService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改YxStoreVisit")
    @ApiOperation(value = "修改YxStoreVisit")
    @PutMapping(value = "/yxStoreVisit")
    @PreAuthorize("hasAnyRole('ADMIN','YXSTOREVISIT_ALL','YXSTOREVISIT_EDIT')")
    public ResponseEntity update(@Validated @RequestBody YxStoreVisit resources){
        yxStoreVisitService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除YxStoreVisit")
    @ApiOperation(value = "删除YxStoreVisit")
    @DeleteMapping(value = "/yxStoreVisit/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','YXSTOREVISIT_ALL','YXSTOREVISIT_DELETE')")
    public ResponseEntity delete(@PathVariable Integer id){
        yxStoreVisitService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}