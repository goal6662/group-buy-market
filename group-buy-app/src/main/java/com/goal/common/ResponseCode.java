package com.goal.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {

    OPERATE_SUCCESS("10200", "操作成功"),

    PARAM_ILLEGAL("10300", "参数不合法"),

    OPERATE_FAIL("10500", "操作失败")
    ;

    private final String code;
    private final String message;

}
