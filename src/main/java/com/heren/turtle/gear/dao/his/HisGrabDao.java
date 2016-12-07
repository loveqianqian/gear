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

package com.heren.turtle.gear.dao.his;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by luoxiaoming on 16-11-29.
 */
@Component
public interface HisGrabDao {

    public List<Map<String, Object>> loadPatientInfo(Map<String, Object> params);

    public List<Map<String, Object>> loadInHosPatient(Map<String, Object> params);

    public List<Map<String, Object>> loadClinicPatients(Map<String, Object> params);

    public List<Map<String, Object>> loadPatVisitRecords(Map<String, Object> params);

    public List<Map<String, Object>> loadPatient(Map<String, Object> params);

    public List<Map<String, Object>> loadOrderExecuteRecordsOfClinic(Map<String, Object> params);

    public List<Map<String, Object>> loadOperationRecord(Map<String, Object> params);

    public List<Map<String, Object>> loadInPatOut(Map<String, Object> params);

    public List<Map<String, Object>> loadClinicVisitId(Map<String, Object> params);

    public List<Map<String, Object>> loadPatientDiagnosis(@Param(value = "patientId") String patientId,
                                                          @Param(value = "visitId") String visitId);

}
