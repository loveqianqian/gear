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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.*;

/**
 * com.heren.turtle.gear.meta
 *
 * @author zhiwei
 * @create 2016-12-01 10:37.
 */
public abstract class AbstractService {

    protected final Log logger = LogFactory.getLog(getClass());

    protected Map<String, Object> getMessage(String message) throws DocumentException {
        Document document = DocumentHelper.parseText(message);
        Element rootElement = document.getRootElement();
        List elements = rootElement.elements();
        Map<String, Object> result = new HashMap<String, Object>();
        for (Iterator it = elements.iterator(); it.hasNext(); ) {
            Element subElement = (Element) it.next();
            if (subElement.isTextOnly()) {
                result.put(subElement.getName(), subElement.getTextTrim());
            } else {
                List<String> subList = new ArrayList<String>();
                List subEle = subElement.elements();
                for (Iterator iterator = subEle.iterator(); iterator.hasNext(); ) {
                    Element itemElements = (Element) iterator.next();
                    subList.add(itemElements.getTextTrim());
                }
                result.put(subElement.getName(), subList);
            }
        }
        return result;
    }

    protected String createXml(List<Map<String, Object>> paramsList, String resultCode, String errorMsg, String itemName) {
        Document document = null;
        try {
            document = DocumentHelper.createDocument();
            Element response = document.addElement("response");
            for (Map<String, Object> map : paramsList) {
                Element itemEle = response.addElement(itemName);
                for (String key : map.keySet()) {
                    Element element = itemEle.addElement(key);
                    element.setText(String.valueOf(map.get(key)));
                }
            }
            Element resultCodeEle = response.addElement("resultCode");
            resultCodeEle.setText(resultCode);
            Element errorMsgEle = response.addElement("errorMsg");
            errorMsgEle.setText(errorMsg);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        return document != null ? document.asXML().replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "") : null;
    }

    protected String createExamXml(List<Map<String, Object>> paramsList, String resultCode, String errorMsg, String itemName) {
        Document document = null;
        try {
            document = DocumentHelper.createDocument();
            Element response = document.addElement("response");
            for (Map<String, Object> map : paramsList) {
                Element itemEle = response.addElement(itemName);
                for (String key : map.keySet()) {
                    if (key.equals("1")) {
                        Element elecList = itemEle.addElement("elecList");
                        Element elecItem = elecList.addElement("elecItem");
                        Element element = elecItem.addElement(key);
                        element.setText(String.valueOf(map.get(key)));
                    } else {
                        Element ucgList = itemEle.addElement("ucgList");
                        Element ucgItem = ucgList.addElement("ucgItem");
                        Element element = ucgItem.addElement(key);
                        element.setText(String.valueOf(map.get(key)));
                    }
                }
            }
            Element resultCodeEle = response.addElement("resultCode");
            resultCodeEle.setText(resultCode);
            Element errorMsgEle = response.addElement("errorMsg");
            errorMsgEle.setText(errorMsg);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        return document != null ? document.asXML() : null;
    }

}
