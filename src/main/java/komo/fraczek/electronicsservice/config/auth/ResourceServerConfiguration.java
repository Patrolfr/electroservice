package komo.fraczek.electronicsservice.config.auth;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

//@EnableResourceServer
//@RestController
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter
{
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/oauth/token", "/oauth/authorize**").permitAll();
        http.requestMatchers().antMatchers("/privatetest", "/equipments/**")
                .and().authorizeRequests()
                .antMatchers("/privatetest", "/equipments/**").access("hasRole('USER')")
                .and().requestMatchers().antMatchers("/admin")
                .and().authorizeRequests()
                .antMatchers("/admin").access("hasRole('ADMIN')");
    }
}