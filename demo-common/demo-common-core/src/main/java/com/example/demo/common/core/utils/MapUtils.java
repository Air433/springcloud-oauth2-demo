package com.example.demo.common.core.utils;

import java.util.HashMap;

/**
 * @Author oyg
 * @Date 2018/7/29/21:07
 */
public class MapUtils extends HashMap {

    @Override
    public MapUtils put(Object key, Object value) {
        super.put(key, value);
        return this;
    }
}
