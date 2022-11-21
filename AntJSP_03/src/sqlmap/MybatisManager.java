package sqlmap;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

// SqlSessionFactoryBuilder => SqlSessionFactory (SqlSession을 만드는 공장) => SqlSession (sql 샐행 객체)
public class MybatisManager {
    private static SqlSessionFactory instance;

    private MybatisManager(){} // 내부에서만 호출 가능한 생성자
    public static SqlSessionFactory getInstance(){
        Reader reader=null;
        try{
            // mybatis 환경설정 파일을 읽음
            reader=Resources.getResourceAsReader("sqlmap/sqlMapConfig.xml");
            instance = new SqlSessionFactoryBuilder().build(reader); // SqlSessionFactory 생성
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(reader!=null) reader.close();
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
        return  instance; // SqlSessionFactory 리턴
    }
}
