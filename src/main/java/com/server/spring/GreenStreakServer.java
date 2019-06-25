package com.server.spring;

import com.server.database.DatabaseManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GreenStreakServer {

    public static void main(String[] args) {
        DatabaseManager.getInstance();
        SpringApplication.run(GreenStreakServer.class, args);
    }
}
