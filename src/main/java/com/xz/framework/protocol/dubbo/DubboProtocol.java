package com.xz.framework.protocol.dubbo;

import com.xz.framework.Invocation;
import com.xz.framework.Protocol;
import com.xz.framework.URL;

public class DubboProtocol implements Protocol {
    @Override
    public void start(URL url) {
        NettyServer nettyServer = new NettyServer();
        nettyServer.start(url.getHostname(), url.getPort());

    }

    @Override
    public String send(URL url, Invocation invocation) {
        NettyClient nettyClient = new NettyClient();
        return nettyClient.send(url.getHostname(),url.getPort(), invocation);
    }
}
