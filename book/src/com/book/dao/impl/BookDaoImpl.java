package com.book.dao.impl;

import com.book.dao.BookDao;
import com.book.pojo.Book;

import java.util.List;

public class BookDaoImpl extends BaseDAO implements BookDao {

    @Override
    public int addBook(Book book) {
        String sql = "INSERT INTO t_book(`name` , `author` , `price` , `sales` , `stock` , `img_path`) values(?,?,?,?,?,?)";
        return this.update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath());
    }

    @Override
    public int deleteBookById(Integer id) {
        String sql = "delete from t_book where `id` = ?";
        return this.update(sql, id);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "update t_book set `name`=? , `author`=? , `price`=? , `sales`=? , `stock`=? , `img_path`=? where `id` = ?";
        return this.update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath(), book.getId());
    }

    @Override
    public Book queryBookById(Integer id) {
        String sql = "select `id`, `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_book where `id` = ?";
        return this.queryForOne(sql, Book.class, id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "select `id`, `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_book";
        return this.queryForList(sql, Book.class);
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from t_book";
        long res = (Long)this.queryForSingleValue(sql);
        return (int)res;
    }

    @Override
    public List<Book> queryForPageItems(Integer begin, Integer pageSize) {
        String sql = "select `id`, `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_book limit ?,?";
        return this.queryForList(sql, Book.class, begin, pageSize);
    }

    @Override
    public Integer queryForPageTotalCountByPrice(int min, int max) {
        String sql = "select count(*) from t_book where `price` between ? and ?";
        long res =  (Long)this.queryForSingleValue(sql, min, max);
        return (int)res;
    }

    @Override
    public List<Book> queryForPageItemsByPrice(Integer begin, Integer pageSize, int min, int max) {
        String sql = "select `id`, `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_book where `price` between ? and ? order by `price` limit ?,?";
        return this.queryForList(sql, Book.class,min, max, begin, pageSize);
    }
}
