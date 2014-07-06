package receive;

import java.io.UnsupportedEncodingException;

import object.JfaceWindowManager;
import object.PK;
import object.PKManager;
import object.PKUser;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;

import ui.KingMain;
import ui.WaitDia;

public class JoinPKResultMessageReceive2003 extends SocketMessageReceived {

	// int camp;
	// int seatID;
	// int pkNum;
	String id, roleName, title, area,map;
	PKUser users[] = new PKUser[10];
	int type;
	int status, point,camp;  
	int faqiSeatCount, yingzhanSeatCount;
	
	long sql_id;
	@Override
	public void parse(ChannelBuffer buffer) {

		status = buffer.readInt();

		type = buffer.readInt();
		point = buffer.readInt();
		// 阵营 1发起 2应战
		camp= buffer.readInt();
		faqiSeatCount = buffer.readInt();
		yingzhanSeatCount = buffer.readInt();
		sql_id= buffer.readLong();
		PK pk=PKManager.getInstance().getPKBySQLID(sql_id);
		pk.faqiSeatCount=faqiSeatCount;
		pk.yingzhanSeatCount=yingzhanSeatCount;
		try {
			int idlength = buffer.readShort();
			byte idBytes[] = new byte[idlength];
			buffer.readBytes(idBytes);
			id = new String(idBytes, "utf-8");

			int myRoleNamelength = buffer.readShort();
			byte myRoleNameBytes[] = new byte[myRoleNamelength];
			buffer.readBytes(myRoleNameBytes);
			roleName = new String(myRoleNameBytes, "utf-8");

			int titlelength = buffer.readShort();
			byte titleBytes[] = new byte[titlelength];
			buffer.readBytes(titleBytes);
			title = new String(titleBytes, "utf-8");
			
			int arealength = buffer.readShort();
			byte areaBytes[] = new byte[arealength];
			buffer.readBytes(areaBytes);
			area = new String(areaBytes, "utf-8");
			
			int maplength = buffer.readShort();
			byte mapBytes[] = new byte[maplength];
			buffer.readBytes(mapBytes);
			map = new String(mapBytes, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		int pkNum = buffer.readShort();
		for (int i = 0; i < pkNum; i++) {
			int Camp = buffer.readShort();
			int seatID = buffer.readShort();
			try {

				int roleNamelength = buffer.readShort();
				byte roleNameBytes[] = new byte[roleNamelength];
				buffer.readBytes(roleNameBytes);
				String roleName = new String(roleNameBytes, "utf-8");
				users[i] = new PKUser(null, roleName, Camp, seatID);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		Display.getDefault().syncExec(new Runnable() {

			@Override
			public void run() {
				KingMain kingMain = null;
				WaitDia waitDia = null;
				for (Window window : JfaceWindowManager.wm.getWindows()) {
					if (window instanceof KingMain) {
						kingMain = (KingMain) window;
					}
					if (window instanceof WaitDia) {
						waitDia = (WaitDia) window;
					}
				}
				if (kingMain == null) {
					return;
				}
				if (status == -1) // 加入房间失败 房间已满
				{
					kingMain.PopErrorJoinMessage(roleName,status);
				} 
				else if(status == -2) //密码错误 加入失败
				{
					kingMain.PopErrorJoinMessage(roleName,status);
				}
				else if(status == -3) //角色名的問題
				{
					kingMain.RoleNameError();
				}
				else {
					kingMain.RefreshTable();
					kingMain.PKJoinSuccess(roleName, type, users, area, title,
							point,camp,sql_id,map);
					if (waitDia != null) {
						waitDia.RefreshLables(users);
					}

				}

			}
		});

	}

	@Override
	public void logicHandle(ChannelBuffer buffer, Channel channel) {
		// TODO Auto-generated method stub

	}

}
