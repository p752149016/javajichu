package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import com.google.gson.Gson;

/**
 * HTTP请求链接工具类
 * @author PLF
 * @date   2020 3.30   
 *
 */
public class SendRequestUtil {
    
	/**
	 * get请求
	 * @param url   携带参数的url
	 * @return		响应的字符串数据
	 */
	public static String sendGet(String url){
    	PrintWriter out = null; 
    	BufferedReader in = null;  
    	String result = "";
    	try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接  
	        URLConnection conn = realUrl.openConnection();  
	        // 设置通用的请求属性 
	        conn.setRequestProperty("accept", "*/*");  
	        conn.setRequestProperty("connection", "Keep-Alive");  
	        conn.setRequestProperty("Accept-Charset", "utf-8");  
	        conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
	        conn.setRequestProperty("Content-Type", "text/json");  
	        // 定义BufferedReader输入流来读取URL的响应    
	        in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
	        String line;  
	        while ((line = in.readLine()) != null) {  
	            result += line;  
	        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//使用finally块来关闭输出流、输入流  
	    finally{
	        try{
	            if(out!=null){
	                out.close();  
	            }  
	            if(in!=null){  
	                in.close();  
	            }  
	        }  
	        catch(IOException ex){
	            ex.printStackTrace();  
	        }  
	    }  
	    return result;
    }
	
	
	/**
	 * @param url        链接
	 * @param fileName   保存的zip文件路径（包含文件名）
	 * @param list       下载的文件src集合（post上传的参数）
	 * @return			   文件是否下载成功
	 */
	public static boolean downloadFile(String url, String fileName, List<String> list){
		PrintWriter out = null;
		boolean res = false;
		try{
			URL ourl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) ourl.openConnection();
			// 设置通用的请求属性 
	        conn.setRequestProperty("accept", "*/*");  
	        conn.setRequestProperty("connection", "Keep-Alive");  
	        conn.setRequestProperty("Accept-Charset", "utf-8");  
	        conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
	        conn.setRequestProperty("Content-Type", "application/json;charsetset=UTF-8");  
	        // 发送POST请求必须设置如下两行  
	        conn.setDoOutput(true);
	        conn.setDoInput(true);
	        // 获取URLConnection对象对应的输出流  
	        out = new PrintWriter(conn.getOutputStream());
	        // 发送请求参数  
	        out.print(new Gson().toJson(list));
	        // flush输出流的缓冲  
	        out.flush();  
			//保存请求的数据
			InputStream input = conn.getInputStream();
			FileOutputStream fs = new FileOutputStream(fileName);
			byte[] b = new byte[1024];
			int bytes = 0;
			while((bytes = input.read(b)) != -1){
				fs.write(b, 0, bytes);
			} 
			input.close();
			fs.close();
			res = true;
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		return res;
	}
	
	//post请求模版
    public static String sendPost(String url, String xmlStr){
    	PrintWriter out = null; 
    	BufferedReader in = null;  
    	String result = "";
    	try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接  
	        URLConnection conn = realUrl.openConnection();  
	        // 设置通用的请求属性 
	        conn.setRequestProperty("accept", "*/*");  
	        conn.setRequestProperty("connection", "Keep-Alive");  
	        conn.setRequestProperty("Accept-Charset", "utf-8");  
	        conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
	        conn.setRequestProperty("Content-Type", "text/xml");  //说明自己发送的是什么数据
	        // 发送POST请求必须设置如下两行  
	        conn.setDoOutput(true);
	        conn.setDoInput(true);
	        // 获取URLConnection对象对应的输出流  
	        out = new PrintWriter(conn.getOutputStream());  
	        // 发送请求参数  
	        out.print(xmlStr);  
	        // flush输出流的缓冲  
	        out.flush();  
	        // 定义BufferedReader输入流来读取URL的响应    
	        in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
	        String line;  
	        while ((line = in.readLine()) != null) {  
	            result += line;  
	        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//使用finally块来关闭输出流、输入流  
	    finally{
	        try{
	            if(out!=null){
	                out.close();  
	            }  
	            if(in!=null){  
	                in.close();  
	            }  
	        }  
	        catch(IOException ex){
	            ex.printStackTrace();  
	        }  
	    }  
	    return result;
    }
    
    
    public static void main(String[] args){
    	String json = sendGet("http://localhost:8080/OrderDinner/Introduce?hn=0000a4df7936&way=json");
    	System.out.println(json);
    }
}


