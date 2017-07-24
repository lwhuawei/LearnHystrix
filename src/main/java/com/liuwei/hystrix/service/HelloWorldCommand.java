package com.liuwei.hystrix.service;

import com.netflix.hystrix.HystrixCollapserProperties;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class HelloWorldCommand extends HystrixCommand<String> {
    private String name;

    public HelloWorldCommand(String name) {
        //super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("HelloWorldGroup")));
                //.andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionIsolationThreadTimeoutInMilliseconds(500)));
        this.name = name;
    }

    @Override
    public String run() throws Exception {
        //throw new RuntimeException("error");
        //long beginMillisRun = System.currentTimeMillis();
        //long beginRun = System.nanoTime();
        TimeUnit.MILLISECONDS.sleep(2);

        /*//远程访问
        URL url = new URL("http://100.100.233.14:8991/websonApp/rest/getUserList");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("POST");
        conn.setUseCaches(false);
        conn.setInstanceFollowRedirects(false);

        return ConvertStream2Json(conn.getInputStream());*/


        /* simulate performing network call to retrieve order *//*
        try {
            Thread.sleep((int) (Math.random() * 200) + 50);
        } catch (InterruptedException e) {
            // do nothing
        }

        *//* fail rarely ... but allow failure as this one has no fallback *//*
        if (Math.random() > 0.9999) {
            throw new RuntimeException("random failure loading order over network");
        }

        *//* latency spike 5% of the time *//*
        if (Math.random() > 0.95) {
            // random latency spike
            try {
                Thread.sleep((int) (Math.random() * 300) + 25);
            } catch (InterruptedException e) {
                // do nothing
            }
        }*/

        return "Hello, World! " + name + " thread:" + Thread.currentThread().getName();

        //return "beginRun:"+ beginRun + "** Hello, World! " + name + " **endRun: " + System.nanoTime() + " time:" +  (System.nanoTime() - beginRun);
        //return "** Hello, World! " + name + " ** millisTime:" +  (System.currentTimeMillis() - beginMillisRun) + " nanoTime:" +  (System.nanoTime() - beginRun);
        //return Long.toString((System.nanoTime() - beginRun)/1000);
    }

    @Override
    protected String getFallback() {
        return  "Exeucute failed, getFallback " + name + "!";
    }

    private static String ConvertStream2Json(InputStream inputStream)
    {
        String jsonStr = "";
        // ByteArrayOutputStream相当于内存输出流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        // 将输入流转移到内存输出流中
        try
        {
            while ((len = inputStream.read(buffer, 0, buffer.length)) != -1)
            {
                out.write(buffer, 0, len);
            }
            // 将内存流转换为字符串
            jsonStr = new String(out.toByteArray());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return jsonStr;
    }

}
