package com.portal.controller;

import com.portal.exception.ServiceException;
import com.portal.pojo.Dto.CommentDto;
import com.portal.service.CommentService;
import com.portal.webResult.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/v1/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    /**
     * 儲存討論區資訊
     * @param cd
     * @return
     */
    @PostMapping("/insert")
    JsonResult<String> save(@RequestBody @Validated CommentDto cd
                            , BindingResult result){
            if(result.hasErrors()){
                String error = result.getFieldError().getDefaultMessage();
                return JsonResult.ok(error);
            }
            String message = commentService.save(cd);
            return JsonResult.ok(message);
    }
}
