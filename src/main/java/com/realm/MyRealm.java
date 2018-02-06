package com.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.model.User;
import com.service.UserService;

public class MyRealm extends AuthorizingRealm {
	
	@Autowired
	private UserService userService;

	@Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String)principals.getPrimaryPrincipal();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(userService.queryUserRoles(username));
        authorizationInfo.setStringPermissions(userService.queryUserPermissions(username));
        return authorizationInfo;
    }

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		User user = userService.queryUserByUserName(username);
		
		if (user == null) {
            // 用户名不存在抛出异常
            throw new UnknownAccountException();
        }
		
        if (user.getLocked() == 1) {
            // 用户被管理员锁定抛出异常
            throw new LockedAccountException();
        }
        
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(),
                user.getPassword(), getName());
        Subject subject = SecurityUtils.getSubject();
        subject.getSession().setAttribute("user", user);
        return authenticationInfo;
	}

}
