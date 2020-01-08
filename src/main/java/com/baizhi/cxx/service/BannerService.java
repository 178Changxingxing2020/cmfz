package com.baizhi.cxx.service;

import com.baizhi.cxx.entity.Banner;
import com.baizhi.cxx.Dto.BannerDto;

import java.util.List;

public interface BannerService {
    public List<Banner> queryAll();
    public BannerDto queryPersonByPage(Integer row, Integer page);
    public Banner queryOne(Banner banner);
    public void remove(List<String> list);
    public void modifly(Banner banner);
    public void insert(Banner banner);
}
