import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Ceshi2 {
	
	//正则获得图片链接
		public static List<String> zhengZe(String str){
			List<String> imageSrcList = new ArrayList<String>();
			Pattern p = Pattern.compile("<img\\b[^>]*\\bsrc\\b\\s*=\\s*('|\")?([^'\"\n\r\f>]+(\\.jpg|\\.bmp|\\.eps|\\.gif|\\.mif|\\.miff|\\.png|\\.tif|\\.tiff|\\.svg|\\.wmf|\\.jpe|\\.jpeg|\\.dib|\\.ico|\\.tga|\\.cut|\\.pic)\\b)[^>]*>");
			Matcher m = p.matcher(str);
			String quote = null;
		    String src = null;
		    while (m.find()) {
		        quote = m.group(1);
		        src = (quote == null || quote.trim().length() == 0) ? m.group(2).split("\\s+")[0] : m.group(2);
		        imageSrcList.add(src);
		    }
		    return imageSrcList;
		}
	private String name;
	private Formatter f;
	public Ceshi2(String name, Formatter f){
		this.name = name;
		this.f = f;
	}
	public void move(int x, Float y){
		f.format("%-10.5s a %d,%010.3f\n", name, x, y);
	}
	public static boolean isNum(String str){
		try{
			Integer.parseInt(str);
			return true;
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
	public static void lianxi20(String s){
		Scanner scanner = new Scanner(s);
		
		int i = scanner.nextInt();
		long l = scanner.nextLong();
		float f = scanner.nextFloat();
		double d = scanner.nextDouble();
		String str = scanner.nextLine();
		System.out.println(i);
		System.out.println(l);
		System.out.println(f);
		System.out.println(d);
		System.out.println(str);
	}
    public static void main(String[] args) {
    	//正则匹配图片链接
//    	String str = "<img class='asd' src='/downloadfile/goods/xiangqing/31.jpg'><img src='/downloadfile/goods/xiangqing/32.jpg'><img src='/downloadfile/goods/xiangqing/33.jpg'>";
//    	for(String s : zhengZe(str)){
//    		System.out.println(s);
//    	}
    	//替换字符串
//    	String input = "This!!unusual use!!of exclamation!!points";
//    	System.out.println(Arrays.toString(input.split("!!")));
//    	System.out.println(Arrays.toString(input.split("!!", 3)));   
    	//读取文件夹下的文件名
//    	File[] files = new File("F:/PLF/java编程思想习题答案/chapter 13/Strings/13.15").listFiles();
//    	for(File file : files){
//    		System.out.println(file.getName() + "----------" + file.getPath());
//    	}
    	lianxi20("1 1000000000000 1.1 1.11 as");
    	
    }
}