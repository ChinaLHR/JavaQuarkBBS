package com.quark.admin.shiro;

import com.quark.admin.service.AdminUserService;
import com.quark.admin.service.PermissionService;
import com.quark.common.entity.AdminUser;
import com.quark.common.entity.Permission;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lhr on 17-8-1.
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Resource
    private AdminUserService adminUserService;

    @Resource
    private PermissionService permissionService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Integer id = (Integer) principalCollection.getPrimaryPrincipal();
        List<Permission> permissionList = permissionService.loadUserPermission(id);
        // 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        permissionList.forEach(p->info.addStringPermission(p.getPerurl()));
        return info;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户的输入的账号.
        String username = (String)token.getPrincipal();
        AdminUser user = adminUserService.findByUserName(username);
        if(user==null) throw new UnknownAccountException();
        if (0==user.getEnable()) {
            throw new LockedAccountException(); // 帐号锁定
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getId(), //用户
                user.getPassword(), //密码
                ByteSource.Util.bytes(username),
                getName() //realm name
        );
        // 把用户信息放在session里
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("AdminSession", user);
        session.setAttribute("AdminSessionId", user.getId());
        return authenticationInfo;
    }
}
