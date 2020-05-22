package com.lagou.service.impl;

import com.lagou.service.HelloService;
import org.apache.dubbo.config.annotation.Service;

import java.util.Random;

@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String methodA() {
        final int i = sleepRandom();
        return "methodA sleeps random: " + i;
    }

    @Override
    public String methodB() {
        final int i = sleepRandom();
        return "methodB sleeps random: " + i;
    }

    @Override
    public String methodC() {
        final int i = sleepRandom();
        return "methodC sleeps random: " + i;
    }

    private static int sleepRandom() {
        final int i = new Random().nextInt(101);
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return i;
    }
}
