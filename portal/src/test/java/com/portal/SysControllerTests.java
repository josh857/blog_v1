package com.portal;

import com.portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SysControllerTests {


    @Autowired
    UserService userService;

}
