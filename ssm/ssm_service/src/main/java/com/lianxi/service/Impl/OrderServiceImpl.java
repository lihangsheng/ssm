package com.lianxi.service.Impl;

import com.github.pagehelper.PageHelper;
import com.lianxi.dao.IOrdersDao;
import com.lianxi.domain.Orders;
import com.lianxi.service.IOdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class OrderServiceImpl implements IOdersService {
    @Autowired
    private IOrdersDao ordersDao;

    @Override
    public List<Orders> findAll(int page,int size) throws Exception {
        PageHelper.startPage(page,size);
        return ordersDao.findAll();
    }

    @Override
    public Orders findById(String ordersId) throws Exception {
        return ordersDao.findById(ordersId);
    }
}
