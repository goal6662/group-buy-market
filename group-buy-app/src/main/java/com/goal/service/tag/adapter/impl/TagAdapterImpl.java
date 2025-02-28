package com.goal.service.tag.adapter.impl;

import com.goal.mapper.CrowdTagsDetailMapper;
import com.goal.mapper.CrowdTagsJobMapper;
import com.goal.mapper.CrowdTagsMapper;
import com.goal.model.CrowdTags;
import com.goal.model.CrowdTagsDetail;
import com.goal.model.CrowdTagsJob;
import com.goal.model.entity.CrowdTagsJobEntity;
import com.goal.service.repository.IRedisService;
import com.goal.service.tag.adapter.ITagAdapter;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBitSet;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class TagAdapterImpl implements ITagAdapter {

    private final CrowdTagsMapper crowdTagsMapper;

    private final CrowdTagsJobMapper crowdTagsJobMapper;

    private final CrowdTagsDetailMapper crowdTagsDetailMapper;

    private final IRedisService redisService;

    @Override
    public CrowdTagsJobEntity queryCrowdTagsJobEntity(String tagId, String batchId) {

        CrowdTagsJob crowdTagsJobReq = CrowdTagsJob.builder()
                .tagId(tagId)
                .batchId(batchId)
                .build();

        CrowdTagsJob crowdTagsJobRes = crowdTagsJobMapper.queryCrowdTagsJob(crowdTagsJobReq);
        if (null == crowdTagsJobRes) {
            return null;
        }

        return CrowdTagsJobEntity.builder()
                .tagType(crowdTagsJobRes.getTagType())
                .tagRule(crowdTagsJobRes.getTagRule())
                .statStartTime(crowdTagsJobRes.getStatStartTime())
                .statEndTime(crowdTagsJobRes.getStatEndTime())
                .build();
    }

    @Override
    public void addCrowdTagsUserId(String tagId, String userId) {

        CrowdTagsDetail crowdTagsDetail = CrowdTagsDetail.builder()
                .tagId(tagId)
                .userId(userId)
                .build();

        try {
            crowdTagsDetailMapper.addCrowdTagsUserId(crowdTagsDetail);

            RBitSet bitSet = redisService.getBitSet(tagId);
            bitSet.set(getIndexFromUserId(userId));
        } catch (DuplicateKeyException ignored) {
        }
    }

    @Override
    public void updateCrowdTagsStatistics(String tagId, int count) {
        CrowdTags crowdTagsReq = CrowdTags.builder()
                .tagId(tagId)
                .statistics(count)
                .build();

        crowdTagsMapper.updateCrowdTagsStatistics(crowdTagsReq);
    }

    @Override
    public long getIndexFromUserId(String userId) {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(userId.getBytes(StandardCharsets.UTF_8));
            // 将哈希字节数组转换为正整数
            BigInteger bigInt = new BigInteger(1, hashBytes);
            // 取模以确保索引在合理范围内
            return bigInt.mod(BigInteger.valueOf(Integer.MAX_VALUE)).longValue();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not found", e);
        }

    }

}
