package org.gaurav.simpleapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize.requestMatchers("/h2-console","/h2-console/**").permitAll())
                .authorizeHttpRequests((authorize) -> authorize.requestMatchers("/transactions", "/transactions/**").authenticated())
                .authorizeHttpRequests((authorize) -> authorize.anyRequest().permitAll())
                .httpBasic(withDefaults()).csrf().disable();

                        http.headers((headers) ->
                headers
                        .contentTypeOptions(withDefaults())
                        .xssProtection(withDefaults())
                        .cacheControl(withDefaults())
                        .httpStrictTransportSecurity(withDefaults())
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
                        );

        return http.build();



    }

//    @Autowired
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .ldapAuthentication()
//                .userDnPatterns("uid={0},ou=people")
//                .groupSearchBase("ou=groups")
//                .contextSource()
//                .url("ldap://localhost:8389/dc=gaurav,dc=org")
//                .and()
//                .passwordCompare()
//                .passwordAttribute("userPassword");
//    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails userDetails = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(userDetails);
    }


}