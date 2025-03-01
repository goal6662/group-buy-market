package com.goal.service.activity;

import com.goal.model.vo.GroupBuyActivityDiscountVO;
import com.goal.model.vo.SCSkuActivityVO;
import com.goal.model.vo.SkuVO;

public interface IActivityService {

    GroupBuyActivityDiscountVO queryGroupBuyActivityDiscountVO(Long activityId);

    SkuVO querySkuByGoodsId(String goodsId);

    SCSkuActivityVO querySCSkuActivityVOBySCGoodsId(String source, String channel, String goodsId);

    /**
     * 判断用户是否在标签范围内
     * @param tagId 标签ID
     * @param userId 用户ID
     */
    boolean isTagCrowdRange(String tagId, String userId);
}
