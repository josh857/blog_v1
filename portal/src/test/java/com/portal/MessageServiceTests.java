package com.portal;

import com.portal.pojo.Dto.MessageDto;
import com.portal.pojo.Vo.CommentVo;
import com.portal.pojo.Vo.MessageVo;
import com.portal.service.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MessageServiceTests {

    @Autowired
    MessageService messageService;

    @Test
    public void insert(){
        MessageDto dto= new MessageDto();
        dto.setName("小名");
        dto.setContent("總是學不會");
        dto.setTitle("林俊傑的歌");
        String message=messageService.insertMessage(dto);
        System.out.println(message);
    }



}
