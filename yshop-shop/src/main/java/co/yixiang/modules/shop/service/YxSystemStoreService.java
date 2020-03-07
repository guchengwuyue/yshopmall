package co.yixiang.modules.shop.service;

import co.yixiang.modules.shop.domain.YxSystemStore;
import co.yixiang.modules.shop.service.dto.YxSystemStoreDto;
import co.yixiang.modules.shop.service.dto.YxSystemStoreQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author hupeng
* @date 2020-03-03
*/
public interface YxSystemStoreService {

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(YxSystemStoreQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<YxSystemStoreDto>
    */
    List<YxSystemStoreDto> queryAll(YxSystemStoreQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id ID
     * @return YxSystemStoreDto
     */
    YxSystemStoreDto findById(Integer id);

    /**
    * 创建
    * @param resources /
    * @return YxSystemStoreDto
    */
    YxSystemStoreDto create(YxSystemStore resources);

    /**
    * 编辑
    * @param resources /
    */
    void update(YxSystemStore resources);

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
    void download(List<YxSystemStoreDto> all, HttpServletResponse response) throws IOException;
}