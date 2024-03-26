package top.dreamlove.blog_system.service;

import top.dreamlove.blog_system.bean.UserInfo;

public interface UserInfoService {
    //根据用户名查询用户信息(登录)
   UserInfo selectByUserName(String username);
}
