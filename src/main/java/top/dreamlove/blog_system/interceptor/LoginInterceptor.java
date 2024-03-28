package top.dreamlove.blog_system.interceptor;

import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import top.dreamlove.blog_system.bean.UserInfo;
import top.dreamlove.blog_system.utils.GlobalException;
import top.dreamlove.blog_system.utils.JwtUtil;
import top.dreamlove.blog_system.utils.RedisUtil;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    private RedisUtil redisUtil = SpringUtil.getBean(RedisUtil.class);

    /*  该方法在目标方法之前之前进行拦截调用
     * 若该方法返回true，则会继续调用后续的拦截器和目标方法
     * 若方法返回false，则会终止后续拦截器和目标方法的执行。
     * preHandle--》目标方法 --》postHandle--》afterCompletion
     *
     * 权限 日志 事务
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //获取Cookie
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length > 0){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    boolean expire = JwtUtil.isExpire(token);//验证cookie里面的token携带的信息是否过期
                    if(expire){
                        //判断redis中是否过期
                        String refreshToken = redisUtil.get("sys:user:refreshToken" + token).toString();
                        if(JwtUtil.isExpire(refreshToken)){
                            //过期
                            //生成jwt
                            String tokenTemp = JwtUtil.createToken(123456,"testUserName");
                            //创建Cookie
                            Cookie cookieTemp  = new Cookie("token",tokenTemp);
                            cookieTemp.setPath("/");
                            //设置Cookie有效期
                            cookieTemp.setMaxAge(1 * 60 * 60 * 24 * 7);//七天
                            response.addCookie(cookieTemp);
                            log.info("token过期重新派发新的",tokenTemp);
                            return true;
                        }else{
                            //未过期
                            log.info("refresh未过期");
                            return true;
                        }

                    }else{
                        //token没过期，
                        UserInfo userInfo = JwtUtil.validate(cookie.getValue());
                        log.info("token没过期");
                        return userInfo != null;
                    }
                    // //校验cookie合法性
                    // UserInfo userInfo = JwtUtil.validate(cookie.getValue());
                    // return userInfo != null;
                }
            }
        }
        throw new GlobalException("请登录后重试");
    }

    /*调用目标方法之后渲染视图之前
     * 可以对请求域中的属性或者视图做出修改
     * */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    /*渲染视图之后
     *  释放资源
     * */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
