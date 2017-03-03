package ru.emitrohin.roox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan("ru.emitrohin.roox")
@EnableJpaRepositories("ru.emitrohin.roox.repository")
@SpringBootApplication(exclude = {RepositoryRestMvcAutoConfiguration.class, ErrorMvcAutoConfiguration.class})

public class RooxTestTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(RooxTestTaskApplication.class, args);
	}
}
