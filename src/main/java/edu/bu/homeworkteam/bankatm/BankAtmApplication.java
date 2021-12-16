package edu.bu.homeworkteam.bankatm;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * the entry of the application
 */
@SpringBootApplication
public class BankAtmApplication {
//    public static void main(String[] args) {
//        SpringApplication.run(BankAtmApplication.class, args);
//    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(BankAtmApplication.class)
                .headless(false)
                .web(WebApplicationType.NONE)
                .run(args);
    }
}






