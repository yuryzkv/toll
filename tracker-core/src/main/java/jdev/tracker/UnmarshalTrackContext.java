package jdev.tracker;

import jdev.tracker.services.DataSendService;
import jdev.tracker.services.UnmarshalTrackService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@PropertySource("classpath:/app.properties")
public class UnmarshalTrackContext {

    @Bean
    public UnmarshalTrackService getUnmarshalTrack() {
        return new UnmarshalTrackService();
    }

    @Bean
    public DataSendService getDataSendService() {
        return new DataSendService();
    }
}
