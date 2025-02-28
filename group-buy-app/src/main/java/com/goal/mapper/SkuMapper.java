package com.goal.mapper;

import com.goal.model.Sku;
import org.apache.ibatis.annotations.Param;

public interface SkuMapper {

    Sku querySkuByGoodsId(@Param("goodsId") String goodsId);

}




