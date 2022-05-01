package com.xz.consumer;

import com.xz.framework.Invocation;
import com.xz.framework.ProxyFactory;
import com.xz.framework.protocol.http.HttpClient;
import com.xz.provider.api.HelloService;

public class Consumer {
    public static void main(String[] args) {
/*        HttpClient httpClient = new HttpClient();
        Invocation invocation = new Invocation(HelloService.class.getName(), "sayHello",
                new Class[]{String.class}, new Object[]{"xz"});
        String result = httpClient.send("localhost", 8080, invocation);
        System.out.println("result:" + result);*/
        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        String result = helloService.sayHello("redLine");
        System.out.println(result);
    }
}
