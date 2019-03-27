package com.az.netty.server.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.tomcat.util.net.NioChannel;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

@Service
/**
 * @author quzhengguo
 */
public class NettyService implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        EventLoopGroup bossGroup =new NioEventLoopGroup();
        EventLoopGroup workGroup=new NioEventLoopGroup();

        try {
            ServerBootstrap b=new ServerBootstrap();

            b.group(bossGroup,workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    .childHandler(new ServerNettyHandler());
            ChannelFuture future=b.bind(8098).sync();
            future.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }


    }
}
