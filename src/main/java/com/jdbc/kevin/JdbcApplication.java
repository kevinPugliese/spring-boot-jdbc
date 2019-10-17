package com.jdbc.kevin;

import com.jdbc.kevin.person.Person;
import com.jdbc.kevin.person.PersonJdbcRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JdbcApplication implements CommandLineRunner {

	@Autowired
	private PersonJdbcRepository personJdbcRepository;

	private Logger LOG = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		SpringApplication.run(JdbcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		LOG.info("Person id 10001 -> {}", personJdbcRepository.findById(10001L));

		LOG.info("Insert -> {}", personJdbcRepository.insert(new Person(10005L, "João", "joao@gmail.com")));

		LOG.info("Update 10002 -> {}", personJdbcRepository.update(new Person(10002L, "Ana", "ana@gmail.com")));

		LOG.info("All users -> {}", personJdbcRepository.findAll());

	}
}
