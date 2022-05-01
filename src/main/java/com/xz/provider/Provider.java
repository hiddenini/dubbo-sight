package com.xz.provider;

import com.xz.framework.Protocol;
import com.xz.framework.ProtocolFactory;
import com.xz.framework.URL;
import com.xz.framework.protocol.http.HttpProtocol;
import com.xz.framework.protocol.http.HttpServer;
import com.xz.framework.register.LocalRegister;
import com.xz.framework.register.RemoteRegister;
import com.xz.provider.api.HelloService;
import com.xz.provider.impl.HelloServiceImpl;

public class Provider {
    public static void main(String[] args) {
        //保存接口名和接口实现类的关系  到时候接受到consumer的时候需要根据接口名获取到接口的实现类进行反射调用
        LocalRegister.register(HelloService.class.getName(), HelloServiceImpl.class);
        //注册中心注册
        URL url = new URL("localhost", 8080);
        RemoteRegister.register(HelloService.class.getName(), url);
        //启动Tomcat
        Protocol httpServer = ProtocolFactory.getProtocol();
        httpServer.start(url);
    }
}
