package memo;

import org.apache.ibatis.session.SqlSession;
import sqlmap.MybatisManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoDAO {
    public List<Map<String,Object>> list(String search_option, String search){
        // mybatis 실행을 위한 SqlSession 객체 생성
        SqlSession session = MybatisManager.getInstance().openSession();
        List<Map<String,Object>>items=null; // 결과 리스트 생성

        // 이름 + 메모로 찾기
        if(search_option.equals("writer_memo")){
            // selectList : 2개 이상의 레코드 집합
            // selectList("네임스페이스.태그id", 전달할 값)
            items=session.selectList("memo.list_all",search);
        }else{ // 이름 또는 메모로 찾기
            Map<String,Object>map=new HashMap<>();
            map.put("search_option", search_option); // 검색 옵션
            map.put("search", search); // 검색 키워드
            items=session.selectList("memo.list", map);
        }
        session.close();
        return items; // 결과리턴
    }
    public void insert(Map<String,Object>map){
        SqlSession session = MybatisManager.getInstance().openSession();
        // 스크립트 공격 방지
        String memo=(String)map.get("memo");
        memo=memo.replace("<","&lt;");
        memo=memo.replace(">","&gt");
        // 브라우저는 공백을 인식못해서 사용.
        memo=memo.replace("  ","&nbsp;&nbsp;");
        map.put("memo",memo);

        // insert 태그를 찾아서 실행. 즉 memo.xml의 memo.insert를 찾아서 실행.
        // 해쉬맵 map에는 이름과 메모내용이 전달된다. #{writer},#{memo}
        session.insert("memo.insert",map);
        session.commit(); // insert, update, delete 커밋해야 한다.
        session.close(); // 세션 닫기
    }

    public Map<String,Object> view(int idx){
        SqlSession session=MybatisManager.getInstance().openSession();
        // selectOne 레코드 1개
        Map<String,Object>map = session.selectOne("memo.view",idx);
        session.close();
        return map;
    }

    public void update(Map<String,Object>map){
        SqlSession session = MybatisManager.getInstance().openSession();

        // 스크립트 공격 방지
        String memo=(String)map.get("memo");
        memo=memo.replace("<","&lt;");
        memo=memo.replace(">","&gt");
        memo=memo.replace("  ","&nbsp;&nbsp;");
        map.put("memo",memo);

        session.update("memo.update",map);
        session.commit();
        session.close();
    }

    public void delete(int idx){
        SqlSession session = MybatisManager.getInstance().openSession();
        session.delete("memo.delete",idx);
        session.commit();
        session.close();
    }
}
