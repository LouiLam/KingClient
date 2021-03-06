package receive;

import java.io.UnsupportedEncodingException;

import object.JfaceWindowManager;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;

import ui.KingMain;



public class StartGamePKResultMessageReceive2006 extends SocketMessageReceived {
	/**
	 * 	0表示成功 否则失败
	 */
	int status;
	String id;
	@Override
	public void parse(ChannelBuffer buffer) {
		
		status=buffer.readInt();
		int namelength = buffer.readShort();
		byte nameBytes[] = new byte[namelength];
		buffer.readBytes(nameBytes);
		 try {
			id = new String(nameBytes,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	
		
			Display.getDefault().syncExec(new Runnable() {
				
				@Override
				public void run() {
					KingMain kingMain = null;
					for (Window window : JfaceWindowManager.wm.getWindows()) {
						if (window instanceof KingMain) {
							kingMain = (KingMain) window;
						}
					}
					if(kingMain==null){return;}
					kingMain.StartGameResult(status,id);
				}
			});
		
		
	}

	@Override
	public void logicHandle(ChannelBuffer buffer, Channel channel) {

	}

}
