package com.baizhi.cxx.serviceImpl;

import com.baizhi.cxx.dao.ChapterDao;
import com.baizhi.cxx.entity.Chapter;
import com.baizhi.cxx.Dto.ChapterDto;
import com.baizhi.cxx.service.ChapterService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("chapterService")
@Transactional
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    ChapterDao chapterDao;

//    @Override
//    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
//    public List<Banner> queryAll() {
//        List<Banner> list = bannerDao.selectAll();
//
//        return list;
//    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Chapter queryOne(Chapter chapter) {
        Chapter chapter1 = chapterDao.selectOne(chapter);
        return chapter1;
    }



    @Override
    public void remove(List<String> list) {
        chapterDao.deleteByIdList(list);
    }

    @Override
    public void modifly(Chapter chapter) {
        chapterDao.updateByPrimaryKeySelective(chapter);
    }

    @Override
    public void insert(Chapter chapter) {
        chapterDao.insert(chapter);
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public ChapterDto queryByPage(Integer row, Integer page,String albumId){
        Chapter chapter = new Chapter();
        chapter.setAlbumId(albumId);

        ChapterDto dto = new ChapterDto();
        dto.setPage(page);
        dto.setRecords(chapterDao.selectCount(chapter));
        dto.setTotal(dto.getRecords() % row == 0 ? dto.getRecords() / row : dto.getRecords() / row + 1);
        int beginResult = (page - 1) * row;
        int endResult = page * row;
        int num=beginResult-endResult;
        //dto.setRows(bannerDao.selectPersonByPage(beginResult, endResult));
        dto.setRows(chapterDao.selectByRowBounds(null,new RowBounds(beginResult,row)));
//       ChapterDto dto2 = new ChapterDto();
//        for (Chapter dtoRow : dto.getRows()) {
//
//        }
        return dto;
    }

}
