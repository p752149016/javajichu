package fifteen;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class ContainerMethodDifferences {
	static Set<String> methodSet(Class<?> type){
		Set<String> result = new TreeSet<>();
		for(Method m : type.getMethods()){
			result.add(m.getName());
		}
		return result;
	}
	static void interfaces(Class<?> type){
		System.out.print("Interfaces in" + type.getSimpleName() + ":");
		List<String> result = new ArrayList<>();
		for(Class<?> c : type.getInterfaces()){
			result.add(c.getSimpleName());
		}
		System.out.println(result);
	}
	static Set<String> object = methodSet(Object.class);
	static{
		object.add("clone");
	}
	static void difference(Class<?> superset, Class<?> subset){
		System.out.print(superset.getSimpleName() + " extends " + subset.getSimpleName() + ", adds:");
		Set<String> comp = methodSet(superset);
		comp.removeAll(methodSet(subset));
		comp.removeAll(object);
		System.out.println(comp);
		interfaces(superset);
	}
	
	public static void main(String[] args){
		methodSet(Collection.class);
		interfaces(Collection.class);
		difference(Set.class, Collection.class);
		difference(HashSet.class, Set.class);
		difference(TreeSet.class, Map.class);
	}

}
