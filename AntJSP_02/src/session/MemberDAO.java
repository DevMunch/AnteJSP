package session;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import common.DB;

public class MemberDAO {
    public String login(MemberDTO dto) {
        String result = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DB.dbConn();
            String sql = "select * from member where userid=? and passwd=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dto.getUserid());
            pstmt.setString(2, dto.getPasswd());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getString("name");
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            DB.dbClose(conn, pstmt, rs);
        }
        return result;
    }
}