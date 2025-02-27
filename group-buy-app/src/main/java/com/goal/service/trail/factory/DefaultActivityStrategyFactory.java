package com.goal.service.trail.factory;

import com.goal.design.StrategyHandler;
import com.goal.model.entity.MarketProductEntity;
import com.goal.model.entity.TrialBalanceEntity;
import com.goal.service.trail.node.RootNode;
import lombok.*;
import org.springframework.stereotype.Service;

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
        // TODO: 2025/2/27 占位字段
        private int id;
    }

}
