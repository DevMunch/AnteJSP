package memo;

import common.DB;
import oracle.jdbc.OracleTypes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemoDAO {

    public void insertMemo(MemoDTO dto){
        Connection conn = null;
        CallableStatement cstmt = null;

        try{
            conn = DB.dbConn();
            String sql = "{call memo_insert_p(?,?,?)}";
            cstmt = conn.prepareCall(sql);
            cstmt.setString(1, dto.getWriter());
            cstmt.setString(2,dto.getMemo());
            cstmt.setString(3,dto.getIp());
            cstmt.execute();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
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

    public List<MemoDTO> list_memo(){
        List<MemoDTO> items = new ArrayList<>();
        Connection conn = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        try{
            conn = DB.dbConn();
            String sql = "{call memo_list_p(?)}";
            cstmt = conn.prepareCall(sql);
            // 현재 리스트는 출력매개변수 이므로 registerOutParameter 메서드를 사용해야 한다.
            // ?에 들어가는 값이 커서타입임을 알려준다.
            // 즉 sql의 out sys_refcursor와 호환된다.
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.execute(); // sql 실행, 커서가 리턴
            // 커서가 가리키는 레코드셋의 시작번지값을 연결한다. ResultSet으로 형변환해서 사용한다.
            rs = (ResultSet) cstmt.getObject(1);
            while(rs.next()){
               int idx = rs.getInt("idx");
               String writer=rs.getString("writer");
               // 크로스사이트 스트립트(로케이션으로 네이버로 보내는등)의 문제와 <xmp>태그(해석안하고 html태그로 출력을 막기위해 처리)
               if(writer.indexOf("<script")!=-1||writer.indexOf("<xmp>")!=-1){
                   // replace(A,B) => A를 B로 변경.
                   writer=writer.replace("<","&lt");
                   writer=writer.replace(">","&gt");
               }
                String memo=rs.getString("memo");
                if(memo.indexOf("<script")!=-1||memo.indexOf("<xmp>")!=-1){
                    memo=memo.replace("<","&lt");
                    memo=memo.replace(">","&gt");
                }
                Date post_date = rs.getDate("post_Date");
                String ip = rs.getString("ip");
                MemoDTO dto = new MemoDTO(idx, writer, memo, post_date, ip);
                items.add(dto);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (cstmt != null)

                    cstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return items;
    }
}
