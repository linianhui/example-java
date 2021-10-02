package example.mysql;

import java.io.IOException;

import com.zaxxer.hikari.HikariDataSource;
import example.mysql.ddl.mapper.DDLBlogMapper;
import example.mysql.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public abstract class AbstractMySqlTest {
    private static SqlSessionFactory sqlSessionFactory;

    private SqlSession sqlSession;

    @BeforeAll
    static void beforeAll() throws IOException {
        sqlSessionFactory = MyBatisUtil.buildSqlSessionFactory();
        HikariDataSource dataSource = (HikariDataSource) sqlSessionFactory
            .getConfiguration()
            .getEnvironment()
            .getDataSource();
        Assertions.assertTrue(dataSource.getJdbcUrl().startsWith("jdbc:h2:mem"));
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
        if (sqlSession!=null) {
            sqlSession.close();
        }
    }


    protected <T> T getMapper(Class<T> type) {
        return sqlSession.getMapper(type);
    }
}
