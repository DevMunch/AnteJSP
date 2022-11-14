package emp;

import common.DB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmpSalaryDAO {

    // 모든 사원정보 리스트 반환.
    public List<EmpDTO> list_emp(){
        List<EmpDTO> items = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            conn = DB.dbConn();
            String sql = "select * from emp order by ename";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                EmpDTO dto = new EmpDTO();
                dto.setEmpno(rs.getInt("empno"));
                dto.setEname(rs.getString("ename"));
                dto.setJob(rs.getString("job"));
                dto.setHiredate(rs.getDate("hiredate"));
                dto.setSal(rs.getInt("sal"));
                items.add(dto);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DB.dbClose(conn, pstmt, rs);
        }
        return items;
    }

    // 트랜잭션 실행 메서드.
    public void update_sal(int empno){
        Connection conn = null;
        CallableStatement cstmt = null; // 저장 프로시저 실행 객체
        try{
            conn = DB.dbConn();
            // ORA-06550 : 프로시저 오류, 프로시저 이름등 확인해보자.
            String sql ="{call update_sal(?)}"; // {call 프로시저이름(전달할 값)}
            cstmt=conn.prepareCall(sql);
            cstmt.setInt(1, empno);
            cstmt.execute(); //저장 프로시저 실행
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(cstmt!=null)cstmt.close();
            }catch (Exception e2){
                e2.printStackTrace();
            }
            try{
                if(conn!=null)conn.close();
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
    }
}
