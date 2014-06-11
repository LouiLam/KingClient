package object;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import ui.KingLogin;

public class TaskScheduled {

	private static ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(1);
	private static ScheduledThreadPoolExecutor schedulerForever = new ScheduledThreadPoolExecutor(1);
	/**
	 * 以固定速率执行周期任务
	 */
	public static ScheduledFuture<?> scheduleAtFixedRate(Runnable command,long initialDelay,long period,TimeUnit unit){
		return scheduler.scheduleAtFixedRate(command, initialDelay, period, unit);
	}
	/**
	 * 以固定速率执行周期任务
	 */
	public static ScheduledFuture<?> scheduleAtFixedRateForever(Runnable command,long initialDelay,long period,TimeUnit unit){
		return schedulerForever.scheduleAtFixedRate(command, initialDelay, period, unit);
	}
	/**
	 * 执行启动延时任务
	 */
	public static ScheduledFuture<?> schedule(Runnable command,long delay,TimeUnit unit){
		return scheduler.schedule(command, delay, unit);
	}

	/**
	 * 取消定时任务
	 */
	public static boolean cancel(ScheduledFuture<?> scheduled){
		if(scheduled == null) return false;
		return scheduled.cancel(true);
	}
	public static void clear()
	{
		System.out.println("时间定时器clear"+KingLogin.id);
		scheduler.shutdown();
		scheduler= new ScheduledThreadPoolExecutor(1);
	}
	public static void toCount()
	{
		System.out.println("当前激活线程数量"+scheduler.getActiveCount());
	}

}