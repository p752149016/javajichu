package twentyOne;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

//如何捕获线程异常
public class ExceptionThread implements Runnable{

	//无法捕获异常
//	@Override
//	public void run() {
//		System.out.println("---");
//		throw new RuntimeException();
//	}
	
	@Override
	public void run() {
		Thread t = Thread.currentThread();
		System.out.println("--- " + t);
		System.out.println(t.getUncaughtExceptionHandler());
		throw new RuntimeException();
	}
	
	
	public static void main(String[] args){
		//未捕获到异常，没有输出asd
		try {
			ExecutorService exec = Executors.newCachedThreadPool(new HandlerThreadFactory());
			exec.execute(new ExceptionThread());
		} catch (Exception e) {
			System.out.println("asd");
			e.printStackTrace();
		}
		
	}
}

//未捕获异常处理器，线程异常抛出后就会调用此类的uncaughtException方法
class MyUncaughtExceptionHandler implements UncaughtExceptionHandler{
	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println("Caugth " + e);
	}
}

//需要将这个类传给线程池，这个类需要将UncaughtExceptionHandler  set给线程，使线程在抛出异常时调用uncaughtException方法
class HandlerThreadFactory implements ThreadFactory{
	@Override
	public Thread newThread(Runnable r) {
		System.out.println("creat new thread");
		Thread t = new Thread(r);
		t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
		return t;
	}
}