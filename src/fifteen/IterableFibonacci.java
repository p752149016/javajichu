package fifteen;

import java.util.Iterator;

//使用适配器的方式编写实现Iterable的Fibonacci生成器
public class IterableFibonacci extends Fibonacci implements Iterable<Integer>{

	private int n;
	public IterableFibonacci(int count){
		n = count;
	}
	@Override
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {
			@Override
			public boolean hasNext() {
				return n > 0;
			}
			@Override
			public Integer next() {
				n--;
				return IterableFibonacci.this.next();
			}
			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}
	public static void main(String[] args){
		for(int i : new IterableFibonacci(10)){
			System.out.println(i);
		}
	}
}
