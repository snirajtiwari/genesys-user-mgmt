package com.genesys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.genesys.usermgmt.*")
@ComponentScan(basePackages = { "com.genesys.usermgmt.*" })
@EntityScan("com.genesys.usermgmt.*")   
public class GenesysUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(GenesysUserApplication.class, args);
	}

}
