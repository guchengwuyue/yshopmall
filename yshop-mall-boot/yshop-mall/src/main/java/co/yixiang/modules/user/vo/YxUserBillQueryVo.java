package co.yixiang.modules.user.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 用户账单表 查询结果对象
 * </p>
 *
 * @author hupeng
 * @date 2019-10-27
 */
@Data
@Schema(description = "YxUserBillQueryVo对象")
public class YxUserBillQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "用户账单id")
    private Long id;



    @Schema(description = "0 = 支出 1 = 获得")
    private Integer pm;

    @Schema(description = "账单标题")
    private String title;

    @Schema(description = "明细数字")
    private BigDecimal number;


    @Schema(description = "备注")
    private String mark;

    @Schema(description = "添加时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;



}
