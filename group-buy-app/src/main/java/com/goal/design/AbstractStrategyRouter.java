package com.goal.design;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class AbstractStrategyRouter<T, D, R>
        implements StrategyMapper<T, D, R>, StrategyHandler<T, D, R> {

    @SuppressWarnings("unchecked")
    protected StrategyHandler<T, D, R> defaultStrategyHandler = StrategyHandler.DEFAULT;

    public R router(T requestParameter, D dynamicContext) throws Exception {

        StrategyHandler<T, D, R> strategyHandler = get(requestParameter, dynamicContext);

        if (null != strategyHandler) {
            return strategyHandler.apply(requestParameter, dynamicContext);
        }

        return defaultStrategyHandler.apply(requestParameter, dynamicContext);

    }

}
