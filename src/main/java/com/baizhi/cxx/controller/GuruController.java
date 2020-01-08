package com.baizhi.cxx.controller;

import com.baizhi.cxx.dao.GuruDao;
import com.baizhi.cxx.entity.Admin;
import com.baizhi.cxx.entity.Banner;
import com.baizhi.cxx.entity.Guru;
import com.baizhi.cxx.service.AdminService;
import com.baizhi.cxx.util.CreateValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/guru")
public class GuruController {

    @Autowired
    private GuruDao guruDao;

    @RequestMapping("queryAll")
    @ResponseBody
    public List<Guru> queryAll(){
        List<Guru> list = guruDao.selectAll();
        return list;
    }
}
