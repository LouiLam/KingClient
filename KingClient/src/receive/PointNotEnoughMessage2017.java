package receive;

import object.JfaceWindowManager;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;

import ui.KingMain;

public class PointNotEnoughMessage2017 extends SocketMessageReceived {

	@Override
	public void parse(ChannelBuffer buffer) {

		Display.getDefault().syncExec(new Runnable() {

			@Override
			public void run() {
				

				for (Window window : JfaceWindowManager.wm.getWindows()) {
					if(window instanceof KingMain)
					{
						KingMain kingMain=(KingMain) window;
						kingMain.PointNotEnoughError();
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
