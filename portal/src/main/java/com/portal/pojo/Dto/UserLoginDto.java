package com.portal.pojo.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Validated
@Schema(name="用戶登入",description = "用戶登入")
public class UserLoginDto {

//    @NotBlank(message="用戶名不為空")
//    @Pattern(regexp = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$",message = "email格式不正確")
    @Schema(name="email",description = "email")
    private String email;
//    @NotBlank(message = "用戶密碼不為空")
    @Schema(name="用戶密碼", description = "用戶密碼")
    private String password;
}
