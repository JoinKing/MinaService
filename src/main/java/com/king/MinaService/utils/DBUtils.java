package com.king.MinaService.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 数据库操作工具类
 * @author king
 *
 */
@Component
public class DBUtils {

    //数据库连接地址
    public  static String URL;
    //用户名
    public  static String USERNAME;
    //密码
    public  static String PASSWORD;
    //mysql的驱动类
    public static String DRIVER;

    @Value("${jdbc.url}")
    public void setURL(String URL) {
        DBUtils.URL = URL;
    }

    @Value("${jdbc.username}")
    public void setUSERNAME(String USERNAME) {
        DBUtils.USERNAME = USERNAME;
    }

    @Value("${jdbc.password}")
    public void setPASSWORD(String PASSWORD) {
        DBUtils.PASSWORD = PASSWORD;
    }

    @Value("${jdbc.driver}")
    public void setDRIVER(String DRIVER) {
        DBUtils.DRIVER = DRIVER;
        try {
            Class.forName(DBUtils.DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private DBUtils(){}

    //使用静态块加载驱动程序
    static{
//        try {
//            Class.forName(DBUtils.DRIVER);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    }
    //定义一个获取数据库连接的方法
    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DBUtils.URL,DBUtils. USERNAME,DBUtils. PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("获取连接失败");
        }
        return conn;
    }

    /**
     * 关闭数据库连接
     * @param rs
     * @param stat
     * @param conn
     */
    public static void close(ResultSet rs,Statement stat,Connection conn){
        try {
            if(rs!=null)rs.close();
            if(stat!=null)stat.close();
            if(conn!=null)conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
