package com.lagou.service.impl;

import com.lagou.ThreadLocalUtil;
import com.lagou.service.HelloService;
import org.apache.dubbo.config.annotation.Service;

@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        System.out.println("远程请求ip: " + ThreadLocalUtil.get());
        return "hello:" + name;
    }
}
