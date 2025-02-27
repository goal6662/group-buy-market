package com.goal.design;

public interface StrategyMapper<T, D, R> {

    /**
     * 获取执行策略
     *
     * @param requestParameter  入参
     * @param dynamicContext    上下文
     * @return  回参
     * @throws Exception    异常
     */
    StrategyHandler<T, D, R> get(T requestParameter, D dynamicContext) throws Exception;

}
