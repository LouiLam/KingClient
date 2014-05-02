package ui;

import java.util.ArrayList;

import object.JfaceWindowManager;
import object.PKUser;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.json.JSONObject;

public abstract class WaitDia extends Dialog {
	private Image image_table_bg,image_wati_icon,image_wait_tz,image_wait_yz,image_wait_vs;
	
	  Label faqi[] = new Label[5];
	 Label yingzhan[] = new Label[5];
	 PKUser users[]=null;
	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public WaitDia(Shell parentShell) {
		super(parentShell);
		setWindowManager(JfaceWindowManager.wm);
		image_table_bg=new Image(Display.getDefault(),"green.jpg");
		image_wati_icon=new Image(Display.getDefault(),"wait_icon.png");
		image_wait_tz=new Image(Display.getDefault(),"wait_tz.png");
		image_wait_yz=new Image(Display.getDefault(),"wait_yz.png");
		image_wait_vs=new Image(Display.getDefault(),"vs.png");
		
		
	}
	public WaitDia(Shell parentShell,PKUser users[]) {
		super(parentShell);
		this.users=users;
		setWindowManager(JfaceWindowManager.wm);
		image_table_bg=new Image(Display.getDefault(),"green.jpg");
		image_wati_icon=new Image(Display.getDefault(),"wait_icon.png");
		image_wait_tz=new Image(Display.getDefault(),"wait_tz.png");
		image_wait_yz=new Image(Display.getDefault(),"wait_yz.png");
		image_wait_vs=new Image(Display.getDefault(),"vs.png");
		
		
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
//					faqi[users[i].seatID].setText(users[i].name);
					faqi[users[i].seatID].setVisible(true);
				} else {
//					yingzhan[users[i].seatID].setText(users[i].name);
					yingzhan[users[i].seatID].setVisible(true);
				}
			}
		}
	}

	public void leaveLables(int camp, int seatID) {
		if (camp == 1) {
//			faqi[seatID].setText("空位");
			faqi[seatID].setVisible(false);
			
		} else {
//			yingzhan[seatID].setText("空位");
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

		Label label1 = new Label(container, SWT.NONE);
		label1.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		label1.setBounds(25, 10, 26, 13);
		label1.setText("标题");
		
		Label label = new Label(container, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		label.setText("地图");
		label.setBounds(25, 41, 26, 13);
		
		Label label_1 = new Label(container, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		label_1.setText("对局人数");
		label_1.setBounds(25, 72, 52, 13);
		
		Label label_2 = new Label(container, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		label_2.setText("距离可退出游戏时间还剩");
		label_2.setBounds(558, 46, 143, 13);
		
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
		composite_1.setBounds(299, 35, 664, 157);
		
		faqi[0] = new Label(composite_1, SWT.NONE);
		faqi[0] .setLocation(32, 20);
		faqi[0] .setSize(94, 116);
		faqi[0] .setImage(image_wati_icon);
		
		faqi[1]  = new Label(composite_1, SWT.NONE);
		faqi[1].setLocation(158, 20);
		faqi[1].setSize(94, 116);
		faqi[1].setImage(image_wati_icon);
		
		faqi[2] = new Label(composite_1, SWT.NONE);
		faqi[2].setLocation(284, 20);
		faqi[2].setSize(94, 116);
		faqi[2].setImage(image_wati_icon);
		
		
		faqi[3]= new Label(composite_1, SWT.NONE);
		faqi[3].setLocation(410, 20);
		faqi[3].setSize(94, 116);
		faqi[3].setImage(image_wati_icon);
		
		faqi[4] = new Label(composite_1, SWT.NONE);
		faqi[4].setLocation(536, 20);
		faqi[4].setSize(94, 116);
		faqi[4].setImage(image_wati_icon);
		
		Label lblNewLabel_3 = new Label(composite, SWT.NONE);
		lblNewLabel_3.setBounds(589, 241, 94, 74);
		lblNewLabel_3.setImage(image_wait_vs);
		
		Composite composite_2 = new Composite(composite, SWT.NONE);
		composite_2.setBounds(299, 361, 664, 157);
		
		yingzhan[0] = new Label(composite_2, SWT.NONE);
		yingzhan[0].setBounds(32, 20, 94, 116);
		yingzhan[0].setImage(image_wati_icon);
		yingzhan[1] = new Label(composite_2, SWT.NONE);
		yingzhan[1].setBounds(158, 20, 94, 116);
		yingzhan[1].setImage(image_wati_icon);
		yingzhan[2] = new Label(composite_2, SWT.NONE);
		yingzhan[2].setBounds(284, 20, 94, 116);
		yingzhan[2].setImage(image_wati_icon);
		yingzhan[3]= new Label(composite_2, SWT.NONE);
		yingzhan[3].setBounds(410, 20, 94, 116);
		yingzhan[3].setImage(image_wati_icon);
		yingzhan[4] = new Label(composite_2, SWT.NONE);
		yingzhan[4].setBounds(536, 20, 94, 116);
		yingzhan[4].setImage(image_wati_icon);
		
		for (int i = 0; i < faqi.length; i++) {
		yingzhan[i].setText("");
		yingzhan[i].setVisible(false);
		faqi[i].setText("");
		faqi[i].setVisible(false);
		
	}
		
		
	

		for (int i = 0; i < PKUser.type; i++) {
			faqi[i].setVisible(false);
			yingzhan[i].setVisible(false);
		}
		createDialogAreaDoSomeThing(container);
		if(users!=null)
		RefreshLables(users);
		return container;
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
