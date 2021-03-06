#### 1、创建示例控制器
* 相关类：`com.self.base.modules.site.controller.IndexController`
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
    * 需要过滤掉`ApiExceptionAdvice`避免进行两次返回值处理（这里直接指定处理包为`com.self.base.modules`）
    
#### 5、添加跨域设置
* 相关类：`com.self.base.system.filters.CorsFilter`
* 实现内容：对请求统一添加跨域的头信息

#### 6、添加Swagger设置
* 相关类：`package com.self.base.system.config.SwaggerConfiguration`
* 相关依赖：`springfox-swagger2`、`springfox-swagger-ui`

#### 7、添加WebSocket服务设置
* 相关类：
    *  `com.self.base.services.websocket.config.WebSocketConfig`
    *  `com.self.base.services.websocket.services.WebSocketService`
* 相关依赖：`spring-boot-starter-websocket`

#### 8、添加AMQP服务设置
* 相关类：
    *  `com.self.base.services.amqp.config.AmqpConfig`
    *  `com.self.base.services.amqp.listeners.AmqpListener`
* 相关依赖：`spring-boot-starter-amqp`

#### 9、添加定时任务示例
* 相关类：`com.self.base.services.works.ExampleWork`
* 实现内容：一个简单的定时输出控制台信息的示例

#### 10、添加异步执行任务示例
* 相关类：`com.self.base.services.async.ExampleComponent`
* 实现内容：一个简单的延迟将信息输出到控制台的示例

#### 11、添加日志配置
* 配置文件：`resources/logback-spring.xml`

#### 12、添加工具类:Http工具类
* 相关类：`com.self.base.utils.HttpUtil`

#### 13、添加工具类:Request工具类
* 相关类：`com.self.base.utils.RequestUtil`

#### 14、添加多环境配置
* 配置文件：
    * `resources/application.yml`
    * `resources/application-dev.yml`
    * `resources/application-pro.yml`
    
#### 15、添加MybatisPlus配置
* 辅助通用依赖：`lombok`
* MybatisPlus相关依赖
    * `mysql-connector-java`
    * `mybatis-spring-boot-starter`
    * `mybatis-plus-boot-starter`
    * `mybatis-plus-generator`
    * `freemarker`(生成器模板)
    
#### 16、添加API测试例子
* 相关类：`com.self.base.modules.site.controller.IndexControllerTest`