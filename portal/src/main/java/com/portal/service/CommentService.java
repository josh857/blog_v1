package com.portal.service;

import com.portal.pojo.Dto.CommentDto;


public interface CommentService {

    /**
     * 儲存Comment資訊方法
     * @param cd
     * @return 返回字串成功或失敗訊息
     */
    String save(CommentDto cd);

}
