package com.gymbuddy.backend_project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("/videos/add_video").permitAll()
                                .requestMatchers("/register/**").permitAll()
                                .requestMatchers("/signup").permitAll()
                                .requestMatchers("/my-account").permitAll()
                                .requestMatchers("/questionnaire1").permitAll()
                                .requestMatchers("/questionnaire2").permitAll()
                                .requestMatchers("/home-curiosity").permitAll()
                                .requestMatchers("/home").permitAll()
                                .requestMatchers("/users").hasRole("ADMIN")
                                .requestMatchers("/nutritionists").permitAll()
                                .requestMatchers("/adaugare_nutritionist").permitAll()
                                .requestMatchers("/workouts").permitAll()
                                .requestMatchers("/trainers").permitAll()
                                .requestMatchers("/gyms").permitAll()
                                .requestMatchers("/gyms/add_sali_fitness").permitAll()
                ).formLogin(
                        form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                );
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}
