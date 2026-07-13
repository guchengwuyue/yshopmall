/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.common.handler;


import co.yixiang.api.ApiCode;
import co.yixiang.api.ApiResult;
import co.yixiang.api.BusinessException;
import co.yixiang.api.DaoException;
import co.yixiang.api.UnAuthenticatedException;
import co.yixiang.api.YshopException;
import co.yixiang.common.bean.RequestDetail;
import co.yixiang.common.util.RequestDetailThreadLocal;
import co.yixiang.exception.BadLimitRequestException;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 全局异常
 * @author hupeng
 * @date 2020-04-30
 */
@ControllerAdvice
@RestController
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 非法参数验证异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public ApiResult<String> handleMethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        printRequestDetail();
        BindingResult bindingResult = ex.getBindingResult();
        List<String> list = new ArrayList<>();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            list.add(fieldError.getDefaultMessage());
        }

        Collections.sort(list);
        String msg = "不能为空";
        if(!list.isEmpty()) {
            msg = list.get(0);
        }
        log.error(getApiCodeString(ApiCode.PARAMETER_EXCEPTION) + ":" + JSON.toJSONString(list));
        return ApiResult.fail(ApiCode.PARAMETER_EXCEPTION.getCode(), msg);
    }




    @ExceptionHandler(value = BadLimitRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResult<Boolean> badLimitRequestException(BadLimitRequestException exception) {
        printRequestDetail();
        printApiCodeException(ApiCode.SYSTEM_EXCEPTION, exception);
        return new ApiResult<Boolean>()
                .setStatus(ApiCode.BAD_LIMIT_EXCEPTION.getCode())
                .setMsg(exception.getMessage());
    }



    /**
     * 自定义业务/数据异常处理
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = {YshopException.class})
    @ResponseStatus(HttpStatus.OK)
    public ApiResult<Boolean> YshopException(YshopException exception) {
        printRequestDetail();
        log.error("YshopException:", exception);
        int errorCode;
        if (exception instanceof BusinessException) {
            errorCode = ApiCode.BUSINESS_EXCEPTION.getCode();
        } else if (exception instanceof DaoException) {
            errorCode = ApiCode.DAO_EXCEPTION.getCode();
        } else if (exception instanceof UnAuthenticatedException) {
            errorCode = ApiCode.UNAUTHORIZED.getCode();

        } else {
            errorCode = ApiCode.BUSINESS_EXCEPTION.getCode();
        }
        System.out.println("=======");
        return new ApiResult<Boolean>()
                .setStatus(errorCode)
                .setMsg(exception.getMessage());
    }





    /**
     * 默认的异常处理
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.OK)
    public ApiResult<String> httpRequestMethodNotSupportedExceptionHandler(Exception exception) {
        printRequestDetail();
        printApiCodeException(ApiCode.HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION, exception);
        return ApiResult.fail(ApiCode.HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION.getCode(), exception.getMessage());
    }

    /**
     * 默认的异常处理
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public ApiResult<Boolean> exceptionHandler(Exception exception) {
        printRequestDetail();
        printApiCodeException(ApiCode.SYSTEM_EXCEPTION, exception);
       // return ApiResult.fail(ApiCode.SYSTEM_EXCEPTION);

        return new ApiResult<Boolean>()
                .setStatus(ApiCode.FAIL.getCode())
                .setMsg(exception.getMessage());
    }


    /**
     * 打印请求详情
     */
    private void printRequestDetail() {
        RequestDetail requestDetail = RequestDetailThreadLocal.getRequestDetail();
        if (requestDetail != null) {
            log.error("异常来源：ip: {}, path: {}", requestDetail.getIp(), requestDetail.getPath());
        }
    }

    /**
     * 获取ApiCode格式化字符串
     *
     * @param apiCode
     * @return
     */
    private String getApiCodeString(ApiCode apiCode) {
        if (apiCode != null) {
            return String.format("errorCode: %s, errorMessage: %s", apiCode.getCode(), apiCode.getMessage());
        }
        return null;
    }

    /**
     * 打印错误码及异常
     *
     * @param apiCode
     * @param exception
     */
    private void printApiCodeException(ApiCode apiCode, Exception exception) {
        log.error(getApiCodeString(apiCode), exception);
    }

}
