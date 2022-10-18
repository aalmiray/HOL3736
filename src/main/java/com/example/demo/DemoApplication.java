package com.example.demo;

import java.util.Arrays;
import java.io.File;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {
    static {
        System.setProperty("oracle.net.tns_admin",
            System.getProperty("user.dir") + File.separator + "wallet");
    }

    @Bean
    public CommandLineRunner demo(TodoRepository repository) {
        return (args) -> {
            repository.saveAll(Arrays.asList(
                new Todo("Javadoc"),
                new Todo("Application")));

            System.out.println("Display all todos:");
            System.out.println("-------------------------------");
            repository.findAll().forEach(todo -> {
                System.out.println(todo.toString());
            });
            System.out.println("");

            repository.findById(1L).ifPresent(todo -> {
                System.out.println("Todo found with findById(1L):");
                System.out.println("--------------------------------");
                System.out.println(todo.toString());
                System.out.println("");
            });

            System.out.println("Todo found with findByDescription('Application'):");
            System.out.println("--------------------------------------------");
            repository.findByDescription("Application").forEach(todo -> {
                System.out.println(todo.toString());
            });

            System.out.println("");
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
