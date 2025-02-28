package com.goal.common;


import lombok.Getter;

@Getter
public class BizException extends RuntimeException {

    private final ResponseCode code;

    public BizException(ResponseCode code) {
        super(code.getMessage());
        this.code = code;
    }

}
