package com.baizhi.cxx.dao;

import com.baizhi.cxx.entity.Album;
import com.baizhi.cxx.entity.Counter;
import tk.mybatis.mapper.additional.idlist.DeleteByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

public interface CounterDao extends Mapper<Counter> , DeleteByIdListMapper<Counter,String> {
}
