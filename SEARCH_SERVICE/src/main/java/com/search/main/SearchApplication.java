package com.search.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Main class for search service
 */
@SpringBootApplication(scanBasePackages = { "com.search" })
@ComponentScan(basePackages = "com.search")
public class SearchApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(SearchApplication.class, args);
	}

	
}
