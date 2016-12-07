package com.heren.turtle.gear.agent;

import java.util.List;
import java.util.Map;

/**
 * com.heren.turtle.gear.agent
 *
 * @author zhiwei
 * @create 2016-12-01 21:27.
 */
public interface IExamAgent {

    List<Map<String, Object>> loadExamResults(Map<String, Object> params);
}
