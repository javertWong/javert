package com.wjk.dubbo.swimlane.provider.service;

import com.wjk.dubbo.provider.api.TestService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author junkai.wang
 * @description TODO
 * @date 2023/5/8 13:31
 */
@DubboService
public class TestServiceImpl implements TestService {
    @Override
    public String sayHi() {
        return "swimlane hi";
    }
}
