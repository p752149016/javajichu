package fourteen;

interface Interface{
	void doSomething();
	void somethingElse(String arg);
}

class RealObject implements Interface{

	@Override
	public void doSomething() {
		System.out.println("doSomething");
	}

	@Override
	public void somethingElse(String arg) {
		System.out.println("somethingElse " + arg);
	}
	
}

class SimpleProxy implements Interface{
	private Interface proxied;
	public SimpleProxy(Interface proxied){
		this.proxied = proxied;
	}
	@Override
	public void doSomething() {
		System.out.println("SimpleProxy doSomething");
		long start = System.nanoTime();
	    proxied.doSomething();
	    long duration = System.nanoTime() - start;
	    System.out.println("METHOD-CALL TIME: " + duration);
	}

	@Override
	public void somethingElse(String arg) {
		System.out.println("SimpleProxy somethingElse");
		long start = System.nanoTime();
		proxied.somethingElse(arg);
		long duration = System.nanoTime() - start;
	    System.out.println("METHOD-CALL TIME: " + duration);
	}
	
}

public class SimpleProxyDemo {
	public static void consumer(Interface iface){
		iface.doSomething();
		iface.somethingElse("bonobo");
	}
	public static void main(String[] args){
		consumer(new RealObject());
		Interface face = new SimpleProxy(new RealObject());
		consumer(face);
	}
}
