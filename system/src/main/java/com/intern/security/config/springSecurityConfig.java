package com.intern.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intern.common.dao.pojo.JsonResult;
import com.intern.security.filter.JwtAuthenticationFilter;
import com.intern.security.handler.CustomLogoutHandler;
import com.intern.security.service.impl.JwtServiceImpl;
import com.intern.security.service.impl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.io.PrintWriter;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class springSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Lazy
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private UserDetailServiceImpl userDetailsServiceImpl;

    @Autowired
    private CustomLogoutHandler customLogoutHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginProcessingUrl("/login")
                .successHandler(customLoginSuccessHandler())
                .and()
                .logout()
                .addLogoutHandler(customLogoutHandler)
                .logoutSuccessHandler(customLogoutSuccessHandler());
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET).permitAll()
                .antMatchers(HttpMethod.POST).permitAll()
                .antMatchers("/global/**").permitAll()
                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/user/setAuthority").hasAuthority("admin")
                .anyRequest().authenticated();


        http
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationFilter, LogoutFilter.class)
                .cors()
                .configurationSource(corsConfigurationSource())
                .and()
                .csrf().disable();

        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

//    @Override
//    public void configure(WebSecurity web) {
//        web.ignoring()
//                .antMatchers("/global/**")
//                .antMatchers("/swagger-ui/**");
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsServiceImpl)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public JwtServiceImpl jwtService() {
        return new JwtServiceImpl();
    }


    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setMaxAge(Duration.ofHours(1));
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    AuthenticationSuccessHandler customLoginSuccessHandler() {
        return (request, response, authentication) -> {
            // run custom logics upon successful login
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtService().generateToken(userDetails);
            Map<String, String> responseBody = Collections.singletonMap("token", token);
            PrintWriter printWriter = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            String jsonOutput = new ObjectMapper().writeValueAsString(JsonResult.success(responseBody));
            printWriter.println(jsonOutput);
            printWriter.flush();
            printWriter.close();
        };
    }

//    LogoutHandler customLogoutHandler() {
//        return (request, response, authentication) -> {
//            if (authentication != null && authentication.getPrincipal() != null) {
//                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//                redisUtil.deleteToken(userDetails.getUsername());
//            }
//        };
//    }

    LogoutSuccessHandler customLogoutSuccessHandler() {
        return (request, response, authentication) -> {
            PrintWriter printWriter = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            String jsonOutput = new ObjectMapper().writeValueAsString(JsonResult.success("Logout"));
            printWriter.println(jsonOutput);
            printWriter.flush();
            printWriter.close();
        };
    }


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    ObjectMapper objectMapper() {
//        return new ObjectMapper();
//    }


}
