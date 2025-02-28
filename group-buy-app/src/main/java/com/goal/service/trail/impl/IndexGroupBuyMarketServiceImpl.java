package com.goal.service.trail.impl;

import com.goal.design.StrategyHandler;
import com.goal.model.entity.MarketProductEntity;
import com.goal.model.entity.TrialBalanceEntity;
import com.goal.service.trail.IIndexGroupBuyMarketService;
import com.goal.service.trail.factory.DefaultActivityStrategyFactory;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class IndexGroupBuyMarketServiceImpl implements IIndexGroupBuyMarketService {

    @Resource
    private DefaultActivityStrategyFactory activityStrategyFactory;

    @Override
    public TrialBalanceEntity indexMarketTrail(MarketProductEntity marketProductEntity) throws Exception {
        StrategyHandler<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity>
                strategyHandler = activityStrategyFactory.strategyHandler();

        return strategyHandler
                .apply(marketProductEntity, DefaultActivityStrategyFactory.DynamicContext.builder().build());
    }

}
