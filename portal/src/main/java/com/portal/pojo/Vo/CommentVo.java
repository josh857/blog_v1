package com.portal.pojo.Vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
