/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.common.utils.QueryHelpPlus;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.exception.BadRequestException;
import co.yixiang.exception.EntityExistException;
import co.yixiang.modules.system.domain.Menu;
import co.yixiang.modules.system.domain.vo.MenuMetaVo;
import co.yixiang.modules.system.domain.vo.MenuVo;
import co.yixiang.modules.system.service.MenuService;
import co.yixiang.modules.system.service.dto.MenuDto;
import co.yixiang.modules.system.service.dto.MenuQueryCriteria;
import co.yixiang.modules.system.service.dto.RoleSmallDto;
import co.yixiang.modules.system.service.mapper.MenuMapper;
import co.yixiang.modules.system.service.mapper.RoleMapper;
import co.yixiang.utils.FileUtil;
import co.yixiang.utils.StringUtils;
import co.yixiang.utils.ValidationUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;

/**
 * @author hupeng
 * @date 2020-05-14
 */
@Service
@AllArgsConstructor
@CacheConfig(cacheNames = "menu")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class MenuServiceImpl extends BaseServiceImpl<MenuMapper, Menu> implements MenuService {

    private final IGenerator generator;
    private final MenuMapper menuMapper;
    private final RoleMapper roleMapper;

    @Override
    @Cacheable
    public Map<String, Object> queryAll(MenuQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<Menu> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), MenuDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    @Cacheable
    public List<Menu> queryAll(MenuQueryCriteria criteria) {
        return baseMapper.selectList(QueryHelpPlus.getPredicate(Menu.class, criteria));
    }


    @Override
    public void download(List<MenuDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (MenuDto menu : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("是否外链", menu.getIFrame());
            map.put("菜单名称", menu.getName());
            map.put("组件", menu.getComponent());
            map.put("上级菜单ID", menu.getPid());
            map.put("排序", menu.getSort());
            map.put("图标", menu.getIcon());
            map.put("链接地址", menu.getPath());
            map.put("缓存", menu.getCache());
            map.put("是否隐藏", menu.getHidden());
            map.put("组件名称", menu.getComponentName());
            map.put("创建日期", menu.getCreateTime());
            map.put("权限", menu.getPermission());
            map.put("类型", menu.getType());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    /**
     * 构建菜单树
     *
     * @param menuDtos 原始数据
     * @return /
     */
    @Override
    public Map<String, Object> buildTree(List<MenuDto> menuDtos) {
        List<MenuDto> trees = new ArrayList<>();
        Set<Long> ids = new HashSet<>();
        for (MenuDto menuDto : menuDtos) {
            if (menuDto.getPid() == 0) {
                trees.add(menuDto);
            }
            for (MenuDto it : menuDtos) {
                if (it.getPid().equals(menuDto.getId())) {
                    if (menuDto.getChildren() == null) {
                        menuDto.setChildren(new ArrayList<>());
                    }
                    menuDto.getChildren().add(it);
                    ids.add(it.getId());
                }
            }
        }
        Map<String, Object> map = new HashMap<>(2);
        if (trees.size() == 0) {
            trees = menuDtos.stream().filter(s -> !ids.contains(s.getId())).collect(Collectors.toList());
        }
        map.put("content", trees);
        map.put("totalElements", menuDtos.size());
        return map;
    }

    /**
     * 构建菜单树
     *
     * @param menuDtos /
     * @return /
     */
    @Override
    public List<MenuVo> buildMenus(List<MenuDto> menuDtos) {
        List<MenuVo> list = new LinkedList<>();
        menuDtos.forEach(menuDTO -> {
                    if (menuDTO != null) {
                        List<MenuDto> menuDtoList = menuDTO.getChildren();
                        MenuVo menuVo = new MenuVo();
                        menuVo.setName(ObjectUtil.isNotEmpty(menuDTO.getComponentName()) ? menuDTO.getComponentName() : menuDTO.getName());
                        // 一级目录需要加斜杠，不然会报警告
                        menuVo.setPath(menuDTO.getPid() == 0 ? "/" + menuDTO.getPath() : menuDTO.getPath());
                        menuVo.setHidden(menuDTO.getHidden());
                        // 如果不是外链
                        if (!menuDTO.getIFrame()) {
                            if (menuDTO.getPid() == 0) {
                                menuVo.setComponent(StrUtil.isEmpty(menuDTO.getComponent()) ? "Layout" : menuDTO.getComponent());
                            } else if (!StrUtil.isEmpty(menuDTO.getComponent())) {
                                menuVo.setComponent(menuDTO.getComponent());
                            }
                        }
                        menuVo.setMeta(new MenuMetaVo(menuDTO.getName(), menuDTO.getIcon(), !menuDTO.getCache()));
                        if (menuDtoList != null && menuDtoList.size() != 0) {
                            menuVo.setAlwaysShow(true);
                            menuVo.setRedirect("noredirect");
                            menuVo.setChildren(buildMenus(menuDtoList));
                            // 处理是一级菜单并且没有子菜单的情况
                        } else if (menuDTO.getPid() == 0) {
                            MenuVo menuVo1 = new MenuVo();
                            menuVo1.setMeta(menuVo.getMeta());
                            // 非外链
                            if (!menuDTO.getIFrame()) {
                                menuVo1.setPath("index");
                                menuVo1.setName(menuVo.getName());
                                menuVo1.setComponent(menuVo.getComponent());
                            } else {
                                menuVo1.setPath(menuDTO.getPath());
                            }
                            menuVo.setName(null);
                            menuVo.setMeta(null);
                            menuVo.setComponent("Layout");
                            List<MenuVo> list1 = new ArrayList<>();
                            list1.add(menuVo1);
                            menuVo.setChildren(list1);
                        }
                        list.add(menuVo);
                    }
                }
        );
        return list;
    }

    /**
     * 获取菜单树
     *
     * @param menus /
     * @return /
     */
    @Override
    @Cacheable(key = "'tree'")
    public Object getMenuTree(List<Menu> menus) {
        List<Map<String, Object>> list = new LinkedList<>();
        menus.forEach(menu -> {
                    if (menu != null) {
                        List<Menu> menuList = menuMapper.findByPid(menu.getId());
                        Map<String, Object> map = new HashMap<>(16);
                        map.put("id", menu.getId());
                        map.put("label", menu.getName());
                        if (menuList != null && menuList.size() != 0) {
                            map.put("children", getMenuTree(menuList));
                        }
                        list.add(map);
                    }
                }
        );
        return list;
    }

    /**
     * 获取待删除的菜单
     *
     * @param menuList /
     * @param menuSet  /
     * @return /
     */
    @Override
    public Set<Menu> getDeleteMenus(List<Menu> menuList, Set<Menu> menuSet) {
        // 递归找出待删除的菜单
        for (Menu menu1 : menuList) {
            menuSet.add(menu1);
            List<Menu> menus = menuMapper.findByPid(menu1.getId());
            if (menus != null && menus.size() != 0) {
                getDeleteMenus(menus, menuSet);
            }
        }
        return menuSet;
    }

    /**
     * 根据pid查询
     *
     * @param pid /
     * @return /
     */
    @Override
    @Cacheable(key = "'pid:'+#p0")
    public List<Menu> findByPid(long pid) {
        return menuMapper.findByPid(pid);
    }

    /**
     * 根据角色查询
     *
     * @param roles /
     * @return /
     */
    @Override
    public List<MenuDto> findByRoles(List<RoleSmallDto> roles) {
        List<Long> roleIds = roles.stream().map(i -> {
            Long role = i.getId();
            return role;
        }).collect(Collectors.toList());
        List<Menu> list = menuMapper.selectListByRoles(roleIds);

        return generator.convert(list, MenuDto.class);
    }

    /**
     * 删除
     *
     * @param menuSet /
     */
    @Override
    @CacheEvict(allEntries = true)
    public void delete(Set<Menu> menuSet) {
        for (Menu menu : menuSet) {
            roleMapper.untiedMenu(menu.getId());
            this.removeById(menu.getId());
        }
    }

    /**
     * 编辑
     *
     * @param resources /
     */
    @Override
    @CacheEvict(allEntries = true)
    public void update(Menu resources) {
        if (resources.getId().equals(resources.getPid())) {
            throw new BadRequestException("上级不能为自己");
        }
        Menu menu = this.getById(resources.getId());
        ValidationUtil.isNull(menu.getId(), "Permission", "id", resources.getId());

        isExitHttp(resources);

        Menu menu1 = this.getOne(new LambdaQueryWrapper<Menu>().eq(Menu::getName, resources.getName()));

        if (menu1 != null && !menu1.getId().equals(menu.getId())) {
            throw new EntityExistException(Menu.class, "name", resources.getName());
        }

        if (StringUtils.isNotBlank(resources.getComponentName())) {
            menu1 = this.getOne(new LambdaQueryWrapper<Menu>().eq(Menu::getComponentName, resources.getComponentName()));
            if (menu1 != null && !menu1.getId().equals(menu.getId())) {
                throw new EntityExistException(Menu.class, "componentName", resources.getComponentName());
            }
        }

        menu.setId(resources.getId());
        menu.setName(resources.getName());
        menu.setComponent(resources.getComponent());
        menu.setPath(resources.getPath());
        menu.setIcon(resources.getIcon());
        menu.setIFrame(resources.getIFrame());
        menu.setPid(resources.getPid());
        menu.setSort(resources.getSort());
        menu.setCache(resources.getCache());
        menu.setHidden(resources.getHidden());
        menu.setComponentName(resources.getComponentName());
        menu.setPermission(resources.getPermission());
        menu.setType(resources.getType());
        this.saveOrUpdate(menu);
    }

    @Override
    @CacheEvict(allEntries = true)
    public MenuDto create(Menu resources) {
        isExitHttp(resources);
        if (this.getOne(new LambdaQueryWrapper<Menu>().eq(Menu::getName, resources.getName())) != null) {
            throw new EntityExistException(Menu.class, "name", resources.getName());
        }
        if (StringUtils.isNotBlank(resources.getComponentName())) {
            if (this.getOne(new LambdaQueryWrapper<Menu>().eq(Menu::getComponentName, resources.getComponentName())) != null) {
                throw new EntityExistException(Menu.class, "componentName", resources.getComponentName());
            }
        }
        this.save(resources);
        return generator.convert(resources, MenuDto.class);
    }

    /**
     * 公共方法提取出来
     * @param resources
     */
    private void isExitHttp(Menu resources) {
        if (resources.getIFrame()) {
            String http = "http://", https = "https://";
            if (!(resources.getPath().toLowerCase().startsWith(http) || resources.getPath().toLowerCase().startsWith(https))) {
                throw new BadRequestException("外链必须以http://或者https://开头");
            }
        }
    }
}
