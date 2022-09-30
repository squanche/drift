package com.example.DB.controller;

import com.example.DB.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangzhch
 * @date 2022/9/30 15:06
 */
@Slf4j
@RequestMapping(path = "/test")
@RestController
public class testController {

    @Autowired
    private UserMapper userMapper;


    @RequestMapping(method = RequestMethod.GET, path = "/user")
    public String getUser() {
        return userMapper.selectList(null).toString();
    }

}
