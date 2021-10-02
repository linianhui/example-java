package example.mysql.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

/*
 * https://mybatis.org/mybatis-3/zh/configuration.html#environments
 * */
public class HikariCPDataSourceFactory extends UnpooledDataSourceFactory {
    public HikariCPDataSourceFactory() {
        final HikariConfig config = new HikariConfig("/hikaricp.properties");
        this.dataSource = new HikariDataSource(config);
    }
}
