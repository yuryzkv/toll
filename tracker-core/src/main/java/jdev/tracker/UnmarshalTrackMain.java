package jdev.tracker;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


@SpringBootApplication
public class UnmarshalTrackMain {

//    public static void main(String... arg){
//        ApplicationContext context = new AnnotationConfigApplicationContext(UnmarshalTrackContext.class);
//    }

    public static void main(String... arg){
        new SpringApplicationBuilder(UnmarshalTrackContext.class).web(false).run(arg);
    }


}
