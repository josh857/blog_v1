package com.portal.pojo.Dto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;




@Data
@Schema(name="用戶登入",description = "用戶登入")
public class UserLoginDto {

    @Schema(name="用戶名",description = "用戶名")
    private String email;
    @Schema(name="用戶密碼", description = "用戶密碼")
    private String password;
}
