package send;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

public class LoginMessage1001 extends SocketMessageToSend{
	String id;
 public LoginMessage1001(String id)
 {
	 this.id=id;
 }

	@Override
	public ChannelBuffer pack() {
		ChannelBuffer cb = ChannelBuffers.dynamicBuffer();
		cb.writeShort(1001);
		
		try {
			cb.writeShort(URLEncoder.encode(id,"UTF-8").getBytes().length);
			cb.writeBytes(URLEncoder.encode(id,"UTF-8").getBytes());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return cb;
	}
}
