package com.goal.design;

public abstract class AbstractMultiThreadStrategyRouter<T, D, R>
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

    @Override
    public R apply(T requestParameter, D dynamicContext) throws Exception {

        multiThread(requestParameter, dynamicContext);

        return doApply(requestParameter, dynamicContext);
    }

    protected abstract void multiThread(T requestParameter, D dynamicContext) throws Exception;

    protected abstract R doApply(T requestParameter, D dynamicContext) throws Exception;

}
