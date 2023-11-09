package com.portal;

import com.portal.dao.BlogMapping;
import com.portal.pojo.Dto.BlogDto;
import com.portal.pojo.Entity.Blog;
import com.portal.pojo.Vo.BlogVo;
import com.portal.service.BlogService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class BlogServiceTests {


 @Autowired
    BlogService blogService;

 @Autowired
    BlogMapping blogMapping;

    @Test
    public void saveBlog(){
        BlogDto bd = new BlogDto();
        bd.setTitle("123");
        bd.setContent("123");
        int ans =blogService.saveBlog(bd);
        System.out.println(ans);
    }

    @Test
    public void getblogs (){
        List<Blog> list = blogMapping.selectList(null);
        list.forEach(blog -> System.out.println(blog.getTitle()));
    }
    @Test
    public void getblogbyid(){
        BlogVo blogVo=blogService.getBlogById(46);
        System.out.println(blogVo);
    }


}
