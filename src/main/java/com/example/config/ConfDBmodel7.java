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
@EnableJpaRepositories(entityManagerFactoryRef = "model7EntityManagerFactory", transactionManagerRef = "model7TransactionManager", basePackages = {
		"com.example.repository.model7" })
public class ConfDBmodel7 {

	@Bean(name = "model7DataSource")
	@ConfigurationProperties(prefix = "spring.model7.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "model7EntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean model7EntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("model7DataSource") DataSource dataSource) {
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		return builder.dataSource(dataSource).properties(properties)
				.packages("com.example.model","com.example.repository.model7").persistenceUnit("model7").build();
	}

	@Bean(name = "model7TransactionManager")
	public PlatformTransactionManager model7TransactionManager(
			@Qualifier("model7EntityManagerFactory") EntityManagerFactory model7EntityManagerFactory) {
		return new JpaTransactionManager(model7EntityManagerFactory);
	}
}
