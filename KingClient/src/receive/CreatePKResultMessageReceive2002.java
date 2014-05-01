package receive;

import java.io.UnsupportedEncodingException;

import object.PK;
import object.PKManager;
import object.PKUser;

import org.eclipse.swt.widgets.Display;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;

import ui.KingLogin;



public class CreatePKResultMessageReceive2002 extends SocketMessageReceived {
	String name=null;
	int type;
	@Override
	public void parse(ChannelBuffer buffer) {
		
			
			
	
			try {
				int status = buffer.readShort();
				if(status!=0)
				{
					Display.getDefault().asyncExec(new Runnable() {
						
						@Override
						public void run() {
							KingLogin.pkui.PopErrorCreateMessage();
						}
					});
					return ;
				}
				int namelength = buffer.readShort();
				byte nameBytes[] = new byte[namelength];
				buffer.readBytes(nameBytes);
				 name = new String(nameBytes,"utf-8");
				
				int arealength = buffer.readShort();
				byte areaBytes[] = new byte[arealength];
				buffer.readBytes(areaBytes);
				String area = new String(areaBytes,"utf-8");
				int maplength = buffer.readShort();
				byte mapBytes[] = new byte[maplength];
				buffer.readBytes(mapBytes);
				String map = new String(mapBytes, "utf-8");

				int titlelength = buffer.readShort();
				byte titleBytes[] = new byte[titlelength];
				buffer.readBytes(titleBytes);
				String title = new String(titleBytes, "utf-8");
				
				int deslength = buffer.readShort();
				byte desBytes[] = new byte[deslength];
				buffer.readBytes(desBytes);
				String des = new String(desBytes, "utf-8");
				
				// 几V几
				 type = buffer.readInt();
				// 点数
				int point = buffer.readInt();
				
				long sql_id = buffer.readLong();
				PK pk=new PK(name,title, area, map,des, type, point,sql_id);
				System.out.println(pk.toString());
				PKManager.getInstance().add(pk);
				PKUser.sql_id=sql_id;
				
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Display.getDefault().asyncExec(new Runnable() {
				
				@Override
				public void run() {
					KingLogin.pkui.RefreshTable();
					KingLogin.pkui.PKCreateSuccess(name,type);
				}
			});
		
		
	}

	@Override
	public void logicHandle(ChannelBuffer buffer, Channel channel) {

	}

}
