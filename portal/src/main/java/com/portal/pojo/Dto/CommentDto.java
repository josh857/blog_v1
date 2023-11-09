package com.portal.pojo.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


import java.io.Serializable;

/**
 * @author josh2
 * 留言區討論儲存參數
 */
@Data
public class CommentDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "message_id不得為空")
    private String messageId;

    @NotBlank(message="name 不得為空")
    private String name;

    @NotBlank(message="content 不得為空")
    private String content;


}
