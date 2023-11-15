package com.portal.pojo.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


/**
 * @author josh2
 * @Date 2023/11/02
 */
@Data
@TableName(value="blog")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "完整部落格實體包裝類")
public class Blog implements Serializable   {

    private static final long serialVersionUID = 1L;

    //主鍵id
    @Schema(description = "主鍵id")
    @TableField("id")
    @TableId(value = "id",type= IdType.AUTO)
    private Integer id;

    //部落格標題
    @TableField("title")
    @Schema(description = "部落格標題")
    private String title;

    //部落格內容
    @TableField("content")
    @Schema(description = "部落格內容")
    private String content;

    //照片轉base64存入
    @TableField("image")
    @Schema(description = "圖片base64字串")
    private String  image;

    //生成資料日期
    @TableField("createdatetime")
    @Schema(description = "自動生成日期")
    private String createdatetime;

    //更動資料日期
    @TableField("updatetime")
    @Schema(description = "更該日期 初始化與生成日期相同")
    private String updatetime;
}
