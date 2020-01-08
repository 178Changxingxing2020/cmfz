package com.baizhi.cxx.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baizhi.cxx.dao.BannerDao;
import com.baizhi.cxx.entity.Banner;
import com.baizhi.cxx.service.BannerService;
import com.baizhi.cxx.serviceImpl.BannerServiceImpl;
import com.baizhi.cxx.util.MyWebAware;

import java.util.ArrayList;
import java.util.List;

public class BannerDateListener extends AnalysisEventListener<Banner> {


    List<Banner> list = new ArrayList<>();
    @Override
    public void invoke(Banner banner, AnalysisContext analysisContext) {
        list.add(banner);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        BannerDao dao = (BannerDao) MyWebAware.getBeanByClass(BannerDao.class);
        for (Banner banner : list) {
            dao.insert(banner);
        }
    }
}
