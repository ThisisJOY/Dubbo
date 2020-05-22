package com.lagou.controller;

import com.lagou.ThreadLocalUtil;
import com.lagou.service.HelloService;
import com.lagou.service.HelloService2;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HelloController {

    @Reference
    private HelloService helloService;

    @Reference
    private HelloService2 helloService2;

    @RequestMapping("/hello.do")
    public String toHello(HttpServletRequest request) {
        ThreadLocalUtil.set(request.getRemoteAddr());
        helloService.sayHello("hello1");
        helloService2.sayHello2("hello2");
        return "hello";
    }

}
