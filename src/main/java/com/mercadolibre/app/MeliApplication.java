package com.mercadolibre.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MeliApplication {

	public static void main(String[] args) {
		System.out.println("Hola MercadoLibre");
		SpringApplication.run(MeliApplication.class, args);
	}
	
	/**
     * Define un bean para el elemento de Spring utilizado en el consumo de Apis Rest
     *
     * @return Instancia del bean
     */
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
