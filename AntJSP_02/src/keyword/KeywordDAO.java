package keyword;

import common.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class KeywordDAO {
    public List<String> list(String keyword){
        List<String> items=new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DB.dbConn();
            String sql="select * from keywords where keyword like ?";
            pstmt= conn.prepareStatement(sql);
            pstmt.setString(1,"%"+keyword+"%");
            rs=pstmt.executeQuery();
            while(rs.next()){
                items.add(rs.getString("keyword"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DB.dbClose(conn, pstmt, rs);
        }
        return items;
    }
}
