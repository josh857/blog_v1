package com.portal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.portal.pojo.Entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapping extends BaseMapper<User> {
    @Select("SELECT * from user where username = #{username}")
    User currentUserByUsername (String username);
}
