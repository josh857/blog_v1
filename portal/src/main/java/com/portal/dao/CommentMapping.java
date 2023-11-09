package com.portal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.portal.pojo.Entity.Comment;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapping extends BaseMapper<Comment> {

    @Select("SELECT c.id , c.name , c.content, c.createtime ,c.updatetime \n" +
            "FROM comment c\n" +
            "LEFT JOIN message_comment mc ON mc.comment_id = c.id \n" +
            "LEFT JOIN message m ON mc.message_id=m.id  " +
            "WHERE m.id=#{id} order by c.id")
    List<Comment> getcomments (Integer id);

}
