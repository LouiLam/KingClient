package receive;

import object.PKManager;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;

public class RoomPKBeginMessageReceive2013 extends SocketMessageReceived {

	@Override
	public void parse(ChannelBuffer buffer) {
		PKManager.getInstance().clear();
		

	}

	@Override
	public void logicHandle(ChannelBuffer buffer, Channel channel) {
		// TODO Auto-generated method stub

	}

}
