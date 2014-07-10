package ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import send.EndGamePKMessage1005;
import send.StartGamePKMessage1004;

import com.zjd.universal.net.GameClient;

public class CreatePKWaitDia extends WaitDia {

	@Override
	public int open() {

		int result = super.open();
		MessageBox mb = new MessageBox(CreatePKWaitDia.this.getParentShell(),
				SWT.ICON_INFORMATION | SWT.OK);
		mb.setMessage("请耐心等待其他玩家加入");
		mb.open();
		return result;
	}

	public CreatePKWaitDia(Shell parentShell, int type, String area,
			String title, int point, String map) {
		super(parentShell, 60 * 5, type, area, title, point, map);
	}

	@Override
	void createDialogAreaDoSomeThing(Composite container) {

		startGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				GameClient.getInstance().sendMessageToGameServer(
						new StartGamePKMessage1004());
				startGame.setEnabled(false);
				MessageBox mb = new MessageBox(CreatePKWaitDia.this.getParentShell(),
						SWT.ICON_INFORMATION | SWT.OK);
				mb.setMessage("1.启动英雄联盟游戏\n\n" + "2.将当前等待界面的玩家加入到英雄联盟游戏中\n\n"
						+ "3.进行英雄联盟比赛\n\n" + "4.比赛结束后请回到当前页面点击\"结束游戏\",系统将会判断比赛胜负");
				mb.open();
			}
		});

		endGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				GameClient.getInstance().sendMessageToGameServer(
						new EndGamePKMessage1005());
				endGame.setEnabled(false);
			}
		});

		faqiName[0].setText(KingLogin.roleName);
		faqi[0].setVisible(true);
		faqiNameCopy[0].setVisible(true);
		faqiShadow[0].setVisible(false);
	}

}
