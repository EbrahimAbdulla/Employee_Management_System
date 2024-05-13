package com.retailcloud.employee;

import org.springframework.boot.SpringApplication;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class retailCloudApplication {
	@Bean
	public ModelMapper modelMapper(){
	    return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(retailCloudApplication.class, args);
	}

}
