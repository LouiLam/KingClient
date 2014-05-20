package send;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
/**
 * 玩家强制离开挑战房间
 *
 */
public class ForceLeavePKMessage1007 extends SocketMessageToSend {


	public ForceLeavePKMessage1007() {
	}
	@Override
	public ChannelBuffer pack() {
		ChannelBuffer cb = ChannelBuffers.dynamicBuffer();
		cb.writeShort(1007);
		return cb;
	}

}
