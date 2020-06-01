package fifteen;

import java.util.HashMap;
import java.util.Map;

class Building{};
class House extends Building{};

public class ClassTypeCapture<T> {
	Class<T> kind;
	Map<String, Class<?>> map = new HashMap<>();
	public ClassTypeCapture(Class<T> kind){
		this.kind = kind;
	}
	public boolean f(Object arg){
		return kind.isInstance(arg);
	}
	public void addType(String typename, Class<?> kind){
		map.put(typename, kind);
	}
	public Object createNew(String typename){
		Class<?> cl = map.get(typename);
		try{
			return cl.newInstance();
		} catch (NullPointerException e){
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args){
		ClassTypeCapture<Building> ctc = new ClassTypeCapture<>(Building.class);
		ctc.addType("Building", Building.class);
		ctc.addType("House", House.class);
		System.out.println(ctc.createNew("Building").getClass());
		System.out.println(ctc.createNew("House").getClass());
	}

}
