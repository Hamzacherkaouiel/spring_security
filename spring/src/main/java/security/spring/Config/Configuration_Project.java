package security.spring.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import security.spring.services.MyUserService;

@Configuration
@EnableWebSecurity
public class Configuration_Project {
    @Autowired
    private MyUserService myuserservice;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf(customize -> customize.disable());
        httpSecurity.authorizeHttpRequests(request-> request.anyRequest().authenticated());
        httpSecurity.httpBasic(Customizer.withDefaults());
        return  httpSecurity.build();
    }

    /*@Bean
    public UserDetailsService configurationusers(){
        UserDetails user1= User.
                           withDefaultPasswordEncoder().
                           username("hamza1").
                           password("hamza").build();
        UserDetails user2= User.
                withDefaultPasswordEncoder().
                username("hamza2").
                password("123").build();
        return  new InMemoryUserDetailsManager(user1,user2);
    }*/
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        provider.setUserDetailsService(myuserservice);
        return provider;
    }
}
