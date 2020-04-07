package co.yixiang.modules.shop.service;

import co.yixiang.modules.shop.domain.YxSystemStoreStaff;
import co.yixiang.modules.shop.service.dto.YxSystemStoreStaffDto;
import co.yixiang.modules.shop.service.dto.YxSystemStoreStaffQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author hupeng
* @date 2020-03-22
*/
public interface YxSystemStoreStaffService {

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(YxSystemStoreStaffQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<YxSystemStoreStaffDto>
    */
    List<YxSystemStoreStaffDto> queryAll(YxSystemStoreStaffQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id ID
     * @return YxSystemStoreStaffDto
     */
    YxSystemStoreStaffDto findById(Integer id);

    /**
    * 创建
    * @param resources /
    * @return YxSystemStoreStaffDto
    */
    YxSystemStoreStaffDto create(YxSystemStoreStaff resources);

    /**
    * 编辑
    * @param resources /
    */
    void update(YxSystemStoreStaff resources);

    /**
    * 多选删除
    * @param ids /
    */
    void deleteAll(Integer[] ids);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<YxSystemStoreStaffDto> all, HttpServletResponse response) throws IOException;
}