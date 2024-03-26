package top.dreamlove.blog_system.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.dreamlove.blog_system.bean.UserInfo;

// public interface UserInfoMapper extends BaseMapper<UserInfo> {
// }

@Mapper
public interface UserInfoMapper  {
    //用户注册(需要将注册成功后的用户id返回)

    //用户登录
    UserInfo selectByUserName(@Param("username") String userName);
}
