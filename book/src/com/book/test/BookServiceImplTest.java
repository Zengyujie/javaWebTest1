package com.book.test;

import com.book.pojo.Page;
import com.book.service.BookService;
import com.book.service.impl.BookServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookServiceImplTest {
    BookService bookService = new BookServiceImpl();
    @Test
    public void addBook() {
    }

    @Test
    public void deleteBookById() {
    }

    @Test
    public void updateBook() {
    }

    @Test
    public void queryBookById() {
    }

    @Test
    public void queryBooks() {
    }

    @Test
    public void page() {
        Page page = bookService.page(1,2);
        System.out.println(page);
    }
}