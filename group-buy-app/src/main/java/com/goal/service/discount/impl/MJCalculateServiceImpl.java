package com.goal.service.discount.impl;

import com.goal.common.Constants;
import com.goal.model.vo.GroupBuyActivityDiscountVO;
import com.goal.service.discount.AbstractDiscountCalculateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 满减折扣计算
 */
@Slf4j
@Service("MJ")
public class MJCalculateServiceImpl extends AbstractDiscountCalculateService {



    @Override
    protected BigDecimal doCalculate(BigDecimal originalPrice, GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount) {

        String marketExpr = groupBuyDiscount.getMarketExpr();
        String[] split = marketExpr.split(Constants.SPLITTER);

        BigDecimal x = new BigDecimal(split[0]);
        BigDecimal y = new BigDecimal(split[1]);

        if (originalPrice.compareTo(x) < 0) {
            return originalPrice;
        }

        return originalPrice.subtract(y);
    }

}
