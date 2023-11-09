package com.portal;

import com.portal.dao.CommentMapping;
import com.portal.pojo.Entity.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MessageMapperTests {

    @Autowired
    CommentMapping commentMapping;

    @Test
    public void getcomments(){
        List<Comment> comments= commentMapping.getcomments(11);
        comments.forEach(comment -> System.out.println(comment));

    }

}
