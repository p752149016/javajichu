package util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * HashMap序列化和返序列化工具类
 * @author PLF
 * @date   2020 3.30
 *
 */
public class HashMapSerilizable {
    /**
     * 将HashMap序列化为字符串存入json文件中
     * @param objMap            Map对象
     * @param OutfilePathName   序列化的文件路径
     * @return
     * @throws IOException
     */
    public static String serilizableForMap(Object objMap, String OutfilePathName)
            throws IOException {

        String listString = JSON.toJSONString(objMap, true);// (maps,CityEntity.class);
        ReadAndWriteJsonUtil.writeFile(OutfilePathName, listString);
        return listString;
    }

    /**
     * 将json文件中的内容读取出来，反序列化为HashMap
     * @param InputfilePathName    读取的文件路径
     * @return
     * @throws IOException
     */
    public static <T, K> HashMap<K, T> deserilizableForMapFromFile(String InputfilePathName) throws IOException {
        String listString2 = ReadAndWriteJsonUtil.readFile(InputfilePathName);
        Map<K, T> map = JSON.parseObject(listString2, new TypeReference<Map<K, T>>() {});
        return (HashMap<K, T>) map;
    }
    
    
    public static void main(String[] args) throws IOException{
    	HashMap<String, String> map = deserilizableForMapFromFile("C://Users/Administrator/Desktop/工作/aaa/test.json");
    	System.out.println(map);
    	String a = map.get("data");
    	System.out.println(a);
    	System.out.println(JSON.parseObject(a, new TypeReference<Map<String, String>>() {}));
    }
}
