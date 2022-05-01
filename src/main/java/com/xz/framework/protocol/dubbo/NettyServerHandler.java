package com.xz.framework.protocol.dubbo;

import com.xz.framework.Invocation;
import com.xz.framework.register.LocalRegister;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.lang.reflect.Method;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Invocation invocation = (Invocation) msg;
        Class impl = LocalRegister.get(invocation.getInterfaceName());
        Method method = impl.getMethod(invocation.getMethodName(), invocation.getParamTypes());
        Object result = method.invoke(impl.newInstance(), invocation.getParams());
        System.out.println("Netty-------------" + result.toString());
        ctx.writeAndFlush("Netty:" + result);
    }
}
