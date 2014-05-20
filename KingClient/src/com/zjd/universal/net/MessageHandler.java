package com.zjd.universal.net;

import java.io.IOException;
import java.net.SocketAddress;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

import receive.SocketMessageReceived;

public class MessageHandler extends SimpleChannelHandler {

	
	@Override 
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {  
		Channel channel = e.getChannel(); 
		SocketAddress ip = channel.getRemoteAddress();
    } 
	public void messageReceived(ChannelHandlerContext ctx,MessageEvent e)throws Exception{
		Channel channel = (Channel)e.getChannel();
		ChannelBuffer channelBuffer = (ChannelBuffer)e.getMessage();
		short msgType = channelBuffer.readShort();
//		1=client.msg.received.HeartBeatMessage
//
//				1001=client.msg.received.LoginMessage
//				1002=client.msg.received.TakeSeatMessage
//				1004=client.msg.received.StandUpMessage
//
//
//				2002=client.msg.received.BetMessage
//				2006=client.msg.received.YaoPaiMessage
		SocketMessageReceived msg = GameServerMessageFactory.getMessage(msgType);
		System.out.println("收到消息"+msgType);
		msg.handle(channelBuffer,channel);
	}
	
	 @Override  
	 public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
		 if(e.getCause() instanceof IOException){
		 }else{
			 e.getCause().printStackTrace();
		 }
	 }
	 @Override  
	 public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) {
		 try{
		 }catch(Exception e1){
			 e1.printStackTrace();
		 }
	 }
}
