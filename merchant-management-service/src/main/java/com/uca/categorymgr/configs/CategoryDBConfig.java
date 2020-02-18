package com.uca.categorymgr.configs;

import java.util.HashMap;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * 
 * @author Udara Amarasinghe
 *
 */
//@Configuration
public class CategoryDBConfig {

	@Value("${datasource.url}")
	private String databaseUrl;

	@Value("${datasource.username}")
	private String username;

	@Value("${datasource.password}")
	private String password;

	@Value("${datasource.driverClassName}")
	private String driverClassName;

	@Value("${datasource.maxActive}")
	private String maxActive;

	@Value("${datasource.validationQuery}")
	private String validationQuery;

	@Value("${datasource.testWhileIdle}")
	private String testWhileIdle;

	@Value("${datasource.hibernate.dialect}")
	private String dialect;

	@Value("${datasource.hibernate.show_sql}")
	private String hibernate_show_sql;

	@Value("${datasource.hibernate.auto}")
	private String auto;

	@Value("classpath:${datasource.schema}")
	private Resource schemaScript;

	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean dboneEntityManager() {
		HashMap<String, Object> properties = new HashMap<String, Object>();
		properties.put("hibernate.hbm2ddl.auto", auto);
		properties.put("hibernate.dialect", dialect);
		properties.put("hibernate.show_sql", hibernate_show_sql);
		properties.put("hibernate.naming-strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
		properties.put("hibernate.physical_naming_strategy",
				"org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
		properties.put("hibernate.enable_lazy_load_no_trans", "true");

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dboneDataSource());
		em.setPackagesToScan(new String[] { "com.uca.assignment.entities" });

		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaPropertyMap(properties);
		return em;
	}

	@Bean
	@Primary
	public DataSource dboneDataSource() {
		Properties connectionProperties = new Properties();
		connectionProperties.setProperty("testWhileIdle", testWhileIdle);
		connectionProperties.setProperty("validationQuery", validationQuery);
		connectionProperties.setProperty("maxActive", maxActive);

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(databaseUrl);
		dataSource.setUsername(username);
		dataSource.setPassword(password);

		dataSource.setConnectionProperties(connectionProperties);

		return dataSource;
	}

	@Primary
	@Bean
	public PlatformTransactionManager dboneTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();

		transactionManager.setEntityManagerFactory(dboneEntityManager().getObject());

		return transactionManager;
	}

	@Bean
	public DataSourceInitializer dboneDataSourceInitializer(@Qualifier("dboneDataSource") DataSource dataSource) {
		DataSourceInitializer initializer = new DataSourceInitializer();
		initializer.setDataSource(dataSource);
		initializer.setDatabasePopulator(databasePopulator());
		return initializer;
	}

	private DatabasePopulator databasePopulator() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(schemaScript);

		return populator;
	}
}