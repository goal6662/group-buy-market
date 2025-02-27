package com.goal.design;

public interface StrategyHandler<T, D, R> {

    @SuppressWarnings("rawtypes")
    StrategyHandler DEFAULT = (T, D) -> null;

    R apply(T requestParameter, D dynamicContext) throws Exception;

}
