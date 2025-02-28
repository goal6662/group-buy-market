package com.goal.test.dao;

import com.alibaba.fastjson2.JSON;
import com.goal.model.GroupBuyActivity;
import com.goal.mapper.GroupBuyActivityMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class GroupBuyActivityTest {


    @Resource
    private GroupBuyActivityMapper groupBuyActivityMapper;

    @Test
    public void queryGroupBuyActivity() {

        List<GroupBuyActivity> groupBuyActivityList = groupBuyActivityMapper.queryList();
        log.info("groupBuyActivityList: {}", JSON.toJSONString(groupBuyActivityList));

    }

}
