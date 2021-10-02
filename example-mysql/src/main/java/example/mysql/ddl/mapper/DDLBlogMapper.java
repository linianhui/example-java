package example.mysql.ddl.mapper;

import java.util.List;
import java.util.Map;

public interface DDLBlogMapper {
    void createTableIfNotExists();

    List<Map> getTableInformation();
}
