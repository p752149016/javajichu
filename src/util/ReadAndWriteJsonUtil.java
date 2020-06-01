package util;

import java.io.*;


/**
 * 文件读写工具类
 * @author PLF
 * @date   2020 3.30
 *
 */
public class ReadAndWriteJsonUtil {
    /**
     * 将字符串写入文件
     *
     * @param filePath 文件所在路径
     * @param input 字符串
     * @throws IOException 异常
     */
    public static void writeFile(String filePath, String input) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        PrintWriter out = new PrintWriter(fw);
        out.write(input);
        out.println();
        fw.close();
        out.close();
    }
    /**
     * 读取文本文件内容
     *
     * @param filePath文件所在路径
     * @return 文本内容
     * @throws IOException 异常
     */
    public static String readFile(String filePath) throws IOException {
        StringBuffer sb = new StringBuffer();
        readToBuffer(sb, filePath);
        return sb.toString();
    }

    /**
     * 将文本文件中的内容读入到buffer中
     *
     * @param bufferbuffer
     * @param filePath文件路径
     * @throws IOException异常
     */
    public static void readToBuffer(StringBuffer buffer, String filePath) throws IOException {
        InputStream is = new FileInputStream(filePath);
        String line; // 用来保存每行读取的内容
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        line = reader.readLine(); // 读取第一行
        while (line != null) { // 如果 line 为空说明读完了
            buffer.append(line); // 将读到的内容添加到 buffer 中
            buffer.append("\n"); // 添加换行符
            line = reader.readLine(); // 读取下一行
        }
        reader.close();
        is.close();
    }
}
