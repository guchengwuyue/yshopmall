package co.yixiang.modules.wechat.rest;

import co.yixiang.aop.log.Log;
import co.yixiang.modules.wechat.domain.YxWechatUser;
import co.yixiang.modules.wechat.service.YxWechatUserService;
import co.yixiang.modules.wechat.service.dto.YxWechatUserQueryCriteria;
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
* @date 2019-12-13
*/
@Api(tags = "YxWechatUser管理")
@RestController
@RequestMapping("api")
public class YxWechatUserController {

    @Autowired
    private YxWechatUserService yxWechatUserService;

    @Log("查询YxWechatUser")
    @ApiOperation(value = "查询YxWechatUser")
    @GetMapping(value = "/yxWechatUser")
    @PreAuthorize("hasAnyRole('ADMIN','YXWECHATUSER_ALL','YXWECHATUSER_SELECT')")
    public ResponseEntity getYxWechatUsers(YxWechatUserQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(yxWechatUserService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增YxWechatUser")
    @ApiOperation(value = "新增YxWechatUser")
    @PostMapping(value = "/yxWechatUser")
    @PreAuthorize("hasAnyRole('ADMIN','YXWECHATUSER_ALL','YXWECHATUSER_CREATE')")
    public ResponseEntity create(@Validated @RequestBody YxWechatUser resources){
        return new ResponseEntity(yxWechatUserService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改YxWechatUser")
    @ApiOperation(value = "修改YxWechatUser")
    @PutMapping(value = "/yxWechatUser")
    @PreAuthorize("hasAnyRole('ADMIN','YXWECHATUSER_ALL','YXWECHATUSER_EDIT')")
    public ResponseEntity update(@Validated @RequestBody YxWechatUser resources){
        yxWechatUserService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除YxWechatUser")
    @ApiOperation(value = "删除YxWechatUser")
    @DeleteMapping(value = "/yxWechatUser/{uid}")
    @PreAuthorize("hasAnyRole('ADMIN','YXWECHATUSER_ALL','YXWECHATUSER_DELETE')")
    public ResponseEntity delete(@PathVariable Integer uid){
        yxWechatUserService.delete(uid);
        return new ResponseEntity(HttpStatus.OK);
    }
}