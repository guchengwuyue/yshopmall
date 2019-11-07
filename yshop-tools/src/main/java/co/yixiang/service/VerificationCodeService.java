package co.yixiang.service;

import co.yixiang.domain.vo.EmailVo;
import co.yixiang.domain.VerificationCode;

/**
 * @author Zheng Jie
 * @date 2018-12-26
 */
public interface VerificationCodeService {

    /**
     * 发送邮件验证码
     * @param code
     */
    EmailVo sendEmail(VerificationCode code);

    /**
     * 验证
     * @param code
     */
    void validated(VerificationCode code);
}
