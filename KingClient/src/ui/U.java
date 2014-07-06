package ui;

import org.eclipse.swt.widgets.Display;

public class U {
	private static String str;
	static Runnable r=new Runnable() {
		
		@Override
		public void run() {
//			DaPaoMain.text.append("================================================\r\n");
//			DaPaoMain.text.append(DateUtil.getCurDate()+":"+str+"\r\n");
			
		}
	};
	public static void info(String str)
	{
//		DaPaoMain.text.append("================================================\r\n");
//		DaPaoMain.text.append(DateUtil.getCurDate()+":"+str+"\r\n");
	}
	public static void infoQueue(String str)
	{
		U.str=str;
		Display.getDefault().syncExec(r);
	}
	
}
