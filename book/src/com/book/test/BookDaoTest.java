package com.book.test;

import com.book.dao.BookDao;
import com.book.dao.impl.BookDaoImpl;
import com.book.pojo.Book;
import com.book.pojo.Page;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BookDaoTest {

    BookDao bookDao = new BookDaoImpl();

    @Test
    public void addBook() {
        Book book = new Book();
        book.setName("埃罗芒阿老师");
        book.setAuthor("和泉纱雾");
        book.setPrice(new BigDecimal(11.99));
        book.setSales(100);
        book.setStock(100);
        book.setImgPath(null);
        bookDao.addBook(book);
    }

    @Test
    public void deleteBookById() {
    }

    @Test
    public void updateBook() {
        Book book = new Book();
        book.setName("埃罗芒阿老师");
        book.setAuthor("和泉纱雾");
        book.setPrice(new BigDecimal(12.99));
        book.setSales(100);
        book.setStock(100);
        book.setImgPath(null);
        book.setId(21);
        bookDao.updateBook(book);
    }

    @Test
    public void queryBookById() {
        Book book = bookDao.queryBookById(21);
        System.out.println(book);
    }

    @Test
    public void queryBooks() {
        List<Book> list = bookDao.queryBooks();
        list.forEach(System.out::println);
    }


    @Test
    public void queryForPageTotalCount() {
        long count = bookDao.queryForPageTotalCount();
        System.out.println(count);
    }

    @Test
    public void queryForPageItems() {
        List<Book> list = bookDao.queryForPageItems(0, Page.DEFAULT_PAGE_SIZE);
        System.out.println(list);
        list = bookDao.queryForPageItems(2,Page.DEFAULT_PAGE_SIZE);
        System.out.println(list);
        list = bookDao.queryForPageItems(20, Page.DEFAULT_PAGE_SIZE);
        System.out.println(list);
    }

}