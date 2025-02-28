package com.goal.service.trail;

import com.goal.design.AbstractMultiThreadStrategyRouter;
import com.goal.service.activity.IActivityService;
import jakarta.annotation.Resource;

import java.time.Duration;

public abstract class AbstractGroupBuyMarketSupport<T, D, R> extends AbstractMultiThreadStrategyRouter<T, D, R> {

    // 超时时间
    protected final Duration timeout = Duration.ofSeconds(60);

    @Resource
    protected IActivityService activityService;

    @Override
    protected void multiThread(T requestParameter, D dynamicContext) throws Exception {
    }

}
