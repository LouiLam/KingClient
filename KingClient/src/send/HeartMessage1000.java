package send;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

public class HeartMessage1000 extends SocketMessageToSend{
 public HeartMessage1000()
 {
 }

	@Override
	public ChannelBuffer pack() {
		ChannelBuffer cb = ChannelBuffers.dynamicBuffer();
		cb.writeShort(1000);
		
		
		return cb;
	}
}
