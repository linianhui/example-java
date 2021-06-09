package mysqlexample;

import java.io.IOException;

import mysqlexample.ddlmapper.DDLBlogMapper;
import mysqlexample.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public abstract class AbstractMySqlTest {
    private static SqlSessionFactory sqlSessionFactory;

    private SqlSession sqlSession;

    @BeforeAll
    static void beforeAll() throws IOException {
        sqlSessionFactory = MyBatisUtil.buildSqlSessionFactory("test");
    }

    @BeforeEach
    void beforeEach() {
        sqlSession = sqlSessionFactory.openSession();
        getMapper(DDLBlogMapper.class).createTableIfNotExists();
        beforeEachCore();
    }

    protected abstract void beforeEachCore();

    @AfterEach
    void afterEach() {
        if (sqlSession != null) {
            sqlSession.close();
        }
    }


    protected <T> T getMapper(Class<T> type) {
        return sqlSession.getMapper(type);
    }
}
