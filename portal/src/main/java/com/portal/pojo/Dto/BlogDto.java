package com.portal.pojo.Dto;

/**
 * @Auth josh
 * @Date 2023/11/02
 *
 */

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "blog新增接收包裝類")
public class BlogDto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Schema(description="title標題",example = "這是標題範例")
    @NotBlank(message = "title不得為空值")
    private String title;
    @Schema(description="content標題",example = "這是內容範例")
    @NotBlank(message = "content不得為空值")
    private String content;
    @Schema(description="image標題",example = "這是圖片範例")
    private String image;
}
