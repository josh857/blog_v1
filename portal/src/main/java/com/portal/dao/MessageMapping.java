package com.portal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.portal.pojo.Entity.Message;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageMapping extends BaseMapper<Message> {

}
