package mysqlexample.mapper;

import java.util.List;
import java.util.UUID;

import mysqlexample.AbstractMySqlTest;
import mysqlexample.Blog;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BlogMapperTest extends AbstractMySqlTest {

    private BlogMapper blogMapper;

    @Override
    public void beforeEachCore() {
        blogMapper = getMapper(BlogMapper.class);
    }

    @Test
    void test_insert_should_ok() {
        final String title = UUID.randomUUID().toString();
        final Blog blog = new Blog(title);
        blogMapper.insert(blog);

        Assertions.assertNotEquals(0, blog.getId());
    }

    @Test
    void test_selectById_should_ok() {
        final String title = UUID.randomUUID().toString();
        final Blog blog = new Blog(title);
        blogMapper.insert(blog);

        final Blog actual = blogMapper.selectById(blog.getId());

        Assertions.assertEquals(blog.getId(), actual.getId());
        Assertions.assertEquals(title, actual.getTitle());
    }

    @Test
    void test_selectAll_should_ok() {
        test_insert_should_ok();
        test_insert_should_ok();

        final List<Blog> actual = blogMapper.selectAll();

        Assertions.assertEquals(2, actual.size());
    }
}