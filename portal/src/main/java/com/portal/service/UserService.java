package com.portal.service;


import com.portal.pojo.Dto.UserLoginDto;
import com.portal.pojo.Dto.UserRegistDto;
import com.portal.pojo.Entity.User;


public interface UserService {

    User getuserByUserName(String username);

    String register (UserRegistDto registDto);

    String login(UserLoginDto loginDto);

}
