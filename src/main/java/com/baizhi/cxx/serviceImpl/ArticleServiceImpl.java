package com.baizhi.cxx.serviceImpl;

import com.baizhi.cxx.Dto.AlbumDto;
import com.baizhi.cxx.Dto.ArticleDto;
import com.baizhi.cxx.dao.AlbumDao;
import com.baizhi.cxx.dao.ArticleDao;
import com.baizhi.cxx.entity.Album;
import com.baizhi.cxx.entity.Article;
import com.baizhi.cxx.service.AlbumService;
import com.baizhi.cxx.service.ArticleService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("articleService")
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleDao articleDao;

//    @Override
//    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
//    public List<Banner> queryAll() {
//        List<Banner> list = bannerDao.selectAll();
//
//        return list;
//    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Article queryOne(Article article) {
        Article article1 = articleDao.selectOne(article);
        return article1;
    }



    @Override
    public void remove(List<String> list) {
        articleDao.deleteByIdList(list);
    }

    @Override
    public void modifly(Article article) {
        articleDao.updateByPrimaryKeySelective(article);
    }

    @Override
    public void insert(Article article) {
        articleDao.insert(article);
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public ArticleDto queryByPage(Integer row, Integer page){

        //AlbumDto dto = new AlbumDto();
        ArticleDto dto = new ArticleDto();
        dto.setPage(page);
        dto.setRecords(articleDao.selectCount(null));
        dto.setTotal(dto.getRecords() % row == 0 ? dto.getRecords() / row : dto.getRecords() / row + 1);
        int beginResult = (page - 1) * row;
        int endResult = page * row;
        int num=beginResult-endResult;
        //dto.setRows(bannerDao.selectPersonByPage(beginResult, endResult));
        dto.setRows(articleDao.selectByRowBounds(null,new RowBounds(beginResult,row)));
        return dto;
    }

}
