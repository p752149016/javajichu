package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 各种数据结构比较
 * @author PLF
 *
 */
public class DataCompareUtil {

	/**
	 * 获取两个list中不同的元素,list.get(0)为list1中没有的值集合（把list2中与list1相同的值删除后的结果）、list.get(1)为list2中没有的值集合
	 * @param list1
	 * @param list2
	 * @return
	 */
	public static List<List<String>> getListDiffrent(List<String> list1, List<String> list2) {
        long st = System.nanoTime();
        List<String> diff1 = new ArrayList<String>();
        List<String> diff2 = new ArrayList<String>();
        Map<String,Integer> map = new HashMap<String,Integer>(list1.size());
        for (String string : list1) {
            map.put(string, 1);
        }
        for (String string : list2) {
            if(map.get(string)!=null)
            {
                map.put(string, 2);
                continue;
            }
            diff1.add(string);
        }
        for(Map.Entry<String, Integer> entry:map.entrySet())
        {
            if(entry.getValue()==1)
            {
                diff2.add(entry.getKey());
            }
        }
        List<List<String>> dif = new ArrayList<>();
        dif.add(diff1);
        dif.add(diff2);
        System.out.println("getDiffrent total times "+(System.nanoTime()-st));
        return dif;
        
    }
}
