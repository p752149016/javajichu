package eighteen;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//对象序列化简单Demo
//可以通过读取序列化的文件，反序列化后和普通对象一样使用
public class Exercise27 implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int i;
	private String s;
	
	public Exercise27(int i, String s) {
		this.i = i;
		this.s = s;
	}
	
	public static void main(String[] args) throws Exception{
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Exercise.out"));
		out.writeObject(new Exercise27(1 , "s"));
		out.close();
		
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("Exercise.out"));
		Exercise27 e = (Exercise27) in.readObject();
		System.out.println(e.i);
		System.out.println(e.s);
		System.out.println(e.getClass());
	}
}
