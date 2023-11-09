package com.portal.pojo.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * @author josh2
 * @Date 2023/11/03
 */

@Data
@TableName(value = "message")
@Schema(description = "留言區留言",name="message")
public class Message  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "留言ID")
    @TableId(value = "id",type= IdType.AUTO)
    @TableField("id")
    private Integer id;

    @Schema(description = "留言標題")
    @TableField("title")
    private String title;

    @Schema(description = "留言人名")
    @TableField("username")
    private String username;

    @Schema(description = "留言內容")
    @TableField("content")
    private String content;

    @Schema(description = "留言新增時間")
    @TableField("createtime")
    private String  createtime;

    @Schema(description = "留言更新時間")
    @TableField("updatetime")
    private String updatetime;
}
