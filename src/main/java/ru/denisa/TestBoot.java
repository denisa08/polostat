package ru.denisa;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by root on 02/07/17.
 */
//


@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = "ru.denisa")

public class TestBoot {


    public static void main(String[] args) {

        SpringApplication.run(TestBoot.class, args);

     }


}
