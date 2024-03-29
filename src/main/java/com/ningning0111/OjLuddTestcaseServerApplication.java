package com.ningning0111;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class OjLuddTestcaseServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OjLuddTestcaseServerApplication.class, args);
    }

}
