package com.baizhi.cxx.serviceImpl;

import com.baizhi.cxx.dao.AdminDao;
import com.baizhi.cxx.entity.Admin;
import com.baizhi.cxx.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("adminService")
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminDao adminDao;

    @Override
    public Admin queryOne(Admin admin) {
        Admin admin1 = adminDao.selectOne(admin);
        return admin1;
    }
}
