package com.portal;


import com.portal.pojo.Dto.RegistUserDto;
import com.portal.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    UserService userService;

    @Test
    public void regist(){
        RegistUserDto registUserDto = new RegistUserDto();
        registUserDto.setUsername("josh");
        registUserDto.setPassword("123456");
        registUserDto.setNickname("josh");


    }
}
