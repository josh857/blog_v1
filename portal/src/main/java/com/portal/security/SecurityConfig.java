package com.portal.security;





import com.portal.security.jwt.JwtAuthenticationFilter;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;




@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    //jwtFilter檢查並生成 token
    private final  JwtAuthenticationFilter jwtAuthenticationFilter ;
    //使用者權限與db 身分認證
    private final AuthenticationProvider authenticationProvider;

    @Resource
    UserDetailsService userDetailsService;

    @Bean
        public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests((req)->{
                    req.requestMatchers("/swagger-ui.html",
                                                 "/swagger-ui/**",
                                                 "/v3/api-docs/**",
                                                 "/v1/login.html",
                                                 "/v1/login/**",
                                                 "/swagger-ui/index.html",
                                                 "/v1/index.html",
                                                 "/v1/register").permitAll();
                    req.anyRequest().authenticated();
                })
                .userDetailsService(userDetailsService)
                .formLogin((form)->{
                    form.loginPage("http://localhost:5173/")
                            .loginProcessingUrl("/v1/login")
                            .successForwardUrl("/v1/index.html");
                })
                .sessionManagement((session)->{
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }





}
