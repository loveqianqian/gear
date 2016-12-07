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

package com.heren.turtle.gear.utils;

import org.apache.neethi.All;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * com.heren.turtle.gear.utils
 *
 * @author zhiwei
 * @create 2016-12-01 14:56.
 */
public class DateUtils {

    private static final String ALL_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    private static final String CLEAR_ALL_DATE_TIME = "yyyyMMddHHmmss";

    public static String transTime(String params) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(CLEAR_ALL_DATE_TIME);
        Date parse = null;
        try {
            parse = dateFormat.parse(params);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat dateFormat1 = new SimpleDateFormat(ALL_DATE_TIME);
        String format = dateFormat1.format(parse);
        return format;
    }
}
