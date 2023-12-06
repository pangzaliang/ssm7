package z.z.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${hikariCP.driverClassName}")
    private String driverClassName;
    @Value("${hikariCP.jdbcUrl}")
    private String jdbcUrl;
    @Value("${hikariCP.userName}")
    private String userName;
    @Value("${hikariCP.password}")
    private String password;
    @Value("${hikariCP.autoCommit}")
    private Boolean autoCommit;
    @Value("${hikariCP.poolName}")
    private String poolName;

    /**
     * 配置数据源
     * @return DataSource
     */
    @Bean
    public HikariDataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        dataSource.setAutoCommit(autoCommit);
        dataSource.setPoolName(poolName);
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
