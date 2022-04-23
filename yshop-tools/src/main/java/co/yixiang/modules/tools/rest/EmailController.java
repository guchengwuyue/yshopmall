/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.tools.rest;

import co.yixiang.modules.logging.aop.log.Log;
import co.yixiang.modules.tools.domain.EmailConfig;
import co.yixiang.modules.tools.service.EmailConfigService;
import co.yixiang.modules.tools.domain.vo.EmailVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 发送邮件
 * @author 郑杰
 * @date 2018/09/28 6:55:53
 */
@RestController
@RequestMapping("api/email")
@Api(tags = "工具：邮件管理")
public class EmailController {

    private final EmailConfigService emailService;

    public EmailController(EmailConfigService emailService) {
        this.emailService = emailService;
    }

    @GetMapping
    public ResponseEntity<Object> get() {
        return new ResponseEntity<>(emailService.find(), HttpStatus.OK);
    }

    @Log("配置邮件")
    @PutMapping
    @ApiOperation("配置邮件")
    public ResponseEntity<Object> emailConfig(@Validated @RequestBody EmailConfig emailConfig) {
        emailService.update(emailConfig, emailService.find());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Log("发送邮件")
    @PostMapping
    @ApiOperation("发送邮件")
    public ResponseEntity<Object> send(@Validated @RequestBody EmailVo emailVo) throws Exception {
        emailService.send(emailVo, emailService.find());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
