package com.heren.turtle.gear.agent.impl;

import com.heren.turtle.gear.agent.IEmrAgent;
import com.heren.turtle.gear.agent.IExamAgent;
import com.heren.turtle.gear.dao.emr.EmrGrabDao;
import com.heren.turtle.gear.dao.his.ExamGrabDao;
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
@Transactional(value = "emrTransactionManager", readOnly = true)
public class EmrAgent implements IEmrAgent {

    @Autowired
    private EmrGrabDao emrGrabDao;

    protected final Log logger = LogFactory.getLog(getClass());

    @Override
    public List<Map<String, Object>> loadEmrInfo(Map<String, Object> params) {
        String patientId = String.valueOf(params.get("patientId"));
        String visitId = String.valueOf(params.get("visitId"));
        List<Map<String, Object>> emrList = emrGrabDao.getEmrByKey(patientId, visitId);
        return emrList;
    }
}
