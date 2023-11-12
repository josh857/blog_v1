package com.portal;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.portal.dao.UserMapping;
import com.portal.pojo.Dto.UserLoginDto;
import com.portal.pojo.Dto.UserRegistDto;
import com.portal.pojo.Entity.User;
import com.portal.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    UserService userService;
    @Autowired
    UserMapping userMapping;

    @Test
    public void regist(){
        UserRegistDto dto= new UserRegistDto();
        dto.setPassword("000000");
        dto.setNickname("jj");
        dto.setEmail("josh19910507@gmail.com");
        String token=userService.register(dto);
        System.out.println(token);
    }


    @Test
    public void login(){
        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setEmail("josh857@");
        userLoginDto.setPassword("1234");
        String token=userService.login(userLoginDto);
        System.out.println(token);
    }

}
