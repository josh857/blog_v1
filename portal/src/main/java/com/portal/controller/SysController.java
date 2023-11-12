package com.portal.controller;



import com.portal.exception.ServiceException;
import com.portal.pojo.Dto.UserLoginDto;
import com.portal.pojo.Dto.UserRegistDto;
import com.portal.service.UserService;
import com.portal.webResult.JsonResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;


@CrossOrigin
@RestController
@RequestMapping("/v1")
@Slf4j
@Tag(name = "登入註冊用介面",description = "系統Controller")
public class SysController {

    @Autowired
    UserService userService;

    /**
     * 用戶註冊並返回 token
     * @param dto
     * @return
     */
    @PostMapping("/register")
    @ResponseBody
    JsonResult<String> register (@RequestBody UserRegistDto dto){

        if(dto==null){
            throw new ServiceException("dto無參數");
        }
        return  JsonResult.ok(userService.register(dto));
    }


    //todo 這裡傳輸有問題!!2023/11/10
    @RequestMapping ("/login/{email}/{password}")
    JsonResult<String> login (@PathVariable String email,@PathVariable String password){
        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setEmail(email);
        userLoginDto.setPassword(password);
        if(email==null || password==null){
            throw new ServiceException("dto無參數");
        }
        return  JsonResult.ok(userService.login(userLoginDto));
    }


    //重定向登入成功首頁頁面
   @RequestMapping("/index.html")
    void index(HttpServletResponse response) throws IOException {
    response.sendRedirect("http://localhost:5173/index/");
}

    //重定向登入頁面
    @RequestMapping("/login.html")
    void login(HttpServletResponse response) throws IOException {
        response.sendRedirect("http://localhost:5173/");
    }


}
