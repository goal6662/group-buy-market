package com.goal.service.trail.thread;

import com.goal.model.vo.SkuVO;
import com.goal.service.activity.IActivityService;
import lombok.AllArgsConstructor;

import java.util.concurrent.Callable;

@AllArgsConstructor
public class QuerySkuVOFromDBThreadTask implements Callable<SkuVO> {

    private final IActivityService activityService;

    private final String goodsId;

    @Override
    public SkuVO call() throws Exception {
        return activityService.querySkuByGoodsId(goodsId);
    }

}
