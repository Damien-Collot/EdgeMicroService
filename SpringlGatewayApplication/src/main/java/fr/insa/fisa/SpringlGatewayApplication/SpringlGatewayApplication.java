package fr.insa.fisa.SpringlGatewayApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringlGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringlGatewayApplication.class, args);
	}

}
