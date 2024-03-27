package top.dreamlove.blog_system.utils;


/**
 * @author
 */

import cn.hutool.core.date.DateUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.JWTValidator;
import top.dreamlove.blog_system.bean.UserInfo;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * jwt工具类
 */
public class JwtUtil {

    // 生成秘钥
    private static final byte[] key = ">S?~hFCiHX9&]x)O(mBnz@4tpsf8,Lqe-\"!'|^:K3YEa;duI{50`%M*oGATPV.c+".getBytes();
    // 有效期7天
    // static final long defaultExpire = 1000 * 60 * 60 * 24 * 7L; //毫秒开始乘
    static final long defaultExpire = 1000 * 10L; //毫秒开始乘(设置20秒)

    /**
     * 验证token是否合理,是否过期,并返回对应数据
     * @param token
     * @return
     */
    public static UserInfo validate(String token){
        if(token == null) return null;
        try{
            JWTValidator.of(token).validateDate(DateUtil.date());
            if(isVarify(token)){
                return JwtUtil.resolveToken(token);
            }else{
                return null;
            }
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 验证token通过校验
     * @param token 待验证token
     * @return 验证结果
     */
    public static Boolean isVarify(String token){
        if(token == null ) return false;
        return JWT.of(token).setKey(key).verify();
    }

    /**
     * 使用默认过期时间（7天），生成一个JWT
     * @param userName 用户名
     * @return token
     */
    public static String createToken(Integer id,String userName )   {
        return JWT.create()
                // 设置加密算法(头)
                .setHeader("alg", "HS256")
                .setHeader("type", "JWT")
                //签发日期
                .setIssuedAt(new Date())
                //过期时间
                .setExpiresAt(new Date(System.currentTimeMillis() + defaultExpire))
                .setJWTId(UUID.randomUUID().toString()) //JWT编号
                //密钥
                .setKey(JwtUtil.key)
                //主题
                .setSubject("auth")
                //自定义信息
                .setPayload("userName",userName)
                .setPayload("id",id)
                //生成token
                .sign();
    }

    /**
     * token 校验，并获取 userInfo(id和用户名)
     * @param token token
     * @return userDto
     */
    public static UserInfo resolveToken(String token){
        if(!isVarify(token)) return null;
        JWT jwt = JWT.of(token);
        Integer id = Integer.parseInt(jwt.getPayload("id").toString());
        String userName = jwt.getPayload("userName").toString();
        return new UserInfo(id,userName);
    }


}
