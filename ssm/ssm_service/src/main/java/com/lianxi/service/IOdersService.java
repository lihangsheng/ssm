package com.lianxi.service;

import com.lianxi.domain.Orders;

import java.util.List;

public interface IOdersService {
    List<Orders> findAll(int page,int size) throws Exception;

    Orders findById(String ordersId) throws Exception;
}
