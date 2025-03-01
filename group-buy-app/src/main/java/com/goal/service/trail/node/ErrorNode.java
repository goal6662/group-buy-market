package com.goal.service.trail.node;

import com.alibaba.fastjson2.JSON;
import com.goal.common.BizException;
import com.goal.common.ResponseCode;
import com.goal.design.StrategyHandler;
import com.goal.model.entity.MarketProductEntity;
import com.goal.model.entity.TrialBalanceEntity;
import com.goal.service.trail.AbstractGroupBuyMarketSupport;
import com.goal.service.trail.factory.DefaultActivityStrategyFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ErrorNode extends AbstractGroupBuyMarketSupport<MarketProductEntity,
        DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> {

    @Override
    protected TrialBalanceEntity doApply(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        log.info("拼团商品查询试算服务-NoMarketNode userId:{} requestParameter:{}", requestParameter.getUserId(), JSON.toJSONString(requestParameter));

        // 无营销配置
        if (null == dynamicContext.getGroupBuyActivityDiscountVO() || null == dynamicContext.getSkuVO()) {
            log.info("商品无拼团营销配置 {}", requestParameter.getGoodsId());
            throw new BizException(ResponseCode.PARAM_ILLEGAL);
        }

        return TrialBalanceEntity.builder().build();

    }

    @Override
    public StrategyHandler<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> get(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        return defaultStrategyHandler;
    }

}
