package com.book.dao;

import com.book.pojo.User;

public interface UserDao {

    /**
     * 根据姓名返回用户信息
     *
     * @param username
     * @return 返回null说明没有用户
     */
    public User queryUserByUserName(String username);

    public int saveUser(User user);

    public User queryUserByUsernameAndPassword(String username, String password);



}
