package fifteen;

//简单泛型方法示例
public class GenericMethods {
	public <T,U> void f(T t, U u, String i){
		System.out.println(t.getClass().getName());
		System.out.println(u.getClass().getName());
		System.out.println(i.getClass().getName());
	}
	public static void main(String[] args){
		GenericMethods gm = new GenericMethods();
		gm.f("",1,"asdas");
	}

}
