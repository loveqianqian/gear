package com.heren.turtle.gear.agent.impl;

import com.heren.turtle.gear.agent.IMedAgent;
import com.heren.turtle.gear.dao.his.HisGrabDao;
import com.heren.turtle.gear.dao.his.MedGrabDao;
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
 * @create 2016-12-01 21:29.
 */
@Component
@Transactional(value = "hisTransactionManager", readOnly = true)
public class MedAgent implements IMedAgent {

    @Autowired
    private MedGrabDao medGrabDao;

    protected final Log logger = LogFactory.getLog(getClass());

    @Override
    public List<Map<String, Object>> loadMedicineRecords(Map<String, Object> params) {
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> queryList = new ArrayList<Map<String, Object>>();
        if (params.get("dataFrom").equals("1")) {
            String zyid = (String) params.get("ZYID");
            String[] split = zyid.split("_");
            params.put("patientId", split[0]);
            params.put("visitId", split[1]);
            queryList = medGrabDao.loadMedicineRecords(params);
            for (Map<String, Object> itemMap : queryList) {
                Map<String, Object> resultMap = new HashMap<String, Object>();
                resultMap.put("medicine_category_name", itemMap.get("firmId"));
                resultMap.put("chemical_name", "");
                resultMap.put("goods_name", itemMap.get("drugName"));
                resultMap.put("dose", itemMap.get("dosage"));
                resultMap.put("unit", itemMap.get("dosageUnits"));
                resultMap.put("usage", itemMap.get("administration"));
                resultMap.put("frequency", itemMap.get("frequency"));
                resultMap.put("drug_spec", itemMap.get("drugSpec"));
                resultMap.put("dosage_form", itemMap.get("drugForm"));
                if (itemMap.get("repeatIndicator").equals("1")) {
                    resultMap.put("use_time", "6");
                } else if (itemMap.get("repeatIndicator").equals("0")) {
                    resultMap.put("use_time", "5");
                } else {
                    resultMap.put("use_time", "");
                }
                resultMap.put("start_time", itemMap.get("startDateTime"));
                resultMap.put("end_time", itemMap.get("stopDateTime"));
                resultList.add(resultMap);
            }
        } else if (params.get("dataFrom").equals("2")) {
            String mzid = (String) params.get("MZID");
            String[] split = mzid.split("_");
            params.put("patientId", split[0]);
            params.put("visitId", split[1]);
            queryList = medGrabDao.loadClinicMedicineRecords(params);
            for (Map<String, Object> itemMap : queryList) {
                Map<String, Object> resultMap = new HashMap<String, Object>();
                resultMap.put("medicine_category_name", itemMap.get("firmId"));
                resultMap.put("chemical_name", "");
                resultMap.put("goods_name", itemMap.get("drug_name"));
                resultMap.put("dose", "");
                resultMap.put("unit", "");
                resultMap.put("usage", itemMap.get("administration"));
                resultMap.put("frequency", itemMap.get("frequency"));
                resultMap.put("drug_spec", itemMap.get("drugSpec"));
                resultMap.put("dosage_form", itemMap.get("drugForm"));
                resultMap.put("use_time", "3");
                resultMap.put("start_time", "");
                resultMap.put("end_time", "");
                resultList.add(resultMap);
            }
        }
        return resultList;
    }
}
