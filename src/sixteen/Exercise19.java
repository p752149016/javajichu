package sixteen;

import java.util.Arrays;

class ArrayInt{
	protected int count;
	public ArrayInt(int i) {
		this.count = i;
	}
	public String toString(){
		return count + "";
	}
}
class ArrayInt2 extends ArrayInt{
	public ArrayInt2(int i) {
		super(i);
	}
	public boolean equals(Object o){
		return o instanceof ArrayInt2 && count == ((ArrayInt)o).count;
	}
}

public class Exercise19 {
	public static void main(String[] args){
		ArrayInt[] array = new ArrayInt[5];
		ArrayInt[] array2 = new ArrayInt[5];
		Arrays.fill(array, new ArrayInt(3));
		Arrays.fill(array2, new ArrayInt(3));
		System.out.println(Arrays.equals(array, array2));
		Arrays.fill(array, new ArrayInt2(3));
		Arrays.fill(array2, new ArrayInt2(3));
		System.out.println(Arrays.equals(array, array2));
	}
}
