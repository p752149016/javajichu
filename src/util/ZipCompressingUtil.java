package util;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.zip.*;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * zip格式压缩解压工具类
 * @author PLF
 * @date   2020 3.30
 *
 */
public class ZipCompressingUtil {
	/**
	 * 
	 * 压缩一整个文件夹
	 * @param zipPath //压缩文件保存路径
	 * @param zipFileName  //压缩文件名
	 * @param sourceFilePath  //源文件路径
	 * @throws Exception
	 */
	public static void zip(String zipPath, String zipFileName, String sourceFilePath) throws Exception {
//		System.out.println("开始压缩...");
		String zipFilePath = zipPath + "/" + zipFileName;
		//判断压缩文件保存的路径是否存在，如果不存在，则创建目录  
        File zipDir = new File(zipPath);  
        if (!zipDir.exists() || !zipDir.isDirectory())  
        {  
            zipDir.mkdirs();  
        }
		//创建zip输出流
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFilePath));
 
		File sourceFile = new File(sourceFilePath);
		
		//调用函数
		compress(out, sourceFile, sourceFile.getName());
		
		out.close();
//		System.out.println("压缩完成！");
	}


	/**
	 * 压缩多个文件到一个压缩文件里，原文件路径不生成
	 * @param zipPath      //压缩文件保存路径
	 * @param zipFileName  //压缩文件名
	 * @param list         //需要压缩的文件路径集合
	 * @throws Exception
	 */
	public static void zip(String zipPath, String zipFileName, List<String> list) throws Exception{
		//压缩文件完整保存路径（含文件名）
		String zipFilePath = zipPath + "/" + zipFileName;
		//创建zip输出流
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFilePath));
		
		//防止重名
		HashMap<String, Integer> map = new HashMap<>();
		//调用压缩方法
		for(String s : list){
			File file = new File(s);
			if(map.get(file.getName()) == null){
				compress(out, file, file.getName());
				map.put(file.getName(), 1);
			}
		}
		System.out.println(map);
		out.close();
	}
	
	/**
	 * 压缩多个文件到一个压缩文件里并放到输出流中（不能是压缩文件输出流），原文件路径不生成
	 * @param os   //输出流，会把压缩的文件放到这个输出流中 如：文件输出流，就会放到在这个流中并保存到硬盘上
	 * @param list //需要压缩的文件路径集合
	 * @throws Exception
	 */
	public static void zip(OutputStream os, List<String> list) throws Exception{
		//创建zip输出流
		ZipOutputStream out = new ZipOutputStream(os);
		
		//防止重名
		HashMap<String, Integer> map = new HashMap<>();
		//调用压缩方法
		for(String s : list){
			File file = new File(s);
			if(map.get(file.getName()) == null){
				compress(out, file, file.getName());
				map.put(file.getName(), 1);
			}
		}
		System.out.println("压缩的文件名： " + map);
		out.close();
	}
	
	/**
	 * 
	 * @param out //压缩文件存储对象
	 * @param sourceFile  //源文件对象
	 * @param base  //压缩文件在zip中的相对路径   如：文件夹名/文件名
	 * @throws Exception
	 */
	public static void compress(ZipOutputStream out, File sourceFile, String base) throws Exception {
		//如果路径为目录（文件夹）
		if(sourceFile.isDirectory()) {
			//取出文件夹中的文件（或子文件夹）
			File[] flist = sourceFile.listFiles();
			
			if(flist.length==0) {//如果文件夹为空，则只需在目的地zip文件中写入一个目录进入点
//				System.out.println(base + File.separator);
				out.putNextEntry(new ZipEntry(base + File.separator));
			} else {//如果文件夹不为空，则递归调用compress,文件夹中的每一个文件（或文件夹）进行压缩
				for(int i=0; i<flist.length; i++) {
					compress(out, flist[i], base+File.separator+flist[i].getName());
				}
			}
		} else {
			out.putNextEntry(new ZipEntry(base));
			FileInputStream fos = new FileInputStream(sourceFile);
	        BufferedInputStream bis = new BufferedInputStream(fos);
	        int len;
	        
	        byte[] buf = new byte[1024];
//	        System.out.println(base);
	        while((len=bis.read(buf, 0, 1024)) != -1) {
	        	out.write(buf, 0, len);
	        }
	        bis.close();
	        fos.close();
		}
	}
	
	/**
	 * 解压zip文件
	 * @param srcFile           //压缩zip文件
	 * @param destDirPath		//解压路径
	 * @throws RuntimeException
	 */
	public static void unZip(File srcFile, String destDirPath) throws RuntimeException {

	    long start = System.currentTimeMillis();

	    // 判断源文件是否存在
	    if (!srcFile.exists()) {
	        throw new RuntimeException(srcFile.getPath() + "所指文件不存在");
	    }

	    // 开始解压
	    ZipFile zipFile = null;

	    try {
	        zipFile = new ZipFile(srcFile);
	        Enumeration<?> entries = zipFile.entries();
	        while (entries.hasMoreElements()) {
	            ZipEntry entry = (ZipEntry) entries.nextElement();
	            System.out.println("解压" + entry.getName());

	            // 如果是文件夹，就创建个文件夹
	            if (entry.isDirectory()) {
	                String dirPath = destDirPath + "/" + entry.getName();
	                File dir = new File(dirPath);
	                dir.mkdirs();
	            } else {
	                // 如果是文件，就先创建一个文件，然后用io流把内容copy过去
	                File targetFile = new File(destDirPath + "/" + entry.getName());
	                // 保证这个文件的父文件夹必须要存在
	                if(!targetFile.getParentFile().exists()){
	                    targetFile.getParentFile().mkdirs();
	                }
	                targetFile.createNewFile();

	                // 将压缩文件内容写入到这个文件中
	                InputStream is = zipFile.getInputStream(entry);
	                FileOutputStream fos = new FileOutputStream(targetFile);
	                int len;
	                byte[] buf = new byte[1024];
	                while ((len = is.read(buf)) != -1) {
	                    fos.write(buf, 0, len);
	                }

	                // 关流顺序，先打开的后关闭
	                fos.close();
	                is.close();
	            }
	        }

	        long end = System.currentTimeMillis();
	        System.out.println("解压完成，耗时：" + (end - start) +" ms");

	    } catch (Exception e) {
	        throw new RuntimeException("unzip error from ZipUtils", e);
	    } finally {
	        if(zipFile != null){
	            try {
	                zipFile.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }

	        }
	    }
	}
	
	public static void main(String[] args) {
		try {
//			zip("C:/Users/Administrator/Desktop", "RoomService.zip", "C:/Users/Administrator/Desktop/RoomService");
			List<String> list = new ArrayList<>();
			list.add("C:/Users/Administrator/Desktop/Web前端/json/test.json");
			list.add("C:/Users/Administrator/Desktop/Web前端/json/test.json.bak");
			list.add("C:/Users/Administrator/Desktop/Web前端/css/nav.css");
			zip("C:/Users/Administrator/Desktop", "test.zip",list);
			String md5 = DigestUtils.md5Hex(new FileInputStream("C:/Users/Administrator/Desktop/test.zip"));//生成md5
			System.out.println(md5);
			unZip(new File("C:/Users/Administrator/Desktop/test.zip"), "C:/Users/Administrator/Desktop");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}