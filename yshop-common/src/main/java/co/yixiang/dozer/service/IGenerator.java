/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.dozer.service;

import co.yixiang.common.web.vo.Paging;

import java.util.List;
import java.util.Set;

/**
 * @author ：LionCity
 * @date ：Created in 2019/10/9 10:42
 * @description：doczer转换接口
 * @modified By：
 * @version: 1.0
 */
public interface IGenerator {

    /**
     * 转换
     *
     * @param s   数据对象
     * @param clz 复制目标类型
     * @return {@link T}
     * @Description: 单个对象的深度复制及类型转换，vo/domain , po
     * @author banjuer@outlook.com
     * @Time 2018年5月9日 下午3:53:24
     */
    <T, S> T convert(S s, Class<T> clz);

    /**
     * @Description: 深度复制结果集(ResultSet为自定义的分页结果集)
     * @param s 数据对象
     * @param clz 复制目标类型
     * @return
     * @author banjuer@outlook.com
     * @Time 2018年5月9日 下午3:53:24
     */
    //<T, S> Result<T> convert(Result<S> s, Class<T> clz);

    /**
     * 转换
     * @param s   数据对象
     * @param clz 复制目标类型
     * @return {@link List<T>}
     * @Description: list深度复制
     * @author banjuer@outlook.com
     * @Time 2018年5月9日 下午3:54:08
     */
    <T, S> List<T> convert(List<S> s, Class<T> clz);

    /**
     *
     * @param s
     * @param clz
     * @param <T>
     * @param <S>
     * @return
     */
    <T, S> Paging<T> convertPaging(Paging<S> s, Class<T> clz);

    /**
     * @param s   数据对象
     * @param clz 复制目标类型
     * @return
     * @Description: set深度复制
     * @author banjuer@outlook.com
     * @Time 2018年5月9日 下午3:54:39
     */
    <T, S> Set<T> convert(Set<S> s, Class<T> clz);

    /**
     * @param s   数据对象
     * @param clz 复制目标类型
     * @return
     * @Description: 数组深度复制
     * @author banjuer@outlook.com
     * @Time 2018年5月9日 下午3:54:57
     */
    <T, S> T[] convert(S[] s, Class<T> clz);
}
