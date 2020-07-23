package com.book.web;

import com.book.pojo.Book;
import com.book.pojo.Page;
import com.book.service.BookService;
import com.book.service.impl.BookServiceImpl;
import com.book.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ClientServlet extends BaseServlet {


    private BookService bookService = new BookServiceImpl();

    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1,获取请求的参数，获取pageNo和pageSize
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.DEFAULT_PAGE_SIZE);
        //2，调用service方法获取page对象
        Page<Book> page = bookService.page(pageNo, pageSize);
        page.setUrl("client/bookServlet?callMethodName=page");
        //3，将page设置到request中
        request.setAttribute("page", page);
        //4，转发给jsp
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);
    }


    protected void pageByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1,获取请求的参数，获取pageNo和pageSize
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.DEFAULT_PAGE_SIZE);
        int min = WebUtils.parseInt(request.getParameter("min"),0);
        int max = WebUtils.parseInt(request.getParameter("max"), 999999);
        //2，调用service方法获取page对象
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize, min, max);
        page.setUrl("client/bookServlet?callMethodName=pageByPrice");
        //3，将page设置到request中
        request.setAttribute("page", page);
        //4，转发给jsp
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);
    }


}
