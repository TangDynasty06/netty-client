package com.netty.chat.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandler extends ChannelInboundHandlerAdapter{
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// TODO Auto-generated method stub
		super.channelRead(ctx, msg);
		ByteBuf buff = (ByteBuf)msg;
		int b = buff.readableBytes();
		char a = buff.readChar();
		System.err.println("client receive msg:" + ctx.channel().id() + a);
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelActive(ctx);
		System.err.println("client active send msg" + ",time:" + System.currentTimeMillis());
		//ctx.writeAndFlush("hello server,I'm client");
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		super.exceptionCaught(ctx, cause);
		System.err.println("find error!");
	}
}
