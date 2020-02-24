package com.self.base.app.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "Index", description = "入口模块")
public class IndexController {

    @RequestMapping("/")
    @ApiOperation(value = "接口入口", httpMethod = "GET")
    public String index() {
        return "Api Index";
    }

}