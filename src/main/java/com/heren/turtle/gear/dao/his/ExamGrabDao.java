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


import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by luoxiaoming on 16-11-29.
 */
@Component
public interface ExamGrabDao {

    public List<Map<String, Object>> loadExamResults(Map<String, Object> params);

}
