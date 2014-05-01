package send;

import object.PKUser;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

public class StartGamePKMessage1004 extends SocketMessageToSend {


	public StartGamePKMessage1004() {
	}

	@Override
	public ChannelBuffer pack() {
		ChannelBuffer cb = ChannelBuffers.dynamicBuffer();
		cb.writeShort(1004);
			cb.writeLong(PKUser.sql_id);
		return cb;
	}

}
