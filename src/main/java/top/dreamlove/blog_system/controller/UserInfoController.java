package top.dreamlove.blog_system.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.dreamlove.blog_system.bean.UserInfo;
import top.dreamlove.blog_system.service.UserInfoService;
import top.dreamlove.blog_system.utils.GlobalException;
import top.dreamlove.blog_system.utils.JwtUtil;
import top.dreamlove.blog_system.utils.Result;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@Validated

public class UserInfoController {
    @Autowired
    UserInfoService userInfoService;

    @PostMapping("/login")
    public Result login(
            @RequestParam(value = "username",required = false) @NotNull(message = "请输入用户名") String userName,
            @RequestParam(value = "password",required = false) @NotNull(message = "请输入密码") String password,
            HttpServletResponse response
    ) {
        UserInfo userInfo = userInfoService.selectByUserName(userName);
        if(userInfo != null){
            if (userInfo.getPassword().equals(password)){
                //登录成功
                //生成jwt
                String token = JwtUtil.createToken(userName);
                //创建Cookie
                Cookie cookie  = new Cookie("token",token);
                //设置Cookie有效期
                cookie.setMaxAge(1 * 60 * 60 * 24 * 7);//七天
                response.addCookie(cookie);
                return Result.ok().message("登录成功");
            }else{
                //登录失败
                return Result.error().message("请检查输入的密码是否正确!");
            }
        }
        return Result.error().message("用户信息不存在");
    }
}
