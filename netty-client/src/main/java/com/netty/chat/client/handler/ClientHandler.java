package com.netty.chat.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandler extends ChannelInboundHandlerAdapter{
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// TODO Auto-generated method stub
		
		System.err.println((String)msg);
		
		/*ByteBuf response = (ByteBuf)msg;
		byte[] responseArr = new byte[response.readableBytes()];
		response.readBytes(responseArr);
		System.out.println(new String(responseArr));
		response.release();*/
		
		
		
		/*ByteBuf buff = (ByteBuf)msg;
		int b = buff.readableBytes();
		byte a = buff.readByte();
		System.err.println("client receive msg:" + ctx.channel().id() + a);*/
		
		//super.channelRead(ctx, msg);
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		
		ctx.writeAndFlush("hello server,I'm client");
		
		/*String str = "hello,server";
		ByteBuf buf = ctx.alloc().directBuffer(4 * str.length());
		buf.writeBytes(str.getBytes());
		ctx.write(buf);
		ctx.flush();
		System.err.println("client active send msg" + ",time:" + System.currentTimeMillis());*/
		
		//ctx.writeAndFlush("hello server,I'm client");
		//super.channelActive(ctx);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		System.err.println("find error!");
		super.exceptionCaught(ctx, cause);
	}
}
