package com.goal.service.trail.factory;

import com.goal.design.StrategyHandler;
import com.goal.model.entity.MarketProductEntity;
import com.goal.model.entity.TrialBalanceEntity;
import com.goal.model.vo.GroupBuyActivityDiscountVO;
import com.goal.model.vo.SkuVO;
import com.goal.service.trail.node.RootNode;
import lombok.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class DefaultActivityStrategyFactory {

    private final RootNode rootNode;

    public StrategyHandler<MarketProductEntity, DynamicContext, TrialBalanceEntity> strategyHandler() {
        return rootNode;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DynamicContext {

        private GroupBuyActivityDiscountVO groupBuyActivityDiscountVO;

        private SkuVO skuVO;

        /**
         * 优惠后的价格
         */
        private BigDecimal deductionPrice;
    }

}
