package com.distribuida;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.distribuida.repository", considerNestedRepositories = true)
@SpringBootApplication

public class Servidor {

public static void main(String[] args) {
	SpringApplication.run(Servidor.class, args);
}

}
