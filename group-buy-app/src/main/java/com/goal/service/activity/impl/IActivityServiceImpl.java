package com.goal.service.activity.impl;

import com.goal.mapper.GroupBuyActivityMapper;
import com.goal.mapper.GroupBuyDiscountMapper;
import com.goal.mapper.SkuMapper;
import com.goal.model.GroupBuyActivity;
import com.goal.model.GroupBuyDiscount;
import com.goal.model.Sku;
import com.goal.model.vo.GroupBuyActivityDiscountVO;
import com.goal.model.vo.SkuVO;
import com.goal.service.activity.IActivityService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class IActivityServiceImpl implements IActivityService {

    @Resource
    private SkuMapper skuMapper;

    @Resource
    private GroupBuyActivityMapper groupBuyActivityMapper;

    @Resource
    private GroupBuyDiscountMapper groupBuyDiscountMapper;

    @Override
    public GroupBuyActivityDiscountVO queryGroupBuyActivityDiscountVO(String source, String channel) {

        GroupBuyActivity groupBuyActivityRes =
                groupBuyActivityMapper.queryValidGroupBuyActivity(GroupBuyActivity.builder()
                        .source(source)
                        .channel(channel)
                        .build());

        String discountId = groupBuyActivityRes.getDiscountId();

        GroupBuyDiscount groupBuyDiscountRes =
                groupBuyDiscountMapper.queryGroupBuyActivityDiscountByDiscountId(discountId);

        GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount = new GroupBuyActivityDiscountVO.GroupBuyDiscount(
                groupBuyDiscountRes.getDiscountName(),
                groupBuyDiscountRes.getDiscountDesc(),
                groupBuyDiscountRes.getDiscountType(),
                groupBuyDiscountRes.getMarketPlan(),
                groupBuyDiscountRes.getMarketExpr(),
                groupBuyDiscountRes.getTagId()
        );


        return GroupBuyActivityDiscountVO.builder()
                .activityId(groupBuyActivityRes.getActivityId())
                .activityName(groupBuyActivityRes.getActivityName())
                .source(groupBuyActivityRes.getSource())
                .channel(groupBuyActivityRes.getChannel())
                .goodsId(groupBuyActivityRes.getGoodsId())
                .groupType(groupBuyActivityRes.getGroupType())
                .takeLimitCount(groupBuyActivityRes.getTakeLimitCount())
                .target(groupBuyActivityRes.getTarget())
                .validTime(groupBuyActivityRes.getValidTime())
                .status(groupBuyActivityRes.getStatus())
                .startTime(groupBuyActivityRes.getStartTime())
                .endTime(groupBuyActivityRes.getEndTime())
                .tagId(groupBuyActivityRes.getTagId())
                .tagScope(groupBuyActivityRes.getTagScope())
                .groupBuyDiscount(groupBuyDiscount)
                .build();
    }

    @Override
    public SkuVO querySkuByGoodsId(String goodsId) {

        Sku skuRes = skuMapper.querySkuByGoodsId(goodsId);

        return SkuVO.builder()
                .goodsId(goodsId)
                .goodsName(skuRes.getGoodsName())
                .originalPrice(skuRes.getOriginalPrice())
                .build();
    }

}
