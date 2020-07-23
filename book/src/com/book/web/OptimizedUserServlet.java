package com.book.web;

import com.book.pojo.User;
import com.book.service.UserService;
import com.book.service.impl.UserServiceImpl;
import com.book.utils.WebUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class OptimizedUserServlet extends BaseServlet {

    /*
    使用第三方包 BeanUtils注入javaBean
    注入bean的底层原理：
    遍历request的参数map，对每一个key查找类中是否有setXX方法，如果有就赋值
    因此，如果setXX方法没有和属性对应，就无法赋值
     */
    protected UserService userService = new UserServiceImpl();

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

        //为什么要传入map而不是request：
        //因为使用性更广，而且使用request会造成DAO层与web层耦合度高
        User user = WebUtils.copyParamToBean(request.getParameterMap(), User.class);

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

}
