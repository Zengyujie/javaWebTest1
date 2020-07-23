package com.book.dao.impl;

import com.book.pojo.User;
import com.book.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDAO {

    private QueryRunner queryRunner = new QueryRunner();

    public int update(String sql, Object... args){
        Connection conn = JDBCUtils.getConnection();
        try {
            return queryRunner.update(conn, sql, args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn);
        }
        return -1;
    }


    public <T> T queryForOne(String sql, Class<T> type, Object... args){
        Connection conn = JDBCUtils.getConnection();
        try {
            return queryRunner.query(conn, sql,new BeanHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn);
        }
        return null;
    }


    public <T> List<T> queryForList(String sql, Class<T> type, Object... args){
        Connection conn = JDBCUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new BeanListHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn);
        }
        return null;
    }


    public Object queryForSingleValue(String sql, Object... args){
        Connection conn = JDBCUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new ScalarHandler(), args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn);
        }
        return null;
    }




}
