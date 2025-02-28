package com.goal.service.discount.impl;

import com.goal.model.vo.GroupBuyActivityDiscountVO;
import com.goal.service.discount.AbstractDiscountCalculateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 直减优惠计算
 */
@Slf4j
@Service("ZJ")
public class ZJCalculateServiceImpl extends AbstractDiscountCalculateService {

    @Override
    protected BigDecimal doCalculate(BigDecimal originalPrice, GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount) {
        return originalPrice.subtract(new BigDecimal(groupBuyDiscount.getMarketExpr()));
    }

}
