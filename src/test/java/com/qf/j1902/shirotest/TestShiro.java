package com.qf.j1902.shirotest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
/**
 * Created by 杜碧天 on 2019/6/6.
 */
public class TestShiro {
    @Test
    public  void test1(){

        //使用特定的安全管理器工厂产生安全管理器工厂对象
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro_first.ini");
        SecurityManager instance = factory.getInstance();
        SecurityUtils.setSecurityManager(instance);
        Subject subject = SecurityUtils.getSubject();
        //构建一个由用户和密码信息组成的令牌
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "xs123456");
      try {


         //调用主体的login方法判断是否登陆
         subject.login(token);
         //登陆成功值为=true；
         boolean authenticated = subject.isAuthenticated();
         System.out.println(authenticated);
      }catch (AuthenticationException exception){
         System.out.println("用户名或密码错误");
      }
    }
    @Test
    public void test2(){
        //初始化的安全管理器工厂类对象
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro_second.ini");
        //获取安全管理器对象
        SecurityManager instance = factory.getInstance();
        //使用securityUtils来装配管理器
        SecurityUtils.setSecurityManager(instance);
        //
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan","123");
        try {
            subject.login(token);
            boolean authenticated = subject.isAuthenticated();
            if(authenticated){
                boolean role1 = subject.hasRole("role1");
                boolean permitted = subject.isPermitted("user:update");
                boolean permitted1 = subject.isPermitted("user:delete");
                System.out.println(subject.getPrincipal()+"是否有？"+role1);
                System.out.println(subject.getPrincipal()+"是否有？"+permitted);
                System.out.println(subject.getPrincipal()+"是否有？"+permitted1);
            }else {
                System.out.println("账户名或密码有误");
            }
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
    }
    @Test
    public  void test3(){
        //安全管理器初始化工厂
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro_realm.ini");
       //获取安全管理器对象
        SecurityManager securityManager = factory.getInstance();
        //装配管理器
        SecurityUtils.setSecurityManager(securityManager);
        //获取请求对象
        Subject subject = SecurityUtils.getSubject();
        String name="zhangsan";
        String pw="123456";
        UsernamePasswordToken token = new UsernamePasswordToken(name, pw);
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        if(subject.isAuthenticated()){
            System.out.println(name+"通过");
        }else {
            System.out.println(name+"未通过");
        }
    }
    @Test
    public  void test4(){
        //安全管理器初始化工厂
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro_realm.ini");
        //获取安全管理器对象
        SecurityManager securityManager = factory.getInstance();
        //装配管理器
        SecurityUtils.setSecurityManager(securityManager);
        //获取请求对象
        Subject subject = SecurityUtils.getSubject();
        String name="zhangsan";
        String pw="123456";
        UsernamePasswordToken token = new UsernamePasswordToken(name, pw);
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        if(subject.isPermitted("xiaoming:run")){
            System.out.println(subject.getPrincipal()+"vip");
        }else {
            System.out.println(subject.getPrincipal()+"平民玩家");
        }
        if(subject.hasRole("role1")){
            System.out.println(subject.getPrincipal()+"vip");
        }else {
            System.out.println(subject.getPrincipal()+"平民玩家");
        }
    }
    @Test
    public  void test5(){
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro_realm_md5.ini");
        SecurityManager instance = factory.getInstance();
        SecurityUtils.setSecurityManager(instance);
        Subject subject = SecurityUtils.getSubject();
        String name="zhangsan";
        String md5pw = "123";
        //Md5Hash md5pw= new Md5Hash("123",name,1024);
        UsernamePasswordToken token = new UsernamePasswordToken(name,md5pw);
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("sssss");
        }
        if(subject.isAuthenticated()){
            System.out.println("通过");
        }else {
            System.out.println("未通过");
        }
    }
    @Test
    public void test9(){
        Md5Hash md5pw= new Md5Hash("123","zhangsan",1024);
        System.out.println(md5pw);
    }
}
