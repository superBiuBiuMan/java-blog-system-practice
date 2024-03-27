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

    public UserInfo selectByUserName(String username){
        return userInfoMapper.selectByUserName(username);
    }

    @Override
    public Integer userRegister(UserInfo userInfo) {
        Integer effectRow = userInfoMapper.insertUserInfo(userInfo);
        if(effectRow < 1){
            //注册失败
            return -1;
        }else{
            //注册成功
            return userInfo.getId();
        }
    }
}
