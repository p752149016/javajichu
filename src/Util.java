import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Util {

	public static String readFile(String filePath){
		String s = null;
		try{
			FileReader reader = new FileReader(filePath);
			BufferedReader br = new BufferedReader(reader);
			
			StringBuffer lineBuffer = new StringBuffer();
			String line = null;
			while ((line = br.readLine()) != null){
				lineBuffer.append(line);
//				lineBuffer.append("\n");
			}
			s = lineBuffer.toString();
			reader.close();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
		}
		return s;
	}
	
	public static void main(String[] args){
		System.out.println(readFile("C:\\Users\\Administrator\\Desktop\\工作\\test.txt"));
	}
}
