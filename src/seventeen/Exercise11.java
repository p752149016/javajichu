package seventeen;

import java.util.PriorityQueue;
import java.util.Random;

public class Exercise11 {
	static class Item implements Comparable<Item>{
		int i;
		Item(int x){
			this.i = x;
		}
		@Override
		public int compareTo(Item arg) {
			return i > arg.i ? 1 : (i == arg.i ? 0 : -1);
		}
		public String toString(){
			return i+"";
		}
	}
	public static void main(String[] args){
		Random rand = new Random();
		PriorityQueue<Item> queue = new PriorityQueue<>();
		for(int i = 0; i < 10; i++){
			queue.add(new Item(rand.nextInt(100)));
		}
		while(!queue.isEmpty()){
			System.out.println(queue.poll());
		}
		
	}
}
