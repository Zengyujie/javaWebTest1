package test1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class RequestAPITestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");//在获取请求参数之前调用才有效
        System.out.println("uri" + request.getRequestURI());
        System.out.println("url" + request.getRequestURL());//ip+port+uri
        System.out.println("ip" + request.getRemoteHost());
        System.out.println("key,value of header" + request.getHeader("User-Agent"));
        System.out.println("---------------param--------------");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String[] hobby = request.getParameterValues("hobby");
        System.out.println(username);
        System.out.println(password);
        System.out.println(Arrays.toString(hobby));

        request.setAttribute("key2","柜台1");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Reforward");//斜线仍然表示工程目录
        dispatcher.forward(request, response);
    }
}
