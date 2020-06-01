package sixteen;

import java.util.Arrays;
import java.util.Locale;

public class Exercise4 {
	public double[][][] creat(int raw1, int raw2, int raw3, double start, double end){
		double[][][] array = new double[raw1][raw2][raw3];
		double increment = (end - start)/(raw1 * raw2);
		double val = start;
		for(int i = 0; i < array.length; i++){
			for(int j = 0; j < array[i].length; j++){
				for(int x = 0; x < array[i][j].length; x++){
					array[i][j][x] = val;
					val += increment;
				}
				
			}
		}
		return array;
	}
	public void print(double[][][] array){
		for(int i = 0; i < array.length; i++){
			for(int j = 0; j < array[i].length; j++){
				for(int x = 0; x < array[i][j].length; x++){
					System.out.printf(Locale.US, "%.2f ", array[i][j][x]);
				}
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args){
		Exercise4 ex3 = new Exercise4();
		ex3.print(ex3.creat(5, 5, 5, 0.22, 0.33));
//		ex3.print(ex3.creat(8, 6, 6, 0.25, 0.73));
		
		Integer[][] a = new Integer[3][3];
		System.out.println(Arrays.deepToString(a));
	}
}
