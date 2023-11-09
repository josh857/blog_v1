package com.portal.controller;



import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequestMapping("")
public class SysController {


   @RequestMapping("/index.html")
    void index(HttpServletResponse response) throws IOException {
    response.sendRedirect("http://localhost:5173/index/");
}

    @RequestMapping("/login.html")
    void login(HttpServletResponse response) throws IOException {
        response.sendRedirect("http://localhost:5173/");
    }


}
