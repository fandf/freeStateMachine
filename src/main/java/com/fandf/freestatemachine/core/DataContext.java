package com.fandf.freestatemachine.core;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据上下文 保存当前待执行状态
 *
 * @author fandongfeng
 * @date 2022-11-17 17:52
 */
public class DataContext {

    private final Map<String, Object> dataMap;

    public DataContext() {
        this.dataMap = new HashMap<>();
    }

    public Object getData(String key) {
        return dataMap.get(key);
    }

    public Object setData(String key, Object value) {
        return dataMap.put(key, value);
    }

}
