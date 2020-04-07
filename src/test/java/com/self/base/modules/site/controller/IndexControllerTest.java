package com.self.base.modules.site.controller;

import com.self.base.BaseApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= BaseApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IndexControllerTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mvc;
    private MockHttpSession session;

    @Before
    public void init(){
        //初始化MockMvc对象
        this.mvc = MockMvcBuilders.webAppContextSetup(wac).build();
        //定义Session
        //如果要通过Session来判断用户登录, 可以在这里给Session注入一个用户
        //如: session.setAttribute("user",new User());
        this.session = new MockHttpSession();
    }

    @Test
    public void siteTest() throws Exception {
        // 设置请求
        ResultActions result = this.mvc.perform(MockMvcRequestBuilders
                .get("/")
                //.contentType(MediaType.APPLICATION_JSON_UTF8)         //设置ContentType
                //.accept(MediaType.APPLICATION_JSON_UTF8)              //设置字符集
                //.session(this.session)                                //注入Session
                //.content("RequestBody")                               //设置请求体
                //.param("param","123")                                 //设置参数
                //.header("Token","ChKAWh3gzNRmcyTFvdb9FcXF6LL3aVak")   //设置Header
        );
        // 测试断言
        result.andExpect(MockMvcResultMatchers.status().isOk());                                                                    //添加执行断言:判断Http状态
        result.andExpect(MockMvcResultMatchers.jsonPath("$.data").value("Api Index"));                  //添加执行断言:用jsonPath来比较输出内容
        result.andDo(MockMvcResultHandlers.print());                                                                                //结果处理器:执行输出内容
    }
}
