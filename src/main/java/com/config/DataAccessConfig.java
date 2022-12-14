package com.config;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration // 이 클래스를 통해 bean 등록이나 각종 설정을 하겠다는 표시
@PropertySource("classpath:application.properties") // 설정파일에서 읽어올 설정 정보들의 경로를 지정          
public class DataAccessConfig {
	
	  @Autowired
	    ApplicationContext applicationContext;
	  // HikariCP 설정
	    @Bean // 스프링 빈으로 등록
	    @ConfigurationProperties(prefix = "spring.datasource.hikari") // 읽어올 설정 정보의 prefix 지정
	    public HikariConfig hikariConfig() {
	        return new HikariConfig();
	    }

	    @Bean
	    public DataSource dataSource() {
	        return new HikariDataSource(hikariConfig());
	    }
	    // MyBatis 설정
	    @Bean
	    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
	        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
	        sqlSessionFactoryBean.setDataSource(dataSource);
	        sqlSessionFactoryBean.setTypeAliasesPackage("com.dto");
	        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:com/mapper/*.xml"));
	        return sqlSessionFactoryBean.getObject();
	    }
	    // SqlSession 을 구현하고 코드에서 SqlSession를 대체
	    @Bean
	    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
	        return new SqlSessionTemplate(sqlSessionFactory);
	    }
	    // 파일업로드
	    @Bean
	    public CommonsMultipartResolver multipartResolver() {
	    	CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	    	multipartResolver.setDefaultEncoding("UTF-8"); //파일 인코딩 설정
	    	multipartResolver.setMaxUploadSizePerFile(5*1024*1024); // 파일당 업로드 크기 제한(5MB)
	    	return multipartResolver;
	    }
	    
}

