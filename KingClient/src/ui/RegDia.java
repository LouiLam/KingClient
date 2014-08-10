package ui;

import java.util.ArrayList;
import java.util.List;

import object.JfaceWindowManager;

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
import org.eclipse.jface.viewers.deferred.SetModel;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.json.JSONObject;

public class RegDia extends Dialog {
	private Text id_text;
	private Text pwd_text;
	private Text pwdok_text;
	private Text mail;
	private Text qq;
	private Text mobile;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public RegDia(Shell parentShell) {
		super(parentShell);
		setWindowManager(JfaceWindowManager.wm);
		setShellStyle(SWT.DIALOG_TRIM);
		// getShell().setImage(parentShell.getImage());
	}

	@Override
	protected void configureShell(Shell newShell) {
		// TODO Auto-generated method stub
		super.configureShell(newShell);
		newShell.setText("\u6CE8\u518C");
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
		gridLayout.numColumns = 3;

		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel.setText("\u7528\u6237\u540D\uFF1A");
		new Label(container, SWT.NONE);

		id_text = new Text(container, SWT.BORDER);
		id_text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		id_text.setMessage("帐号最长20个字符，只能包含英文和数字，不能包含中文和特殊字符");
		id_text.setTextLimit(20);
		id_text.setToolTipText("帐号最长20个字符，只能包含英文和数字，不能包含中文和特殊字符");

		Label lblNewLabel_1 = new Label(container, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel_1.setText("\u767B\u5F55\u5BC6\u7801\uFF1A");
		new Label(container, SWT.NONE);

		
		pwd_text = new Text(container, SWT.BORDER | SWT.PASSWORD);
		pwd_text.setFocus();
		pwd_text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		pwd_text.setTextLimit(36);
		pwd_text.setToolTipText("\u5bc6\u7801\u6700\u957f36\u4e2a\u5b57\u7b26");

		Label lblNewLabel_2 = new Label(container, SWT.NONE);
		lblNewLabel_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel_2.setText("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		new Label(container, SWT.NONE);

		pwdok_text = new Text(container, SWT.BORDER | SWT.PASSWORD);
		pwdok_text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		pwdok_text.setTextLimit(36);
		Label label = new Label(container, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
				1, 1));
		label.setText("\u90AE\u7BB1\uFF1A");
		new Label(container, SWT.NONE);

		mail = new Text(container, SWT.BORDER);
		mail.setMessage("邮箱用于接收验证信息，请保证邮箱的真实性");
		mail.setToolTipText("邮箱用于接收验证信息，请保证邮箱的真实性");
		mail.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblNewLabel_3 = new Label(container, SWT.NONE);
		lblNewLabel_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel_3.setText("QQ\uFF1A");

		new Label(container, SWT.NONE);

		qq = new Text(container, SWT.BORDER);
		qq.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));
		qq.setMessage("必填");

		Label lblNewLabel_4 = new Label(container, SWT.NONE);
		lblNewLabel_4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel_4.setText("\u624B\u673A\uFF1A");
		new Label(container, SWT.NONE);

		mobile = new Text(container, SWT.BORDER);
		mobile.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));
		mobile.setMessage("必填");
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
				if (pwd_text.getText() == null
						|| pwd_text.getText().length() == 0
						|| pwdok_text.getText() == null
						|| pwdok_text.getText().length() == 0||qq.getText()==null||qq.getText().length()==0||
						mobile.getText()==null||mobile.getText().length()==0||
								mail.getText()==null||mail.getText().length()==0
						) {
					MessageBox mb = new MessageBox(
							RegDia.this.getParentShell(), SWT.ICON_INFORMATION
									| SWT.OK);
					mb.setMessage("所有字段必须填写");
					mb.open();
					return;
				}
				if (pwdok_text.getText().equals(pwd_text.getText())) {
					httpPost(id_text.getText(), pwd_text.getText(),
							pwdok_text.getText(), mail.getText(),qq.getText(),mobile.getText());
				} else {
					MessageBox mb = new MessageBox(
							RegDia.this.getParentShell(), SWT.ICON_INFORMATION
									| SWT.OK);
					mb.setMessage("\u5bc6\u7801\u524d\u540e\u4e0d\u4e00\u81f4");
					mb.open();
				}
				RegDia.this.close();
			}
		});
		button.setText("\u6CE8\u518C");
		Button button_1 = createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		button_1.setText("退出");
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}

	public void httpPost(String id, String pwd, String pwdok, String mail,String qq,String mobile) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(
				"http://121.127.253.207/yxlm/member/reg_new.php?dopost=regbase&step=1");

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("userid", id));
		nvps.add(new BasicNameValuePair("userpwd", pwd));
		nvps.add(new BasicNameValuePair("userpwdok", pwdok));
		nvps.add(new BasicNameValuePair("email", mail));
		nvps.add(new BasicNameValuePair("qq", qq));
		nvps.add(new BasicNameValuePair("mobile", mobile));
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			CloseableHttpResponse httppHttpResponse2 = httpClient
					.execute(httpPost);
			if (httppHttpResponse2.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String entity = EntityUtils.toString(httppHttpResponse2
						.getEntity());
				System.out.println(entity);
				JSONObject obj = new JSONObject(entity);
//				int value = Integer.parseInt(obj.get("value") + "");
				MessageBox mb = new MessageBox(
						RegDia.this.getParentShell(), SWT.ICON_INFORMATION
								| SWT.OK);
				mb.setMessage(obj.getString("msg"));// ע��ɹ�
				mb.open();
			} else {
				MessageBox mb = new MessageBox(RegDia.this.getParentShell(),
						SWT.ICON_INFORMATION | SWT.OK);
				mb.setMessage("error!StatusCode:"
						+ httppHttpResponse2.getStatusLine().getStatusCode());// http״̬�����
				mb.open();
			}

			httppHttpResponse2.close();
			httpClient.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
