package com.netty.chat.client.handler;

import com.chat.common.message.QchatMessage;
import com.chat.common.message.QchatMessage.person;
import com.google.protobuf.MessageLite;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientProtoHandler extends ChannelInboundHandlerAdapter{
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		person.Builder  builder = person.newBuilder();
		builder.setId(101);
		builder.setName("client");
		ctx.writeAndFlush(builder.build());
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		// TODO Auto-generated method stub
		if(msg instanceof MessageLite){
			QchatMessage.person p = (QchatMessage.person)msg;
			System.err.println(p.getName() + " , " + p.getId());
		}
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		// TODO Auto-generated method stub
		super.exceptionCaught(ctx, cause);
	}
}
