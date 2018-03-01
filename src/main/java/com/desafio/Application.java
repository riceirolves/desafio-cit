package com.desafio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public static void main(String args[]) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {

		jdbcTemplate.execute("DROP TABLE event IF EXISTS");
		jdbcTemplate.execute("CREATE TABLE event("
				+ "id SERIAL, modalidade VARCHAR(255), local VARCHAR(255), inicio TIMESTAMP, termino TIMESTAMP, pais1 VARCHAR(255), pais2 VARCHAR(255), etapa VARCHAR(255))");

	}
}