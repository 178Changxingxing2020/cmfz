package com.baizhi.cxx.dao;

import com.baizhi.cxx.entity.Counter;
import com.baizhi.cxx.entity.Course;
import tk.mybatis.mapper.additional.idlist.DeleteByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

public interface CourseDao extends Mapper<Course> , DeleteByIdListMapper<Course,String> {
}
