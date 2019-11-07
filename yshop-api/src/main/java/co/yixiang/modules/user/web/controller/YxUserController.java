package co.yixiang.modules.user.web.controller;

import co.yixiang.modules.user.entity.YxUser;
import co.yixiang.modules.user.service.YxUserService;
import co.yixiang.modules.user.web.param.YxUserQueryParam;
import co.yixiang.modules.user.web.vo.YxUserQueryVo;
import co.yixiang.common.web.controller.BaseController;
import co.yixiang.common.api.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import co.yixiang.common.web.vo.Paging;
import co.yixiang.common.web.param.IdParam;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author hupeng
 * @since 2019-10-16
 */
@Slf4j
@RestController
@RequestMapping("/yxUser")
@Api("用户表 API")
public class YxUserController extends BaseController {

    @Autowired
    private YxUserService yxUserService;

    /**
    * 添加用户表
    */
    @PostMapping("/add")
    @ApiOperation(value = "添加YxUser对象",notes = "添加用户表",response = ApiResult.class)
    public ApiResult<Boolean> addYxUser(@Valid @RequestBody YxUser yxUser) throws Exception{
        boolean flag = yxUserService.save(yxUser);
        return ApiResult.result(flag);
    }

    /**
    * 修改用户表
    */
    @PostMapping("/update")
    @ApiOperation(value = "修改YxUser对象",notes = "修改用户表",response = ApiResult.class)
    public ApiResult<Boolean> updateYxUser(@Valid @RequestBody YxUser yxUser) throws Exception{
        boolean flag = yxUserService.updateById(yxUser);
        return ApiResult.result(flag);
    }

    /**
    * 删除用户表
    */
    @PostMapping("/delete")
    @ApiOperation(value = "删除YxUser对象",notes = "删除用户表",response = ApiResult.class)
    public ApiResult<Boolean> deleteYxUser(@Valid @RequestBody IdParam idParam) throws Exception{
        boolean flag = yxUserService.removeById(idParam.getId());
        return ApiResult.result(flag);
    }

    /**
    * 获取用户表
    */
    @PostMapping("/info")
    @ApiOperation(value = "获取YxUser对象详情",notes = "查看用户表",response = YxUserQueryVo.class)
    public ApiResult<YxUserQueryVo> getYxUser(@Valid @RequestBody IdParam idParam) throws Exception{
        YxUserQueryVo yxUserQueryVo = yxUserService.getYxUserById(idParam.getId());
        return ApiResult.ok(yxUserQueryVo);
    }

    /**
     * 用户表分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "获取YxUser分页列表",notes = "用户表分页列表",response = YxUserQueryVo.class)
    public ApiResult<Paging<YxUserQueryVo>> getYxUserPageList(@Valid @RequestBody(required = false) YxUserQueryParam yxUserQueryParam) throws Exception{
        Paging<YxUserQueryVo> paging = yxUserService.getYxUserPageList(yxUserQueryParam);
        return ApiResult.ok(paging);
    }

}

