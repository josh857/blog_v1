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

@Data
@TableName("message_comment")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "討論區資訊")
public class Message_Comment implements Serializable {

    @Schema(description = "討論id")
    @TableField(value = "id")
    @TableId(value = "id",type= IdType.AUTO)
    private Integer id ;

    @Schema(description = "留言id")
    @TableField(value="message_id")
    private Integer message_id;

    @Schema(description = "討論id")
    @TableField(value="comment_id")
    private Integer comment_id;
}
