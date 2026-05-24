package com.pss.CreatorStore;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CreatorStoreApplication {

	public static void main(String[] args) {
//        configuration of dotenv-java
        Dotenv dotenv = Dotenv.configure().directory("./CreatorStore").ignoreIfMissing().load();
        dotenv.entries().forEach((entry) -> System.setProperty(entry.getKey(), entry.getValue()));
		SpringApplication.run(CreatorStoreApplication.class, args);
	}

}
// http://localhost:8080/swagger-ui/index.html#/