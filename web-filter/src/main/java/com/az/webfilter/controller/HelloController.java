package com.az.webfilter.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("hello")
@Api(value = "hello",tags = "webFilter 测试实例")
public class HelloController {

    @RequestMapping("welcome")
    @ResponseBody
    @ApiOperation(value = "wel",tags = "欢迎Api",httpMethod = "get",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({@ApiImplicitParam()})
    public String welcome() {
        return "welcome";
    }

}
