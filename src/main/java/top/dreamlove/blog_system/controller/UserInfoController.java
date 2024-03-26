package top.dreamlove.blog_system.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.dreamlove.blog_system.bean.UserInfo;
import top.dreamlove.blog_system.service.UserInfoService;

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

    @PostMapping("/test")
    public String createDemo( @Valid @RequestBody UserInfo abc, BindingResult result){
        if(result.hasErrors())
            return result.getFieldError().getDefaultMessage();
        return "success";
    }

    @GetMapping("/test2")
    public String getUserStr(
            @RequestParam("username") @NotEmpty(message = "名字不能为空") String name
    ) {

        return "success" + name;
    }
}
