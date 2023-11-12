package com.portal.security;





import com.portal.security.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;




@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    //jwtFilter檢查並生成 token
    private final  JwtAuthenticationFilter jwtAuthenticationFilter ;
    //使用者權限與db 身分認證
    private final AuthenticationProvider authenticationProvider;

    @Bean
        public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((req)->{
                    req.requestMatchers("/v1/**","/v1/login","/v1/login.html","/**").permitAll();
                            req.anyRequest().authenticated();
                })
                .sessionManagement((session)->{
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                //login
                .formLogin((login)->{
                    login   .loginPage("/v1/login.html")
                            .loginProcessingUrl("/v1/login")
                            .successForwardUrl("/v1/index.html")
                            .failureForwardUrl("/v1/login.html")
                            .permitAll();
                })
                //logout
                .logout((out)->{
                    out.permitAll();
                });
           return http.build();
    }





}
