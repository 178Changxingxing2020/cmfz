package com.baizhi.cxx.cmfz;

import com.baizhi.cxx.dao.AdminDao;
import com.baizhi.cxx.entity.Admin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CmfzApplicationTests {

    @Autowired
    AdminDao adminDao;

    @Test
    public void contextLoads() {
        List<Admin> list = adminDao.selectAll();
        for (Admin admin : list) {
            System.out.println(admin);
        }
    }
    @Test
    public void queryOne(){
        Admin admin = adminDao.selectOne(new Admin(null, "admin", null));
        System.out.println(admin);
    }

}
