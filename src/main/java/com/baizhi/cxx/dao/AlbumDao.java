package com.baizhi.cxx.dao;

import com.baizhi.cxx.entity.Album;
import com.baizhi.cxx.entity.Banner;
import com.baizhi.cxx.entity.Chapter;
import tk.mybatis.mapper.additional.idlist.DeleteByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

public interface AlbumDao extends Mapper<Album> , DeleteByIdListMapper<Album,String> {
}
