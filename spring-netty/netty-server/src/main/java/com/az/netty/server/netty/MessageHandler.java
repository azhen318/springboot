package com.az.netty.server.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.net.InetSocketAddress;


/**
 * @author quzhengguo
 */
public class MessageHandler extends ChannelInboundHandlerAdapter{

    private Log logger= LogFactory.getLog(this.getClass());

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        logger.info("客户端IP：" + ((InetSocketAddress) ctx.channel().remoteAddress()).getHostString());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //super.channelRead(ctx, msg);
        ByteBuf byteBuf=(ByteBuf)msg;

        byte[] bytes=new byte[byteBuf.readableBytes()];

        byteBuf.readBytes(bytes);
        logger.info("收到客户端信息："+new String(bytes,"GBK"));
        ctx.writeAndFlush(Unpooled.copiedBuffer(
                ("收到客户端消息："+new String(bytes,"GBK")).getBytes("GBK")));
        ctx.close();
    }

    /* @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        super.channelRead(ctx, msg);
        logger.info("收到客户端信息："+msg);
        ctx.channel().writeAndFlush("welcome"+"\n");
        ReferenceCountUtil.release(msg);
    }*/


    /*@Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        ctx.writeAndFlush("hello");

        logger.info("客户端IP：" + ((InetSocketAddress) ctx.channel().remoteAddress()).getHostString());

    }*/
    /*public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf=(ByteBuf)msg;

        byte[] bytes=new byte[byteBuf.readableBytes()];

        byteBuf.readBytes(bytes);



        ctx.channel().writeAndFlush(Unpooled.copiedBuffer("收到".getBytes("utf-8")));
        ctx.channel().closeFuture();
    }*/


}
