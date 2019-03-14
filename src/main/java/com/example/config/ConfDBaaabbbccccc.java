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
@EnableJpaRepositories(entityManagerFactoryRef = "aaabbbcccccEntityManagerFactory", transactionManagerRef = "aaabbbcccccTransactionManager", basePackages = {
		"com.example.repository.aaabbbccccc" })
public class ConfDBaaabbbccccc {

	@Bean(name = "aaabbbcccccDataSource")
	@ConfigurationProperties(prefix = "spring.aaabbbccccc.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "aaabbbcccccEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean aaabbbcccccEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("aaabbbcccccDataSource") DataSource dataSource) {
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		return builder.dataSource(dataSource).properties(properties)
				.packages("com.example.model","com.example.repository.aaabbbccccc").persistenceUnit("aaabbbccccc").build();
	}

	@Bean(name = "aaabbbcccccTransactionManager")
	public PlatformTransactionManager aaabbbcccccTransactionManager(
			@Qualifier("aaabbbcccccEntityManagerFactory") EntityManagerFactory aaabbbcccccEntityManagerFactory) {
		return new JpaTransactionManager(aaabbbcccccEntityManagerFactory);
	}
}
