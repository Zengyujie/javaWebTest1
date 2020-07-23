package com.book.test;

import com.book.pojo.User;
import com.book.service.UserService;
import com.book.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {

    UserService userService = new UserServiceImpl();

    @Test
    public void registUser() {
        userService.registUser(new User(null, "pony","123456","pony@qq.com"));
        userService.registUser(new User(null, "tony","123456","tony@qq.com"));

    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null, "tttt","123dd456","pony@qq.com")));
    }

    @Test
    public void existsUsername() {
        System.out.println(userService.existsUsername("pony"));
    }
}