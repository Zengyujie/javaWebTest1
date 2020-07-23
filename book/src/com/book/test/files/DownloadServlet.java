package com.book.test.files;

import org.apache.commons.io.IOUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import sun.nio.ch.IOUtil;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

public class DownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String downloadFileName = "temp.txt";
        ServletContext servletContext = getServletContext();

        //填写头部
        //告诉浏览器要下载的数据类新
        String mimeType = servletContext.getMimeType("/file/" + downloadFileName);
        response.setContentType(mimeType);
        //告诉浏览器接受到的数据怎么处理，默认是直接在浏览器打开查看
        //设置要下载的方式是以附件的形式，并且告诉文件名，文件名可以和源文件名不同
        //response.setHeader("Content-Disposition","attachment;filename="+downloadFileName);
        //但是以上对文件名的 操作不支持文中，因此需要对文件名重新编码
        response.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode("文档.txt","UTF-8"));
        //如果是谷歌浏览器，需要进行URL编码，将中文转化为%XX%XX的形式
        //因为谷歌，IE浏览器都是使用URL编码

        //火狐使用Base64编码
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String encodedString = base64Encoder.encode(downloadFileName.getBytes("UTF-8"));
        BASE64Decoder base64Decoder = new BASE64Decoder();
        byte[] bytes = base64Decoder.decodeBuffer(encodedString);
        downloadFileName = new String(bytes,"UTF-8");

        //对火狐浏览器，应该满足以下格式
        //response.setHeader("Content-Disposition", "attachment; filename==?UTF-8?B?" + new BASE64Encoder().encode("中国.txt".getBytes("UTF-8")) + "?=");

        //因此，可以根据header中不同的浏览器，返回不同的编码格式
        if(request.getHeader("User-Agent").contains("fire-fox")){
            //base64
        }else{
            //url
        }


        InputStream is = servletContext.getResourceAsStream("/file/" + downloadFileName);
        OutputStream outputStream = response.getOutputStream();
        IOUtils.copy(is,outputStream);//读取输入并赋值给输出流


    }
}
