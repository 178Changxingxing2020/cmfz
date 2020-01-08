package com.baizhi.cxx.dao;

import com.baizhi.cxx.entity.Album;
import com.baizhi.cxx.entity.Article;
import tk.mybatis.mapper.additional.idlist.DeleteByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

public interface ArticleDao extends Mapper<Article> , DeleteByIdListMapper<Article,String> {
}
