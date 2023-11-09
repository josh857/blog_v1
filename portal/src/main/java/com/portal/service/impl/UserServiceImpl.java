package com.portal.service.impl;



import com.portal.dao.UserMapping;
import com.portal.exception.ServiceException;
import com.portal.pojo.Entity.User;
import com.portal.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;



@Slf4j
@Service
public class UserServiceImpl implements UserService {

@Autowired
UserMapping userMapping;

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

    /**
     * 根據名字獲取用戶資料放入security裡的 userdetails
     * @param username
     * @return
     */
    @Override
    public UserDetails getuserDetail(String username) {
        User user = getuserByUserName(username);
        return  org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities("/")
                .build();

    }
}
