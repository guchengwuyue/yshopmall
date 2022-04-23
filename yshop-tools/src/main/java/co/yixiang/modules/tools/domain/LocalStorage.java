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
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author hupeng
 * @date 2020-05-13
 */

@Getter
@Setter
@TableName("local_storage")
public class LocalStorage implements Serializable {

    @TableId
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name = "id")
    private Long id;


    /** 文件真实的名称 */
    // @Column(name = "real_name")
    private String realName;


    /** 文件名 */
    // @Column(name = "name")
    private String name;


    /** 后缀 */
    // @Column(name = "suffix")
    private String suffix;


    /** 路径 */
    // @Column(name = "path")
    private String path;


    /** 类型 */
    // @Column(name = "type")
    private String type;


    /** 大小 */
    // @Column(name = "size")
    private String size;


    /** 操作人 */
    // @Column(name = "operate")
    private String operate;


    /** 创建日期 */
    // @Column(name = "create_time")
    @TableField(fill = FieldFill.INSERT)
    private Timestamp createTime;


    public LocalStorage(String realName, String name, String suffix, String path, String type, String size, String operate) {
        this.realName = realName;
        this.name = name;
        this.suffix = suffix;
        this.path = path;
        this.type = type;
        this.size = size;
        this.operate = operate;
    }

    public void copy(LocalStorage source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
