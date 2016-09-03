package com.netty.chat.client.client;

import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.atomic.AtomicInteger;

public class Client {
	private static AtomicInteger idGenerate = new AtomicInteger(1024);
	
	private int id;
	
	private ChannelHandlerContext ctx;

	public Client(ChannelHandlerContext ctx) {
		this.id = idGenerate.getAndIncrement();
		this.ctx = ctx;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ChannelHandlerContext getCtx() {
		return ctx;
	}

	public void setCtx(ChannelHandlerContext ctx) {
		this.ctx = ctx;
	}

	public void wirteMsg(String str){
		ctx.writeAndFlush(str);
	}
	
	public static int getNowId(){
		return idGenerate.get();
	} 
	
}
