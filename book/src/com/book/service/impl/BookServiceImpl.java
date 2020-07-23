package com.book.service.impl;

import com.book.dao.BookDao;
import com.book.dao.impl.BookDaoImpl;
import com.book.pojo.Book;
import com.book.pojo.Page;
import com.book.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {

    private BookDao bookDao = new BookDaoImpl();

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        page.setPageTotalCount(pageTotalCount);
        Integer pageTotal = pageTotalCount / pageSize;
        if(pageTotalCount % pageSize != 0){
            pageTotal++;
        }
        page.setPageTotal(pageTotal);
        page.selfCheck();
        List<Book> items = bookDao.queryForPageItems(page.getBeginItemIndex(), page.getPageSize());
        page.setItems(items);
        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page = new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(min, max);
        page.setPageTotalCount(pageTotalCount);
        Integer pageTotal = pageTotalCount / pageSize;
        if(pageTotalCount % pageSize != 0){
            pageTotal++;
        }
        page.setPageTotal(pageTotal);
        page.selfCheck();
        List<Book> items = bookDao.queryForPageItemsByPrice(page.getBeginItemIndex(), page.getPageSize(), min, max);
        page.setItems(items);
        return page;
    }
}
