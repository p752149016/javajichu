package fifteen;

interface ErasedInterface{
	void a();
	void b();
}

class ErasedImpl implements ErasedInterface{

	@Override
	public void a() {
		System.out.println("a");
	}

	@Override
	public void b() {
		System.out.println("b");
	}
	
	public void c(){
		System.out.println("c");
	}
	
}

public class Erased {
	public static <T extends ErasedInterface> void test(T t){
		t.a();
		t.b();
	}
	public static void main(String[] args){
		ErasedImpl erased = new ErasedImpl();
		test(erased);
	}

}
