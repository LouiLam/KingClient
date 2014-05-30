package receive;

import object.JfaceWindowManager;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;

import ui.KingMain;

/**
 *  重复登录，发送给先登录的玩家
 * 
 * @author Administrator
 * 
 */
public class RoleNameErrorMessageRecevie2015 extends
		SocketMessageReceived {


	@Override
	public void parse(ChannelBuffer buffer) {
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
						kingMain.RoleNameError();
					}
				}
			
			}
		});

	}

}
