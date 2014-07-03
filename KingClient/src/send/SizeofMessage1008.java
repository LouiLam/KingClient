package send;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

public class SizeofMessage1008 extends SocketMessageToSend {


	public SizeofMessage1008() {
	}
	@Override
	public ChannelBuffer pack() {
		ChannelBuffer cb = ChannelBuffers.dynamicBuffer();
		cb.writeShort(1008);
		return cb;
	}

}
