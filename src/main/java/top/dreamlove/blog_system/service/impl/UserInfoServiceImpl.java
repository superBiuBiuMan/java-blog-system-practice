package top.dreamlove.blog_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.dreamlove.blog_system.bean.UserInfo;
import top.dreamlove.blog_system.mapper.UserInfoMapper;
import top.dreamlove.blog_system.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    // 用户登录
    public UserInfo selectByUserName(String username){
        return userInfoMapper.selectByUserName(username);
    }
}
