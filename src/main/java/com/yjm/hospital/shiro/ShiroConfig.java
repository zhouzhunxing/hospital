package com.yjm.hospital.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.yjm.hospital.shiro.realm.UserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Filter;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
        //关联SecurityManager 配置核心安全事务管理器
        bean.setSecurityManager(securityManager);
        //配置登录的URL（未登录的用户访问的页面）
        //bean.setLoginUrl("/auth/login");
        // 配置登录成功的url(登录后用户访问的页面)
        //bean.setSuccessUrl("/auth/index");

        //自定义拦截器
        Map<String, Filter> MyfiltersMap=new LinkedHashMap<String,Filter>();
        //限制同一个账号的同时在线个数
        //MyfiltersMap.put("kickout",kickout)
        //配置访问权限
        //<url,权限类型>
        Map<String,String> filterChainDefinitionMap=new LinkedHashMap<>();
        //注意过滤器配置顺序 不能颠倒
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了，登出后跳转配置的loginUrl
        filterChainDefinitionMap.put("/auth/logout","logout");
        // 配置不会被拦截的链接 顺序判断
        //authc:所有url都必须认证通过才可以访问;
        // anon:所有url都都可以匿名访问
        filterChainDefinitionMap.put("/css/**","anon");
        filterChainDefinitionMap.put("/js/**","anon");
        filterChainDefinitionMap.put("/img/**","authc");//表示需要认证才可以访问
        filterChainDefinitionMap.put("/auth/login","anon");
        filterChainDefinitionMap.put("/*", "anon");
        filterChainDefinitionMap.put("/**", "anon");
        //设置授权形式的访问
        filterChainDefinitionMap.put("/sardine/**","perms[admin:play]");

        //设置未授权界面
        bean.setUnauthorizedUrl("/403");
        //配置shiro的主要拦截器（默认有个拦截器）
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return bean;
    }

    //DefaultwebSecurityManager 配置核心安全事务管理器
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联Realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    //创建realm对象（需要自定义）
    @Bean
    public UserRealm userRealm(@Qualifier("credentialsMatcher") HashedCredentialsMatcher credentialsMatcher) {
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(credentialsMatcher);
        return userRealm;
    }

    //以上三层是shiro的三层结构
    // 配置密码比较器（密码加密）
    @Bean
    public HashedCredentialsMatcher credentialsMatcher() {

        //RetryLimitHashedCredentialsMatcher为另外类的构造函数
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        credentialsMatcher.setHashIterations(0);//散列的次数，比如散列两次，相当于 md5(md5(""));
        return credentialsMatcher;
    }

    //4.配置Thymleaf的Shiro扩展标签
    @Bean
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }
}

