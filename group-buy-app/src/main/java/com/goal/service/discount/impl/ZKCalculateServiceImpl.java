package com.goal.service.discount.impl;

import com.goal.model.vo.GroupBuyActivityDiscountVO;
import com.goal.service.discount.AbstractDiscountCalculateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 折扣优惠计算
 */
@Slf4j
@Service("ZK")
public class ZKCalculateServiceImpl extends AbstractDiscountCalculateService {

    @Override
    protected BigDecimal doCalculate(BigDecimal originalPrice, GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount) {
        String marketExpr = groupBuyDiscount.getMarketExpr();
        return originalPrice.multiply(new BigDecimal(marketExpr));
    }

}
