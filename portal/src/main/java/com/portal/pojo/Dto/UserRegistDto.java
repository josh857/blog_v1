package com.portal.pojo.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name="user註冊參數",description = "user註冊參數")
public class UserRegistDto {
    private String password;
    private String email;
    private String nickname;
}
