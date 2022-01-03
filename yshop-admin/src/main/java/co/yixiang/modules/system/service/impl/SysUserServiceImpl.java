/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.yixiang.co
 * 注意：
 * 本软件为www.yixiang.co开发研制
 */
package co.yixiang.modules.system.service.impl;

import cn.hutool.core.date.DateUtil;
import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.common.utils.QueryHelpPlus;
import co.yixiang.dozer.service.IGenerator;
import co.yixiang.exception.BadRequestException;
import co.yixiang.exception.EntityExistException;
import co.yixiang.modules.system.domain.Role;
import co.yixiang.modules.system.domain.User;
import co.yixiang.modules.system.domain.UserAvatar;
import co.yixiang.modules.system.domain.UsersRoles;
import co.yixiang.modules.system.service.*;
import co.yixiang.modules.system.service.dto.UserDto;
import co.yixiang.modules.system.service.dto.UserQueryCriteria;
import co.yixiang.modules.system.service.mapper.RoleMapper;
import co.yixiang.modules.system.service.mapper.SysUserMapper;
import co.yixiang.utils.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;

/**
 * @author hupeng
 * @date 2020-05-14
 */
@Service
//@AllArgsConstructor
//@CacheConfig(cacheNames = "user")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, User> implements UserService {

    @Value("${file.avatar}")
    private String avatar;

    private final IGenerator generator;
    private final SysUserMapper userMapper;
    private final UserAvatarService userAvatarService;
    private final JobService jobService;
    private final DeptService deptService;
    private final RoleMapper roleMapper;
    private final RedisUtils redisUtils;
    private final UsersRolesService usersRolesService;

    public SysUserServiceImpl(IGenerator generator, SysUserMapper userMapper, UserAvatarService userAvatarService, JobService jobService, DeptService deptService, RoleMapper roleMapper, RedisUtils redisUtils, UsersRolesService usersRolesService) {
        this.generator = generator;
        this.userMapper = userMapper;
        this.userAvatarService = userAvatarService;
        this.jobService = jobService;
        this.deptService = deptService;
        this.roleMapper = roleMapper;
        this.redisUtils = redisUtils;
        this.usersRolesService = usersRolesService;
    }

    @Override
    //@Cacheable
    public Map<String, Object> queryAll(UserQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<User> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), UserDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<User> queryAll(UserQueryCriteria criteria) {
        List<User> userList = baseMapper.selectList(QueryHelpPlus.getPredicate(User.class, criteria));
        for (User user : userList) {
            user.setJob(jobService.getById(user.getJobId()));
            user.setDept(deptService.getById(user.getDeptId()));
            user.setRoles(roleMapper.findByUsers_Id(user.getId()));
        }
        return userList;
    }


    @Override
    public void download(List<UserDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (UserDto user : all) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("邮箱", user.getEmail());
            map.put("状态：1启用、0禁用", user.getEnabled());
            map.put("密码", user.getPassword());
            map.put("用户名", user.getUsername());
            map.put("部门名称", user.getDeptId());
            map.put("手机号码", user.getPhone());
            map.put("创建日期", user.getCreateTime());
            map.put("最后修改密码的日期", user.getLastPasswordResetTime());
            map.put("昵称", user.getNickName());
            map.put("性别", user.getSex());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    /**
     * 根据用户名查询
     *
     * @param userName /
     * @return /
     */
    @Override
    public UserDto findByName(String userName) {
        User user = userMapper.findByName(userName);
        //用户所属岗位
        user.setJob(jobService.getById(user.getJobId()));
        //用户所属部门
        user.setDept(deptService.getById(user.getDeptId()));
        return generator.convert(user, UserDto.class);
    }

    /**
     * 修改密码
     *
     * @param username        用户名
     * @param encryptPassword 密码
     */
    @Override
    public void updatePass(String username, String encryptPassword) {
        userMapper.updatePass(encryptPassword, DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"), username);
    }

    /**
     * 修改头像
     *
     * @param multipartFile 文件
     */
    @Override
    public void updateAvatar(MultipartFile multipartFile) {
        User user = this.getOne(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, SecurityUtils.getUsername()));
        UserAvatar userAvatar = userAvatarService.getOne(new LambdaQueryWrapper<UserAvatar>()
                .eq(UserAvatar::getId, user.getAvatarId()));
        String oldPath = "";
        if (userAvatar != null) {
            oldPath = userAvatar.getPath();
        } else {
            userAvatar = new UserAvatar();
        }
        File file = FileUtil.upload(multipartFile, avatar);
        assert file != null;
        userAvatar.setRealName(file.getName());
        userAvatar.setPath(file.getPath());
        userAvatar.setSize(FileUtil.getSize(multipartFile.getSize()));
        userAvatarService.saveOrUpdate(userAvatar);
        user.setAvatarId(userAvatar.getId());
        this.saveOrUpdate(user);
        if (StringUtils.isNotBlank(oldPath)) {
            FileUtil.del(oldPath);
        }
    }

    /**
     * 修改邮箱
     *
     * @param username 用户名
     * @param email    邮箱
     */
    @Override
    public void updateEmail(String username, String email) {
        userMapper.updateEmail(email, username);
    }

    /**
     * 新增用户
     *
     * @param resources /
     * @return /
     */
    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public boolean create(User resources) {
        User userName = this.getOne(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, resources.getUsername()));
        if (userName != null) {
            throw new EntityExistException(User.class, "username", resources.getUsername());
        }
        User userEmail = this.getOne(new LambdaQueryWrapper<User>()
                .eq(User::getEmail, resources.getEmail()));
        if (userEmail != null) {
            throw new EntityExistException(User.class, "email", resources.getEmail());
        }
        resources.setDeptId(resources.getDept().getId());
        resources.setJobId(resources.getJob().getId());
        boolean result = this.save(resources);
        UsersRoles usersRoles = new UsersRoles();
        usersRoles.setUserId(resources.getId());
        Set<Role> set = resources.getRoles();
        for (Role roleIds : set) {
            usersRoles.setRoleId(roleIds.getId());
        }
        if (result) {
            usersRolesService.save(usersRoles);
        }
        return result;
    }

    /**
     * 编辑用户
     *
     * @param resources /
     */
    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(User resources) {
        User user = this.getOne(new LambdaQueryWrapper<User>()
                .eq(User::getId, resources.getId()));
        ValidationUtil.isNull(user.getId(), "User", "id", resources.getId());
        User user1 = this.getOne(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, resources.getUsername()));
        User user2 = this.getOne(new LambdaQueryWrapper<User>()
                .eq(User::getEmail, resources.getEmail()));

        if (user1 != null && !user.getId().equals(user1.getId())) {
            throw new BadRequestException("当前用户名已存在");
        }

        if (user2 != null && !user.getId().equals(user2.getId())) {
            throw new EntityExistException(User.class, "email", resources.getEmail());
        }
        user.setUsername(resources.getUsername());
        user.setEmail(resources.getEmail());
        user.setEnabled(resources.getEnabled());
        user.setDeptId(resources.getDept().getId());
        user.setJobId(resources.getJob().getId());
        user.setPhone(resources.getPhone());
        user.setNickName(resources.getNickName());
        user.setSex(resources.getSex());
        boolean result = this.saveOrUpdate(user);
        usersRolesService.lambdaUpdate().eq(UsersRoles::getUserId, resources.getId()).remove();
        UsersRoles usersRoles = new UsersRoles();
        usersRoles.setUserId(resources.getId());
        Set<Role> set = resources.getRoles();
        for (Role roleIds : set) {
            usersRoles.setRoleId(roleIds.getId());
        }
        if (result) {
            usersRolesService.save(usersRoles);
        }

        // 如果用户的角色改变了，需要手动清理下缓存
        if (!resources.getRoles().equals(user.getRoles())) {
            String key = "role::loadPermissionByUser:" + user.getUsername();
            redisUtils.del(key);
            key = "role::findByUsers_Id:" + user.getId();
            redisUtils.del(key);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Set<Long> ids) {
        for (Long id : ids) {
            usersRolesService.lambdaUpdate().eq(UsersRoles::getUserId, id).remove();
        }
        this.removeByIds(ids);
    }

}
