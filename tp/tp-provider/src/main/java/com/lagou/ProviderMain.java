package com.lagou;

import com.lagou.config.DubboProviderConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class ProviderMain {
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DubboProviderConfig.class);
        context.start();
        System.in.read();
    }
}
