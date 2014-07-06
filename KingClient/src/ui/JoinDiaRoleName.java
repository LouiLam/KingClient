package ui;

import object.JfaceWindowManager;
import object.PK;
import object.PKManager;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
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

import send.JoinPKMessage1003;

import com.zjd.universal.net.GameClient;

public class JoinDiaRoleName extends Dialog {
	private Text roleName;
	private int index,camp;
	private String password;
	private Text passwordText;
	private boolean shaixuanState;
	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public JoinDiaRoleName(Shell parentShell,int index,int camp,String password,boolean shaixuanState) {
		super(parentShell);
		setWindowManager(JfaceWindowManager.wm);
		this.index=index;
		this.camp=camp;
		this.password=password;
		this.shaixuanState=shaixuanState;
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

		
		Label label = new Label(container, SWT.NONE);
		label.setText("游戏角色名");
		new Label(container, SWT.NONE);
		
		roleName = new Text(container, SWT.BORDER);
		roleName.setTextLimit(50);
		roleName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(container, SWT.NONE);
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setText("此房间需要密码");
		new Label(container, SWT.NONE);
		passwordText = new Text(container, SWT.BORDER | SWT.PASSWORD);
		passwordText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		if(password.equals(""))
		{
			lblNewLabel.setVisible(false);
			passwordText.setVisible(false);
		}
		
		
		
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
				if (roleName.getText()==null||roleName.getText().trim().length()==0) {
					MessageBox mb = new MessageBox(JoinDiaRoleName.this.getParentShell(),
							SWT.ICON_INFORMATION | SWT.OK);
					mb.setMessage("填写字段不能为空");
					mb.open();
					return;
				}
				KingLogin.roleName=roleName.getText();
				String str=null;
				if(!passwordText.getVisible())
				{
					str="";
				}
				else
				{
					str=passwordText.getText();
				}
				PK pk=null;
				if(shaixuanState)
				{ pk = PKManager.getInstance().getFilterPKByIndex(index);}
				else
				{ pk = PKManager.getInstance().getPKByIndex(index);}
				
				System.out.println("加入房间sqlid"+pk.sql_id);
				GameClient.getInstance().sendMessageToGameServer(
						new JoinPKMessage1003(pk.sql_id,camp,KingLogin.id,
								KingLogin.roleName,str));

			
				JoinDiaRoleName.this.close();
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
		return new Point(537, 165);
	}
}
