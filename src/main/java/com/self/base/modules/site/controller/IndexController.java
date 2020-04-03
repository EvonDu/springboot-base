package com.self.base.modules.site.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Api(tags = "Index", description = "入口模块")
public class IndexController {

    @RequestMapping("")
    @ApiOperation(value = "接口入口", httpMethod = "GET")
    public String index() {
        return "Api Index";
    }

}