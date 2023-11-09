package com.portal.service;

import com.github.pagehelper.PageInfo;
import com.portal.pojo.Dto.BlogDto;
import com.portal.pojo.Entity.Blog;
import com.portal.pojo.Vo.BlogVo;

/**
 * @author josh2
 */

public interface BlogService {

    /**
     * 存取Blog
     * @param blogDto
     */
    Integer saveBlog(BlogDto blogDto);

    /**
     * 獲取blog 清單
     * @return
     */
 PageInfo<BlogVo> getBlogs(Integer pageNum , Integer pagSize);

    /**
     * 根據文章id 獲取blog data
     * @Param  id  文章id
     * @return
     */
    BlogVo getBlogById(Integer  id);

    String  deleteById(Integer blogId);
}
