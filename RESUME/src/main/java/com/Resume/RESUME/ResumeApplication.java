package com.Resume.RESUME;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class ResumeApplication {

	@Autowired
	private MongoTemplate mongoTemplate;

	public static void main(String[] args) {
		SpringApplication.run(ResumeApplication.class, args);
	}

	@PostConstruct
	public void testConnection() {
		System.out.println("MongoDB connected. Collections:");
		mongoTemplate.getDb().listCollectionNames().forEach(System.out::println);
	}
}

