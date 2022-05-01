package com.xz.framework;

import com.xz.framework.protocol.http.HttpClient;
import com.xz.framework.protocol.http.HttpProtocol;
import com.xz.framework.register.RemoteRegister;
import com.xz.provider.api.HelloService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class ProxyFactory {
    @SuppressWarnings("unchecked")
    public static <T> T getProxy(final Class interfaceClass) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                try {
                    //代理逻辑
                    Protocol protocol = ProtocolFactory.getProtocol();
                    Invocation invocation = new Invocation(interfaceClass.getName(), method.getName(),
                            new Class[]{String.class}, args);
                    /**
                     *负载均衡
                     */
                    List<URL> urls = RemoteRegister.get(interfaceClass.getName());
                    URL url = LoadBalance.random(urls);
                    String result = protocol.send(url, invocation);
                    return result;
                } catch (Exception e) {
                    //可以做一些容错的操作
                    return "执行出错";
                }
            }
        });
    }
}
