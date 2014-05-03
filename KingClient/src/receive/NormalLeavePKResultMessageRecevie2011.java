package receive;

import object.JfaceWindowManager;

import org.eclipse.swt.widgets.Display;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;

import ui.WaitDia;

/**
 * 离开房间
 * 
 * @author Administrator
 * 
 */
public class NormalLeavePKResultMessageRecevie2011 extends SocketMessageReceived {

	int camp;
	int seatID;
	String name;

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
		System.out.println("正常离开房间camp:"+camp+",seatID:"+seatID);
	}

	@Override
	public void logicHandle(ChannelBuffer buffer, Channel channel) {
		Display.getDefault().asyncExec(new Runnable() {

			@Override
			public void run() {
				if(JfaceWindowManager.getCurWindow() instanceof WaitDia)
				{WaitDia waitDia=(WaitDia) JfaceWindowManager.getCurWindow();
				waitDia.leaveLables(camp,seatID);}
			}
		});

	}

}
