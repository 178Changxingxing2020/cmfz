package com.baizhi.cxx.controller;

import com.baizhi.cxx.entity.Admin;
import com.baizhi.cxx.service.AdminService;
import com.baizhi.cxx.util.CreateValidateCode;
import io.netty.handler.codec.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.resource.HttpResource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("login")
    @ResponseBody
    public String AdminLogin(String username,String password,HttpSession session,String checkCode){
        String message=null;
        String code = session.getAttribute("code").toString();
        if(code.equals(checkCode)) {
            Admin admin = new Admin(null, username, null);
            Admin admin1 = adminService.queryOne(admin);
            if (admin1 != null) {
                if (admin1.getPassword().equals(password)) {
                    message = "登陆成功";
                    session.setAttribute("admin",admin1);
                } else {
                    message = "密码错误";
                }
            } else {
                message = "用户名错误";
            }
        }else {
            message="验证码错误";
        }
        return message;
    }

    @RequestMapping("exit")
    public String AdminExit(HttpSession session){
        session.removeAttribute("admin");
        return  "redirect:/jsp/login.jsp";
    }

    // 验证码
    @RequestMapping("checkCode")
    public void checkCode(HttpServletResponse response, HttpSession session) {
        CreateValidateCode vCode = new CreateValidateCode();
        //得到随机码
        String code = vCode.getCode();
        try {
            vCode.write(response.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
        //HttpSession session = ServletActionContext.getRequest().getSession(true);
        session.setAttribute("code", code);


    }


}
