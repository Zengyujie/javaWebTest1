package com.book.test.files;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class UploadServlet extends HttpServlet {


/*

文件的上传下载：
1，要有一个form标签，method为post
2，form标签的encType属性必须为multipart/form-data，否则会出错
    该形式提交数据，数据以多段的形式拼接，然后以二进制流的形式发送给服务器
3，form标签中使用input type=file添加上传文件
4，编写服务器代码接收，处理上传数据

5，文件上传的第三方包的类：commons-fileupload.jar
    ServletFileUpload.isMultiportContent()：判断是否为多段格式
    parseRequest()解析上传数据得到多个集合
    FileItem.isFormField（）判断 当前表单项是普通的还是文件
    FileItem.getFieldName（）获取表单项的name属性
    FileItem.getString()获取当前表单项的值
    FileItem.getName()获取文件名
    FileItem.write(file)将文件保存到固定路径

文件下载过程：
    1，浏览器发送请求，告诉服务器要下载的文件名
    2，浏览器读取要下载文件的内容
    3，响应头填写回传的数据类型
    4，把下载文件回传给客户端



*/

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("文件上传");
        if(ServletFileUpload.isMultipartContent(request)){
            try{
                FileItemFactory fileItemFactory = new DiskFileItemFactory();
                ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
                List<FileItem> list = servletFileUpload.parseRequest(request);
                for(FileItem fileItem : list){
                    if(fileItem.isFormField()){
                        //如果是普通表单
                        System.out.println(fileItem.getFieldName());
                    }else{
                        //如果是内容表单
                        fileItem.write(new File("d:\\" + fileItem.getName()));
                    }
                }

            } catch(Exception e){
                e.printStackTrace();
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
