package twentyOne;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exercise4 {
	public static void main(String[] args){
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i = 1; i < 6; i++){
			exec.execute(new Exercise2Thread(i));
		}
		exec.shutdown();//防止新任务被提交给这个Executor
		
		ExecutorService exec2 = Executors.newFixedThreadPool(3);
		for(int i = 1; i < 6; i++){
			exec2.execute(new Exercise2Thread(i));
		}
		exec2.shutdown();
		
		ExecutorService exec3 = Executors.newSingleThreadExecutor();
		for(int i = 1; i < 6; i++){
			exec3.execute(new Exercise2Thread(i));
		}
		exec3.shutdown();
	}

}
