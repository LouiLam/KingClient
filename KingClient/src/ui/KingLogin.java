package ui;
import java.util.ArrayList;
import java.util.List;

import obj.PKUser;

import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.eclipse.jface.action.StatusLineManager;
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
	public static String name;
	public static KingMain pkui;
	Image image,image_reg,image_login;
	/**
	 * Create the application window,
	 */
	public KingLogin() {
		super(null);
		 image = new Image(Display.getDefault(),"1.jpg");
		 image_reg = new Image(Display.getDefault(),"reg.png");
		 image_login = new Image(Display.getDefault(),"login.png");
		setShellStyle(SWT.CLOSE | SWT.TITLE);
//		createActions();
//		addCoolBar(SWT.FLAT);
//		addMenuBar();
//		addStatusLine();
	}
	protected boolean showTopSeperator() {return false;}
	Text pwd_text;
	Combo combo;
	
	/**
	 * Create contents of the application window.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) {
		
//		parent.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_GREEN));
		Composite container = new Composite(parent, SWT.NONE);
		getShell().setImage(image);
		parent.setBackgroundMode(SWT.INHERIT_DEFAULT); 
		parent.setBackgroundImage(image);
		Label id = new Label(container, SWT.NONE);
		id.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		id.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		id.setBounds(36, 117, 54, 12);
		id.setText("用户帐号："); // �ʺ�:
		combo = new Combo(container, SWT.NONE); // �ʺ������
		combo.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		combo.setBounds(103, 114, 190, 20);
		combo.setTextLimit(20);
		Label pwd = new Label(container, SWT.NONE);
		pwd.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		pwd.setText("登录密码：");// ����:
		pwd.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		pwd.setBounds(36, 167, 54, 12);
		
		pwd_text = new Text(container, SWT.BORDER | SWT.PASSWORD);// ���������
		pwd_text.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		pwd_text.setBounds(103, 164, 190, 20);
		pwd_text.setTextLimit(36);
		Button reg = new Button(container, SWT.FLAT);
		reg.setImage(image_reg);
		reg.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				RegDia regDia = new RegDia(KingLogin.this.getShell());
				regDia.open();
			}
		});
		reg.setBounds(10, 212, 138, 37);
//		reg.setText("\u6CE8\u518C");// ע��
		Button login = new Button(container,  SWT.NONE);
		login.setImage(image_login);
		login.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
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
				name=combo.getText();
				httpPost();
			
				
			
//				Display.getDefault().asyncExec(new Runnable() {
//					
//					@Override
//					public void run() {
//					
//					
//					}
//				});
				
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
		login.setBounds(205, 212, 138, 37);
		
		 final Button btnNewButton = new Button(container, SWT.CHECK);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(btnNewButton.getSelection())
				{
					System.out.println("选中");
				}
				else
				{
					System.out.println("未选中");
				}
			}
		});
		btnNewButton.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnNewButton.setBounds(103, 190, 72, 22);
		btnNewButton.setText("记住密码");
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				System.out.println("鼠标点击");
			}
		});
		lblNewLabel.setBounds(221, 194, 54, 12);
		lblNewLabel.setText("忘记密码");
//		login.setText("\u767B\u5F55");// ��¼

		return container;
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
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
				"http://www.woowgo.com/yxlm/member/index_do.php?fmdo=login&dopost=login");

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
				
				JSONObject  obj=new JSONObject(entity);
				int value=Integer.parseInt(obj.get("value")+"");
				MessageBox mb = new MessageBox(KingLogin.this.getShell(),
						SWT.ICON_INFORMATION | SWT.OK);
				mb.setMessage(obj.get("msg")+"");
				mb.open();
				if(value==1)
				{
				PKUser.uid=Integer.parseInt((String) obj.get("uid"));
				Display.getCurrent().dispose();
				pkui = new KingMain();
				pkui.setBlockOnOpen(true);
				pkui.open();}
				
			}
			else
			{
				MessageBox mb = new MessageBox(KingLogin.this.getShell(),
				SWT.ICON_INFORMATION | SWT.OK);
				mb.setMessage("error!StatusCode:"+httppHttpResponse2.getStatusLine().getStatusCode());// http״̬�����
				mb.open();
			}
			httppHttpResponse2.close();
			httpClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
