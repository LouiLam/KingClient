package ui;

import java.util.Calendar;

import object.JfaceWindowManager;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class DateTimeDia extends Dialog {
	Label queryTime;
	public static long timeMillis;
	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 * @param lblNewLabel_1 
	 */
	public DateTimeDia(Shell parentShell, Label queryTime) {
		super(parentShell);
		setWindowManager(JfaceWindowManager.wm);
		this.queryTime=queryTime;
	}
	DateTime calendar, date, time;

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		GridLayout gridLayout = (GridLayout) container.getLayout();
		gridLayout.numColumns = 2;
		calendar = new DateTime(container, SWT.CALENDAR | SWT.BORDER);
		date = new DateTime(container, SWT.DATE | SWT.SHORT);
		time = new DateTime(container, SWT.TIME | SWT.SHORT);

		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);


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
				Calendar fromCalendar = Calendar.getInstance();
				fromCalendar.set(calendar.getYear(), calendar.getMonth(), calendar.getDay(), time.getHours(), time.getMinutes());
				timeMillis=fromCalendar.getTimeInMillis();
				System.out.println(fromCalendar.getTimeInMillis());
				queryTime.setText(calendar.getYear()+"-"+(calendar.getMonth()+1)+"-"+ calendar.getDay()+" "+time.getHours()+":"+ time.getMinutes()+":"+time.getSeconds());
				queryTime.setVisible(true);
//				System.out.println(" Calendar date selected (MM/DD/YYYY) = "
//						+ (calendar.getMonth() + 1) + " / " + calendar.getDay()
//						+ " / " + calendar.getYear());
//				System.out.println(" Date selected (MM/YYYY) = "
//						+ (date.getMonth() + 1) + " / " + date.getYear());
//				System.out.println(" Time selected (HH:MM) = "
//						+ time.getHours() + " : " + time.getMinutes()+":"+time.getSeconds());
				DateTimeDia.this.close();
			
			}
		});
		Button button1 =createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
		button1.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}

}
