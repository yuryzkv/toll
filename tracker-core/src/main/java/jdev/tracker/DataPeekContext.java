package jdev.tracker;

import jdev.tracker.services.DataPeekService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@PropertySource("classpath:/app.properties")
public class DataPeekContext {

    @Bean
    public DataPeekService getDataPeekService() {return new DataPeekService();}
}
