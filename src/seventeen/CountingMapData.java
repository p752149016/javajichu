package seventeen;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

//生成可供测试的Map对象（可以具有任意尺寸）
public class CountingMapData extends AbstractMap<Integer, String>{
	private int size;
	private static String[] chars = "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z".split(" ");
	public CountingMapData(int size){
		if(size < 0){
			this.size = 0;
		}else{
			this.size = size;
		}
	}
	private static class Entry implements Map.Entry<Integer, String>{
		int index;
		Entry(int index){
			this.index = index;
		}
		@Override
		public boolean equals(Object o){
			return Integer.valueOf(index).equals(o);
		}
		@Override
		public Integer getKey() {
			return index;
		}
		@Override
		public String getValue() {
			return chars[index % chars.length] + Integer.toString(index / chars.length);
		}
		@Override
		public String setValue(String arg0) {
			throw new UnsupportedOperationException();
		}
		@Override
		public int hashCode(){
			return Integer.valueOf(index).hashCode();
		}
	}
//	@Override
//	public Set<java.util.Map.Entry<Integer, String>> entrySet() {
//		Set<Map.Entry<Integer, String>> entries = new LinkedHashSet<Map.Entry<Integer, String>>();
//		for(int i = 0; i < size; i++){
//			entries.add(new Entry(i));
//		}
//		return entries;
//	}
	//使用了享元模式
	class EntrySet extends AbstractSet<Map.Entry<Integer, String>>{
		private class Iter implements Iterator<Map.Entry<Integer, String>>{
			private Entry entry = new Entry(-1);
			@Override
			public boolean hasNext() {
				return entry.index < size - 1;
			}
			@Override
			public java.util.Map.Entry<Integer, String> next() {
				entry.index++;
				return entry;
			}
			@Override
			public void remove() {throw new UnsupportedOperationException();}
		}
		@Override
		public Iterator<java.util.Map.Entry<Integer, String>> iterator() {return new Iter();}
		@Override
		public int size() {return size;}
	}
	private Set<Map.Entry<Integer, String>> entries = new EntrySet();
	@Override
	public Set<java.util.Map.Entry<Integer, String>> entrySet() {return entries;}
	
	public static void main(String[] args){
		System.out.println(new CountingMapData(60));
	}
}
