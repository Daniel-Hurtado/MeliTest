/**
 * 
 */
package com.mercadolibre.app.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Alejandro.Hurtado
 *
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.mercadolibre")
@ComponentScan(basePackages = "com.mercadolibre")
@EntityScan(basePackages = "com.mercadolibre")
@Profile("test")
public class DataSourceConfig {

}
