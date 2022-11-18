package com.fandf.freestatemachine.core;

import lombok.extern.slf4j.Slf4j;

/**
 * 状态机实例
 *
 * @author fandongfeng
 * @date 2022-11-17 17:41
 */
@Slf4j
public class StateMachineInstance<S, B, A extends ActionHandler, U extends UserInterface> {

    public final StateMachineConfig<S, B, A, U> config;

    public StateMachineInstance(StateMachineConfig<S, B, A, U> config) {
        this.config = config;
    }


    /**
     * 触发
     *
     * @param behavior 行为
     * @param context  上下文 存储动作
     * @param userId   执行用户id
     */

    public void trigger(B behavior, DataContext context, String userId) {
        //上下文获取状态， 也可以从数据库直接获取
        S currentState = (S) context.getData(BaseStateMachine.CURRENT_STATE);
        if (currentState == null) {
            log.error("数据上下文传递错误 {}", context);
            throw new RuntimeException("没有找到当前状态，状态机无法继续");
        }
        A action = config.getAction(currentState, behavior);
        if (action == null) {
            log.error("状态和行为无法找到对应的动作 state = {}, behavior ={}", currentState, behavior);
            throw new RuntimeException("动作为空");
        }
        U user = config.getUser(currentState, action);
        if (user == null || user.listUser() == null || user.listUser().isEmpty()) {
            log.error("状态和动作无法找到对应的用户 state = {}, action ={}", currentState, action);
            throw new RuntimeException("用户为空");
        }
        if (!user.listUser().contains(userId)) {

        }
        S nextState = config.getNextState(currentState, behavior);
        context.setData(BaseStateMachine.CURRENT_STATE, nextState);
        log.info("状态机运行 当前状态={}, 行为={}, 动作={}, 下一个状态={}", currentState, behavior, action.getClass().getSimpleName(), nextState);
        action.handler(context, this, userId);
    }

}
