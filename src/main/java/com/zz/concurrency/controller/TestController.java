package com.zz.concurrency.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("/test")
@Slf4j
public class TestController {

    @RequestMapping
    @ResponseBody
    public String test(){
        return "test";
    }

}
