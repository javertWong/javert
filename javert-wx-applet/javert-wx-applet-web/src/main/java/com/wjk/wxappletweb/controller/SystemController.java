package com.wjk.wxappletweb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author junkai.wang
 * @description TODO
 * @date 2023/5/18 9:54
 */
@RestController
@RequestMapping("/")
public class SystemController {
    @GetMapping("check.htm")
    public String checkIsLoad() {
        return "success";
    }
}
