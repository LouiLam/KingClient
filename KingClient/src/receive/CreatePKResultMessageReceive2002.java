package receive;

import java.io.UnsupportedEncodingException;

import object.JfaceWindowManager;
import object.PKUser;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;

import ui.KingMain;

public class CreatePKResultMessageReceive2002 extends SocketMessageReceived {
	String id = null,roleName=null;
	int type,point;
	String area, title,map;

	@Override
	public void parse(ChannelBuffer buffer) {
		
		try {
			int status = buffer.readShort();
			if (status != 0) {
				Display.getDefault().syncExec(new Runnable() {

					@Override
					public void run() {
						KingMain kingMain = (KingMain) JfaceWindowManager
								.getCurWindow();
						kingMain.PopErrorCreateMessage();
					}
				});
				return;
			}
			int idlength = buffer.readShort();
			byte idBytes[] = new byte[idlength];
			buffer.readBytes(idBytes);
			id = new String(idBytes, "utf-8");

			int roleNamelength = buffer.readShort();
			byte roleNameBytes[] = new byte[roleNamelength];
			buffer.readBytes(roleNameBytes);
			roleName = new String(roleNameBytes, "utf-8");

			int arealength = buffer.readShort();
			byte areaBytes[] = new byte[arealength];
			buffer.readBytes(areaBytes);
			area = new String(areaBytes, "utf-8");
			int maplength = buffer.readShort();
			byte mapBytes[] = new byte[maplength];
			buffer.readBytes(mapBytes);
			map = new String(mapBytes, "utf-8");

			int titlelength = buffer.readShort();
			byte titleBytes[] = new byte[titlelength];
			buffer.readBytes(titleBytes);
			title = new String(titleBytes, "utf-8");

			int deslength = buffer.readShort();
			byte desBytes[] = new byte[deslength];
			buffer.readBytes(desBytes);
			String des = new String(desBytes, "utf-8");

			// 几V几
			type = buffer.readInt();
			// 点数
			 point = buffer.readInt();
			 
			int faqiSeatCount=buffer.readInt();
			int yingzhanSeatCount=buffer.readInt();
			long sql_id = buffer.readLong();
//			PK pk = new PK(id, roleName, title, area, map, des, type, point,
//					sql_id);
//			pk.faqiSeatCount=faqiSeatCount;
//			pk.yingzhanSeatCount=yingzhanSeatCount;
//			PKManager.getInstance().add(pk);
			PKUser.sql_id = sql_id;

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Display.getDefault().syncExec(new Runnable() {

			@Override
			public void run() {
				
				KingMain kingMain = null;
				for (Window window : JfaceWindowManager.wm.getWindows()) {
					if (window instanceof KingMain) {
						kingMain = (KingMain) window;
						kingMain.RefreshTable();
						kingMain.PKCreateSuccess(id, type, area, title,point,map);
					}
				}
			}
		});

	}

	@Override
	public void logicHandle(ChannelBuffer buffer, Channel channel) {

	}

}
