package com.lagou.config;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:dubbo-consumer.properties")
@EnableDubbo
@ComponentScan("com.lagou.bean")
public class DubboConsumerConfig {
}

