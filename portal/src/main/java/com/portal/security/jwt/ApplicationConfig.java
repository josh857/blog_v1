package com.portal.security.jwt;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.portal.dao.UserMapping;

import com.portal.exception.ServiceException;
import com.portal.pojo.Entity.User;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.firewall.StrictHttpFirewall;




@RequiredArgsConstructor
@Configuration
public class ApplicationConfig {

    @Resource
    UserMapping userMapping;

   @Bean
   public UserDetailsService userDetailsService(){
      return new UserDetailsService() {
          @Override
          public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
              try {
                  QueryWrapper<User> queryWrapper = new QueryWrapper<>();
                  queryWrapper.eq("email",username);
                  return userMapping.selectOne(queryWrapper);
              }catch (ServiceException e){
                  throw e;
              }
          }
      };
   }

    @Bean
    public AuthenticationProvider  authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
       return  config.getAuthenticationManager();
    }

    //密碼自動加密
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
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
