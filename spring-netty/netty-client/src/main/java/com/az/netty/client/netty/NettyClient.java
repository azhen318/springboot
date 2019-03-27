package com.az.netty.client.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;

@Component
/**
 * @author quzhengguo
 */
public class NettyClient implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        EventLoopGroup workGroup =new NioEventLoopGroup();

        try {
            Bootstrap b=new Bootstrap();

            b.group(workGroup).channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline=ch.pipeline();
                            pipeline.addLast("chat",new MessageHandler());
                        }
                    })
                    .option(ChannelOption.TCP_NODELAY,true)
                    .option(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture future=
                    b.connect(new InetSocketAddress("localhost",8098)).sync();

            Channel channel=future.channel();

            try {
                while(true){
                    BufferedReader  reader=new BufferedReader(new InputStreamReader(System.in));
                    String content = reader.readLine();
                    if (content!=null && content.trim().length()!=0) {
                        if ("q".equalsIgnoreCase(content )) {
                            System.exit(1);
                        }
                        channel.writeAndFlush(content);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            future.channel().closeFuture().sync();
        } finally {
            workGroup.shutdownGracefully();
        }


    }
}
