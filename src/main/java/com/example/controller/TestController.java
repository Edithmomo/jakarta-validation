package com.example.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangjiameng
 * @date 2020-08-18 13:59
 */
@Api(tags = {"测试"})
@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("add")
    public String add() {
        return "s";
    }

    @PostMapping("testPost")
    public String testPost(Integer pageNo) {
        return "pageNo=" + pageNo;
    }
}
