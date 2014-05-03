package ui;

import java.util.ArrayList;

import object.JfaceWindowManager;
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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;

import send.JoinPKMessage1003;

import com.zjd.universal.net.GameClient;

public class KingMain extends ApplicationWindow {
	private Table table;

	private Button btn_create_tz;
	private Image image[] = new Image[5];
	private TabItem tabItem[] = new TabItem[5];
	private String tabItemText[] = { "首页", "个人设置", "挑战记录", "充值管理", "礼品兑换" };
	private String urlText[] = { "http://woowgo.com/yxlm/setting.html",
			"http://woowgo.com/yxlm/lszj.html",
			"http://woowgo.com/yxlm/cz.html", "http://woowgo.com/yxlm/dj.html" };

	private Image image_join_tz, image_join_yz, image_create_tz, image_query,
			image_table_bg;

	/**
	 * Create the application window.
	 */
	public KingMain() {

		super(null);
		setWindowManager(JfaceWindowManager.wm);
		createActions();
		// addStatusLine();
		for (int i = 0; i < 5; i++) {
			image[i] = new Image(Display.getDefault(), "tab" + i + ".png");
			;
		}
		image_join_tz = new Image(Display.getDefault(), "join_tz.jpg");
		image_join_yz = new Image(Display.getDefault(), "join_yz.jpg");
		image_create_tz = new Image(Display.getDefault(), "create_tz.png");
		image_query = new Image(Display.getDefault(), "query.png");
		image_table_bg = new Image(Display.getDefault(), "green.jpg");
		setShellStyle(SWT.CLOSE | SWT.TITLE);
	}

	public void PKCreateSuccess(String name, int type, String area, String title) {
		if (name.equals(KingLogin.name)) {
			PKUser.type = type;
			MessageBox mb = new MessageBox(KingMain.this.getShell(),
					SWT.ICON_INFORMATION | SWT.OK);
			mb.setMessage("挑战发起成功，作为发起人，你现在不能加入其他挑战房间！");
			mb.open();
			tableDisable();

			btn_create_tz.setEnabled(false);

			CreatePKWaitDia waitDia = new CreatePKWaitDia(getShell(), type,
					area, title);
			waitDia.open();
		}
	}

	public void PKJoinSuccess(String name, int type, PKUser users[],
			String area, String title) {
		if (name.equals(KingLogin.name)) {
			PKUser.type = type;
			MessageBox mb = new MessageBox(KingMain.this.getShell(),
					SWT.ICON_INFORMATION | SWT.OK);
			mb.setMessage("加入成功，作为加入人，你现在不能加入其他挑战房间！");
			mb.open();
			tableDisable();
			btn_create_tz.setEnabled(false);
			JoinPKWaitDia waitDia = new JoinPKWaitDia(getShell(), users, type,
					area, title);
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

	/**
	 * Create contents of the application window.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) {

		final TabFolder tabFolder = new TabFolder(parent, SWT.None);
		tabFolder.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		tabFolder.setBounds(10, 0, 881, 649);
		// Create each tab and set its text, tool tip text,
		// image, and control
		for (int i = 0; i < 5; i++) {
			tabItem[i] = new TabItem(tabFolder, SWT.NONE);
			tabItem[i].setText(tabItemText[i]);
			tabItem[i].setToolTipText("提示文本");
			tabItem[i].setImage(image[i]);
			if (i == 0) {
				tabItem[i].setControl(getTabControlOne(tabFolder));
			} else {
				tabItem[i].setControl(getTabContrlOther(tabFolder,
						urlText[i - 1]));
			}
		}

		// Select the third tab (index is zero-based)
		tabFolder.setSelection(0);

		// Add an event listener to write the selected tab to stdout
		tabFolder.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(
					org.eclipse.swt.events.SelectionEvent event) {
				System.out.println(tabFolder.getSelection()[0].getText()
						+ " selected");
			}
		});

		GameClient.getInstance().onCreate();
		GameClient.getInstance().connectGameServer(GameClient.GAME_IP,
				GameClient.GAME_PORT);
		return tabFolder;
	}

	private Control getTabContrlOther(TabFolder tabFolder, String url) {
		Composite container = new Composite(tabFolder, SWT.NONE);
		Browser browser = new Browser(container, SWT.BORDER);
		browser.setUrl(url);
		browser.setBounds(0, 0, 1024, 768);
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
	private Control getTabControlOne(TabFolder tabFolder) {
		Composite container = new Composite(tabFolder, SWT.NONE);
		container.setFont(SWTResourceManager.getFont("宋体", 15, SWT.NORMAL));
		table = new Table(container, SWT.BORDER | SWT.MULTI);
		table.setLinesVisible(false);
		table.setHeaderVisible(true);
		table.setBounds(10, 64, 900, 500);
		table.setFont(SWTResourceManager.getFont("宋体", 15, SWT.NORMAL));
		TableColumn column = new TableColumn(table, SWT.NONE);
		column.setWidth(100);
		column.setText("房主");
		column = new TableColumn(table, SWT.CENTER);
		column.setWidth(100);
		column.setText("标题");
		column = new TableColumn(table, SWT.CENTER);
		column.setWidth(150);
		column.setText("游戏区");
		column = new TableColumn(table, SWT.CENTER);
		column.setWidth(120);
		column.setText("挑战图");
		column = new TableColumn(table, SWT.CENTER);
		column.setWidth(100);
		column.setText("对战人数");
		column = new TableColumn(table, SWT.CENTER);
		column.setWidth(100);
		column.setText("挑战点");
		column = new TableColumn(table, SWT.CENTER);// 挑战方
		column.setWidth(108);
		column.setText("挑战方");
		column = new TableColumn(table, SWT.CENTER);// 应战方
		column.setWidth(108);
		column.setText("应战方");
		btn_create_tz = new Button(container, SWT.CENTER);
		btn_create_tz.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				PKDia pkDia = new PKDia(KingMain.this.getShell());
				pkDia.open();
			}
		});
		btn_create_tz.setBounds(10, 10, 193, 38);
		btn_create_tz.setImage(image_create_tz);

		Button query = new Button(container, SWT.NONE);
		query.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				QueryDia pkDia = new QueryDia(KingMain.this.getShell());
				pkDia.open();
			}
		});
		query.setImage(image_query);
		query.setBounds(209, 10, 193, 38);

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
		newShell.setText("\u6211\u662F\u738B\u8005" + "----用户:"
				+ KingLogin.name);

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

	public void PopErrorJoinMessage(String name) {
		if (name.equals(KingLogin.name)) {
			MessageBox mb = new MessageBox(KingMain.this.getShell(),
					SWT.ICON_INFORMATION | SWT.OK);
			mb.setMessage("加入房间房间失败,阵营人数已满，或房间无效");//
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

	public void StartGameResult(int status, String name) {
		if (status == 0) {
			State.CurState = State.STATE_GAME_START;
			MessageBox mb = new MessageBox(KingMain.this.getShell(),
					SWT.ICON_INFORMATION | SWT.OK);
			if (name.equals(KingLogin.name)) {
				mb.setMessage("现在你有操作权限可以结束游戏了");
				if (JfaceWindowManager.getCurWindow() instanceof CreatePKWaitDia) {
					CreatePKWaitDia dia = (CreatePKWaitDia) JfaceWindowManager
							.getCurWindow();
					dia.btn_end_game.setEnabled(true);
				}
			} else {
				mb.setMessage("房主已经点击开始游戏了，游戏开始");
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
				dia.close();
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
	public void HostLeave() {
		tableEnable();
		MessageBox mb = new MessageBox(KingMain.this.getShell(),
				SWT.ICON_INFORMATION | SWT.OK);
		mb.setMessage("房主异常退出，挑战解散");//
		mb.open();

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

			TableEditor editor = new TableEditor(table);
			Label textName = new Label(table, SWT.CENTER);
			textName.setText(PKManager.getInstance().getPKByIndex(i).name);
			// text.setEditable(false);
			// text.setBackground(table.getBackground());
			textName.setFont(SWTResourceManager.getFont("宋体", 15, SWT.NORMAL));
			editor.grabHorizontal = true;
			editor.setEditor(textName, items[i], 0);
			textName.setToolTipText(PKManager.getInstance().getPKByIndex(i).name);
			listControl.add(textName);

			editor = new TableEditor(table);
			Label textTitle = new Label(table, SWT.CENTER);
			textTitle.setText(PKManager.getInstance().getPKByIndex(i).title);
			textTitle.setFont(SWTResourceManager.getFont("宋体", 15, SWT.NORMAL));
			editor.grabHorizontal = true;
			editor.setEditor(textTitle, items[i], 1);
			textTitle
					.setToolTipText(PKManager.getInstance().getPKByIndex(i).title);
			listControl.add(textTitle);

			editor = new TableEditor(table);
			Label textArea = new Label(table, SWT.CENTER);
			textArea.setText(PKManager.getInstance().getPKByIndex(i).area);
			textArea.setFont(SWTResourceManager.getFont("宋体", 15, SWT.NORMAL));
			editor.grabHorizontal = true;
			editor.setEditor(textArea, items[i], 2);
			textArea.setToolTipText(PKManager.getInstance().getPKByIndex(i).area);
			listControl.add(textArea);

			editor = new TableEditor(table);
			Label textMap = new Label(table, SWT.CENTER);
			textMap.setText(PKManager.getInstance().getPKByIndex(i).map);
			textMap.setFont(SWTResourceManager.getFont("宋体", 15, SWT.NORMAL));
			editor.grabHorizontal = true;
			editor.setEditor(textMap, items[i], 3);
			textMap.setToolTipText(PKManager.getInstance().getPKByIndex(i).map);
			listControl.add(textMap);

			editor = new TableEditor(table);
			Label textType = new Label(table, SWT.CENTER);
			textType.setFont(SWTResourceManager.getFont("宋体", 15, SWT.NORMAL));
			textType.setText(PKManager.getInstance().getPKByIndex(i).type + "v"
					+ PKManager.getInstance().getPKByIndex(i).type);
			editor.grabHorizontal = true;
			editor.setEditor(textType, items[i], 4);
			textType.setToolTipText(PKManager.getInstance().getPKByIndex(i).type
					+ "v" + PKManager.getInstance().getPKByIndex(i).type);
			listControl.add(textType);

			editor = new TableEditor(table);
			Label textPoint = new Label(table, SWT.CENTER);
			textPoint.setText(PKManager.getInstance().getPKByIndex(i).point
					+ "");
			textPoint.setFont(SWTResourceManager.getFont("宋体", 15, SWT.NORMAL));
			editor.grabHorizontal = true;
			editor.setEditor(textPoint, items[i], 5);
			textPoint
					.setToolTipText(PKManager.getInstance().getPKByIndex(i).point
							+ "");
			listControl.add(textPoint);

			editor = new TableEditor(table);
			Button join_tz = new Button(table, SWT.CENTER);
			join_tz.setImage(image_join_tz);
			join_tz.setData(i);
			join_tz.addSelectionListener(new SelectionAdapter() {

				@Override
				public void widgetSelected(SelectionEvent e) {

					int index = (int) ((Button) e.getSource()).getData();
					GameClient.getInstance().sendMessageToGameServer(
							new JoinPKMessage1003(PKManager.getInstance()
									.getPKByIndex(index).sql_id, 1,
									KingLogin.name));

				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					widgetSelected(e);
				}
			});
			editor.minimumWidth = join_tz.getSize().x;
			editor.minimumHeight = join_tz.getSize().y;
			editor.grabHorizontal = true;
			editor.setEditor(join_tz, items[i], 6);
			listControl.add(join_tz);

			editor = new TableEditor(table);
			Button join_yz = new Button(table, SWT.CENTER);
			join_yz.setImage(image_join_yz);
			join_yz.setData(i);
			join_yz.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					int index = (int) ((Button) e.getSource()).getData();
					GameClient.getInstance().sendMessageToGameServer(
							new JoinPKMessage1003(PKManager.getInstance()
									.getPKByIndex(index).sql_id, 2,
									KingLogin.name));

				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					widgetSelected(e);
				}
			});
			editor.minimumWidth = join_yz.getSize().x;
			editor.minimumHeight = join_yz.getSize().y;
			editor.grabHorizontal = true;
			editor.setEditor(join_yz, items[i], 7);
			listControl.add(join_yz);
		}

	}
}
