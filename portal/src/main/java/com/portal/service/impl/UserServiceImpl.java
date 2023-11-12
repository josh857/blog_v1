package com.portal.service.impl;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.portal.dao.UserMapping;
import com.portal.exception.ServiceException;
import com.portal.pojo.Dto.Role;
import com.portal.pojo.Dto.UserLoginDto;
import com.portal.pojo.Dto.UserRegistDto;
import com.portal.pojo.Entity.User;
import com.portal.security.jwt.JwtService;
import com.portal.service.UserService;

import com.portal.webResult.JsonResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

@Autowired
UserMapping userMapping;



private final PasswordEncoder passwordEncoder;

private final JwtService jwtService;

private final AuthenticationManager authenticationManager;
    /**
     * 根據用戶名獲取用戶
     * @param username
     * @return
     */
    @Override
    public User getuserByUserName(String username) {
        if(username==null){
            throw new ServiceException("找不到用戶名");
        }
        User user = userMapping.currentUserByUsername(username);
        if (user==null){
            throw new ServiceException("找不到用戶");
        }
        return user;
    }


    //註冊業務
    @Transactional
    @Override
    public String register(UserRegistDto registDto) {
        //格式化時間
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date =sdf.format(new Date());
        //密碼加密
        String password=passwordEncoder.encode(registDto.getPassword());
        User user = new User();
        user.setEmail(registDto.getEmail());
        user.setNickname(registDto.getNickname());
        user.setRole(Role.USER);
        user.setPassword(password);
        user.setCreatedatetime(date);
        user.setUpdatetime(date);
        userMapping.insert(user);
        String token=jwtService.generateToken(user);
        return token;
    }

    //使用者登入
    @Override
    public String login(UserLoginDto loginDto) {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginDto.getEmail(),
                                loginDto.getPassword()
                        )
                );
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("email", loginDto.getEmail());
        User user=userMapping.selectOne(queryWrapper);
        if(user == null){
            throw new ServiceException("找不到用戶");
        }
        String jwtToken = jwtService.generateToken(user);
        return jwtToken;
    }
}
