package receive;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;

/**
 * 房主强制或正常或崩溃离开房间
 * 
 * @author Administrator
 * 
 */
public class SizeofResultMessageRecevie2016 extends SocketMessageReceived {


	@Override
	public void parse(ChannelBuffer buffer) {
		int size=buffer.readInt();
		System.out.println(size);
	}

	@Override
	public void logicHandle(ChannelBuffer buffer, Channel channel) {}

}
