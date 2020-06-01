import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeSet;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

class LoggingException extends Exception{
	private static Logger logger = Logger.getLogger("LoggingException");
	public LoggingException(){
		StringWriter trace = new StringWriter();
		printStackTrace(new PrintWriter(trace));
		logger.severe(trace.toString());
	}
}

class new1Exception extends Exception{}
class new2Exception extends Exception{}


public class Ceshi {
	void g() throws new1Exception{
		throw new new1Exception();
	}
	void f(){
		try{
			g();
		}catch (new1Exception e) {
			throw new RuntimeException();
		}
	}
	public void createHtml(String interfaceName, String mac) throws Exception{
		InputStream inputStream;//接收字节输入流
		InputStreamReader inputStreamReader;//将字节输入流转换成字符输入流
	    BufferedReader bufferedReader;//为字符输入流加缓冲
	    FileOutputStream fileOutputStream;//字节输出流
	    OutputStreamWriter outputStreamWriter;//将字节输出流转换成字符输出流
	    
	    URL url = new URL("http://192.168.0.10:8085/OrderDinner/" + interfaceName + "?" + mac);
	    inputStream = url.openStream();
	    inputStreamReader = new InputStreamReader(inputStream, "utf-8");
	    bufferedReader = new BufferedReader(inputStreamReader);
	    String s;
	    File dest = new File("E:\\upload/downloadfile/zipFile/Introduce/Introduce.html");
	    fileOutputStream = new FileOutputStream(dest);
	    outputStreamWriter = new OutputStreamWriter(fileOutputStream, "utf-8");
	    while ((s = bufferedReader.readLine()) != null){
	    	System.out.println(s);
	    	outputStreamWriter.write(s);
	    }

        outputStreamWriter.close();
        fileOutputStream.close();
        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();
	}
//	public void way_2(String interfaceName, String mac) throws Exception{
//	    File dest = new File("F:\\PLF\\creatHtml\\ceshi2.html");
//	    InputStream is;//接收字节输入流
//	    FileOutputStream fos = new FileOutputStream(dest);//字节输出流
//	 
//	    URL wangyi = new URL("http://localhost:8080/OrderDinner/" + interfaceName + "?" + mac);
//	    is = wangyi.openStream();
//	 
//	    BufferedInputStream bis = new BufferedInputStream(is);//为字节输入流加缓冲
//	    BufferedOutputStream bos = new BufferedOutputStream(fos);//为字节输出流加缓冲
//	 
//	    int length;
//	 
//	    byte[] bytes = new byte[1024*20];
//	    while((length = bis.read(bytes, 0, bytes.length)) != -1){
//	        fos.write(bytes, 0, length);
//	    }
//	 
//	    bos.close();
//	    fos.close();
//	    bis.close();
//	    is.close();
//	}
	
	//去除html自带的图片链接和css、js
	public static String getImgPath(String str){
		String s = getImgPathZC(str);
		if(s != null && Pattern.matches("^\\..*", s)){
			return null;
		}else{
			return s;
		}
	}
	
	//获得图片链接
	public static String getImgPathZC(String str){
		if(str != null){
			String pattern = "<img\\b[^>]*\\bsrc\\b\\s*=\\s*('|\")?([^'\"\n\r\f>]+(\\.jpg|\\.bmp|\\.eps|\\.gif|\\.mif|\\.miff|\\.png|\\.tif|\\.tiff|\\.svg|\\.wmf|\\.jpe|\\.jpeg|\\.dib|\\.ico|\\.tga|\\.cut|\\.pic)\\b)[^>]*>";
			Pattern r = Pattern.compile(pattern);
			Matcher m = r.matcher(str);
			if(m.find()){
				String quote = m.group(1);
				String src = (quote == null || quote.trim().length() == 0) ? m.group(2).split("\\s+")[0] : m.group(2);
				return src;
			}else{
				return null;
			}
		}else{
			return null;
		}
	}
	
	//获取图片链接中的图片名称
	public static String getImgName(String str){
		if(str != null){
			String pattern = ".*/(.*)";
			Pattern r = Pattern.compile(pattern);
			Matcher m = r.matcher(str);
			if(m.find()){
				return m.group(1);
			}else{
				return null;
			}
		}else{
			return null;
		}
	}
	
	public static String getFileMD5(File file) {
	    if (!file.isFile()) {
	        return null;
	    }
	    MessageDigest digest = null;
	    FileInputStream in = null;
	    byte buffer[] = new byte[1024];
	    int len;
	    try {
	        digest = MessageDigest.getInstance("MD5");
	        in = new FileInputStream(file);
	        while ((len = in.read(buffer, 0, 1024)) != -1) {
	            digest.update(buffer, 0, len);
	        }
	        in.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	    return bytesToHexString(digest.digest());
	}

	public static String bytesToHexString(byte[] src) {
	    StringBuilder stringBuilder = new StringBuilder("");
	    if (src == null || src.length <= 0) {
	        return null;
	    }
	    for (int i = 0; i < src.length; i++) {
	        int v = src[i] & 0xFF;
	        String hv = Integer.toHexString(v);
	        if (hv.length() < 2) {
	            stringBuilder.append(0);
	        }
	        stringBuilder.append(hv);
	    }
	    return stringBuilder.toString();
	}

	public static void main(String[] args) throws Exception{
//		Ceshi ceshi = new Ceshi();
//		ceshi.way_2("Introduce","name=F0CEEE000000");
//		ceshi.createHtml("Introduce","name=F0CEEE08961F");
//		File file = new File("E:/upload/downloadfile/zipFile/mierdun/Introduce/Introduce.zip");
//		System.out.println(file.lastModified());
//		if(file.lastModified() < System.currentTimeMillis()){
//			System.out.println("aaaaa");
//		}
//		Ceshi ceshi = new Ceshi();
//		
//			ceshi.f();
//		//检验文件是否存在，不存在就创建（只能创建一层）
//		File LocalPath=new File("E:/upload/downloadfile/zipFileSource/Introduce/aaa");
//		if (!LocalPath.exists()) {
//			LocalPath.mkdir();
//			System.out.println("aaaaaaaaaa");
//		}
		//文件md5生成
		String md5 = DigestUtils.md5Hex(new FileInputStream("E:/upload/downloadfile/zipFile/yuanfang13/Order.zip"));
		System.out.println(md5);
//		System.out.println(getFileMD5(new File("E:/upload/downloadfile/zipFile/yuanfang13/Introduce.zip")));
		//操作系统分隔符
//		System.out.println(System.getProperty("file.separator"));
//		if("\\".equals(System.getProperty("file.separator").toString())){
//			System.out.println(1);
//		}else{
//			System.out.println(2);
//		}
////		创建文件并写入内容
//		String newlines = "\r\n";
//		String filePath = "F:/PLF/asd";
//		FileInputStream fis = null;
//        InputStreamReader isr = null;
//        BufferedReader br = null;
//        FileOutputStream fos  = null;
//        PrintWriter pw = null;
//		File file = new File(filePath);
////		if(!file.exists()){
//			file.createNewFile();
//			try{
//				//将文件读入输入流
//	            fis = new FileInputStream(file);
//	            isr = new InputStreamReader(fis);
//	            br = new BufferedReader(isr);
//	            StringBuffer buffer = new StringBuffer();
//	            buffer.append("asd");
//	            buffer.append(newlines + "bbbbbbbbbbbb");
//	            fos = new FileOutputStream(file);
//	            pw = new PrintWriter(fos);
//	            pw.write(buffer.toString().toCharArray());
//	            pw.flush();
//			}catch(Exception e){
//				e.printStackTrace();
//			}finally{
//				//不要忘记关闭
//	            if (pw != null) {
//	                pw.close();
//	            }
//	            if (fos != null) {
//	                fos.close();
//	            }
//	            if (br != null) {
//	                br.close();
//	            }
//	            if (isr != null) {
//	                isr.close();
//	            }
//	            if (fis != null) {
//	                fis.close();
//	            }
//			}
//		}
//		//字符串分割
//		String str = "s,4";
//		String[] strList = str.split(",");
//		System.out.println(strList.length);
//		for(int i = 0; i < strList.length; i++){
//			System.out.println(strList[i]);
//		}
//		for(String s : strList){
//			System.out.println("a" + s);
//		}
	}
}