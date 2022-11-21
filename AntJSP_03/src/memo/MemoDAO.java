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
        return items; // 결과리턴
    }
}
