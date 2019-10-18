package com.lianxi.dao;

import com.lianxi.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ITravellerDaao {
    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId=#{ordersId})")
    public List<Traveller>findByOrdersId(String ordersId) throws Exception;
}
