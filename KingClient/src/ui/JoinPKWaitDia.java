package ui;

import object.PKUser;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

public class JoinPKWaitDia extends WaitDia {
//	public JoinPKWaitDia(Shell parentShell) {
//		
//		super(parentShell,300);
//		// TODO Auto-generated constructor stub
//	}
	public JoinPKWaitDia(Shell parentShell,PKUser users[],int type,String area,String title,int point) {
		super(parentShell, users,60*5,type,area,title,point);
		// TODO Auto-generated constructor stub
	}
	@Override
	void createDialogAreaDoSomeThing(Composite container) {
		// TODO Auto-generated method stub
		
	}


	
}
