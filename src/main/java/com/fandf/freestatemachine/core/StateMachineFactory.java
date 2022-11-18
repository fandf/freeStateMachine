package com.fandf.freestatemachine.core;

import java.util.HashMap;
import java.util.Map;

/**
 * 状态机工厂
 * 多个实例并行执行
 *
 * @author fandongfeng
 * @date 2022-11-17 18:30
 */
public class StateMachineFactory {

    private static final Map<String, StateMachineInstance<Object, Object, ActionHandler, UserInterface>> STATE_MACHINE_MAP = new HashMap<>();


    private StateMachineFactory() {
    }


    public static void register(String key, StateMachineInstance<Object, Object, ActionHandler, UserInterface> instance) {
        STATE_MACHINE_MAP.put(key, instance);
    }

    public static StateMachineInstance<Object, Object, ActionHandler, UserInterface> getInstance(String key) {
        return STATE_MACHINE_MAP.get(key);
    }

}
