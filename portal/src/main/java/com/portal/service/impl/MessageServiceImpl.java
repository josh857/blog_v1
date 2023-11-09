package com.portal.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.portal.dao.CommentMapping;
import com.portal.dao.MessageMapping;
import com.portal.exception.ServiceException;

import com.portal.pageUtils.PageInfo2Vo;
import com.portal.pojo.Dto.MessageDto;
import com.portal.pojo.Entity.Comment;
import com.portal.pojo.Entity.Message;
import com.portal.pojo.Vo.CommentVo;
import com.portal.pojo.Vo.MessageVo;
import com.portal.service.MessageService;
import com.portal.webResult.ServiceCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageMapping messageMapping;

    @Autowired
    CommentMapping commentMapping;

    /**
     * 儲存留言區留言訊息
     * @param mdt
     * @return
     */
    @Override
    @Transactional
    public String insertMessage(MessageDto mdt) {
        if(mdt==null){
            throw new ServiceException(ServiceCode.OK.getValue(), "找不到參數");
        }
        Message message = new Message();
        message.setUsername(mdt.getName());
        message.setContent(mdt.getContent());
        message.setTitle(mdt.getTitle());
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String date=sdf.format(new Date());
        message.setCreatetime(date);
        message.setUpdatetime(date);
        int row =messageMapping.insert(message);
        if(row>=1){
            return "儲存成功";
        }else{
            throw new ServiceException(ServiceCode.NO_CONTENT.getValue(), "儲存失敗請查詢問題");
        }
    }

    /**
     * 獲取messageVo 分頁傳到前端
     * @param pageNum
     * @param pagesize
     * @return
     */
    @Override
    public PageInfo<MessageVo> getMessages(Integer pageNum, Integer pagesize) {
        PageHelper.startPage(pageNum,pagesize);
        //dao層獲取message list
        List<Message>messages=messageMapping.selectList(null);
        //pageinfo message  將list 放入
        PageInfo<Message> messagePageInfo = new PageInfo<>(messages);
        //將message list 數據 放入 messageVo list中
        List<MessageVo> vo= new ArrayList<>();
        for(Message message:messages){
            MessageVo mv=new MessageVo();
            mv.setId(message.getId());
            mv.setName(message.getUsername());
            mv.setTitle(message.getTitle());
            mv.setContent(message.getContent());
            vo.add(mv);
        }

        PageInfo<MessageVo> pagevoInfo=PageInfo2Vo.PageUtilmessage(messagePageInfo);
        pagevoInfo.setList(vo);
        System.out.println(pagevoInfo.getList());
        return pagevoInfo;
    }

    /**
     * 根據id 獲取留言區資料  及  留言區討論留言並做分頁管理 轉成Vo 返回
     * @param id
     * @param pagenum
     * @param pagesize
     * @return
     */
    @Override
    public MessageVo getMessage(Integer id ,Integer pagenum,Integer pagesize) {
        Message message = new Message();
        //根據id 獲取 留言資料
        message= messageMapping.selectById(id);
        //根據留言id join message_comment 關聯表 找尋 討論list
        PageHelper.startPage(pagenum,pagesize);
        List<Comment> comments= commentMapping.getcomments(id);
        PageInfo<Comment> pageInfo = new PageInfo<>(comments);
        //轉換comment 為 vo 放入list
        List<CommentVo> commentvos= new ArrayList<>();
        for(Comment c :comments){
            CommentVo cv = new CommentVo();
            cv.setId(c.getId());
            cv.setName(c.getName());
            cv.setContent(c.getContent());
            cv.setCreatedate(c.getCreatetime());
            commentvos.add(cv);
        }
        //分頁管理轉成vo 及  volist
        PageInfo<CommentVo> voPageInfo=PageInfo2Vo.PageUtilComment(pageInfo);
        voPageInfo.setList(commentvos);

        //將留言Message 轉 vo  並將 CommentVo list 放入 MessageVo 裡的屬性 commentVo list
        MessageVo mvo = new MessageVo();
        mvo.setName(message.getUsername());
        mvo.setId(message.getId());
        mvo.setTitle(message.getTitle());
        mvo.setContent(message.getContent());
        mvo.setCreatedate(message.getCreatetime());
        mvo.setPageinfo(voPageInfo);
        return mvo;
    }


}
