package com.example.sampleawsapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import com.example.sampleawsapp.config.*;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
@RestController
@ComponentScan(basePackages = "com.example.sampleawsapp.config")
@ConfigurationPropertiesScan({ "com.example.sampleawsapp.config" })
public class SampleawsappApplication {
	public static void main(String[] args) {
		ApplicationContext ac = SpringApplication.run(SampleawsappApplication.class, args);
	}

	@Value("${test}")
	private String testProperty;

	@Autowired
	private ApplicationContext applicationContext;

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			String[] beanNames = applicationContext.getBeanDefinitionNames();
			System.out.println("Listing all beans in the application context:");
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}
		};
	}

//	@Bean
//	public CommandLineRunner commandLineRunner(RolesConfig rolesConfig) {
//		return args -> {
//			System.out.println("Roles Configuration: " + rolesConfig);
//			rolesConfig.getRole().forEach((key, value) -> {
//				System.out.println("Role Key: " + key);
//				System.out.println("Role Details: " + value);
//			});
//		};
//	}

	@GetMapping("/hello")
	public String hello() {
		return testProperty;
	}
}
