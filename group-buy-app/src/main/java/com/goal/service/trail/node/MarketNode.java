package com.goal.service.trail.node;

import com.goal.design.StrategyHandler;
import com.goal.model.entity.MarketProductEntity;
import com.goal.model.entity.TrialBalanceEntity;
import com.goal.model.vo.GroupBuyActivityDiscountVO;
import com.goal.model.vo.SkuVO;
import com.goal.service.trail.AbstractGroupBuyMarketSupport;
import com.goal.service.trail.factory.DefaultActivityStrategyFactory;
import com.goal.service.trail.thread.QueryGroupBuyActivityDiscountVOThreadTask;
import com.goal.service.trail.thread.QuerySkuVOFromDBThreadTask;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class MarketNode extends AbstractGroupBuyMarketSupport<MarketProductEntity,
        DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> {

    @Resource
    private EndNode endNode;

    @Resource
    private ThreadPoolExecutor threadPoolExecutor;

    /**
     * 异步数据加载
     */
    @Override
    protected void multiThread(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        // 创建线程对象
        QueryGroupBuyActivityDiscountVOThreadTask queryGroupBuyActivityDiscountVOThreadTask =
                new QueryGroupBuyActivityDiscountVOThreadTask(activityService,
                        requestParameter.getSource(), requestParameter.getChannel());
        // 创建任务
        FutureTask<GroupBuyActivityDiscountVO> groupBuyActivityDiscountVOThreadTaskFutureTask =
                new FutureTask<>(queryGroupBuyActivityDiscountVOThreadTask);

        // 执行任务
        threadPoolExecutor.execute(groupBuyActivityDiscountVOThreadTaskFutureTask);

        QuerySkuVOFromDBThreadTask querySkuVOFromDBThreadTask =
                new QuerySkuVOFromDBThreadTask(activityService, requestParameter.getGoodsId());
        FutureTask<SkuVO> skuVOFutureTask = new FutureTask<>(querySkuVOFromDBThreadTask);
        threadPoolExecutor.execute(skuVOFutureTask);

        dynamicContext.setGroupBuyActivityDiscountVO(
                groupBuyActivityDiscountVOThreadTaskFutureTask.get(timeout.toMillis(), TimeUnit.MILLISECONDS));

        dynamicContext.setSkuVO(
                skuVOFutureTask.get(timeout.toMillis(), TimeUnit.MILLISECONDS));

    }

    @Override
    public TrialBalanceEntity doApply(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        return router(requestParameter, dynamicContext);
    }

    @Override
    public StrategyHandler<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> get(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        return endNode;
    }

}
