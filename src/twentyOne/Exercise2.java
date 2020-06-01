package twentyOne;

import java.util.Arrays;

import nineteen.Generator;

public class Exercise2 {
	public static void main(String[] args){
		for(int i = 1; i < 6; i++){
			new Thread(new Exercise2Thread(i)).start();
		}
	}
}

class Exercise2Thread implements Generator<Integer>,Runnable{

	public int n;
	private int count = 0;
	public Exercise2Thread(int n){
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
	public void run() {
		Integer[] list = new Integer[n];
		for(int i = 0; i < n; i++){
			list[i] = next();
		}
		System.out.println(Arrays.toString(list));
	}
	
}