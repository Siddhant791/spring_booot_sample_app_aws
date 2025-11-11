package com.example.sampleawsapp;

import com.example.sampleawsapp.config.CustomObject;
import com.example.sampleawsapp.config.FooConfig;
import com.example.sampleawsapp.config.RolesList;
import com.example.utility.StringUtilty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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
        return StringUtilty.convertToUpperCase("Hello");
//		return "Hello";
	}
}
