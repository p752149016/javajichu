package twentyOne;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exercise3 {
	public static void main(String[] args){
		ExecutorService exec = Executors.newCachedThreadPool(); //缓存线程池执行器（每个任务都创建一个线程）
		for(int i = 0; i < 5; i++){
			exec.execute(new inner(i+""));
		}
		exec.shutdown();//防止新任务被提交给这个Executor
		
		ExecutorService exec2 = Executors.newFixedThreadPool(3); //有限（固定）线程池执行器（固定线程的数量）
		for(int i = 0; i < 5; i++){
			exec2.execute(new inner(i+""));
		}
		exec2.shutdown();
		
		ExecutorService exec3 = Executors.newSingleThreadExecutor(); //单列线程池执行器（只有一个线程）
		for(int i = 0; i < 5; i++){
			exec3.execute(new inner(i+""));
		}
		exec3.shutdown();
	}
}