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

package com.heren.turtle.gear.agent;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by luoxiaoming on 16-11-30.
 */
public interface IPatientAgent {

    public List<Map<String, Object>> loadInHosPatient(Map<String, Object> params);

    public List<Map<String, Object>> loadClinicPatients(Map<String, Object> params);

    public List<Map<String, Object>> loadPatientInfo(Map<String, Object> params) throws Exception;


}
