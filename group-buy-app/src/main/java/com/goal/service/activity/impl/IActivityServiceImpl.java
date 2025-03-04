package com.goal.service.activity.impl;

import com.goal.enums.type.DiscountTypeEnum;
import com.goal.mapper.*;
import com.goal.model.*;
import com.goal.model.vo.GroupBuyActivityDiscountVO;
import com.goal.model.vo.SCSkuActivityVO;
import com.goal.model.vo.SkuVO;
import com.goal.service.activity.IActivityService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IActivityServiceImpl implements IActivityService {

    @Resource
    private SkuMapper skuMapper;

    @Resource
    private CrowdTagsDetailMapper crowdTagsDetailMapper;

    @Resource
    private ScSkuActivityMapper scSkuActivityMapper;

    @Resource
    private GroupBuyActivityMapper groupBuyActivityMapper;

    @Resource
    private GroupBuyDiscountMapper groupBuyDiscountMapper;

    @Override
    public GroupBuyActivityDiscountVO queryGroupBuyActivityDiscountVO(Long activityId) {

        GroupBuyActivity groupBuyActivityRes =
                groupBuyActivityMapper.queryValidGroupBuyActivity(GroupBuyActivity.builder()
                        .activityId(activityId)
                        .build());

        String discountId = groupBuyActivityRes.getDiscountId();

        GroupBuyDiscount groupBuyDiscountRes =
                groupBuyDiscountMapper.queryGroupBuyActivityDiscountByDiscountId(discountId);

        GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount = new GroupBuyActivityDiscountVO.GroupBuyDiscount(
                groupBuyDiscountRes.getDiscountName(),
                groupBuyDiscountRes.getDiscountDesc(),
                DiscountTypeEnum.getDiscountTypeEnum(groupBuyDiscountRes.getDiscountType()),
                groupBuyDiscountRes.getMarketPlan(),
                groupBuyDiscountRes.getMarketExpr(),
                groupBuyDiscountRes.getTagId()
        );


        return GroupBuyActivityDiscountVO.builder()
                .activityId(groupBuyActivityRes.getActivityId())
                .activityName(groupBuyActivityRes.getActivityName())
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
        if (skuRes == null) {
            return null;
        }

        return SkuVO.builder()
                .goodsId(goodsId)
                .goodsName(skuRes.getGoodsName())
                .originalPrice(skuRes.getOriginalPrice())
                .build();
    }

    @Override
    public SCSkuActivityVO querySCSkuActivityVOBySCGoodsId(String source, String channel, String goodsId) {

        ScSkuActivity scSkuActivityRes = scSkuActivityMapper.queryScSkuActivityByGoodsId(ScSkuActivity.builder()
                .goodsId(goodsId)
                .build());

        if (null == scSkuActivityRes) {
            return null;
        }

        return SCSkuActivityVO.builder()
                .goodsId(goodsId)
                .source(source)
                .chanel(channel)
                .activityId(scSkuActivityRes.getActivityId())
                .build();
    }

    @Override
    public boolean isTagCrowdRange(String tagId, String userId) {

        List<CrowdTagsDetail> crowdTagsDetailList = crowdTagsDetailMapper.queryCrowdTagDetailByDetail(CrowdTagsDetail.builder()
                        .tagId(tagId)
                        .userId(userId)
                .build());

        return !crowdTagsDetailList.isEmpty();
    }

}
