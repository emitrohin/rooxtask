package ru.emitrohin.roox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan("ru.emitrohin.roox")
@EnableJpaRepositories("ru.emitrohin.roox.repository")
@SpringBootApplication
public class RooxTestTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(RooxTestTaskApplication.class, args);
	}
}
