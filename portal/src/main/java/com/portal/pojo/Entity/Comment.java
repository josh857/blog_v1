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
@TableName("comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("id")
    @TableId(value = "id" ,type = IdType.AUTO)
    @Schema(description = "留言討論id" ,name = "留言討論id")
    private Integer id;

    @Schema(description = "留言人名")
    @TableField("name")
    private String name;

    @Schema(description = "留言內容")
    @TableField("content")
    private String content;

    @Schema(description = "留言新增時間")
    @TableField("createtime")
    private String  createtime;

    @Schema(description = "留言更新時間")
    @TableField("updatetime")
    private String  updatetime;


}
