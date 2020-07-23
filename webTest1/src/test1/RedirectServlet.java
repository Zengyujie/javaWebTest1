package test1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RedirectServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置状态码
        response.setStatus(302);
        //设置响应头
        response.setHeader("Location", "http://localhost:8080/webTest1_war_exploded/ResponseIO");

        //方式二：推荐使用
        //response.sendRedirect("http://localhost:8080/webTest1_war_exploded/ResponseIO");
    }
}
