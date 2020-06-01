package twentyOne;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Exercise17 {
	private int count = 0;
	private Random rand = new Random(47);
	
	public synchronized int increment(){
		int temp = count;
		if(rand.nextBoolean()){
			Thread.yield();
		}
		return count = ++temp;
	}
	public synchronized int value(){
		return count;
	}
	
	public static void main(String[] args) throws InterruptedException{
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i = 0; i < 5; i++){
			exec.execute(new Sensor());
		}
		TimeUnit.SECONDS.sleep(3);
		Sensor.off();
		exec.shutdown();
		if(!exec.awaitTermination(300, TimeUnit.MILLISECONDS)){
			System.out.println("在等待时间内有未终止的线程");
		}
		System.out.println("Total: " + Sensor.getCount());
	}
}

class Sensor implements Runnable{

	private static Exercise17 e = new Exercise17();
	private static volatile boolean canceled = true;
	@Override
	public void run() {
		while(canceled)
			e.increment();
	}
	
	public static int getCount(){
		return e.value();
	}
	public static void off(){
		canceled = false;
	}
}
