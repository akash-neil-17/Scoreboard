package com.wlfscsg.GroupCamp.config;

import com.wlfscsg.GroupCamp.dto.ActiveUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.io.IOException;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ActiveUser activeUser(){
            return new ActiveUser();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails adminUser1 = User.builder()
                .username("admin1")
                .password(bCryptPasswordEncoder().encode("admin1"))
                .roles("ADMIN")
                .build();
        UserDetails adminUser2 = User.builder()
                .username("admin2")
                .password(bCryptPasswordEncoder().encode("admin2"))
                .roles("ADMIN")
                .build();
        UserDetails adminUser3 = User.builder()
                .username("admin3")
                .password(bCryptPasswordEncoder().encode("admin3"))
                .roles("ADMIN")
                .build();
        UserDetails adminUser4 = User.builder()
                .username("admin4")
                .password(bCryptPasswordEncoder().encode("admin4"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(adminUser1, adminUser2, adminUser3, adminUser4);
    }

    @Bean
    public SecurityFilterChain sessionSecurityFilterChain(HttpSecurity http, AuthenticationSuccessHandler authenticationSuccessHandler, AuthenticationFailureHandler authenticationFailureHandler, ActiveUser activeUser) throws Exception {
        http
                .authorizeHttpRequests( configurer -> {
                    configurer.requestMatchers("/css/**","/icon/**","/img/**","/js/**","/font/**").permitAll();
                    configurer.requestMatchers("/","/home", "/dashboard","/patrol", "/activity","/point", "/patrol/**", "/activity/**", "/point/**","/admin/**").hasRole("ADMIN");
                    configurer.anyRequest().authenticated();
                })
                .formLogin(form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/processSignIn")
                                .usernameParameter("userName")
                                .passwordParameter("userLoginPassword")
                                .successHandler(authenticationSuccessHandler)
//                        .defaultSuccessUrl("/home")
//                        .failureUrl("/login?error=true")
                                .failureHandler(authenticationFailureHandler)
                                .permitAll()
                )
                .logout(logout -> logout
                        .deleteCookies("IB_ADMIN_SESSION")
                        .invalidateHttpSession(true)
                        .logoutSuccessHandler(new LogoutSuccessHandler() {
                            @Override
                            public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                                response.sendRedirect(request.getContextPath());
                            }
                        })
                        .permitAll()
                )
        ;
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
