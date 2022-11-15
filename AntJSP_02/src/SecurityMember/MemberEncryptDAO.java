package SecurityMember;

import common.DB;
import member.MemberDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberEncryptDAO {
    public String login(MemberEncryptDTO dto) {
        String result = "";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DB.dbConn();
            String sql = "select * from member where userid=? and passwd = PACK_ENCRYPTION_DECRYPTION.FUNC_ENCRYPT(?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dto.getUserid());
            pstmt.setString(2, dto.getPasswd());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getString("name") + "님 환영합니다.";
            } else {
                result = "로그인 실패";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.dbClose(conn, pstmt, rs);
        }
        return result;
    }

    public void insertEncript(MemberEncryptDTO dto){
        Connection conn = null;
        PreparedStatement pstmt=null;
        try{
            conn = DB.dbConn();
            String sql = "insert into member(userid,passwd,name) values (?,PACK_ENCRYPTION_DECRYPTION.FUNC_ENCRYPT(?),?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,dto.getUserid());
            pstmt.setString(2,dto.getPasswd());
            pstmt.setString(3,dto.getName());
            pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DB.dbClose(conn,pstmt);
        }
    }
}
