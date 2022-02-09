package com.yjm.hospital.shiro.realm;

import com.yjm.hospital.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

public class UserRealm extends AuthorizingRealm {

    /**
     * 执行授权逻辑
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("执行授权逻辑!");
        //给资源进行授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //添加授权字符串,就是在shiroConfig中授权时定义的字符串
        //到数据库中查询当前登录用户的授权字符串
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //要想获取到当前用户,需要在下面的认证逻辑完成传过来
        User user = (User) subject.getPrincipal();
        //然后添加授权字符串
          info.addStringPermission("user:add");
        //   info.addStringPermissions();  添加一个集合
        return info;
    }


    /**
     * 执行认证逻辑
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行认证逻辑!");
        //模拟数据库中的用户名和密码
        String username = "aaa";
        String password = "123456";
        //编写Shiro的判断逻辑,判断用户名和密码
        UsernamePasswordToken token1 = (UsernamePasswordToken) token;
        //判断用户名
        if (!token1.getUsername().equals(username)) {
            //用户名不存在!
            return null; //Shiro底层会抛出UnKnowAccountException
        }
        //判断密码
        return new SimpleAuthenticationInfo("", password, "");
    }

}

