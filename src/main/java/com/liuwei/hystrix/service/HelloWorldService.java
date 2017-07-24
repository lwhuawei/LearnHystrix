package com.liuwei.hystrix.service;

import java.util.concurrent.TimeUnit;

public class HelloWorldService {
    public String sayHello() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(2);

        return "Hello, World!" + " thread:" + Thread.currentThread().getName();
    }
}
