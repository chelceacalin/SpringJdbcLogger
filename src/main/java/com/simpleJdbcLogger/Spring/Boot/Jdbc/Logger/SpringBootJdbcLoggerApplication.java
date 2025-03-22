package com.simpleJdbcLogger.Spring.Boot.Jdbc.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringBootJdbcLoggerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJdbcLoggerApplication.class, args);
    }
}
