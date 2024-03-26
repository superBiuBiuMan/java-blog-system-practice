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
import top.dreamlove.blog_system.utils.Result;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@RestController
@Slf4j
@Validated

public class UserInfoController {
    @Autowired
    UserInfoService userInfoService;

    @PostMapping("/login")
    // @RequestParam("username") @Valid String username, @RequestParam("password") String password, BindingResult result
    public UserInfo login(@RequestBody @Valid String username,BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            String defaultMessage = bindingResult.getFieldError().getDefaultMessage();
            System.out.println("defaultMessage = " + defaultMessage);
        }
        return new UserInfo();
    }
}
