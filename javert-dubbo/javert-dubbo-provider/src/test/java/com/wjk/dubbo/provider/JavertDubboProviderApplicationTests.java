package com.wjk.dubbo.provider;

import com.wjk.dubbo.provider.api.TestService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JavertDubboProviderApplicationTests {

    @DubboReference
    TestService testService;
    @Test
    void contextLoads() {
        System.out.println(testService.sayHi());
    }

}
