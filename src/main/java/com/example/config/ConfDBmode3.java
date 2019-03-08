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
@EnableJpaRepositories(entityManagerFactoryRef = "mode3EntityManagerFactory", transactionManagerRef = "mode3TransactionManager", basePackages = {
		"com.example.repository.mode3" })
public class ConfDBmode3 {

	@Bean(name = "mode3DataSource")
	@ConfigurationProperties(prefix = "spring.mode3.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "mode3EntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean mode3EntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("mode3DataSource") DataSource dataSource) {
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		return builder.dataSource(dataSource).properties(properties)
				.packages("com.example.model","com.example.repository.mode3").persistenceUnit("mode3").build();
	}

	@Bean(name = "mode3TransactionManager")
	public PlatformTransactionManager mode3TransactionManager(
			@Qualifier("mode3EntityManagerFactory") EntityManagerFactory mode3EntityManagerFactory) {
		return new JpaTransactionManager(mode3EntityManagerFactory);
	}
}
