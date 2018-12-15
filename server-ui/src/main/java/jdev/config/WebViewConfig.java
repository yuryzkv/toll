package jdev.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebViewConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry reg){
        reg.addViewController("/client").setViewName("client");
        reg.addViewController("error").setViewName("error");
        reg.addViewController("/").setViewName("home");
        reg.addViewController("/login").setViewName("login");
        reg.addViewController("/manager").setViewName("manager");
        reg.addViewController("/payments").setViewName("payments");
        reg.addViewController("/registerClient").setViewName("registerClient");
        reg.addViewController("/registerManager").setViewName("registerManager");
        reg.addViewController("/routes").setViewName("routes");




    }
}
