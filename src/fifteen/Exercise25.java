package fifteen;

interface One{
	void methodOne();
}
interface Two{
	void methodTwo();
}

class OneImpl implements One,Two{
	@Override
	public void methodOne() {
		System.out.println("One");
	}
	@Override
	public void methodTwo() {
		System.out.println("Two");
	}
}

public class Exercise25 {

	<T extends One> void a(T t){
		t.methodOne();
	}
	<T extends Two> void b(T t){
		t.methodTwo();
	}
	
	public static void main(String[] args){
		Exercise25 e25 = new Exercise25();
		e25.a(new OneImpl());
		e25.b(new OneImpl());
	}
}
