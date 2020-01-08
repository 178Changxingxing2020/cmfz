package com.baizhi.cxx.dao;

import com.baizhi.cxx.entity.Banner;
import com.baizhi.cxx.entity.Chapter;
import tk.mybatis.mapper.additional.idlist.DeleteByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

public interface ChapterDao extends Mapper<Chapter> , DeleteByIdListMapper<Chapter,String> {
}
