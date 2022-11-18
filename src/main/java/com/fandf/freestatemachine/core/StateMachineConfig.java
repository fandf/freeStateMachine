package com.fandf.freestatemachine.core;

import java.util.HashMap;
import java.util.Map;

/**
 * 状态机配置类
 *
 * @author fandongfeng
 * @date 2022-11-17 17:17
 */
public class StateMachineConfig<S, B, A, U> {

    /**
     * 当前状态
     */
    private S currentState;
    /**
     * 行为
     */
    private B behavior;
    /**
     * 动作
     */
    private A action;
    /**
     * 用户
     */
    private U user;
    /**
     * 变更状态
     */
    private S nextState;

    /**
     * 存储状态和状态机配置集合
     */
    private final Map<S, StateConfiguration<S, B, A, U>> stateConfigurationMap = new HashMap<>(16);

    /**
     * 通过状态、行为 获取动作
     *
     * @param state    状态
     * @param behavior 行为
     * @return A 动作
     */
    public A getAction(S state, B behavior) {
        StateConfiguration<S, B, A, U> stateConfiguration = stateConfigurationMap.get(state);
        if (stateConfiguration == null) {
            return null;
        }
        return stateConfiguration.getAction(behavior);
    }

    public U getUser(S state, A action) {
        StateConfiguration<S, B, A, U> stateConfiguration = stateConfigurationMap.get(state);
        if (stateConfiguration == null) {
            return null;
        }
        return stateConfiguration.getUser(action);
    }

    /**
     * 通过状态、行为 获取下一个状态
     *
     * @param state    状态
     * @param behavior 行为
     * @return S 状态
     */
    public S getNextState(S state, B behavior) {
        return stateConfigurationMap.get(state) == null ? null : stateConfigurationMap.get(state).getNextState(behavior);
    }


    public StateMachineConfig<S, B, A, U> from(S s) {
        this.currentState = s;
        return this;
    }

    public StateMachineConfig<S, B, A, U> behavior(B behavior) {
        this.behavior = behavior;
        return this;
    }

    public StateMachineConfig<S, B, A, U> action(A action) {
        this.action = action;
        return this;
    }

    public StateMachineConfig<S, B, A, U> user(U user) {
        this.user = user;
        return this;
    }


    public StateMachineConfig<S, B, A, U> to(S s) {
        this.nextState = s;
        return this;
    }


    public void build() {
        StateConfiguration<S, B, A, U> stateConfiguration = createOrGetStateConfiguration(currentState);
        stateConfiguration.configBehaviorAction(behavior, action);
        stateConfiguration.configActionUser(action, user);
        stateConfiguration.configNextState(behavior, nextState);
        this.currentState = null;
        this.behavior = null;
        this.action = null;
        this.user = null;
        this.nextState = null;
    }

    private StateConfiguration<S, B, A, U> createOrGetStateConfiguration(S s) {
        if (stateConfigurationMap.get(s) == null) {
            StateConfiguration<S, B, A, U> stateConfiguration = new StateConfiguration<>(s);
            stateConfigurationMap.put(s, stateConfiguration);
        }
        return stateConfigurationMap.get(s);
    }

}
