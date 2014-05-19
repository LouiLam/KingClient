package ui;

import java.util.ArrayList;

import object.JfaceWindowManager;
import object.PKUser;
import object.State;
import object.TaskScheduled;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.json.JSONObject;

import send.NormalLeavePKMessage1006;

import com.zjd.universal.net.GameClient;

public abstract class WaitDia extends Dialog {
	private Image image_table_bg,image_wati_icon,image_wait_tz,image_wait_yz,image_wait_vs;
	
	  Label faqi[] = new Label[5];
	  Label faqiName[] = new Label[5];
	 Label yingzhan[] = new Label[5];
	 Label yingzhanName[] = new Label[5];
	 PKUser users[]=null;
	 int curTime;
	 Label time;
	 int type;
	 String area;
	 String title;
	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 * @wbp.parser.constructor
	 */
	public WaitDia(Shell parentShell,int curTime,int type,String area,String title) {
		super(parentShell);
		setWindowManager(JfaceWindowManager.wm);
		this.curTime=curTime;
		this.type = type;
		this.area = area;
		this.title = title;
		image_table_bg=new Image(Display.getDefault(),"green.jpg");
		image_wati_icon=new Image(Display.getDefault(),"wait_icon.png");
		image_wait_tz=new Image(Display.getDefault(),"wait_tz.png");
		image_wait_yz=new Image(Display.getDefault(),"wait_yz.png");
		image_wait_vs=new Image(Display.getDefault(),"vs.png");
		
		
	}
	public WaitDia(Shell parentShell,PKUser users[],int curTime,int type,String area,String title) {
		super(parentShell);
		setWindowManager(JfaceWindowManager.wm);
		this.users=users;
		this.curTime=curTime;
		this.type = type;
		this.area = area;
		this.title = title;
		image_table_bg=new Image(Display.getDefault(),"green.jpg");
		image_wati_icon=new Image(Display.getDefault(),"wait_icon.png");
		image_wait_tz=new Image(Display.getDefault(),"wait_tz.png");
		image_wait_yz=new Image(Display.getDefault(),"wait_yz.png");
		image_wait_vs=new Image(Display.getDefault(),"vs.png");
		
		
	}
	@Override
	protected void handleShellCloseEvent() {
		if(curTime>1&&State.CurState==State.STATE_GAME_START)
		{
			MessageBox mb = new MessageBox(getParentShell(),
					SWT.ICON_INFORMATION | SWT.OK );
			mb.setMessage("倒计时结束后才能退出挑战");//
			mb.open();
		}
		else
		{
			MessageBox mb = new MessageBox(getParentShell(),
					SWT.ICON_INFORMATION | SWT.OK | SWT.CANCEL);
			mb.setMessage("确定要关闭吗?");//
			int rc = mb.open();
			if (rc == SWT.OK) {
				GameClient.getInstance().sendMessageToGameServer(
						new NormalLeavePKMessage1006());
				for (Window window : JfaceWindowManager.wm.getWindows()) {
					if(window instanceof KingMain)
						{
						
						KingMain main=(KingMain) window;
						main.tableEnable();
						}
				}
				
				
				super.handleShellCloseEvent();
			} else if (rc == SWT.CANCEL) {
				return;
			}
		}
		
	

	}
	 abstract void  createDialogAreaDoSomeThing(Composite container);
//	 abstract void  openBefore();
//	@Override
//	protected void constrainShellSize() {
//		super.constrainShellSize();
//	}
	public void RefreshLables(PKUser[] users) {
		for (int i = 0; i < users.length; i++) {
			if (users[i] != null) {
				if (users[i].camp == 1) {
					faqiName[users[i].seatID].setText(users[i].roleName);
					faqi[users[i].seatID].setVisible(true);
				} else {
					yingzhanName[users[i].seatID].setText(users[i].roleName);
					yingzhan[users[i].seatID].setVisible(true);
				}
			}
		}
	}

	public void leaveLables(int camp, int seatID) {
		if (camp == 1) {
			faqiName[seatID].setText("");
			faqi[seatID].setVisible(false);
			
		} else {
			yingzhanName[seatID].setText("");
			yingzhan[seatID].setVisible(false);
		}
	}
	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(null);

		Label label = new Label(container, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		label.setBounds(25, 10, 26, 13);
		label.setText("标题:");
		label.pack();
		label = new Label(container, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		label.setText("地图:");
		label.setBounds(25, 41, 26, 13);
		label.pack();
		label = new Label(container, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		label.setText("对局人数:");
		label.setBounds(25, 72, 52, 13);
		label.pack();
		Label label_2 = new Label(container, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		label_2.setText("距离可退出游戏时间还剩：");
		label_2.setBounds(558, 46, 156, 13);
		
		Composite composite = new Composite(container, SWT.NONE);
		composite.setBounds(10, 91, 998, 567);
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite.setBackgroundImage(image_table_bg);
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setBounds(10, 96, 223, 38);
		lblNewLabel.setImage(image_wait_tz);
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setBounds(10, 419, 223, 38);
		lblNewLabel_1.setImage(image_wait_yz);
		
		Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setBounds(299, 10, 664, 182);
		
		
		for (int i = 0; i <faqiName.length; i++) {
			faqiName[i]=new Label(composite_1, SWT.CENTER);
			faqiName[i].setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
			faqiName[i].setBounds(32+i*126, 155, 94, 20);
			faqi[i]= new Label(composite_1, SWT.NONE);
			faqi[i] .setBounds(32+i*126, 20,94,116);
			faqi[i] .setImage(image_wati_icon);
		}
		
		Label lblNewLabel_3 = new Label(composite, SWT.NONE);
		lblNewLabel_3.setBounds(589, 241, 94, 74);
		lblNewLabel_3.setImage(image_wait_vs);
		
		Composite composite_2 = new Composite(composite, SWT.NONE);
		composite_2.setBounds(299, 361, 664, 182);
		for (int i = 0; i <yingzhanName.length; i++) {
			yingzhanName[i]=new Label(composite_2, SWT.CENTER);
			yingzhanName[i].setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
			yingzhanName[i].setBounds(32+i*126, 155, 94, 20);
			
			yingzhan[i]= new Label(composite_2, SWT.NONE);
			yingzhan[i] .setBounds(32+i*126, 20,94,116);
			yingzhan[i] .setImage(image_wati_icon);
		}
		
		
		
	

		for (int i = 0; i <yingzhanName.length; i++) {
			faqi[i].setVisible(false);
			yingzhan[i].setVisible(false);
		}
		createDialogAreaDoSomeThing(container);
		
		 time = new Label(container, SWT.NONE);
		time.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		time.setBounds(720, 44, 202, 17);
		
		Label titleLabel = new Label(container, SWT.NONE);
		titleLabel.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		titleLabel.setBounds(82, 10, 61, 17);
		titleLabel.setText(title);
		titleLabel.pack();
		Label areaLabel = new Label(container, SWT.NONE);
		areaLabel.setText(area);
		areaLabel.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		areaLabel.setBounds(82, 41, 61, 17);
		areaLabel.pack();
		Label typeLabel = new Label(container, SWT.CENTER);
		typeLabel.setText(type+"v"+type);
		typeLabel.setFont(SWTResourceManager.getFont("宋体", 11, SWT.NORMAL));
		typeLabel.setBounds(83, 68, 61, 17);
		if(users!=null)
		{RefreshLables(users);}
		return container;
	}
	public void showTime()
	{
		String min=curTime/60+"分:";
		String sec=curTime%60+"秒";
		time.setText(min+sec);
		curTime--;
		if(curTime<0)
		{
			TaskScheduled.clear();
		}
	}
	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
//		Button button = createButton(parent, IDialogConstants.FINISH_ID,
//				IDialogConstants.OK_LABEL, true);
//		button.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				try {
//					DateTimeDia.timeMillis=0;
//				} catch (Exception e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			}
//		});
//		button.setText("确定");
//		Button button_1 = createButton(parent, IDialogConstants.CANCEL_ID,
//				IDialogConstants.CANCEL_LABEL, false);
//		button_1.setText("取消");
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(1024, 768);
	}
	ArrayList<JSONObject> list=new ArrayList<JSONObject>();
	ArrayList<Control> listControl=new ArrayList<Control>();
}
