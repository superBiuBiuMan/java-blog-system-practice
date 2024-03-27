package top.dreamlove.blog_system;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.exceptions.ValidateException;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTException;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.JWTValidator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.dreamlove.blog_system.bean.ArticleInfo;
import top.dreamlove.blog_system.bean.UserInfo;
import top.dreamlove.blog_system.mapper.ArticleInfoMapper;
import top.dreamlove.blog_system.mapper.UserInfoMapper;
import top.dreamlove.blog_system.utils.JwtUtil;

import java.util.List;

@SpringBootTest
@Slf4j
class BlogSystemApplicationTests {
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    ArticleInfoMapper articleInfoMapper;
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

    @Test
    //测试分页
    void testPage(){
        PageHelper.startPage(1,10);
        List<ArticleInfo> articleInfos = articleInfoMapper.articleList();
        PageInfo<ArticleInfo> articleInfoPageInfo = new PageInfo<>(articleInfos);
        for (ArticleInfo articleInfo : articleInfos) {
            log.info(articleInfo.toString());
        }
    }

    @Test
    void testJWT(){
        //测试jwt是否有效期内
        // JwtUtil.validate()
        // String token = JwtUtil.createToken(123, "qiuye");
        // log.info(token);
    }
    @Test
    void testJWT1(){
        // String token = "eyJhbGciOiJIUzI1NiIsInR5cGUiOiJKV1QiLCJ0eXAiOiJKV1QifQ.eyJpYXQiOjE3MTE1Mjg2NjYsImV4cCI6MTcxMTUyODY3NiwianRpIjoiM2RkN2IzNmUtNjQxMS00OGEzLTkwMDMtZDVhZGVlZWQ1OTBhIiwic3ViIjoiYXV0aCIsInVzZXJOYW1lIjoicWl1eWUiLCJpZCI6MTIzfQ.lPbcvtgbI4VEhQZrkhmws-zmZLXlQrysRlAmFnNsVeU";
        String token = "eyJhbGciOiJIUzI1NiIsInR5cGUiOiJKV1QiLCJ0eXAiOiJKV1QifQ.eyJpYXQiOjE3MTE1MDI2NjQsImV4cCI6MTcxMjEwNzQ2NCwianRpIjoiYTM3NzExYzMtNzg5ZC00MzVmLTlhMDEtOTFkOWEwYzJhMzM1Iiwic3ViIjoiYXV0aCIsInVzZXJOYW1lIjoiMSIsImlkIjoxfQ.G-krZVa4uBJC7e1YJf_8zhNyPQjUOKi4jL18960q4GY";
        UserInfo validate = JwtUtil.validate(token);
        log.info("是否有信息{}",validate);
    }
}
