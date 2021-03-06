package com.netty.chat.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.chat.common.message.QchatMessage;
import com.chat.common.message.QchatMessage.repeatedMsg3;
import com.chat.common.netty.handler.decode.ByteToMessageDecode;
import com.chat.common.netty.handler.decode.ProtobufDecoder;
import com.chat.common.netty.handler.decode.ProtobufVarint32FrameDecoder;
import com.chat.common.netty.handler.encode.MessageToByteEncode;
import com.chat.common.netty.handler.encode.MsgEncode;
import com.chat.common.netty.handler.encode.ProtobufEncoder;
import com.chat.common.netty.handler.encode.ProtobufVarint32LengthFieldPrepender;
import com.chat.common.scan.MsgScan;
import com.netty.chat.client.client.Client;
import com.netty.chat.client.client.OlineClients;
import com.netty.chat.client.handler.ClientHandler;
import com.netty.chat.client.handler.ClientProtoHandler;

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

//					字符串编解码					
//					ByteBuf delimiter = Unpooled.copiedBuffer("@$_F_F".getBytes());
//					ch.pipeline().addLast(
//											new DelimiterBasedFrameDecoder(1024, delimiter),
//				    			 			new StringDecoder(),
//				    			 			new MsgEncode(),
//											new ClientHandler()
//										  );
					
					
					
//					proto尝试					
//					ch.pipeline().addLast(
//											new ProtobufVarint32FrameDecoder(),
//				    			 			new ProtobufDecoder(QchatMessage.person1.getDefaultInstance()),
//				    			 			
//				    			 			new ProtobufVarint32LengthFieldPrepender(),
//				    			 			new ProtobufEncoder(),
//				    			 			new ClientProtoHandler()
//										 );
					
					//自定义尝试
					ch.pipeline().addLast(
    			 			new ByteToMessageDecode(),
    			 			new MessageToByteEncode(),
    			 			new ClientProtoHandler()
    			 		 );
				}
			});

			ChannelFuture f = b.connect(ip, port).sync();
			
			try {
				while (true) {
					BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
					String str = null;
					while ((str = reader.readLine()) != null) {
//						字符串解码
//						f.channel().writeAndFlush(str);
//						str = null;
						
//						proto解码
						repeatedMsg3 msg3 =  repeatedMsg3.newBuilder().setMsg(str).build();
						f.channel().writeAndFlush(msg3);
						str = null;
						
					}
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
			
			
			//f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			work.shutdownGracefully();
		}
	}
	
	public static void main(String[] args) {
		MsgScan msgScan = new MsgScan("com/chat/common/message");
		msgScan.initMsg();
		NettyClient client = new NettyClient();
		client.connection("127.0.0.1", 6100);
	}
}
