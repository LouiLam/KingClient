package receive;

import java.io.UnsupportedEncodingException;

import object.JfaceWindowManager;
import object.PKUser;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;

import ui.KingLogin;
import ui.KingMain;
import ui.WaitDia;

public class JoinPKResultMessageReceive2003 extends SocketMessageReceived {

	// int camp;
	// int seatID;
	// int pkNum;
	String name;
	PKUser users[] = new PKUser[10];
	int type;
	int status;

	@Override
	public void parse(ChannelBuffer buffer) {

		status = buffer.readInt();

		type = buffer.readInt();
		int mynamelength = buffer.readShort();
		byte mynameBytes[] = new byte[mynamelength];
		buffer.readBytes(mynameBytes);
		try {
			name = new String(mynameBytes, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int pkNum = buffer.readShort();

		for (int i = 0; i < pkNum; i++) {
			String name = null;
			int Camp = buffer.readShort();
			int seatID = buffer.readShort();
			int namelength = buffer.readShort();
			byte nameBytes[] = new byte[namelength];
			buffer.readBytes(nameBytes);
			try {
				name = new String(nameBytes, "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			users[i] = new PKUser(name, Camp, seatID);
		}

		Display.getDefault().asyncExec(new Runnable() {

			@Override
			public void run() {
				KingMain kingMain = null;
				WaitDia waitDia = null;
				for (Window window : JfaceWindowManager.wm.getWindows()) {
					if (window instanceof KingMain) {
						kingMain = (KingMain) window;
					}
					if(window instanceof WaitDia)
					{
						waitDia= (WaitDia) window;
					}
				}
				if(kingMain==null){return;}
				if (status != 0) // 加入房间失败 房间已满
				{
					kingMain.PopErrorJoinMessage(name);
				} else {
					kingMain.PKJoinSuccess(name, type,users );
					if(waitDia!=null)
					waitDia.RefreshLables(users);

				}

			}
		});

	}

	@Override
	public void logicHandle(ChannelBuffer buffer, Channel channel) {
		// TODO Auto-generated method stub

	}

}
