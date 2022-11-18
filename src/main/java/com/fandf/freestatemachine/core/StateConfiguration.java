package com.fandf.freestatemachine.core;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * 状态配置类
 *
 * @author fandongfeng
 * @date 2022-11-17 17:20
 */
public class StateConfiguration<S, B, A, U> {
    @Getter
    private final S currentState;

    /**
     * 行为、动作 集合
     */
    private final Map<B, A> behaviorActionMap;

    /**
     * 动作、用户 集合
     */
    private final Map<A, U> actionUserMap;

    /**
     * 行为、变更状态 集合
     */
    private final Map<B, S> nextStateMap;


    public StateConfiguration(S state) {
        this.currentState = state;
        behaviorActionMap = new HashMap<>(8);
        actionUserMap = new HashMap<>(8);
        nextStateMap = new HashMap<>(8);
    }

    public void configBehaviorAction(B behavior, A action) {
        behaviorActionMap.put(behavior, action);
    }

    public void configActionUser(A action, U user) {
        actionUserMap.put(action, user);
    }

    public void configNextState(B behavior, S nextState) {
        nextStateMap.put(behavior, nextState);
    }

    public A getAction(B behavior) {
        return behaviorActionMap.get(behavior);
    }

    public U getUser(A action) {
        return actionUserMap.get(action);
    }

    public S getNextState(B behavior) {
        return nextStateMap.get(behavior);
    }


}
