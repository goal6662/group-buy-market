package com.goal.service.activity;

import com.goal.model.vo.GroupBuyActivityDiscountVO;
import com.goal.model.vo.SCSkuActivityVO;
import com.goal.model.vo.SkuVO;

public interface IActivityService {

    GroupBuyActivityDiscountVO queryGroupBuyActivityDiscountVO(Long activityId);

    SkuVO querySkuByGoodsId(String goodsId);

    SCSkuActivityVO querySCSkuActivityVOBySCGoodsId(String source, String channel, String goodsId);

}
