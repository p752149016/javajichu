package fifteen;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

interface Addable<T> {void add(T t);}

//用适配器仿真潜在类型机制
class Fill {
	public static <T> void fill(Addable<T> addable, Class<? extends T> classToken, int size){
		for(int i = 0; i < size; i++){
			try{
				addable.add(classToken.newInstance());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
	public static <T> void fill(Addable<T> addable, Generator<T> generator, int size){
		for(int i = 0; i < size; i++){
			addable.add(generator.next());
		}
	}
}

class AddableCollectionAdapter<T> implements Addable<T>{
	private Collection<T> c;
	public AddableCollectionAdapter(Collection<T> c){
		this.c = c;
	}
	public void add(T t) {
		c.add(t);
	}
}

class Adapter{
	public static <T> Addable<T> collectionAdapter(Collection<T> c){
		return new AddableCollectionAdapter<>(c);
	}
}

class AddableSimpleQueue<T> extends SimpleQueue<T> implements Addable<T>{
	public void add(T t){super.add(t);}
}

class Coffee{
	
}
class Latte extends Coffee{
	
}
class Mocha extends Coffee{
	
}

public class Fill2{
	public static void main(String[] args){
		List<Coffee> carrier = new ArrayList<Coffee>();
		Fill.fill(new AddableCollectionAdapter<Coffee>(carrier), Coffee.class, 3);
		Fill.fill(Adapter.collectionAdapter(carrier), Latte.class, 2);
		for(Coffee c : carrier){
			System.out.println(c);
		}
		System.out.println("--------------");
		AddableSimpleQueue<Coffee> coffeeQueue = new AddableSimpleQueue<Coffee>();
		Fill.fill(coffeeQueue, Mocha.class, 4);
		Fill.fill(coffeeQueue, Latte.class, 1);
		for(Coffee c : coffeeQueue){
			System.out.println(c);
		}
	}
}
