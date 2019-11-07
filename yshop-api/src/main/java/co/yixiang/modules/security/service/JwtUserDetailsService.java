package co.yixiang.modules.security.service;

import co.yixiang.exception.BadRequestException;
import co.yixiang.modules.security.security.JwtUser;
import co.yixiang.modules.user.entity.YxUser;
import co.yixiang.modules.user.service.YxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author hupeng
 * @since 2019-10-16
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class JwtUserDetailsService implements UserDetailsService {


    @Autowired
    private YxUserService yxUserService;

    @Autowired
    private JwtPermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username){

        YxUser user = yxUserService.findByName(username);
        if (user == null) {
            throw new BadRequestException("账号不存在");
        } else {
            return createJwtUser(user);
        }
    }

    public UserDetails createJwtUser(YxUser user) {
        return new JwtUser(
                user.getUid().longValue(),
                user.getUsername(),
                user.getPassword(),
                user.getAvatar(),
                user.getPhone(),
                permissionService.mapToGrantedAuthorities(user),
                user.getStatus(),
                user.getAddTime()
        );
    }
}
