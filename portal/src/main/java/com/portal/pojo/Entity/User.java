package com.portal.pojo.Entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@TableName("user")
@Schema(description = "用戶資訊")
public class User {

    @Schema(name="用戶id",description = "用戶id")
    @TableField(value = "id")
    @TableId
    private Integer id;

    @Schema(name="用戶帳戶", description = "用戶帳戶")
    @TableField(value = "username")
    private String username;

    @Schema(name="用戶密碼" ,description = "用戶密碼")
    @TableField(value="password")
    private String password;

    @Schema(name="用戶綽號",description = "用戶使用小名")
    @TableField(value = "nickname")
    private String nickname;

    @Schema(name="用戶權限",description = "用戶權限")
    @TableField(value = "ROLE")
    private String ROLE;

    @Schema(name="用戶創建時間",description = "用戶創建時間")
    @TableField(value = "createdatetime")
    private String createdatetime;

    @Schema(name="用戶更新時間",description = "用戶更新時間")
    @TableField(value = "updatetime")
    private String updatetime;
}
