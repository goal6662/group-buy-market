package com.goal.service.tag.adapter;

import com.goal.model.entity.CrowdTagsJobEntity;

public interface ITagAdapter {

    CrowdTagsJobEntity queryCrowdTagsJobEntity(String tagId, String batchId);

    void addCrowdTagsUserId(String tagId, String userId);

    void updateCrowdTagsStatistics(String tagId, int count);

    /**
     * 获取 bitSet 的下标位置
     */
    long getIndexFromUserId(String userId);

}
