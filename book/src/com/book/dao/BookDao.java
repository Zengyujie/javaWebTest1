package com.book.dao;

import com.book.pojo.Book;

import java.util.List;

public interface BookDao {

    public int addBook(Book book);

    public int deleteBookById(Integer id);

    public int updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    public Integer queryForPageTotalCount();

    public List<Book> queryForPageItems(Integer begin, Integer pageSize);

    Integer queryForPageTotalCountByPrice(int min, int max);

    List<Book> queryForPageItemsByPrice(Integer beginItemIndex, Integer pageSize, int min, int max);
}
