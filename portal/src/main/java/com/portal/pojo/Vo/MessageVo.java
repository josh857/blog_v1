package com.portal.pojo.Vo;

import com.github.pagehelper.PageInfo;
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
public class MessageVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "傳入後端id")
    private Integer id;

    @Schema(description = "傳入後端人名")
    private String name;

    @Schema(description = "傳入後端標題")
    private String title;

    @Schema(description = "傳入後端內容")
    private String content;

    @Schema(description = "創建資料時間")
    private String  createdate;

    @Schema(description = "討論留言分頁")
    private PageInfo<CommentVo> pageinfo;


}
