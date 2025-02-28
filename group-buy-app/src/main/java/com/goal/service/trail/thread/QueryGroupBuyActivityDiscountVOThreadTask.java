package com.goal.service.trail.thread;

import com.goal.model.vo.GroupBuyActivityDiscountVO;
import com.goal.service.activity.IActivityService;
import lombok.AllArgsConstructor;

import java.util.concurrent.Callable;

@AllArgsConstructor
public class QueryGroupBuyActivityDiscountVOThreadTask implements Callable<GroupBuyActivityDiscountVO> {

    private final IActivityService activityService;

    private final String source;
    private final String channel;

    @Override
    public GroupBuyActivityDiscountVO call() throws Exception {
        return activityService.queryGroupBuyActivityDiscountVO(source, channel);
    }

}
