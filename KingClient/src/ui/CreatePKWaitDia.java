package ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import send.EndGamePKMessage1005;
import send.StartGamePKMessage1004;

import com.zjd.universal.net.GameClient;

public class CreatePKWaitDia extends WaitDia {
	 Label btn_start_game,btn_end_game;
	 Image image_start_game,iamge_end_game;
	

	public CreatePKWaitDia(Shell parentShell, int type,String area,String title) {
		super(parentShell,60*30,type, area, title);
		image_start_game=new Image(Display.getDefault(),"start_game.png");
		iamge_end_game=new Image(Display.getDefault(),"end_game.png");
	}

	@Override
	void createDialogAreaDoSomeThing(Composite container) {
		btn_start_game = new Label(container, SWT.NONE);
		btn_start_game.setBounds(210, 664, 193, 38);
		btn_start_game.setImage(image_start_game);
		btn_start_game.setEnabled(false);
		btn_start_game.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				GameClient.getInstance().sendMessageToGameServer(
						new StartGamePKMessage1004());
				btn_start_game.setEnabled(false);
			}
		});
		
		btn_end_game= new Label(container, SWT.NONE);
		btn_end_game.setBounds(613, 664, 193, 38);
		btn_end_game.setImage(iamge_end_game);
		btn_end_game.setEnabled(false);
		btn_end_game.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				GameClient.getInstance().sendMessageToGameServer(
						new EndGamePKMessage1005());
				btn_end_game.setEnabled(false);
			}
		});
		
		faqiName[0].setText(KingLogin.roleName);
		faqi[0].setVisible(true);
	}


	}
