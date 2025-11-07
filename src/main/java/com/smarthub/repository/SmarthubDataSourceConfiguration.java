package com.smarthub.repository;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.smarthub.repository",
                        transactionManagerRef = "smarthubTransactionManager",
                        entityManagerFactoryRef = "smarthubEntityManagerFactoryBean")
public class SmarthubDataSourceConfiguration {

    @Bean
    @Primary
    public PlatformTransactionManager smarthubTransactionManager(@Qualifier("smarthubEntityManagerFactoryBean")
                                                                 EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
