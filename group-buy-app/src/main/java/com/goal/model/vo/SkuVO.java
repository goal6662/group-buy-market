package com.goal.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SkuVO {

    private String goodsId;

    private String goodsName;

    /**
     * 原始价格
     */
    private BigDecimal originalPrice;

}
