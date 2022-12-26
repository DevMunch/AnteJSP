package page;

import org.apache.ibatis.session.SqlSession;
import sqlmap.MybatisManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmpDAO {
    public List<EmpDTO> list(int start, int end){
        List<EmpDTO> items = null;
        SqlSession session = null;
        try {
            session = MybatisManager.getInstance().openSession();
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("start", start);    // 페이지 시작번호
            map.put("end", end);        // 페이지가 끝나는 번호
            items = session.selectList("emp.list", map);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(session!=null)session.close();
        }

        return items;
    }

    public int count(){ // 전체 레코드 개수
        int count = 0;
        SqlSession session = null;
        try{
            session = MybatisManager.getInstance().openSession();
            count = session.selectOne("emp.count");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(session!=null) session.close();
        }
        return count;
    }
}
