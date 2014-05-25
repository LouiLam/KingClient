package ui;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
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
		
		Browser browser = new Browser(container, SWT.BORDER);
		GridData gd_browser = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_browser.widthHint = 425;
		gd_browser.heightHint = 222;
		browser.setLayoutData(gd_browser);
		gd_browser.widthHint = 423;
		gd_browser.heightHint = 225;
		browser.setUrl(url);
		browser.setJavascriptEnabled(true);

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

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}

}
