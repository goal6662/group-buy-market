package com.goal.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SCSkuActivityVO {

    /**
     * 营销活动 ID
     */
    private Long activityId;

    /**
     * 渠道
     */
    private String source;

    /**
     * 来源
     */
    private String chanel;

    /**
     * 商品ID
     */
    private String goodsId;

}
