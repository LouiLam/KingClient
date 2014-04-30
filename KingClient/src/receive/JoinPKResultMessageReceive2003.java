package receive;

import java.io.UnsupportedEncodingException;

import obj.PKUser;

import org.eclipse.swt.widgets.Display;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;

import ui.KingLogin;

public class JoinPKResultMessageReceive2003 extends SocketMessageReceived {

//	int camp;
//	int seatID;
//	int pkNum;
	String name;
	PKUser users[]=new PKUser[10];
	int type;
	 int status;
	@Override
	public void parse(ChannelBuffer buffer) {
		
		  status=buffer.readInt();
		
		type=buffer.readInt();
		int mynamelength = buffer.readShort();
		byte mynameBytes[] = new byte[mynamelength];
		buffer.readBytes(mynameBytes);
		try {
			 name = new String(mynameBytes, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int	pkNum=buffer.readShort();
		
		
		
		
		for (int i = 0; i < pkNum; i++) {
			String name=null;
			int Camp=buffer.readShort();
			int seatID=buffer.readShort();
			int namelength = buffer.readShort();
			byte nameBytes[] = new byte[namelength];
			buffer.readBytes(nameBytes);
			try {
				 name = new String(nameBytes, "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			users[i]=new PKUser(name, Camp, seatID);
		}
		
		
	
		
		
		
		
		
		Display.getDefault().asyncExec(new Runnable() {
			
			@Override
			public void run() {
				 if(status!=0) //加入房间失败 房间已满
				 {
					 KingLogin.pkui. PopErrorJoinMessage(name);
				 }
				 else
				 {
					 KingLogin.pkui.RefreshLables(users);
						KingLogin.pkui.PKJoinSuccess(name,type);
				 }
				
			}
		});
		
	}

	@Override
	public void logicHandle(ChannelBuffer buffer, Channel channel) {
		// TODO Auto-generated method stub
		
	}


}
