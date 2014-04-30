package receive;

import org.eclipse.swt.widgets.Display;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;

import ui.KingLogin;

public class RoomPKMessageReceive2008 extends SocketMessageReceived {

	@Override
	public void parse(ChannelBuffer buffer) {

		
		Display.getDefault().asyncExec(new Runnable() {

			@Override
			public void run() {
				KingLogin.pkui.RefreshTable();
			}
		});

	}

	@Override
	public void logicHandle(ChannelBuffer buffer, Channel channel) {
		// TODO Auto-generated method stub

	}

}
