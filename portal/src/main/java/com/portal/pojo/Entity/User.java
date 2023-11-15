package com.portal.pojo.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.portal.pojo.Dto.Role;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
@Schema(description = "用戶資訊")
public class User  implements UserDetails {

    @Schema(name="用戶id",description = "用戶id")
    @TableField(value = "id")
    @TableId(value = "id",type= IdType.AUTO)
    private Integer id;



    @Schema(name="用戶密碼" ,description = "用戶密碼")
    @TableField(value="password")
    private String password;

    @Schema(name="用戶綽號",description = "用戶使用小名")
    @TableField(value = "nickname")
    private String nickname;

    @Schema(name="用戶權限",description = "用戶權限")
    @TableField(value = "ROLE")
    private Role role;

    @Schema(name="電郵" , description = "電郵")
    @TableField(value="email")
    private String email;

    @Schema(name="用戶創建時間",description = "用戶創建時間")
    @TableField(value = "createdatetime")
    private String createdatetime;

    @Schema(name="用戶更新時間",description = "用戶更新時間")
    @TableField(value = "updatetime")
    private String updatetime;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
