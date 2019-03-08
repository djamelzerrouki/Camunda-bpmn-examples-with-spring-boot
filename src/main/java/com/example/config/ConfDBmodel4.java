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
@EnableJpaRepositories(entityManagerFactoryRef = "model4EntityManagerFactory", transactionManagerRef = "model4TransactionManager", basePackages = {
		"com.example.repository.model4" })
public class ConfDBmodel4 {

	@Bean(name = "model4DataSource")
	@ConfigurationProperties(prefix = "spring.model4.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "model4EntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean model4EntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("model4DataSource") DataSource dataSource) {
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		return builder.dataSource(dataSource).properties(properties)
				.packages("com.example.model","com.example.repository.model4").persistenceUnit("model4").build();
	}

	@Bean(name = "model4TransactionManager")
	public PlatformTransactionManager model4TransactionManager(
			@Qualifier("model4EntityManagerFactory") EntityManagerFactory model4EntityManagerFactory) {
		return new JpaTransactionManager(model4EntityManagerFactory);
	}
}
