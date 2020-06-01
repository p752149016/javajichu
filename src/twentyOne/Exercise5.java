package twentyOne;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import nineteen.Generator;

public class Exercise5 {
	public static void main(String[] args){
		ExecutorService exec = Executors.newCachedThreadPool();
		ArrayList<Future<String>> results = new ArrayList<>();
		for(int i = 1; i < 11; i++){
			results.add(exec.submit(new Exercise5Thread5(i)));
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
				exec.shutdown();
			}
		}
	}
}

/**
 * 当需要线程返回值时，可以实现Callable接口
 * @author PLF
 *
 */
class Exercise5Thread5 implements Generator<Integer>,Callable<String>{

	public int n;
	private int count = 0;
	public Exercise5Thread5(int n){
		this.n = n;
	}
	@Override
	public Integer next() {
		return fib(count++);
	}
	private int fib(int n){
		if(n < 2) return 1;
		return fib(n-2) + fib(n-1);
	}
	
	@Override
	public String call() throws Exception {
		Integer[] list = new Integer[n];
		for(int i = 0; i < n; i++){
			list[i] = next();
		}
		return Arrays.toString(list);
	}
}