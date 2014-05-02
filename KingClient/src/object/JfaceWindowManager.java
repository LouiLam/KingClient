package object;

import org.eclipse.jface.window.Window;
import org.eclipse.jface.window.WindowManager;

public class JfaceWindowManager {
	public static WindowManager wm=new WindowManager();
	public static Window getCurWindow()
	{
		return wm.getWindows()[wm.getWindowCount()-1];
	}
	public static void toMyString()
	{
		for (Window window : wm.getWindows()) {
			System.out.println(window.getClass().toString());
			
		}
	}
}
