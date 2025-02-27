package com.goal.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarketProductEntity {

    private String userId;

    private String goodsId;

    /**
     * 渠道
     */
    private String source;

    /**
     * 来源
     */
    private String channel;
}
