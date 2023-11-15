package com.portal.pojo.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

/**
 * @author josh2
 * 留言儲存參數
 */
@Data
@Validated
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "前端參數傳入人名")
    @NotBlank(message = "名字不得為空")
    private String name;

    @NotBlank(message = "標題不得為空")
    @Schema(description = "前端參數傳入標題")
    private String title;

    @NotBlank(message="內容不得為空")
    @Schema(description = "前端參數傳入內容")
    private String content;

}
