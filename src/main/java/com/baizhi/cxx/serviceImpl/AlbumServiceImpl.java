package com.baizhi.cxx.serviceImpl;

import com.baizhi.cxx.dao.AlbumDao;
import com.baizhi.cxx.entity.Album;
import com.baizhi.cxx.Dto.AlbumDto;
import com.baizhi.cxx.service.AlbumService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("albumService")
@Transactional
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    AlbumDao albumDao;

//    @Override
//    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
//    public List<Banner> queryAll() {
//        List<Banner> list = bannerDao.selectAll();
//
//        return list;
//    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Album queryOne(Album album) {
        Album album1 = albumDao.selectOne(album);
        return album1;
    }



    @Override
    public void remove(List<String> list) {
        albumDao.deleteByIdList(list);
    }

    @Override
    public void modifly(Album album) {
        albumDao.updateByPrimaryKeySelective(album);
    }

    @Override
    public void insert(Album album) {
        albumDao.insert(album);
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public AlbumDto queryByPage(Integer row, Integer page){

        AlbumDto dto = new AlbumDto();
        dto.setPage(page);
        dto.setRecords(albumDao.selectCount(null));
        dto.setTotal(dto.getRecords() % row == 0 ? dto.getRecords() / row : dto.getRecords() / row + 1);
        int beginResult = (page - 1) * row;
        int endResult = page * row;
        int num=beginResult-endResult;
        //dto.setRows(bannerDao.selectPersonByPage(beginResult, endResult));
        dto.setRows(albumDao.selectByRowBounds(null,new RowBounds(beginResult,row)));
        return dto;
    }

}
