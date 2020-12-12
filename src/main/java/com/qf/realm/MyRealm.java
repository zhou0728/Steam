package com.qf.realm;

import com.qf.dao.PermissionMapper;
import com.qf.pojo.Permission;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;

public class MyRealm extends AuthorizingRealm {

//    @Autowired
//    TUserMapper tUserMapper;

    @Autowired
    PermissionMapper permissionMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        List<Permission> permissionByUsername = permissionMapper.findPermissionByUsername(username);
        //去重
        HashSet<String> strings = new HashSet<>();
        for (Permission permission : permissionByUsername) {
            strings.add(permission.getPermissionName());
        }
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(strings);

        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
//        TUser byName = tUserMapper.findByName(username);
//        SimpleAuthenticationInfo simpleAuthenticationInfo = null;
//        if (byName != null){
//            simpleAuthenticationInfo = new SimpleAuthenticationInfo(username, byName.getPassword(), getName());
//        }
//        return simpleAuthenticationInfo;

        return null;
    }
}
