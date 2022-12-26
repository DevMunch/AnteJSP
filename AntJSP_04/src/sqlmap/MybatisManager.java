package sqlmap;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class MybatisManager {
    private static SqlSessionFactory instance;

    private MybatisManager(){}

    public static SqlSessionFactory getInstance(){
        Reader reader = null;
        try{
            reader = Resources.getResourceAsReader("sqlmap/sqlMapConfig.xml");
            instance = new SqlSessionFactoryBuilder().build(reader);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try{
                if(reader!=null)
                    reader.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return instance;
    }
}
