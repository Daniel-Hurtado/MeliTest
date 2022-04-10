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
 * 
 * Clase que define un Bean de configuracion de Spring, el cual permite definir la conexion al sistema de base de datos
 * @author Alejandro.Hurtado
 *
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "com.mercadolibre" })
@ComponentScan(basePackages = "com.mercadolibre")
@EntityScan(basePackages = "com.mercadolibre")
@Profile({ "default", "dev" })
public class DataSourceConfiguration {

}
