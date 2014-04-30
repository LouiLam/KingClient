package send;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

public class LoginMessage1001 extends SocketMessageToSend{
	String name;
 public LoginMessage1001(String name)
 {
	 this.name=name;
 }

	@Override
	public ChannelBuffer pack() {
		ChannelBuffer cb = ChannelBuffers.dynamicBuffer();
		cb.writeShort(1001);
		
		try {
			cb.writeShort(URLEncoder.encode(name,"UTF-8").getBytes().length);
			cb.writeBytes(URLEncoder.encode(name,"UTF-8").getBytes());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cb;
	}
}
