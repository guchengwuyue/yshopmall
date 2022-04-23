/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.tools.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author hupeng
 * @date 2020-05-13
 */

@Data
@TableName("qiniu_content")
public class QiniuContent implements Serializable {

    /** ID */
    @TableId
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name = "id")
    private Long id;


    /** Bucket 识别符 */
    // @Column(name = "bucket")
    private String bucket;


    /** 文件名称 */
    @TableField("name")
    private String name;


    /** 文件大小 */
    // @Column(name = "size")
    private String size;


    /** 文件类型：私有或公开 */
    // @Column(name = "type")
    private String type;


    /** 上传或同步的时间 */
    // @Column(name = "update_time")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Timestamp updateTime;


    /** 文件url */
    // @Column(name = "url")
    private String url;


    // @Column(name = "suffix")
    private String suffix;


    public void copy(QiniuContent source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
