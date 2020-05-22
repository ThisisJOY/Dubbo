package com.lagou.bean;

import com.lagou.service.HelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

@Component
public class ConsumerComponent {

    @Reference
    private HelloService helloService;

    public void methodA() {
        helloService.methodA();
    }

    public void methodB() {
        helloService.methodB();
    }
    public void methodC() {
        helloService.methodC();
    }
}
