package com.book.test;

import com.book.dao.UserDao;
import com.book.dao.impl.UserDaoImpl;
import com.book.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {

    UserDao userDao = new UserDaoImpl();

    @Test
    public void queryUserByUserName() {

        User usr = userDao.queryUserByUserName("pony");
        if(usr == null){
            System.out.println("用户不存在");
        }else{
            System.out.println(usr);
        }
    }

    @Test
    public void saveUser() {
        User user = new User(null, "张三","123456","zhangsan@qq.com");
        int res = userDao.saveUser(user);
        if(res == -1){
            System.out.println("加入失败");
        }else{
            System.out.println(res);
        }
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        User usr = userDao.queryUserByUsernameAndPassword("admin","admin");
        if(usr == null){
            System.out.println("登陆失败");
        }else{
            System.out.println(usr);
        }
    }
}