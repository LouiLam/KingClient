package receive;

import org.eclipse.swt.widgets.Display;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;

import ui.KingLogin;



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
					
					KingLogin.pkui.EndGameResult(status);
				}
			});
		
		
	}

	@Override
	public void logicHandle(ChannelBuffer buffer, Channel channel) {

	}

}
