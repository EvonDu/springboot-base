package com.self.base.system.response;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * 返回结构处理类: 结果处理
 */
@ControllerAdvice
public class ApiResponseAdvice implements ResponseBodyAdvice {

    /**
     * 用于判断哪些需要拦截(这里排除掉ApiExceptionAdvice)
     * @param methodParameter   方法参数(包含请求的类、原本返回的结果等)
     * @param cls               相关类
     * @return                  是否对这次请求做出调整
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class cls) {
        Type adviceClass = methodParameter.getDeclaringClass();
        Type filterClass = ApiExceptionAdvice.class;
        return adviceClass != filterClass;
    }

    /**
     * 对返回结果进行调整
     * @param data              方法参数(包含请求的类、原本返回的结果等)
     * @param methodParameter   相关参数
     * @param mediaType         MediaType
     * @param cls               相关类
     * @param request           请求对象
     * @param response          相应对象
     * @return                  调整后的返回体
     */
    @Override
    public Object beforeBodyWrite(Object data, MethodParameter methodParameter, MediaType mediaType, Class cls, ServerHttpRequest request, ServerHttpResponse response) {
        //设置Http头
        HttpHeaders headers = response.getHeaders();
        headers.add("Content-type", "application/json");

        // 处理结构并返回结果(需要对String类型做特殊处理)
        if(data instanceof String){
            return "{\"code\":0,\"message\":\"OK\",\"data\":\""+ data.toString() +"\"}";
        } else {
            Map<String,Object> map = new HashMap<>();
            map.put("code",0);
            map.put("message","OK");
            map.put("data",data);
            return map;
        }
    }

}
