package seventeen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

class Asdsdsds{
	public static String str = "asd";
}

public class Exercise1 {
	public static void main(String[] args){
		List<String> list = Arrays.asList("a", "b", "c", "d", "e", "f", "h", "g");
		Collections.sort(list);
		System.out.println("sort:" + list);
		for(int i = 0; i < 5; i++){
			Collections.shuffle(list);
			System.out.println("shuffle(" + i + "):" + list);
		}
	}
}
