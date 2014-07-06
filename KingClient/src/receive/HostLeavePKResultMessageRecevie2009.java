package receive;

import java.io.UnsupportedEncodingException;

import object.JfaceWindowManager;
import object.State;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;

import ui.KingMain;
import ui.WaitDia;

/**
 * 房主强制或正常或崩溃离开房间
 * 
 * @author Administrator
 * 
 */
public class HostLeavePKResultMessageRecevie2009 extends SocketMessageReceived {
	String id;

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
		int idlength = buffer.readShort();
		byte idBytes[] = new byte[idlength];
		buffer.readBytes(idBytes);
		try {
			id = new String(idBytes, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		State.CurState=State.STATE_GAME_EXCEPTION_EXIT;
	}

	@Override
	public void logicHandle(ChannelBuffer buffer, Channel channel) {
		Display.getDefault().syncExec(new Runnable() {

			@Override
			public void run() {
				for (Window window : JfaceWindowManager.wm.getWindows()) {
					if (window instanceof KingMain) {
						KingMain main = (KingMain) window;
						main.HostLeave(id);
						main.enable();
					}
					if (window instanceof WaitDia) {
						WaitDia waitDia = (WaitDia) window;
						waitDia.myClose();
					}
				}
			}
		});

	}

}
