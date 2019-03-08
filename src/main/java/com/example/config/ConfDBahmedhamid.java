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
@EnableJpaRepositories(entityManagerFactoryRef = "ahmedhamidEntityManagerFactory", transactionManagerRef = "ahmedhamidTransactionManager", basePackages = {
		"com.example.repository.ahmedhamid" })
public class ConfDBahmedhamid {

	@Bean(name = "ahmedhamidDataSource")
	@ConfigurationProperties(prefix = "spring.ahmedhamid.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "ahmedhamidEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean ahmedhamidEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("ahmedhamidDataSource") DataSource dataSource) {
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		return builder.dataSource(dataSource).properties(properties)
				.packages("com.example.model","com.example.repository.ahmedhamid").persistenceUnit("ahmedhamid").build();
	}

	@Bean(name = "ahmedhamidTransactionManager")
	public PlatformTransactionManager ahmedhamidTransactionManager(
			@Qualifier("ahmedhamidEntityManagerFactory") EntityManagerFactory ahmedhamidEntityManagerFactory) {
		return new JpaTransactionManager(ahmedhamidEntityManagerFactory);
	}
}
