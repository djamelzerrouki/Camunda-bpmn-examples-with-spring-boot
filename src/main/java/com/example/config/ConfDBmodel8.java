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
@EnableJpaRepositories(entityManagerFactoryRef = "model8EntityManagerFactory", transactionManagerRef = "model8TransactionManager", basePackages = {
		"com.example.repository.model8" })
public class ConfDBmodel8 {

	@Bean(name = "model8DataSource")
	@ConfigurationProperties(prefix = "spring.model8.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "model8EntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean model8EntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("model8DataSource") DataSource dataSource) {
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		return builder.dataSource(dataSource).properties(properties)
				.packages("com.example.model","com.example.repository.model8").persistenceUnit("model8").build();
	}

	@Bean(name = "model8TransactionManager")
	public PlatformTransactionManager model8TransactionManager(
			@Qualifier("model8EntityManagerFactory") EntityManagerFactory model8EntityManagerFactory) {
		return new JpaTransactionManager(model8EntityManagerFactory);
	}
}
