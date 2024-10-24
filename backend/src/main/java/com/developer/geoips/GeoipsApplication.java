package com.developer.geoips;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GeoipsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeoipsApplication.class, args);
		System.out.println("GeoIps 1.0.0 OnLine");
	}

}
