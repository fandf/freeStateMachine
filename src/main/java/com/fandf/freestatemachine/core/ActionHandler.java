package com.fandf.freestatemachine.core;

/**
 * 动作接口
 *
 * @author fandongfeng
 * @date 2022-11-17 17:40
 */
public interface ActionHandler {

    /**
     * 动作实现类
     *
     * @param context              数据上下文  存储动作
     * @param stateMachineInstance 状态机实例
     * @param userId               执行用户id
     */
    void handler(DataContext context, StateMachineInstance stateMachineInstance, String userId);

}
