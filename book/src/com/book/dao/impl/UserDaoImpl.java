package com.book.dao.impl;

import com.book.dao.UserDao;
import com.book.pojo.User;

public class UserDaoImpl extends BaseDAO implements UserDao {

    @Override
    public User queryUserByUserName(String username) {
        String sql = "select `id`,`username`,`password`,`email` from t_user where username=?";
        return this.queryForOne(sql, User.class, username);
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user(`username`,`password`,`email`) values(?,?,?)";
        return this.update(sql, user.getUsername(), user.getPassword(), user.getEmail());
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql = "select `id`,`username`,`password`,`email` from t_user where username=? and password=?";
        return this.queryForOne(sql, User.class, username, password);
    }
}
