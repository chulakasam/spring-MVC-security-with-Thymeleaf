package lk.ijse.demoMVCSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails john = User.builder()
                .username("john")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();
        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER")
                .build();
        UserDetails susan = User.builder()
                .username("susan")
                .password("{noop}test123")
                .roles("EMPLOYEE", "ADMIN", "MANAGER")
                .build();
        return new InMemoryUserDetailsManager(john,mary,susan);
    }

    @Bean
    public SecurityFilterChain  filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configure->configure

                        .requestMatchers("/").hasRole("EMPLOYEE")
                        .requestMatchers("/leaders/**").hasRole("MANAGER")
                        .requestMatchers("/systems/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
        )
                .exceptionHandling(configure->
                        configure.accessDeniedPage("/access_denied"))//this use for create access denied page customize.

        .formLogin(form->
                form
                        .loginPage("/showLogInForm")
                        .loginProcessingUrl("/authenticateTheUser")
                        .permitAll()
        )
        .logout(logout->
                logout.permitAll()
        );
        return http.build();
    }

}
