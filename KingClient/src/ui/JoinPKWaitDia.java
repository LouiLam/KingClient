package ui;

import object.PKUser;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import send.EndGamePKMessage1005;

import com.zjd.universal.net.GameClient;

public class JoinPKWaitDia extends WaitDia {
//	public JoinPKWaitDia(Shell parentShell) {
//		
//		super(parentShell,300);
//		// TODO Auto-generated constructor stub
//	}
		@Override
		public int open() {

			int result = super.open();
			
			MessageBox mb = new MessageBox(JoinPKWaitDia.this.getParentShell(),
					SWT.ICON_INFORMATION | SWT.OK);
			mb.setMessage("请耐心等待其他玩家加入");
			mb.open();
			return result;
		}
	public JoinPKWaitDia(Shell parentShell,PKUser users[],int type,String area,String title,int point,String map) {
		super(parentShell, users,60*1,type,area,title,point,map);
	}
	@Override
	void createDialogAreaDoSomeThing(Composite container) {
		endGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				GameClient.getInstance().sendMessageToGameServer(
						new EndGamePKMessage1005());
				endGame.setEnabled(false);
			}
		});
	}


	
}
