package com.portal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.portal.pojo.Entity.Blog;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author josh2
 * blog 表 mapping 實體類
 */
@Repository
public interface BlogMapping extends BaseMapper<Blog> {

    @Select("SELECT * FROM BLOG ORDER BY createdatetime DESC")
    public List<Blog> getBlogs();


}
