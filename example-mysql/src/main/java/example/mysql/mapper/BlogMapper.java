package example.mysql.mapper;

import java.util.List;

import example.mysql.Blog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

public interface BlogMapper {
    @Select("SELECT * FROM blog;")
    List<Blog> selectAll();

    @Select("SELECT * FROM blog WHERE id=#{id};")
    Blog selectById(int id);

    @Insert("INSERT INTO blog(title) VALUES(#{title});")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(Blog blog);
}
