package book;

import common.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    public List<BookDTO> list_book(){
        List<BookDTO> items = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            conn = DB.dbConn();
            String sql ="select * from books";
            pstmt = conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while(rs.next()){
                BookDTO dto = new BookDTO();
                dto.setIdx(rs.getInt("idx"));
                dto.setTitle(rs.getString("title"));
                dto.setAuthor(rs.getString("author"));
                dto.setPrice(rs.getInt("price"));
                dto.setAmount(rs.getInt("amount"));
                items.add(dto);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DB.dbClose(conn, pstmt, rs);
        }
        return items;
    }
}
