package com.wjk.provider.service.impl;

import com.wjk.provider.service.TestService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author junkai.wang
 * @description TODO
 * @date 2023/5/6 19:21
 */
@DubboService
public class TestServiceImpl implements TestService {
    @Override
    public String sayHi() {
        return "Hi";
    }
}
