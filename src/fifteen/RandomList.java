package fifteen;

import java.util.ArrayList;
import java.util.Random;

public class RandomList<T> {

	private ArrayList<T> storage = new ArrayList<T>();
	private Random rand = new Random(47);
	public void add(T t){
		storage.add(t);
	}
	public T select(){
		return storage.get(rand.nextInt(storage.size()));
	}
	public static void main(String[] args){
		RandomList<Integer> list = new RandomList<Integer>();
		for(int i = 0; i < 10; i++){
			list.add(i);
		}
		for(int i = 0; i < 10; i++){
			System.out.println(list.select());
		}
	}
}
