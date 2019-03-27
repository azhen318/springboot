package com.az.netty.server.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class ServerNettyHandler extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline=ch.pipeline();
       /* //编码通道处理
        pipeline.addLast("decode", new StringDecoder());
        //转码通道处理
        pipeline.addLast("encode", new StringEncoder());*/
        pipeline.addLast("chat",new MessageHandler());
    }
}
