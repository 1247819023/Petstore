package com.lgj.controller;

import com.lgj.dao.UserMapper;
import com.lgj.entity.ApiResponse;
import com.lgj.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/create")
    public ApiResponse create(User user) {
        userMapper.insert(user);

        return new ApiResponse("default", "successful operation");
    }

    @RequestMapping(path = "", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ApiResponse createWithArray(@RequestBody ArrayList<User> arrayList) {
        boolean flag = false;
        for (User user : arrayList) {
            if (userMapper.insert(user) > 0) {
                flag = true;
            }
        }
        return new ApiResponse("default", "successful operation");
    }

    @RequestMapping(path = "", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ApiResponse createWithList(@RequestBody List<User> list) {
        boolean flag = false;
        for (User user : list) {
            if (userMapper.insert(user) > 0) {
                flag = true;
            }
        }
        return new ApiResponse("default", "successful operation");
    }

    @RequestMapping(method = RequestMethod.GET)
    public ApiResponse login(User user) {
        if (userMapper.login(user) != 0) {
            userMapper.updateByPrimaryKey(user);
            return new ApiResponse("default", "successful operation");
        } else {
            return new ApiResponse(400, "error", "Invalid username/password supplied");
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse loginout(@SessionAttribute User user) {
        userMapper.updateByPrimaryKey(user);
        return new ApiResponse("default", "successful operation");
    }

    @RequestMapping(method = RequestMethod.GET)
    public ApiResponse findByName(@PathVariable("name") String name, Model model) {
        if (name == null){
            return new ApiResponse(400, "error", "Invalid username supplied");
        }else {
            if (userMapper.selectByName(name) != null){
                return new ApiResponse(200,"default", "successful operation");
            }else {
                return new ApiResponse(404, "error", "User not found");
            }
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ApiResponse update(User user) {
        if(user.getUid() == null){
            return new ApiResponse(400, "error", "Invalid username supplied");
        }else {
            if (userMapper.updateByPrimaryKey(user) != 0){
                return new  ApiResponse();
            }else {
                return new ApiResponse(404, "error", "User not found");
            }
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ApiResponse delece(@PathVariable("uid") int uid) {
        if(uid == 0){
            return new ApiResponse(400, "error", "Invalid username supplied");
        }else {
            if (userMapper.deleteByPrimaryKey(uid) != 0){
                return new  ApiResponse();
            }else {
                return new ApiResponse(404, "error", "User not found");
            }
        }
    }
}
