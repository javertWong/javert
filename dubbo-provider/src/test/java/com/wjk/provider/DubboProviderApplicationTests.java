package com.wjk.provider;

import com.wjk.provider.service.TestService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DubboProviderApplicationTests {
    @DubboReference
    TestService testService;
    @Test
    void contextLoads() {
        System.out.println(testService.sayHi());
    }

}
