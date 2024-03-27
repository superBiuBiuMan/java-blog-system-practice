package top.dreamlove.blog_system.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import top.dreamlove.blog_system.bean.UserInfo;
import top.dreamlove.blog_system.utils.GlobalException;
import top.dreamlove.blog_system.utils.JwtUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
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
                    //校验cookie合法性
                    UserInfo userInfo = JwtUtil.validate(cookie.getValue());
                    return userInfo != null;
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
