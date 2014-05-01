package ui;

import java.util.ArrayList;

import object.PKManager;
import object.PKUser;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import send.EndGamePKMessage1005;
import send.JoinPKMessage1003;
import send.StartGamePKMessage1004;

import com.zjd.universal.net.GameClient;
import org.eclipse.swt.browser.Browser;

public class IndexDemo extends ApplicationWindow {
	private Table table;
	private Label faqi[] = new Label[5];
	private Label yingzhan[] = new Label[5];
	
	private Button btn_start_game,btn_end_game,btn_create_tz;
	private Image image[]=new Image[5];
	private TabItem tabItem[]=new TabItem[5];
	private String tabItemText[]={"首页","个人设置","挑战记录","充值管理","礼品兑换"};
	private Image image_join_tz,image_join_yz,image_create_tz,image_query,image_end_game,image_start_game;

	/**
	 * Create the application window.
	 */
	public IndexDemo() {
		super(null);
		createActions();
		for (int i = 0; i < 5; i++) {
			image[i]= new Image(Display.getDefault(),"tab"+i+".png");;
		}
		image_join_tz=new Image(Display.getDefault(), "join_tz.jpg");
		image_join_yz=new Image(Display.getDefault(),"join_yz.jpg");
		image_create_tz=new Image(Display.getDefault(),"create_tz.png");
		image_query=new Image(Display.getDefault(),"query.png");
		image_end_game=new Image(Display.getDefault(),"end_game.png");
		image_start_game=new Image(Display.getDefault(),"start_game.png");
		setShellStyle(SWT.CLOSE | SWT.TITLE);
	}

	public void PKCreateSuccess(String name, int type) {
		if (name.equals(KingLogin.name)) {
			PKUser.type = type;
			MessageBox mb = new MessageBox(IndexDemo.this.getShell(),
					SWT.ICON_INFORMATION | SWT.OK);
			mb.setMessage("挑战发起成功，作为发起人，你现在不能加入其他挑战房间！");
			mb.open();
			for (int i = 0; i < table.getChildren().length; i++) {
				if(table.getChildren()[i] instanceof Button)
				{table.getChildren()[i].setEnabled(false);}
			}
			
			btn_create_tz.setEnabled(false);
			faqi[0].setText(KingLogin.name);
			for (int i = 0; i < PKUser.type; i++) {
				faqi[i].setVisible(true);
				yingzhan[i].setVisible(true);
			}
		}
	}

	public void PKJoinSuccess(String name, int type) {
		if (name.equals(KingLogin.name)) {
			PKUser.type = type;
			MessageBox mb = new MessageBox(IndexDemo.this.getShell(),
					SWT.ICON_INFORMATION | SWT.OK);
			mb.setMessage("加入成功，作为加入人，你现在不能加入其他挑战房间！");
			mb.open();
			for (int i = 0; i < table.getChildren().length; i++) {
				if(table.getChildren()[i] instanceof Button)
				{table.getChildren()[i].setEnabled(false);}
			}
			btn_create_tz.setEnabled(false);

			for (int i = 0; i < PKUser.type; i++) {
				faqi[i].setVisible(true);
				yingzhan[i].setVisible(true);
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
		Composite container = new Composite(parent, SWT.NONE);
		container.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(false);
		table.setHeaderVisible(true);
		table.setBounds(10, 64, 900, 500);
		table.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		TableColumn column = new TableColumn(table, SWT.NONE);
		column.setWidth(460);
		TableColumn column1 = new TableColumn(table, SWT.NONE);
		column1.setWidth(106);
		TableColumn column2 = new TableColumn(table, SWT.NONE);
		column2.setWidth(106);
		btn_create_tz = new Button(container, SWT.NONE);
		btn_create_tz.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				PKDia pkDia = new PKDia(IndexDemo.this.getShell());
				pkDia.open();
			}
		});
		btn_create_tz.setBounds(10, 10, 193, 38);
		btn_create_tz.setImage(image_create_tz);
		

		Group group = new Group(container, SWT.NONE);
		group.setText("发起方");
		group.setBounds(10, 273, 243, 262);

		faqi[0] = new Label(group, SWT.NONE);
		faqi[0].setBounds(10, 32, 54, 12);

		faqi[1] = new Label(group, SWT.NONE);
		faqi[1].setBounds(10, 78, 54, 12);

		faqi[2] = new Label(group, SWT.NONE);
		faqi[2].setBounds(10, 126, 54, 12);

		faqi[3] = new Label(group, SWT.NONE);
		faqi[3].setBounds(10, 177, 54, 12);

		faqi[4] = new Label(group, SWT.NONE);
		faqi[4].setBounds(10, 226, 54, 12);

		for (int i = 0; i < faqi.length; i++) {
			faqi[i].setText("空位");
			faqi[i].setVisible(false);
		}
		Group group_1 = new Group(container, SWT.NONE);
		group_1.setText("应战方");
		group_1.setBounds(349, 273, 243, 262);

		yingzhan[0] = new Label(group_1, SWT.NONE);
		yingzhan[0].setBounds(10, 32, 54, 12);

		yingzhan[1] = new Label(group_1, SWT.NONE);
		yingzhan[1].setBounds(10, 78, 54, 12);

		yingzhan[2] = new Label(group_1, SWT.NONE);
		yingzhan[2].setBounds(10, 126, 54, 12);

		yingzhan[3] = new Label(group_1, SWT.NONE);
		yingzhan[3].setBounds(10, 177, 54, 12);

		yingzhan[4] = new Label(group_1, SWT.NONE);
		yingzhan[4].setBounds(10, 226, 54, 12);

		for (int i = 0; i < faqi.length; i++) {
			yingzhan[i].setText("空位");
			yingzhan[i].setVisible(false);
		}

		

		btn_start_game = new Button(container, SWT.NONE);
		btn_start_game.setImage(image_start_game);
		btn_start_game.setBounds(408, 10, 193, 38);
		btn_start_game.setEnabled(false);
		btn_start_game.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				GameClient.getInstance().sendMessageToGameServer(
						new StartGamePKMessage1004());
				btn_start_game.setEnabled(false);
			}
		});
		
		Button query = new Button(container, SWT.NONE);
		query.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				QueryDia pkDia = new QueryDia(IndexDemo.this.getShell());
				pkDia.open();
			}
		});
		query.setImage(image_query);
		query.setBounds(209, 10, 193, 38);
		
		 btn_end_game = new Button(container, SWT.NONE);
		 btn_end_game.setImage(image_end_game);
		btn_end_game.setEnabled(false);
		btn_end_game.setBounds(607, 10, 193, 38);
		
		Browser browser = new Browser(container, SWT.BORDER);
		browser.setUrl("www.baidu.com");
		browser.setBounds(0, 0, 300, 200);
		
		
		
		btn_end_game.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				GameClient.getInstance().sendMessageToGameServer(
						new EndGamePKMessage1005());
				btn_end_game.setEnabled(false);
			}
		});
		return container;
  }

	@Override
	protected void handleShellCloseEvent() {
		MessageBox mb = new MessageBox(IndexDemo.this.getShell(),
				SWT.ICON_INFORMATION | SWT.OK | SWT.CANCEL);
		mb.setMessage("确定要关闭吗?");//
		int rc=mb.open();
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
		 try {
		 IndexDemo window = new IndexDemo();
		 window.setBlockOnOpen(true);
		 window.open();
		 Display.getCurrent().dispose();
		 System.exit(0);
		 } catch (Exception e) {
		 e.printStackTrace();
		 }
	}

	/**
	 * Configure the shell.
	 * 
	 * @param newShell
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("\u6211\u662F\u738B\u8005"+"----用户:"+KingLogin.name);
		
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(1024, 768);
	}

	public void RefreshLables(PKUser[] users) {
		for (int i = 0; i < users.length; i++) {
			if (users[i] != null) {
				if (users[i].camp == 1) {
					faqi[users[i].seatID].setText(users[i].name);
				} else {
					yingzhan[users[i].seatID].setText(users[i].name);
				}
			}
		}

	}

	public void leaveLables(int camp, int seatID) {
		if (camp == 1) {
			faqi[seatID].setText("空位");
		} else {
			yingzhan[seatID].setText("空位");
		}
	}

	public void PopErrorCreateMessage() {
		MessageBox mb = new MessageBox(IndexDemo.this.getShell(),
				SWT.ICON_INFORMATION | SWT.OK);
		mb.setMessage("创建房间失败");//
		mb.open();
	}

	public void PopErrorJoinMessage(String name) {
		if (name.equals(KingLogin.name)) {
			MessageBox mb = new MessageBox(IndexDemo.this.getShell(),
					SWT.ICON_INFORMATION | SWT.OK);
			mb.setMessage("加入房间房间失败,阵营人数已满，或房间无效");//
			mb.open();
		}

	}

	public void btn_start_gameEnable() {
		MessageBox mb = new MessageBox(IndexDemo.this.getShell(),
				SWT.ICON_INFORMATION | SWT.OK);
		mb.setMessage("房间已满 你可以开始游戏了");//
		mb.open();
		btn_start_game.setEnabled(true);
	}
	public void StartGameResult(int status,String name) {
		if(status==0)
		{
			
			MessageBox mb = new MessageBox(IndexDemo.this.getShell(),
				SWT.ICON_INFORMATION | SWT.OK);
			if(name.equals(KingLogin.name))
		{mb.setMessage("现在你有操作权限可以结束游戏了");
		btn_end_game.setEnabled(true);
		}
			else
			{
				mb.setMessage("房主已经点击开始游戏了，游戏开始");	
			}
		mb.open();
		
		}
		else
		{
			MessageBox mb = new MessageBox(IndexDemo.this.getShell(),
					SWT.ICON_INFORMATION | SWT.OK);
			mb.setMessage("开始游戏失败");//
			mb.open();
		}
		
	}
	public void EndGameResult(int status) {
		if(status==0)
		{
			MessageBox mb = new MessageBox(IndexDemo.this.getShell(),
				SWT.ICON_INFORMATION | SWT.OK);
		mb.setMessage("房主结束游戏成功，房间解散");//
		mb.open();
		btn_end_game.setEnabled(false);
		for (int i = 0; i < faqi.length; i++) {
			yingzhan[i].setText("空位");
			yingzhan[i].setVisible(false);
		}
		for (int i = 0; i < faqi.length; i++) {
			faqi[i].setText("空位");
			faqi[i].setVisible(false);
		}
		btn_create_tz.setEnabled(true);
		}
		else
		{
			MessageBox mb = new MessageBox(IndexDemo.this.getShell(),
					SWT.ICON_INFORMATION | SWT.OK);
			mb.setMessage("结束游戏失败");//
			mb.open();
		}
		
	}
	
	ArrayList<Control> listControl=new ArrayList<Control>();
	public void RefreshTable() {

		table.clearAll();
		table.removeAll();
		for (int i = 0; i < listControl.size(); i++) {
			listControl.get(i).dispose();
		}
		listControl.clear();
		for (int i = 0; i < PKManager.getInstance().getPKNum(); i++) {
			new TableItem(table, SWT.NONE);
		}
		TableItem[] items = table.getItems();
		for (int i = 0; i < items.length; i++) {
			TableEditor editor = new TableEditor(table);

			editor = new TableEditor(table);
			Text text = new Text(table, SWT.NONE);
			text.setText(PKManager.getInstance().getPKByIndex(i).toString());
			text.setEditable(false);
			text.setBackground(table.getBackground());
			editor.grabHorizontal = true;
			editor.setEditor(text, items[i], 0);
			text.setToolTipText(PKManager.getInstance().getPKByIndex(i).toString());
			listControl.add(text);
			
			
			editor = new TableEditor(table);
			Button join_tz = new Button(table, SWT.NONE);
			join_tz.setText("加入发起方");
			join_tz.pack();
			join_tz.setData(i);
			join_tz.addSelectionListener(new SelectionAdapter() {

				@Override
				public void widgetSelected(SelectionEvent e) {

					int index = (int) ((Button) e.getSource()).getData();
					GameClient.getInstance().sendMessageToGameServer(
							new JoinPKMessage1003(index, 1, KingLogin.name));

				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					widgetSelected(e);
				}
			});
			editor.minimumWidth = join_tz.getSize().x;
			editor.minimumHeight = join_tz.getSize().y;
			editor.grabHorizontal = true;
			editor.setEditor(join_tz, items[i], 1);
			listControl.add(join_tz);
			
			
			editor = new TableEditor(table);
			Button join_yz = new Button(table, SWT.NONE);
			join_yz.setText("加入应战方");
			join_yz.pack();
			join_yz.setData(i);
			join_yz.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					int index = (int) ((Button) e.getSource()).getData();
					GameClient.getInstance().sendMessageToGameServer(
							new JoinPKMessage1003(index, 2, KingLogin.name));

				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					widgetSelected(e);
				}
			});
			editor.minimumWidth = join_yz.getSize().x;
			editor.minimumHeight = join_yz.getSize().y;
			editor.grabHorizontal = true;
			editor.setEditor(join_yz, items[i], 2);
			listControl.add(join_yz);
		}

	}
}
