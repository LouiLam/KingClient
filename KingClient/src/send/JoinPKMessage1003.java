package send;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import object.PKUser;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

public class JoinPKMessage1003 extends SocketMessageToSend {

	long sql_id;//sqlID
	int camp;//阵营 1发起 2应战
	String roleName,id;
	public JoinPKMessage1003(long sql_id,int camp,String id,String roleName) {
		this.sql_id = sql_id;
		this.camp=camp;
		this.id=id;
		this.roleName=roleName;
	}

	@Override
	public ChannelBuffer pack() {
		ChannelBuffer cb = ChannelBuffers.dynamicBuffer();
		cb.writeShort(1003);
			cb.writeLong(sql_id);
			cb.writeShort(camp);
			
			
			
			try {
				cb.writeShort(URLEncoder.encode(id,"UTF-8").getBytes().length);
				cb.writeBytes(URLEncoder.encode(id,"UTF-8").getBytes());
				cb.writeShort(URLEncoder.encode(roleName,"UTF-8").getBytes().length);
				cb.writeBytes(URLEncoder.encode(roleName,"UTF-8").getBytes());
			
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cb.writeInt(PKUser.uid);
		return cb;
	}


}
