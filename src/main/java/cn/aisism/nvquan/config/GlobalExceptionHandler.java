package cn.aisism.nvquan.config;

import cn.cutenine.talvapp.pojo.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author KasonZzz
 * description:统一异常处理信息
 * @date 2020-07-13
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static BaseResult COMMON_RESULT;

    static {
        COMMON_RESULT = new BaseResult(500);
    }
    /**
     * 处理所有不可知的异常
     */
    @ExceptionHandler(Exception.class)
    public BaseResult handleException(Exception e) {
        log.error("系统错误信息:",e);
        return COMMON_RESULT;
    }





}
