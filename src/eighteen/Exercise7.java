package eighteen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;

public class Exercise7 {
	public static LinkedList<String> read(String path) throws Exception{
		BufferedReader in = new BufferedReader(new FileReader(path));
		LinkedList<String> list = new LinkedList<>();
		String s;
		while((s = in.readLine()) != null){
			list.add(s + "\n");
		}
		in.close();
		return list;
	}
	
	public static void main(String[] args) throws Exception{
		LinkedList<String> list = read("F:/PLF/英语单词本.txt");
		for(int i = list.size() - 1; i >= 0; i--){
			//显示有查找单词的行
			if(list.get(i).indexOf("and") != -1){
				System.out.print(list.get(i).toUpperCase());
			}
		}
		System.out.println(System.getProperty("file.encoding"));
	}
}
