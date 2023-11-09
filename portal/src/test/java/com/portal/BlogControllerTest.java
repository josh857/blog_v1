package com.portal;

import com.portal.controller.BlogController;
import com.portal.pojo.Dto.BlogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BlogControllerTest {

    @Autowired
    BlogController blogController;
    public void saveBlog(){
        BlogDto bd= new BlogDto();

    }
}
