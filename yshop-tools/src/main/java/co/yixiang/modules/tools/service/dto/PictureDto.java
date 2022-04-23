/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.tools.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author hupeng
 * @date 2020-05-13
 */
@Data
public class PictureDto implements Serializable {

    /** ID */
    private Long id;

    /** 上传日期 */
    private Timestamp createTime;

    /** 删除的URL */
    private String deleteUrl;

    /** 图片名称 */
    private String filename;

    /** 图片高度 */
    private String height;

    /** 图片大小 */
    private String size;

    /** 图片地址 */
    private String url;

    /** 用户名称 */
    private String username;

    /** 图片宽度 */
    private String width;

    /** 文件的MD5值 */
    private String md5code;
}
