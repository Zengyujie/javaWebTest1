package test1;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet2 extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);//重写init方法是必须调用父类的方法 传入config
        //不然无法在后续调用getServletConfig方法获取配置信息
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletConfig conf = getServletConfig();
        System.out.println(conf.getInitParameter("url"));
        System.out.println("doget");

        ServletContext con = getServletConfig().getServletContext();
        String s1 = con.getInitParameter("con1");
        System.out.println(s1);
        System.out.println(con.getContextPath());
        //斜杠被服务器解析为 http:ip:port/工程名/
        System.out.println(con.getRealPath("/"));
        //表示获取当前工程名的真是磁盘地址
        System.out.println(con.getRealPath("/a.html"));

        System.out.println("-------------");

        System.out.println(conf.getServletName());
        System.out.println(con.getAttribute("key1"));

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("dopost");
    }
}
