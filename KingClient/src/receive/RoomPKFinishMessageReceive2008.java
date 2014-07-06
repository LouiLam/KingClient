package receive;

import object.JfaceWindowManager;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;

import ui.KingMain;

public class RoomPKFinishMessageReceive2008 extends SocketMessageReceived {

	@Override
	public void parse(ChannelBuffer buffer) {

		
		Display.getDefault().syncExec(new Runnable() {

			@Override
			public void run() {
				for (Window window : JfaceWindowManager.wm.getWindows()) {
					if(window instanceof KingMain)
					{
						KingMain kingMain=(KingMain) window;
						kingMain.RefreshTable();
					}
				}
			
//				System.out.println("RoomPKFinishMessageReceive2008--"+PKManager.getInstance().getPKNum());
			}
		});

	}

	@Override
	public void logicHandle(ChannelBuffer buffer, Channel channel) {
		// TODO Auto-generated method stub

	}

}
