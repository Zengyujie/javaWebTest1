package com.book.test;

import com.book.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;

public class JDBCUtilsTest {

    @Test
    public void testJdbcUtils(){
        for(int i = 0; i < 100; i++){
            Connection conn = JDBCUtils.getConnection();
            System.out.println(conn);
            JDBCUtils.close(conn);
        }
    }

}
