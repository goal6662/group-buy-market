package com.goal.service.trail.node;

import com.goal.design.StrategyHandler;
import com.goal.model.entity.MarketProductEntity;
import com.goal.model.entity.TrialBalanceEntity;
import com.goal.service.trail.AbstractGroupBuyMarketSupport;
import com.goal.service.trail.factory.DefaultActivityStrategyFactory;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RootNode extends AbstractGroupBuyMarketSupport<MarketProductEntity,
        DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> {

    @Resource
    private SwitchNode switchNode;

    @Override
    public TrialBalanceEntity apply(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        return null;
    }

    @Override
    public StrategyHandler<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> get(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        return switchNode;
    }

}
