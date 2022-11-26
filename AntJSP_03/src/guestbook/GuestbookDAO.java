package guestbook;

import org.apache.ibatis.session.SqlSession;
import sqlmap.MybatisManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuestbookDAO {

    // searchkey : 검색 옵션    search : 검색명
    public List<GuestbookDTO> list(String searchkey, String search){
        SqlSession session = MybatisManager.getInstance().openSession();
        List<GuestbookDTO> list = null;

        // 검색 옵션 : 이름과 내용으로 찾기.
        if(searchkey.equals("name_contents")){
            list = session.selectList("guestbook.list_all", search);
        } else{ // 검색 옵션 : 이름 또는 내용으로 찾기.
            Map<String,Object> map = new HashMap<>();
            map.put("searchkey",searchkey);
            map.put("search", search);
            list = session.selectList("guestbook.list", map);
        }
        session.close();
        return list;
    }

    public void insert(GuestbookDTO dto){
        SqlSession session = MybatisManager.getInstance().openSession();
        String contents = dto.getContents();
        // 태그 문자 처리
        contents = contents.replace("<","&lt;");
        contents = contents.replace(">","&gt;");
        // 공백 문자 처리
        contents = contents.replace("  ","&nbsp;&nbsp");
        dto.setContents(contents);
        session.insert("guestbook.insert",dto);
        session.commit();
        session.close();
    }

    // 비밀번호 찾기
    public boolean check_pwd(int idx, String passwd){ // 글번호, 비밀번호
        boolean result = false;
        SqlSession session = MybatisManager.getInstance().openSession();
        GuestbookDTO dto = new GuestbookDTO();
        dto.setIdx(idx);
        dto.setPasswd(passwd);
        int count = session.selectOne("guestbook.check_pwd",dto);
        result = count==1?true:false;
        session.close();
        return result;
    }

    public GuestbookDTO detail(int idx){
        GuestbookDTO dto = new GuestbookDTO();
        SqlSession session = MybatisManager.getInstance().openSession();
        dto=session.selectOne("guestbook.detail",idx);
        session.close();
        return dto;
    }

    public void update(GuestbookDTO dto){
        SqlSession session = MybatisManager.getInstance().openSession();
        session.update("guestbook.update",dto);
        session.commit();
        session.close();
    }

    public void delete(int idx){
        SqlSession session = MybatisManager.getInstance().openSession();
        session.delete("guestbook.delete",idx);
        session.commit();
        session.close();
    }
}
