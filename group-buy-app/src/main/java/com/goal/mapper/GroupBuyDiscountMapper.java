package com.goal.mapper;

import com.goal.model.GroupBuyDiscount;
import org.apache.ibatis.annotations.Param;

public interface GroupBuyDiscountMapper {

    GroupBuyDiscount queryGroupBuyActivityDiscountByDiscountId(@Param("discountId") String discountId);

}
