package com.ironhack.MidtermProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
//@EnableJpaRepositories
public class MidtermProjectApplication {

	//@Autowired
	//Data data;

	public static void main(String[] args) {
		SpringApplication.run(MidtermProjectApplication.class, args);
	}

		//data.populateRepos();

}
