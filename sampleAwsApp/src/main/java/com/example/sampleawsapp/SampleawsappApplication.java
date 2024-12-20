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
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Map;
import java.util.List;

@SpringBootApplication
@RestController
public class SampleawsappApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleawsappApplication.class, args);
	}

	@Autowired
	private FooConfig fooConfig;

	@Autowired
	private RolesList rolesList;

	@GetMapping("/hello")
	public String hello() {
		System.out.println("In hello");
		System.out.println(rolesList.getList());
		Map<String, List<CustomObject>> list = fooConfig.getList();
		list.values().forEach(System.out::println);
		return "Hello";
	}
}
