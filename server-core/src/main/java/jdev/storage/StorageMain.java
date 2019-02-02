package jdev.storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan(basePackages = {"jdev.storage.controllers","jdev.config"})
@EnableJpaRepositories("jdev.dao")
@EntityScan(basePackageClasses = {jdev.domain.Client.class,
        jdev.domain.Manager.class, jdev.domain.PlaceMark.class, jdev.domain.Route.class})
public class StorageMain {


    public static void main(String[] args) {
        SpringApplication.run(StorageMain.class, args);
    }


}
