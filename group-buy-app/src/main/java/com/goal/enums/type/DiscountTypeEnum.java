package com.goal.enums.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DiscountTypeEnum {

    BASE(0, "基础优惠"),
    TAG(1, "人群优惠"),
    ;

    private final Integer type;
    private final String desc;

    public static DiscountTypeEnum getDiscountTypeEnum(Integer type) {
        return switch (type) {
            case 0 -> BASE;
            case 1 -> TAG;
            default -> throw new RuntimeException("不存在的折扣类型");
        };
    }

}
