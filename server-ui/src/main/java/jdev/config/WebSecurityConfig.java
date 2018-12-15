package jdev.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http  ) throws Exception{
        http
                .authorizeRequests()
                    .antMatchers("/resources/**").permitAll()
                    .antMatchers("/error").authenticated()
                    .antMatchers("/", "/home").authenticated()
                    .antMatchers("/payments","/routes").hasRole("USER")
                    .antMatchers("/client","/registerClient","/payments","/routes").hasAnyRole("MANAGER","ADMIN")
                    .antMatchers("/manager","/registerManager").hasRole("ADMIN")
                    .anyRequest().permitAll()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll();



    }

    @Autowired
    public void configureRole(AuthenticationManagerBuilder auth)throws Exception{
        auth
                .inMemoryAuthentication()
                    .withUser("user").password("password").roles("USER")
                    .and()
                    .withUser("manager").password("manager").roles("MANAGER","USER")
                    .and()
                    .withUser("root").password("secret").roles("ADMIN","MANAGER","USER");

    }
}
