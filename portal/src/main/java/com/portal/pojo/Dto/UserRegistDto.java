package com.portal.pojo.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
      @NotBlank(message="密碼不為空")
      @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$",message = "密碼格式不正確")
      private String password;
      @NotBlank(message="email 不為空")
      @Pattern(regexp = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$",message = "email格式不正確")
      private String email;
      @NotBlank(message = "綽號不為空")
      private String nickname;
}
