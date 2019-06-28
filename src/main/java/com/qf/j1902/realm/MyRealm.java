package com.qf.j1902.realm;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import java.util.HashSet;
import java.util.Set;
/**
 * Created by 杜碧天 on 2019/6/6.
 */
public class  MyRealm extends AuthorizingRealm{
    //负责用户的授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取用户名
        String principal =(String) principalCollection.getPrimaryPrincipal();
        //根据用户名查询权限//查出来的应该是个集合
        //将userService注入，
        //userService.chaPermissionBy(principal)
        Set<String> permissions = new HashSet<>();
            permissions.add("user:delete");
        permissions.add("user:create");
        permissions.add("xiaoming:run");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setStringPermissions(permissions);//StringPermissions(permissions);
        authorizationInfo.addRole("role1");
        return authorizationInfo;
    }
//负责用户的认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
       //获取当前用户名
        String principal =(String) authenticationToken.getPrincipal();
        //System.out.println(principal);
        //根据账号查出密码
        //假设123456为查出来的密码
        String pw="123456";
        //将查出的账号和密码封装成一个autheactionInfo对象，返回
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(principal, pw, getName());
        return authenticationInfo;
    }
}
