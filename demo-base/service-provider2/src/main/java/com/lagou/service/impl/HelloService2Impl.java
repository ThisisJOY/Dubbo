package com.lagou.service.impl;

import com.lagou.ThreadLocalUtil;
import com.lagou.service.HelloService2;
import org.apache.dubbo.config.annotation.Service;

@Service
public class HelloService2Impl implements HelloService2 {
    @Override
    public String sayHello2(String name) {
        System.out.println("远程请求ip: " + ThreadLocalUtil.get());
        return "hello2:" + name;
    }
}
