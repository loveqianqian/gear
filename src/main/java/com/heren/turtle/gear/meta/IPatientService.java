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

package com.heren.turtle.gear.meta;

/**
 * com.heren.turtle.gear.meta
 *
 * @author zhiwei
 * @create 2016-12-01 10:39.
 */
@javax.jws.WebService
public interface IPatientService {

    String getPatientInfo(String message);

    String getLabCheck(String message);

    String getMzxxByJzsj(String message);

    String getMedicineRecord(String message);

    String getEquipCheck(String message);

    String getZyxxByCysj(String message);

    String getEmrInfo(String message);

}
