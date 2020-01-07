package co.yixiang.modules.system.service;

import co.yixiang.modules.system.domain.Dept;
import co.yixiang.modules.system.service.dto.DeptDTO;
import co.yixiang.modules.system.service.dto.DeptQueryCriteria;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
* @author Zheng Jie
* @date 2019-03-25
*/
public interface DeptService {

    /**
     * 查询所有数据
     * @param criteria 条件
     * @return /
     */
    List<DeptDTO> queryAll(DeptQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id /
     * @return /
     */
    DeptDTO findById(Long id);

    /**
     * 创建
     * @param resources /
     * @return /
     */
    DeptDTO create(Dept resources);

    /**
     * 编辑
     * @param resources /
     */
    void update(Dept resources);

    /**
     * 删除
     * @param deptDtos /
     *
     */
    void delete(Set<DeptDTO> deptDtos);

    /**
     * 构建树形数据
     * @param deptDtos 原始数据
     * @return /
     */
    Object buildTree(List<DeptDTO> deptDtos);

    /**
     * 根据PID查询
     * @param pid /
     * @return /
     */
    List<Dept> findByPid(long pid);

    /**
     * 根据角色ID查询
     * @param id /
     * @return /
     */
    Set<Dept> findByRoleIds(Long id);

    /**
     * 导出数据
     * @param queryAll 待导出的数据
     * @param response /
     * @throws IOException /
     */
    void download(List<DeptDTO> queryAll, HttpServletResponse response) throws IOException;

    /**
     * 获取待删除的部门
     * @param deptList /
     * @param deptDtos /
     * @return /
     */
    Set<DeptDTO> getDeleteDepts(List<Dept> deptList, Set<DeptDTO> deptDtos);
}