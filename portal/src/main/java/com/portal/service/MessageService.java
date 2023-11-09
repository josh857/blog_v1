package com.portal.service;


import com.github.pagehelper.PageInfo;
import com.portal.pojo.Dto.MessageDto;
import com.portal.pojo.Vo.MessageVo;


public interface MessageService {
    /**
     * 儲存留言訊息
     */
    String insertMessage(MessageDto bd);

    /**
     * 獲得message 分頁管理list
     * @param pageNum
     * @param pagesize
     * @return
     */
   PageInfo<MessageVo> getMessages(Integer pageNum, Integer pagesize);

    /**
     * 根據 id  獲取message訊息 並將屬性 comments list  分業管理
     * @param id
     * @param pagenum 分頁管理 頁數
     * @param pagesize 分頁管理 項目數
     * @return MessageVo對象
     */
   MessageVo getMessage(Integer id ,Integer pagenum,Integer pagesize);

}
