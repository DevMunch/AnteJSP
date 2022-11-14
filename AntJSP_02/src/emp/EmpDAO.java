package emp;

import common.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class EmpDAO {
    public void insert(){
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            conn = DB.dbConn();
            conn.setAutoCommit(false); // auto commit 해제
            long before = System.currentTimeMillis(); // 현재 시간을 측정, 타임스탬프 값을 밀리세컨드로 가져온다.
            for(int i=1; i<=100000; i++){
                String sql = "insert into emp2 (empno, ename, deptno) values(?, ?, ?)";
                pstmt=conn.prepareStatement(sql);
                pstmt.setInt(1, i);
                pstmt.setString(2, "kim"+i);
                pstmt.setInt(3,i);
                pstmt.executeUpdate(); // 추가
                pstmt.close(); // 1. sql, 1 statement 이므로 하나 넣고 무조건 해제해야한다.
            }
            long after = System.currentTimeMillis();
            conn.commit(); // 커밋
            conn.setAutoCommit(true); // 다시 auto commit 설정
            System.out.println("실행시간:" + (after-before));
        }catch (Exception e){
            e.printStackTrace();
            try{
                if(conn!=null) conn.rollback(); // 문제시 rollback
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }finally {
            DB.dbClose(conn, pstmt);
        }
    }

    public void insert_batch(){
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            conn = DB.dbConn();
            conn.setAutoCommit(false);
            long before = System.currentTimeMillis();
            // sql과 state가 한번만 이루어진다.
            String sql = "insert into emp2 (empno, ename, deptno) values(?, ?, ?)";
            pstmt=conn.prepareStatement(sql);
            for(int i=100001; i<=200000; i++){
                pstmt.setInt(1, i);
                pstmt.setString(2, "kim"+i);
                pstmt.setInt(3,i);
                pstmt.addBatch(); // 추가 작업 예약
            }
            pstmt.executeBatch(); // 10만건의 작업이 모두 끝난 이후 excute가 한꺼번에 한번만 이루어진다.
            long after = System.currentTimeMillis();
            conn.commit();
            conn.setAutoCommit(true);
            System.out.println("실행시간:" + (after-before));
        }catch (Exception e){
            e.printStackTrace();
            try{
                if(conn!=null) conn.rollback();
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }finally {
            DB.dbClose(conn, pstmt);
        }
    }
}
