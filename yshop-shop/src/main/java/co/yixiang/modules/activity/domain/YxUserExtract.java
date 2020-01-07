package co.yixiang.modules.activity.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
* @author hupeng
* @date 2019-11-14
*/
@Entity
@Data
@Table(name="yx_user_extract")
public class YxUserExtract implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "uid")
    private Integer uid;

    // 名称
    @Column(name = "real_name")
    private String realName;

    // bank = 银行卡 alipay = 支付宝wx=微信
    @Column(name = "extract_type")
    private String extractType;

    // 银行卡
    @Column(name = "bank_code")
    private String bankCode;

    // 开户地址
    @Column(name = "bank_address")
    private String bankAddress;

    // 支付宝账号
    @Column(name = "alipay_code")
    private String alipayCode;

    // 提现金额
    @Column(name = "extract_price")
    private BigDecimal extractPrice;

    @Column(name = "mark")
    private String mark;

    @Column(name = "balance")
    private BigDecimal balance;

    // 无效原因
    @Column(name = "fail_msg")
    private String failMsg;

    @Column(name = "fail_time")
    private Integer failTime;

    // 添加时间
    @Column(name = "add_time")
    private Integer addTime;

    // -1 未通过 0 审核中 1 已提现
    @Column(name = "status")
    private Integer status;

    // 微信号
    @Column(name = "wechat")
    private String wechat;

    public void copy(YxUserExtract source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}