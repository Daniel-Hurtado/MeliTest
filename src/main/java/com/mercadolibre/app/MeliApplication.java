package com.mercadolibre.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MeliApplication {

	public static void main(String[] args) {
		System.out.println("Hola MercadoLibre");
		SpringApplication.run(MeliApplication.class, args);
	}

}
