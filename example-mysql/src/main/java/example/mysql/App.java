package example.mysql;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import example.mysql.mapper.BlogMapper;
import example.mysql.ddl.mapper.DDLBlogMapper;
import example.mysql.util.LogUtil;
import example.mysql.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class App {
    public static void main(String[] args) throws Exception {
        LogUtil.logArgs(args);
        final SqlSessionFactory sqlSessionFactory = MyBatisUtil.buildSqlSessionFactory();
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            final DDLBlogMapper ddlBlogMapper = session.getMapper(DDLBlogMapper.class);
            createTableIfNotExists(ddlBlogMapper);
            getTableInformation(ddlBlogMapper);

            final BlogMapper blogMapper = session.getMapper(BlogMapper.class);
            int newBlogId = insertBlog(blogMapper);
            selectBlogById(blogMapper, newBlogId);
            selectAllBlog(blogMapper);
        }
    }

    private static void selectAllBlog(BlogMapper blogMapper) {
        LogUtil.logReturn(blogMapper.selectAll());
    }

    private static void selectBlogById(BlogMapper blogMapper, int id) {
        LogUtil.logArgs(id);
        LogUtil.logReturn(blogMapper.selectById(id));
    }

    private static int insertBlog(BlogMapper blogMapper) {
        final String title = UUID.randomUUID().toString();
        LogUtil.logArgs(title);
        final Blog blog = new Blog(title);
        blogMapper.insert(blog);
        LogUtil.logReturn(blog.getId());
        return blog.getId();
    }

    private static void getTableInformation(DDLBlogMapper ddlBlogMapper) {
        List<Map> tableInformation = ddlBlogMapper.getTableInformation();
        LogUtil.logReturn(tableInformation);
    }

    private static void createTableIfNotExists(DDLBlogMapper ddlBlogMapper) {
        LogUtil.logArgs();
        ddlBlogMapper.createTableIfNotExists();
    }


}
