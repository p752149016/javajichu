package twentyOne;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//同一类中只能有一个被标记的方法被调用
class AtomicityTest2 implements Runnable {
	private int i;

	public synchronized int getValue() {
		return i;
	}

	private synchronized void evenIncrement() {
		i++;
		i++;
	}

	public void run() {
		while (true)
			evenIncrement();
	}
}

public class Exercise12 {
	public static void main(String[] args) {
		System.out.println("Press Control-C to exit");
		ExecutorService exec = Executors.newCachedThreadPool();
		AtomicityTest2 at = new AtomicityTest2();
		exec.execute(at);
		while (true) {
			int val = at.getValue();
			if (val % 2 != 0) {
				System.out.println(val);
				System.exit(0);
			}
		}
	}
}