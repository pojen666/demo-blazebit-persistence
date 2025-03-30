package com.demo;

import com.demo.repository.impl.BaseRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
@SpringBootApplication
public class DemoBlazebitPersistneceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoBlazebitPersistneceApplication.class, args);
	}

}
