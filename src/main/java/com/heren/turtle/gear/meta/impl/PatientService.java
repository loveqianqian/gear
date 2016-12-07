/*
 *
 *  *****************************************************************************
 *  * Copyright ( c ) 2016 Heren Tianjin Inc. All Rights Reserved.
 *  *
 *  * This software is the confidential and proprietary information of Heren Tianjin Inc
 *  * ("Confidential Information").  You shall not disclose such Confidential Information
 *  *  and shall use it only in accordance with the terms of the license agreement
 *  *  you entered into with Heren Tianjin or a Heren Tianjin authorized
 *  *  reseller (the "License Agreement").
 *  ****************************************************************************
 *  *
 */

package com.heren.turtle.gear.meta.impl;

import com.heren.turtle.gear.agent.*;
import com.heren.turtle.gear.agent.impl.PatientAgent;
import com.heren.turtle.gear.meta.AbstractService;
import com.heren.turtle.gear.meta.IPatientService;
import com.heren.turtle.gear.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * com.heren.turtle.gear.meta.impl
 *
 * @author zhiwei
 * @create 2016-12-01 10:39.
 */
@Component("patientService")
@javax.jws.WebService(endpointInterface = "com.heren.turtle.gear.meta.IPatientService", serviceName = "patientService", targetNamespace = "http://meta.gear.turtle.heren.com/")
public class PatientService extends AbstractService implements IPatientService {

    @Autowired
    private IPatientAgent patientAgent;

    @Autowired
    private ILabAgent labAgent;

    @Autowired
    private IMedAgent medAgent;

    @Autowired
    private IExamAgent examAgent;

    @Autowired
    private IEmrAgent emrAgent;

    /**
     * 病人基本信息
     *
     * @param message
     * @return
     */
    @Override
    public String getPatientInfo(String message) {
        logger.info("loadPatientInfo request:" + message);
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        try {
            Map<String, Object> paramsMap = getMessage(message);
            resultList = patientAgent.loadPatientInfo(paramsMap);
            String result = createXml(resultList, "0", "", "item");
            this.logger.info("result:" + result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            this.logger.info(e.getMessage(), e);
            return createXml(resultList, "1", e.getMessage(), "item");
        }
    }

    /**
     * 检验
     *
     * @param message
     * @return
     */
    @Override
    public String getLabCheck(String message) {
        logger.info("getLabCheck request:" + message);
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        try {
            Map<String, Object> paramsMap = getMessage(message);
            resultList = labAgent.loadLabResults(paramsMap);
            String result = createXml(resultList, "0", "", "item");
            this.logger.info("result:" + result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            this.logger.info(e.getMessage(), e);
            return createXml(resultList, "1", e.getMessage(), "item");
        }
    }

    /**
     * 门诊
     *
     * @param message
     * @return
     */
    @Override
    public String getMzxxByJzsj(String message) {
        logger.info("getMzxxByJzsj request:" + message);
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        try {
            Map<String, Object> paramsMap = getMessage(message);
            paramsMap.put("visitDateBegin", DateUtils.transTime(String.valueOf(paramsMap.get("startTime"))));
            paramsMap.put("visitDateEnd", DateUtils.transTime(String.valueOf(paramsMap.get("endTime"))));
            resultList = patientAgent.loadClinicPatients(paramsMap);
            String result = createXml(resultList, "0", "", "item");
            this.logger.info("result:" + result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            this.logger.info(e.getMessage(), e);
            return createXml(resultList, "1", e.getMessage(), "item");
        }
    }

    /**
     * 用药
     *
     * @param message
     * @return
     */
    @Override
    public String getMedicineRecord(String message) {
        logger.info("getMedicineRecord request:" + message);
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        try {
            Map<String, Object> paramsMap = getMessage(message);
            resultList = medAgent.loadMedicineRecords(paramsMap);
            String result = createXml(resultList, "0", "", "item");
            this.logger.info("result:" + result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            this.logger.info(e.getMessage(), e);
            return createXml(resultList, "1", e.getMessage(), "item");
        }
    }

    /**
     * 检查
     *
     * @param message
     * @return
     */
    @Override
    public String getEquipCheck(String message) {
        logger.info("getEquipCheck request:" + message);
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        try {
            Map<String, Object> paramsMap = getMessage(message);
            resultList = examAgent.loadExamResults(paramsMap);
            String result = createXml(resultList, "0", "", "item");
            this.logger.info("result:" + result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            this.logger.info(e.getMessage(), e);
            return createXml(resultList, "1", e.getMessage(), "item");
        }
    }

    /**
     * 住院
     *
     * @param message
     * @return
     */
    @Override
    public String getZyxxByCysj(String message) {
        logger.info("getZyxxByCysj request:" + message);
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        try {
            Map<String, Object> paramsMap = getMessage(message);
            resultList = patientAgent.loadInHosPatient(paramsMap);
            String result = createXml(resultList, "0", "", "item");
            this.logger.info("result:" + result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            this.logger.info(e.getMessage(), e);
            return createXml(resultList, "1", e.getMessage(), "item");
        }
    }

    /**
     * 电子病历
     *
     * @param message
     * @return
     */
    @Override
    public String getEmrInfo(String message) {
        logger.info("getEmrInfo request:" + message);
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        try {
            Map<String, Object> paramsMap = getMessage(message);
            resultList = emrAgent.loadEmrInfo(paramsMap);
            String result = createXml(resultList, "0", "", "item");
            this.logger.info("result:" + result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            this.logger.info(e.getMessage(), e);
            return createXml(resultList, "1", e.getMessage(), "item");
        }
    }
}
