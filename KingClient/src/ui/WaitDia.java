package ui;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import object.JfaceWindowManager;
import object.PKUser;
import object.State;
import object.TaskScheduled;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.wb.swt.SWTResourceManager;
import org.json.JSONObject;

import send.ForceLeavePKMessage1007;
import send.NormalLeavePKMessage1006;

import com.zjd.universal.net.GameClient;

public abstract class WaitDia extends Dialog {
	private Image image_table_bg, image_tx_icon, image_wait_tz, image_wait_yz,
			image_wait_vs, iamge_copy, image_blank;
	Label startGame, endGame, shensu, zhongcai;
	Label faqi[] = new Label[5];
	Label faqiName[] = new Label[5];
	Label faqiNameCopy[] = new Label[5];
	Label yingzhan[] = new Label[5];
	Label yingzhanName[] = new Label[5];
	Label yingzhanNameCopy[] = new Label[5];
	PKUser users[] = null;
	int curTime;
	Label time;
	int type, point;
	String title, map, area;
	Label label_2;
	Label faqiShadow[] = new Label[5];
	Label yingzhanShadow[] = new Label[5];
	Image image_start_game, image_end_game, image_shensu, image_zhongcai;

	// Image iamge_bg_head;
	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 * @wbp.parser.constructor
	 */
	public WaitDia(Shell parentShell, int curTime, int type, String area,
			String title, int point, String map) {
		super(parentShell);
		setWindowManager(JfaceWindowManager.wm);
		setShellStyle(SWT.DIALOG_TRIM | SWT.MIN);
		this.curTime = curTime;
		this.type = type;
		this.area = area;
		this.title = title;
		this.point = point;
		this.map = map;
		image_start_game = new Image(Display.getDefault(), "bt_ks.png");
		image_end_game = new Image(Display.getDefault(), "bt_js.png");
		image_table_bg = new Image(Display.getDefault(), "wait_dia_bg.jpg");
		image_tx_icon = new Image(Display.getDefault(), "tx.png");
		image_wait_tz = new Image(Display.getDefault(), "wait_tz.png");
		image_wait_yz = new Image(Display.getDefault(), "wait_yz.png");
		image_wait_vs = new Image(Display.getDefault(), "vs.png");
		iamge_copy = new Image(Display.getDefault(), "copy.png");
		// iamge_bg_head=new Image(Display.getDefault(), "bg_head.png");
		image_shensu = new Image(Display.getDefault(), "shensu.png");
		image_zhongcai = new Image(Display.getDefault(), "zhongcai.png");
		image_blank = new Image(Display.getDefault(), "blank.png");
	}

	public void myClose() {
		if (State.CurState == State.STATE_GAME_EXCEPTION_EXIT)// 房主退出的时候，加入者直接关掉定时器
		{
			curTime = 0;
			TaskScheduled.clear();
			State.CurState = State.STATE_GAME_NULL;
		}
		this.close();
	}

	public WaitDia(Shell parentShell, PKUser users[], int curTime, int type,
			String area, String title, int point, String map) {
		super(parentShell);
		setWindowManager(JfaceWindowManager.wm);
		setShellStyle(SWT.DIALOG_TRIM | SWT.MIN);
		this.users = users;
		this.curTime = curTime;
		this.type = type;
		this.area = area;
		this.title = title;
		this.point = point;
		this.map = map;
		image_end_game = new Image(Display.getDefault(), "bt_js.png");
		image_table_bg = new Image(Display.getDefault(), "wait_dia_bg.jpg");
		image_tx_icon = new Image(Display.getDefault(), "tx.png");
		image_wait_tz = new Image(Display.getDefault(), "wait_tz.png");
		image_wait_yz = new Image(Display.getDefault(), "wait_yz.png");
		image_wait_vs = new Image(Display.getDefault(), "vs.png");
		iamge_copy = new Image(Display.getDefault(), "copy.png");
		image_blank = new Image(Display.getDefault(), "blank.png");
	}

	@Override
	protected void handleShellCloseEvent() {
		if (curTime > 1) {
			MessageBox mb = new MessageBox(getParentShell(),
					SWT.ICON_INFORMATION | SWT.OK | SWT.CANCEL);
			mb.setMessage("现在还未到游戏可退出时间，如果强行退出，您会被扣除" + point + "积分");//
			int rc = mb.open();
			if (rc == SWT.OK) {
				GameClient.getInstance().sendMessageToGameServer(
						new ForceLeavePKMessage1007());
				System.out
						.println("handleShellCloseEvent发送1007" + KingLogin.id);
				for (Window window : JfaceWindowManager.wm.getWindows()) {
					if (window instanceof KingMain) {

						KingMain main = (KingMain) window;
						main.tableEnable();
					}
				}
				curTime = 0;
				TaskScheduled.clear();

				super.handleShellCloseEvent();
			} else if (rc == SWT.CANCEL) {
				return;
			}
		} else {
			MessageBox mb = new MessageBox(getParentShell(),
					SWT.ICON_INFORMATION | SWT.OK | SWT.CANCEL);
			mb.setMessage("确定要关闭吗?");//
			int rc = mb.open();
			if (rc == SWT.OK) {
				System.out
						.println("handleShellCloseEvent发送1006" + KingLogin.id);
				GameClient.getInstance().sendMessageToGameServer(
						new NormalLeavePKMessage1006());
				for (Window window : JfaceWindowManager.wm.getWindows()) {
					if (window instanceof KingMain) {
						KingMain main = (KingMain) window;
						main.tableEnable();
					}
				}
				curTime = 0;
				TaskScheduled.clear();
				super.handleShellCloseEvent();
			} else {

			}
		}

	}

	abstract void createDialogAreaDoSomeThing(Composite container);

	// abstract void openBefore();
	// @Override
	// protected void constrainShellSize() {
	// super.constrainShellSize();
	// }
	public void RefreshLables(PKUser[] users) {
		for (int i = 0; i < users.length; i++) {
			if (users[i] != null) {
				if (users[i].camp == 1) {
					faqiName[users[i].seatID].setText(users[i].roleName);
					faqiName[users[i].seatID].pack();
					faqi[users[i].seatID].setVisible(true);
					faqiNameCopy[users[i].seatID].setVisible(true);
					faqiShadow[users[i].seatID].setVisible(false);
				} else {
					yingzhanName[users[i].seatID].setText(users[i].roleName);
					yingzhanName[users[i].seatID].pack();
					yingzhan[users[i].seatID].setVisible(true);
					yingzhanNameCopy[users[i].seatID].setVisible(true);
					yingzhanShadow[users[i].seatID].setVisible(false);
				}
			}
		}
	}

	public void leaveLables(int camp, int seatID) {
		if (camp == 1) {
			faqiName[seatID].setText("");
			faqi[seatID].setVisible(false);
			faqiNameCopy[seatID].setVisible(false);
			faqiShadow[seatID].setVisible(true);
		} else {
			yingzhanName[seatID].setText("");
			yingzhan[seatID].setVisible(false);
			yingzhanNameCopy[seatID].setVisible(false);
			yingzhanShadow[seatID].setVisible(true);
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
		parent.setBackgroundMode(SWT.INHERIT_DEFAULT);
		parent.setBackgroundImage(image_table_bg);

		Label wait_tz = new Label(container, SWT.NONE);
		wait_tz.setBounds(147, 55, 162, 69);
		wait_tz.setImage(image_wait_tz);
		Label wait_yz = new Label(container, SWT.NONE);
		wait_yz.setBounds(720, 55, 162, 69);
		wait_yz.setImage(image_wait_yz);
		Composite faqi_group = new Composite(container, SWT.NONE);
		faqi_group.setBounds(100, 130, 200, 580);

		for (int i = 0; i < 5; i++) {
			faqiShadow[i] = new Label(faqi_group, SWT.NONE);
			faqiShadow[i].setBounds(0, 25 + 111 * i, 200, 86);
			faqiShadow[i].setImage(image_blank);
		}

		Composite item_faqi_group = new Composite(faqi_group, SWT.NONE);
		item_faqi_group.setBounds(0, 25, 200, 86);
		createItem(item_faqi_group, 0, true);

		item_faqi_group = new Composite(faqi_group, SWT.NONE);
		item_faqi_group.setBounds(0, 136, 200, 86);
		item_faqi_group.setBackgroundImage(image_blank);
		createItem(item_faqi_group, 1, true);
		item_faqi_group = new Composite(faqi_group, SWT.NONE);
		item_faqi_group.setBounds(0, 247, 200, 86);
		item_faqi_group.setBackgroundImage(image_blank);
		createItem(item_faqi_group, 2, true);
		item_faqi_group = new Composite(faqi_group, SWT.NONE);
		item_faqi_group.setBounds(0, 358, 200, 86);
		item_faqi_group.setBackgroundImage(image_blank);
		createItem(item_faqi_group, 3, true);
		item_faqi_group = new Composite(faqi_group, SWT.NONE);
		item_faqi_group.setBounds(0, 469, 200, 86);
		item_faqi_group.setBackgroundImage(image_blank);
		createItem(item_faqi_group, 4, true);

		Label wait_vs = new Label(container, SWT.NONE);
		wait_vs.setBounds(459, 315, 100, 80);
		wait_vs.setImage(image_wait_vs);

		Composite yingzhan_group = new Composite(container, SWT.NONE);
		yingzhan_group.setBounds(724, 130, 200, 580);

		for (int i = 0; i < 5; i++) {
			yingzhanShadow[i] = new Label(yingzhan_group, SWT.NONE);
			yingzhanShadow[i].setBounds(0, 25 + 111 * i, 200, 86);
			yingzhanShadow[i].setImage(image_blank);
		}
		Composite item_yingzhan_group = new Composite(yingzhan_group, SWT.NONE);
		item_yingzhan_group.setBounds(0, 25, 200, 86);
		createItem(item_yingzhan_group, 0, false);
		item_yingzhan_group = new Composite(yingzhan_group, SWT.NONE);
		item_yingzhan_group.setBounds(0, 136, 200, 86);
		createItem(item_yingzhan_group, 1, false);
		item_yingzhan_group = new Composite(yingzhan_group, SWT.NONE);
		item_yingzhan_group.setBounds(0, 247, 200, 86);
		createItem(item_yingzhan_group, 2, false);
		item_yingzhan_group = new Composite(yingzhan_group, SWT.NONE);
		item_yingzhan_group.setBounds(0, 358, 200, 86);
		createItem(item_yingzhan_group, 3, false);
		item_yingzhan_group = new Composite(yingzhan_group, SWT.NONE);
		item_yingzhan_group.setBounds(0, 469, 200, 86);
		createItem(item_yingzhan_group, 4, false);

		for (int i = 0; i < yingzhanName.length; i++) {
			faqi[i].setVisible(false);
			yingzhan[i].setVisible(false);
			yingzhanNameCopy[i].setVisible(false);
			faqiNameCopy[i].setVisible(false);
		}
		startGame = new Label(container, SWT.NONE);
		startGame.setBounds(413, 567, 191, 61);
		startGame.setImage(image_start_game);
		endGame = new Label(container, SWT.NONE);
		endGame.setBounds(413, 635, 191, 61);
		endGame.setImage(image_end_game);
		startGame.setEnabled(false);
		endGame.setEnabled(false);

		shensu = new Label(container, SWT.NONE);
		shensu.setBounds(614, 669, 80, 27);
		shensu.setImage(image_shensu);
		shensu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
			}
		});
		zhongcai = new Label(container, SWT.NONE);
		zhongcai.setBounds(614, 634, 80, 27);
		zhongcai.setImage(image_zhongcai);
		zhongcai.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
			}
		});
		createDialogAreaDoSomeThing(container);

		Composite composite_titlebar = new Composite(container, SWT.NONE);
		composite_titlebar.setBounds(359, 130, 300, 115);
		// composite_titlebar.setBackgroundImage(iamge_bg_head);

		label_1 = new Label(composite_titlebar, SWT.NONE);
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_1.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		label_1.setText("挑战点数：");
		label_1.setBounds(32, 7, 26, 13);
		label_1.pack();
		Label areaLabel = new Label(composite_titlebar, SWT.NONE);
		areaLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		areaLabel.setText(point + "");
		areaLabel.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		areaLabel.setBounds(169, 7, 61, 17);
		areaLabel.pack();

		Label label = new Label(composite_titlebar, SWT.NONE);
		label.setText("游戏区服：");
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		label.setBounds(32, 34, 105, 21);

		Label label_3 = new Label(composite_titlebar, SWT.NONE);
		label_3.setText(area);
		label_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		label_3.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		label_3.setBounds(169, 34, 99, 21);

		Label label_4 = new Label(composite_titlebar, SWT.NONE);
		label_4.setText("挑战地图：");
		label_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_4.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		label_4.setBounds(32, 61, 105, 21);

		Label label_5 = new Label(composite_titlebar, SWT.NONE);
		label_5.setText(map);
		label_5.setForeground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		label_5.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		label_5.setBounds(169, 61, 99, 21);

	
		// Label typeLabel = new Label(composite_titlebar, SWT.CENTER);
		// typeLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		// typeLabel.setText(type + "v" + type);
		// typeLabel.setFont(SWTResourceManager.getFont("宋体", 11, SWT.NORMAL));
		// typeLabel.setBounds(146, 5, 61, 17);
		label_2 = new Label(container, SWT.NONE);
		label_2.setBounds(415, 456, 156, 13);
		label_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_2.setFont(SWTResourceManager.getFont("宋体", 13, SWT.NORMAL));
		label_2.setText("距可退出游戏时间还剩：");
		label_2.setVisible(false);
		label_2.pack();
		time = new Label(container, SWT.NONE);
		time.setBounds(460, 491, 202, 17);
		time.setForeground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		time.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setBounds(409, 438, 200, 98);
		lblNewLabel.setImage(image_blank);
		if (users != null) {
			RefreshLables(users);
		}
		TaskScheduled.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				Display.getDefault().asyncExec(new Runnable() {

					@Override
					public void run() {
						showTime();
						// WaitDia waitDia = null;
						// for (Window window :
						// JfaceWindowManager.wm.getWindows()) {
						// if (window instanceof WaitDia) {
						// waitDia = (WaitDia) window;
						// }
						// }
						// if(waitDia==null){return;}
						// waitDia.showTime();
					}
				});

			}
		}, 0, 1, TimeUnit.SECONDS);
		return container;
	}

	public void hideTime() {
		curTime = 0;
		TaskScheduled.clear();
		time.setText("");
		label_2.setVisible(false);
	}

	public void showTime() {
		label_2.setVisible(true);
		String min = curTime / 60 + "分:";
		String sec = curTime % 60 + "秒";
		time.setText(min + sec);
		time.pack();
		curTime--;
		if (curTime < 0) {
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
		// Button button = createButton(parent, IDialogConstants.FINISH_ID,
		// IDialogConstants.OK_LABEL, true);
		// button.addSelectionListener(new SelectionAdapter() {
		// @Override
		// public void widgetSelected(SelectionEvent e) {
		// try {
		// DateTimeDia.timeMillis=0;
		// } catch (Exception e1) {
		// e1.printStackTrace();
		// }
		// }
		// });
		// button.setText("确定");
		// Button button_1 = createButton(parent, IDialogConstants.CANCEL_ID,
		// IDialogConstants.CANCEL_LABEL, false);
		// button_1.setText("取消");
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(1024, 768);
	}

	private void createItem(Composite item_group, int index, boolean isFaQi) {

		Label headIcon = new Label(item_group, SWT.NONE);
		if (isFaQi)
			faqi[index] = headIcon;
		else
			yingzhan[index] = headIcon;
		headIcon.setImage(image_tx_icon);
		headIcon.setBounds(10, 0, 93, 86);

		Label name = new Label(item_group, SWT.CENTER);
		if (isFaQi)
			faqiName[index] = name;
		else
			yingzhanName[index] = name;
		name.setBounds(109, 11, 91, 30);
		name.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		name.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		Label copy = new Label(item_group, SWT.NONE);
		if (isFaQi)
			faqiNameCopy[index] = copy;
		else
			yingzhanNameCopy[index] = copy;

		copy.setBounds(109, 52, 64, 22);
		copy.setImage(iamge_copy);
		copy.setData("index", index);
		copy.setData("isFaQi", isFaQi);
		copy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				final Clipboard cb = new Clipboard(getParentShell()
						.getDisplay());
				int index = (int) ((Widget) e.getSource()).getData("index");
				boolean isFaQi = (boolean) ((Widget) e.getSource())
						.getData("isFaQi");
				System.out.println("name:" + index);
				String str = null;
				if (isFaQi) {
					str = faqiName[index].getText();
				} else {
					str = yingzhanName[index].getText();
				}
				TextTransfer textTransfer = TextTransfer.getInstance();
				cb.setContents(new Object[] { str },
						new Transfer[] { textTransfer });
				MessageBox messageBox = new MessageBox(getParentShell(), SWT.OK);
				messageBox.setMessage("复制成功，请直接Ctrl+V或者鼠标右键选择粘贴");
				messageBox.open();
			}
		});
	}

	ArrayList<JSONObject> list = new ArrayList<JSONObject>();
	ArrayList<Control> listControl = new ArrayList<Control>();
	private Label label_1;
}
