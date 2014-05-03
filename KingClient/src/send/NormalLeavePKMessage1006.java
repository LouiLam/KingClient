package send;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
/**
 * 玩家正常主动离开挑战房间
 *
 */
public class NormalLeavePKMessage1006 extends SocketMessageToSend {


	public NormalLeavePKMessage1006() {
	}
	@Override
	public ChannelBuffer pack() {
		ChannelBuffer cb = ChannelBuffers.dynamicBuffer();
		cb.writeShort(1006);
		return cb;
	}

}
