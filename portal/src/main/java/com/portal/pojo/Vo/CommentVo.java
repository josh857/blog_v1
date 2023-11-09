package com.portal.pojo.Vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
public class CommentVo implements Serializable {

    @Schema(description = "討論id")
    private Integer id ;

    @Schema(description = "討論人名")
    private String name;

    @Schema(description = "討論內容")
    private String content;

    @Schema(description = "討論區更新時間")
    private String createdate;
}
