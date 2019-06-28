package com.qf.j1902.realm;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
/**
 * Created by 杜碧天 on 2019/6/6.
 */
public class MyRealmMd5 extends AuthorizingRealm{
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String principal = (String)principalCollection.getPrimaryPrincipal();
        return null;
    }
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String principal = (String) authenticationToken.getPrincipal();
        //用用户名字查出密码
        String salt="zhangsan";
        String md5pw="271dad09d1a71f27b7aeaa27306d5e24";
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(principal,md5pw, ByteSource.Util.bytes(salt),getName());
        return simpleAuthenticationInfo;
    }
}
