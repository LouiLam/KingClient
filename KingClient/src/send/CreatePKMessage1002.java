package send;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import object.PK;
import object.PKUser;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

public class CreatePKMessage1002 extends SocketMessageToSend {

	PK pk;

	public CreatePKMessage1002(PK pk) {
		this.pk = pk;
	}
	@Override
	public ChannelBuffer pack() {
		ChannelBuffer cb = ChannelBuffers.dynamicBuffer();
		cb.writeShort(1002);
		try {
			cb.writeShort(URLEncoder.encode(pk.id,"UTF-8").getBytes().length);
			cb.writeBytes(URLEncoder.encode(pk.id,"UTF-8").getBytes());
			cb.writeShort(URLEncoder.encode(pk.roleName,"UTF-8").getBytes().length);
			cb.writeBytes(URLEncoder.encode(pk.roleName,"UTF-8").getBytes());
			cb.writeShort(URLEncoder.encode(pk.area,"UTF-8").getBytes().length);
			cb.writeBytes(URLEncoder.encode(pk.area,"UTF-8").getBytes());
			cb.writeShort(URLEncoder.encode(pk.map,"UTF-8").getBytes().length);
			cb.writeBytes(URLEncoder.encode(pk.map,"UTF-8").getBytes());
			cb.writeShort(URLEncoder.encode(pk.title,"UTF-8").getBytes().length);
			cb.writeBytes(URLEncoder.encode(pk.title,"UTF-8").getBytes());
			cb.writeShort(URLEncoder.encode(pk.des,"UTF-8").getBytes().length);
			cb.writeBytes(URLEncoder.encode(pk.des,"UTF-8").getBytes());
			cb.writeShort(URLEncoder.encode(pk.password,"UTF-8").getBytes().length);
			cb.writeBytes(URLEncoder.encode(pk.password,"UTF-8").getBytes());
			cb.writeInt(pk.type);
			cb.writeInt(pk.point);
			cb.writeInt(PKUser.uid);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cb;
	}

}
