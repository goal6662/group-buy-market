package com.goal.mapper;

import com.goal.model.ScSkuActivity;
import org.apache.ibatis.annotations.Param;

/**
* @author Goal
* @description 针对表【sc_sku_activity(渠道商品活动配置关联表)】的数据库操作Mapper
* @createDate 2025-03-01 10:21:05
* @Entity com.goal.model.ScSkuActivity
*/
public interface ScSkuActivityMapper {

    ScSkuActivity queryScSkuActivityByGoodsId(@Param("scSkuActivity") ScSkuActivity scSkuActivity);

}




