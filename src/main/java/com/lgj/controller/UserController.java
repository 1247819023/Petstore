package com.lgj.controller;

import com.google.gson.Gson;
import com.lgj.dao.UserMapper;
import com.lgj.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(method = RequestMethod.GET)
    public String home(){
        return "login";
    }

    @RequestMapping(value = "/success")
    public String index(HttpServletRequest request){
        String user = (String) request.getSession().getAttribute("user");
        if(!user.isEmpty())
            return "user";
        return "login";
    }

    @GetMapping("/insertForm")
    public String insertForm(){
        return "insertForm";
    }

    @GetMapping(value = "/login", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String login(String user_name, String password, HttpServletRequest request){
        int count = userMapper.login(user_name, password);
        if(count > 0){
            request.getSession().setAttribute("user",user_name);
            return "{\"msg\":\"success\"}";
        }
        return "{\"msg\":\"fail\"}";
    }

    @GetMapping(value = "/logout", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String logout(HttpServletRequest request, HttpServletResponse response){
        if(request.getSession().getAttribute("user") != null){
            request.getSession().removeAttribute("user");
            try {
                response.sendRedirect("/user");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "{\"msg\":\"注销成功\"}";
    }

    @GetMapping(value = "/findAllUser", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String findAllUser(){
        List<User> userList = userMapper.selectAll();
        return new Gson().toJson(userList);
    }

    @GetMapping(value = "/finByIdUser", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String findByIdUser(int userId){
        User user = userMapper.selectByPrimaryKey(userId);
        return new Gson().toJson(user);
    }

    @GetMapping(value = "/findByUserName", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String findByUserName(String user_name){
        User user = userMapper.selectByName(user_name);
        return new Gson().toJson(user);
    }

    @PostMapping(value = "/addUser", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String createUser(User user){
        userMapper.insert(user);
        return "{\"msg\":\"注册成功\"}";
    }

    @GetMapping("/updateForm")
    public String updateForm(Model model, int userId){
        model.addAttribute("userId", userId);
        return "updateForm";
    }

    @PostMapping(value = "/updateUser", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String updateUser(User user){
        userMapper.updateByPrimaryKey(user);
        return "{\"msg\":\"修改成功\"}";
    }

    @GetMapping(value = "/deleteUser", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String deleteUser(int userId){
        userMapper.deleteByPrimaryKey(userId);
        return "{\"msg\":\"删除成功\"}";
    }
}
