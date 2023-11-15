package com.portal.pojo.Vo;

import com.portal.pojo.Dto.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVo implements Serializable {
    private String nickname;
    private Role role;

}
