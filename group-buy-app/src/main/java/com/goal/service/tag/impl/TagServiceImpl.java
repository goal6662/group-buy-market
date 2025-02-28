package com.goal.service.tag.impl;

import com.goal.model.entity.CrowdTagsJobEntity;
import com.goal.service.tag.ITagService;
import com.goal.service.tag.adapter.ITagAdapter;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TagServiceImpl implements ITagService {

    @Resource
    private ITagAdapter tagAdapter;

    @Override
    public void executeTagBatchJob(String tagId, String batchId) {

        log.info("人群标签批次任务 tagId:{} batchId:{}", tagId, batchId);

        // 1. 查询批次任务
        CrowdTagsJobEntity crowdTagsJobEntity = tagAdapter.queryCrowdTagsJobEntity(tagId, batchId);

        // 2. 采集用户数据 - 这部分需要采集用户的消费类数据，后续有用户发起拼单后再处理。

        // 3. 数据写入记录
        List<String> userIdList = new ArrayList<>() {{
            add("xiaofuge");
            add("liergou");
        }};

        // 4. 一般人群标签的处理在公司中，会有专门的数据数仓团队通过脚本方式写入到数据库，就不用这样一个个或者批次来写。
        for (String userId : userIdList) {
            tagAdapter.addCrowdTagsUserId(tagId, userId);
        }

        // 5. 更新人群标签统计量
        tagAdapter.updateCrowdTagsStatistics(tagId, userIdList.size());

    }

}
