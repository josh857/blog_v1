package com.portal.service;


import com.portal.pojo.Dto.UserLoginDto;
import com.portal.pojo.Dto.UserRegistDto;
import com.portal.pojo.Vo.UserVo;


public interface UserService {


    String registerUser (UserRegistDto registDto);

    String login(UserLoginDto loginDto);

    UserVo getUserByEmail(String email);
}
