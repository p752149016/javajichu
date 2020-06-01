package sixteen;

import java.util.Arrays;

class Beryllium {
	static long counter;
	public String toString() { return "Sphere " + counter; }
}

public class Exercise18 {
	@SuppressWarnings("static-access")
	public static void main(String[] args){
		Beryllium[] array = new Beryllium[10];
		Arrays.fill(array, new Beryllium());
		System.out.println("array: " + Arrays.toString(array));
		Beryllium[] array2 = new Beryllium[10];
		System.out.println("array2: " + Arrays.toString(array2));
		System.arraycopy(array, 0, array2, 0, array.length);//这个方法是浅拷贝，拷贝数组中对象的引用
		System.out.println("拷贝后array2:  " + Arrays.toString(array2));
		array2[5].counter = 5;
		System.out.println("改变后array:  " + Arrays.toString(array));
		System.out.println("改变后array2:  " + Arrays.toString(array2));
	}
}
