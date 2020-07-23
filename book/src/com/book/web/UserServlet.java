package com.book.web;

import com.book.pojo.User;
import com.book.service.UserService;
import com.book.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    /*
    使用一个servlet实现用户相关的模块，合并登陆和注册


     */

    private UserService userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userType = request.getParameter("userType");
        if(userType.equals("login")){
            login(request,response);
        }else if(userType.equals("regist")){
            regist(request,response);
        }
        //如此就等效于转发了
    }

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.login(new User(null, username, password, null));
        if(user == null){
            request.setAttribute("errormsg","用户名或密码错误!");
            request.setAttribute("username", username);
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
        }else{
            request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request,response);
        }
    }

    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String code = request.getParameter("code");
        if("abcde".equalsIgnoreCase("abcde")){
            if(userService.existsUsername(username)){
                System.out.println("用户名已存在");
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
            }else{
                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request,response);
            }
        }else{
            System.out.println("验证码错误");
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
