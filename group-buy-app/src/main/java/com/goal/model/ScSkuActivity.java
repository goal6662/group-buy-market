package com.goal.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScSkuActivity implements Serializable {

    /**
     * 自增ID
     */
    private Integer id;
    /**
     * 渠道
     */
    private String source;
    /**
     * 来源
     */
    private String channel;
    /**
     * 活动ID
     */
    private Long activityId;
    /**
     * 商品ID
     */
    private String goodsId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}
