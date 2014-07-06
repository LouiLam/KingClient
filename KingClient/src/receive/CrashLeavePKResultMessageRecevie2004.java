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
 * 非房主客户端崩溃，或者通过任务管理器关闭程序离开房间
 * 
 * @author Administrator
 * 
 */
public class CrashLeavePKResultMessageRecevie2004 extends SocketMessageReceived {

	int camp;
	int seatID;
	String name;
	long sql_id;
	// public LeavePKResultMessageRecevie2004(String name, int camp, int seatID)
	// {
	// this.seatID = seatID;
	// this.camp = camp;
	// this.name = name;
	// }

	// @Override
	// public ChannelBuffer pack() {
	// ChannelBuffer cb = ChannelBuffers.dynamicBuffer();
	// cb.writeShort(2004);
	// cb.writeShort(camp);
	// cb.writeShort(seatID);
	// cb.writeShort(name.getBytes().length);
	// try {
	// cb.writeBytes(name.getBytes("utf-8"));
	// } catch (UnsupportedEncodingException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// return cb;
	// }

	@Override
	public void parse(ChannelBuffer buffer) {
		camp = buffer.readShort();
		seatID = buffer.readShort();
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
		Display.getDefault().syncExec(new Runnable() {

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
