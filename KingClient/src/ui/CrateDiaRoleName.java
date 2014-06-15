package ui;

import java.util.ArrayList;
import java.util.List;

import object.JfaceWindowManager;
import object.PK;

import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import send.CreatePKMessage1002;

import com.zjd.universal.net.GameClient;

public class CrateDiaRoleName extends Dialog {
	private Text title;
	private Combo area;
	private Combo map;
	private Combo point;
	private Combo type;
	private Text des;
	private Text roleName;
	private Text password;
	private Button btnCheckButton;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public CrateDiaRoleName(Shell parentShell) {
		super(parentShell);
		setWindowManager(JfaceWindowManager.wm);
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		GridLayout gridLayout = (GridLayout) container.getLayout();
		gridLayout.numColumns = 4;
		new Label(container, SWT.NONE);

		Label label1 = new Label(container, SWT.NONE);
		label1.setText("挑战标题：");
		new Label(container, SWT.NONE);

		title = new Text(container, SWT.BORDER);
		title.setTextLimit(50);
		title.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));
		new Label(container, SWT.NONE);

		Label label2 = new Label(container, SWT.NONE);
		label2.setText("挑战区");
		new Label(container, SWT.NONE);

		area = new Combo(container, SWT.READ_ONLY);
		area.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		for (String str : 	KingLogin.AREA) {
			area.add(str);
		}
		
		area.select(0);
		new Label(container, SWT.NONE);

		Label label3 = new Label(container, SWT.NONE);
		label3.setText("挑战地图");
		new Label(container, SWT.NONE);

		map = new Combo(container, SWT.READ_ONLY);
		map.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		map.add("扭曲丛林");
		map.add("召唤师峡谷");
		map.add("水晶之痕");
		map.add("嚎哭深渊");
		

		map.select(0);
		new Label(container, SWT.NONE);

		Label label4 = new Label(container, SWT.NONE);
		label4.setText("对战人数");
		new Label(container, SWT.NONE);

		type = new Combo(container, SWT.READ_ONLY);
		type.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		type.add("1V1");
		type.add("2V2");
		type.add("3V3");
		type.add("4V4");
		type.add("5V5");
		type.select(4);

		new Label(container, SWT.NONE);

		Label label5 = new Label(container, SWT.NONE);
		label5.setText("挑战点数");
		new Label(container, SWT.NONE);

		point = new Combo(container, SWT.READ_ONLY);
		point.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));
		point.add("10");
		point.add("30");
		point.add("50");
		point.add("100");
		point.add("200");
		point.select(0);
		new Label(container, SWT.NONE);

		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setText("挑战说明");
		new Label(container, SWT.NONE);

		des = new Text(container, SWT.BORDER);
		des.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		des.setTextLimit(100);
		new Label(container, SWT.NONE);
		
		Label label = new Label(container, SWT.NONE);
		label.setText("游戏角色名");
		new Label(container, SWT.NONE);
		
		roleName = new Text(container, SWT.BORDER);
		roleName.setTextLimit(50);
		roleName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(container, SWT.NONE);
		
		  btnCheckButton = new Button(container, SWT.CHECK);
		btnCheckButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(btnCheckButton.getSelection())
				{
					password.setEnabled(true);
				}
				else
				{
					password.setEnabled(false);
				}
			}
		});
		btnCheckButton.setText("设置密码");
		new Label(container, SWT.NONE);
		password = new Text(container, SWT.BORDER | SWT.PASSWORD);
		password.setEnabled(false);
		password.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		return container;
	}

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		Button button = createButton(parent, IDialogConstants.FINISH_ID,
				IDialogConstants.OK_LABEL, true);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (title.getText() == null || title.getText().trim().length() == 0
						|| des.getText() == null || des.getText().trim().length() == 0||roleName.getText()==null||roleName.getText().trim().length()==0) {
					MessageBox mb = new MessageBox(CrateDiaRoleName.this.getParentShell(),
							SWT.ICON_INFORMATION | SWT.OK);
					mb.setMessage("填写字段不能为空");
					mb.open();
					return;
				}
				KingLogin.roleName=roleName.getText();
//				httpPostFightAdd();
				String str=null;
				if(btnCheckButton.getSelection())
				{
					if(password.getText()==null||password.getText().trim().length()==0)
					{ MessageBox mb = new MessageBox(CrateDiaRoleName.this.getParentShell(),
							SWT.ICON_INFORMATION | SWT.OK);
					mb.setMessage("设置密码不能为空");
					mb.open();
					return;
					}
					else
					{
						str=password.getText();
					}
				}
				else
				{
					str="";
				}
				PK pk = new PK( KingLogin.id,KingLogin.roleName,title.getText(), area.getText(),
						map.getText(),des.getText(),type.getSelectionIndex() +1,
						Integer.parseInt(point.getText()),-1,str);
				GameClient.getInstance().sendMessageToGameServer(
						new CreatePKMessage1002(pk));
				CrateDiaRoleName.this.close();
			}
		});
		button.setText("确定");
		Button button_1 = createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
		button_1.setText("取消");
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(537, 396);
	}
	public void httpPostFightAdd() {
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("http://198.204.255.98/yxlm/member/fight_add.php");

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("action", "dopost"));
		nvps.add(new BasicNameValuePair("date_create", System.currentTimeMillis()/1000+""));
		nvps.add(new BasicNameValuePair("gamename","英雄联盟"));
		nvps.add(new BasicNameValuePair("area",area.getText()));
		nvps.add(new BasicNameValuePair("title",title.getText()));
		nvps.add(new BasicNameValuePair("price",point.getText()));
		nvps.add(new BasicNameValuePair("map",map.getText()));
		nvps.add(new BasicNameValuePair("description",des.getText()));
		nvps.add(new BasicNameValuePair("type",type.getText()));
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps,"utf-8"));
			CloseableHttpResponse httppHttpResponse2 = httpClient
					.execute(httpPost);
			if (httppHttpResponse2.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String entity = EntityUtils.toString(httppHttpResponse2
						.getEntity());
				MessageBox mb = new MessageBox(CrateDiaRoleName.this.getParentShell(),
						SWT.ICON_INFORMATION | SWT.OK);
				mb.setMessage(entity);
				mb.open();
			}
			else
			{
				MessageBox mb = new MessageBox(CrateDiaRoleName.this.getParentShell(),
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
