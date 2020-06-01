package twentyOne;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//防止多线程在获取同一资源时导致的问题
public class Exercise11 implements Runnable{
	private int num1 = 0;
	private int num2 = 1;
	
	//synchronized 使方法同步，同一时刻下只有一个这个方法会被调用
	public synchronized void method(){
		num1++;
		Thread.yield();//增加线程混乱调用的可能性
		num2++;
		System.out.println("sum: " + num1 + " + " + num2 + " = " + (num1+num2));
	}

	@Override
	public void run() {
		method();
	}
	
	public static void main(String[] args){
		Exercise11 e = new Exercise11();
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i = 0; i < 10; i++){
			exec.submit(e);
		}
	}
}
