package com.webdev.webservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Документация Spring Boot Rest API",
                description = "Документация Spring Boot Rest API",
                version = "v1.0",
                contact = @Contact(
                        name = "Sultan",
                        email = "osultan@yandex.ru",
                        url = "https://github.com/Orunov-Sultan"
                )
        )
)
public class CrudWebserviceApplication {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(CrudWebserviceApplication.class, args);
    }

}
