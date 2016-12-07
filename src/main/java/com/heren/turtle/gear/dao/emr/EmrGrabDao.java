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

package com.heren.turtle.gear.dao.emr;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by luoxiaoming on 16-11-29.
 */
@Component
public interface EmrGrabDao {

    /**
     * <p>the element of emr database </p>
     * <ul>
     * <li>主诉内容</li>
     * <li>现病史内容</li>
     * <li>既往病史</li>
     * <li>传染病史</li>
     * <li>外伤史</li>
     * <li>手术史</li>
     * <li>药物过敏史</li>
     * <li>预防接种史</li>
     * <li>疫区居住史</li>
     * <li>疫水、疫源接触史</li>
     * <li>毒品接触史</li>
     * <li>放射物、毒物接触史</li>
     * <li>吸烟史</li>
     * <li>饮酒史</li>
     * <li>月经史</li>
     * <li>无痛经</li>
     * <li>经期规则否</li>
     * <li>婚否</li>
     * <li>配偶体质</li>
     * <li>是否生育</li>
     * <li>家族传染病及遗传病史</li>
     * <li>父母健在否</li>
     * </ul>
     *
     * @param patientId
     * @param visitId
     * @return
     */
    public List<Map<String, Object>> getEmrByKey(@Param(value = "patientId") String patientId,
                                                 @Param(value = "visitId") String visitId);

}
