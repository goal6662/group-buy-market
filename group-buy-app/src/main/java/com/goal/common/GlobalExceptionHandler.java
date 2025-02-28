package com.goal.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public Result<?> runtimeException(RuntimeException e) {
        log.error(e.getMessage(), e);
        return Result.fail();
    }

    @ExceptionHandler(BizException.class)
    public Result<?> bizException(BizException e) {
        log.error("BizException: {}", e.getCode().getMessage(), e);
        return Result.fail(e.getCode());
    }

}
