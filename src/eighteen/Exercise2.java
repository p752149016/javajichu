package eighteen;

import java.io.File;
import java.util.Arrays;

//获取规定正则表达式的文件名
public class Exercise2 {
	File file;
	public Exercise2(String path){
		file = new File(path);
	}
	public String[] list(){
		String[] list = file.list();
		Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
		return list;
	}
	public String[] list(String regex){
		String[] list = file.list(new DirFilter(regex));
		Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
		return list;
	}
	
	public static void main(String[] args){
		Exercise2 exercise = new Exercise2("F:/PLF");
		System.out.println(Arrays.toString(exercise.list()));
		System.out.println(Arrays.toString(exercise.list(".*\\.txt")));
	}
}
