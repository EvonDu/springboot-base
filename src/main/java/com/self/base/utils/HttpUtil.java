package com.self.base.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Http请求类,用于调用API接口
 * 使用示例：
 * Http http = new Http();
 * http.addHeaders(new HashMap<String, String>(){{ put("Content-type", "application/json"); }});
 * String result = http.post("http://localhost/project/jk/www/api/web/jky/test", "{\"asd\":\"1\"}");
 * Integer code = http.getResponseCode();
 * Map<String, List<String>> header = http.getResponseHeaders();
 */
public class HttpUtil {
    private HttpURLConnection connect;
    private Map<String, String> headers = new HashMap<>();

    /**
     * 获取Response(提取输入流内容)
     * @param connect Http连接
     * @return response 请求结果报文
     * @throws IOException 异常
     */
    private String getResponse(HttpURLConnection connect) throws IOException {
        InputStream input = connect.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line).append(System.getProperty("line.separator"));
        }
        String result = builder.toString();
        input.close();
        return result;
    }

    /**
     * 设置RequestBody(写入输出流内容)
     * @param connect Http连接
     * @param content 请求体内容
     * @throws IOException 异常
     */
    private void setBody(HttpURLConnection connect, String content) throws IOException {
        OutputStream out = connect.getOutputStream();
        out.write(content.getBytes());
        out.flush();
        out.close();
    }

    /**
     * 设置RequestHeader
     * @param connect Http连接
     * @param items Header的集合
     */
    private void setHeaders(HttpURLConnection connect, Map<String, String> items){
        for (Map.Entry<String, String> item : items.entrySet()){
            connect.setRequestProperty(item.getKey(), item.getValue());
        }
    }

    /**
     * 发起GET请求
     * @param path 请求地址
     * @return response 请求结果报文
     */
    public String get(String path) {
        try{
            // 打开连接
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // 设置调用方法
            conn.setRequestMethod("GET");

            // 设置输入输出
            conn.setDoOutput(true);

            // 设置是否使用缓存
            conn.setUseCaches(true);

            // 设置Header
            this.setHeaders(this.connect, this.headers);

            // 获取输入流,并取得返回结果
            String result = getResponse(conn);

            // 断开连接
            conn.disconnect();

            // 返回结果
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 发起POST请求
     * @param path 请求地址
     * @param body 请求体
     * @return response 请求结果报文
     */
    public String post(String path, String body) {
        try{
            // 打开连接
            URL url = new URL(path);
            this.connect = (HttpURLConnection) url.openConnection();

            // 设置调用方法
            this.connect.setRequestMethod("POST");

            // 设置输入输出
            this.connect.setDoInput(true);
            this.connect.setDoOutput(true);

            // 设置是否使用缓存
            this.connect.setUseCaches(true);

            // 设置Header
            this.setHeaders(this.connect, this.headers);

            // 设置请求体
            this.setBody(this.connect, body);

            // 获取输入流,并取得返回结果
            String result = getResponse(this.connect);

            // 断开连接
            this.connect.disconnect();

            // 返回结果
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 添加HttpHeader
     * @param items 请求头项MAP
     */
    public void addHeaders(Map<String, String> items){
        for (Map.Entry<String, String> item : items.entrySet()){
            this.headers.put(item.getKey(), item.getValue());
        }
    }

    /**
     * 获取最后一次请求的状态码
     * @return code Http状态码
     * @throws IOException 异常
     */
    public Integer getResponseCode() throws IOException {
        return this.connect.getResponseCode();
    }

    /**
     * 获取最后一次请求返回的响应头
     * @return headers 响应头
     */
    public Map<String, List<String>> getResponseHeaders(){
        return this.connect.getHeaderFields();
    }
}
