package top.dreamlove.blog_system;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.dreamlove.blog_system.bean.UserInfo;
import top.dreamlove.blog_system.mapper.UserInfoMapper;

import java.util.List;

@SpringBootTest
@Slf4j
class BlogSystemApplicationTests {
    @Autowired
    UserInfoMapper userInfoMapper;
    @Test
    void contextLoads() {
    }

    @Test
    void mybatis(){
        // List<UserInfo> userInfos = userInfoMapper.selectList(null);
        // userInfos.forEach(System.out::println);
    }

    @Test
    void testInsert(){
        UserInfo userInfo = new UserInfo();
        userInfo.setPassword("456");
        userInfo.setUsername("东高好人");
        userInfoMapper.insertUserInfo(userInfo);
        log.info(userInfo.toString());
    }
}
