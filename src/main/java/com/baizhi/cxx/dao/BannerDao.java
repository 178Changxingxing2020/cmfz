package com.baizhi.cxx.dao;

import com.baizhi.cxx.cache.MyBatisCache;
import com.baizhi.cxx.entity.Banner;
import jdk.internal.dynalink.linker.LinkerServices;
import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.cache.annotation.Cacheable;
import tk.mybatis.mapper.additional.idlist.DeleteByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
//@CacheNamespace(implementation= MyBatisCache.class)
public interface BannerDao extends Mapper<Banner> , DeleteByIdListMapper<Banner,String> {
    public List<Banner> queryBannersByTime();
}
