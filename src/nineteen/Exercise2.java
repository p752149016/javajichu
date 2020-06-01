package nineteen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

//使用static方法实现生成器
enum CartoonCharacter{
	SLAPPY, SPANKY, PUNCHY, SILLY, BOUNCY, NUTTY, BOB;
	private static Random rand = new Random(47);
	public static CartoonCharacter next() {
		return values()[rand.nextInt(values().length)];
	}
}

public class Exercise2{
	public static void printNext(){
		System.out.print(CartoonCharacter.next() + ",");
	}
	public String returnNext(){
		return CartoonCharacter.next() + "";
	}
	public static void main(String[] args){
		for(int i = 0; i < 10; i++){
			printNext();
		}
	}
}
