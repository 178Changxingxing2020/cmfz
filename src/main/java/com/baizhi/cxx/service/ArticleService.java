package com.baizhi.cxx.service;

import com.baizhi.cxx.Dto.ArticleDto;
import com.baizhi.cxx.entity.Article;

import java.util.List;

public interface ArticleService {
    //public List<Banner> queryAll();
    public ArticleDto queryByPage(Integer row, Integer page);
    public Article queryOne(Article Article);
    public void remove(List<String> list);
    public void modifly(Article Article);
    public void insert(Article Article);
}
