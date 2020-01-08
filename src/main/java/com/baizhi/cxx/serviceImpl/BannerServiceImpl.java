package com.baizhi.cxx.serviceImpl;

import com.baizhi.cxx.dao.BannerDao;
import com.baizhi.cxx.entity.Banner;
import com.baizhi.cxx.Dto.BannerDto;
import com.baizhi.cxx.service.BannerService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("bannerService")
@Transactional
public class BannerServiceImpl implements BannerService {

    @Autowired
    BannerDao bannerDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Banner> queryAll() {
        List<Banner> list = bannerDao.selectAll();

        return list;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Banner queryOne(Banner banner) {
        Banner banner1 = bannerDao.selectOne(banner);
        return banner1;
    }



    @Override
    public void remove(List<String> list) {
        bannerDao.deleteByIdList(list);
    }

    @Override
    public void modifly(Banner banner) {
        bannerDao.updateByPrimaryKeySelective(banner);
    }

    @Override
    public void insert(Banner banner) {
        bannerDao.insert(banner);
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public BannerDto queryPersonByPage(Integer row, Integer page){

        BannerDto dto = new BannerDto();
        dto.setPage(page);
        dto.setRecords(bannerDao.selectCount(null));
        dto.setTotal(dto.getRecords() % row == 0 ? dto.getRecords() / row : dto.getRecords() / row + 1);
        int beginResult = (page - 1) * row;
        int endResult = page * row;
        int num=beginResult-endResult;
        //dto.setRows(bannerDao.selectPersonByPage(beginResult, endResult));
        dto.setRows(bannerDao.selectByRowBounds(null,new RowBounds(beginResult,row)));
        return dto;
    }

}
