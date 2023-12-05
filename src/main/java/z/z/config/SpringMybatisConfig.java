package z.z.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringMybatisConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactory () throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setMapperLocations();
        bean.setDataSource();
        return bean.getObject();
    }


}
