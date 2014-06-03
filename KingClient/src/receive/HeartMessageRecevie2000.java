package receive;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;

/**
 * 非房主客户端崩溃，或者通过任务管理器关闭程序离开房间
 * 
 * @author Administrator
 * 
 */
public class HeartMessageRecevie2000 extends SocketMessageReceived {


	@Override
	public void parse(ChannelBuffer buffer) {
	}

	@Override
	public void logicHandle(ChannelBuffer buffer, Channel channel) {
		System.out.println("收到服务端返回的心跳包");
	}

}
