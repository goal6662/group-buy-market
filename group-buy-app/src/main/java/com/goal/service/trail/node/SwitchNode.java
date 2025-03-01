package com.goal.service.trail.node;

import com.goal.common.BizException;
import com.goal.common.ResponseCode;
import com.goal.dcc.service.IDccService;
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
public class SwitchNode extends AbstractGroupBuyMarketSupport<MarketProductEntity,
        DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> {

    @Resource
    private MarketNode marketNode;

    @Resource
    private IDccService dccService;

    @Override
    public TrialBalanceEntity doApply(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {

        String userId = requestParameter.getUserId();

        if (dccService.isCutRange(userId)) {
            log.warn("拼团活动切量拦截 {}", userId);
            throw new BizException(ResponseCode.ACTIVITY_CUT_RANGE);
        }

        if (dccService.isDowngradeSwitch()) {
            log.warn("拼团活动降级拦截 {}", userId);
            throw new BizException(ResponseCode.ACTIVITY_DOWNGRADE);
        }

        return router(requestParameter, dynamicContext);
    }

    @Override
    public StrategyHandler<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> get(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        return marketNode;
    }

}
