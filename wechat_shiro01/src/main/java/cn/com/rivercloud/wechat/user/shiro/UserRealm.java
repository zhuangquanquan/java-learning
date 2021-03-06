package cn.com.rivercloud.wechat.user.shiro;

import cn.com.rivercloud.wechat.user.entity.User;
import cn.com.rivercloud.wechat.user.jwt.JwtToken;
import cn.com.rivercloud.wechat.user.jwt.JwtUtils;
import cn.com.rivercloud.wechat.user.service.RoleService;
import cn.com.rivercloud.wechat.user.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    RoleService roleService;

    @Autowired
    JwtUtils jwtUtils;

    /**
     * 必须重写此方法，不然会报错
     */

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     *  默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("————身份认证方法————");
        String token = (String) authenticationToken.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String userId = jwtUtils.getClaimByToken(token).getSubject();
        User user = userService.getUserById(Long.valueOf(userId));
        if (user == null) {
            throw new UnknownAccountException("账户不存在");
        }
        /*int ban = userMapper.checkUserBanStatus(username);
        if (ban == 1) {
            throw new AuthenticationException("该用户已被封号！");
        }*/
        return new SimpleAuthenticationInfo(token, token, getName());
    }

    /**
     *  只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("————权限认证————");
        String userId = jwtUtils.getClaimByToken(principals.toString()).getSubject();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        String roleName = roleService.selectRoleByUserId(Long.valueOf(userId));
        if (Strings.isEmpty(roleName)) {
            log.error("用户没有对应的角色表,用户Id:" + userId);
        }
        Set<String> roleSet = new HashSet<>();
        roleSet.add(roleName);
        info.setRoles(roleSet);

        //获得该用户角色
        //User user = userService.getOne(new QueryWrapper<User>().eq("username", user.getUserName()));
        //每个角色拥有默认的权限
        //String rolePermission = userService.getRolePermission(user.getUserName());
        //每个用户可以设置新的权限
        //String permission = userService.getPermission(user.getUserName());
        //Set<String> roleSet = new HashSet<>();
        //Set<String> permissionSet = new HashSet<>();
        //需要将 role, permission 封装到 Set 作为 info.setRoles(), info.setStringPermissions() 的参数
        //roleSet.add(user.getRole());
        //permissionSet.add(rolePermission);
        //permissionSet.add(permission);
        //设置该用户拥有的角色和权限
        //info.setRoles(roleSet);
        //info.setStringPermissions(permissionSet);
        return info;
    }
}
