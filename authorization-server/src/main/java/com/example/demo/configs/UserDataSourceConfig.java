package com.example.demo.configs;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
public class UserDataSourceConfig {

	@Value("${userdatasource.url}")
	private String datasourceUrl;

	@Value("${userdatasource.username}")
	private String datasourceUsername;

	@Value("${userdatasource.password}")
	private String datasourcePassword;

	@Value("classpath:${userdatasource.schema}")
	private Resource schemaScript;

	@Bean
	public DataSourceInitializer dataSourceInitializerUserDB(@Qualifier("user_dbDataSource") DataSource dataSource) {
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

	@Bean(name = "user_dbDataSource")
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setUrl(datasourceUrl);
		dataSource.setUsername(datasourceUsername);
		dataSource.setPassword(datasourcePassword);
		return dataSource;
	}
}
