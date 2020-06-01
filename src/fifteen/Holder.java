package fifteen;

//简单泛型对象示例（泛型对象通常用于做容器）
public class Holder<T> {
	private T a, b, c;
	public Holder(T a, T b, T c){
		this.a = a;
		this.b = b;
		this.c = c;
	}
	public void setA(T a){
		this.a = a;
	}
	public void setB(T b){
		this.b = b;
	}
	public void setC(T c){
		this.c = c;
	}
	public T getA(){
		return a;
	}
	public T getB(){
		return b;
	}
	public T getC(){
		return c;
	}
	public static void main(String[] args){
		Holder<String> h = new Holder<String>("a", "b", "c");
		System.out.println(h.getA());
		h.setA("aa");
		System.out.println(h.getA());
	}
}
