package com.goal.service.discount.impl;

import com.goal.model.vo.GroupBuyActivityDiscountVO;
import com.goal.service.discount.AbstractDiscountCalculateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * N元直售
 */
@Slf4j
@Service("NC")
public class NCCalculateServiceImpl extends AbstractDiscountCalculateService {

    @Override
    protected BigDecimal doCalculate(BigDecimal originalPrice, GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount) {
        return new BigDecimal(groupBuyDiscount.getMarketExpr());
    }

}
