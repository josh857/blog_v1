package com.portal.service.impl;

import com.portal.dao.CommentMapping;
import com.portal.dao.Message_CommentMapping;
import com.portal.exception.ServiceException;
import com.portal.pojo.Dto.CommentDto;
import com.portal.pojo.Entity.Comment;
import com.portal.pojo.Entity.Message_Comment;
import com.portal.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapping commentMapping;

    @Autowired
    Message_CommentMapping message_commentMapping;

    /**
     * 儲存Comment
     * @param commentdto
     * @return
     */
    @Transactional
    @Override
    public String save(CommentDto commentdto) {
        Comment comment= new Comment();
        comment.setName(commentdto.getName());
        comment.setContent(commentdto.getContent());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date=sdf.format(new Date());
        comment.setCreatetime(date);
        comment.setUpdatetime(date);
        int ans = commentMapping.insert(comment);
        //立即獲得comment_id
        Integer CommentId=comment.getId();
        log.debug("{}",CommentId);
        //新增關聯表實體類 並放入 comment_id 與 message_Id
        Message_Comment message_comment= new Message_Comment();
        message_comment.setComment_id(CommentId);
        message_comment.setMessage_id(Integer.parseInt(commentdto.getMessageId()));
        message_commentMapping.insert(message_comment);
        if(ans<1){
            throw new ServiceException("找不到資料");
        }
        return "儲存成功";
    }
}
