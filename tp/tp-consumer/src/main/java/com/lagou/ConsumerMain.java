package com.lagou;

import com.lagou.bean.ConsumerComponent;
import com.lagou.config.DubboConsumerConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConsumerMain {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DubboConsumerConfig.class);
        context.start();
        ConsumerComponent service = context.getBean(ConsumerComponent.class);
        while (true) {
            for (int i = 0; i < 100; i++) {
                Thread.sleep(10); // 1分钟可调用6000次
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        service.methodA();
                    }
                }).start();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        service.methodB();
                    }
                }).start();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        service.methodC();
                    }
                }).start();

            }
        }
    }
}
