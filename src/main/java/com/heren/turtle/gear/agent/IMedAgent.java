package com.heren.turtle.gear.agent;

import java.util.List;
import java.util.Map;

/**
 * com.heren.turtle.gear.agent
 *
 * @author zhiwei
 * @create 2016-12-01 21:29.
 */
public interface IMedAgent {

    List<Map<String, Object>> loadMedicineRecords(Map<String, Object> params);
}
