package com.self.base.modules.example.controller;

import com.self.base.modules.example.entity.Demo;
import com.self.base.modules.example.service.IDemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author base
 * @since 2020-04-03
 */
@RestController
@RequestMapping("/example/demo")
@Api(tags = "Example", description = "示例模块")
public class DemoController {

    @Autowired
    private IDemoService demoService;

    @RequestMapping(path ="", method = RequestMethod.GET)
    @ApiOperation(value = "列表示例", httpMethod = "GET")
    public List list() {
        return demoService.list();
    }

    @RequestMapping(path ="/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查询示例", httpMethod = "GET")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "主键", required = true, paramType = "path", dataType = "String", defaultValue="1"),
    })
    public Demo detail(@PathVariable("id") Integer id) throws Exception {
        Demo model = demoService.getById(id);
        if(model == null)
            throw new Exception("对象不存在");

        return model;
    }

    @RequestMapping(path ="", method = RequestMethod.POST)
    @ApiOperation(value = "插入示例", httpMethod = "POST")
    public Integer create(@RequestBody Demo model) {
        demoService.save(model);
        return model.getId();
    }

    @RequestMapping(path ="/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "更新示例", httpMethod = "PUT")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", required = true, paramType = "path", dataType = "String", defaultValue="1"),
    })
    public Boolean update(@PathVariable("id") Integer id, @RequestBody Demo change) throws Exception {
        Demo model = demoService.getById(id);
        if(model == null)
            throw new Exception("对象不存在");

        model.setValue(change.getValue());

        return demoService.updateById(model);
    }

    @RequestMapping(path ="/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "更新示例", httpMethod = "DELETE")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", required = true, paramType = "path", dataType = "String", defaultValue="1"),
    })
    public Boolean delete(@PathVariable("id") Integer id){
        return demoService.removeById(id);
    }
}
