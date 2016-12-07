package com.heren.turtle.gear.agent.impl;

import com.heren.turtle.gear.agent.IExamAgent;
import com.heren.turtle.gear.dao.his.ExamGrabDao;
import com.heren.turtle.gear.dao.his.HisGrabDao;
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
 * @create 2016-12-01 21:27.
 */
@Component
@Transactional(value = "hisTransactionManager", readOnly = true)
public class ExamAgent implements IExamAgent {

    @Autowired
    private ExamGrabDao examGrabDao;

    protected final Log logger = LogFactory.getLog(getClass());

    @Override
    public List<Map<String, Object>> loadExamResults(Map<String, Object> params) {
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
        queryList = examGrabDao.loadExamResults(params);
        for (Map<String, Object> itemMap : queryList) {
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put("checkTime", itemMap.get("examDateTime"));
            resultMap.put("desc", itemMap.get("description"));
            resultMap.put("imgsPath", itemMap.get("url"));
            resultMap.put("result", itemMap.get("impression"));
            if (itemMap.get("examClass").equals("心功能")){
                resultMap.put("type", "1");//心电图
            }else if(itemMap.get("examClass").equals("超声")){
                resultMap.put("type", "2");//心电彩超
            }
            resultList.add(resultMap);
        }
        return resultList;
    }
}
