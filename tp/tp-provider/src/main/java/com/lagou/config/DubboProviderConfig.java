package com.lagou.config;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableDubbo(scanBasePackages = "com.lagou.service.impl")
@PropertySource("classpath:/dubbo-provider.properties")
public class DubboProviderConfig {
}