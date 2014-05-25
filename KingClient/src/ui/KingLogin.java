package ui;

import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import object.Account;
import object.JfaceWindowManager;
import object.PKUser;
import object.ReadFile;
import object.WriteFile;

import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
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
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.json.JSONObject;

public class KingLogin extends ApplicationWindow {
	public static String id, roleName;
	public static boolean isFirstRun=false;
	Image image, image_reg, image_login;
	Button btn_isAutoSave;
	String autoSave = "0";
	boolean isBreak=true;
	
	/**
	 * Create the application window,
	 */
	public KingLogin() {
		super(null);
		// InetAddress addresses;
		// try {
		// addresses = InetAddress.getByName("louislam0714.xicp.net");
		// GameClient.GAME_IP=addresses.getHostAddress();
		// System.out.println(GameClient.GAME_IP);
		// } catch (UnknownHostException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		setWindowManager(JfaceWindowManager.wm);

		ReadFile.init();

		image = new Image(Display.getDefault(), "1.jpg");
		image_reg = new Image(Display.getDefault(), "reg.png");
		image_login = new Image(Display.getDefault(), "login.png");
		setShellStyle(SWT.CLOSE | SWT.TITLE);
		// createActions();
		// addCoolBar(SWT.FLAT);
		// addMenuBar();
		// addStatusLine();
	}

	protected boolean showTopSeperator() {
		return false;
	}

	Text pwd_text;
	Combo combo;

	/**
	 * Create contents of the application window.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) {
		if(KingLogin.isFirstRun)
		{
			UrlDia dia=new UrlDia(KingLogin.this.getShell(),"http://www.hexcm.com/yxlm/single/lc1.html");
			dia.open();
		}
		parent.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));

		// parent.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_GREEN));
		Composite container = new Composite(parent, SWT.NONE);
//		getShell().setImage(image);
		parent.setBackgroundMode(SWT.INHERIT_DEFAULT);
		parent.setBackgroundImage(image);
		Label id = new Label(container, SWT.NONE);
		id.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		id.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		id.setBounds(36, 117, 54, 12);
		id.setText("用户帐号：");
		combo = new Combo(container, SWT.NONE);
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				int index = ((Combo) e.getSource()).getSelectionIndex();
				if (!ReadFile.accounts.get(index).isAutoSave.equals("0")) {
					pwd_text.setText(ReadFile.accounts.get(index).password);
					btn_isAutoSave.setSelection(true);
				} else {
					pwd_text.setText("");
					btn_isAutoSave.setSelection(false);
				}
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				widgetDefaultSelected(e);
			}
		});
		combo.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		combo.setBounds(103, 114, 190, 20);
		combo.setTextLimit(20);

		Label pwd = new Label(container, SWT.NONE);
		pwd.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		pwd.setText("登录密码：");
		pwd.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		pwd.setBounds(36, 167, 54, 12);

		pwd_text = new Text(container, SWT.BORDER | SWT.PASSWORD);
		pwd_text.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		pwd_text.setBounds(103, 164, 190, 20);
		pwd_text.setTextLimit(36);
		for (Account obj : ReadFile.accounts) {
			combo.add(obj.id);

		}
		combo.select(combo.getItemCount() - 1);
		if (!ReadFile.accounts.get(combo.getItemCount() - 1).isAutoSave
				.equals("0")) {
			pwd_text.setText(ReadFile.accounts.get(combo.getItemCount() - 1).password);
		}
		Label reg = new Label(container, SWT.FLAT);
		reg.setImage(image_reg);
		reg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				RegDia regDia = new RegDia(KingLogin.this.getShell());
				regDia.open();
			}
		});

		reg.setBounds(10, 218, 138, 37);
		Label login = new Label(container, SWT.NONE);
		login.setImage(image_login);

		login.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if (combo.getText() == null
						|| combo.getText().trim().length() == 0
						|| pwd_text.getText() == null
						|| pwd_text.getText().trim().length() == 0) {
					MessageBox mb = new MessageBox(KingLogin.this.getShell(),
							SWT.ICON_INFORMATION | SWT.OK);
					mb.setMessage("\u5e10\u53f7\u6216\u5bc6\u7801\u4e0d\u80fd\u4e3a\u7a7a");
					mb.open();
					return;
				}
				httpPost();
//				ProgressMonitorDialog dialog = new ProgressMonitorDialog(
//						getShell());
//				try {
//					dialog.run(true, true, new IRunnableWithProgress() {
//
//						@Override
//						public void run(IProgressMonitor monitor)
//								throws InvocationTargetException,
//								InterruptedException {
//						
//							monitor.beginTask("wait ...", 10);
//							int i=0;
//							while (isBreak) {
//								i++;
//								Thread.sleep(100);
//								monitor.worked(1);
//								if (monitor.isCanceled()) {
//									throw new InterruptedException();
//								}
//								if(i>10)
//								{
//								i=0;
//								monitor.beginTask("wait ...", 10);
//								}
//							}
//							monitor.done();
//						
//							
//						}
//					});
//					MessageDialog.openInformation(getShell(), "登录提示",
//							"登录完成");
//				} catch (InvocationTargetException s) {
//					MessageDialog.openError(getShell(), "InvocationTargetException", 
//							s.getMessage());
//				} catch (InterruptedException s) {
//					MessageDialog.openInformation(getShell(), "InterruptedException",
//							"User cancels it.");
//				}
			
			}
		});
		login.setBounds(206, 218, 138, 37);

		btn_isAutoSave = new Button(container, SWT.CHECK);
		btn_isAutoSave
				.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		btn_isAutoSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btn_isAutoSave.getSelection()) {
					autoSave = "1";
				} else {
					autoSave = "0";
				}
			}
		});
		btn_isAutoSave.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));
		btn_isAutoSave.setBounds(103, 190, 72, 22);
		btn_isAutoSave.setText("记住密码");
		if (!ReadFile.accounts.get(combo.getItemCount() - 1).isAutoSave
				.equals("0")) {
			btn_isAutoSave.setSelection(true);
			autoSave = "1";
		}
		Label lblNewLabel = new Label(container, SWT.WRAP);
		lblNewLabel.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				System.out.println("鼠标点击");
			}
		});
		lblNewLabel.setBounds(221, 194, 54, 12);
		lblNewLabel.setText("忘记密码");

		return container;
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
			KingLogin window = new KingLogin();
			window.setBlockOnOpen(true);
			window.open();
			try {
				Display.getCurrent().dispose();
			} catch (Exception e) {
				System.exit(0);
			}
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
		newShell.setText("欢迎您登录我是王者");
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(360, 293);
	}

	public void httpPost() {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(
				"http://www.hexcm.com/yxlm/member/index_do.php?fmdo=login&dopost=login");
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("userid", combo.getText()));
		nvps.add(new BasicNameValuePair("pwd", pwd_text.getText()));
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			CloseableHttpResponse httppHttpResponse2 = httpClient
					.execute(httpPost);
			if (httppHttpResponse2.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String entity = EntityUtils.toString(httppHttpResponse2
						.getEntity());
				System.out.println(entity);
				JSONObject obj = new JSONObject(entity);
				int value = Integer.parseInt(obj.get("value") + "");
				if (value == 1) {
					// 登录成功就写入帐号信息
					PrintWriter out = WriteFile.openWriter("account.txt");
					boolean temp = false;
					for (Account account : ReadFile.accounts) {
						if (account.id.equals(combo.getText())) {
							account.password = pwd_text.getText();
							account.isAutoSave = autoSave;
							temp = true;
						}
					}
					if (!temp) {
						ReadFile.accounts.add(new Account(combo.getText(),
								pwd_text.getText(), autoSave));
					}
					for (Account account : ReadFile.accounts) {
						WriteFile.writeMovie(account, out);
					}
					out.close();
					PKUser.uid = Integer.parseInt((String) obj.get("uid"));
					// id=obj.getString("uname");
					id = combo.getText();
					// JfaceWindowManager.getCurWindow().close();
					Display.getCurrent().dispose();
					KingMain kingMain = new KingMain();
					kingMain.setBlockOnOpen(true);
					kingMain.open();
				}

			} else {
				MessageBox mb = new MessageBox(KingLogin.this.getShell(),
						SWT.ICON_INFORMATION | SWT.OK);
				mb.setMessage("error!StatusCode:"
						+ httppHttpResponse2.getStatusLine().getStatusCode());// http״̬�����
				mb.open();
			}
			
			httppHttpResponse2.close();
			httpClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
