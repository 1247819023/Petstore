package com.lgj.controller;

import com.lgj.dao.OrderMapper;
import com.lgj.entity.ApiResponse;
import com.lgj.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/store")
public class OrderController {

    @Autowired
    private OrderMapper orderMapper;

    @RequestMapping(method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public ApiResponse insert(Order order){
        if (orderMapper.insert(order)>0){
            return new ApiResponse(200,"successful","successful operation");
        }else {
            return new ApiResponse(400,"error","Invalid status value");
        }
    }

    @GetMapping("/inventory")
    public ApiResponse selectByStatus(String status){
        orderMapper.selectByStatus(status);
        return new ApiResponse(200,"successful","successful operation");
    }

    @RequestMapping(method = RequestMethod.GET)
    public ApiResponse selectById(@PathVariable("oid") int oid, Model model){
        if (oid == 0){
            return new ApiResponse(400,"error","Invalid status value");
        }else {
            if(orderMapper.selectByPrimaryKey(oid) == null){
                return new ApiResponse(200,"successful","successful operation");
            }else {
                return  new ApiResponse(404,"error","Order not found");
            }
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ApiResponse delect(@PathVariable("oid") int oid){
        if (oid == 0){
            return new ApiResponse(400,"error","Invalid status value");
        }else {
            if(orderMapper.selectByPrimaryKey(oid) == null){
                return new ApiResponse();
            }else {
                return  new ApiResponse(404,"error","Order not found");
            }
        }
    }
}
