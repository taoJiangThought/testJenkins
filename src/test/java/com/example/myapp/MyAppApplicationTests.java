package com.example.myapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
class MyAppApplicationTests {

    @Container
    public GenericContainer sql = new GenericContainer(DockerImageName.parse("postgres:last"))
            .withExposedPorts(5432);

    @Test
    void contextLoads() {
    }

}
