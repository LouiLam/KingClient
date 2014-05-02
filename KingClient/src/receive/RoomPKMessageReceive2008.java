package receive;

import object.JfaceWindowManager;

import org.eclipse.swt.widgets.Display;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;

import ui.KingMain;

public class RoomPKMessageReceive2008 extends SocketMessageReceived {

	@Override
	public void parse(ChannelBuffer buffer) {

		
		Display.getDefault().asyncExec(new Runnable() {

			@Override
			public void run() {
				KingMain kingMain=(KingMain) JfaceWindowManager.getCurWindow();
				kingMain.RefreshTable();
			}
		});

	}

	@Override
	public void logicHandle(ChannelBuffer buffer, Channel channel) {
		// TODO Auto-generated method stub

	}

}
