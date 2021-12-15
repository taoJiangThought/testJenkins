package com.example.myapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@SpringBootApplication
public class MyAppApplication {

    private ThreadLocal<SimpleDateFormat> threadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy/MM/dd hh:mm:ss"));


    public static void main(String[] args) {
        SpringApplication.run(MyAppApplication.class, args);
    }

    @GetMapping("/")
    public String retrieveTime() {
        return threadLocal.get().format(new Date());
    }
}
