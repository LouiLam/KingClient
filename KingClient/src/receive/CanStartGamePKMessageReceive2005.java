package receive;

import org.eclipse.swt.widgets.Display;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;

import ui.KingLogin;



public class CanStartGamePKMessageReceive2005 extends SocketMessageReceived {
	@Override
	public void parse(ChannelBuffer buffer) {
		
			
			
	
			Display.getDefault().asyncExec(new Runnable() {
				
				@Override
				public void run() {
					KingLogin.pkui.btn_start_gameEnable();
				}
			});
		
		
	}

	@Override
	public void logicHandle(ChannelBuffer buffer, Channel channel) {

	}

}
