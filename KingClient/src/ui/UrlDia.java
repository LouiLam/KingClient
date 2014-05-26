package ui;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class UrlDia extends Dialog {

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	private String url;
	public UrlDia(Shell parentShell,String url) {
		super(parentShell);
		this.url=url;
	}
	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(null);
		Browser browser = new Browser(container, SWT.BORDER);
//		GridData gd_browser = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
//		gd_browser.widthHint = 1024;
//		gd_browser.heightHint = 768;
		browser.setBounds(0, 0, 1024, 768);
//		browser.setLayoutData(gd_browser);
		browser.setUrl(url);
		browser.setJavascriptEnabled(true);
//		container.getShell().setSize(1024, 768);
		return container;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		
//		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
//				true);
//		createButton(parent, IDialogConstants.CANCEL_ID,
//				IDialogConstants.CANCEL_LABEL, false);
	}
@Override
protected Button createButton(Composite parent, int id, String label,
		boolean defaultButton) {
	// TODO Auto-generated method stub
	return null;
}
	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(1024, 768);
	}

}
