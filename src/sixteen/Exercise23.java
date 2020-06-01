package sixteen;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Exercise23 {
	public static void main(String[] args){
		Random random = new Random();
		Integer[] array = new Integer[10];
		for(int i = 0; i < 10; i++){
			array[i] = random.nextInt(1000);
		}
		System.out.println("随机的：" + Arrays.toString(array));
		Arrays.sort(array);
		System.out.println("正序：" + Arrays.toString(array));
		Arrays.sort(array, Collections.reverseOrder());
		System.out.println("逆序：" + Arrays.toString(array));
	}
}
