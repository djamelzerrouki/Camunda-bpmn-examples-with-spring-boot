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
@EnableJpaRepositories(entityManagerFactoryRef = "model3EntityManagerFactory", transactionManagerRef = "model3TransactionManager", basePackages = {
		"com.example.repository.model3" })
public class ConfDBmodel3 {

	@Bean(name = "model3DataSource")
	@ConfigurationProperties(prefix = "spring.model3.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "model3EntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean model3EntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("model3DataSource") DataSource dataSource) {
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		return builder.dataSource(dataSource).properties(properties)
				.packages("com.example.model","com.example.repository.model3").persistenceUnit("model3").build();
	}

	@Bean(name = "model3TransactionManager")
	public PlatformTransactionManager model3TransactionManager(
			@Qualifier("model3EntityManagerFactory") EntityManagerFactory model3EntityManagerFactory) {
		return new JpaTransactionManager(model3EntityManagerFactory);
	}
}
