package com.lianxi.service.Impl;

import com.lianxi.dao.ISysLogDao;
import com.lianxi.domain.SysLog;
import com.lianxi.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysLogServiceImpl implements ISysLogService {
    @Autowired
    private ISysLogDao sysLogDao;

    @Override
    public void save(SysLog sysLog) throws Exception {
           sysLogDao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll() throws Exception {
       return sysLogDao.findAll();

    }
}
