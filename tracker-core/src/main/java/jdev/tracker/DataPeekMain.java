package jdev.tracker;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DataPeekMain {
    public static void main(String... args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(DataPeekContext.class);
    }
}
