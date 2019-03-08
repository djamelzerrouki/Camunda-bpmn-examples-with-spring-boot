package com.example.config;

import java.util.HashMap;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "jkljlkjEntityManagerFactory", transactionManagerRef = "jkljlkjTransactionManager", basePackages = {
		"com.example.repository.jkljlkj" })
public class ConfDBjkljlkj {

	@Bean(name = "jkljlkjDataSource")
	@ConfigurationProperties(prefix = "spring.jkljlkj.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "jkljlkjEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean jkljlkjEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("jkljlkjDataSource") DataSource dataSource) {
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		return builder.dataSource(dataSource).properties(properties)
				.packages("com.example.model","com.example.repository.jkljlkj").persistenceUnit("jkljlkj").build();
	}

	@Bean(name = "jkljlkjTransactionManager")
	public PlatformTransactionManager jkljlkjTransactionManager(
			@Qualifier("jkljlkjEntityManagerFactory") EntityManagerFactory jkljlkjEntityManagerFactory) {
		return new JpaTransactionManager(jkljlkjEntityManagerFactory);
	}
}
