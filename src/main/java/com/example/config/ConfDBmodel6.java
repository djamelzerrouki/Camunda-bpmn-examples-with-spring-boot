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
@EnableJpaRepositories(entityManagerFactoryRef = "model6EntityManagerFactory", transactionManagerRef = "model6TransactionManager", basePackages = {
		"com.example.repository.model6" })
public class ConfDBmodel6 {

	@Bean(name = "model6DataSource")
	@ConfigurationProperties(prefix = "spring.model6.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "model6EntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean model6EntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("model6DataSource") DataSource dataSource) {
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		return builder.dataSource(dataSource).properties(properties)
				.packages("com.example.model","com.example.repository.model6").persistenceUnit("model6").build();
	}

	@Bean(name = "model6TransactionManager")
	public PlatformTransactionManager model6TransactionManager(
			@Qualifier("model6EntityManagerFactory") EntityManagerFactory model6EntityManagerFactory) {
		return new JpaTransactionManager(model6EntityManagerFactory);
	}
}
