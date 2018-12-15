package jdev.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("jdev.config")
public class WebMain {

    public static void main(String... args){
        SpringApplication.run(WebMain.class,args);
    }
}
