package com.baizhi.cxx.dao;

import com.baizhi.cxx.entity.Course;
import com.baizhi.cxx.entity.UserGuru;
import tk.mybatis.mapper.additional.idlist.DeleteByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

public interface UserGuruDao extends Mapper<UserGuru> , DeleteByIdListMapper<UserGuru,String> {
}
