package com.netty.chat.client;

import com.chat.common.netty.handler.MsgEncode;
import com.netty.chat.client.handler.ClientHandler;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class NettyClient {
	public void connection(String ip, int port){
		EventLoopGroup work = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(work);
			b.channel(NioSocketChannel.class);
			b.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					// TODO Auto-generated method stub
					ByteBuf delimiter = Unpooled.copiedBuffer("@$_F_F".getBytes());
					ch.pipeline().addLast(
											new DelimiterBasedFrameDecoder(1024, delimiter),
				    			 			new StringDecoder(),
				    			 			new MsgEncode(),
											new ClientHandler()
										  );
				}
			});

			ChannelFuture f = b.connect(ip, port).sync();
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			work.shutdownGracefully();
		}
	}
	
	public static void main(String[] args) {
		NettyClient client = new NettyClient();
		client.connection("127.0.0.1", 6100);
	}
}
