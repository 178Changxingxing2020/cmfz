package com.baizhi.cxx.service;

import com.baizhi.cxx.entity.Chapter;
import com.baizhi.cxx.Dto.ChapterDto;

import java.util.List;

public interface ChapterService {
    //public List<Banner> queryAll();
    public ChapterDto queryByPage(Integer row, Integer page,String albumId);
    public Chapter queryOne(Chapter chapter);
    public void remove(List<String> list);
    public void modifly(Chapter chapter);
    public void insert(Chapter chapter);
}
