package com.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;import java.sql.*;
import java.io.*;
import java.util.*;
import java.util.logging.Logger;

public class DatabaseConnection {
    public static final String DB_URL="jdbc:mysql://localhost:3306/my_db?useSSL=false&serverTimezone=UTC";
    public static final String USER="root";
    public static final String PASS="gwx114514";


    //get数据库连接
    public static Connection getConn() throws SQLException{
        Connection conn=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
        try{
            conn=DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println("Connected to database");
        }catch(SQLException e){
            e.printStackTrace();
        }
        return conn;
    }
    //close数据库连接
    public void closeAll(Connection conn,PreparedStatement pstmt,ResultSet rs){
        if(rs != null){
            try{
                rs.close();
            }catch (SQLException e){
                e.printStackTrace();

            }
        }
        if(pstmt != null){
            try {
                pstmt.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(conn != null){
            try{
                conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    //执行增删改操作
    public int executeSQL(String preparedSql,Object[] param){
        Connection conn = null;
        PreparedStatement pstmt = null;
        int num=0;
        try{
            conn=getConn();
            pstmt=conn.prepareStatement(preparedSql);
            if(param!=null){
                for(int i=0;i<param.length;i++){
                    pstmt.setObject(i+1,param[i]);
                }
            }
            num=pstmt.executeUpdate();//进行语句的执行
        }catch( SQLException e){
            e.printStackTrace();
        }finally{
            this.closeAll(conn,pstmt,null);
        }
        return num;
    }
}
