package com.jw.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import oracle.jdbc.pool.OracleDataSource;

@Configuration
@ConfigurationProperties(prefix = "spring.course")
@EnableJpaRepositories(basePackages="com.jw.dao.repo", entityManagerFactoryRef="courseEntityManagerFactory") //, transactionManagerRef="courseTransactionManager")
//@EnableTransactionManagement
//@EnableAutoConfiguration
//@EntityScan(basePackages="com.example.dao.entities")
public class OracleConfig {

	private static final String[] PACKAGES_TO_SCAN = { "com.jw.dao.entities" };
	
	private String url;
	private String username;
	private String password;
	private String dialect;
	
	public void setUrl(String url) {
		this.url = url;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setDialect(String dialect) {
		this.dialect = dialect;
	}
	
	@Bean(name="courseDataSource")
	DataSource courseDataSource() throws SQLException{
		OracleDataSource ds = new OracleDataSource();
		ds.setURL(url);
		ds.setUser(username);
		ds.setPassword(password);
		return ds;
	}
	
	private HibernateJpaVendorAdapter vendorAdapter(){
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setShowSql(true);
		vendorAdapter.setGenerateDdl(false);
		vendorAdapter.setDatabase(Database.ORACLE);
		vendorAdapter.setDatabasePlatform(dialect);
		return vendorAdapter;
	}
	
	@Bean(name="courseEntityManagerFactory")
	LocalContainerEntityManagerFactoryBean courseEntityManagerFactory() throws SQLException{
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setDataSource(courseDataSource());
		factoryBean.setJpaVendorAdapter(vendorAdapter());
		factoryBean.setPersistenceUnitName("coursePersistentUnit");
		factoryBean.setPackagesToScan(PACKAGES_TO_SCAN);
		return factoryBean;
	}

}
