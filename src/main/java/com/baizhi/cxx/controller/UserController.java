package com.baizhi.cxx.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import com.baizhi.cxx.Dto.AlbumDto;
import com.baizhi.cxx.Dto.MapDto;
import com.baizhi.cxx.dao.UserDao;
import com.baizhi.cxx.entity.Album;
import com.baizhi.cxx.entity.User;
import com.baizhi.cxx.service.AlbumService;
import com.baizhi.cxx.util.HttpUtil;
import io.goeasy.GoEasy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.UnknownHostException;
import java.util.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserDao userDao;

    @RequestMapping("queryUserByTime")
    @ResponseBody
    public Map queryUserByTime(){
        HashMap hashMap = new HashMap();
        List manList = new ArrayList();
        manList.add(userDao.queryUserByTime("1",1));
        manList.add(userDao.queryUserByTime("1",7));
        manList.add(userDao.queryUserByTime("1",30));
        manList.add(userDao.queryUserByTime("1",365));
        List womenList = new ArrayList();
        womenList.add(userDao.queryUserByTime("0",1));
        womenList.add(userDao.queryUserByTime("0",7));
        womenList.add(userDao.queryUserByTime("0",30));
        womenList.add(userDao.queryUserByTime("0",365));
        hashMap.put("man",manList);
        hashMap.put("women",womenList);
        return hashMap;
    }

    @RequestMapping("addUser")
    @ResponseBody
    public void addUser(){
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setLocation("河南");
        user.setRigestDate(new Date());
        user.setSex("1");
        userDao.insert(user);
        GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io", "BC-0ba8670ad5894f69b4ee326915c50e93");
        Map map = queryUserByTime();
        String s = JSONUtils.toJSONString(map);
        System.out.println(s);
        goEasy.publish("cmfz", s);
    }

    @RequestMapping("queryUserByLocation")
    @ResponseBody
    public Map queryUserByLocation(){
        HashMap hashMap = new HashMap();
        List<MapDto> man = userDao.queryUserByLocation("1");

        List<MapDto> women = userDao.queryUserByLocation("0");

        hashMap.put("man",man);
        hashMap.put("women",women);
        return hashMap;
    }

    @RequestMapping("addUserMap")
    @ResponseBody
    public void addUserMap(){
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setLocation("河南");
        user.setRigestDate(new Date());
        user.setSex("1");
        userDao.insert(user);
        GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io", "BC-0ba8670ad5894f69b4ee326915c50e93");
        Map map = queryUserByLocation();
        String s = JSONObject.toJSONString(map);
        System.out.println(s);
        goEasy.publish("cmfz", s);
    }
}
