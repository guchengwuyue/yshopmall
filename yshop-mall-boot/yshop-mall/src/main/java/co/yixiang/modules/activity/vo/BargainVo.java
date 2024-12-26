package co.yixiang.modules.activity.vo;


import co.yixiang.modules.user.vo.YxUserQueryVo;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName BargainVo
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/12/21
 **/
@Data
@Builder
public class BargainVo implements Serializable {
    private YxStoreBargainQueryVo bargain;
    private YxUserQueryVo userInfo;
    private Long bargainSumCount;//砍价支付成功订单数量
}
