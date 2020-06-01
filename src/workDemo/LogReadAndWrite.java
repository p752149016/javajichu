package workDemo;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import com.google.gson.stream.JsonReader;

/*
 * 用于机顶盒更新本地服务
 * 通过类似数据库同步的方式，读取指定位置日志，进行更新
 * 
 */
public class LogReadAndWrite {
	
	public static void write(String filePath, String str) throws IOException{
		File file = new File(filePath);
		FileUtil ob = new FileUtil(filePath, file.length());
		byte[] data = (str+"\r\n").getBytes();
		ob.write(data, 0, data.length);
	}
	
	public static List<String> read(String filePath, long startPos) throws IOException{
		FileUtil ob = new FileUtil(filePath, startPos);
		return ob.read();
	}
	
	public static void main(String[] args) throws IOException{
		
		String filePath = "C:\\Users\\Administrator\\Desktop\\工作\\aaa\\test.json";
		
//		write("C:\\Users\\Administrator\\Desktop\\工作\\test.txt", "{\"package\":\"wel.newlive.philtv\",\"downloadlink\":\"http://192.168.0.10:8082/newlauncher/apks/wel.newlive.philtv.01.1.01.20161215\"}");
		String jsonStr = read(filePath, 0).get(0);
		String res = jsonStr.substring(10, jsonStr.length()-1);
		System.out.println(res);
		JsonReader reader = new JsonReader(new StringReader(res));
		reader.beginObject();
		while(reader.hasNext()){
			String name = reader.nextName();
			if(name.equals("data")){
				System.out.println(name);
				reader.beginObject();
				System.out.println(reader.nextName());
				reader.skipValue();
				System.out.println(reader.nextName());
				reader.beginObject();
				System.out.println(reader.nextName());
				System.out.println(reader.nextString());
				System.out.println("end");
			}else{
				System.out.println(name);
				System.out.println(reader.nextString());
			}
			
		}
	}
}
