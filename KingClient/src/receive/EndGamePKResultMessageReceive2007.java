package receive;

import object.JfaceWindowManager;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;

import ui.KingMain;



public class EndGamePKResultMessageReceive2007 extends SocketMessageReceived {
	/**
	 * 	0表示成功 否则失败
	 */
	int status;
	@Override
	public void parse(ChannelBuffer buffer) {
		
		status=buffer.readInt();
			
	
			Display.getDefault().asyncExec(new Runnable() {
				
				@Override
				public void run() {
					KingMain kingMain = null;
					for (Window window : JfaceWindowManager.wm.getWindows()) {
						if (window instanceof KingMain) {
							kingMain = (KingMain) window;
						}
					}
					if(kingMain==null){return;}
					kingMain.EndGameResult(status);
				}
			});
		
		
	}

	@Override
	public void logicHandle(ChannelBuffer buffer, Channel channel) {

	}

}
