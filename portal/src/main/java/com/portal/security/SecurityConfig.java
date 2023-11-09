package com.portal.security;




import com.portal.security.jwt.JwtAuthenticationFilter;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.StrictHttpFirewall;



@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Resource
     JwtAuthenticationFilter jwtAuthenticationFilter ;
    @Resource
    UserDetail userDetail;


    @Bean
        public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        http.cors(Customizer.withDefaults()).csrf().disable()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .userDetailsService(userDetail)
                .authorizeHttpRequests((req)->{
                    req.requestMatchers("/v1/**").permitAll();
                            req.anyRequest().authenticated();
                })
                .sessionManagement((session)->{
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                //login
                .formLogin((login)->{
                    login
                            .usernameParameter("username")
                            .passwordParameter("password")
                            .loginPage("/login.html")
                            .loginProcessingUrl("/login")
                            .defaultSuccessUrl("http://localhost:5173/index")
                            .failureForwardUrl("/login.html")
                            .permitAll();
                })
                //logout
                .logout((out)->{
                    out.permitAll();
                });
           return http.build();
    }


    //密碼自動加密
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //權限管理
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    //防火牆 csrf
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowBackSlash(true);
        firewall.setAllowUrlEncodedDoubleSlash(true);
        return (web) -> web.httpFirewall(firewall);
    }




}
