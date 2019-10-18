package com.lianxi.dao;

import com.lianxi.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;


import java.util.List;

public interface IProductDao {
    /**
     * 查询所有产品信息
     * @return
     * @throws Exception
     */

    @Select("select * from product")
    List<Product> findAll() throws Exception;

    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);

    //根据id查询产品
    @Select("select * from product where id=#{id}")
    public Product findById(String id) throws Exception;
}
