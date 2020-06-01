package fifteen;

import java.util.ArrayList;
import java.util.Collection;

public class Generators {
	/**
	 * 生成器泛型方法（用于方便填充一个Collection）
	 * @param coll 容器对象
	 * @param gen  生成器对象
	 * @param n    填充的数量
	 * @return     Collection
	 */
	public static <T> Collection<T> fill(Collection<T> coll, Generator<T> gen, int n){
		for(int i = 0; i < n; i++){
			coll.add(gen.next());
		}
		return coll;
	}
	
	public static void main(String[] args){
		Collection<Integer> num = fill(new ArrayList<Integer>(), new Fibonacci(), 10);
		for(int i : num){
			System.out.println(i);
		}
	}

}
