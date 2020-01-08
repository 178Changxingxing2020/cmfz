package com.baizhi.cxx.service;

import com.baizhi.cxx.entity.Album;
import com.baizhi.cxx.Dto.AlbumDto;

import java.util.List;

public interface AlbumService {
    //public List<Banner> queryAll();
    public AlbumDto queryByPage(Integer row, Integer page);
    public Album queryOne(Album album);
    public void remove(List<String> list);
    public void modifly(Album album);
    public void insert(Album album);
}
