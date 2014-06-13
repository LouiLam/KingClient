package ui;

import object.PKUser;

import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import send.EndGamePKMessage1005;

import com.zjd.universal.net.GameClient;

public class JoinPKWaitDia extends WaitDia {
//	public JoinPKWaitDia(Shell parentShell) {
//		
//		super(parentShell,300);
//		// TODO Auto-generated constructor stub
//	}
	public JoinPKWaitDia(Shell parentShell,PKUser users[],int type,String area,String title,int point) {
		super(parentShell, users,60*5,type,area,title,point);
		// TODO Auto-generated constructor stub
	}
	@Override
	void createDialogAreaDoSomeThing(Composite container) {
		endGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				GameClient.getInstance().sendMessageToGameServer(
						new EndGamePKMessage1005());
				endGame.setEnabled(false);
			}
		});
	}


	
}
