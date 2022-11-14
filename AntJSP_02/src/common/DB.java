package common;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DB {

    public static Connection dbConn(){
        DataSource ds=null;
        Connection conn=null;

        try{
            Context ctx = new InitialContext();
            ds=(DataSource) ctx.lookup("java:comp/env/oraDB");
            conn = ds.getConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }

    public static void dbClose(Connection conn, PreparedStatement pstmt, ResultSet rs){
        try {
            if(rs!=null) rs.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            if(pstmt!=null) pstmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            if(conn!=null) conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void dbClose(Connection conn, PreparedStatement pstmt){
        try {
            if(pstmt!=null) pstmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            if(conn!=null) conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void PdbClose(Connection conn, CallableStatement cstmt, ResultSet rs){
        try {
            if(rs!=null) rs.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            if(cstmt!=null) cstmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            if(conn!=null) conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void PdbClose(Connection conn, CallableStatement cstmt){
        try {
            if(cstmt!=null) cstmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            if(conn!=null) conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
