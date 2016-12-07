package com.heren.turtle.gear.agent;

import java.util.List;
import java.util.Map;

/**
 * com.heren.turtle.gear.agent
 *
 * @author zhiwei
 * @create 2016-12-02 14:47.
 */
public interface IEmrAgent {

    List<Map<String, Object>> loadEmrInfo(Map<String, Object> params);
}
