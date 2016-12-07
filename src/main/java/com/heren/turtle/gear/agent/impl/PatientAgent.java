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

package com.heren.turtle.gear.agent.impl;

import com.heren.turtle.gear.agent.IPatientAgent;
import com.heren.turtle.gear.dao.emr.EmrGrabDao;
import com.heren.turtle.gear.dao.his.HisGrabDao;
import jdk.nashorn.internal.ir.IfNode;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by luoxiaoming on 16-11-30.
 */

@Component
public class PatientAgent implements IPatientAgent {

    @Autowired
    private HisGrabDao hisGrabDao;

    protected final Log logger = LogFactory.getLog(getClass());

    @Override
    @Transactional(value = "hisTransactionManager", readOnly = true)
    public List<Map<String, Object>> loadInHosPatient(Map<String, Object> params) {
        List<Map<String, Object>> patientList = hisGrabDao.loadInPatOut(params);
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> patient : patientList) {
            Map<String, Object> resultMap = new HashMap<String, Object>();
            String patientId = (String) patient.get("patientId");
            String visitId = String.valueOf(patient.get("visitId"));
            List<Map<String, Object>> diagnosisList = hisGrabDao.loadPatientDiagnosis(patientId, visitId);
            if (diagnosisList.size() > 0) {
                StringBuilder sb = new StringBuilder();
                StringBuilder sbMain = new StringBuilder();
                for (Map<String, Object> diagnosis : diagnosisList) {
                    String diagnosisNo = String.valueOf(diagnosis.get("diagnosisNo"));
                    if (diagnosisNo.equals("1")) {
                        sbMain.append(diagnosis.get("diagnosisDesc"));
                    } else {
                        sb.append(diagnosis.get("diagnosisDesc"));
                        sb.append("_");
                    }
                }
                resultMap.put("mainDiagnose", sbMain.toString());
                resultMap.put("otherDiagnose", sb.toString());
            } else {
                resultMap.put("mainDiagnose", "");
                resultMap.put("otherDiagnose", "");
            }
            resultMap.put("patientId", patient.get("patientId"));
            resultMap.put("visitId", patient.get("visitId"));
            resultMap.put("bedNumber", "");
            resultMap.put("mainDoctor", patient.get("attendingDoctor"));
            resultMap.put("admissionDate", patient.get("admissionDateTime"));
            resultMap.put("dischargeDate", patient.get("dischargeDateTime"));
            resultMap.put("allMoney", patient.get("totalCosts"));
            resultMap.put("deptName", patient.get("deptName"));
            resultMap.put("wardName", patient.get("wardName"));
            resultList.add(resultMap);
        }
        return resultList;
    }

    @Override
    @Transactional(value = "hisTransactionManager", readOnly = true)
    public List<Map<String, Object>> loadClinicPatients(Map<String, Object> params) {
        List<Map<String, Object>> queryList = hisGrabDao.loadClinicPatients(params);
        List<Map<String, Object>> clinicVisitIdList = hisGrabDao.loadClinicVisitId(params);
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> itemMap : queryList) {
            Map<String, Object> resultMap = new HashMap<String, Object>();
            for (Map<String, Object> clinicMap : clinicVisitIdList) {
                if (itemMap.get("patientId").equals(clinicMap.get("patientId"))
                        && itemMap.get("tempDate").equals(clinicMap.get("tempDate"))) {
                    resultMap.put("visitNo", clinicMap.get("clinicVisitId"));
                    break;
                }
            }
            if (!resultMap.containsKey("visitNo")) {
                resultMap.put("visitNo", "");
            }
            resultMap.put("patientId", itemMap.get("patientId"));
            resultMap.put("visitDate", itemMap.get("tempDate"));
            resultMap.put("clinicNo", itemMap.get("visitNo"));
            resultMap.put("doctorName", itemMap.get("doctor"));
            resultMap.put("patientName", itemMap.get("name"));
            resultMap.put("gender", itemMap.get("sex"));
            resultMap.put("idCard", itemMap.get("idNo"));
            resultMap.put("birthday", itemMap.get("dateOfBirth"));
            resultMap.put("telNumber", itemMap.get("phoneNumberHome"));
            resultList.add(resultMap);
        }
        return resultList;
    }

    @Override
    @Transactional(value = "hisTransactionManager", readOnly = true)
    public List<Map<String, Object>> loadPatientInfo(Map<String, Object> params) throws Exception {
        List<Map<String, Object>> queryList = hisGrabDao.loadPatientInfo(params);
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> itemMap : queryList) {
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put("patientId", itemMap.get("patientId"));
            resultMap.put("gender", itemMap.get("sex"));
            resultMap.put("patientName", itemMap.get("name"));
            resultMap.put("patientEnName", itemMap.get("namePhonetic"));
            resultMap.put("birthday", itemMap.get("dateOfBirth"));
            resultMap.put("telNumber", itemMap.get("phoneNumberHome"));
            resultMap.put("healthInsuranceType", itemMap.get("insurType"));
            resultMap.put("idCard", itemMap.get("idNo"));
            resultMap.put("contactPerson", itemMap.get("contactPerson"));
            resultMap.put("contactPhone", itemMap.get("contactPhone"));
            resultMap.put("contactAddress", itemMap.get("contactAddress"));
            resultMap.put("patient_relation", itemMap.get("relationship"));
            resultMap.put("marital_status", itemMap.get("maritalStatus"));
            resultMap.put("nation", itemMap.get("nation"));
            resultMap.put("current_address", itemMap.get("mailingAddress"));
            resultMap.put("occupation_status", itemMap.get("workingStatus"));
            resultMap.put("occupation", itemMap.get("occupation"));
            resultMap.put("zipcode", itemMap.get("zipCode"));
            resultMap.put("company", itemMap.get("service_agency"));
            resultList.add(resultMap);
        }
        return resultList;
    }

}
