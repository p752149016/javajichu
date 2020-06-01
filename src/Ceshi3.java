import io.FangWen;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

abstract class Shape {
	boolean leixing = false;
	void highlight(){leixing = true;}
	void clearhighlight(){leixing = false;}
  void draw() { System.out.println(this + ".draw()"); }
  abstract public String toString();
}

class Circle extends Shape {
  public String toString() { 
	  if(leixing)
		  return "beibiaoji";
	return "Circle"; }
}

class Square extends Shape {
  public String toString() { return "Square"; }
}

class Triangle extends Shape {
  public String toString() { return "Triangle"; }
}	

public class Ceshi3 {
	/**
	 * 获取输入类的所在继承体系中的所有类
	 * @param c 类
	 */
	public static void printType(Class<?> c){
		if(c != null){
			for(Class<?> k : c.getInterfaces()) {
				System.out.println("Interface: " + k.getName());
				printType(k.getSuperclass());
			}
			System.out.println(c.getName());
			printType(c.getSuperclass());
		}
	}
	/**
	 * 使用类中的方法，无论是否私有
	 * @param a 传入的类
	 * @param methodName  使用的方法名
	 * @throws Exception
	 */
	public static void callHiddenMethod(Object a, String methodName) throws Exception{
		Method m = a.getClass().getDeclaredMethod(methodName);
		m.setAccessible(true);
		m.invoke(a);
		
	}
  public static void main(String[] args) throws Exception {
    //获得String类的继承体系类信息
//    printType(new String().getClass());
	  
//	  printType(Class.forName("java.lang.reflect.Proxy"));
	  
	  //通过反射使用私有方法
//    FangWen fangwen = new FangWen();
//    callHiddenMethod(fangwen, "a");
//    callHiddenMethod(fangwen, "b");
//    callHiddenMethod(fangwen, "c");
	  
	  //时间格式
//	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	  System.out.println(sdf.format(new Date()));
    List<Shape> shapeList = Arrays.asList(
      new Circle(), new Square(), new Triangle()
    );
    
    
    for(Shape shape : shapeList){
    	if(shape instanceof Circle){
    		shape.highlight();
    	}
    }
    for(Shape shape : shapeList)
      shape.draw();
    
    try {
		Class<?> c = Class.forName("java.lang.reflect.Proxy");
		for(Method method : c.getMethods()){
			System.out.println(method);
		}
		for(Constructor con : c.getConstructors()){
			System.out.println(con);
		}
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

  }
}
