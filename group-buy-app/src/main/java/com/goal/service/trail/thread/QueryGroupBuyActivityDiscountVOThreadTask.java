package com.goal.service.trail.thread;

import com.goal.model.vo.GroupBuyActivityDiscountVO;
import com.goal.model.vo.SCSkuActivityVO;
import com.goal.service.activity.IActivityService;
import lombok.AllArgsConstructor;

import java.util.concurrent.Callable;

@AllArgsConstructor
public class QueryGroupBuyActivityDiscountVOThreadTask implements Callable<GroupBuyActivityDiscountVO> {

    private final IActivityService activityService;

    private final String source;
    private final String channel;
    private final String goodsId;

    @Override
    public GroupBuyActivityDiscountVO call() throws Exception {

        // 查询渠道商品活动配置关联配置
        SCSkuActivityVO scSkuActivityVO = activityService.querySCSkuActivityVOBySCGoodsId( source, channel, goodsId);
        if (null == scSkuActivityVO) {
            return null;
        }

        return activityService.queryGroupBuyActivityDiscountVO(scSkuActivityVO.getActivityId());
    }

}
