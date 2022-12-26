/*
1. MybatisManager
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

2. sqlMapConfig.xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <typeAliases>
    </typeAliases>
    <environments default="">
        <environment id="">
            <transactionManager type="JDBC" />
            <dataSource type="JNDI">
                <property name="data_source" value="java:comp/env/oraDB" />
            </dataSource>
        </environment>
    </environments>
    <mappers>
        <mapper resource="memo/memo.xml" />
    </mappers>
</configuration>

*/