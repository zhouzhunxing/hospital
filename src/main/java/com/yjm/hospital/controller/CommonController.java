package com.yjm.hospital.controller;

import com.yjm.hospital.entity.Layui;
import com.yjm.hospital.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.IncorrectCredentialsException;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "共通Controller", value = "共通Controller")
@Controller
public class CommonController {

    @RequestMapping(value = "")
    public String index(Model model, HttpServletResponse response) {
        return "index";
    }
    @RequestMapping(value = "index1")
    public String index1(Model model, HttpServletResponse response) {
        return "index1";
    }

    @RequestMapping(value = "/toData")
    @ResponseBody
    public Layui toData(Model model, HttpServletResponse response) {
        List<Map<Object, Object>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            HashMap<Object, Object> map = new HashMap<>();
            map.put("id", i);
            map.put("username", "张杰" + i);
            map.put("sex", "男");
            map.put("city", "北京" + i);
            map.put("sign", "qq");
            map.put("experience", "100" + i);
            map.put("score", "100");
            map.put("classify", "100");
            map.put("wealth", "100");
            list.add(map);
        }
        return Layui.data(list.size(), list);
    }

//    @ApiOperation(value = "登录接口", notes = "登录接口")
//    @RequestMapping(value = "/userlogin")
//    public String userLogin(Model model, User user, HttpServletResponse response) {
//        if (user == null) {
//            return "login";
//        }
//        String username = user.getUsername();
//        String password = user.getPassword();
//        UsernamePasswordToken token = new UsernamePasswordToken(username, password, false);
//        Subject currentUser = SecurityUtils.getSubject();
//        try {
//            currentUser.login(token);
//            //此步将 调用realm的认证方法
//        } catch (IncorrectCredentialsException e) {
//            //这最好把 所有的 异常类型都背会
//            model.addAttribute("message", "密码错误");
//            return "login";
//        } catch (AuthenticationException e) {
//            model.addAttribute("message", "登录失败");
//            return "login";
//        }
//        return "index";
//    }
//
//    //配合shiro配置中的默认访问url
//    @RequestMapping(value = "/login")
//    public String getLogin(HttpServletRequest request, Model model, HttpSession session, HttpServletResponse response) {
//        return "login";
//    }
//
//    @RequestMapping(value = "/hello")
//    public String hello() {
//        return "NewFile";
//    }
//
//    @RequestMapping(value = "/")
//    public String index() {
//        System.out.println("访问了后端 /  请求");
//        return "login";
//    }
//
//    /**
//     * 退出
//     *
//     * @return
//     */
//    @RequestMapping(value = "logout", method = RequestMethod.GET)
//    public String logout(HttpServletRequest request) {
//
//        //subject的实现类DelegatingSubject的logout方法，将本subject对象的session清空了
//        //即使session托管给了redis ，redis有很多个浏览器的session
//        //只要调用退出方法，此subject的、此浏览器的session就没了
//        try {
//            //退出
//            SecurityUtils.getSubject().logout();
//
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//        }
//        return "login";
//
//    }
//
//    @RequestMapping(value = "403")
//    public String unAuth() {
//
//        return "403";
//    }
}
