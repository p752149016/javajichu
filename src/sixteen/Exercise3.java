package sixteen;

import java.util.Locale;

public class Exercise3 {
	public double[][] creat(int raw1, int raw2, double start, double end){
		double[][] array = new double[raw1][raw2];
		double increment = (end - start)/(raw1 * raw2);
		double val = start;
		for(int i = 0; i < array.length; i++){
			for(int j = 0; j < array[i].length; j++){
				array[i][j] = val;
				val += increment;
			}
		}
		return array;
	}
	public void print(double[][] array){
		for(int i = 0; i < array.length; i++){
			for(int j = 0; j < array[i].length; j++){
				System.out.printf(Locale.US, "%.2f ", array[i][j]);
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args){
		Exercise3 ex3 = new Exercise3();
		ex3.print(ex3.creat(5, 5, 0.22, 0.33));
		ex3.print(ex3.creat(8, 6, 0.25, 0.73));
	}
}
