package jdev.tracker;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UnmarshalTrackMain {

    public static void main(String... arg){
        ApplicationContext context = new AnnotationConfigApplicationContext(UnmarshalTrackContext.class);
    }
}
