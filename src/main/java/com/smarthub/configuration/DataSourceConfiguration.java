package com.smarthub.configuration;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.smarthub")
    public DataSourceProperties smartHubProperties(){
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource smarthubDataSource(){
        return smartHubProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean smarthubEntityManagerFactoryBean(EntityManagerFactoryBuilder builder){
        return builder
                .dataSource(smarthubDataSource())
                .packages("com.smarthub.domain.entity")
                .persistenceUnit("smarthub")
                .build();
    }
}
