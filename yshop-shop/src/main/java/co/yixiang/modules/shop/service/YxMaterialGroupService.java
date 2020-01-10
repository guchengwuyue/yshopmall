package co.yixiang.modules.shop.service;

import co.yixiang.modules.shop.domain.YxMaterialGroup;
import co.yixiang.modules.shop.service.dto.YxMaterialGroupDto;
import co.yixiang.modules.shop.service.dto.YxMaterialGroupQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author hupeng
* @date 2020-01-09
*/
public interface YxMaterialGroupService {

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(YxMaterialGroupQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<YxMaterialGroupDto>
    */
    List<YxMaterialGroupDto> queryAll(YxMaterialGroupQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id ID
     * @return YxMaterialGroupDto
     */
    YxMaterialGroupDto findById(String id);

    /**
    * 创建
    * @param resources /
    * @return YxMaterialGroupDto
    */
    YxMaterialGroupDto create(YxMaterialGroup resources);

    /**
    * 编辑
    * @param resources /
    */
    void update(YxMaterialGroup resources);

    /**
    * 多选删除
    * @param ids /
    */
    void deleteAll(String[] ids);

    void deleteById(String id);


}