package workDemo;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 * 存储文件工具类
 *
 */
public class FileUtil{
	
	private RandomAccessFile file; 
	private long startPos; // 文件存储的起始位置
	
	public FileUtil(String fileName, long startPos) throws IOException{
		file = new RandomAccessFile(fileName, "rw");
		this.startPos = startPos;
		file.seek(startPos);
	}
	
	public synchronized int write(byte[] data, int start, int len){
		int res = -1;
		try {
			file.write(data, start, len);
			res = len;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public List<String> read(){
		List<String> res = new ArrayList<>();
		try{
			String s;
			while((s=file.readLine()) != null){
				res.add(new String(s.getBytes("ISO-8859-1")));//转码，否则中文乱码
				//new String(s.getBytes("ISO-8859-1"), "utf-8");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}
}
