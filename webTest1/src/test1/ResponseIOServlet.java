package test1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResponseIOServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(response.getCharacterEncoding());
        //默认iso-8859-1不支持中文
        response.setCharacterEncoding("UTF-8");//只是设置服务器的字符集，浏览器不知道
        //通过响应头设置浏览器为utf-8
        response.setHeader("Content-Type","text/html; charset=UTF-8");

        //或者一行代码搞定：同时设置服务器和响应头的字符集
        response.setContentType("text/html; charset=UTF-8");


        PrintWriter pw = response.getWriter();
        pw.write("回传");
        pw.flush();
        pw.close();
    }
}
