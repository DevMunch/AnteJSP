package shop;

import org.apache.ibatis.session.SqlSession;
import sqlmap.MybatisManager;

public class MemberDAO {
    public String login(MemberDTO dto){
        SqlSession session = MybatisManager.getInstance().openSession();
        // selectOne 레코드 1개, selectList 2개 이상
        // 네임스페이스.아이디
        String name=session.selectOne("member.login",dto);
        session.close();
        return name;
    }
}
