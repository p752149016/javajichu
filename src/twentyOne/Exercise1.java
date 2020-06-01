package twentyOne;

public class Exercise1 {
	public static void main(String[] args){
		for(int i = 0; i < 10; i++){
			new Thread(new inner(i+"")).start();
		}
	}
}

/**
 * .yield()是调度线程的一种建议，说明我已经执行完重要部分了，是切换线程给其他任务执行的大好时机
 * @author PLF
 *
 */
class inner implements Runnable{
	private String message;
	public inner(String message){
		this.message = message;
	}
	@Override
	public void run() {
		System.out.println(message);
		Thread.yield();
		System.out.println(message);
		Thread.yield();
		System.out.println(message);
		Thread.yield();
		System.out.println(message + " 执行完毕");
	}
}