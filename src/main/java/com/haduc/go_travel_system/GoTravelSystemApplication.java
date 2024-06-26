package com.haduc.go_travel_system;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Spring Boot REST API Documentation",
                description = "Spring Boot REST API Documentation",
                version = "v1.0",
                contact = @Contact(
                        name = "Ha Duc",
                        email = "havanducdeptrai@gmail.com"
                )
        )
)
@EnableScheduling
public class GoTravelSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoTravelSystemApplication.class, args);
    }

}
