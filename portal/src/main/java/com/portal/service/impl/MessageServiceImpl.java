package com.portal.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.portal.dao.CommentMapping;
import com.portal.dao.MessageMapping;
import com.portal.exception.ServiceException;

import com.portal.pageUtils.Entitytrans4VoList;
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
        //格式化當前日期
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String date=sdf.format(new Date());
        //創建對象
        Message message = new Message(1,mdt.getName(),mdt.getContent(),mdt.getTitle(),date,date);
        //持久層儲存
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
        List<MessageVo> vo= Entitytrans4VoList.MessageTransfertoVo(messages);
        //轉換pageinfo 裡內容
        PageInfo<MessageVo> pagevoInfo=PageInfo2Vo.PageUtilmessage(messagePageInfo);
        //設置轉換vo當前的list
        pagevoInfo.setList(vo);
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
        //PageInfo component
        PageInfo<Comment> pageInfo = new PageInfo<>(comments);
        //轉換comment 為 vo 放入list
        List<CommentVo> commentvos= Entitytrans4VoList.CommenttransfertoVo(comments);
        //分頁管理轉成vo 及  volist
        PageInfo<CommentVo> voPageInfo=PageInfo2Vo.PageUtilComment(pageInfo);
        voPageInfo.setList(commentvos);
        return new MessageVo(message.getId()
                           ,message.getUsername()
                           ,message.getTitle()
                           ,message.getContent()
                           ,message.getCreatetime()
                           ,voPageInfo);
    }




}
