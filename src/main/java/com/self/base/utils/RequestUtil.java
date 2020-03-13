package com.self.base.utils;

import java.util.List;
import java.util.Map;

public class RequestUtil {

    /**
     * 把JSON字符串转换成MAP,并判断是否存在参数
     * @param params 请求参数
     * @param requests 必需参数
     * @throws Exception 异常
     */
    static public void getJsonParams(Map params, List<String> requests) throws Exception {
        for(String key:requests){
            if(!params.containsKey(key))
                throw new Exception("缺少参数:" + key);
        }
    }

}
