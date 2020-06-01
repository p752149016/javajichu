package twentyOne;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

//Future在Java8中已经不鼓励使用，鼓励使用Java8中的CompletableFuture
public class Exercise10 {
	public static void main(String[] args){
		ArrayList<Future<String>> results = new ArrayList<>();
		ThreadMethod.init();
		for(int i = 1; i < 11; i++){
			results.add(ThreadMethod.runTask(i));
		}
		
		for(Future<String> fs : results){
			try {
				System.out.println(fs.get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				ThreadMethod.shutdown();
			}
		}
	}
}

class ThreadMethod{
	private static ExecutorService exec;
	public static void init(){
		if(exec == null){
			exec = Executors.newCachedThreadPool();
		}
	}
	public static void shutdown(){
		if(exec != null){
			exec.shutdown();
		}
	}
	public static synchronized Future<String> runTask(final int num){
		return exec.submit(new Callable<String>(){
			private int count = 0;
			private Integer[] list;
			public Integer next() {
				return fib(count++);
			}
			private int fib(int n){
				if(n < 2) return 1;
				return fib(n-2) + fib(n-1);
			}
			public String toString(){
				return Arrays.toString(list);
			}
			@Override
			public String call() throws Exception {
				list = new Integer[num];
				for(int i = 0; i < num; i++){
					list[i] = next();
				}
				return Arrays.toString(list);
			}
		});
	}
}