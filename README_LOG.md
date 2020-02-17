#### 1、创建示例控制器
* 相关类：`com.self.base.modules.site.controllers.IndexController`
* 实现内容：简单的返回字符串

#### 2、添加接口异常类
* 相关类：`com.self.base.system.base.ApiException`
* 实现内容：扩展属性`code(返回码)`

#### 3、对接口异常处理进行统一处理
* 相关类：`com.self.base.system.response.ApiExceptionAdvice`
* 实现内容：统一处理异常时接口的返回内容

#### 4、对接口返回结构进行统一处理
* 相关类：`com.self.base.system.response.ApiResponseAdvice`
* 实现内容：统一处理接口返回结果的数据结构
* 注意事项：
    * 需要对`String`类型的返回做特殊处理，因为`MessageConverter`是不一样的
    * 需要过滤掉`ApiExceptionAdvice`，避免进行两次返回值处理
    
#### 5、添加跨域设置
* 相关类：`com.self.base.system.filters.CorsFilter`
* 实现内容：对请求统一添加跨域的头信息