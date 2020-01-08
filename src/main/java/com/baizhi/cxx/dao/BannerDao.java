package com.baizhi.cxx.dao;

import com.baizhi.cxx.entity.Banner;
import tk.mybatis.mapper.additional.idlist.DeleteByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BannerDao extends Mapper<Banner> , DeleteByIdListMapper<Banner,String> {
    public List<Banner> queryBannersByTime();
}
