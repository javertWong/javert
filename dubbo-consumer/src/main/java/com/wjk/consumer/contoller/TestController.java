package com.wjk.consumer.contoller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author junkai.wang
 * @description TODO
 * @date 2023/5/8 10:36
 */
@RestController
@RequestMapping("/")
public class TestController {

    public String sayHi(){
        return "";
    }
}
