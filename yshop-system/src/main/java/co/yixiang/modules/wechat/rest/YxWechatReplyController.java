package co.yixiang.modules.wechat.rest;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import co.yixiang.aop.log.Log;
import co.yixiang.modules.wechat.domain.YxWechatReply;
import co.yixiang.modules.wechat.service.YxWechatReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

/**
* @author hupeng
* @date 2019-10-10
*/
@Api(tags = "YxWechatReply管理")
@RestController
@RequestMapping("api")
public class YxWechatReplyController {

    @Autowired
    private YxWechatReplyService yxWechatReplyService;

    @Log("查询")
    @ApiOperation(value = "查询")
    @GetMapping(value = "/yxWechatReply")
    @PreAuthorize("hasAnyRole('ADMIN','YXWECHATREPLY_ALL','YXWECHATREPLY_SELECT')")
    public ResponseEntity getYxWechatReplys(){
        return new ResponseEntity(yxWechatReplyService.isExist("subscribe"),HttpStatus.OK);
    }

    @Log("新增自动回复")
    @ApiOperation(value = "新增自动回复")
    @PostMapping(value = "/yxWechatReply")
    @PreAuthorize("hasAnyRole('ADMIN','YXWECHATREPLY_ALL','YXWECHATREPLY_CREATE')")
    public ResponseEntity create(@RequestBody String jsonStr){
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        YxWechatReply yxWechatReply = new YxWechatReply();
        YxWechatReply isExist = yxWechatReplyService.isExist(jsonObject.get("key").toString());
        yxWechatReply.setKey(jsonObject.get("key").toString());
        yxWechatReply.setStatus(Integer.valueOf(jsonObject.get("status").toString()));
        yxWechatReply.setData(jsonObject.get("data").toString());
        yxWechatReply.setType(jsonObject.get("type").toString());
        if(ObjectUtil.isNull(isExist)){
            yxWechatReplyService.create(yxWechatReply);
        }else{
            yxWechatReply.setId(isExist.getId());
            yxWechatReplyService.update(yxWechatReply);
        }

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @Log("修改YxWechatReply")
    @ApiOperation(value = "修改YxWechatReply")
    @PutMapping(value = "/yxWechatReply")
    @PreAuthorize("hasAnyRole('ADMIN','YXWECHATREPLY_ALL','YXWECHATREPLY_EDIT')")
    public ResponseEntity update(@Validated @RequestBody YxWechatReply resources){
        yxWechatReplyService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除YxWechatReply")
    @ApiOperation(value = "删除YxWechatReply")
    @DeleteMapping(value = "/yxWechatReply/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','YXWECHATREPLY_ALL','YXWECHATREPLY_DELETE')")
    public ResponseEntity delete(@PathVariable Integer id){
        yxWechatReplyService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}