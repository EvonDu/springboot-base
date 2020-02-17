package com.self.base.system.response;

import com.self.base.system.base.ApiException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 返回结构处理类: 错误处理
 */
@ControllerAdvice
public class ApiExceptionAdvice {

    /**
     * 接口错误处理函数
     * @param e         错误对象
     * @param request   请求对象
     * @param response  相应对象
     * @return          输出结果
     */
    @ResponseBody
    @ExceptionHandler(ApiException.class)
    public Object apiExceptionHandler(ApiException e, HttpServletRequest request , HttpServletResponse response){
        //设置状态码
        response.setStatus(200);

        // 处理结构
        Map<String,Object> map = new HashMap<>();
        map.put("code", e.getCode());
        map.put("message", e.getMessage());

        // 返回结果
        return map;
    }

    /**
     * 通用错误处理函数
     * @param e         错误对象
     * @param request   请求对象
     * @param response  相应对象
     * @return          输出结果
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Object commonExceptionHandler(Exception e, HttpServletRequest request , HttpServletResponse response){
        //设置状态码
        response.setStatus(200);

        // 处理结构
        Map<String,Object> map = new HashMap<>();
        map.put("code", 500);
        map.put("message", e.getMessage());

        // 返回结果
        return map;
    }

}
