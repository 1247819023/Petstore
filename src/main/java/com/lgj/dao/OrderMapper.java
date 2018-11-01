package com.lgj.dao;

import com.lgj.entity.Order;
import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer oid);

    int insert(Order record);

    Order selectByPrimaryKey(Integer oid);

    List<Order> selectAll();

    int updateByPrimaryKey(Order record);

    Order selectByStatus(String status);
}