package test1;

import com.sun.deploy.net.HttpResponse;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/*

tomcat目录介绍：

bin   专门存放tomcat服务器的可执行程序
conf  专门用来存放服务器的配置文件
lib   专门用来存放服务器的jar包
logs  专门用来存放服务器运行时输出的日志
temp  专门用来存放服务器运行时产生的临时数据
webapps  专门用来存放部署的工程
work  tomcat工作的临时目录，用来存放jsp翻译为servlet的源码，和session钝化(序列化)的结果

如何部署web工程：
1，将工程目录复制到webapps目录下
2，在conf/Catalina/localhost目录下写xml文件
<?xml version="1.0" encoding="UTF-8"?>
<Context path="/web01" docBase="D:\java\idea_workspace\javaWebStaticPage\bookstore"></Context>
<!--注意，文件名才是要访问的path，无比和path同名 -->

手托页面到浏览器和浏览器url访问的区别：一个是file协议，一个是http协议

在浏览器访问时，如果没有工程名，默认访问root工程，如果没有资源名，默认访问index.html页面

java web工程目录：
1，web：存放资源文件
2，web-inf：由服务器保护的特俗目录，浏览器无法直接访问
3，web.xml:整个web工程的配置部署描述文件，可以配置很多组件如servlet，filiter, listener,seesion等
4，lib目录存放第三方jar包，idea还需要自己配置jar包


什么是servlet
1，servlet时javaee规范之一，规范就是接口
2，servlet时Javaweb三大组件之一，三大组件分别时servlet程序，filiter过滤器，listener监听器
3，servlet是运行在服务器上的java小程序，可以接受客户端发送的请求，并响应数据

手动实现servlet程序
1，手动编写类实现servlet接口
2，实现service方法，处理请求并响应数据
3，在web.xml中配置servlet的响应地址



servlet声明周期：

1，执行servlet构造器方法
2，执行init初始化方法
3，执行service方法
4，执行destroy销毁方法

1，2步是在servlet创建时调用
3部每次访问都会调用
4步在web工程停止时调用


一般在实际开发中都使用实现继承HttpServlet类的方法去实现Servlet程序
重写doGet或者doPost方法

doGet在get请求使用
doPost在post请求使用

servlet继承关系：

Servlet 接口
    |
GenericServlet抽象类，实现接口(做了一些空的实现，持有一个ServletConfig引用，并使用它做了一些方法)
    |
HttpServlet继承，实现了service抽象方法，并实现了请求的分发(调用getMethod方法获取方法字符串)
    |           其中的doget等方法就是负责抛异常，所以需要重写方法
    |
自定义Servlet类继承


ServletConfig类：是Servlet程序的配置信息类
三大作用：
    1，获取Servlet程序的别名，即<servlet-name>的值
    2，初始化参数init-param
    3，获取ServletContext对象
说明：
    1，servlet程序和servletconfig对象是由tomcat负责创建，我们负责使用，
    2，servlet默认是第一次访问时创建，servletconfig是每个servlet创建时，就创建了一个对应的对象
    3，每个servlet中的servletconfig只能得到自己servlet的信息，不能获取其他的

ServletContext类
1，是一个接口，表示Servlet上下文对象
2，一个web工程，只有一个ServletContext对象
3，ServletContext对象是一个域对象
（域对象：可以像Map一样存取数据的对象）
            添加          获取          删除
Map          put         get            remove
域对象    setAttribute  getAttribute   removeAttribute
4，servletcontext对象在web工程启动时创建，关闭时销毁
5，servletcontext的作用
    1，获取web.xml中配置的上下文参数context-param
    2，获取当前工程的路径，格式：/工程路径
    3，获取工程部署后在服务器硬盘上的绝对路径
    4，像map一样存取数据


*/

public class HelloServlet implements Servlet {

    public HelloServlet() {
        System.out.println("构造器方法 ");
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("初始化方法");
        //1,获取别名
        System.out.println("helloservlet的别名是：" + servletConfig.getServletName());
        //2，获取参数
        System.out.println("初始化参数的值是" + servletConfig.getInitParameter("username"));
        System.out.println(servletConfig.getClass().getName());
        //3，获取servletContext对象
        System.out.println(servletConfig.getServletContext());
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        //专门迎来处理请求和响应的
        System.out.println("servlet start");
        HttpServletRequest htr = (HttpServletRequest)servletRequest;
        String method = htr.getMethod();
        if("GET".equals(method)){
            System.out.println("get");
        }else{
            System.out.println("post");
        }
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("销毁方法 ");
    }
}
