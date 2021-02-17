package com.project.RestaurantReviewer;

import com.project.RestaurantReviewer.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class RestaurantReviewerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantReviewerApplication.class, args);
	}

}
