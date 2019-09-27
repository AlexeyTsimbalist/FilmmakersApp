package com.alexey.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
@PropertySources({@PropertySource("classpath:database.properties"),
                  @PropertySource("classpath:sql.properties")})
public class JdbcConfiguration {

    @Value("${database.driver}")
    String driver;

    @Value("${database.url}")
    String url;

    @Value("${database.username}")
    String username;

    @Value("${database.password}")
    String password;

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        Resource createScript = new ClassPathResource("create-tables.sql");
        DatabasePopulator databasePopulator = new ResourceDatabasePopulator(createScript);
        DatabasePopulatorUtils.execute(databasePopulator, dataSource);

        Resource insertScript = new ClassPathResource("insert-values.sql");
        databasePopulator = new ResourceDatabasePopulator(insertScript);
        DatabasePopulatorUtils.execute(databasePopulator, dataSource);

        return dataSource;
    }
}
