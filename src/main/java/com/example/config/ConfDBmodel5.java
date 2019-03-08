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
@EnableJpaRepositories(entityManagerFactoryRef = "model5EntityManagerFactory", transactionManagerRef = "model5TransactionManager", basePackages = {
		"com.example.repository.model5" })
public class ConfDBmodel5 {

	@Bean(name = "model5DataSource")
	@ConfigurationProperties(prefix = "spring.model5.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "model5EntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean model5EntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("model5DataSource") DataSource dataSource) {
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		return builder.dataSource(dataSource).properties(properties)
				.packages("com.example.model","com.example.repository.model5").persistenceUnit("model5").build();
	}

	@Bean(name = "model5TransactionManager")
	public PlatformTransactionManager model5TransactionManager(
			@Qualifier("model5EntityManagerFactory") EntityManagerFactory model5EntityManagerFactory) {
		return new JpaTransactionManager(model5EntityManagerFactory);
	}
}
