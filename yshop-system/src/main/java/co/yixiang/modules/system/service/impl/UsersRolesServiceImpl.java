/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co
 */
package co.yixiang.modules.system.service.impl;

import co.yixiang.common.service.impl.BaseServiceImpl;
import co.yixiang.modules.system.domain.UsersRoles;
import co.yixiang.modules.system.service.UsersRolesService;
import co.yixiang.modules.system.service.mapper.UsersRolesMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
* @author hupeng
* @date 2020-05-16
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "usersRoles")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UsersRolesServiceImpl extends BaseServiceImpl<UsersRolesMapper, UsersRoles> implements UsersRolesService {

}
