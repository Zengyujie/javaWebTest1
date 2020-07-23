package test1;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HellloServlet3 extends HttpServlet {



/*
GET请求
    1，请求行(第一行)
        1，请求方式：GET
        2，请求的资源路径(/工程名/资源)，可以附带?+参数
        3，请求协议的版本号  HTTP/1.1
    2，请求头(后面的行)
        key：value组成，不同的键值对表示不同的含义
        常见请求头：
            1，Accept:XXX
            告诉服务器，客户端可以接收的数据类型
            2，Accept-Language
            告诉服务器，客户端可以接受的语言类型zh_CN，en_US等
            3，User-Agent：浏览器的信息
            4，Accept-Encoding：客户端可以接受的数据编码格式
            5，Host：请求的服务器ip和端口号
            6，Connection：keep-alive
            告诉服务器当前链接怎么处理，keep-alive：回传完数据保持一小段时长的连接，不要马上关闭
                                        closed：传完马上关闭

POST请求
    1，请求行(第一行)
        1，请求方式：POST
        2，请求的资源路径(/工程名/资源)，可以附带?+参数
        3，请求协议的版本号  HTTP/1.1
    2，请求头(后面的行)
        key：value组成，不同的键值对表示不同的含义
        常见请求头：
            1，Accept:XXX
            告诉服务器，客户端可以接收的数据类型
            2，Accept-Language
            告诉服务器，客户端可以接受的语言类型zh_CN，en_US等
            3，User-Agent：浏览器的信息
            4，Accept-Encoding：客户端可以接受的数据编码格式
            5，Host：请求的服务器ip和端口号
            6，Connection：keep-alive
            告诉服务器当前链接怎么处理，keep-alive：回传完数据保持一小段时长的连接，不要马上关闭
                                        closed：传完马上关闭
            7，Referer:表示请求发起时，浏览器地址栏中的地址(从那里来)
            8，Content-Type：表示发送的数据类型：
                application/x-www-form-urlencoded
                    表示提交的数据格式是：name=value&name=value，对其进行url编码(把非英文内容转为%XX%XX)
                multipart/form-data
                    表示提交的数据格式是：以多段的形式提交(用于上传)
                其他。。。
            9，Content-Length:发送数据的长度
            10，Cache-Control：表示如何控制缓存，no-cache不缓存
    3，空行
    4，请求体：发送给服务器的数据(表单中的等等)

GET请求的来源有那些：
    1，form提交，设置method=get
    2，<a href="xxx">link</a>
    3，<link rel="stylesheet" type="text/css" href="1.css"/>
    4，<stript type="text/javascript" src="xxx"></script>
    5，<img src="xxx"></img>
    6，<iframe></iframe>
    7，浏览器输入地址后回车
POST请求的来源有那些：
    1，form提交，设置method=post


响应的HTTP协议格式：
    1，响应行(第一行)
        1，响应的协议和版本号   HTTP/1.1
        2，响应状态码
        3，响应状态描述符
            常见响应码：
                200  请求成功
                302  请求重定向
                404  服务器收到了，但是数据不存在
                500  服务器收到了但是服务器内部错误
    2，响应头(后续行)
        key：value
        常见响应头：
            Server：表示服务器的信息
            Content-Type：表示响应体的数据类型
            Content-Length：响应体长度
            Date：请求响应的事件(格林威治时间)
    3，空行
    4，响应体：回传给客户端的数据，一般是页面


MIME类型说明
    MIME是HTTP协议中数据类型(Multipurpose Internet Mail Extensions多功能Internet邮件扩充服务，
    格式是：大类型/小类型

    常见的有：
        .html  text/html
        .txt   text/plain
        .rtf   application/rtf
        .gif   image/gif
        jpg    image/jpeg
        .au    audio/basic
        .mid   audio/x-mid
        .mpg   video/mpeg
        .gz    application/x-gzip


HttpServletRequest类

只要有进入tomcat的请求，就会把http解析好封装到request中，然后传递到service方法中，
可以通过HttpServletRequest对象获取所有请求信息
常用方法：
getRequestURI()  获取请求的资源路径
getRequestURL()  获取请求的统一资源定位符(绝对路径)
getRemoteHost()  获取客户端的 ip
getHeader()      获取请求头
getParameter()   获取请求参数
getParameterValues()  获取请求参数(多个值使用)
getMethod()      获取请求方式
setAttribute(key, value)  设置域数据
getAttribute(key)         获取域数据
getRequestDispatcher()    获取请求转发对象


请求转发：
服务器从一个请求资源跳转到另一个请求资源
请求转发的特点：
    1，浏览器地址栏没有变化
    2，他们是一次请求
    3，他们共享Request域中的数据
    4，可以转发到web-inf目录下的资源

所有相对路径的工作跳转都会参照浏览器当前的地址类跳转
当使用请求转发来进行跳转时，浏览器中的地址记录仍然是当前servlet的地址，
即对于ip/project/resource1，resource1表示一个servlet，当resource1跳转时，
跳转结果的地址记录仍然时原来的地址，所以此时再次跳转可能出问题

解决办法：servlet-base标签
base标签可以设置一个当前所有页面工作路径作为参照
<base href="ip:port/protj/resource">,声明在<head></head>中
有base值之后，就会忽略掉当前浏览器的地址，以base为准


在web中 / 是一种绝对路径
被浏览器解析式http://ip:port/
被服务器解析是http://ip:port/工程路径/
特殊情况：response.sendRedirect("/")表示把“/”发送给浏览器解析，属于浏览器解析结果


HttpServletResponse类

每次请求，tomcat都会创建一个该对象传递给servlet使用，表示响应的所有信息

响应的两个流：
    字节流：getOutputStream(),常用于下载
    字符流：getWriter()，常用于回传字符串
    两个流只能使用一个，否则就会报错

如何往客户端回传数据：
获得流，写入内容即可

如何解决浏览器中文乱码问题：
    首先在服务器端通过response对象设置字符集为utf-8
    然后response设置head中的浏览器接受的字符集也为utf-8即可

    或者一行代码搞定：同时设置服务器和响应头的字符集
     response.setContentType("text/html; charset=UTF-8");

    设置完毕之后再获取流即可解决


请求重定向：客户端访问时，浏览器给客户端新的地址，让用户访问，因为之前的地址
可能已经被废弃，例如写了新的servlet等

方式一，原response中欧给设置响应码为302，然后告知客户端新地址（通过response设置头内容
客户端获取后再通过头不的Location值访问新地址

请求重定向的特点：
    1，浏览器地址栏会发生变化
    2，两次请求
    3，不共享Request域中的数据
    4，不能访问Web-inf目录
    5，可以访问当前工程以外的工程（本质上是浏览器再次发送请求）

方式二：推荐使用
response.sendRedirect("http://localhost:8080/webTest1_war_exploded/ResponseIO");





*/
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();
        context.setAttribute("key1", "value1");
        System.out.println(getServletConfig().getServletName());
        System.out.println(context.getAttribute("key1"));

        ///////////////////////




    }
}
