//package com.example.sampleawsapp;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.Map;
//
//@Component
//public class MyRunner implements CommandLineRunner {
//
//    @Autowired
//    public MyRunner(FooConfig fooConfig){
//        this.fooConfig = fooConfig;
//    }
//
//    private final FooConfig fooConfig;
//
//    @Override
//    public void run(String... args) throws Exception {
//        Map<String, List<CustomObject>> list = fooConfig.getList();
//        list.values().forEach(System.out::println);
//    }
//
//
//}