package send;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import object.PKUser;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

public class JoinPKMessage1003 extends SocketMessageToSend {

	int index;//条目索引
	int camp;//阵营 1发起 2应战
	String name;
	public JoinPKMessage1003(int index,int camp,String name) {
		this.index = index;
		this.camp=camp;
		this.name=name;
	}

	@Override
	public ChannelBuffer pack() {
		ChannelBuffer cb = ChannelBuffers.dynamicBuffer();
		cb.writeShort(1003);
			cb.writeShort(index);
			cb.writeShort(camp);
			
			
			
			try {
				cb.writeShort(URLEncoder.encode(name,"UTF-8").getBytes().length);
				cb.writeBytes(URLEncoder.encode(name,"UTF-8").getBytes());
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cb.writeInt(PKUser.uid);
		return cb;
	}


}
