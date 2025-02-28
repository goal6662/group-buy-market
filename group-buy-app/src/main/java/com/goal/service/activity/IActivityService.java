package com.goal.service.activity;

import com.goal.model.vo.GroupBuyActivityDiscountVO;
import com.goal.model.vo.SkuVO;

public interface IActivityService {

    GroupBuyActivityDiscountVO queryGroupBuyActivityDiscountVO(String source, String channel);

    SkuVO querySkuByGoodsId(String goodsId);

}
