package ui;

import java.util.ArrayList;

import object.JfaceWindowManager;
import object.PK;
import object.PKManager;
import object.PKUser;
import object.State;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.wb.swt.SWTResourceManager;

import com.zjd.universal.net.GameClient;

public class KingMain extends ApplicationWindow {
	private Table table;

	private Button btn_create_tz;
	private Image image[] = new Image[5];
	// private TabItem tabItem[] = new TabItem[5];
	Composite[] tabItem = new Composite[5];
	// private String tabItemText[] = { "对战信息", "个人设置", "挑战记录", "充值管理",
	// "礼品兑换" };
	private String urlText[] = { "http://www.hexcm.com/yxlm/setting.php",
			"http://www.hexcm.com/yxlm/setting.php",
			"http://www.hexcm.com/yxlm/lszj.php",
			"http://www.hexcm.com/yxlm/cz.php",
			"http://www.hexcm.com/yxlm/dj.php" };

	private Image image_join_tz, image_join_yz, image_create_tz, image_query,
			image_table_bg, tab_bg;
	private Image image_pk_flow,image_zhogncai_way,image_pk_point_count;
private String curMap,curArea;
private Image icon;
	/**
	 * Create the application window.
	 */
	public KingMain() {

		super(null);
		setWindowManager(JfaceWindowManager.wm);
		icon = new Image(Display.getDefault(), "1.jpg");
		// createActions();
		// addStatusLine();
		for (int i = 0; i < 5; i++) {
			image[i] = new Image(Display.getDefault(), "tab" + i + ".png");
		}
		image_join_tz = new Image(Display.getDefault(), "join_tz.jpg");
		image_join_yz = new Image(Display.getDefault(), "join_yz.jpg");
		image_create_tz = new Image(Display.getDefault(), "create_tz.png");
		image_query = new Image(Display.getDefault(), "query.png");
		image_table_bg = new Image(Display.getDefault(), "green.jpg");
		tab_bg = new Image(Display.getDefault(), "tab_bg.png");
		image_pk_flow=new Image(Display.getDefault(),"pk_flow.png");
		image_zhogncai_way=new Image(Display.getDefault(),"zhongcai_way.png");
		image_pk_point_count=new Image(Display.getDefault(),"pk_point_count.png");
		setShellStyle(SWT.CLOSE | SWT.MIN | SWT.TITLE);
	}

	public void PKCreateSuccess(String id, int type, String area, String title,
			int point) {
		if (id.equals(KingLogin.id)) {
			PKUser.type = type;
			MessageBox mb = new MessageBox(KingMain.this.getShell(),
					SWT.ICON_INFORMATION | SWT.OK);
			mb.setMessage("挑战发起成功，作为发起人，你现在不能加入其他挑战房间！");
			mb.open();
			tableDisable();

			btn_create_tz.setEnabled(false);

			CreatePKWaitDia waitDia = new CreatePKWaitDia(getShell(), type,
					area, title, point);
			waitDia.open();
		}
	}

	public void PKJoinSuccess(String roleName, int type, PKUser users[],
			String area, String title, int point) {
		if (roleName.equals(KingLogin.roleName)) {
			PKUser.type = type;
			MessageBox mb = new MessageBox(KingMain.this.getShell(),
					SWT.ICON_INFORMATION | SWT.OK);
			mb.setMessage("加入成功，作为加入人，你现在不能加入其他挑战房间！");
			mb.open();
			tableDisable();
			btn_create_tz.setEnabled(false);
			JoinPKWaitDia waitDia = new JoinPKWaitDia(getShell(), users, type,
					area, title, point);
			waitDia.open();
		}
	}

	public void tableDisable() {
		for (int i = 0; i < table.getChildren().length; i++) {
			if (table.getChildren()[i] instanceof Button) {
				table.getChildren()[i].setEnabled(false);
			}
		}
	}

	public void tableEnable() {
		for (int i = 0; i < table.getChildren().length; i++) {
			if (table.getChildren()[i] instanceof Button) {
				table.getChildren()[i].setEnabled(true);
			}
		}
	}

	protected boolean showTopSeperator() {
		return false;
	}

	/**
	 * Create contents of the application window.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createContents(final Composite parent) {
		parent.setLayout(null);
		// System.out.println(getInitialSize().x+","+getInitialSize().y+","+
		// tab_bg.getBounds().height);

		// RowLayout r=new RowLayout(SWT.VERTICAL);
		// r.spacing=0;
		// r.fill=true;
		// r.marginWidth=0;
		// r.marginLeft=0;
		// r.marginHeight=0;
		// r.marginTop=0;
		// parent.setLayout(r);
		// parent.setLayout(new abso)
		parent.setBackgroundMode(SWT.INHERIT_DEFAULT);

		Composite composite = new Composite(parent, SWT.NONE);
		// 设置容器框大小
		composite
				.setBounds(0, 0, getInitialSize().x, tab_bg.getBounds().height);
		// 设置布局
		// composite.setLayoutData(new RowData(getInitialSize().x,
		// tab_bg.getBounds().height));
		// RowLayout r1=new RowLayout(SWT.HORIZONTAL);
		// r1.spacing=110;
		// composite.setLayout(r1);

		composite.setBackgroundImage(tab_bg);

		// 设置容器里的控件
		for (int i = 0; i < 5; i++) {
			Label label = new Label(composite, SWT.NONE);
			label.setImage(image[i]);
			label.setBounds(10 + i * (image[i].getBounds().width + 50), 0,
					image[i].getBounds().width, image[i].getBounds().height);
			label.setData(i);
			label.addMouseListener(new MouseListener() {

				@Override
				public void mouseUp(MouseEvent arg0) {

				}

				@Override
				public void mouseDown(MouseEvent arg0) {
					Widget d = (Widget) arg0.getSource();
					int index = (int) d.getData();
					for (int i = 0; i < 5; i++) {
						tabItem[i].setVisible(false);
					}
					tabItem[index].setVisible(true);
					System.out.println("index" + index);
				}

				@Override
				public void mouseDoubleClick(MouseEvent arg0) {

				}
			});
		}

		for (int i = 0; i < 5; i++) {
			if (i == 0) {
				tabItem[i] = getTabControlOne(parent);
			} else {
				tabItem[i] = getTabContrlOther(parent, urlText[i]+"?uid="+ PKUser.uid);
			}

			tabItem[i].setBackground(SWTResourceManager
					.getColor(SWT.COLOR_CYAN));
			tabItem[i].setBounds(0, tab_bg.getBounds().height,
					getInitialSize().x, getInitialSize().y-tab_bg.getBounds().height);
			// tabItem[i].setLayoutData(new RowData(getInitialSize().x,
			// parent.getClientArea().height-tab_bg.getBounds().height));
			tabItem[i].setVisible(false);
		}
		tabItem[0].setVisible(true);

		// Composite composite = new Composite(container, SWT.NONE);
		// composite.setBounds(0, 0, 64, parent.get());
		// final TabFolder tabFolder = new TabFolder(parent, SWT.None);
		// tabFolder.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		// tabFolder.setBounds(10, 0, 881, 649);
		// tabFolder.setVisible(false);
		// for (int i = 0; i < 5; i++) {
		// tabItem[i] = new TabItem(tabFolder, SWT.NONE);
		// tabItem[i].setImage(image[i]);
		//
		// if (i == 0) {
		// tabItem[i].setControl(getTabControlOne(tabFolder));
		// } else {
		// tabItem[i].setControl(getTabContrlOther(tabFolder, urlText[i]
		// + "?uid=" + PKUser.uid));
		// }
		// }
		//
		// tabFolder.setSelection(0);
		// tabFolder.addSelectionListener(new SelectionAdapter() {
		// public void widgetSelected(
		// org.eclipse.swt.events.SelectionEvent event) {
		// System.out.println(tabFolder.getSelection()[0].getText()
		// + " selected");
		// }
		// });

		 GameClient.getInstance().onCreate();
		 GameClient.getInstance().connectGameServer(GameClient.GAME_IP,
		 GameClient.GAME_PORT);
		return parent;
	}

	private Composite getTabContrlOther(Composite parent, String url) {
		Composite container = new Composite(parent, SWT.NONE);
		Browser browser = new Browser(container, SWT.BORDER);
		browser.setUrl(url);
		browser.setBounds(0, 0, 1020, 768);
		// GridData layoutData = new GridData(GridData.FILL_BOTH);
		// layoutData.horizontalSpan = 2;
		// layoutData.verticalSpan = 2;
		// browser.setLayoutData(tabFolder.getLayoutData());
		// browser.setBounds(5,30,1024,768);
		browser.setJavascriptEnabled(true);
		return container;
	}

	/**
	 * Gets the control for tab one
	 * 
	 * @param tabFolder
	 *            the parent tab folder
	 * @return Control
	 */
	private Composite getTabControlOne(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setFont(SWTResourceManager.getFont("宋体", 15, SWT.NORMAL));
		table = new Table(container, SWT.BORDER | SWT.MULTI);
		table.setLinesVisible(false);
		table.setHeaderVisible(true);
		table.setBounds(2, 64, 1024-289, 587);
		table.setFont(SWTResourceManager.getFont("宋体", 15, SWT.NORMAL));
		TableColumn column = new TableColumn(table, SWT.CENTER);
		column.setWidth(115);
		column.setText("房主");
		column = new TableColumn(table, SWT.CENTER);
		column.setWidth(110);
		column.setText("标题");
		column = new TableColumn(table, SWT.CENTER);
		column.setWidth(160);
		column.setText("游戏区");
		column = new TableColumn(table, SWT.CENTER);
		column.setWidth(130);
		column.setText("挑战图");
		column = new TableColumn(table, SWT.CENTER);// 挑战方
		column.setWidth(108);
		column.setText("挑战方");
		column = new TableColumn(table, SWT.CENTER);// 应战方
		column.setWidth(108);
		column.setText("应战方");
	
		column = new TableColumn(table, SWT.CENTER);
		column.setWidth(110);
		column.setText("对战人数");
		column = new TableColumn(table, SWT.CENTER);
		column.setWidth(120);
		column.setText("挑战点");
		column = new TableColumn(table, SWT.CENTER);
		column.setWidth(220);
		column.setText("当前人数（挑-应）");
	
		
		//发起挑战按钮
		btn_create_tz = new Button(container, SWT.CENTER);
		btn_create_tz.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				CrateDiaRoleName pkDia = new CrateDiaRoleName(KingMain.this
						.getShell());
				pkDia.open();
			}
		});
		btn_create_tz.setBounds(287, 11, 137, 40);
		btn_create_tz.setImage(image_create_tz);

//		Button query = new Button(container, SWT.CENTER);
//		query.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				QueryDia pkDia = new QueryDia(TestDemo.this.getShell());
//				pkDia.open();
//			}
//		});
//		query.setImage(image_query);
//		query.setBounds(476, 11, 193, 38);
		
		Button pk_flow = new Button(container, SWT.CENTER);
		pk_flow.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				UrlDia dia=new UrlDia(KingMain.this.getShell(),"http://www.hexcm.com/yxlm/single/lc.html");
				dia.open();
			}
		});
		pk_flow.setImage(image_pk_flow);
		pk_flow.setBounds(430, 11, 137, 40);
		
		Button zhogncai_way = new Button(container, SWT.CENTER);
		zhogncai_way.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				UrlDia dia=new UrlDia(KingMain.this.getShell(),"http://www.hexcm.com/yxlm/single/zc.html");
				dia.open();
			}
		});
		zhogncai_way.setImage(image_zhogncai_way);
		zhogncai_way.setBounds(576, 11, 137, 40);
		
		Button pk_point_count = new Button(container, SWT.CENTER);
		pk_point_count.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				UrlDia dia=new UrlDia(KingMain.this.getShell(),"http://www.hexcm.com/yxlm/single/ds.html");
				dia.open();
			}
		});
		pk_point_count.setImage(image_pk_point_count);
		pk_point_count.setBounds(716, 11, 137, 40);
		
		Browser browser = new Browser(container, SWT.BORDER);
		browser.setBounds(1024-285, 64, 278, 587);
		browser.setUrl("http://www.hexcm.com/yxlm/index_right.php?uid=" + PKUser.uid);
		browser.setJavascriptEnabled(true);
		Combo area = new Combo(container, SWT.NONE);
		area.setBounds(10, 20, 84, 21);
		area.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		area.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
			
				curArea=((Combo)arg0.getSource()).getText();
				if(curArea.equals("选择区服")){
					curArea=null;
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				curArea=((Combo)arg0.getSource()).getText();
				if(curArea.equals("选择区服")){
					curArea=null;
				}
				
			}
		});
		area.add("选择区服");
		area.add("艾欧尼亚(电信)");
		area.add("祖安(电信)");
		area.add("诺克萨斯(电信)");
		area.add("班德尔城(电信)");
		area.add("皮尔特沃夫(电信)");
		area.add("战争学院(电信)");
		area.add("巨神峰(电信)");
		area.add("雷瑟守备(电信)");
		area.add("钢铁烈阳(电信)");
		area.add("裁决之地(电信)");
		area.add("黑色玫瑰(电信)");
		area.add("暗影岛(电信)");
		area.add("均衡教派(电信)");
		area.add("水晶之痕(电信)");
		area.add("影流(电信)");
		area.add("守望之海(电信)");
		area.add("征服之海(电信)");
		area.add("卡拉曼达(电信)");
		area.add("皮城警备(电信)");

		area.add("比尔吉沃特(网通)");
		area.add("德玛西亚(网通)");
		area.add("费雷尔卓德(网通)");
		area.add("无畏先锋(网通)");
		area.add("怒瑞玛(网通)");
		area.add("扭曲丛林(网通)");
		area.setText("选择区服");
		
		Combo map = new Combo(container, SWT.NONE);
		map.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		map.setBounds(111, 20, 84, 21);
		map.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				curMap=((Combo)arg0.getSource()).getText();
				if(curMap.equals("选择地图")){
					curMap=null;
				}
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				curMap=((Combo)arg0.getSource()).getText();
				if(curMap.equals("选择地图")){
					curMap=null;
				}
			}
		});
		map.add("选择地图");
		map.add("扭曲丛林");
		map.add("召唤师峡谷");
		map.setText("选择地图");
		
		Button btnNewButton = new Button(container, SWT.NONE);
		btnNewButton.setBounds(201, 16, 80, 27);
		btnNewButton.setText("筛选");
		btnNewButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
			
				RefreshTableFilter(curMap, curArea);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				RefreshTableFilter(curMap, curArea);
				
			}
		});

		return container;
	}

	@Override
	protected void handleShellCloseEvent() {
		MessageBox mb = new MessageBox(KingMain.this.getShell(),
				SWT.ICON_INFORMATION | SWT.OK | SWT.CANCEL);
		mb.setMessage("确定要关闭吗?");//
		int rc = mb.open();
		if (rc == SWT.OK) {
			super.handleShellCloseEvent();
		} else if (rc == SWT.CANCEL) {
			return;
		}

	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Create the menu manager.
	 * 
	 * @return the menu manager
	 */
	@Override
	protected MenuManager createMenuManager() {
		MenuManager menuManager = new MenuManager("menu");
		return menuManager;
	}

	/**
	 * Create the toolbar manager.
	 * 
	 * @return the toolbar manager
	 */
	@Override
	protected ToolBarManager createToolBarManager(int style) {
		ToolBarManager toolBarManager = new ToolBarManager(style);
		return toolBarManager;
	}

	/**
	 * Create the status line manager.
	 * 
	 * @return the status line manager
	 */
	@Override
	protected StatusLineManager createStatusLineManager() {
		StatusLineManager statusLineManager = new StatusLineManager();
		return statusLineManager;
	}

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		// try {
		// KingMain window = new KingMain();
		// window.setBlockOnOpen(true);
		// window.open();
		// Display.getCurrent().dispose();
		// System.exit(0);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}

	/**
	 * Configure the shell.
	 * 
	 * @param newShell
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("\u6211\u662F\u738B\u8005" + "----id:" + KingLogin.id);
		newShell.setImage(icon);

	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(1024, 768);
	}

	public void PopErrorCreateMessage() {
		MessageBox mb = new MessageBox(KingMain.this.getShell(),
				SWT.ICON_INFORMATION | SWT.OK);
		mb.setMessage("创建房间失败");//
		mb.open();
	}

	public void PopErrorJoinMessage(String roleName, int status) {
		if (roleName.equals(KingLogin.roleName)) {

			MessageBox mb = new MessageBox(KingMain.this.getShell(),
					SWT.ICON_INFORMATION | SWT.OK);
			if (status == -1) {
				mb.setMessage("加入房间房间失败,阵营人数已满，或房间无效");
			} else {
				mb.setMessage("加入房间房间失败,密码错误");
			}
			mb.open();
		}

	}

	public void btn_start_gameEnable() {
		if (JfaceWindowManager.getCurWindow() instanceof CreatePKWaitDia) {
			CreatePKWaitDia dia = (CreatePKWaitDia) JfaceWindowManager
					.getCurWindow();
			dia.btn_start_game.setEnabled(true);
		}
		MessageBox mb = new MessageBox(KingMain.this.getShell(),
				SWT.ICON_INFORMATION | SWT.OK);
		mb.setMessage("房间已满 你可以开始游戏了");//
		mb.open();

	}

	public void btn_start_gameDisable() {
		if (JfaceWindowManager.getCurWindow() instanceof CreatePKWaitDia) {
			CreatePKWaitDia dia = (CreatePKWaitDia) JfaceWindowManager
					.getCurWindow();
			dia.btn_start_game.setEnabled(false);
		}
		MessageBox mb = new MessageBox(KingMain.this.getShell(),
				SWT.ICON_INFORMATION | SWT.OK);
		mb.setMessage("开始挑战前，由于有玩家退出了，请继续耐心等待");//
		mb.open();

	}

	public void StartGameResult(int status, String id) {
		if (status == 0) {
			State.CurState = State.STATE_GAME_START;
			MessageBox mb = new MessageBox(KingMain.this.getShell(),
					SWT.ICON_INFORMATION | SWT.OK);
			if (id.equals(KingLogin.id)) {
				mb.setMessage("现在你有操作权限可以结束游戏了");
				if (JfaceWindowManager.getCurWindow() instanceof CreatePKWaitDia) {
					CreatePKWaitDia dia = (CreatePKWaitDia) JfaceWindowManager
							.getCurWindow();
					dia.btn_end_game.setEnabled(true);
					dia.hideTime();
				}
			} else {
				mb.setMessage("房主已经点击开始游戏了，游戏开始");
				if (JfaceWindowManager.getCurWindow() instanceof WaitDia) {
					WaitDia dia = (WaitDia) JfaceWindowManager.getCurWindow();
					dia.hideTime();
				}
			}
			mb.open();

		} else {
			MessageBox mb = new MessageBox(KingMain.this.getShell(),
					SWT.ICON_INFORMATION | SWT.OK);
			mb.setMessage("开始游戏失败");//
			mb.open();
		}

	}

	public void EndGameResult(int status) {
		if (status == 0) {
			MessageBox mb = new MessageBox(KingMain.this.getShell(),
					SWT.ICON_INFORMATION | SWT.OK);
			mb.setMessage("房主结束游戏成功，房间解散");//
			mb.open();
			if (JfaceWindowManager.getCurWindow() instanceof WaitDia) {
				WaitDia dia = (WaitDia) JfaceWindowManager.getCurWindow();
				dia.myClose();
			}
			if (JfaceWindowManager.getCurWindow() instanceof WaitDia) {
				WaitDia waitDia = (WaitDia) JfaceWindowManager.getCurWindow();
				waitDia.close();
			}

			btn_create_tz.setEnabled(true);
			State.CurState = State.STATE_GAME_END;
		} else {
			MessageBox mb = new MessageBox(KingMain.this.getShell(),
					SWT.ICON_INFORMATION | SWT.OK);
			mb.setMessage("结束游戏失败");//
			mb.open();
		}

	}

	// 房主退出
	public void HostLeave(String id) {
		if (!id.equals(KingLogin.id))// 非房主弹出此对话框
		{
			tableEnable();
			MessageBox mb = new MessageBox(KingMain.this.getShell(),
					SWT.ICON_INFORMATION | SWT.OK);
			mb.setMessage("房主退出房间，挑战解散");//
			mb.open();
		}
		//不管是不是房主这个按钮有要允许点击
		btn_create_tz.setEnabled(true);

	}
	// 房主强制退出
	public void HostCrashLeave(String id) {
		if (!id.equals(KingLogin.id))// 自己是房主 不需要弹出此对话框
		{
			tableEnable();
			MessageBox mb = new MessageBox(KingMain.this.getShell(),
					SWT.ICON_INFORMATION | SWT.OK);
			mb.setMessage("房主异常退出房间，挑战解散");//
			mb.open();
		}

	}
	ArrayList<Control> listControl = new ArrayList<Control>();

	public void RefreshTable() {

		table.clearAll();
		table.removeAll();
		for (int i = 0; i < listControl.size(); i++) {
			listControl.get(i).dispose();
		}
		table.setBackgroundMode(SWT.INHERIT_DEFAULT);
		table.setBackgroundImage(image_table_bg);
		listControl.clear();
		for (int i = 0; i < PKManager.getInstance().getPKNum(); i++) {
			TableItem item = new TableItem(table, SWT.NONE);
		}
		TableItem[] items = table.getItems();
		for (int i = 0; i < items.length; i++) {
			PK pk = PKManager.getInstance().getPKByIndex(i);
			TableEditor editor = new TableEditor(table);
			Label textName = new Label(table, SWT.CENTER);
			textName.setText(pk.id);
			textName.setFont(SWTResourceManager.getFont("宋体", 15, SWT.NORMAL));
			editor.grabHorizontal = true;
			editor.setEditor(textName, items[i], 0);
			textName.setToolTipText(pk.id);
			listControl.add(textName);

			editor = new TableEditor(table);
			Label textTitle = new Label(table, SWT.CENTER);
			textTitle.setText(pk.title);
			textTitle.setFont(SWTResourceManager.getFont("宋体", 15, SWT.NORMAL));
			editor.grabHorizontal = true;
			editor.setEditor(textTitle, items[i], 1);
			textTitle.setToolTipText(pk.title);
			listControl.add(textTitle);

			editor = new TableEditor(table);
			Label textArea = new Label(table, SWT.CENTER);
			textArea.setText(pk.area);
			textArea.setFont(SWTResourceManager.getFont("宋体", 15, SWT.NORMAL));
			editor.grabHorizontal = true;
			editor.setEditor(textArea, items[i], 2);
			textArea.setToolTipText(pk.area);
			listControl.add(textArea);

			editor = new TableEditor(table);
			Label textMap = new Label(table, SWT.CENTER);
			textMap.setText(pk.map);
			textMap.setFont(SWTResourceManager.getFont("宋体", 15, SWT.NORMAL));
			editor.grabHorizontal = true;
			editor.setEditor(textMap, items[i], 3);
			textMap.setToolTipText(pk.map);
			listControl.add(textMap);
			
			editor = new TableEditor(table);
			Button join_tz = new Button(table, SWT.CENTER);
			join_tz.setImage(image_join_tz);
			join_tz.setData(i);
			join_tz.addSelectionListener(new SelectionAdapter() {

				@Override
				public void widgetSelected(SelectionEvent e) {

					int index = (int) ((Button) e.getSource()).getData();
					PK pk = PKManager.getInstance().getPKByIndex(index);
					JoinDiaRoleName pkDia = new JoinDiaRoleName(KingMain.this
							.getShell(), index, 1, pk.password);
					pkDia.open();

				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					widgetSelected(e);
				}
			});
			editor.minimumWidth = join_tz.getSize().x;
			editor.minimumHeight = join_tz.getSize().y;
			editor.grabHorizontal = true;
			editor.setEditor(join_tz, items[i], 4);
			listControl.add(join_tz);

			editor = new TableEditor(table);
			Button join_yz = new Button(table, SWT.CENTER);
			join_yz.setImage(image_join_yz);
			join_yz.setData(i);
			join_yz.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					int index = (int) ((Button) e.getSource()).getData();
					PK pk = PKManager.getInstance().getPKByIndex(index);
					JoinDiaRoleName pkDia = new JoinDiaRoleName(KingMain.this
							.getShell(), index, 2, pk.password);
					pkDia.open();

				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					widgetSelected(e);
				}
			});
			editor.minimumWidth = join_yz.getSize().x;
			editor.minimumHeight = join_yz.getSize().y;
			editor.grabHorizontal = true;
			editor.setEditor(join_yz, items[i], 5);
			listControl.add(join_yz);
			
		

			editor = new TableEditor(table);
			Label textType = new Label(table, SWT.CENTER);
			textType.setFont(SWTResourceManager.getFont("宋体", 15, SWT.NORMAL));
			textType.setText(pk.type + "v" + pk.type);
			editor.grabHorizontal = true;
			editor.setEditor(textType, items[i], 6);
			textType.setToolTipText(pk.type + "v" + pk.type);
			listControl.add(textType);

			editor = new TableEditor(table);
			Label textPoint = new Label(table, SWT.CENTER);
			textPoint.setText(pk.point + "");
			textPoint.setFont(SWTResourceManager.getFont("宋体", 15, SWT.NORMAL));
			editor.grabHorizontal = true;
			editor.setEditor(textPoint, items[i], 7);
			textPoint.setToolTipText(pk.point + "");
			listControl.add(textPoint);

			editor = new TableEditor(table);
			Label textCurNum = new Label(table, SWT.CENTER);
			if (pk.password.equals("")) {
				textCurNum.setText(pk.faqiSeatCount + "-"
						+ +pk.yingzhanSeatCount);
			} else {
				textCurNum.setText(pk.faqiSeatCount + "-"
						+ +pk.yingzhanSeatCount + "(密)");
			}
			textCurNum
					.setFont(SWTResourceManager.getFont("宋体", 15, SWT.NORMAL));
			editor.grabHorizontal = true;
			editor.setEditor(textCurNum, items[i], 8);
			textCurNum.setToolTipText(pk.faqiSeatCount + "-"
					+ +pk.yingzhanSeatCount);
			listControl.add(textCurNum);

		
		}

	}
	//筛选刷新
	public void RefreshTableFilter(String map,String area) {

		table.clearAll();
		table.removeAll();
		for (int i = 0; i < listControl.size(); i++) {
			listControl.get(i).dispose();
		}
		table.setBackgroundMode(SWT.INHERIT_DEFAULT);
		table.setBackgroundImage(image_table_bg);
		listControl.clear();
		
		System.out.println("map:"+map+"area:"+area);
			for (int i = 0; i < PKManager.getInstance().getPKNum(); i++) {
				PK pk = PKManager.getInstance().getPKByIndex(i);
				if(map!=null&&area!=null)
				{
					if(pk.map.equals(map)&&pk.area.equals(area))
					{
						 PKManager.getInstance().addFilter(pk);
					}
				}
				if(map==null&&area!=null)
				{
					System.out.println("pk.area:"+pk.area);
					if(pk.area.equals(area))
					{
						
						 PKManager.getInstance().addFilter(pk);
					}
				}
				if(area==null&map!=null)
				{
					if(pk.map.equals(area))
					{
						System.out.println("area==null&map!=null");
						 PKManager.getInstance().addFilter(pk);
					}
				}
				if(area==null&map==null)
				{
					RefreshTable();
					return;
				}
			}	
		
		
		for (int i = 0; i < PKManager.getInstance().getFilterPKNum(); i++) {
			TableItem item = new TableItem(table, SWT.NONE);
		}
		
		
		TableItem[] items = table.getItems();
		for (int i = 0; i < items.length; i++) {
			PK pk = PKManager.getInstance().getFilterPKByIndex(i);
			TableEditor editor = new TableEditor(table);
			Label textName = new Label(table, SWT.CENTER);
			textName.setText(pk.id);
			textName.setFont(SWTResourceManager.getFont("宋体", 15, SWT.NORMAL));
			editor.grabHorizontal = true;
			editor.setEditor(textName, items[i], 0);
			textName.setToolTipText(pk.id);
			listControl.add(textName);

			editor = new TableEditor(table);
			Label textTitle = new Label(table, SWT.CENTER);
			textTitle.setText(pk.title);
			textTitle.setFont(SWTResourceManager.getFont("宋体", 15, SWT.NORMAL));
			editor.grabHorizontal = true;
			editor.setEditor(textTitle, items[i], 1);
			textTitle.setToolTipText(pk.title);
			listControl.add(textTitle);

			editor = new TableEditor(table);
			Label textArea = new Label(table, SWT.CENTER);
			textArea.setText(pk.area);
			textArea.setFont(SWTResourceManager.getFont("宋体", 15, SWT.NORMAL));
			editor.grabHorizontal = true;
			editor.setEditor(textArea, items[i], 2);
			textArea.setToolTipText(pk.area);
			listControl.add(textArea);

			editor = new TableEditor(table);
			Label textMap = new Label(table, SWT.CENTER);
			textMap.setText(pk.map);
			textMap.setFont(SWTResourceManager.getFont("宋体", 15, SWT.NORMAL));
			editor.grabHorizontal = true;
			editor.setEditor(textMap, items[i], 3);
			textMap.setToolTipText(pk.map);
			listControl.add(textMap);
			
			editor = new TableEditor(table);
			Button join_tz = new Button(table, SWT.CENTER);
			join_tz.setImage(image_join_tz);
			join_tz.setData(i);
			join_tz.addSelectionListener(new SelectionAdapter() {

				@Override
				public void widgetSelected(SelectionEvent e) {

					int index = (int) ((Button) e.getSource()).getData();
					PK pk = PKManager.getInstance().getPKByIndex(index);
					JoinDiaRoleName pkDia = new JoinDiaRoleName(KingMain.this
							.getShell(), index, 1, pk.password);
					pkDia.open();

				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					widgetSelected(e);
				}
			});
			editor.minimumWidth = join_tz.getSize().x;
			editor.minimumHeight = join_tz.getSize().y;
			editor.grabHorizontal = true;
			editor.setEditor(join_tz, items[i], 4);
			listControl.add(join_tz);

			editor = new TableEditor(table);
			Button join_yz = new Button(table, SWT.CENTER);
			join_yz.setImage(image_join_yz);
			join_yz.setData(i);
			join_yz.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					int index = (int) ((Button) e.getSource()).getData();
					PK pk = PKManager.getInstance().getPKByIndex(index);
					JoinDiaRoleName pkDia = new JoinDiaRoleName(KingMain.this
							.getShell(), index, 2, pk.password);
					pkDia.open();

				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					widgetSelected(e);
				}
			});
			editor.minimumWidth = join_yz.getSize().x;
			editor.minimumHeight = join_yz.getSize().y;
			editor.grabHorizontal = true;
			editor.setEditor(join_yz, items[i], 5);
			listControl.add(join_yz);
			
		

			editor = new TableEditor(table);
			Label textType = new Label(table, SWT.CENTER);
			textType.setFont(SWTResourceManager.getFont("宋体", 15, SWT.NORMAL));
			textType.setText(pk.type + "v" + pk.type);
			editor.grabHorizontal = true;
			editor.setEditor(textType, items[i], 6);
			textType.setToolTipText(pk.type + "v" + pk.type);
			listControl.add(textType);

			editor = new TableEditor(table);
			Label textPoint = new Label(table, SWT.CENTER);
			textPoint.setText(pk.point + "");
			textPoint.setFont(SWTResourceManager.getFont("宋体", 15, SWT.NORMAL));
			editor.grabHorizontal = true;
			editor.setEditor(textPoint, items[i], 7);
			textPoint.setToolTipText(pk.point + "");
			listControl.add(textPoint);

			editor = new TableEditor(table);
			Label textCurNum = new Label(table, SWT.CENTER);
			if (pk.password.equals("")) {
				textCurNum.setText(pk.faqiSeatCount + "-"
						+ +pk.yingzhanSeatCount);
			} else {
				textCurNum.setText(pk.faqiSeatCount + "-"
						+ +pk.yingzhanSeatCount + "(密)");
			}
			textCurNum
					.setFont(SWTResourceManager.getFont("宋体", 15, SWT.NORMAL));
			editor.grabHorizontal = true;
			editor.setEditor(textCurNum, items[i], 8);
			textCurNum.setToolTipText(pk.faqiSeatCount + "-"
					+ +pk.yingzhanSeatCount);
			listControl.add(textCurNum);

		
		}

	}
}
