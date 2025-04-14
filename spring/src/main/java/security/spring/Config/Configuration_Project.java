package security.spring.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Configuration_Project {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf(customize -> customize.disable());
        httpSecurity.authorizeHttpRequests(request-> request.anyRequest().authenticated());
        httpSecurity.httpBasic(Customizer.withDefaults());
        return  httpSecurity.build();
    }
}
