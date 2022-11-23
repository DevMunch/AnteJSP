package survey;

import org.apache.ibatis.session.SqlSession;
import sqlmap.MybatisManager;

import java.util.List;

public class SurveyDAO {
    public List<SummaryDTO> summary(int survey_idx){
        SqlSession session= MybatisManager.getInstance().openSession();
        List<SummaryDTO> items = session.selectList("survey.summary",survey_idx);
        session.close();
        return items;
    }

    public void insert(AnswerDTO dto){
        SqlSession session =MybatisManager.getInstance().openSession();
        session.insert("survey.insert", dto);
        session.commit(); // insert, update, delete commit() 해야한다.
        session.close();
    }

    public SurveyDTO view(int survey_idx){ // 문제 내용
        SqlSession session =MybatisManager.getInstance().openSession();
        // 레코드 1개, 네임스패이스.태그id
        SurveyDTO dto = session.selectOne("survey.view",survey_idx);
        session.close();
        return dto;
    }
}
