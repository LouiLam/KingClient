package send;

import object.PKUser;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

public class EndGamePKMessage1005 extends SocketMessageToSend {


	public EndGamePKMessage1005() {
	}

	@Override
	public ChannelBuffer pack() {
		ChannelBuffer cb = ChannelBuffers.dynamicBuffer();
		cb.writeShort(1005);
			cb.writeLong(PKUser.sql_id);
		return cb;
	}

}
