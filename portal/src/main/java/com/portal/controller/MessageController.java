package com.portal.controller;

import com.github.pagehelper.PageInfo;
import com.portal.exception.ServiceException;
import com.portal.pojo.Dto.MessageDto;
import com.portal.pojo.Vo.MessageVo;
import com.portal.service.MessageService;
import com.portal.webResult.JsonResult;
import com.portal.webResult.ServiceCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author josh2
 * @Date 2023/11/03
 */

@CrossOrigin
@RestController
@RequestMapping("/v1/Message")
public class MessageController {

    @Autowired
    MessageService messageService;

    /**
     * 儲存留言方法
     * @param messageDto
     * @return
     */
    @PostMapping("/insert")
    JsonResult<String> insertMessage(@Validated @RequestBody MessageDto messageDto,
                                     BindingResult result ){
        if(result.hasErrors()){
            String error = result.getFieldError().getDefaultMessage();
           return JsonResult.ok(error);
        }
        if(messageDto == null){
            throw new ServiceException(ServiceCode.OK.getValue(), "找不到參數");
        }

        String message = messageService.insertMessage(messageDto);
        return JsonResult.ok(message);
    }

    /**
     *獲取所有留言並分頁返回
     * @param pagenum
     * @return
     */
    @GetMapping("/getlist/{pagenum}")
    JsonResult<List<MessageVo>> getlist(@PathVariable Integer pagenum){
        if(pagenum==null){
            pagenum=1;
        }
        int pagesize=3;
        PageInfo<MessageVo> voPageInfo =messageService.getMessages(pagenum,pagesize);

        if(voPageInfo==null){
            throw new ServiceException(ServiceCode.OK.getValue(), "數據出錯請稍後再試");
        }
        if(voPageInfo.getList()==null){
            throw new ServiceException(ServiceCode.OK.getValue(), "連接成功但找不到數據");
        }
        return JsonResult.ok(voPageInfo);
    }

    /**
     * 根據id 獲取留言區資料並獲取留言區下 討論區資料list
     * @param id
     * @return
     */
    @GetMapping("/getcomment/{id}/{pagenum}")
    JsonResult<MessageVo> getmessageById(@PathVariable Integer id ,@PathVariable Integer pagenum){
        if(id==null){
            throw new ServiceException("找不到留言id");
        }
        Integer pagesize= 4;
        MessageVo messageVo=messageService.getMessage(id,pagenum,pagesize);
        if(messageVo == null){
            throw new ServiceException("找不到資料,稍後在試");
        }
        return JsonResult.ok(messageVo);
    }

}
