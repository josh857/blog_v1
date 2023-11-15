package com.portal.service.impl;



import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.portal.dao.BlogMapping;
import com.portal.exception.ServiceException;
import com.portal.pageUtils.Entitytrans4VoList;
import com.portal.pageUtils.PageInfo2Vo;
import com.portal.pojo.Dto.BlogDto;
import com.portal.pojo.Entity.Blog;
import com.portal.pojo.Vo.BlogVo;
import com.portal.service.BlogService;
import com.portal.webResult.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Slf4j
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapping blogMapping;

    /**
     * 存取blog實做方法
     * @param blogDto
     */
    @Transactional
    @Override
    public Integer saveBlog(BlogDto blogDto) throws ServiceException{
        Integer ans=0;
        if(blogDto==null) {
            throw new ServiceException(ServiceCode.NOTFOUND.getValue(), "找不到相關資料(blog)");
        }else {
            try {
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String d = sdf.format(date);
                Blog blog = new Blog();
                blog.setContent(blogDto.getContent());
                blog.setTitle(blogDto.getTitle());
                blog.setImage(blogDto.getImage());
                blog.setCreatedatetime(d);
                blog.setUpdatetime(d);
                //儲存部落格
                ans =blogMapping.insert(blog);
                }catch (ServiceException e) {
                        throw new ServiceException(ServiceCode.Internal_Server_Error.getValue(),"處理業務錯誤");
                }
            }
        return ans;
        }

    /**
     * 獲取所有部落格文章list
      * @return List<BlogVo> 放入Vo 返回
     */
    @Override
    public PageInfo<BlogVo> getBlogs(Integer pageNum , Integer pageSize) throws ServiceException{
            //分頁管理
            PageHelper.startPage(pageNum,pageSize);
            //取得Blog Entity 列表list
            List<Blog> blogs = blogMapping.getBlogs();
            //新增PageInfo 放入Blog list
            PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
            //新增Vo list
        try {
            List<BlogVo> vo = Entitytrans4VoList.BlogTransfertoVo(blogs);
            //轉換成 Vo 的 pageInfo 回傳
            PageInfo<BlogVo> pageVoInfo= PageInfo2Vo.PageUtil(pageInfo);
            //list 轉換 Volist 放入 pageInfo List
            pageVoInfo.setList(vo);
            return pageVoInfo;
        }catch (ServiceException e){
            throw new ServiceException(ServiceCode.NOTFOUND.getValue(),"獲取資料失敗");
        }
    }

    /**
     * 根據文章id 獲取文章資訊
     * @param id
     * @return
     */
    @Override
    public BlogVo getBlogById(Integer id) throws ServiceException{
        if(id==null){
            throw new ServiceException(ServiceCode.NOTFOUND.getValue(), "找不到文章id");
        }
        try{
            Blog blog= blogMapping.selectById(id);
            if(blog==null){
                throw new ServiceException(ServiceCode.NOTFOUND.getValue(), "找不到此文章");
            }
            BlogVo blogVo = new BlogVo(blog.getId(),blog.getTitle(),blog.getContent(),blog.getImage(),blog.getCreatedatetime());
            return blogVo;
        }catch (ServiceException e){
             e.setServiceCode(ServiceCode.NOTFOUND.getValue());
             e.setMessage("找不到此文章");
             throw e;
        }
    }

    @Transactional
    @Override
    public String  deleteById(Integer blogId) {
        int ans = blogMapping.deleteById(blogId);
        log.info("{}",ans);
        if(ans==1){
            return "儲存成功";
        }else {
            throw new ServiceException("刪除失敗");
        }

    }
}
