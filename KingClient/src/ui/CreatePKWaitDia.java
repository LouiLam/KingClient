package ui;

import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import send.EndGamePKMessage1005;
import send.StartGamePKMessage1004;

import com.zjd.universal.net.GameClient;

public class CreatePKWaitDia extends WaitDia {
	

	public CreatePKWaitDia(Shell parentShell, int type,String area,String title,int point) {
		super(parentShell,60*20,type, area, title,point);
	}

	@Override
	void createDialogAreaDoSomeThing(Composite container) {
	
		startGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				GameClient.getInstance().sendMessageToGameServer(
						new StartGamePKMessage1004());
				startGame.setEnabled(false);
			}
		});
	
		endGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				GameClient.getInstance().sendMessageToGameServer(
						new EndGamePKMessage1005());
				endGame.setEnabled(false);
			}
		});
		
		faqiName[0].setText(KingLogin.roleName);
		faqi[0].setVisible(true);
		faqiNameCopy[0].setVisible(true);
	}


	}
