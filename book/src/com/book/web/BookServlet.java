package com.book.web;

import com.book.pojo.Book;
import com.book.pojo.Page;
import com.book.service.BookService;
import com.book.service.impl.BookServiceImpl;
import com.book.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet {

    /*
    由于jsp不应该直接访问数据库，因此需要首先跳转到servlet做数据库处理，
    然后再将查询结果传递给结果jsp显示，servlet是请求之后才会执行，因此需要
    先创造请求（通过jsp的超链接）



     */

    private BookService bookService = new BookServiceImpl();

    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Book book = WebUtils.copyParamToBean(request.getParameterMap(), Book.class);
        bookService.addBook(book);

        //注意：当用户按下F5功能键或刷新时，最近一次表单会再次提交
        //可能造成表单重复提交问题，导致数据库的再次写入
        //request.getRequestDispatcher("manager/bookServlet?callMethodName=list");
        //因此，以上操作有风险，不应该使用请求转发，而应该使用重定向（用户新建一次请求）
        response.sendRedirect(request.getContextPath() + "/manager/bookServlet?callMethodName=page&pageNo="+request.getParameter("pageNo"));
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        int i = WebUtils.parseInt(id, -1);
        bookService.deleteBookById(i);
        response.sendRedirect(request.getContextPath() + "/manager/bookServlet?callMethodName=page");
    }

    protected void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        int i = WebUtils.parseInt(id, -1);
        Book book = bookService.queryBookById(i);
        request.setAttribute("book",book);
        request.getRequestDispatcher("/pages/manager/book_edit.jsp?pageNo="+request.getParameter("pageNo")).forward(request,response);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //由于页面和add共用所以需要做判断
        //方案一：在添加和修改按钮的跳转超链接加入字段
        //方案二：在页面判断是否有id参数，然后修改表单的调用方法值
        //方案三：判断requestScope中是否包含book对象
        Book book = WebUtils.copyParamToBean(request.getParameterMap(), Book.class);
        bookService.updateBook(book);
        response.sendRedirect(request.getContextPath() + "/manager/bookServlet?callMethodName=page&pageNo="+request.getParameter("pageNo"));
    }

    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询全部图书
        List<Book> list = bookService.queryBooks();
        //把list存到request中
        request.setAttribute("books",list);
        //请求转发
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }


    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1,获取请求的参数，获取pageNo和pageSize
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.DEFAULT_PAGE_SIZE);
        //2，调用service方法获取page对象
        Page<Book> page = bookService.page(pageNo, pageSize);
        page.setUrl("manager/bookServlet?callMethodName=page");
        //3，将page设置到request中
        request.setAttribute("page", page);
        //4，转发给jsp
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }
}
