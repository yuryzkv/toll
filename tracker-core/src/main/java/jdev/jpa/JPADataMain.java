package jdev.jpa;

import jdev.domain.PointInfo;
import jdev.service.PeekService;
import jdev.service.SendService;
import jdev.service.StoreService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling
@EntityScan(basePackageClasses = {jdev.domain.Route.class,
        jdev.domain.PlaceMark.class,PointInfo.class})
public class JPADataMain {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public StoreService getStoreService() {
        return new StoreService();
    }
    @Bean
    public PeekService getPeekService(){ return new PeekService();}
    @Bean
    public RestTemplate restTemplate() { return new RestTemplate();}
    @Bean
    public SendService getSendService(){ return new SendService(new RestTemplate());}


    public static void main(String... arg) {
        new SpringApplicationBuilder(JPADataMain.class).run();
    }
}


