package receive;

import object.JfaceWindowManager;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;

import ui.KingMain;



public class NoCanStartGamePKMessageReceive2010 extends SocketMessageReceived {
	@Override
	public void parse(ChannelBuffer buffer) {
		
			
			
	
			Display.getDefault().syncExec(new Runnable() {
				
				@Override
				public void run() {
					for (Window window : JfaceWindowManager.wm.getWindows()) {
						if(window instanceof KingMain)
						{
							KingMain kingMain=(KingMain) window;
							kingMain.btn_start_gameDisable();
						}
					}
				}
			});
		
		
	}

	@Override
	public void logicHandle(ChannelBuffer buffer, Channel channel) {

	}

}
