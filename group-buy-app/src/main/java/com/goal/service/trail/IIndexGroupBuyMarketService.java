package com.goal.service.trail;

import com.goal.model.entity.MarketProductEntity;
import com.goal.model.entity.TrialBalanceEntity;

public interface IIndexGroupBuyMarketService {

    TrialBalanceEntity indexMarketTrail(MarketProductEntity marketProductEntity) throws Exception;

}
