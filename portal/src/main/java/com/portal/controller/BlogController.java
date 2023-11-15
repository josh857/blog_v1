package com.portal.controller;


import com.github.pagehelper.PageInfo;
import com.portal.exception.ServiceException;
import com.portal.pojo.Dto.BlogDto;
import com.portal.pojo.Entity.Blog;
import com.portal.pojo.Vo.BlogVo;
import com.portal.service.BlogService;
import com.portal.webResult.JsonResult;
import com.portal.webResult.ServiceCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/Blog")
@Tag(name="部落格處理介面",description = "部落格處理介面")
public class BlogController {

    @Autowired
    BlogService blogService;

    /**
     * 存取blog
     * @param blog
     * @return
     */
    @Operation(summary = "新增部落格方法",description = "新增部落格api")
    @PostMapping(value = "/save",produces = {MediaType.APPLICATION_JSON_VALUE})
    public JsonResult<String> saveBlog(@RequestBody @Validated @Parameter(description = "前端資訊") BlogDto blog,
                                       BindingResult result){
        //BindingResult validation格式驗證 回傳對象 hasErrors 錯誤判斷
        if(result.hasErrors()){
           String error = result.getFieldError().getDefaultMessage();
           return JsonResult.ok(error);
        }
        try {
            //儲存blogDto
            int ans =blogService.saveBlog(blog);
            if(ans<1){
                throw new ServiceException(ServiceCode.NO_CONTENT.getValue(), "存取數據失敗");
            }
            return JsonResult.ok("儲存成功");
        }catch (ServiceException e){
             e.setMessage("數據問題,請連絡相關人員");
             e.setServiceCode(ServiceCode.NO_CONTENT.getValue());
             throw e;
        }
    }

    /**
     * 獲取部落格列表
     * @return 回傳部落格列表
     */
    @Operation(summary = "獲取所有部落格列表",description = "獲取部落格列表api")
    @GetMapping(value = "/getblogs/{pageNum}")
    public JsonResult<List<Blog>> getblogs( @PathVariable  Integer pageNum){
        if(pageNum==null){
                pageNum=1;
            }
            Integer pageSize=2;
            PageInfo<BlogVo> pageInfo =blogService.getBlogs(pageNum,pageSize);

            if(pageInfo.getList()==null) {
                throw new ServiceException(ServiceCode.ACCEPTED.getValue(), "連接成功但無任何資料");
            }
            return JsonResult.ok(pageInfo);
    }

    /**
     * 根據id 獲取單個部落格訊息
     * @param id
     * @return
     */
    @GetMapping(value = "/getblog/{id}")
    public JsonResult<BlogVo> getBlogById(@PathVariable Integer id){
        BlogVo blogVo=new BlogVo();
        if(id==null){
            throw new ServiceException(ServiceCode.NOTFOUND.getValue(), "請給予id值");
        }
            blogVo= blogService.getBlogById(id);
            if(blogVo==null){
                throw new ServiceException(ServiceCode.NOTFOUND.getValue(), "找不到對應文章id資料");
            }

        return JsonResult.ok(blogVo);
    }

    @DeleteMapping("/delete/{id}")
    public JsonResult<String> deleteById(@PathVariable Integer id){
        if(id==null){
            throw new ServiceException(ServiceCode.NOTFOUND.getValue(), "找不到對應的id值");
        }
        String message = blogService.deleteById(id);
        return JsonResult.ok(message);
    }

}
