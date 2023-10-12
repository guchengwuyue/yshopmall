/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制，未经购买不得使用
 * 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
 * 一经发现盗用、分享等行为，将追究法律责任，后果自负
 */
package co.yixiang.api;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义异常
 * @author hupeng
 * @date 2020-04-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class YshopException extends RuntimeException{

    private static final long serialVersionUID = -2470461654663264392L;

    private Integer errorCode;
    private String message;

    public YshopException() {
        super();
    }

    public YshopException(String message) {
        super(message);
        this.message = message;
    }

    public YshopException(Integer errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }

    public YshopException(ApiCode apiCode) {
        super(apiCode.getMessage());
        this.errorCode = apiCode.getCode();
        this.message = apiCode.getMessage();
    }

    public YshopException(String message, Throwable cause) {
        super(message, cause);
    }

    public YshopException(Throwable cause) {
        super(cause);
    }

}
