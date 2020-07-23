package com.book.service;

import com.book.pojo.User;

public interface UserService {

    public void registUser(User user);

    public User login(User user);


    /**
     *
     * @param username
     * @return true 用户存在，false，用户名可用(不在数据库里)
     */
    public boolean existsUsername(String username);


}
