package top.dreamlove.blog_system.common;


import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.dreamlove.blog_system.utils.GlobalException;
import top.dreamlove.blog_system.utils.Result;

/**
 * 统一异常处理
 */
@RestControllerAdvice
public class ExceptionAdvice {
    // private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 处理 Exception 异常
     * @param e 异常
     * @return 处理结果
     */
    @ExceptionHandler(Exception.class)
    public Result handlerException(Exception e) {
        // logger.error((e.getMessage(), e);////日志记录
        return Result.error().message(e.toString());
    }

    /**
     * 处理空指针异常
     * @param e 异常
     * @return 处理结果
     */
    @ExceptionHandler(NullPointerException.class)
    public Result handlerNullPointerException(NullPointerException e) {
        // logger.error(e.getMessage(), e);
        return Result.error().message(e + "空指针异常");
    }

    /**
     * 处理自定义异常
     * @param e 异常
     * @return 处理结果
     */
    @ExceptionHandler(GlobalException.class)
    public Result handlerGlobalException(GlobalException e) {
        // logger.error(e.getMessage(), e);
        return Result.error().message(e.getMessage()).code(e.getCode());
    }

}
