package twentyOne;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exercise15 {

	private int x = 0;
	private int y = 0;
	
	public void add1(){
		synchronized(Run1.class){
			x++;
			y++;
		}
	}
	public void add2(){
		synchronized(Run2.class){
			x--;
			y--;
		}
	}
	public void add3(){
		synchronized(Run3.class){
			if(x != y){
				System.out.println("error:x= " + x + " y=" + y);
			}
		}
	}
	
	public static void main(String args[]){
		Exercise15 e = new Exercise15();
		Run1 r1 = new Run1(e);
		Run2 r2 = new Run2(e);
		Run3 r3 = new Run3(e);
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(r1);
		exec.execute(r2);
		exec.execute(r3);
		
	}
	
}

class Run1 implements Runnable{
	private Exercise15 e;
	public Run1(Exercise15 e){
		this.e = e;
	}
	@Override
	public void run() {
		while(true)
			e.add1();
	}
}
class Run2 implements Runnable{
	private Exercise15 e;
	public Run2(Exercise15 e){
		this.e = e;
	}
	@Override
	public void run() {
		while(true)
			e.add2();
	}
}
class Run3 implements Runnable{
	private Exercise15 e;
	public Run3(Exercise15 e){
		this.e = e;
	}
	@Override
	public void run() {
		while(true)
			e.add3();
	}
}