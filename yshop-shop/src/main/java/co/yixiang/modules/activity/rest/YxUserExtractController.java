package co.yixiang.modules.activity.rest;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import co.yixiang.aop.log.Log;
import co.yixiang.exception.BadRequestException;
import co.yixiang.modules.activity.domain.YxUserExtract;
import co.yixiang.modules.activity.service.YxUserExtractService;
import co.yixiang.modules.activity.service.dto.YxUserExtractQueryCriteria;
import co.yixiang.modules.shop.domain.YxUserBill;
import co.yixiang.modules.shop.service.YxUserBillService;
import co.yixiang.modules.shop.service.YxUserService;
import co.yixiang.modules.shop.service.dto.YxUserDTO;
import co.yixiang.utils.OrderUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author hupeng
* @date 2019-11-14
*/
@Api(tags = "提现管理")
@RestController
@RequestMapping("api")
public class YxUserExtractController {

    @Autowired
    private YxUserExtractService yxUserExtractService;

    @Autowired
    private YxUserService yxUserService;

    @Autowired
    private YxUserBillService yxUserBillService;

    @Log("查询")
    @ApiOperation(value = "查询")
    @GetMapping(value = "/yxUserExtract")
    @PreAuthorize("@el.check('admin','YXUSEREXTRACT_ALL','YXUSEREXTRACT_SELECT')")
    public ResponseEntity getYxUserExtracts(YxUserExtractQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(yxUserExtractService.queryAll(criteria,pageable),HttpStatus.OK);
    }



    @Log("修改")
    @ApiOperation(value = "修改审核")
    @PutMapping(value = "/yxUserExtract")
    @PreAuthorize("@el.check('admin','YXUSEREXTRACT_ALL','YXUSEREXTRACT_EDIT')")
    public ResponseEntity update(@Validated @RequestBody YxUserExtract resources){
        if(StrUtil.isEmpty(resources.getStatus().toString())){
            throw new BadRequestException("请选择审核状态");
        }
        if(resources.getStatus() != -1 && resources.getStatus() != 1){
            throw new BadRequestException("请选择审核状态");
        }
        if(resources.getStatus() == -1){
            if(StrUtil.isEmpty(resources.getFailMsg())){
                throw new BadRequestException("请填写失败原因");
            }
            String mark = "提现失败,退回佣金"+resources.getExtractPrice()+"元";
            YxUserDTO userDTO = yxUserService.findById(resources.getUid());

            //增加流水
            YxUserBill userBill = new YxUserBill();
            userBill.setTitle("提现失败");
            userBill.setUid(resources.getUid());
            userBill.setCategory("now_money");
            userBill.setType("extract");
            userBill.setNumber(resources.getExtractPrice());
            userBill.setLinkId(resources.getId().toString());
            userBill.setBalance(NumberUtil.add(userDTO.getBrokeragePrice(),resources.getExtractPrice()));
            userBill.setMark(mark);
            userBill.setStatus(1);
            userBill.setAddTime(OrderUtil.getSecondTimestampTwo());
            userBill.setPm(1);
            yxUserBillService.create(userBill);

            //返回提现金额
            yxUserService.incBrokeragePrice(resources.getExtractPrice().doubleValue()
                    ,resources.getUid());

            resources.setFailTime(OrderUtil.getSecondTimestampTwo());

        }
        yxUserExtractService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}