package com.portal.service;


import com.portal.pojo.Entity.User;
import org.springframework.security.core.userdetails.UserDetails;


public interface UserService {

    User getuserByUserName(String username);

    UserDetails getuserDetail(String username);

}
