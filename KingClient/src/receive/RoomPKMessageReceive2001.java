package receive;

import java.io.UnsupportedEncodingException;

import object.PK;
import object.PKManager;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;

public class RoomPKMessageReceive2001 extends SocketMessageReceived {

	@Override
	public void parse(ChannelBuffer buffer) {

		try {
			int idlength = buffer.readShort();
			byte idBytes[] = new byte[idlength];
			buffer.readBytes(idBytes);
			String id = new String(idBytes, "utf-8");

			
			int roleNamelength = buffer.readShort();
			byte roleNameBytes[] = new byte[roleNamelength];
			buffer.readBytes(roleNameBytes);
			String roleName = new String(roleNameBytes, "utf-8");
			
			int arealength = buffer.readShort();
			byte areaBytes[] = new byte[arealength];
			buffer.readBytes(areaBytes);
			String area = new String(areaBytes, "utf-8");
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
			String des = new String(titleBytes, "utf-8");
			
			int pwdlength = buffer.readShort();
			byte pwdBytes[] = new byte[pwdlength];
			buffer.readBytes(pwdBytes);
			String password = new String(pwdBytes, "utf-8");
			// 几V几
			int type = buffer.readInt();
			// 点数
			int point = buffer.readInt();
			
			int faqiSeatCount= buffer.readInt();
			int yingzhanSeatCount= buffer.readInt();
			// 数据库ID
			long sql_id = buffer.readLong();
			PK pk = new PK(id,roleName, title, area, map,des, type, point,sql_id,password);
			pk.faqiSeatCount=faqiSeatCount;
			pk.yingzhanSeatCount=yingzhanSeatCount;
			PKManager.getInstance().add(pk);
			System.out.println("RoomPKMessageReceive2001---"+PKManager.getInstance().getPKNum());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
//		Display.getDefault().syncExec(new Runnable() {
//
//			@Override
//			public void run() {
//				KingLogin.pkui.RefreshTable();
//			}
//		});

	}

	@Override
	public void logicHandle(ChannelBuffer buffer, Channel channel) {
		// TODO Auto-generated method stub

	}

}
