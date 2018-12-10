package jdev.config;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

public class WebViewConfig extends WebMvcConfigurerAdapter {
    
    public void addViewControllers(ViewControllerRegistry reg){
        reg.addViewController("/home").setViewName("home");
        reg.addViewController("/").setViewName("home");
        reg.addViewController("/hello").setViewName("hello");
        reg.addViewController("/login").setViewName("login");
        reg.addViewController("/error").setViewName("error");
        reg.addViewController("/admin").setViewName("admin");
    }
}
