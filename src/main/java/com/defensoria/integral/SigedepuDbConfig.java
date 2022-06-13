package com.defensoria.integral;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
@EnableJpaRepositories(
        entityManagerFactoryRef = "sigedepuEntityManagerFactory",
        transactionManagerRef = "sigedepuTransactionManager",
        basePackages = {"com.defensoria.sigedepu.repository"}
)

public class SigedepuDbConfig {

   

    @Bean(name = "sigedepuDataSource")
    @ConfigurationProperties(prefix = "sigedepu.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "sigedepuEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean
            barEntityManagerFactory(
                    EntityManagerFactoryBuilder builder,
                    @Qualifier("sigedepuDataSource") DataSource dataSource
            ) {
        return builder
                .dataSource(dataSource)
                .packages("com.defensoria.sigedepu.model")
                .persistenceUnit("sigedepu")
                .build();
    }

    @Bean(name = "sigedepuTransactionManager")
    public PlatformTransactionManager barTransactionManager(
            @Qualifier("sigedepuEntityManagerFactory") EntityManagerFactory barEntityManagerFactory
    ) {
        return new JpaTransactionManager(barEntityManagerFactory);
    }

   

}
