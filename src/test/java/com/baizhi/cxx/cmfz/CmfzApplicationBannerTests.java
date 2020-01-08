package com.baizhi.cxx.cmfz;

import com.baizhi.cxx.dao.AdminDao;
import com.baizhi.cxx.dao.BannerDao;
import com.baizhi.cxx.entity.Admin;
import com.baizhi.cxx.entity.Banner;
import com.baizhi.cxx.service.BannerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CmfzApplicationBannerTests {

    @Autowired
    AdminDao adminDao;
    @Autowired
    BannerDao bannerDao;
    @Autowired
    BannerService bannerService;

    @Test
    public void queryall() {
        List<Banner> list = bannerDao.selectAll();
        for (Banner banner : list) {
            System.out.println(banner);
        }
    }
    @Test
    public void queryOne(){
        Banner banner = new Banner();
        banner.setId("1");
        Banner banner1 = bannerDao.selectOne(banner);
        System.out.println(banner1);
    }
    @Test
    public void delelte(){
        Banner banner = new Banner();
        banner.setId("2");
       // Banner banner1 = bannerDao.selectOne(banner);
        bannerDao.deleteByPrimaryKey(banner);

        queryall();
    }

    @Test
    public void update(){
        Banner banner = new Banner();
        banner.setId("2");
//        Banner banner1 = bannerDao.selectOne(banner);
        banner.setStatus("0");
        bannerService.modifly(banner);
        //System.out.println(i);
        System.out.println(banner);


        //queryall();
    }


}
