package z.z.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * MyBatis配置
 */
@Configuration(proxyBeanMethods = false)
@MapperScan("z.z.mapper")
public class SpringMybatisConfig {

    /**
     * 配置数据源
     * @return DataSource
     */
    @Bean
    public HikariDataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/ppap");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        dataSource.setAutoCommit(Boolean.TRUE); //自动提交事务
        dataSource.setConnectionTimeout(180 * 1000); //连接超时时间
        dataSource.setIdleTimeout(30 * 1000); //闲置后抛弃
        dataSource.setKeepaliveTime(0);// 检查一次连接的活性
        dataSource.setMinimumIdle(5);// 核心连接数
        dataSource.setMaximumPoolSize(10); // 最大连接数
        dataSource.setPoolName("数据库连接池-");
        return dataSource;
    }

    /**
     * 配置SQL会话工厂
     * @param dataSource 数据源
     * @return SqlSessionFactory
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory (HikariDataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    /**
     * 配置数据源事务管理器
     * @param dataSource 数据源
     * @return DataSourceTransactionManager
     */
    @Bean
    public DataSourceTransactionManager transactionManager (DataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }


}
