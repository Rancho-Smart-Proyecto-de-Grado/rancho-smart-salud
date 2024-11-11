package com.rancho_smart.salud_composer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SaludComposerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaludComposerApplication.class, args);
	}

}
