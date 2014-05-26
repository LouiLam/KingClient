package receive;

import object.JfaceWindowManager;
import object.PK;
import object.PKManager;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;

import ui.KingMain;
import ui.WaitDia;

/**
 * 非房主客户端时间没到的情况下 强制离开房间
 * @author Administrator
 *
 */
public class ForceLeavePKResultMessageReceive2012   extends SocketMessageReceived  {

	int camp;
	int seatID;
	String roleName;
long sql_id;
	public ForceLeavePKResultMessageReceive2012() {
	
	}

//	@Override
//	public ChannelBuffer pack() {
//		ChannelBuffer cb = ChannelBuffers.dynamicBuffer();
//		cb.writeShort(2012);
//		cb.writeShort(camp);
//		cb.writeShort(seatID);
//		cb.writeShort(roleName.getBytes().length);
//		try {
//			cb.writeBytes(roleName.getBytes("utf-8"));
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//
//		return cb;
//	}

	@Override
	public void parse(ChannelBuffer buffer) {
		 camp=buffer.readShort();
		 seatID=buffer.readShort();
		 sql_id=buffer.readLong();
		PK pk=PKManager.getInstance().getPKBySQLID(sql_id);
		if(camp==1)
		{
			pk.faqiSeatCount--;
		}
		else
		{
			pk.yingzhanSeatCount--;
		}
	}

	@Override
	public void logicHandle(ChannelBuffer buffer, Channel channel) {
		Display.getDefault().asyncExec(new Runnable() {

			@Override
			public void run() {
				for (Window window : JfaceWindowManager.wm.getWindows()) {
					if(window instanceof KingMain)
					{
						KingMain kingMain=(KingMain) window;
						kingMain.RefreshTable();
					}
					if(window instanceof WaitDia)
					{
						WaitDia waitDia=(WaitDia)window;
						waitDia.leaveLables(camp,seatID);
					}
				}
			}
		});
	}

}
