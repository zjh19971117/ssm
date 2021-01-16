package com.qf.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/html")
@CrossOrigin
public class TestController {
    @RequestMapping("/path")
    public String url(){
        System.out.println(123456);
        String a = "http://10.12.154.51:8889/file/fc25bd50-c652-40c6-98fb-3abcf2b23327.html";
        return a;
    }
}
