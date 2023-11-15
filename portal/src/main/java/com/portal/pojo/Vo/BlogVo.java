package com.portal.pojo.Vo;

/**
 * @Auth josh
 * @Date 2023/11/02
 */

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogVo  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主鍵id")
    private Integer id;

    @Schema(description = "部落格標題")
    private String title;

    @Schema(description = "部落格內容")
    private String content;

    @Schema(description = "圖片base64字串")
    private String image;

    @Schema(description = "自動生成日期")
    private String createdatetime;
}
