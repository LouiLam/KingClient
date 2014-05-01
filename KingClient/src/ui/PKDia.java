package ui;

import java.util.ArrayList;
import java.util.List;

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

public class PKDia extends Dialog {
	private Text title;
	private Combo area;
	private Combo map;
	private Combo point;
	private Combo type;
	private Text des;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public PKDia(Shell parentShell) {
		super(parentShell);
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
		area.add("艾欧尼亚---电信");
		area.add("祖安---电信");
		area.add("诺克萨斯---电信");
		area.add("班德尔城---电信");
		area.add("皮尔特沃夫---电信");
		area.add("战争学院---电信");
		area.add("巨神峰---电信");
		area.add("雷瑟守备---电信");
		area.add("钢铁烈阳---电信");
		area.add("裁决之地---电信");
		area.add("黑色玫瑰---电信");
		area.add("暗影岛---电信");
		area.add("均衡教派---电信");
		area.add("水晶之痕---电信");
		area.add("影流---电信");
		area.add("守望之海---电信");
		area.add("征服之海---电信");
		area.add("卡拉曼达---电信");
		area.add("皮城警备---电信");

		area.add("比尔吉沃特---网通");
		area.add("德玛西亚---网通");
		area.add("费雷尔卓德---网通");
		area.add("无畏先锋---网通");
		area.add("怒瑞玛---网通");
		area.add("扭曲丛林---网通");
		area.select(0);
		new Label(container, SWT.NONE);

		Label label3 = new Label(container, SWT.NONE);
		label3.setText("挑战地图");
		new Label(container, SWT.NONE);

		map = new Combo(container, SWT.READ_ONLY);
		map.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		map.add("扭曲丛林");
		map.add("召唤师峡谷");
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
				if (title.getText() == null || title.getText().length() == 0
						|| des.getText() == null || des.getText().length() == 0) {
					MessageBox mb = new MessageBox(PKDia.this.getParentShell(),
							SWT.ICON_INFORMATION | SWT.OK);
					mb.setMessage("填写字段不能为空");
					mb.open();
					return;
				}
//				httpPostFightAdd();
				PK pk = new PK(KingLogin.name, title.getText(), area.getText(),
						map.getText(),des.getText(),type.getSelectionIndex() +1,
						Integer.parseInt(point.getText()),-1);
				GameClient.getInstance().sendMessageToGameServer(
						new CreatePKMessage1002(pk));
				PKDia.this.close();
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
		return new Point(450, 300);
	}
	public void httpPostFightAdd() {
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(
				
				"http://www.woowgo.com/yxlm/member/fight_add.php");

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
				MessageBox mb = new MessageBox(PKDia.this.getParentShell(),
						SWT.ICON_INFORMATION | SWT.OK);
				mb.setMessage(entity);
				mb.open();
			}
			else
			{
				MessageBox mb = new MessageBox(PKDia.this.getParentShell(),
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
