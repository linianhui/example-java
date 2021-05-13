package mysqlexample;

import java.util.List;
import java.util.Map;

import mysqlexample.mapper.DDLBlogMapper;
import mysqlexample.util.LogUtil;
import mysqlexample.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class App {
    public static void main(String[] args) throws Exception {
        LogUtil.logCaller();
        final SqlSessionFactory sqlSessionFactory = MyBatisUtil.buildSqlSessionFactory();
        try (SqlSession session = sqlSessionFactory.openSession()) {
            final DDLBlogMapper ddlBlogMapper = session.getMapper(DDLBlogMapper.class);
            createTableIfNotExists(ddlBlogMapper);
            getTableInformation(ddlBlogMapper);
        }
    }

    private static void getTableInformation(DDLBlogMapper ddlBlogMapper) {
        List<Map> tableInformation = ddlBlogMapper.getTableInformation();
        LogUtil.logCaller(tableInformation);
    }

    private static void createTableIfNotExists(DDLBlogMapper ddlBlogMapper) {
        LogUtil.logCaller();
        ddlBlogMapper.createTableIfNotExists();
    }


}
