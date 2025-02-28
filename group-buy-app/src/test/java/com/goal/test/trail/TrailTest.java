package com.goal.test.trail;

import com.alibaba.fastjson2.JSON;
import com.goal.model.entity.MarketProductEntity;
import com.goal.model.entity.TrialBalanceEntity;
import com.goal.service.trail.IIndexGroupBuyMarketService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class TrailTest {

    @Resource
    IIndexGroupBuyMarketService indexGroupBuyMarketService;

    @Test
    public void test() throws Exception {

        MarketProductEntity marketProductEntity = MarketProductEntity.builder()
                .userId("xiaofuge")
                .channel("c01")
                .source("s01")
                .goodsId("9890001")
                .build();

        TrialBalanceEntity trialBalanceEntity = indexGroupBuyMarketService.indexMarketTrail(marketProductEntity);

        log.info("请求入参：{}", JSON.toJSONString(marketProductEntity));
        log.info("相应结果：{}", JSON.toJSONString(trialBalanceEntity));

    }

}
