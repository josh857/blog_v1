package com.portal.service.impl;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.portal.dao.UserMapping;
import com.portal.exception.ServiceException;
import com.portal.pojo.Dto.Role;
import com.portal.pojo.Dto.UserLoginDto;
import com.portal.pojo.Dto.UserRegistDto;
import com.portal.pojo.Entity.User;
import com.portal.pojo.Vo.UserVo;
import com.portal.security.jwt.JwtService;
import com.portal.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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
     * 註冊業務
     * @param registDto
     * @return
     */
    //註冊業務
    @Transactional
    @Override
    public String registerUser(UserRegistDto registDto) {
        //格式化時間
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date =sdf.format(new Date());
        //密碼加密
        String password=passwordEncoder.encode(registDto.getPassword());
        //判斷用戶是否存在
        User u= userMapping.getUserByEmail(registDto.getEmail());
        if(u!=null){
            throw new ServiceException("此用戶已存在");
        }
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

    /**
     * 登入作業
     * @param loginDto
     * @return
     */
    //使用者登入
    @Override
    public String login(UserLoginDto loginDto) {
            if(loginDto.getEmail()==null || loginDto.getPassword()==null){
                throw new ServiceException("找不到登入參數");
            }
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginDto.getEmail() , loginDto.getPassword()) ;
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
                if(authentication == null) {
                    throw new ServiceException("用户名或密码错误");
                }
                //********獲取當前登入用戶
                User u = (User) authentication.getPrincipal();
                String jwtToken = jwtService.generateToken(u);
                log.info("principal:{}",u);
                    return jwtToken;
    }

    /**
     * 根據email獲取User
     * @param email
     * @return
     */
    @Override
    public UserVo getUserByEmail(String email) {
        if(email==null){
            throw new ServiceException("找不到用戶email");
        }
        User user=userMapping.getUserByEmail(email);
        if(user ==null){
            throw new ServiceException("找不到用戶");
        }
        UserVo uv = new UserVo();
        uv.setNickname(user.getNickname());
        uv.setRole(user.getRole());
        return uv;
    }
}
