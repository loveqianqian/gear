package com.heren.turtle.gear.agent.impl;

import com.heren.turtle.gear.agent.ILabAgent;
import com.heren.turtle.gear.dao.his.HisGrabDao;
import com.heren.turtle.gear.dao.his.LabGrabDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * com.heren.turtle.gear.agent.impl
 *
 * @author zhiwei
 * @create 2016-12-01 21:23.
 */
@Component
@Transactional(value = "hisTransactionManager", readOnly = true)
public class LabAgent implements ILabAgent {

    @Autowired
    private LabGrabDao labGrabDao;

    protected final Log logger = LogFactory.getLog(getClass());

    @Override
    public List<Map<String, Object>> loadLabResults(Map<String, Object> params) {
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> queryList;
        if (params.get("dataFrom").equals("1")) {
            String zyid = (String) params.get("ZYID");
            String[] split = zyid.split("_");
            params.put("patientId", split[0]);
            params.put("visitId", split[1]);
        } else if (params.get("dataFrom").equals("2")) {
            String mzid = (String) params.get("MZID");
            String[] split = mzid.split("_");
            params.put("patientId", split[0]);
            params.put("clinicVisitId", split[1]);
        }
        queryList = labGrabDao.loadLabResults(params);
        for (Map<String, Object> itemMap : queryList) {
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put("checkName", itemMap.get("checkName"));
            resultMap.put("itemCode", itemMap.get("checkItemCode"));
            resultMap.put("itemName", itemMap.get("checkItemName"));
            resultMap.put("result", itemMap.get("result"));
            resultMap.put("unit", itemMap.get("units"));
            resultMap.put("refRange", itemMap.get("refRange"));
            resultMap.put("checkTime", itemMap.get("resultDateTime"));
            resultList.add(resultMap);
        }
        return resultList;
    }

}
