package com.book.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import jdk.nashorn.internal.scripts.JD;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {


    private static DruidDataSource dataSource;

    static{
        Properties properties = null;
        InputStream in = null;
        try{
            properties = new Properties();
            //在javaWeb中，系统加载器调用资源文件的方式失效了
            in = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            properties.load(in);
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection getConnection(){
        Connection conn = null;
        try{
            conn = dataSource.getConnection();
            return conn;
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public static void close(Connection conn){
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

    }

}
