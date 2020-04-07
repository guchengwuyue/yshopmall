package co.yixiang.modules.shop.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @author hupeng
* @date 2020-03-22
*/
@Data
public class YxSystemStoreStaffDto implements Serializable {

    private Integer id;

    /** 微信用户id */
    private Integer uid;

    /** 店员头像 */
    private String avatar;

    /** 门店id */
    private Integer storeId;

    /** 店员名称 */
    private String staffName;

    /** 手机号码 */
    private String phone;

    /** 核销开关 */
    private Integer verifyStatus;

    /** 状态 */
    private Integer status;

    /** 添加时间 */
    private Integer addTime;

    /** 微信昵称 */
    private String nickname;

    /** 所属门店 */
    private String storeName;
}