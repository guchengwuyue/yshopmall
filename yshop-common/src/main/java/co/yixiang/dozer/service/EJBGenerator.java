/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.dozer.service;

import co.yixiang.common.web.vo.Paging;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author ：LionCity
 * @date ：Created in 2019/10/9 10:43
 * @description：dozer实现类
 * @modified By：
 * @version: 1.0
 */
@Component
@Lazy(true)
public class EJBGenerator implements IGenerator {

    @Autowired
    protected Mapper dozerMapper;

    @Override
    public <T, S> T convert(final S s, Class<T> clz) {
        return s == null ? null : this.dozerMapper.map(s, clz);
    }

    @Override
    public <T, S> List<T> convert(List<S> s, Class<T> clz) {
        return s == null ? null : s.stream().map(vs -> this.dozerMapper.map(vs, clz)).collect(Collectors.toList());
    }

    @Override
    public <T, S> Paging<T> convertPaging(Paging<S> paging, Class<T> clz) {
        Paging<T> pagingVo = new Paging<T>();
        pagingVo.setRecords(convert(paging.getRecords(), clz));
        pagingVo.setTotal(paging.getTotal());
        return pagingVo;
    }

    @Override
    public <T, S> Set<T> convert(Set<S> s, Class<T> clz) {
        return s == null ? null : s.stream().map(vs -> this.dozerMapper.map(vs, clz)).collect(Collectors.toSet());
    }

    @Override
    public <T, S> T[] convert(S[] s, Class<T> clz) {
        if (s == null) {
            return null;
        }
        @SuppressWarnings("unchecked")
        T[] arr = (T[]) Array.newInstance(clz, s.length);
        for (int i = 0; i < s.length; i++) {
            arr[i] = this.dozerMapper.map(s[i], clz);
        }
        return arr;
    }
}
