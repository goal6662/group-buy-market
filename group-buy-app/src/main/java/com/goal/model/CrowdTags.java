package com.goal.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 人群标签
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrowdTags implements Serializable {

    /**
     * 自增ID
     */
    private Integer id;
    /**
     * 人群ID
     */
    private String tagId;
    /**
     * 人群名称
     */
    private String tagName;
    /**
     * 人群描述
     */
    private String tagDesc;
    /**
     * 人群标签统计量
     */
    private Integer statistics;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

}
