package top.dreamlove.blog_system.utils;


/**
 * @author
 */

import cn.hutool.jwt.JWT;

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
    static final long defaultExpire = 1000 * 60 * 60 * 24 * 7L;

    /**
     * 使用默认过期时间（7天），生成一个JWT
     *
     * @return
     */
    public static String createToken(String userName) {
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
                //生成token
                .sign();

    }

    /**
     * 生成token
     *
     * @param claims   请求体数据
     * @param expire   过期时间 单位：毫秒
     * @return token
     */


    /**
     * 解析token
     *
     * @param token jwt token
     * @return payload
     */
}
