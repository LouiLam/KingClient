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
			mb.setMessage("1.启动英雄联盟游戏\n\n" + "2.请等待当前游戏的房主将您加入到英雄联盟游戏中\n\n"
					+ "3.进行英雄联盟比赛\n\n" + "4.比赛结束后请回到当前页面点击\"结束游戏\",系统将会判断比赛胜负");
			mb.open();
			return result;
		}
	public JoinPKWaitDia(Shell parentShell,PKUser users[],int type,String area,String title,int point,String map) {
		super(parentShell, users,60*5,type,area,title,point,map);
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
