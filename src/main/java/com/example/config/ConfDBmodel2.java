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
@EnableJpaRepositories(entityManagerFactoryRef = "model2EntityManagerFactory", transactionManagerRef = "model2TransactionManager", basePackages = {
		"com.example.repository.model2" })
public class ConfDBmodel2 {

	@Bean(name = "model2DataSource")
	@ConfigurationProperties(prefix = "spring.model2.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "model2EntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean model2EntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("model2DataSource") DataSource dataSource) {
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		return builder.dataSource(dataSource).properties(properties)
				.packages("com.example.model","com.example.repository.model2").persistenceUnit("model2").build();
	}

	@Bean(name = "model2TransactionManager")
	public PlatformTransactionManager model2TransactionManager(
			@Qualifier("model2EntityManagerFactory") EntityManagerFactory model2EntityManagerFactory) {
		return new JpaTransactionManager(model2EntityManagerFactory);
	}
}
