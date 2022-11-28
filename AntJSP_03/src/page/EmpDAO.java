package page;

import org.apache.ibatis.session.SqlSession;
import sqlmap.MybatisManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmpDAO {
    public List<EmpDTO> list(int start, int end){ // 레코드 번호
        List<EmpDTO> items = null;
        SqlSession session = MybatisManager.getInstance().openSession();
        Map<String,Object> map = new HashMap<>();
        map.put("start",start);
        map.put("end",end);
        items=session.selectList("emp.list",map); // 시작번호와 끝번호를 map에 담아 전달
        return items;
    }
    public int count(){ // 전체 레코드 개수
        int count=0;
        SqlSession session = null;
        try{
            session=MybatisManager.getInstance().openSession();
            count=session.selectOne("emp.count");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(session!=null)
                session.close();
        }
        return count;
    }
}
