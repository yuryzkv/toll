package jdev.storage;

import jdev.service.StoreService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@ComponentScan({"jdev.storage.controllers"})
@EnableJpaRepositories("jdev.dao")
@EntityScan(basePackageClasses = {jdev.domain.Client.class,
        jdev.domain.Manager.class, jdev.domain.PlaceMark.class, jdev.domain.Route.class})
public class StorageMain {


    public static void main(String[] args) {
        SpringApplication.run(StorageMain.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public StoreService getStoreService(){return new StoreService();}
}
