package shop;

import org.apache.ibatis.session.SqlSession;
import sqlmap.MybatisManager;

public class AdminDAO {
    public String login(MemberDTO dto){
        SqlSession session = MybatisManager.getInstance().openSession();
        String name = session.selectOne("admin.login",dto);
        session.close();
        return name;
    }
}
