package com.liuwei.hystrix.webapp;

import com.liuwei.hystrix.service.CommandWithFallbackViaNetwork;
import com.liuwei.hystrix.service.HelloWorldCommand;
import com.liuwei.hystrix.service.HelloWorldService;
import rx.Observable;
import rx.Observer;
import rx.util.functions.Action1;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Path("/HelloWeb")
public class HelloWebapp {

   /*public static void  main(String[] args) {
        //System.out.println(System.currentTimeMillis());
        //System.out.println(Calendar.getInstance().getTimeInMillis());
        //System.out.println(new Date().getTime());
        long a= 10;
        long b = a - Long.valueOf("8");
        System.out.println(b);
    }*/

    @GET()
    @Path("/hello")
    public String hello() throws InterruptedException {
        HelloWorldService helloWorldService = new HelloWorldService();

        return helloWorldService.sayHello() + " | mainThread " + Thread.currentThread().getName();
    }

    @GET()
    @Path("/hystrixSynHello")
    public String hystrixSynHello() {
        //long beginMillisSyn = System.currentTimeMillis();
        //long beginSyn = System.nanoTime();
        //同步
        HelloWorldCommand helloWorldCommand = new HelloWorldCommand("Synchronous-hystrix");
        String result = helloWorldCommand.execute();

        return result + " | mainThread " + Thread.currentThread().getName();
        //return "beginSyn:"+ beginSyn + " |---" + result + "---| endSyn:" + System.nanoTime() + " time:" +  (System.nanoTime() - beginSyn);
        //return "|---" + result + "---| " + " millisTime:" +  (System.currentTimeMillis() - beginMillisSyn) + " nanoTime:" +  (System.nanoTime() - beginSyn);
        //long endSyn = (System.nanoTime() - beginSyn)/1000;
        //long spendTime = endSyn - Long.valueOf(result);
        //return result + " " + endSyn + " " + spendTime;
    }

    @GET()
    @Path("/hystrixASynHello")
    public String hystrixASynHello() {
        //long beginMillisASyn = System.currentTimeMillis();
        //long beginASyn = System.nanoTime();
        //异步
        HelloWorldCommand helloWorldCommand = new HelloWorldCommand("Asynchronous-hystrix");
        Future<String> future = helloWorldCommand.queue();
        String result = null;
        try {
            result = future.get(100, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | TimeoutException | ExecutionException e) {
            e.printStackTrace();
        }

        return result + " | mainThread" + Thread.currentThread().getName();
        //return "beginASyn:"+ beginASyn + " |---" + result + "---| endASyn:" + System.nanoTime() + " time:" +  (System.nanoTime() - beginASyn);
        //return "|---" + result + "---| " + " millisTime:" +  (System.currentTimeMillis() - beginMillisASyn) + " nanoTime:" +  (System.nanoTime() - beginASyn);
        //long endASyn = (System.nanoTime() - beginASyn)/1000;
        //long spendTime = endASyn - Long.valueOf(result);
        //return result + " " + endASyn + " " + spendTime;
    }

    @GET()
    @Path("/hystrixObservable")
    public String hystrixObservable() {
        Observable<String> fs = new HelloWorldCommand("Observable-hystrix").observe();

        //注册结果回调事件
        fs.subscribe(new Action1<String>() {
            @Override
            public void call(String result) {
                System.out.println(result);
            }
        });

        //注册完整执行生命周期事件
        fs.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                //System.out.println("execute onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                //System.out.println("onError " + e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onNext(String args) {
                //System.out.println("onNext: " + args);
            }
        });

        return "Observable-hystrix";
    }

    @GET()
    @Path("/CommandWithFallback")
    public String CommandWithFallback() {
        CommandWithFallbackViaNetwork commandWithFallback = new CommandWithFallbackViaNetwork(100);
        return "Command With Fallback Result : " + commandWithFallback.execute();
    }
}
